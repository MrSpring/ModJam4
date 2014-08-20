package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.item.ItemFood;

public class ItemFoodBase extends ItemFood
{
    public ItemFoodBase(String name, int healAmount)
    {
        this(name, healAmount, false);
    }

    public ItemFoodBase(String name, int healAmount, boolean canHealWolf)
    {
        this(name, ModInfo.modid + ":" + name, healAmount, canHealWolf);
    }

    public ItemFoodBase(String name, String textureName, int healAmount)
    {
        this(name, textureName, healAmount, false);
    }

    public ItemFoodBase(String name, String textureName, int healAmount, boolean canHealWolf)
    {
        super(healAmount, canHealWolf);

        this.setUnlocalizedName(name);
        this.setTextureName(name);

        this.setCreativeTab(Kitchen.instance.tab);
    }
}
