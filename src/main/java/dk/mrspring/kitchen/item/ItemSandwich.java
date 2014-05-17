package dk.mrspring.kitchen.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.combo.SandwichCombo;

public class ItemSandwich extends ItemFood
{
	public ItemSandwich()
	{
		super(0, false);
		this.setMaxStackSize(1);
		
		this.setUnlocalizedName("sandwich");
		this.setTextureName("minecraft:bread");
		
		this.setCreativeTab(null);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		byte combo = par1ItemStack.getTagCompound().getCompoundTag("Combo").getByte("Id");
		
		if (SandwichCombo.combos[(int) combo] != null)
			return SandwichCombo.combos[(int) combo].getRarity();
		else
			return EnumRarity.common;
	}
	
	@Override
	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		byte combo = par1ItemStack.getTagCompound().getCompoundTag("Combo").getByte("Id");
		
		if (SandwichCombo.combos[(int) combo] != null)
			SandwichCombo.combos[(int) combo].onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
		else
			super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
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
		
		byte combo = item.getTagCompound().getCompoundTag("Combo").getByte("Id");
		
		if (SandwichCombo.combos[(int) combo] != null)
			healAmount += SandwichCombo.combos[(int) combo].getAdditionalHeal();
		
		return healAmount;
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		NBTTagList layersList = par1ItemStack.stackTagCompound.getTagList("SandwichLayers", 10);
		
		for (int i = 0; i < layersList.tagCount(); ++i)
		{
			NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
			par3List.add(StatCollector.translateToLocal(ItemStack.loadItemStackFromNBT(layerCompound).getDisplayName()));
		}
		
		byte combo = par1ItemStack.getTagCompound().getCompoundTag("Combo").getByte("Id");
		
		if (SandwichCombo.combos[(int) combo] != null)
			SandwichCombo.combos[(int) combo].addCustomItemInformation(par3List);
		
		/*if (this.combo != null)
			this.combo.addCustomItemInformation(par3List);*/
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
