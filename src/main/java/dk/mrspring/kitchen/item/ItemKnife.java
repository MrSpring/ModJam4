package dk.mrspring.kitchen.item;

import net.minecraft.item.ItemStack;

public class ItemKnife extends ItemBase
{
	public ItemKnife()
	{
		super("knife", true);
		
		this.setContainerItem(this);
        this.setMaxDamage(15);
	}

    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        ItemStack item = itemStack.copy();
        item.setItemDamage(item.getItemDamage() + 1);
        if (item.getItemDamage() >= 15)
            return null;
        else return item;
    }
}
