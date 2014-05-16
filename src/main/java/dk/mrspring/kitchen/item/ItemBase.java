package dk.mrspring.kitchen.item;

import static dk.mrspring.kitchen.GameRegisterer.registerItem;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.KitchenBlocks;
import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

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
		registerItem(new ItemBase("knife", true).setMaxStackSize(1));
		registerItem(new ItemSandwichable("bacon_raw", true, 2));
		registerItem(new ItemSandwichBread("bread_slice", true));
		registerItem(new ItemSandwich().setFull3D(), "sandwich");
		registerItem(new ItemTomato());
	}
}
