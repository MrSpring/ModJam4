package dk.mrspring.kitchen.combo;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ComboBigMac extends SandwichCombo
{
	public ComboBigMac(int ID)
	{
		super(ID);
		this.setNames(new String[] { "bread_slice", "cheese_slice", "roast_beef", "lettuce_leaf", "bread_slice", "roast_beef", "lettuce_leaf", "bread_slice" });
		this.setRarity(EnumRarity.rare);
	}
	
	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		par1.add("There is only one.");
	}
	
	@Override
	public int getAdditionalHeal()
	{
		return 1;
	}
	
	@Override
	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.addPotionEffect(new PotionEffect(2, 20 * 20, 0));
	}
}
