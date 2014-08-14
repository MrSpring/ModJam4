package dk.mrspring.kitchen.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by MrSpring on 14-08-14 for MC Music Player.
 */
public class TileEntityFridge extends TileEntity
{
	ItemStack[] itemStacks = new ItemStack[4];

	public boolean addItemStack(ItemStack itemStack)
	{
		for (int i = 0; i < this.itemStacks.length; i++)
		{
			if (this.itemStacks[i] == null)
				return this.addItemStack(i, itemStack);
		}
		return false;
	}

	public boolean addItemStack(int position, ItemStack itemStack)
	{

	}
}
