package mainserverjt.piratemod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import mainserverjt.piratemod.command.CommandMain;
import mainserverjt.piratemod.qui.Qui;

@Mod(modid = "pm1994", name = "Pirate Mod", version = "0.0.1 pre_Alpha !!experimental!!")
public class Main {
	public static final String modName = "PirateMod";
	public CommandMain commandMain;
	public Qui qui;
	
		@EventHandler
		public void serverLoad(FMLServerStartingEvent event) {
			commandMain = new CommandMain(this, event);
		}
}
