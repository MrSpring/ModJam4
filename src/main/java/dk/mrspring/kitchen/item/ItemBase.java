package dk.mrspring.kitchen.item;

import static dk.mrspring.kitchen.GameRegisterer.registerItem;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelBaconCooked;
import dk.mrspring.kitchen.model.ModelBaconRaw;
import dk.mrspring.kitchen.model.ModelBreadSliceBottom;
import dk.mrspring.kitchen.model.ModelBreadSliceTop;

public class ItemBase extends Item
{
	protected ItemBase(String name, String textureName, boolean useCreativeTab)
	{
		super();
		
		this.setUnlocalizedName(name);
		this.setTextureName(textureName);
		
		if (useCreativeTab)
			this.setCreativeTab(Kitchen.instance.tab);
	}
	
	protected ItemBase(String name, boolean useCreativeTab) 
	{
		this(name, ModInfo.modid + ":" + name, useCreativeTab);
	}
	
	public static void load()
	{
		registerItem(new ItemKnife("knife", true).setMaxStackSize(1));
		registerItem(new ItemSandwichable("bacon_raw", true, 1).setCustomModel(new ModelBaconRaw(), new ModelBaconRaw(), 1, 1));
		registerItem(new ItemSandwichable("bacon_cooked", true, 4).setCustomModel(new ModelBaconCooked(), new ModelBaconCooked(), 2, 2));
		registerItem(new ItemSandwichBread("bread_slice", true).setCustomModel(new ModelBreadSliceTop(), new ModelBreadSliceBottom(), 3, 2));
		GameRegistry.registerCustomItemStack("sandwich_itemstack", KitchenItems.basic_sandwich);
		registerItem(new ItemTomato());
		registerItem(new ItemLettuce());
		registerItem(new ItemSandwichable("tomato_slice", true, 1));
		registerItem(new ItemSandwichable("lettuce_leaf", true, 2));
		registerItem(new ItemSandwichable("potato_slice", true, 2));
		registerItem(new ItemSandwichable("carrot_slice", true, 2));
	}
}
