package dk.mrspring.kitchen.item.board.cakeable;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.item.board.IBoardable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemCakeable extends ItemBase implements IBoardable
{
    protected int healAmount;

    public ItemCakeable(String name, int healAmount)
    {
        this(name, healAmount, true);
    }

    public ItemCakeable(String name, String textureName, int healAmount)
    {
        this(name, textureName, healAmount, true);
    }

    public ItemCakeable(String name, int healAmount, boolean useCreativeTab)
    {
        this(name, ModInfo.modid + ":" + name, healAmount, useCreativeTab);
    }

    public ItemCakeable(String name, String textureName, int healAmount, boolean useCreativeTab)
    {
        super(name, textureName, useCreativeTab);

        this.setHealAmount(healAmount);
    }

    public void setHealAmount(int healAmount)
    {
        this.healAmount = healAmount;
    }

    public int getHealAmount()
    {
        return healAmount;
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
		return null;
	}

    @Override
    public double getRenderHeight(NBTTagCompound specialTagInfo, int itemIndex, ItemStack item, List<ItemStack> itemStacks)
    {
        return 1;
    }

    @Override
    public boolean canAddOnTop(NBTTagCompound specialTagInfo, ItemStack toAdd, ItemStack topItem)
    {
        return false;
    }
}
