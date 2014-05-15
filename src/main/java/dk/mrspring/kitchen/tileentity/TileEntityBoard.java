package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.item.ItemSandwichable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoard extends TileEntity
{
	private ItemStack[] layers = new ItemStack[10];
	private ItemStack lastRemoved;
	private boolean isInitialized = false;
	int layerIndex = 0;
	
	public TileEntityBoard()
	{
		
	}
	
	@Override
	public void updateEntity()
	{
		if (!this.isInitialized)
		{
			if (this.worldObj.isRemote)
				System.out.println(" World is Remote!");
			else
				System.out.println(" World is NOT Remote!");
			
			for (int i = 0; i < this.layers.length; ++i)
			{
				if (this.layers[i] != null)
					System.out.println(" Current layer in slot: " + i + " is: " + this.layers[i].getDisplayName());
			}
			
			System.out.println(" Current LayerIndex: " + this.layerIndex);
			this.isInitialized = true;
		}
	}
	
	public boolean addLayer(ItemSandwichable par1)
	{
		if (this.layerIndex + 1 <= 10)
		{
			layers[layerIndex] = new ItemStack(par1, 1, 0); layerIndex++;
			
			if (this.worldObj.isRemote)
				System.out.println(" Adding layer: " + par1.getUnlocalizedName() + ", and the world is Remote!");
			else
				System.out.println(" Adding layer: " + par1.getUnlocalizedName() + ", and the world is NOT Remote!");
			
			return true;
		}
		else return false;
	}
	
	public ItemStack[] getLayers()
	{
		return this.layers;
	}
	
	public boolean removeTopLayer()
	{
		if (layerIndex - 1 >= 0)
			{ this.lastRemoved = this.layers[layerIndex - 1]; this.layers[layerIndex - 1] = null; --this.layerIndex; return true; }
		else
			return false;
	}
	
	public ItemStack getLastRemoved()
	{
		return this.lastRemoved;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
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
		
		if (this.worldObj.isRemote)
			System.out.println(" Writing to NBT, and the world is Remote!");
		else
			System.out.println(" Writing to NBT, and the world is NOT Remote!");
		
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
	}
}
