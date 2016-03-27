package voorbeeld.mainserverjt.piratemod.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import voorbeeld.mainserverjt.piratemod.PiratemodMain;

public class SampleCommand implements ICommand {

	private List commands;
	private String name = "pm";

	public SampleCommand() {
		this.commands = new ArrayList();
		this.commands.add("pirateMod");
		this.commands.add("pm");
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return this.name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return this.name + " <txt>";
	}

	@Override
	public List getCommandAliases() {
		return this.commands;
	}

	//nog wat info bij http://jabelarminecraft.blogspot.be/p/minecraft-forge-172.html
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length == 0)
	    {
	      sender.addChatMessage(new ChatComponentText("het werkt"));
	      return;
	    }
	    
		sender.addChatMessage(new ChatComponentText(PiratemodMain.modName + ": [" + args[0] + "]"));
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}

}
