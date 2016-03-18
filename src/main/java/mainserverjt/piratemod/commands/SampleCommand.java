package mainserverjt.piratemod.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return this.name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "pirateMod <var>";
	}

	@Override
	public List getCommandAliases() {
		return this.commands;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender instanceof EntityPlayer) {
			sender.func_145748_c_().appendText("player");
			EntityPlayer p = ((EntityPlayer) sender);
		} else {
			sender.func_145748_c_().appendText("player command only");
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		// TODO Auto-generated method stub
		return false;
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
