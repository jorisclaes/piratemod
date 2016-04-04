package mainserverjt.piratemod.command.commands;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import mainserverjt.piratemod.command.permissions.PermissionsHelper;
import mainserverjt.piratemod.crew.Pirate;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class HelpCommand {

	private static Main main;
	
	public static final float UseLrvl = PermissionsHelper.f_helpCommand;
	private static String[] bestaandeCommands = {
		"/pm register"	
	};

	public static void processCommand(ICommandSender p_71515_1_, String[] removeIndexUntil) {
		if(p_71515_1_ instanceof EntityPlayer){
			//is player
			Pirate p = null;
			for(Pirate pi : main.getOnlinePirates()){
				if(((EntityPlayer)p_71515_1_).getUniqueID().equals(pi.getUniekID())){
					p = pi;
					break;
				}
			}
			if(p != null && !p.canPirateUseCommand(PermissionsHelper.f_helpCommand)){
				ChatColor.sendPrivateMessage(p_71515_1_, ChatColor.prefix + ChatColor.rood + "U Dont Have Permission");
				return;
			}
			ChatColor.sendPrivateMessage(p_71515_1_, ChatColor.prefix + "List Of Commands:");
			for(String s : bestaandeCommands){
				ChatColor.sendPrivateMessage(p_71515_1_, s);
			}
		}else{
			//is server
		}
	}

	public static void setMain(Main main) {
		HelpCommand.main = main;
	}
}
