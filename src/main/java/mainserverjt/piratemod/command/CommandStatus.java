package mainserverjt.piratemod.command;

import cpw.mods.fml.common.FMLCommonHandler;
import mainserverjt.piratemod.Main;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandStatus {

	private Main main;
	private boolean quiFlag;

	public CommandStatus(Main main) {
		this.main = main;
	}

	public String getStatus(ICommandSender p_71515_1_, String[] p_71515_2_) {
		if (p_71515_2_.length >= 2) {
			if(!p_71515_1_.canCommandSenderUseCommand(4, "PirateMod.status.manage.*")){
				main.commandMain.sendError(p_71515_1_,"U dont have permission");
				return "Command discarded";
			}
			if (p_71515_2_[1].equalsIgnoreCase("qui")) {
				if (p_71515_2_.length == 3) {
					if (p_71515_2_[2].equalsIgnoreCase("open")) {
						setQuiStatusOpen(true);
					} else if (p_71515_2_[2].equalsIgnoreCase("close")) {
						setQuiStatusOpen(false);
					} else {
						return "Usage mp status qui <open,close>";
					}
				}
				return "The qui is: " + getStatusQiu();
			}
		}
		return "Usage mp status <var>";
	}

	protected String getStatusQiu() {
		if (quiFlag) {
			return "Open";
		}
		return "Closed";
	}
	
	protected boolean getStatusQui(){
		return quiFlag;
	}

	private void setQuiStatusOpen(Boolean flag) {
		quiFlag = flag;
		if (flag) {
			ChatComponentText text = new ChatComponentText("[Server:" + main.modName + "] " + "Qui open "
					+ "To registe use: pm register <teamName> <person1> <person2> <person3> <person4>");
			ChatStyle style = new ChatStyle();
			style.setColor(EnumChatFormatting.LIGHT_PURPLE);
			text.setChatStyle(style);
			FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(text);
		} else {
			ChatComponentText text = new ChatComponentText("[Server:" + main.modName + "] " + "Qui is closed\n"
														 + "U cant register anny more");
			ChatStyle style = new ChatStyle();
			style.setColor(EnumChatFormatting.LIGHT_PURPLE);
			text.setChatStyle(style);
			FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(text);
			main.commandMain.qui.clean();
		}
	}
}
