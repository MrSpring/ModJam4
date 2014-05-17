package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.KitchenItems;

public class ItemKnife extends ItemBase
{
	public ItemKnife()
	{
		super("knife", true);
		
		this.setContainerItem(this);
	}
}
