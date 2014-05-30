package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.KitchenItems;
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

public class TileEntityGrill extends TileEntity
{
	protected ItemStack[] items = new ItemStack[4];
	protected boolean isOpen = false;
	protected boolean isEmpty = true;

	protected int itemGrillTime = 0;
	protected int coalTimeLeft = 0;

	public final int NOT_FINISHED = 0;
	public final int FINISHED = 1;
	public final int BURNT = 2;
	public final int INCINERATED = 3;

	protected int itemState = NOT_FINISHED;

	public boolean setItem(ItemStack item, int index)
	{
		ItemStack itemStack = item;
		itemStack.stackSize = 1;

		if (item.getItem() == Items.coal)
		{
			this.coalTimeLeft = 1600;
			return true;
		}

		if (this.isOpen)
            if (FurnaceRecipes.smelting().getSmeltingResult(item) != null)
    			if (FurnaceRecipes.smelting().getSmeltingResult(item).getItem() instanceof ItemFood)
                    if (this.items[index] == null)
                    {
                        this.items[index] = itemStack;
                        this.isEmpty = true;

                        if (this.items[0] != null)
                            System.out.println("1: " + this.items[0].getDisplayName());
                        if (this.items[1] != null)
                            System.out.println("2: " + this.items[1].getDisplayName());
                        if (this.items[2] != null)
                            System.out.println("3: " + this.items[2].getDisplayName());
                        if (this.items[3] != null)
                            System.out.println("4: " + this.items[3].getDisplayName());

                        return true;
                    } else
                        return false;
                else
                    return false;
            else
                return false;
        else
            return false;
	}

    public ItemStack[] getItems()
    {
        return this.items;
    }

	public void removeItem(int index)
	{
		this.items[index] = null;
	}

    public ItemStack getItem(int index)
    {
        return this.items[index];
    }

	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			if (this.coalTimeLeft > 0)
				--this.coalTimeLeft;

			if (!this.isOpen && this.coalTimeLeft > 0)
				++this.itemGrillTime;
			else
				this.itemGrillTime = 0;

			if (this.itemGrillTime > 200)
				this.itemState = FINISHED;
			else if (this.itemGrillTime > 300)
				this.itemState = BURNT;
			else if (this.itemGrillTime > 450)
				this.itemState = INCINERATED;
			else
				this.itemState = NOT_FINISHED;

			if (this.itemState == FINISHED)
				this.finishItems();

			if (this.itemState == BURNT)
				this.burnItems();

			if (this.itemState == INCINERATED)
				this.burnGrill();
		}
	}

	public void finishItems()
	{
		for (int i = 0; i < this.items.length; ++i)
			if (this.items[i] != null)
				this.items[i] = FurnaceRecipes.smelting().getSmeltingResult(this.items[i]);
	}

	public void burnItems()
	{
		for (int i = 0; i < this.items.length; ++i)
			if (this.items[i] != null)
				this.items[i] = new ItemStack(KitchenItems.burnt_meat, 1, 1);
	}

	public void burnGrill()
	{
		// TODO Replace Block in world with Fire, and drop Contents
		System.out.println(" The Grill Incinerated!");
	}

	public boolean getIsOpen()
	{
		return this.isOpen;
	}

	public boolean getIsEmpty()
	{
		return this.isEmpty;
	}

	public void closeLid()
	{
		this.isOpen = false;
	}

	public void openLid()
	{
		this.isOpen = true;
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        System.out.println(" Reading from NBT!");

        this.items = new ItemStack[4];

        NBTTagList nbtTagList = nbtTagCompound.getTagList("Items", 10);
        for(int i = 0; i < nbtTagList.tagCount(); ++i)
        {
            NBTTagCompound itemCompound = nbtTagList.getCompoundTagAt(i);
            this.items[i] = ItemStack.loadItemStackFromNBT(itemCompound);
        }

        this.itemGrillTime = nbtTagCompound.getByte("GrillTime");
        this.coalTimeLeft = nbtTagCompound.getByte("CoalTime");
        this.isOpen = nbtTagCompound.getBoolean("IsOpen");
        this.isEmpty = nbtTagCompound.getBoolean("IsEmpty");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        System.out.println(" Writing to NBT!");

        NBTTagList itemList = new NBTTagList();

        for(int i = 0; i < this.items.length; ++i)
        {
            if (this.getItem(i) != null)
            {
                NBTTagCompound itemCompound = new NBTTagCompound();
                this.items[i].writeToNBT(itemCompound);
                itemList.appendTag(itemCompound);
            }
        }

        nbtTagCompound.setTag("Items", itemList);

        nbtTagCompound.setByte("GrillTime", (byte) this.itemGrillTime);
        nbtTagCompound.setByte("CoalTime", (byte) this.coalTimeLeft);
        nbtTagCompound.setBoolean("IpOpen", this.isOpen);
        nbtTagCompound.setBoolean("IsEmpty", this.isEmpty);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        this.writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
    }
}
