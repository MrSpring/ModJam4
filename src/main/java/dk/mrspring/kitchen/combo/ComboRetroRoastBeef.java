package dk.mrspring.kitchen.combo;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ComboRetroRoastBeef extends SandwichCombo
{
	public ComboRetroRoastBeef(int ID)
	{
		super(ID);
		this.setNames(new String[] { "bread_slice", "roast_beef", "roast_beef", "tomato_slice", "lettuce_leaf", "bread_slice" });
		this.setRarity(EnumRarity.uncommon);
	}
	
	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		par1.add("Retro Roastbeef");
	}
	
	@Override
	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.addPotionEffect(new PotionEffect(5, 20 * 20, 1));
	}
}
