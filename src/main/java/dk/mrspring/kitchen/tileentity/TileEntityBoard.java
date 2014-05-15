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
		this.layers = new ItemStack[] { new ItemStack(Items.apple), new ItemStack(Items.arrow), new ItemStack(Items.baked_potato), new ItemStack(Items.bed) };
	}
	
	public boolean addLayer(ItemStack toAdd)
	{
		// Sorry, I don't have a microphone. :(
		// My mod allows you to make sandwiches. XD
		// My stream might not be very interesting.
		
		if (this.layerIndex + 1 <= 10)
			{ layers[layerIndex] = toAdd; layerIndex++; return true; }
		else return false;
	}
	
	public ItemStack[] getLayers()
	{
		return this.layers;
	}
}
