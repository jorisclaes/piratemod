package mainserverjt.piratemod.command;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import mainserverjt.piratemod.Main;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandMain implements ICommand{
	
	private final Main main;
	protected CommandStatus status;
	protected RegisterHelper registerHelper;
	protected Qui qui;
	private String[] commands = {"status", "register", "remove"};
	
	public CommandMain(Main main, FMLServerStartingEvent event){
		this.main = main;
		status = new CommandStatus(main);
		registerHelper = new RegisterHelper(main);
		qui = new Qui(main);
		this.regisreCommands(event);
	}

	private void regisreCommands(FMLServerStartingEvent event) {
		event.registerServerCommand(this);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "Pirate Mod";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "PiretMod <var>";
	}

	@Override
	public List getCommandAliases() {
		List list = new ArrayList();
		list.add("Pirate Mod");
		list.add("pm");
		list.add("p");
		return list;
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		if(p_71515_2_.length != 0){
			if(p_71515_2_[0].equalsIgnoreCase("status")){
				p_71515_1_.addChatMessage(new ChatComponentText(status.getStatus(p_71515_1_,p_71515_2_)));
			}else if(p_71515_2_[0].equalsIgnoreCase("register")){
				registerHelper.addGroup(p_71515_1_, p_71515_2_);
			}else if(p_71515_2_[0].equalsIgnoreCase("remove")){
				registerHelper.removeGroep(p_71515_1_, p_71515_2_[1]);
			}else
			{
				String temp = "";
				for(String s : commands){
					temp += s + " ";
				}
				p_71515_1_.addChatMessage(new ChatComponentText(temp));
			}
		}else{
			p_71515_1_.addChatMessage(new ChatComponentText("Use pm list for commands"));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		if(p_71519_1_ instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) p_71519_1_;
			if(player.canCommandSenderUseCommand(4, "PirateMod.use.status.*")){
				return true;
			}
			return false;
		}
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
	
	protected void sendError(ICommandSender p_71515_1_, String string) {
		ChatComponentText text = new ChatComponentText("[PirateMod] " + string);
		ChatStyle style = new ChatStyle();
		style.setColor(EnumChatFormatting.RED);
		text.setChatStyle(style);
		p_71515_1_.addChatMessage(text);
	}

	protected void sendMassage(ICommandSender p_71515_1_, String string) {
		ChatComponentText text = new ChatComponentText("[PirateMod] " + string);
		p_71515_1_.addChatMessage(text);
	}
}
