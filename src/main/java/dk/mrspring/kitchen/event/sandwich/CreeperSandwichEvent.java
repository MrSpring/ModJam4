package dk.mrspring.kitchen.event.sandwich;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Konrad on 13-07-2014 for The Kitchen Mod.
 */
public class CreeperSandwichEvent extends OnSandwichEatenEvent
{
    public CreeperSandwichEvent()
    {
        super("CREEPER SANDWICH");
    }

    @Override
    public void addFoodEffect(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        super.addFoodEffect(itemStack, world, entityPlayer);
    }
}
