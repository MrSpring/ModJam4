package dk.mrspring.kitchen.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlockPlate extends ItemBlock
{
	public ItemBlockPlate(Block p_i45328_1_)
	{
		super(p_i45328_1_);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (par1ItemStack.stackTagCompound != null)
			if (par1ItemStack.stackTagCompound.getCompoundTag("PlateData") != null)
			{
				NBTTagCompound compound = par1ItemStack.stackTagCompound.getCompoundTag("PlateData");
				NBTTagList itemList = compound.getTagList("Items", 10);

				for (int i = 0; i < itemList.tagCount(); ++i)
				{
					if (i == 9)
						{ list.add("And " + (itemList.tagCount() - i) + " more..."); break; }

					if (itemList.getCompoundTagAt(i) != null)
					{
						NBTTagCompound itemCompound = itemList.getCompoundTagAt(i);
						ItemStack item = ItemStack.loadItemStackFromNBT(itemCompound);
						list.add((i + 1) + ". " + StatCollector.translateToLocal(item.getDisplayName()));
					}
				}
			}
	}
}
