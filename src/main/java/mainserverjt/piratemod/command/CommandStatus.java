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
	private Timer timer;

	public CommandStatus(Main main) {
		this.main = main;
		timer = new Timer(main);
	}

	public String getStatus(ICommandSender p_71515_1_, String[] p_71515_2_) {
		if (p_71515_2_.length >= 2) {
			if (!p_71515_1_.canCommandSenderUseCommand(4, "PirateMod.status.manage.*")) {
				main.commandMain.sendError(p_71515_1_, "U dont have permission");
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
				main.commandMain.sendGreenMassage(p_71515_1_, "The qui is: " + getStatusQiu());
				return "END";
			}
			if (p_71515_2_[1].equalsIgnoreCase("show")) {
				if (p_71515_2_.length == 2) {
					main.commandMain.sendGreenMassage(p_71515_1_, "Groeppen:");
					for (Object o : main.commandMain.qui.getGroeppen()) {
						String[] r = (String[]) o;
						main.commandMain.sendGreenMassage(p_71515_1_, r[0]);
					}
					return "END";
				}else if(p_71515_2_.length == 3){
					main.commandMain.sendGreenMassage(p_71515_1_, "Deelnemrs:");
					for(Object o : main.commandMain.qui.getGroeppen()){
						String[] r = (String[]) o;
						if(r[0].equals(p_71515_2_[2])){
							main.commandMain.sendGreenMassage(p_71515_1_, r[1] + " " + r[2] + " " + r[3] + " " + r[4]);
						}
					}
					return "END";
				}
			}
		}
		return "Usage mp status list";
	}

	protected String getStatusQiu() {
		if (quiFlag) {
			return "Open";
		}
		return "Closed";
	}

	protected boolean getStatusQui() {
		return quiFlag;
	}

	protected void setQuiStatusOpen(Boolean flag) {
		quiFlag = flag;
		if (flag) {
			main.commandMain.sendBrodcastMesage("Qui open To register use: pm register <teamName> <person1> <person2> <person3> <person4>");
			timer.start();
		} else {
			main.commandMain.sendBrodcastMesage("Qui is closed U cant register anny more");
		}
	}

	public void listComands(ICommandSender p_71515_1_) {
		main.commandMain.sendGreenMassage(p_71515_1_,
				"[qui <open,close>]:suit-open-qui; [qui]:voor status; [show]:retunrt de mee doende groeppen;");
	}
}
