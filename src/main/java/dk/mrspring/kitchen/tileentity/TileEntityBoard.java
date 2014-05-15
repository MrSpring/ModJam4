package dk.mrspring.kitchen.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoard extends TileEntity
{
	private ItemStack[] layers;
	int layerIndex = 0;
	
	public TileEntityBoard()
	{
		this.layers = new ItemStack[10];
	}
	
	public boolean addLayer(ItemStack toAdd)
	{
		toAdd.stackSize = 1;
		
		if (this.layerIndex + 1 <= 10)
			{ layers[layerIndex] = toAdd; layerIndex++; return true; }
		else return false;
	}
	
	public ItemStack[] getLayers()
	{
		return this.layers;
	}
}
