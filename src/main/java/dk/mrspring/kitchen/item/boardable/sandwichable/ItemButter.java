package dk.mrspring.kitchen.item.boardable.sandwichable;

import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.item.boardable.ISandwichable;
import dk.mrspring.kitchen.model.butter.ModelButter0;
import dk.mrspring.kitchen.model.butter.ModelButter1;
import dk.mrspring.kitchen.model.butter.ModelButter2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemButter extends ItemSandwichableBase implements ISandwichable
{
    ModelBase[] models = new ModelBase[] { new ModelButter0(), new ModelButter1(), new ModelButter2() };

    public ItemButter(String name)
    {
        super(name, true, 1);
    }

    @Override
    public boolean canAddOnTop(NBTTagCompound specialTagInfo, ItemStack toAdd, ItemStack topItem)
    {
        if (topItem != null)
            if (topItem.getItem() != null)
                if (topItem.getItem() instanceof ItemSandwichBread)
                    return true;

        return false;
    }

    @Override
    public void onAddedToBoard(NBTTagCompound specialTagInfo, ItemStack item)
    {
        specialTagInfo.setInteger("RightClickCount", 2);
    }

    @Override
    public boolean hasSpecialRightClick(NBTTagCompound specialTagInfo)
    {
        if (specialTagInfo.hasKey("RightClickCount"))
            if (specialTagInfo.getInteger("RightClickCount") != 0)
                return true;

        return false;
    }

	@Override
	public void onRightClicked(NBTTagCompound specialTagInfo, ItemStack item)
	{
		if (item != null)
			if (item.getItem() != null)
				if (item.getItem() == KitchenItems.butter_knife)
				{
					int clickCount = specialTagInfo.getInteger("RightClickCount");
					clickCount--;
					specialTagInfo.removeTag("RightClickCount");
					specialTagInfo.setInteger("RightClickCount", clickCount);
				}
	}

    @Override
    public double getRenderHeight(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks)
    {
        if (specialTagInfo != null)
            if (specialTagInfo.hasKey("RightClickCount"))
                if (specialTagInfo.getInteger("RightClickCount") != 0)
                    return 3;

        return 0.5;
    }

    @Override
    public ModelBase getModel(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks)
    {
        if (specialTagInfo!=null)
            if (specialTagInfo.hasKey("RightClickCount"))
                if (specialTagInfo.getInteger("RightClickCount") < this.models.length)
                    return this.models[specialTagInfo.getInteger("RightClickCount")];

        return this.models[0];
    }

	@Override
	public boolean dropItem(NBTTagCompound specialTagInfo, ItemStack removed)
	{
		if (specialTagInfo != null)
		{
			int rightClickCount = specialTagInfo.getInteger("RightClickCount");

			if (rightClickCount == 2)
				return true;
		}
		return false;
	}

	@Override
	public boolean canBeRemoved(NBTTagCompound specialTagInfo, ItemStack toRemove)
	{
		if (specialTagInfo != null)
		{
			int rightClickCount = specialTagInfo.getInteger("RightClickCount");

			if (rightClickCount == 0 || rightClickCount == 2)
				return true;
		}
		return false;
	}
}
