package mainserverjt.piratemod.event;

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
		String[] permisions = main.permissionHandler.getPermissions(pl.getDisplayName());
		if(permisions != null){
			for(String s : permisions){
				if(p != null){
					float f = PermissionsHelper.permissions.get(s);
					if(p.getPermissionLvl() < f){
						p.setPermissionLvl(f);
					}
				}else{
					System.out.println("player read fout: Permission set FALED");
					break;
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
				if(p.getFunding() == 0 && p.getCrew() == null && p.getRank() == 0 && p.getType() == null){
					main.pirateHandler.saveData(p);
				}
			}
		}
		System.out.println("lenth: " + main.getOnlinePirates().size());
	}
}
