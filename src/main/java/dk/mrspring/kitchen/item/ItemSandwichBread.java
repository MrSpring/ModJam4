package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.ModInfo;

public class ItemSandwichBread extends ItemSandwichable
{
	public ItemSandwichBread(String name, String textureName, boolean useCreativeTab)
	{
		super(name, textureName, useCreativeTab, 0);
	}
	
	public ItemSandwichBread(String name, boolean useCreativeTab)
	{
		this(name, ModInfo.modid + ":" + name, useCreativeTab);
	}
}
