package dk.mrspring.kitchen.combo;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ComboSmartChicken extends SandwichCombo
{
	public ComboSmartChicken(int ID)
	{
		super(ID);
		this.setNames(new String[] { "toast", "chicken_fillet_cooked", "tomato_slice", "lettuce_leaf", "toast" });
		this.setRarity(EnumRarity.uncommon);
	}
	
	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		par1.add("7-Smart Chicken");
	}
	
	@Override
	public int getAdditionalHeal()
	{
		return 1;
	}
	
	@Override
	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.addPotionEffect(new PotionEffect(1, 10 * 20, 0));
	}
}
