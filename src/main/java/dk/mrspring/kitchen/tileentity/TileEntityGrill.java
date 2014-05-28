package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.KitchenItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGrill extends TileEntity
{
	protected ItemStack[] items = new ItemStack[4];
	protected boolean isOpen = false;
	protected boolean isEmpty = true;

	protected int itemGrillTime = 0;
	protected int coalTimeLeft = 0;

	public final int NOT_FINISHED = 0;
	public final int FINISHED = 1;
	public final int BURNT = 2;
	public final int INCINERATED = 3;

	protected int itemState = NOT_FINISHED;

	public boolean setItem(ItemStack item, int index)
	{
		ItemStack itemStack = item;
		itemStack.stackSize = 1;

		if (item.getItem() == Items.coal)
		{
			this.coalTimeLeft = 1600;
			return true;
		}

		if (!this.isOpen)
			if (FurnaceRecipes.smelting().getSmeltingResult(item).getItem() instanceof ItemFood)
				if (this.items[index] == null)
				{
					this.items[index] = itemStack;
					this.isEmpty = true;
					return true;
				} else
					return false;
			else
				return false;
		else
			return false;
	}

	public ItemStack removeItem(int index)
	{
		if (!this.isEmpty)
		{
			if (this.items[index] != null)
			{
				boolean wasLastItem = true;

				for (int i = 0; i < this.items.length; ++i)
					if (this.items[i] != null)
						wasLastItem = false;

				this.isEmpty = wasLastItem;

				ItemStack item = this.items[index];
				this.items[index] = null;

				return item;
			} else
				return null;
		} else
			return null;
	}

	@Override
	public void updateEntity()
	{
		if (this.coalTimeLeft > 0 && !this.isOpen)
			--this.coalTimeLeft;

		if (!this.isOpen)
			++this.itemGrillTime;
		else
			this.itemGrillTime = 0;

		if (this.itemGrillTime > 200)
			this.itemState = FINISHED;
		else if (this.itemGrillTime > 300)
			this.itemState = BURNT;
		else if (this.itemGrillTime > 450)
			this.itemState = INCINERATED;
		else
			this.itemState = NOT_FINISHED;

		System.out.println(" Current State: " + this.itemState);
		System.out.println(" Current Grill Time: " + this.itemGrillTime);
		System.out.println(" Current Coal Time: " + this.coalTimeLeft);

		if (this.itemState == FINISHED)
			this.finishItems();

		if (this.itemState == BURNT)
			this.burnItems();

		if (this.itemState == INCINERATED)
			this.burnGrill();
	}

	public void finishItems()
	{
		for (int i = 0; i < this.items.length; ++i)
			if (this.items[i] != null)
				this.items[i] = FurnaceRecipes.smelting().getSmeltingResult(this.items[i]);
	}

	public void burnItems()
	{
		for (int i = 0; i < this.items.length; ++i)
			if (this.items[i] != null)
				this.items[i] = new ItemStack(KitchenItems.burnt_meat, 1, 1);
	}

	public void burnGrill()
	{
		// TODO Replace Block in world with Fire, and drop Contents
		System.out.println(" The Grill Incinerated!");
	}

	public boolean getIsOpen()
	{
		return this.isOpen;
	}

	public boolean getIsEmpty()
	{
		return this.isEmpty;
	}

	public void closeLid()
	{
		this.isOpen = false;
	}

	public void openLid()
	{
		this.isOpen = true;
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	public ItemStack getItem(int index)
	{
		return this.items[index];
	}
}
