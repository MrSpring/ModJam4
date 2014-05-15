package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.item.ItemSandwichable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoard extends TileEntity
{
	private ItemStack[] layers;
	int layerIndex = 0;
	
	public TileEntityBoard()
	{
		this.layers = new ItemStack[10];
	}
	
	public boolean addLayer(ItemSandwichable par1)
	{
		if (this.layerIndex + 1 <= 10)
			{ layers[layerIndex] = new ItemStack(par1, 1, 0); layerIndex++; return true; }
		else return false;
	}
	
	public ItemStack[] getLayers()
	{
		return this.layers;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		NBTTagList list = compound.getTagList("Layers", 10);
		this.layers = new ItemStack[10];
		
		for (int i = 0; i < list.tagCount(); ++i)
		{
			NBTTagCompound layerCompound = list.getCompoundTagAt(i);
			byte index = layerCompound.getByte("Layer");
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
				layerCompound.setByte("Layer", (byte) i);
				this.layers[i].writeToNBT(layerCompound);
				list.appendTag(layerCompound);
			}
		}
		
		compound.setTag("Layers", list);
	}
}
