package dk.mrspring.kitchen.block.plant;

import dk.mrspring.kitchen.ModInfo;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * Created by Konrad on 03-09-2014 for ModJam4.
 */
public class BlockCropBase extends BlockCrops
{
    private IIcon[] icons;
    Item droppedItem;

    public BlockCropBase(String name, Item dropped)
    {
        super();

        this.setBlockName(name + "_crop");
        this.setBlockTextureName(ModInfo.modid + ":" + name + "_crop");
        this.droppedItem = dropped;

        this.setCreativeTab(null);
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        if (metadata < 7)
        {
            if (metadata == 6)
                metadata = 5;

            return this.icons[metadata >> 1];
        }
        else
            return this.icons[3];
    }

    protected Item func_149866_i()
    {
        return this.droppedItem;
    }

    protected Item func_149865_P()
    {
        return this.droppedItem;
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.icons = new IIcon[4];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
}
