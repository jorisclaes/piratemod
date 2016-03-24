package mainserverjt.piratemod.qui;

import java.util.ArrayList;
import java.util.HashMap;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import scala.concurrent.Channel.LinkedList;

public class Qui {
	
	public Main main;
	private HashMap<String, ICommandSender[]> groepen;
	private static ChatColor color;
	
	private boolean quiInschrijvingenOpen;
	private int timerOpen;
	private Timer timer;
	
	public Qui(Main main){
		this.main = main;
		groepen = new HashMap<String, ICommandSender[]>();
		timerOpen = 20;//in sec
		timer = new Timer(main);
	}
	
	/**
	 * zet de inschrijvingen open of toe afhankelijk van de flag
	 * stuurt automatis een message
	 * @param flag inschrijvingen open zetten?
	 */
	public void setQuiOpen(boolean flag){
		this.quiInschrijvingenOpen = flag;
		if(flag){//qui opent
			color.sendBroadcastMessage(color.prefix + color.lichtPaars + "Qui Now Open");		
			color.sendBroadcastMessage(color.prefix + color.groen + "use /pm register to register");
			timer.setTimer(this.timerOpen);
			timer.setRun(true);
		}else{//qui sluit
			color.sendBroadcastMessage(color.prefix + color.lichtPaars + "Qui Now Closed");
			timer.setRun(false);
		}
	}
	
	/**
	 * zet de personen in een groep
	 * 
	 * returnt -1 als de goep of iemand in de groep al eder voorkomt
	 * @param pesonen ICommandSender[]
	 * @param groepnaam String
	 * @return return true als het gelukt is
	 */
	public boolean addGroep(ICommandSender[] personen, String groepnaam){
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
			return true;
		}
		return false;
	}
	
	/**
	 * verwijderd een groep als die bestaad
	 * 
	 * @param groepnaam van de groep die verwijdert wordt
	 * @return returnt true als het gelukt is
	 */
	public boolean removeGroep(String groepnaam, ICommandSender sender){
		ICommandSender[] groep = null;
		groep = groepen.remove(groepnaam);
		if(groep != null){
			String text = color.prefix + color.rood +"Your Group Has Bin Removed By " + color.groen + sender.getCommandSenderName();
			color.sendPrivateMessageToMultiple(groep, text);
			return true;
		}
		return false;
	}
}
