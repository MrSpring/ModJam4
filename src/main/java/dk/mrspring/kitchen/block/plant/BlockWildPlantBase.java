package dk.mrspring.kitchen.block.plant;

import dk.mrspring.kitchen.ModInfo;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Konrad on 03-09-2014 for ModJam4.
 */
public class BlockWildPlantBase extends BlockBush
{
    Item dropped;
    int dropAmount;

    public BlockWildPlantBase(String name, Item droppedItem, int drops)
    {
        super(Material.plants);

        this.setBlockName("wild_" + name);
        this.setBlockTextureName(ModInfo.modid + ":wild_" + name);
        this.setTickRandomly(true);
        this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.8F, 0.2F * 3.0F, 0.8F);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab(null);
        this.dropAmount = drops;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return dropped;
    }

    @Override
    public int quantityDropped(Random p_149745_1_)
    {
        return this.dropAmount;
    }
}
