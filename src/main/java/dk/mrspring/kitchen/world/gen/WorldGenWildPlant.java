package dk.mrspring.kitchen.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import dk.mrspring.kitchen.KitchenBlocks;
import dk.mrspring.kitchen.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class WorldGenWildPlant implements IWorldGenerator
{
    Block[] generating = new Block[] { KitchenBlocks.wild_lettuce, KitchenBlocks.wild_tomato };

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.dimensionId == 0)
		{
			int x = (chunkX * 16) + random.nextInt(16);
			int z = (chunkZ * 16) + random.nextInt(16);
			int y = world.getTopSolidOrLiquidBlock(x, z);
			
			int rand = random.nextInt(100);
			
			if (world.getBlock(x, y - 1, z) == Blocks.grass && world.getWorldInfo().getTerrainType() != WorldType.FLAT && rand < ModConfig.plantSpawnRate)
            {
                int generateIndex = random.nextInt(this.generating.length);
                world.setBlock(x, y, z, this.generating[generateIndex]);
            }
		}
	}
}
