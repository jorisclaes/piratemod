package mainserverjt.piratemod;

import java.util.ArrayList;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import mainserverjt.piratemod.command.CommandMain;
import mainserverjt.piratemod.crew.Crew;
import mainserverjt.piratemod.crew.Pirate;
import mainserverjt.piratemod.event.PlayerEvent;
import mainserverjt.piratemod.queue.queue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

@Mod(modid = "pm1994", name = "Pirate Mod", version = "0.0.1 pre_Alpha !!experimental!!")
public class Main {
	public static final String modName = "PirateMod";
	public CommandMain commandMain;
	public queue queue;
	private Crew crew;
	private ArrayList<Pirate> onlinePirate;
	
	
		@EventHandler
		public void serverLoad(FMLServerStartingEvent event) {
			commandMain = new CommandMain(this, event);
			onlinePirate = new ArrayList<Pirate>();
			FMLCommonHandler.instance().bus().register(new PlayerEvent(this));
		}
		
		
		
		/**
		 * returnt de lijst met online piraaten
		 * @return ArrayList van Pirate
		 */
		public ArrayList<Pirate> getOnlinePirates(){
			return onlinePirate;
		}
		
}
