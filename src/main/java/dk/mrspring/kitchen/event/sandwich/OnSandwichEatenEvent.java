package dk.mrspring.kitchen.event.sandwich;

import dk.mrspring.kitchen.ModLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by Konrad on 13-07-2014 for The Kitchen Mod.
 */
public class OnSandwichEatenEvent
{
    protected String name = "UNNAMED EVENT";

    public OnSandwichEatenEvent(String label)
    {
        this.name = label;
    }

    public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        try
        {
            ModLogger.print(ModLogger.DEBUG, "OnEatenEvent " + this.name + " triggered!", null);
            this.addFoodEffect(par1ItemStack, par2World, par3EntityPlayer);
        }
        catch (Exception e)
        {
            ModLogger.print(ModLogger.ERROR, "OnEatenEvent " + this.name + " failed. Stacktrace: ", e);
        }
    }

    public void addFoodEffect(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        entityPlayer.addPotionEffect(new PotionEffect(11, 10, 6, false));
        if (!world.isRemote)
            world.createExplosion(entityPlayer, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, 1.5F, world.getGameRules().getGameRuleBooleanValue("mobGriefing"));
    }
}
