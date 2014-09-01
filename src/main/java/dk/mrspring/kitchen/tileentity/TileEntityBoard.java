package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.ModConfig;
import dk.mrspring.kitchen.item.board.IBoardable;
import dk.mrspring.kitchen.item.board.cakeable.ItemCakeable;
import dk.mrspring.kitchen.item.board.sandwichable.ItemSandwichable;
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
            if (((IBoardable) this.boardItemStacks.get(this.boardItemStacks.size() - 1).getItem()).hasSpecialRightClick(this.specialTagInfo))
			{
				((IBoardable) this.boardItemStacks.get(this.boardItemStacks.size() - 1).getItem()).onRightClicked(this.specialTagInfo, item);
				return true;
			}

        if (item == null)
            return false;
        else if (item.getItem() == null)
            return false;

        if (!(item.getItem() instanceof IBoardable))
            return false;

        Type itemStackType = this.identifyType(item);
        switch (this.currentType)
        {
            case EMPTY:
            {
                if (itemStackType != Type.UNKNOWN && itemStackType != Type.EMPTY)
                {
                    if (((IBoardable)item.getItem()).canAddOnTop(this.specialTagInfo, item, null))
                    {
                        ItemStack temp = item.copy();
                        temp.stackSize = 1;
                        --item.stackSize;
                        boardItemStacks.add(temp);
                        this.currentType = itemStackType;
                        this.specialTagInfo = new NBTTagCompound();
						if (callEvents) ((IBoardable) temp.getItem()).onAddedToBoard(this.specialTagInfo, temp);
                        return true;
                    } else return false;
                } else break;
            }
            case SANDWICH:
            {
                if (itemStackType == Type.SANDWICH && boardItemStacks.size() + 1 < ModConfig.maxSandwichLayers)
                {
                    if (((IBoardable)item.getItem()).canAddOnTop(this.specialTagInfo, item, this.boardItemStacks.get(this.boardItemStacks.size() - 1)))
                    {
                        ItemStack temp = item.copy();
                        temp.stackSize = 1;
                        --item.stackSize;
                        boardItemStacks.add(temp);
                        this.currentType = itemStackType;
                        this.specialTagInfo = new NBTTagCompound();
						if (callEvents) ((IBoardable) temp.getItem()).onAddedToBoard(this.specialTagInfo, temp);
                        return true;
                    } else return false;
                } else break;
            }
            case CAKE:
            {
                if (itemStackType == Type.CAKE)
                {
                    if (((IBoardable)item.getItem()).canAddOnTop(this.specialTagInfo, item, null))
                    {
                        ItemStack temp = item.copy();
                        temp.stackSize = 1;
                        --item.stackSize;
                        boardItemStacks.add(temp);
                        this.currentType = itemStackType;
                        this.specialTagInfo = new NBTTagCompound();
                        if (callEvents) ((IBoardable) temp.getItem()).onAddedToBoard(this.specialTagInfo, temp);
                        return true;
                    } else return false;
                } else return false;
            }
            case CUTTING:
            {

            }
            default: return false;
        }

        return false;
    }

	public boolean canRemoveTopMostItem()
	{
		ItemStack item = this.boardItemStacks.get(this.boardItemStacks.size() - 1);
		if (item != null)
		{
			return ((IBoardable) item.getItem()).canBeRemoved(this.specialTagInfo, item);
		} return false;
	}

	public ItemStack removeTopMostItem()
	{
		ItemStack item = this.boardItemStacks.get(this.boardItemStacks.size() - 1);
		if (item != null)
		{
			if (((IBoardable) item.getItem()).dropItem(this.specialTagInfo, item))
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
                if (itemStack.getItem() instanceof ItemSandwichable)
                    return Type.SANDWICH;
                else if (itemStack.getItem() instanceof ItemCakeable)
                    return Type.CAKE;
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
        p_145841_1_.setTag("SpecialTag", this.specialTagInfo);
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

	/*private ItemStack[] layers = new ItemStack[10];
	private ItemStack lastRemoved;
	int layerIndex = 0;
	
	public void resetLayers()
	{
		this.layers = new ItemStack[10];
		this.lastRemoved = null;
		this.layerIndex = 0;
	}
	
	public boolean addLayer(ItemSandwichable par1)
	{
		if (this.layerIndex + 1 <= 10)
		{
			this.layers[this.layerIndex] = new ItemStack(par1, 1, 0);
			this.layerIndex++;
			
			return true;
		}
		else
			return false;
	}
	
	public ItemStack[] getLayers()
	{
		return this.layers;
	}
	
	public boolean removeTopLayer()
	{
		if (layerIndex - 1 >= 0)
		{
			this.lastRemoved = this.layers[this.layerIndex - 1];
			this.layers[this.layerIndex - 1] = null;
			--this.layerIndex;
			return true;
		}
		else
			return false;
	}
	
	public ItemStack getLastRemoved()
	{
		return this.lastRemoved;
	}
	
	public boolean isAcceptableSandwich()
	{
		if (this.layers[0] != null)
			if (this.layers[0].getItem() instanceof ItemSandwichBread && this.layers[this.layerIndex - 1].getItem() instanceof ItemSandwichBread)
				return true;
			else
				return false;
		else
			return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.resetLayers();
		NBTTagList list = compound.getTagList("Items", 10);
		this.layers = new ItemStack[10];
		
		for (int i = 0; i < list.tagCount(); ++i)
		{
			NBTTagCompound layerCompound = list.getCompoundTagAt(i);
			
			this.addLayer((ItemSandwichable) ItemStack.loadItemStackFromNBT(layerCompound).getItem());
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < this.layers.length; ++i)
		{
			if (this.layers[i] != null)
			{
				NBTTagCompound layerCompound = new NBTTagCompound();
				this.layers[i].writeToNBT(layerCompound);
				list.appendTag(layerCompound);
			}
		}
		
		compound.setTag("Items", list);
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
	}*/
}
