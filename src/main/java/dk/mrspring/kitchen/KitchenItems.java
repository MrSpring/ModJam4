package dk.mrspring.kitchen;

import static dk.mrspring.kitchen.GameRegisterer.findItem;
import dk.mrspring.kitchen.item.ItemSandwich;
import dk.mrspring.kitchen.item.ItemSandwichBread;
import dk.mrspring.kitchen.item.ItemSandwichable;
import net.minecraft.item.Item;

public class KitchenItems
{
	public static final Item knife = findItem("knife");
	public static final ItemSandwichable bacon_raw = (ItemSandwichable) findItem("bacon_raw");
	public static final ItemSandwichBread bread_slice = (ItemSandwichBread) findItem("bread_slice");
	public static final ItemSandwich basic_sandwich = (ItemSandwich) findItem("sandwich");
}
