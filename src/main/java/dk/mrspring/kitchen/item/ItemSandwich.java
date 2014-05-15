package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSandwich extends ItemFood
{
	private ItemSandwichable[] layers = new ItemSandwichable[10];
	
	public ItemSandwich(int healAmount, ItemSandwichable[] layers)
	{
		super(healAmount, false);
		this.layers = layers;
		
		this.setUnlocalizedName("sandwich");
		this.setTextureName(ModInfo.modid + ":sandwich");
	}
	
	public static int calculateHealAmount(ItemSandwichable[] layers)
	{
		int totalHealAmount = 0;
		
		for (int i = 0; i < layers.length; ++i)
		{
			totalHealAmount += layers[i].getHealAmount();
		}
		
		return totalHealAmount;
	}
}
