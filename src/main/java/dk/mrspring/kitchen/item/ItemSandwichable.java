package dk.mrspring.kitchen.item;

import net.minecraft.client.model.ModelBase;
import dk.mrspring.kitchen.ModInfo;

public class ItemSandwichable extends ItemBase
{
	private int healOnEaten = 1;
	protected boolean hasCustomModel;
	private ModelBase customModel;
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
	
	public void setCustomModel(ModelBase model, int modelHeight)
	{
		this.hasCustomModel = true;
		this.customModel = model;
		this.modelHeight = modelHeight;
	}
}
