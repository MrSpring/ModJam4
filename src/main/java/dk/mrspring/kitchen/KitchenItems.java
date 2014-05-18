package dk.mrspring.kitchen;

import static dk.mrspring.kitchen.GameRegisterer.findItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.item.ItemKnife;
import dk.mrspring.kitchen.item.ItemLettuce;
import dk.mrspring.kitchen.item.ItemMandP;
import dk.mrspring.kitchen.item.ItemSandwich;
import dk.mrspring.kitchen.item.ItemSandwichBread;
import dk.mrspring.kitchen.item.ItemSandwichable;
import dk.mrspring.kitchen.item.ItemTomato;
import dk.mrspring.kitchen.model.ModelBaconCooked;
import dk.mrspring.kitchen.model.ModelBaconRaw;
import dk.mrspring.kitchen.model.ModelBreadSliceBottom;
import dk.mrspring.kitchen.model.ModelBreadSliceTop;

public class KitchenItems
{
	public static final Item knife = new ItemKnife().setMaxStackSize(1);
	public static final Item mortar_and_pestle = new ItemMandP().setMaxStackSize(1);
	public static final Item mortar = new ItemBase("mortar", true);
	public static final Item pestle = new ItemBase("pestle", true);
	public static final ItemSandwichable bacon_raw = new ItemSandwichable("bacon_raw", true, 1).setCustomModel(new ModelBaconRaw(), new ModelBaconRaw(), 1, 1);
	public static final ItemSandwichBread bread_slice = (ItemSandwichBread) new ItemSandwichBread("bread_slice", true).setCustomModel(new ModelBreadSliceTop(), new ModelBreadSliceBottom(), 3, 2);
	public static ItemStack basic_sandwich = getSandwichItemStackWithNBTTags(new ItemStack[] { new ItemStack(bread_slice, 1, 0), new ItemStack(bacon_raw, 1, 0), new ItemStack(bread_slice, 1, 0) });
	public static final Item tomato = new ItemTomato();
	public static final Item lettuce = new ItemLettuce();
	public static final ItemSandwichable tomato_slice = new ItemSandwichable("tomato_slice", true, 1);
	public static final ItemSandwichable lettuce_leaf = new ItemSandwichable("lettuce_leaf", true, 2);
	public static final ItemSandwichable cooked_bacon = new ItemSandwichable("bacon_cooked", true, 4).setCustomModel(new ModelBaconCooked(), new ModelBaconCooked(), 2, 2);
	public static final ItemSandwichable potato_slice = new ItemSandwichable("potato_slice", true, 2);
	public static final ItemSandwichable carrot_slice = new ItemSandwichable("carrot_slice", true, 2);
	public static final Item flour = new ItemBase("flour", true);
	public static final ItemSandwichBread toast = new ItemSandwichBread("toast", true);
	public static final ItemSandwichable raw_roast_beef = new ItemSandwichable("raw_roast_beef", ModInfo.modid + ":beef_slice", true, 1);
	public static final ItemSandwichable roast_beef = new ItemSandwichable("roast_beef", true, 4);
	
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
