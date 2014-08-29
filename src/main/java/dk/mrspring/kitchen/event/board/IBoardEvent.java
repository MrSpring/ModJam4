package dk.mrspring.kitchen.event.board;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

/**
 * Created by MrSpring on 21-08-14 for MC Music Player.
 */
public interface IBoardEvent
{
	public String getName();

	public boolean trigger(ItemStack itemStack, EntityPlayer player, List<ItemStack> currentStacks);
}
