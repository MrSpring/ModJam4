package dk.mrspring.kitchen.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;

public class ItemSandwich extends ItemFood
{
	public ItemSandwich()
	{
		super(0, false);
		this.setMaxStackSize(1);
		
		this.setUnlocalizedName("sandwich");
		this.setTextureName(ModInfo.modid + ":sandwich");
		
		this.setCreativeTab(Kitchen.instance.tab);
	}
	
	@Override
	public int func_150905_g(ItemStack item)
	{
		int healAmount = 0;
		
		NBTTagList layersList = item.stackTagCompound.getTagList("SandwichLayers", 10);
		
		for (int i = 0; i < layersList.tagCount(); ++i)
		{
			NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
			healAmount += ((ItemSandwichable) ItemStack.loadItemStackFromNBT(layerCompound).getItem()).getHealAmount();
		}
		
		return healAmount;
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Line 1");
		par3List.add("Line 2. Hello!");
	}
	
	public static int calculateHealAmount(ItemSandwichable[] layers)
	{
		int totalHealAmount = 0;
		
		for (int i = 0; i < layers.length; ++i)
		{
			totalHealAmount += layers[i].getHealAmount();
		}
		
		return totalHealAmount;
	}
}
