package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.KitchenItems;

public class ItemKnife extends ItemBase
{
	public ItemKnife(String name, boolean useCreativeTab)
	{
		super(name, useCreativeTab);
		
		this.setContainerItem(this);
	}
}
