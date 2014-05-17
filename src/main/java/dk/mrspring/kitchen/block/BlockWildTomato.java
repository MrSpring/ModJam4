package dk.mrspring.kitchen.block;

import java.util.Random;

import dk.mrspring.kitchen.KitchenItems;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockWildTomato extends BlockFlower
{
	public BlockWildTomato()
	{
		super(field_149862_O);
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
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
