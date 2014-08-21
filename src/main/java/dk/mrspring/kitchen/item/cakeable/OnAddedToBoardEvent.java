package dk.mrspring.kitchen.item.cakeable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class OnAddedToBoardEvent
{
    public OnAddedToBoardEvent(String label)
    {

    }

    public boolean onRightClick(ItemStack rightClicked, NBTTagCompound specialCompoundInfo, List<ItemStack> itemStacks)
    {
        return false;
    }
}
