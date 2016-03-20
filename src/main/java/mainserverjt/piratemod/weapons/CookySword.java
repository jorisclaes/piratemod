package mainserverjt.piratemod.weapons;

import cpw.mods.fml.common.registry.GameRegistry;
import mainserverjt.piratemod.PiratemodMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class CookySword extends ItemSword {
	
	//dit voodbeeld is op :)
		private static final String name = "CookySword";
		private static int harvestLevel = 4; //dit zecht wat je kan harvesten
		private static int maxUses = 2000;//hoe hoger hoelanger hij megaat saat voor blok gemied of mob gehit
		private static float efficiency = 20.0F;//zegdt hoeveel hij blockt
		private static float damage = 5.0F;//ze de damage dat die doet
		private static int enchantability = 30;//zet iest bu
		private static final Item.ToolMaterial mat = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);

	public CookySword() {
		super(mat);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		setTextureName(PiratemodMain.modID + ":" + name);
		setCreativeTab(PiratemodMain.tab);
		
	}

}
