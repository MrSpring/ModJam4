package dk.mrspring.kitchen.item.board.cakeable;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.item.board.IBoardable;

public class ItemCakeable extends ItemBase implements IBoardable
{
    protected int healAmount;

    public ItemCakeable(String name, int healAmount)
    {
        this(name, healAmount, true);
    }

    public ItemCakeable(String name, String textureName, int healAmount)
    {
        this(name, textureName, healAmount, true);
    }

    public ItemCakeable(String name, int healAmount, boolean useCreativeTab)
    {
        this(name, ModInfo.modid + ":" + name, healAmount, useCreativeTab);
    }

    public ItemCakeable(String name, String textureName, int healAmount, boolean useCreativeTab)
    {
        super(name, textureName, useCreativeTab);

        this.setHealAmount(healAmount);
    }

    public void setHealAmount(int healAmount)
    {
        this.healAmount = healAmount;
    }

    public int getHealAmount()
    {
        return healAmount;
    }
}
