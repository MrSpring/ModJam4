package dk.mrspring.kitchen;

import static dk.mrspring.kitchen.GameRegisterer.findItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import dk.mrspring.kitchen.item.ItemKnife;
import dk.mrspring.kitchen.item.ItemSandwich;
import dk.mrspring.kitchen.item.ItemSandwichBread;
import dk.mrspring.kitchen.item.ItemSandwichable;

public class KitchenItems
{
	// public static final Item knife = new ItemKnife("knife", true);
	public static final Item knife = findItem("knife");
	public static final ItemSandwichable bacon_raw = (ItemSandwichable) findItem("bacon_raw");
	public static final ItemSandwichBread bread_slice = (ItemSandwichBread) findItem("bread_slice");
	public static ItemStack basic_sandwich = getSandwichItemStackWithNBTTags(new ItemStack[] { new ItemStack(bread_slice, 1, 0), new ItemStack(bacon_raw, 1, 0), new ItemStack(bread_slice, 1, 0) });
	public static final Item tomato = findItem("tomato");
	public static final Item lettuce = findItem("lettuce");
	public static final ItemSandwichable tomato_slice = (ItemSandwichable) findItem("tomato_slice");
	public static final ItemSandwichable lettuce_leaf = (ItemSandwichable) findItem("lettuce_leaf");
	public static final ItemSandwichable cooked_bacon = (ItemSandwichable) findItem("bacon_cooked");
	
	private static ItemStack getSandwichItemStackWithNBTTags(ItemStack[] layers)
	{
		ItemSandwich sandwich = new ItemSandwich();
		GameRegistry.registerItem(sandwich, "sandwich");
		
		ItemStack basicSandwich = new ItemStack(sandwich, 1, 0);
		
		NBTTagList layersList = new NBTTagList();
		
		for (int i = 0; i < layers.length; ++i)
		{
			NBTTagCompound layerCompound = new NBTTagCompound();
			layers[i].writeToNBT(layerCompound);
			layersList.appendTag(layerCompound);
		}
		
		basicSandwich.setTagInfo("SandwichLayers", layersList);
		
		return basicSandwich;
	}
}
