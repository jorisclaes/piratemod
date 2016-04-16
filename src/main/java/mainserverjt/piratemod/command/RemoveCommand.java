package mainserverjt.piratemod.command;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.permissions.PermissionsHelper;
import mainserverjt.piratemod.crew.Pirate;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class RemoveCommand {
	
	private static Main main;

	public static void setMain(Main main) {
		RemoveCommand.main = main;
	}

	public static void procesCommand(ICommandSender p_71515_1_, String[] args) {
		if(p_71515_1_ instanceof EntityPlayer){
			//player
			EntityPlayer pl = (EntityPlayer)p_71515_1_;
			Pirate p = null;
			for(Pirate pi : main.getOnlinePirates()){
				if(pl.getUniqueID().equals(pi.getUniekID())){
					p = pi;
					break;
				}
			}
			if(p == null){
				return;
			}
		}else{
			//server
		}
	}

}
