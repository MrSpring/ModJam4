package dk.mrspring.kitchen;

import static dk.mrspring.kitchen.GameRegisterer.findItem;
import dk.mrspring.kitchen.item.ItemSandwichable;
import net.minecraft.item.Item;

public class KitchenItems
{
	public static final Item knife = findItem("knife");
	public static final ItemSandwichable bacon_raw = (ItemSandwichable) findItem("bacon_raw");
}
