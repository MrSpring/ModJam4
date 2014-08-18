package dk.mrspring.kitchen.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFridge extends TileEntity
{
	ItemStack[] itemStacks = new ItemStack[4];
	//ItemStack item;

	public boolean addItemStack(ItemStack itemStack)
	{
		for (int i = 0; i < this.itemStacks.length; i++)
		{
			if (this.itemStacks[i] == null)
				return this.addItemStack(i, itemStack);
		} return false;
	}

	public boolean addItemStack(int position, ItemStack itemStack)
	{
		if (itemStack != null && itemStacks[position] == null)
		{
			this.itemStacks[position] = itemStack;
			return true;
		} else return false;

		/*if (itemStack != null)
		{
			if (itemStack.getItem() != null)
			{
				this.itemStacks[position] = itemStack;
				return true;
			} else return false;
		} else return false;*/
	}

	public ItemStack getItemStack(int index)
	{
		return this.itemStacks[index];
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		if (compound.hasKey("Items"))
		{
			NBTTagList itemsCompound = compound.getTagList("Items", 10);
			for (int i = 0; i < itemsCompound.tagCount() && i < this.itemStacks.length; i++)
			{
				NBTTagCompound itemCompound = itemsCompound.getCompoundTagAt(i);
				ItemStack item = ItemStack.loadItemStackFromNBT(itemCompound);

				if (item != null)
					this.itemStacks[i] = item;
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		NBTTagList itemsList = new NBTTagList();

		for (ItemStack itemStack : this.itemStacks)
		{
			NBTTagCompound itemCompound = new NBTTagCompound();

			if (itemStack != null)
				itemsList.appendTag(itemStack.writeToNBT(itemCompound));
		}

		compound.setTag("Items", itemsList);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}
}
