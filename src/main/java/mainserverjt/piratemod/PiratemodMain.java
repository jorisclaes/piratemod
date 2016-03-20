package mainserverjt.piratemod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import mainserverjt.piratemod.armor.Armor;
import mainserverjt.piratemod.block.BlockTable;
import mainserverjt.piratemod.commands.SampleCommand;
import mainserverjt.piratemod.food.Cooky;
import mainserverjt.piratemod.food.GesmoletenCooky;
import mainserverjt.piratemod.generators.CookyGeneration;
import mainserverjt.piratemod.items.CookySchovel;
import mainserverjt.piratemod.items.ItemTable;
import mainserverjt.piratemod.weapons.CookySword;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

@Mod(modid = "pm1994", name = "Pirate Mod", version = "0.0.1 pre_Alpha !!experimental!!")
public class PiratemodMain {

	public final static String modID = "pm1994";
	public final static String modName = "Pirate Mod";

	// items
	public static Item itemTable;

	// food
	public static Item coocie;
	public static Item gesmolte_coocky;

	// tools
	public static Item cooky_schovel;

	// weapon
	public static Item cooky_sword;

	// blocks
	public static Block blockTable;

	// armor
	public static Item helmet;
	public static Item boddy;
	public static Item lagges;
	public static Item bootys;

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		// init van items, block, registeren van allses
		// config handling

		cooky_schovel = new CookySchovel();

		cooky_sword = new CookySword();

		helmet = new Armor("Helmet", 0, 0);
		boddy = new Armor("Boddy", 0, 1);
		lagges = new Armor("Lagges", 0, 2);
		bootys = new Armor("bootys", 0, 3);

		itemTable = new ItemTable();

		coocie = new Cooky();
		gesmolte_coocky = new GesmoletenCooky();

		blockTable = new BlockTable();

		GameRegistry.registerWorldGenerator(new CookyGeneration(), 0);

	}

	@EventHandler
	public void init(FMLInitializationEvent evt) {
		// proxy, tileEntiy, gui and alle classes init$

		((ItemTable) itemTable).itemRegiserResepe();
		((BlockTable) blockTable).BlockRegiserResepe();

		GameRegistry.addSmelting(coocie, new ItemStack(gesmolte_coocky), 5.0F);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new SampleCommand());
	}

	public static CreativeTabs tab = new CreativeTabs(modID) {

		@Override
		public Item getTabIconItem() {
			return new ItemStack(itemTable).getItem();
			// verander itemtable met een fatsoenlijk logo
		}

	};
}
