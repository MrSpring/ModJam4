package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.item.ItemSandwich;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.Random;

public class TileEntityPlate extends TileEntity
{
	protected ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	protected double prevYPos = 0.0;
	protected boolean isFull = false;
	protected Random random = new Random();

	public boolean addItem(ItemStack itemStack)
	{
		if (itemStack != null)
			if (itemStack.getItem() != null)
			{
				if (itemStack.getItem() instanceof ItemSandwich)
				{
					if (!this.isFull)
					{
						ItemStack item = itemStack.copy();
						item.stackSize = 1;
						double x = 0.0;
						double y = this.prevYPos + 0.1;
						this.prevYPos -= 0.025;
						double z = 0.0;
						NBTTagCompound posCompound = new NBTTagCompound();
						posCompound.setDouble("X", x);
						posCompound.setDouble("Y", y);
						posCompound.setDouble("Z", z);
						item.setTagInfo("PlatePosition", posCompound);

						this.items.add(item);
						this.isFull = true;
						--itemStack.stackSize;
						return true;
					}
					else
						return false;
				}
				else // TODO Add pizza, casserole, etc.
				{
					ItemStack item = itemStack.copy();
					item.stackSize = 1;
					double x = (this.random.nextDouble() - 0.5) / 8;
					double y = this.prevYPos + 0.1;
					this.prevYPos -= 0.025;
					double z = (this.random.nextDouble() - 0.5) / 8;
					NBTTagCompound posCompound = new NBTTagCompound();
					posCompound.setDouble("X", x);
					posCompound.setDouble("Y", y);
					posCompound.setDouble("Z", z);

					item.setTagInfo("PlatePosition", posCompound);

					this.items.add(item);
					System.out.println(" Returning true when checking normal Item!");
					return true;
				}
			}
			else
			{
				System.out.println(" Returning false when null checking Item!");
				return false;
			}
		else
		{
			System.out.println(" Returning false when null checking ItemStack!");
			return false;
		}
	}

	public ItemStack removeTopItem()
	{
		int index = this.items.size();

		if (index != 0)
		{
			ItemStack item = this.items.get(index - 1);

			if (item != null)
				if (item.getItem() != null)
				{
					item.stackTagCompound.removeTag("PlatePosition");
					this.items.remove(index - 1);

					if (this.isFull)
						this.isFull = false;

					return item;
				}
				else
					return null;
			else
				return null;
		}
		else
			return null;
	}

	public ItemStack[] getItemsAsArray()
	{
		return this.items.toArray(new ItemStack[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		NBTTagList itemList = new NBTTagList();

		for (int i = 0; i < this.items.size(); ++i)
		{
			if (this.items.get(i) != null)
			{
				NBTTagCompound itemCompound = new NBTTagCompound();
				itemCompound = this.items.get(i).writeToNBT(itemCompound);
				itemList.appendTag(itemCompound);
			}
		}

		compound.setTag("Items", itemList);
		compound.setBoolean("IsFull", this.isFull);
		compound.setDouble("LastY", this.prevYPos);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		NBTTagList itemList = compound.getTagList("Items", 10);

		for(int i = 0; i < itemList.tagCount(); ++i)
		{
			if (itemList.getCompoundTagAt(i) != null)
			{
				NBTTagCompound item = itemList.getCompoundTagAt(i);
				ItemStack itemStack = ItemStack.loadItemStackFromNBT(item);
				this.items.add(itemStack);
			}
		}

		this.isFull = compound.getBoolean("IsFull");
		this.prevYPos = compound.getDouble("LastY");
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, compound);
	}
}
