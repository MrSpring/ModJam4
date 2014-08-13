package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.entity.EntityCup;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCup extends Item
{
    public ItemCup()
    {
        super();

        this.setUnlocalizedName("cup");
        this.setTextureName(ModInfo.modid + ":cup");

        this.setCreativeTab(Kitchen.instance.tab);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack)
    {
        // TODO Return true if cup has liquid NBT tags, false if not.
        return true;
    }

    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
        // TODO Create Cup entity, transfer liquid NBT tags to it.
        System.out.println(" Creating custom entity!");
        return new EntityCup(world, location.posX, location.posY, location.posZ);
    }
}
