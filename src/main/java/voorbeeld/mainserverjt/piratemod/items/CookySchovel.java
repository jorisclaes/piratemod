package voorbeeld.mainserverjt.piratemod.items;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.util.EnumHelper;
import voorbeeld.mainserverjt.piratemod.PiratemodMain;

public class CookySchovel extends ItemPickaxe {
	
	//dit voodbeeld is op :)
	private static  String name = "CookyPickAxe";
	private static int harvestLevel = 4; //dit zecht wat je kan harvesten
	private static int maxUses = 3000;//hoe hoger hoelanger hij megaat saat voor blok gemied of mob gehit
	private static float efficiency = 20.0F;//zegdt hoe geot hij blocks mined
	private static float damage = 10.0F;//ze de damage dat die doet
	private static int enchantability = 30;//zet iest wat bu
	private static Item.ToolMaterial mat = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
	
	public CookySchovel() {
		super(mat);
		setUnlocalizedName(name);
		setTextureName(PiratemodMain.modID + ":" + name);
		setCreativeTab(PiratemodMain.tab);
		GameRegistry.registerItem(this, this.name);
		setFull3D();//doe dit weg en dan lijkt het niet alsof je een zwaart vast hebt
		setNoRepair();//laat je het item niet repairen
		setContainerItem(PiratemodMain.coocie);//zorg ervoor dat je dit terug krijcht als je iet kraft
		setMaxStackSize(20);//zet de max tack size dheu
		setMaxDamage(3000);//de max damad dat die kan nemen == maxUses denk ik
		//setPotionEffect(PotionHelper.sugarEffect);//lees cut
	}
}
