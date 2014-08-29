package dk.mrspring.kitchen.item.board.sandwichable;

import dk.mrspring.kitchen.model.ModelBreadSliceBottom;
import dk.mrspring.kitchen.model.ModelBreadSliceTop;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemSandwichBread extends ItemSandwichable
{
	ModelBase bottomModel;
	ModelBase topModel;

	public ItemSandwichBread(String name)
	{
		super(name, true, 0);

		this.setIsBread();

		this.bottomModel = new ModelBreadSliceBottom();
		this.topModel = new ModelBreadSliceTop();
	}

	@Override
	public int getRenderHeight(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks)
	{
		if (itemIndex == itemStacks.size() - 1)
			return 3;
		else return 2;
	}

	@Override
	public ModelBase getModel(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks)
	{
		if (itemIndex == itemStacks.size() - 1)
			return this.topModel;
		else return this.bottomModel;
	}
}
