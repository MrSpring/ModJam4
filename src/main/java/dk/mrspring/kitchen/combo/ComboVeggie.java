package dk.mrspring.kitchen.combo;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ComboVeggie extends SandwichCombo
{
	public ComboVeggie(int ID)
	{
		super(ID, new String[] { "bread_slice", "bread_slice", "carrot_slice", "lettuce_leaf", "tomato_slice", "cheese_slice" });
		this.setRarity(EnumRarity.rare);
	}
	
	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		par1.add("Veggie Sandwich");
	}
	
	@Override
	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.addPotionEffect(new PotionEffect(10, 6 * 20, 1));
	}
}
