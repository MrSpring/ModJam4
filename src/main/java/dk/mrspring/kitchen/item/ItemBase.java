package dk.mrspring.kitchen.item;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.item.Item;

import static dk.mrspring.kitchen.GameRegisterer.registerItem;

public class ItemBase extends Item
{
	public ItemBase(String name, String textureName, boolean useCreativeTab)
	{
		super();
		
		this.setUnlocalizedName(name);
		this.setTextureName(textureName);
		
		if (useCreativeTab)
			this.setCreativeTab(Kitchen.instance.tab);
	}
	
	public ItemBase(String name, boolean useCreativeTab) 
	{
		this(name, ModInfo.modid + ":" + name, useCreativeTab);
	}
	
	public static void load()
	{
		registerItem(KitchenItems.knife);
        registerItem(KitchenItems.butter_knife);
		registerItem(KitchenItems.mortar_and_pestle);
		registerItem(KitchenItems.mortar);
		registerItem(KitchenItems.pestle);
		registerItem(KitchenItems.raw_bacon);
		registerItem(KitchenItems.bread_slice);
		GameRegistry.registerCustomItemStack("sandwich_itemstack", KitchenItems.basic_sandwich);
		registerItem(KitchenItems.tomato);
		registerItem(KitchenItems.lettuce);
		registerItem(KitchenItems.tomato_slice);
		registerItem(KitchenItems.lettuce_leaf);
		registerItem(KitchenItems.bacon);
		registerItem(KitchenItems.potato_slice);
		registerItem(KitchenItems.carrot_slice);
		registerItem(KitchenItems.flour);
		registerItem(KitchenItems.toast);
		registerItem(KitchenItems.raw_roast_beef);
		registerItem(KitchenItems.roast_beef);
		registerItem(KitchenItems.raw_chicken_fillet);
		registerItem(KitchenItems.chicken_fillet);
		registerItem(KitchenItems.chicken_leg);
		registerItem(KitchenItems.cheese);
		registerItem(KitchenItems.cheese_slice);
		registerItem(KitchenItems.burnt_meat);
        registerItem(KitchenItems.sliced_creeper);
        registerItem(KitchenItems.cup);
        registerItem(KitchenItems.board_analyzer);
        registerItem(KitchenItems.butter);
        registerItem(KitchenItems.hand_book);
		registerItem(KitchenItems.strawberry);
		registerItem(KitchenItems.strawberry_jam);
		registerItem(KitchenItems.apple_jam);
		//registerItem(KitchenItems.jam_jar);
	}
}
