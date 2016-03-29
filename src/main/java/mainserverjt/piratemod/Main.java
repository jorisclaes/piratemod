package mainserverjt.piratemod;

import java.util.ArrayList;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import mainserverjt.piratemod.command.CommandMain;
import mainserverjt.piratemod.crew.Crew;
import mainserverjt.piratemod.crew.Pirate;
import mainserverjt.piratemod.queue.queue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

@Mod(modid = "pm1994", name = "Pirate Mod", version = "0.0.1 pre_Alpha !!experimental!!")
public class Main {
	public static final String modName = "PirateMod";
	public CommandMain commandMain;
	public queue queue;
	private Crew crew;
	public ArrayList<Pirate> onlinePirate;
	
	
		@EventHandler
		public void serverLoad(FMLServerStartingEvent event) {
			commandMain = new CommandMain(this, event);
			onlinePirate = new ArrayList<Pirate>();
		}
		
		@EventHandler
		public void onPlayerlogIn(EntityJoinWorldEvent evt){
			Entity entity = evt.entity;
			if(entity instanceof EntityPlayer){
				EntityPlayer pl = (EntityPlayer) entity;
				Pirate pi = new Pirate(this, pl.getDisplayName(), pl.getUniqueID());
				onlinePirate.add(pi);
			}
		}
		
		
}
