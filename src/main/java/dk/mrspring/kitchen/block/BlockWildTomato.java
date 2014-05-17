package dk.mrspring.kitchen.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import dk.mrspring.kitchen.KitchenItems;

public class BlockWildTomato extends BlockBase
{
	public BlockWildTomato()
	{
		super(Material.plants, "wild_tomato", true);
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y - 1, z) == Blocks.grass)
			return true;
		else
			return false;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return KitchenItems.tomato;
	}
	
	@Override
	public int quantityDropped(Random p_149745_1_)
	{
		return 2;
	}
}
