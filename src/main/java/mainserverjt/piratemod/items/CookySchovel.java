package mainserverjt.piratemod.items;

import com.google.common.util.concurrent.SettableFuture;

import cpw.mods.fml.common.registry.GameRegistry;
import mainserverjt.piratemod.PiratemodMain;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.util.EnumHelper;

public class CookySchovel extends ItemPickaxe {
	
	//dit voodbeeld is op :)
	private static final String name = "CookySchovel";
	private static int harvestLevel = 4; //dit zecht wat je kan harvesten
	private static int maxUses = 2000;//hoe hoger hoelanger hij megaat saat voor blok gemied of mob gehit
	private static float efficiency = 20.0F;//zegdt hoeveel hij blockt
	private static float damage = 5.0F;//ze de damage dat die doet
	private static int enchantability = 1;//zet iest wat bu
	private static final Item.ToolMaterial mat = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
	
	public CookySchovel() {
		super(mat);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		//setTextureName(PiratemodMain.modID + ":" + name);
		setCreativeTab(PiratemodMain.tab);
		setFull3D();//doe dit weg en dan lijkt het niet alsof je een zwaart vast hebt
		setNoRepair();//laat je het item niet repairen
		setContainerItem(PiratemodMain.coocie);//zorg ervoor dat je dit terug krijcht als je iet kraft
		setMaxStackSize(20);//zet de max tack size dheu
		setMaxDamage(50);//zet 
		//setPotionEffect(PotionHelper.sugarEffect);//lees cut
	}
}
