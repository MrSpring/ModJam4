package dk.mrspring.kitchen.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoard extends TileEntity
{
	private ItemStack[] layers;
	
	public TileEntityBoard()
	{
		this.layers = new ItemStack[] { new ItemStack(Items.apple), new ItemStack(Items.arrow), new ItemStack(Items.baked_potato), new ItemStack(Items.bed) };
	}
	
	public ItemStack[] getLayers()
	{
		return this.layers;
	}
}
