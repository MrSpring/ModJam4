package dk.mrspring.kitchen.block;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.tileentity.TileEntityKitchenCabinet;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Konrad on 12-07-2014 for The Kitchen Mod.
 */
public class BlockKitchenCabinet extends BlockContainer
{
    public BlockKitchenCabinet()
    {
        super(Material.wood);

        this.setBlockName("kitchen_cabinet");
        this.setBlockTextureName(ModInfo.modid + ":" + "kitchen_cabinet");

        this.setHardness(2.0F);
        this.setResistance(5.0F);

        this.setCreativeTab(Kitchen.instance.tab);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileEntityKitchenCabinet();
    }
}
