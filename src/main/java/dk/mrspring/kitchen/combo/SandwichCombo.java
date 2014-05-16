package dk.mrspring.kitchen.combo;

import java.util.ArrayList;

import dk.mrspring.kitchen.item.ItemSandwichable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SandwichCombo
{
	protected ArrayList<ItemSandwichable> layers = new ArrayList<ItemSandwichable>();
	// TODO Implement PotionEffect things
	
	public SandwichCombo(ItemStack[] items)
	{
		for (int i = 0; i < items.length; ++i)
		{
			if (items[i] != null)
				this.layers.add((ItemSandwichable) items[i].getItem());
		}
	}
	
	public boolean matches(ItemStack sandwich)
	{
		ArrayList<ItemSandwichable> layersInSandwich = new ArrayList<ItemSandwichable>();
		NBTTagList layersList = sandwich.stackTagCompound.getTagList("SandwichLayers", 10);
		
		for (int i = 0; i < layersList.tagCount(); ++i)
		{
			NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
			layersInSandwich.add((ItemSandwichable) ItemStack.loadItemStackFromNBT(layerCompound).getItem());
		}
		
		if (this.layers.containsAll(layersInSandwich) && layersInSandwich.containsAll(this.layers))
			return true;
		else
			return false;
	}
	
	public String getUnlocalizedName()
	{
		return "sandwich.combo.default";
	}
}
