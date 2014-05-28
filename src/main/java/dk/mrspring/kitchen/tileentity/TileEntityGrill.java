package dk.mrspring.kitchen.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGrill extends TileEntity
{
	protected ItemStack[] items = new ItemStack[4];
	
	public boolean setItem(ItemStack item, int index)
	{
		ItemStack itemStack = item;
		itemStack.stackSize = 1;
		
		if (this.items[index] == null)
		{
			this.items[index] = itemStack;
			return true;
		}
		else
			return false;
	}
	
	public ItemStack getItem(int index)
	{
		return this.items[index];
	}
}
