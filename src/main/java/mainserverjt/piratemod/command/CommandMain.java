package mainserverjt.piratemod.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.commands.HelpCommand;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class CommandMain extends CommandBase implements ICommand {
	public Main main;
	private List commands;

	public CommandMain(Main main, FMLServerStartingEvent event) {
		this.main = main;
		event.registerServerCommand((ICommand) this);
		this.commands = new ArrayList();
		this.commands.add("pirateMod");
		this.commands.add("pm");
		HelpCommand.setMain(main);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "pm";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return "pm";
	}

	@Override
	public List getCommandAliases() {
		// TODO Auto-generated method stub
		return this.commands;
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		if(p_71515_2_.length >= 1){
			if(p_71515_2_[0].equalsIgnoreCase("help")){
				HelpCommand.processCommand(p_71515_1_, removeIndexUntil(1, p_71515_2_));
			}
		}else{
			ChatColor.sendPrivateMessage(p_71515_1_, ChatColor.prefix + ChatColor.rood + "Use /pm help");
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private String[] removeIndexUntil(int index, String[] args){
		if(args.length == index)return null;
		String[] hulp = new String[((args.length - 1) - index)];
		int j = 0;
		for(int i = index ; i < args.length; i++){
			hulp[j] = args[i];
			j += 1;
		}
		return hulp;
	}
}
