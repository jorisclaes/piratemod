package mainserverjt.piratemod.command;

import java.util.ArrayList;

import mainserverjt.piratemod.Main;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class RegisterHelper {
	
	private Main main;

	public RegisterHelper(Main main) {
		this.main = main;
	}

	protected void addGroup(ICommandSender p_71515_1_, String[] p_71515_2_) {
		if(main.commandMain.status.getStatusQui()){
			if(p_71515_2_.length == 6){
				if(p_71515_1_ instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) p_71515_1_;
					String[] hulp = {
							p_71515_2_[1],player.getDisplayName(),
							p_71515_2_[2],p_71515_2_[3],p_71515_2_[4]
					};//undo comment int echt
					/*for(int i = 1; i< hulp.length; i++){
						for(int j = 1; j < hulp.length; j++){
							if(hulp[i].equals(hulp[j])){
								if(i != j){
									main.commandMain.sendError(p_71515_1_, "geef niet je eigen naam op of meerderekeren de zelfde");
									return;
								}
							}
						}
					}*/
					ArrayList list = (ArrayList) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
					int tel = 0;
					EntityPlayer p = null;
					for(String naam : hulp){
						boolean gevonden = false;
						for(Object o : list){
							p = (EntityPlayer) o;
							if(p.getDisplayName().equals(naam)){
								gevonden = true;
								break;
							}
						}
						if(gevonden){
							gevonden = false;
							main.commandMain.sendMassage(p, player.getDisplayName() + " heeft je toegevoegd aan groep " + p_71515_2_[1]);
							main.commandMain.sendMassage(p, "remove groep met mp remove <groepnaam>");
						}else if (tel != 0){
							main.commandMain.sendError(p_71515_1_, "Player " + naam + " is niet online");
							return;
						}
						tel++;
					}
					main.commandMain.qui.addGroep(hulp);
				}else{
					main.commandMain.sendError(p_71515_1_, "Only a player command");
				}
			}else{
				main.commandMain.sendError(p_71515_1_, "Usage: pm register <groepNaam> <player1> <player2> <player3> <player4>");
			}
		}else{
			main.commandMain.sendError(p_71515_1_, "Qui is closed");
		}
	}
	
	protected void removeGroep(ICommandSender sender,String groepnaam){
		if(main.commandMain.status.getStatusQui()){
			if(sender instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) sender;
				boolean flag = main.commandMain.qui.removeGroep(sender, groepnaam);
				if(flag){
					main.commandMain.sendMassage(sender, "Goup removed");
				}else{
					main.commandMain.sendError(sender, "Goep not found");
				}
			}
		}else{
			main.commandMain.sendError(sender, "Qui is closed");
		}
	}
}
