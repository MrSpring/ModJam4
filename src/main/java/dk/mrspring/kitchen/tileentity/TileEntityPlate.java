package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.item.ItemSandwich;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.Random;

public class TileEntityPlate extends TileEntity
{
	protected ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	protected double prevYPos = 0.0;
	protected boolean isFull = false;
	protected Random random = new Random();

	public boolean addItem(ItemStack itemStack)
	{
		if (itemStack != null && !this.isFull)
			if (itemStack.getItem() != null)
			{
				if (itemStack.getItem() instanceof ItemSandwich)
				{
					ItemStack item = itemStack.copy();
					item.stackSize = 1;
					this.items.add(item);
					this.isFull = true;
					--itemStack.stackSize;
					return true;
				}
				else // TODO Add pizza, casserole, etc.
				{
					ItemStack item = itemStack.copy();
					double x = (this.random.nextDouble() - 0.5) / 4;
					double y = this.prevYPos + 0.1;
					this.prevYPos += 0.1;
					double z = (this.random.nextDouble() - 0.5) / 4;
					NBTTagCompound posCompound = new NBTTagCompound();
					posCompound.setDouble("X", x);
					posCompound.setDouble("Y", y);
					posCompound.setDouble("Z", z);

					item.setTagInfo("PlatePosition", posCompound);

					this.items.add(item);
					return true;
				}
			}
			else
				return false;
		else
			return false;
	}

	public ItemStack removeTopItem()
	{
		int index = this.items.size();
		ItemStack item = this.items.get(index);

		if (item != null)
			if (item.getItem() != null)
			{
				item.stackTagCompound.removeTag("PlatePosition");
				this.items.remove(index);
				return item;
			}
			else
				return null;
		else
			return null;
	}
}
