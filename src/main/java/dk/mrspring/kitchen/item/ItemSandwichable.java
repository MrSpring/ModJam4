package dk.mrspring.kitchen.item;

import net.minecraft.item.Item;

public class ItemSandwichable extends Item
{
	private int healOnEaten = 1;
	
	public ItemSandwichable(int healAmount)
	{
		super();
		
		this.healOnEaten = healAmount;
	}
}
