package mainserverjt.piratemod.command.commands;

import java.util.ArrayList;

import mainserverjt.piratemod.Main;
import mainserverjt.piratemod.command.ChatColor;
import mainserverjt.piratemod.command.permissions.PermissionsHelper;
import mainserverjt.piratemod.crew.Crew;
import mainserverjt.piratemod.crew.Pirate;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class RegisterCommand {
	
	private static Main main;
	
	private static float UseLvl = PermissionsHelper.f_registerCommand;
	
	public static void procesCommand(ICommandSender sender, String[] args){
		if (sender instanceof EntityPlayer){
			//player
			Pirate p = null;
			for(Pirate pi : main.getOnlinePirates()){
				if(((EntityPlayer)sender).getUniqueID().equals(pi.getUniekID())){
					p = pi;
					break;
				}
			}
			if (p != null && p.canPirateUseCommand(UseLvl)){
				//heeft toesteming
				if (args.length >= 2){
					if (args[0].equalsIgnoreCase("crew")){
						//maak new crew
						if(args.length == 2){
							if (!p.canPirateUseCommand(PermissionsHelper.f_registerCommandCrew)){
								p.sendMessage(ChatColor.prefix + ChatColor.rood + "You Don't Have Permission");
								return;
							}
							for(Crew c : main.getBestaandeCrews()){
								if(c.getNaamCrew().equals(args[1])){
									p.sendMessage(ChatColor.prefix + ChatColor.rood + "Crew Name " + ChatColor.groen + args[1] + ChatColor.rood + " Alredy Exists");
								}else{
									Crew cr = new Crew(main, main.getBestaandeCrews().size() + 1);
									cr.setNaamCrew(args[1]);
									cr.setStichterUUID(p.getUniekID().toString());
									cr.saveData();
									main.getBestaandeCrews().add(cr);
									return;
								}
							}
						}
					}else if(args.length == 5){
						//nieuwe inschrijveing voor turnooi
						
						//kij of iedereeen onilne is
						
						//voeg groep toe
					}
				}
				p.sendMessage(ChatColor.prefix + ChatColor.rood + "Use /pm register <GroupName> <Player1> <Player2> <Player3> <Player3> To Register Turnement");
				p.sendMessage(ChatColor.prefix + ChatColor.rood + "Use /pm register crew <clanName> To Register A New Crew");
			}else{
				//heeft geen toesteming
				if (p == null){
					return;
				}else{
					p.sendMessage(ChatColor.prefix + ChatColor.rood + "You Don't Have Permission");
				}
			}
		}else{
			//server
		}
	}
	
	public static void setMain(Main main){
		RegisterCommand.main = main;
	}

}
