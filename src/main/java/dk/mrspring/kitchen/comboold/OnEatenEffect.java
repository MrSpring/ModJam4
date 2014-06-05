package dk.mrspring.kitchen.comboold;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class OnEatenEffect
{
	int potionID = 0;
	int duration = 0;
	int amplifier = 0;

	public OnEatenEffect(int potionID, int duration, int amplifier)
	{
		this.potionID = potionID;
		this.duration = duration;
		this.amplifier = amplifier;
	}

	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.addPotionEffect(new PotionEffect(this.potionID, this.duration * 20, this.amplifier));
	}
}
