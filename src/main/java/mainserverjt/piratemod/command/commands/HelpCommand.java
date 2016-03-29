package mainserverjt.piratemod.command.commands;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import mainserverjt.piratemod.command.permissions.PermissionsHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class HelpCommand {

	public static final float UseLrvl = PermissionsHelper.helpCommand;
	private static String[] bestaandeCommands = {
		"/pm register"	
	};

	public static void processCommand(ICommandSender p_71515_1_, String[] removeIndexUntil) {
		if(p_71515_1_ instanceof EntityPlayer){
			
		}else{
			ChatColor.sendPrivateMessage(p_71515_1_, ChatColor.prefix + "List Of Commands:");
			for(String s : bestaandeCommands){
				ChatColor.sendPrivateMessage(p_71515_1_, ChatColor.prefix + s);
			}
		}
	}
}
