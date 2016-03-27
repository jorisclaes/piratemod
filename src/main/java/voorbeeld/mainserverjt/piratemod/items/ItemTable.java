package voorbeeld.mainserverjt.piratemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import voorbeeld.mainserverjt.piratemod.PiratemodMain;

public class ItemTable extends ItemSpade {
	
	private static final String name= "ItemSpade";
	private static int harvestLevel = 4; //dit zecht wat je kan harvesten
	private static int maxUses = 2000;//hoe hoger hoelanger hij megaat saat voor blok gemied of mob gehit
	private static float efficiency = 20.0F;//zegdt hoeveel hij blockt
	private static float damage = 5.0F;//ze de damage dat die doet
	private static int enchantability = 30;//zet iest wat bu
	private static final Item.ToolMaterial mat = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
	
	public ItemTable(){
		super(mat);
		this.setUnlocalizedName(this.name).setTextureName(PiratemodMain.modID + ":" + this.name);
		GameRegistry.registerItem(this, this.name);
		setCreativeTab(PiratemodMain.tab);
		setMaxStackSize(64);
	}

	public void itemRegiserResepe(){
		GameRegistry.addRecipe(new ItemStack(this),
		    "WWW",//rij een
		    "WWW",//rij twee
		    "WWW",//rij drie
		    'W',new ItemStack(Blocks.planks));//w = plaks
	}
}
