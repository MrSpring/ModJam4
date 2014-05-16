package dk.mrspring.kitchen.combo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import dk.mrspring.kitchen.item.ItemSandwichable;

public class SandwichCombo
{
	protected ArrayList<ItemSandwichable> comboLayers = new ArrayList<ItemSandwichable>();
	protected EnumRarity rarity;
	// TODO Implement PotionEffect things
	
	public SandwichCombo(ItemSandwichable[] items)
	{
		for (int i = 0; i < items.length; ++i)
		{
			if (items[i] != null)
				this.comboLayers.add(items[i]);
		}
	}
	
	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		
	}
	
	public void addCustomItemInformation(List par1)
	{
		par1.add("Default Combo");
	}
	
	public void setRarity(EnumRarity par1)
	{
		this.rarity = par1;
	}
	
	public EnumRarity getRarity()
	{
		return this.rarity;
	}
	
	public boolean matches(ItemStack sandwich)
	{
		ArrayList<ItemSandwichable> layersInSandwich = new ArrayList<ItemSandwichable>();
		NBTTagList layersList = sandwich.stackTagCompound.getTagList("SandwichLayers", 10);
		
		for (int i = 0; i < layersList.tagCount(); ++i)
		{
			NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
			layersInSandwich.add((ItemSandwichable) ItemStack.loadItemStackFromNBT(layerCompound).getItem());
		}
		
		if (this.comboLayers.containsAll(layersInSandwich) && layersInSandwich.containsAll(this.comboLayers))
			return true;
		else
			return false;
	}
	
	public String getUnlocalizedName()
	{
		return "sandwich.combo.default";
	}
}
