package dk.mrspring.kitchen.item.board.sandwichable;

import dk.mrspring.kitchen.ModConfig;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.item.board.IBoardable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemSandwichable extends ItemBase implements IBoardable
{
	private int healOnEaten = 1;
	public double modelHeight = 1;
	public int modelTopHeight = 1;
    public boolean showSandwichableInformation = true;
    public boolean isBread = false;
	public ModelBase model;
	
	public ItemSandwichable(String name, String textureName, boolean useCreativeTab, int healAmount)
	{
		super(name, textureName, useCreativeTab);
		
		this.healOnEaten = healAmount;
	}
	
	public ItemSandwichable(String name, boolean useCreativeTab, int healAmount)
	{
		this(name, ModInfo.modid + ":" + name, useCreativeTab, healAmount);
	}

    public ItemSandwichable disableInformation()
    {
        this.showSandwichableInformation = false;
        return this;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if (this.showSandwichableInformation)
            par3List.add("Sandwichable");

        if (ModConfig.showItemDebug)
        {
            par3List.add("Heal Amount: " + String.valueOf(this.getHealAmount()));
            par3List.add("Has Custom Model: " + String.valueOf(this.model != null));
        }
    }

    public int getHealAmount()
	{
		return this.healOnEaten;
	}
	
	public ItemSandwichable setModel(ModelBase model, int modelHeight)
	{
		this.model = model;
		this.modelHeight = modelHeight;

		return this;
	}

    public ItemSandwichable setIsBread()
    {
        this.isBread = true;
        return this;
    }

    @Override
    public boolean hasSpecialRightClick(NBTTagCompound specialTagInfo)
    {
        return false;
    }

    @Override
    public boolean onRightClicked(NBTTagCompound specialTagInfo, ItemStack item)
    {
        return false;
    }

    @Override
    public void onAddedToBoard(NBTTagCompound specialTagInfo, ItemStack item)
    {

    }

	@Override
	public ModelBase getModel(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks)
	{
		if (model != null)
			return model;
		else return null;
	}

	@Override
	public double getRenderHeight(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks)
	{
		return modelHeight;
	}

    @Override
    public boolean canAddOnTop(NBTTagCompound specialTagInfo, ItemStack toAdd, ItemStack topItem)
    {
        return true;
    }
}
