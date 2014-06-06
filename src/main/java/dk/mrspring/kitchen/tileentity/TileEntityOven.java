package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.KitchenBlocks;
import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.block.BlockOven;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOven extends TileEntity
{
	protected ItemStack[] ovenItems = new ItemStack[4];
	protected int burnTime = 0;
	protected int itemState = 0;

	private final int RAW = 0;
	private final int COOKED = 1;
	private final int BURNT = 2;

	public static final int TOGGLE_OPEN_CLOSE = 0;
	public static final int SET_ACTIVE = 1;
	public static final int SET_INACTIVE = 2;

	protected boolean isOpen = false;
	protected boolean hasCoal = false;

	protected float lidAngle = 0;
	protected float lastLidAngle = lidAngle;

	public boolean addItemStack(ItemStack itemStack)
	{
		if (!worldObj.isRemote)
			worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);

		if (itemStack != null)
			if (itemStack.getItem() != null)
				if (FurnaceRecipes.smelting().getSmeltingResult(itemStack) != null)
				{
					if (FurnaceRecipes.smelting().getSmeltingResult(itemStack).getItem() instanceof ItemFood)
					{
						ItemStack item = itemStack.copy();
						item.stackSize = 1;

						boolean returning = false;

						for (int i = 0; i < this.ovenItems.length; ++i)
						{
							if (this.ovenItems[i] == null)
							{
								this.ovenItems[i] = item;
								returning = true;
								break;
							} else
							{
								if (this.ovenItems[i].getItem() == item.getItem())
								{
									if (this.ovenItems[i].stackSize < 4)
									{
										this.ovenItems[i].stackSize++;
										returning = true;
										break;
									}
								}
							}
						}

						if (returning)
						{
							--itemStack.stackSize;
							return true;
						} else
							return false;
					} else
						return false;
				}
				else  if (itemStack.getItem() == Items.coal)
				{
					this.hasCoal = true;
					--itemStack.stackSize;
					return true;
				}
				else
					return false;
			else
				return false;
		else
			return false;
	}

	@Override
	public void updateEntity()
	{
		lastLidAngle = lidAngle;

		if (this.isOpen())
		{
			if (lidAngle + 0.1F < 1.0)
				lidAngle += 0.1F;
		}
		else
		{
			if (lidAngle - 0.1F > 0.0)
				lidAngle -= 0.1F;
		}

		System.out.println(" Lid Angle: " + lidAngle);

		if (!this.isOpen && this.hasCoal)
			this.burnTime++;
		else
			this.burnTime = 0;

		if (this.burnTime == 0)
			{ this.itemState = RAW; ((BlockOven) KitchenBlocks.oven).updateBlockState(this.worldObj, this.xCoord, this.yCoord, this.zCoord, SET_INACTIVE); }
		else
			((BlockOven) KitchenBlocks.oven).updateBlockState(this.worldObj, this.xCoord, this.yCoord, this.zCoord, SET_ACTIVE);

		if (this.burnTime > 200)
		{
			this.itemState = COOKED;
			this.cookItems();
		}

		if (this.burnTime > 300)
		{
			this.itemState = BURNT;
			this.burnItems();
		}
	}

	public boolean isOpen()
	{
		return isOpen;
	}

	public void setOpen()
	{
		this.isOpen = true;
	}

	public void setClosed()
	{
		this.isOpen = false;
		this.burnTime = 0;
	}

	public void cookItems()
	{
		for(int i = 0; i < this.ovenItems.length; ++i)
		{
			if (this.ovenItems[i] != null)
			{
				if (this.ovenItems[i].getItem() != null)
				{
					int stackSize = this.ovenItems[i].stackSize;
					this.ovenItems[i] = FurnaceRecipes.smelting().getSmeltingResult(this.ovenItems[i]);
					this.ovenItems[i].stackSize = stackSize;
				}
			}
		}
	}

	public void burnItems()
	{
		for(int i = 0; i < this.ovenItems.length; ++i)
		{
			if (this.ovenItems[i] != null)
			{
				if (this.ovenItems[i].getItem() != null)
				{
					int stackSize = this.ovenItems[i].stackSize;
					this.ovenItems[i] = new ItemStack(KitchenItems.burnt_meat, stackSize);
				}
			}
		}
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

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		compound.setShort("CookTime", (short) this.burnTime);
		compound.setBoolean("IsOpen", this.isOpen());
		compound.setBoolean("HasCoal", this.hasCoal);
		compound.setShort("ItemState", (short) this.itemState);

		NBTTagList nbtTagList = new NBTTagList();

		for (int i = 0; i < this.ovenItems.length; ++i)
		{
			if (this.ovenItems[i] != null)
			{
				NBTTagCompound itemCompound = new NBTTagCompound();
				itemCompound.setByte("Slot", (byte) i);
				this.ovenItems[i].writeToNBT(itemCompound);
				nbtTagList.appendTag(itemCompound);
			}
		}

		compound.setTag("Items", nbtTagList);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		this.burnTime = compound.getShort("CookTime");
		this.isOpen = compound.getBoolean("IsOpen");
		this.hasCoal = compound.getBoolean("HasCoal");
		this.itemState = compound.getShort("ItemState");

		this.ovenItems = new ItemStack[4];

		NBTTagList nbtTagList = compound.getTagList("Items", 10);

		for (int i = 0; i < nbtTagList.tagCount(); ++i)
		{
			NBTTagCompound itemCompound = nbtTagList.getCompoundTagAt(i);
			byte slot = itemCompound.getByte("Slot");

			if (slot >= 0 && slot < this.ovenItems.length)
				this.ovenItems[slot] = ItemStack.loadItemStackFromNBT(itemCompound);
		}
	}
}
