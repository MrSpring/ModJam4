package dk.mrspring.kitchen.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemSandwich extends ItemFood
{
	private ItemSandwichable[] layers = new ItemSandwichable[10];
	
	public ItemSandwich(int healAmount, ItemStack[] layers)
	{
		super(healAmount, false);
	}
}
