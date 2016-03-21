package voorbeeld.mainserverjt.piratemod.generators;

import java.util.Random;

import javax.swing.JOptionPane;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import voorbeeld.mainserverjt.piratemod.PiratemodMain;

public class CookyGeneration implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		//System.out.println(world.provider.dimensionId);
		switch(world.provider.dimensionId){
		case -1:
			generateNether(world, random, chunkX, chunkZ);
			break;
		case 0:
			generateOverworld(world, random, chunkX, chunkZ);//normal
			break;
		case 1:
			generateEnd(world, random, chunkX, chunkZ);
			break;
		}
	}
	
	public void generateNether(World world, Random rand, int x, int z){
		generateOre(PiratemodMain.blockTable, world, rand, x, z, 
				2,//min kluster
				10,//max kluster
				5,//kans van spawn
				0,//bigen hoochte
				100,// maximum hoochte
				Blocks.netherrack);	//waar i gaat spownen
	}

	public void generateOverworld(World world, Random rand, int x, int z){
		generateOre(PiratemodMain.blockTable, world, rand, x, z, 
				2,//min kluster
				10,//max kluster
				5,//kans van spawn
				0,//bigen hoochte
				100,// maximum hoochte
				Blocks.stone);	//waar i gaat spownen
	}
	
	public void generateEnd(World world, Random rand, int x, int z){
		generateOre(PiratemodMain.blockTable, world, rand, x, z, 
				2,//min kluster
				10,//max kluster
				5,//kans van spawn
				0,//bigen hoochte
				100,// maximum hoochte
				Blocks.stone);	//waar i gaat spownen
	}
	
	public void generateOre(Block block, World world, Random random, int x, int z, int minVienSize, int maxVienSize, int chance, int minY, int maxY, Block generateIn){
		int vienSieze = minVienSize + random.nextInt(maxVienSize - minVienSize);
		int heightRange = maxY - minY;
		WorldGenMinable gen = new WorldGenMinable(block, vienSieze, generateIn);
		for(int i = 0; i < chance; i++){
			int xRand = x * 16 + random.nextInt(16);
			int yRand = random.nextInt(heightRange) + minY;
			int zRand = z *16 + random.nextInt(16);
			gen.generate(world, random, xRand, yRand, zRand);
		}
	}
}
