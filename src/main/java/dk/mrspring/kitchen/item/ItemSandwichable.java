package dk.mrspring.kitchen.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import dk.mrspring.kitchen.ModInfo;

public class ItemSandwichable extends ItemBase
{
	private int healOnEaten = 1;
	public boolean hasCustomModel;
	private ModelBase bottomModel;
	private ModelBase topModel;
	private int modelHeight = 1;
	
	public ItemSandwichable(String name, String textureName, boolean useCreativeTab, int healAmount)
	{
		super(name, textureName, useCreativeTab);
		
		this.healOnEaten = healAmount;
	}
	
	public ItemSandwichable(String name, boolean useCreativeTab, int healAmount)
	{
		this(name, ModInfo.modid + ":" + name, useCreativeTab, healAmount);
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
	
	public ItemSandwichable setCustomModel(ModelBase top, ModelBase bottom, int modelHeight)
	{
		this.hasCustomModel = true;
		this.topModel = top;
		this.bottomModel = bottom;
		this.modelHeight = modelHeight;
		
		return this;
	}
}
