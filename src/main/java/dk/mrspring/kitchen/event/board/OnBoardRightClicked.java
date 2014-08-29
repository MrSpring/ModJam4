package dk.mrspring.kitchen.event.board;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by MrSpring on 21-08-14 for MC Music Player.
 */
public class OnBoardRightClicked implements IBoardEvent
{
	@Override
	public String getName()
	{
		return "OnRightClick_" + "UNNAMED";
	}

	@Override
	public boolean trigger(ItemStack itemStack, EntityPlayer player, List<ItemStack> currentStacks)
	{
		return false;
	}

	public boolean addItemStack(ItemStack itemStack, EntityPlayer player, List<ItemStack> currentStacks)
	{
		return false;
	}

	public boolean onRightClick(ItemStack itemStack, EntityPlayer player, List<ItemStack> currentStacks)
	{
		return false;
	}
}
