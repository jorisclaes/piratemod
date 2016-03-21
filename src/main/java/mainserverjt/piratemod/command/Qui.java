package mainserverjt.piratemod.command;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import cpw.mods.fml.common.FMLCommonHandler;
import mainserverjt.piratemod.Main;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class Qui {

	private Main main;
	private ArrayList groeppen;
	private int maxAantalGroeppen;
	
	public Qui(Main main) {
		this.main = main;
		groeppen = new ArrayList();
		maxAantalGroeppen = 5;
	}
	
	protected void addGroep(String[] args){
		groeppen.add(args);
	}
	
	/**
	 * returnd true als de groep verwijdert is
	 * @param sender ICommandSender
	 * @param groepnaam String
	 * @return boolean
	 */
	protected boolean removeGroep(ICommandSender sender,String groepnaam){
		for(Object o: groeppen){
			String[] a = (String[]) o;
			if(a[0].equals(groepnaam)){
				for(int i = 1; i < a.length; i++){
					EntityPlayer player = (EntityPlayer) sender;
					if(player.getDisplayName().equals(a[i])){
						for(String naam : a){
							ArrayList list = (ArrayList) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
							for(Object ob : list){
								EntityPlayer p = (EntityPlayer) ob;
								if(p.getDisplayName().equals(naam)){
									main.commandMain.sendGreenMassage(p, "Uw groep is verwijderd");
								}
							}
						}
						groeppen.remove(o);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	protected void clean() {
		groeppen.clear();
	}
	
	protected ArrayList getGroeppen(){
		return groeppen;
	}

}
