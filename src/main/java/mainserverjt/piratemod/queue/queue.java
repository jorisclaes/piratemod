package mainserverjt.piratemod.queue;

import java.util.ArrayList;
import java.util.HashMap;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import scala.concurrent.Channel.LinkedList;

public class queue {
	
	public Main main;
	private HashMap<String, ICommandSender[]> groepen;
	private HashMap<String, Integer> clanId;
	private static ChatColor color;
	
	private boolean quiInschrijvingenOpen;
	private int timerOpen;
	private Timer timer;
	
	public queue(Main main){
		this.main = main;
		groepen = new HashMap<String, ICommandSender[]>();
		clanId = new HashMap<String, Integer>();
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
	 * @param pesonen ICommandSender[]
	 * @param groepnaam String
	 * @param sender de persoon die iedereen heeft toegevoegt
	 * @param clanId is het id van de clan waar de personen in zitten
	 * @return return true als het gelukt is
	 */
	public boolean addGroep(ICommandSender[] personen, String groepnaam, ICommandSender sender, int clanID){
		if (!groepen.containsKey(groepnaam)){
			for(ICommandSender[] g : groepen.values()){
				for(ICommandSender p : g){
					EntityPlayer player = (EntityPlayer) p;
					for(ICommandSender check : personen){
						EntityPlayer checkPlayer = (EntityPlayer) check;
						if(player.getUniqueID().equals(checkPlayer.getUniqueID())){
							return false;
						}
					}
				}
			}
			groepen.put(groepnaam, personen);
			clanId.put(groepnaam, clanID);
			String text = color.prefix + "U Have Bin Added To Group " + color.groen + color.wit + " By " + color.groen + sender.getCommandSenderName();
			color.sendPrivateMessageToMultiple(personen, text);
			return true;
		}
		return false;
	}
	
	/**
	 * verwijderd een groep als die bestaad
	 * 
	 * @param groepnaam van de groep die verwijdert wordt
	 * @return returnt true als het gelukt is anders false
	 */
	public boolean removeGroep(String groepnaam, ICommandSender sender){
		ICommandSender[] groep = null;
		groep = groepen.remove(groepnaam);
		if(groep != null){
			clanId.remove(groepnaam);
			String text = color.prefix + color.rood +"Your Group Has Bin Removed By " + color.groen + sender.getCommandSenderName();
			color.sendPrivateMessageToMultiple(groep, text);
			return true;
		}
		return false;
	}
}
