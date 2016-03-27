package mainserverjt.piratemod.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import mainserverjt.piratemod.Main;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class CommandMain implements ICommand {
	public Main main;
	private List commands;

	public CommandMain(Main main, FMLServerStartingEvent event) {
		this.main = main;
		event.registerServerCommand((ICommand) this);
		this.commands = new ArrayList();
		this.commands.add("pirateMod");
		this.commands.add("pm");
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
		// TODO Auto-generated method stub
		ChatColor.sendPrivateMessage(p_71515_1_, "test");
		((EntityPlayer)p_71515_1_).getUniqueID();
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

}
