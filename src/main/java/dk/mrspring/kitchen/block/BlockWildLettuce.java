package dk.mrspring.kitchen.block;

import java.util.Random;

import dk.mrspring.kitchen.KitchenItems;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockWildLettuce extends BlockBase
{
	public BlockWildLettuce()
	{
		super(Material.grass, "wild_lettuce", true);
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return KitchenItems.lettuce;
	}
	
	@Override
	public int quantityDropped(Random p_149745_1_)
	{
		return 2;
	}
}
