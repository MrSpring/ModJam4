package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.combo.SandwichCombo;
import dk.mrspring.kitchen.item.board.CuttingRegistry;
import dk.mrspring.kitchen.item.board.IBoardable;
import dk.mrspring.kitchen.item.board.ISandwichable;
import dk.mrspring.kitchen.item.board.cakeable.ItemCakeable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class TileEntityBoard extends TileEntity
{
    protected ArrayList<ItemStack> boardItemStacks = new ArrayList<ItemStack>();
    protected Type currentType = Type.EMPTY;
    // The special info tag, used to store right-click event stuff. Gets cleared whenever a new Item is added to the List.
    protected NBTTagCompound specialTagInfo = new NBTTagCompound();

	public NBTTagCompound getSpecialTagInfo()
	{
		if (specialTagInfo != null)
			return specialTagInfo;
		else return new NBTTagCompound();
	}

	/***
	 * Short version of addItem(ItemStack item, boolean callEvents).
	 *
	 * @param item The ItemStack to add.
	 * @return Returns true if the ItemStack was successfully added, false if not.
	 */
	public boolean addItem(ItemStack item)
	{
		return this.addItem(item, true);
	}

    /***
     * Adds the ItemStack to the List, if the board is empty, or the ItemStack fits into the current type.
     *
     * @param item The ItemStack to add.
	 * @param callEvents Whether to call the onRightClicked- and the onAddedToBoardEvent of the items. False when loaded from NBTData.
     * @return Returns true if the ItemStack was successfully added, false if not.
     */
    public boolean addItem(ItemStack item, boolean callEvents)
    {
        if (this.boardItemStacks.size() != 0 && callEvents)
            if (((IBoardable) this.boardItemStacks.get(this.boardItemStacks.size() - 1).getItem()).hasSpecialRightClick(this.getSpecialTagInfo()))
			{
				((IBoardable) this.boardItemStacks.get(this.boardItemStacks.size() - 1).getItem()).onRightClicked(this.getSpecialTagInfo(), item);
				return true;
			}

        if (item == null)
            return false;
        else if (item.getItem() == null)
            return false;

        if (!(item.getItem() instanceof IBoardable))
            return false;

        Type itemStackType = this.identifyType(item);

		if (itemStackType == this.currentType || this.currentType == Type.EMPTY)
		{
			if (((IBoardable) item.getItem()).canAddOnTop(this.getSpecialTagInfo(), item, this.getTopItem()))
			{
				if (this.currentType == Type.EMPTY)
					this.currentType = itemStackType;

				ItemStack temp = item.copy();
				temp.stackSize = 1;
				--item.stackSize;
				boardItemStacks.add(temp);
				if (callEvents) ((IBoardable) temp.getItem()).onAddedToBoard(this.getSpecialTagInfo(), temp);
				return true;
			}
			return false;
		}

        return false;
    }

	/***
	 * @return Returns the finished item, based on which type the Board is currently holding.
	 */
	public ItemStack finishItems()
	{
		switch (this.currentType)
		{
			case EMPTY: return null;
			case SANDWICH: return this.finishSandwich();
			case CAKE: return this.finishCake();
		}
		return null;
	}

	/***
	 * Called if currentState is SANDWICH.
	 * @return Returns the finished sandwich, null if the sandwich was not made.
	 */
	private ItemStack finishSandwich()
	{
		if (this.boardItemStacks.size() < 2)
			return null;
		if (!(((ISandwichable) this.boardItemStacks.get(0).getItem()).isBread() && ((ISandwichable) this.boardItemStacks.get(this.boardItemStacks.size() - 1).getItem()).isBread()))
			return null;

		ItemStack sandwich = KitchenItems.basic_sandwich.copy();
		sandwich.stackTagCompound = new NBTTagCompound();

		NBTTagList layersList = new NBTTagList();

		for (ItemStack layer : this.boardItemStacks)
		{
			if (layer != null)
			{
				NBTTagCompound layerCompound = new NBTTagCompound();
				layer.writeToNBT(layerCompound);
				layersList.appendTag(layerCompound);
			}
		}

		sandwich.setTagInfo("SandwichLayers", layersList);

		NBTTagCompound comboCompound = new NBTTagCompound();
		byte comboId = 0;

		for (SandwichCombo combo : SandwichCombo.combos)
		{
			if (combo != null)
				if (combo.matches(sandwich))
					comboId = (byte) combo.id;
		}

		comboCompound.setByte("Id", comboId);
		sandwich.setTagInfo("Combo", comboCompound);

		return sandwich;
	}

	private ItemStack finishCake()
	{
		return null;
	}

	/***
	 * Resets the board.
	 * @return Returns the list of ItemStacks from before the Board was reset.
	 */
	public List<ItemStack> resetBoard()
	{
		List<ItemStack> oldList = this.boardItemStacks;
		this.boardItemStacks = new ArrayList<ItemStack>();
		this.currentType = Type.EMPTY;
		this.specialTagInfo = new NBTTagCompound();
		return oldList;
	}

	public ItemStack getTopItem()
	{
		if (this.boardItemStacks.size() != 0)
			return this.boardItemStacks.get(this.boardItemStacks.size() - 1);
		else return null;
	}

	public boolean canRemoveTopMostItem()
	{
		if (this.boardItemStacks.size() == 0)
			return false;

		ItemStack item = this.boardItemStacks.get(this.boardItemStacks.size() - 1);
		if (item != null)
		{
			return ((IBoardable) item.getItem()).canBeRemoved(this.getSpecialTagInfo(), item);
		} return false;
	}

	public ItemStack removeTopMostItem()
	{
		ItemStack item = this.boardItemStacks.remove(this.boardItemStacks.size() - 1);
		if (item != null)
		{
			if (this.boardItemStacks.size() == 0)
				this.currentType = Type.EMPTY;

			if (((IBoardable) item.getItem()).dropItem(this.getSpecialTagInfo(), item))
				return item;
			else return null;
		} else return null;
	}

    /***
     * @return Returns all the ItemStacks currently being held by the board.
     */
    public List<ItemStack> getAllItems()
    {
        return this.boardItemStacks;
    }

    /***
     * Identifies the ItemStack parsed through. Used to determine if the ItemStack can be added to the board.
     *
     * @param itemStack The ItemStack to identify.
     * @return Returns the type the ItemStack is of. EMPTY if the ItemStack is null, UNKNOWN if it doesn't match any type.
     */
    public Type identifyType(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            if (itemStack.getItem() != null)
            {
                if (itemStack.getItem() instanceof ISandwichable)
                    return Type.SANDWICH;
                else if (itemStack.getItem() instanceof ItemCakeable)
                    return Type.CAKE;
                else if (CuttingRegistry.hasOutput(itemStack))
                    return Type.CUTTING;
            } else return Type.EMPTY;
        } else return Type.EMPTY;

        return Type.UNKNOWN;
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
        super.writeToNBT(p_145841_1_);

        NBTTagList list = new NBTTagList();

        for (ItemStack item : this.boardItemStacks)
        {
            NBTTagCompound itemCompound = new NBTTagCompound();
            list.appendTag(item.writeToNBT(itemCompound));
        }

        p_145841_1_.setTag("Items", list);
        p_145841_1_.setTag("SpecialTag", this.getSpecialTagInfo());
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_)
    {
        super.readFromNBT(p_145839_1_);

        this.boardItemStacks = new ArrayList<ItemStack>();
        this.currentType = Type.EMPTY;

        NBTTagList list = p_145839_1_.getTagList("Items", 10);

        if (list != null)
            for (int i = 0; i < list.tagCount(); i++)
            {
                NBTTagCompound itemCompound = list.getCompoundTagAt(i);
                ItemStack item = ItemStack.loadItemStackFromNBT(itemCompound);
                this.addItem(item, false);
            }

        this.specialTagInfo = p_145839_1_.getCompoundTag("SpecialTag");
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

    /***
     * Enum Type, used to identify what's on the board, and if an ItemStack if of that Type.
     */
    private enum Type
    {
        UNKNOWN,
        EMPTY,
        SANDWICH,
        CAKE,
        CUTTING
    }
}
