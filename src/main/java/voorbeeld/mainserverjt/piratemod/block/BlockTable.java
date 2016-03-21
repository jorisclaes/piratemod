package voorbeeld.mainserverjt.piratemod.block;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import voorbeeld.mainserverjt.piratemod.PiratemodMain;

public class BlockTable extends Block {
	
	private static final String name = "BlockTable";
	
	public BlockTable(){
		super(Material.rock);//set het materiaal
		this.setBlockName(this.name);// set de naam
		GameRegistry.registerBlock(this, this.name);//registreed de block
		this.setBlockTextureName(PiratemodMain.modID + ":" + this.name);//set de textur
		setCreativeTab(PiratemodMain.tab);// voegd het toe aan de creativeTab
		setResistance(5.0F);//zet hoegoed die tegen tnt kan
		setHardness(2.0F);//zet hoelang het duurt voor ie breekt
		setHarvestLevel("pickaxe", 4);//erst welk item het kan mollen en dan welk level het moet zijn om de block terug te krijgen, materiaal gevoelig
		setLightLevel(0.3F);//zet hoeveel licht de block afgeeft van 0.0 tot 1.0
		setLightOpacity(5);//zecht hoeveel lich er mag doorgelaten worden, geen is 16 en hoe minder hoe meer licht er door gaat
		setStepSound(this.soundTypeAnvil);//zet het geluit van de block als je hem plaats of breekt
		this.setHardness(1.0F);//hardness van block duhhhh
	}
	
	public void BlockRegiserResepe(){
		GameRegistry.addRecipe(new ItemStack(this),
		    "RRR",//rij een
		    "MMM",//rij twee
		    "SSS",//rij drie
		    'R',new ItemStack(Blocks.redstone_block),// R = redstone block
		    'M',new ItemStack(Blocks.mossy_cobblestone),// M = mossy stone
		    'S',new ItemStack(Blocks.stone));// S = stone block
	}
	
	@Override
	public boolean isOpaqueCube(){
		return true;
		//als de block doorzichtich is zet die op false
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune){
		return PiratemodMain.itemTable;
	}
	
	@Override
	public int quantityDropped(Random random){
		return random.nextInt(5);
	}
	
}
