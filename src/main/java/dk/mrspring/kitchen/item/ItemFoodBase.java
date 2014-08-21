package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ItemFoodBase extends ItemFood
{
    protected ItemStack onEatenResult;

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
        this.setTextureName(textureName);

        this.setCreativeTab(Kitchen.instance.tab);
    }

    @Override
    protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);

        if (!par2World.isRemote && this.onEatenResult != null)
        {
            EntityItem entityItem = new EntityItem(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ);

            Random random = new Random();

            entityItem.motionX = random.nextGaussian() * 0.05F;
            entityItem.motionY = random.nextGaussian() * 0.05F + 0.2F;
            entityItem.motionZ = random.nextGaussian() * 0.05F;

            par2World.spawnEntityInWorld(entityItem);
        }
    }

    public ItemFoodBase setOnFoodEatenItem(Item item)
    {
        return this.setOnFoodEatenItem(new ItemStack(item, 1, 0));
    }

    public ItemFoodBase setOnFoodEatenItem(Block block)
    {
        return this.setOnFoodEatenItem(new ItemStack(block, 1, 0));
    }

    public ItemFoodBase setOnFoodEatenItem(ItemStack itemStack)
    {
        this.onEatenResult = itemStack;
        return this;
    }
}
