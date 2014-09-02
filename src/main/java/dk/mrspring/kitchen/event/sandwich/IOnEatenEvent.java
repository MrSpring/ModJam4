package dk.mrspring.kitchen.event.sandwich;

import dk.mrspring.kitchen.ModLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by Konrad on 13-07-2014 for The Kitchen Mod.
 */
public interface IOnEatenEvent extends ISandwichEvent
{
	@Override
	public String getName();

	public void onEaten(ItemStack item, World world, EntityPlayer player);
}
