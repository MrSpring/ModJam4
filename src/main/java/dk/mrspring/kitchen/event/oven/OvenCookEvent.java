package dk.mrspring.kitchen.event.oven;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Konrad on 14-07-2014 for The Kitchen Mod.
 */
public class OvenCookEvent
{
    String name = "UNNAMED EVENT";
    Item item;

    public OvenCookEvent(String label, ItemStack item)
    {
        this.name = label;
        this.item = item.getItem();
    }

    /***
     * Get's called whenever the Items associated with the Events item stack.
     *
     * @param z The Z coordinate.
     * @param y The Y coordinate.
     * @param x The X coordinate.
     * @param added The ItemStack which the player tried to add.
     * @return Returns true if Item was added to the oven, false if not.
     */
    public boolean onItemAddedToOven(ItemStack added, int x, int y, int z)
    {
        return false;
    }
}
