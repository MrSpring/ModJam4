package dk.mrspring.kitchen.item.board.sandwichable;

import dk.mrspring.kitchen.ModConfig;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.item.board.IBoardable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemSandwichable extends ItemBase implements IBoardable
{
	private int healOnEaten = 1;
	public boolean hasCustomModel;
	private ModelBase bottomModel;
	private ModelBase topModel;
	public int modelBottomHeight = 1;
	public int modelTopHeight = 1;
    public boolean showSandwichableInformation = true;
    public boolean isBread = false;
	
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
            par3List.add("Has Custom Model: " + String.valueOf(this.hasCustomModel));

            if (this.hasCustomModel)
            {
                par3List.add("Top Model Height: " + String.valueOf(this.modelTopHeight));
                par3List.add("Bottom Model Height: " + String.valueOf(this.modelBottomHeight));
            }
        }
    }

    public int getHealAmount()
	{
		return this.healOnEaten;
	}
	
	public ModelBase getBottomModel()
	{
		return this.bottomModel;
	}
	
	public ModelBase getTopModel()
	{
		return this.topModel;
	}
	
	public ItemSandwichable setCustomModel(ModelBase top, ModelBase bottom, int modelTopHeight, int modelBottomHeight)
	{
		this.hasCustomModel = true;
		this.topModel = top;
		this.bottomModel = bottom;
		this.modelBottomHeight = modelBottomHeight;
		this.modelTopHeight = modelTopHeight;
		
		return this;
	}

    public ItemSandwichable setIsBread()
    {
        this.isBread = true;
        return this;
    }
}
