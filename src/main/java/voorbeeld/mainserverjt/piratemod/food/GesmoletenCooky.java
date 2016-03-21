package voorbeeld.mainserverjt.piratemod.food;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import voorbeeld.mainserverjt.piratemod.PiratemodMain;

public class GesmoletenCooky extends ItemFood{
	
	private static final String name = "Gesmolte_Kooky";

	public GesmoletenCooky() {
		super(16, 1.0F, false);//hunger, tijdActief, wolfKanEten
		setUnlocalizedName(this.name);
		GameRegistry.registerItem(this, this.name);
		setTextureName(PiratemodMain.modID + ":" + name);
		setCreativeTab(PiratemodMain.tab);
	}

}
