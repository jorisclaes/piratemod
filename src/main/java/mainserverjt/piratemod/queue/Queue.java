package mainserverjt.piratemod.queue;

import java.util.ArrayList;
import java.util.HashMap;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import mainserverjt.piratemod.crew.Pirate;
import mainserverjt.piratemod.db.tabelInit.PirateTable;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import scala.concurrent.Channel.LinkedList;

public class Queue {
	
	public Main main;
	private HashMap<String, ArrayList> groepen;
	private static ChatColor color;
	
	private boolean quiInschrijvingenOpen;
	private int timerOpen;
	private Timer timer;
	
	public Queue(Main main){
		this.main = main;
		groepen = new HashMap<String, ArrayList>();
		timerOpen = 20;//in sec
		timer = new Timer(main);
	}
	
	/**
	 * zet de inschrijvingen open of toe afhankelijk van de flag
	 * stuurt automatis een message
	 * @param flag inschrijvingen open zetten?
	 */
	public void setQueueOpen(boolean flag){
		this.quiInschrijvingenOpen = flag;
		if(flag){//queue opent
			color.sendBroadcastMessage(color.prefix + color.lichtPaars + "Queue Is Now Open");		
			color.sendBroadcastMessage(color.prefix + color.groen + "use /pm register to register");
			timer.setTimer(this.timerOpen);
			timer.setRun(true);
		}else{//queue sluit
			color.sendBroadcastMessage(color.prefix + color.lichtPaars + "Queue Is Now Closed");
			timer.setRun(false);
		}
	}
	
	/**
	 * zet de personen in een groep
	 * 
	 * returnt false als de goep of iemand in de groep al eder voorkomt
	 * en stuurt een bericht naar de mensen die juist zijn toegevoegd
	 * 
	 * @param tijdelijk ArrayList
	 * @param groepnaam String
	 * @param sender de persoon die iedereen heeft toegevoegt
	 * @return return true als het gelukt is
	 */
	public boolean addGroep(ArrayList<Pirate> tijdelijk, String groepnaam, Pirate sender){
		if (!groepen.containsKey(groepnaam)){
			for(ArrayList<Pirate> g : groepen.values()){
				for(Pirate p : g){
					for(Pirate check : tijdelijk){
						EntityPlayer checkPlayer = check.getPlayer();
						if(p.getUniekID().equals(checkPlayer.getUniqueID())){
							return false;
						}
					}
				}
			}
			groepen.put(groepnaam, tijdelijk);
			String text = color.prefix + "U Have Bin Added To Group " + color.groen + color.wit + " By " + color.groen + sender.getNaam();
			color.sendPrivateMessageToMultiple(tijdelijk, text);
			return true;
		}
		return false;
	}
	
	/**
	 * verwijderd een groep als die bestaad
	 * en stuurd een bericht naar iedereen in de groep
	 * returnt true als het gelukt is
	 * 
	 * @param groepnaam van de groep die verwijdert wordt
	 * @param sender is de persoon die het comando heeft verstuurd
	 * @return returnt true als het gelukt is anders false
	 */
	public boolean removeGroep(String groepnaam, Pirate sender){
		ArrayList groep = null;
		groep = groepen.remove(groepnaam);
		if(groep != null){
			String text = color.prefix + color.rood +"Your Group Has Bin Removed By " + color.groen + sender.getNaam();
			color.sendPrivateMessageToMultiple(groep, text);
			return true;
		}
		return false;
	}
}
