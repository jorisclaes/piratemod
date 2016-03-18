package mainserverjt.piratemod.food;

import cpw.mods.fml.common.registry.GameRegistry;
import mainserverjt.piratemod.PiratemodMain;
import net.minecraft.item.ItemFood;
import scala.reflect.internal.TreeInfo.SeeThroughBlocks;

public class Cooky extends ItemFood{
	
	private static final String name = "Cooky";
	
	public Cooky(){
		super(20, 1.0F, true);//hunger, tijdActief, wolfKanEten
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		setTextureName(PiratemodMain.modID + ":" + name);
		setCreativeTab(PiratemodMain.tab);
	}

}
