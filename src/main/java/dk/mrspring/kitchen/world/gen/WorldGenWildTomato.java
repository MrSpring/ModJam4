package dk.mrspring.kitchen.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import dk.mrspring.kitchen.KitchenBlocks;

public class WorldGenWildTomato implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.dimensionId == 0)
		{
			int x = chunkX + random.nextInt(16);
			int z = chunkZ + random.nextInt(16);
			int y = world.getTopSolidOrLiquidBlock(x, z);
			
			if (world.getBlock(x, y, z) == Blocks.grass)
				world.setBlock(x, y + 1, z, KitchenBlocks.wild_tomato);
		}
	}
}
