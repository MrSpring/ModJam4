package dk.mrspring.kitchen.item.board.sandwichable;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModConfig;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.item.board.ISandwichable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemSandwichableBase extends ItemBase implements ISandwichable
{
	private int healOnEaten = 1;
	public double modelHeight = 0.7;
    public boolean showSandwichableInformation = true;
    protected boolean isBread = false;
	public ModelBase model;
	
	public ItemSandwichableBase(String name, String textureName, boolean useCreativeTab, int healAmount)
	{
		super(name, textureName, false);
		
		this.healOnEaten = healAmount;
		if (useCreativeTab)
			this.setCreativeTab(Kitchen.instance.sandwichable_tab);
	}
	
	public ItemSandwichableBase(String name, boolean useCreativeTab, int healAmount)
	{
		this(name, ModInfo.modid + ":" + name, useCreativeTab, healAmount);
	}

    public ItemSandwichableBase disableInformation()
    {
        this.showSandwichableInformation = false;
        return this;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if (this.showSandwichableInformation)
            par3List.add(StatCollector.translateToLocal("item_info.sandwichable"));

        if (ModConfig.showItemDebug)
        {
            par3List.add(StatCollector.translateToLocal("item_info.heal_amount") + ": " + String.valueOf(this.getHealAmount()));
            par3List.add(StatCollector.translateToLocal("item_info.has_model") + ": " + String.valueOf(this.model != null));
        }
    }

	@Override
    public int getHealAmount()
	{
		return this.healOnEaten;
	}

	@Override
	public boolean isBread()
	{
		return this.isBread;
	}

	public ItemSandwichableBase setModel(ModelBase model, int modelHeight)
	{
		this.model = model;
		this.modelHeight = modelHeight;

		return this;
	}

    public ItemSandwichableBase setIsBread()
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
    public void onRightClicked(NBTTagCompound specialTagInfo, ItemStack item)
    {
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

	@Override
	public boolean canBeRemoved(NBTTagCompound specialTagInfo, ItemStack toRemove)
	{
		return true;
	}

	@Override
	public boolean dropItem(NBTTagCompound specialTagInfo, ItemStack removed)
	{
		return true;
	}
}
