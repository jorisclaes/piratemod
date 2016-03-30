package mainserverjt.piratemod.event;

import java.util.UUID;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import mainserverjt.piratemod.crew.Pirate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerEvent {
	
	private Main main;
	
	public PlayerEvent(Main main){
		this.main = main;
	}

	@SubscribeEvent
	public void onLogin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent evt){
		EntityPlayer pl = evt.player;
		for(Pirate pi : main.getOnlinePirates()){
			if (pi.getUniekID().equals(pl.getUniqueID())){
				return;
			}
		}
		ChatColor.sendBroadcastMessage(ChatColor.prefix + "player toegevoegd");
		Pirate pi = new Pirate(main, pl);
		main.getOnlinePirates().add(pi);
		System.out.println("lenth: " + main.getOnlinePirates().size());
	}
	
	@SubscribeEvent
	public void onLogout(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent evt){
		EntityPlayer player = evt.player;
		for(int i = 0; i < main.getOnlinePirates().size(); i++){
			if(player.getUniqueID().equals(main.getOnlinePirates().get(i).getUniekID())){
				Pirate p = main.getOnlinePirates().get(i);
				if(p.getFunding() == 0 && p.getCrew() == null && p.getRank() == 0 && p.getType() == null){
					main.getOnlinePirates().remove(p);
				}
			}
		}
		System.out.println("lenth: " + main.getOnlinePirates().size());
	}
}
