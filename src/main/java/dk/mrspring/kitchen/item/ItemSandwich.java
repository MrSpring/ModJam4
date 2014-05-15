package dk.mrspring.kitchen.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemSandwich extends ItemFood
{
	private ItemSandwichable[] layers = new ItemSandwichable[10];
	
	public ItemSandwich(int healAmount, ItemStack[] layers)
	{
		super(healAmount, false);
		
		for (int i = 0; i < layers.length; ++i)
		{
			if (layers[i] != null)
				this.layers[i] = (ItemSandwichable) layers[i].getItem();
		}
	}
	
	public int calculateHealAmount()
	{
		int totalHealAmount = 0;
		
		for (int i = 0; i < layers.length; ++i)
		{
			totalHealAmount += layers[i].getHealAmount();
		}
		
		return totalHealAmount;
	}
}
