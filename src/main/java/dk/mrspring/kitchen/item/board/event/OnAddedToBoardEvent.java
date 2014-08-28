package dk.mrspring.kitchen.item.board.event;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class OnAddedToBoardEvent implements IBoardEvent
{
    public boolean onRightClick(ItemStack rightClicked, NBTTagCompound specialCompoundInfo, List<ItemStack> itemStacks)
    {
        return false;
    }

    @Override
    public String getName()
    {
        return "UNNAMED:ON_ADDED_TO_BOARD_EVENT";
    }
}
