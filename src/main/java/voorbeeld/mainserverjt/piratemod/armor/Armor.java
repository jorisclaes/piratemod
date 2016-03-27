package voorbeeld.mainserverjt.piratemod.armor;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import voorbeeld.mainserverjt.piratemod.PiratemodMain;

public class Armor extends ItemArmor {

	
	private static int durability = 50000; //is hoeveel keer je kan hitten voor het breekt
	private static int[] reductionAmounts = {20,70,50,30};//damage apsorpsion voor dat u hartjes kapot gaan
	private static int enchantability = 30;//zet iest wat bu
	private static final ArmorMaterial mat = EnumHelper.addArmorMaterial("Armor Mat", durability, reductionAmounts, enchantability);
	private static final String url = PiratemodMain.modID + ":textures/models/armor/";
	private static final String leggingsFileName = "cheese_layer_2.png";//somting .png
	private static final String restArmorFileName = "cheese_layer_1.png";//somting .png
	
	
	public Armor(String name, int renderIndex, int armorType) {
		super(mat, renderIndex, armorType);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		setTextureName(PiratemodMain.modID + ":" + name);
		setCreativeTab(PiratemodMain.tab);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
			if(armorType == 2){
				return url + leggingsFileName;
			}
			return url + restArmorFileName;
	}
}
