package mainserverjt.piratemod.event;

import java.util.ArrayList;
import java.util.UUID;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import mainserverjt.piratemod.command.permissions.PermissionsHelper;
import mainserverjt.piratemod.crew.Pirate;
import mainserverjt.piratemod.db.CrewHandler;
import mainserverjt.piratemod.db.PirateHandler;
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
		Pirate p = main.pirateHandler.loadData(pl);
		ArrayList<String> groeppen = main.permissionHandler.getGroeppen();
		for(String groepNaam : groeppen){
			String[] groepUser = main.permissionHandler.getUsersInGroup(groepNaam);
			String[] rechten = main.permissionHandler.getPermissions(groepNaam);
			if(groepUser == null || rechten == null) break;
			for(String user: groepUser){
				if(user.equals(p.getPlayer().getDisplayName())){
					for(String per : rechten){
						if(p.getPermissionLvl() < PermissionsHelper.permissions.get(per));
						p.setPermissionLvl(PermissionsHelper.permissions.get(per));
					}
				}
			}
		}
		String[] userPer = main.permissionHandler.getPermissions(p.getPlayer().getDisplayName());
		if(userPer != null){
			for(String per : userPer){
				if(p.getPermissionLvl() < PermissionsHelper.permissions.get(per)){
					p.setPermissionLvl(PermissionsHelper.permissions.get(per));
				}
			}
		}
		System.out.println("lenth: " + main.getOnlinePirates().size());
	}
	
	@SubscribeEvent
	public void onLogout(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent evt){
		EntityPlayer player = evt.player;
		for(int i = 0; i < main.getOnlinePirates().size(); i++){
			if(player.getUniqueID().equals(main.getOnlinePirates().get(i).getUniekID())){
				Pirate p = main.getOnlinePirates().get(i);
				main.getOnlinePirates().remove(p);
				if(p.getFunding() != 0 && p.getCrew() != null && p.getRank() != 0 && p.getType() != null){
					main.pirateHandler.saveData(p);
				}
			}
		}
		System.out.println("lenth: " + main.getOnlinePirates().size());
	}
}
