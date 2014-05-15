package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.ModInfo;
import net.minecraft.item.Item;

public class ItemSandwichable extends ItemBase
{
	private int healOnEaten = 1;
	
	public ItemSandwichable(String name, String textureName, boolean useCreativeTab, int healAmount)
	{
		super(name, textureName, useCreativeTab);
		
		this.healOnEaten = healAmount;
	}
	
	public ItemSandwichable(String name, boolean useCreativeTab, int healAmount)
	{
		this(name, ModInfo.modid + ":" + name, useCreativeTab, healAmount);
	}
	
	public int getHealAmount()
	{
		return this.healOnEaten;
	}
}
