package dk.mrspring.kitchen.block;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.tileentity.TileEntityTextTest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTextRenderTest extends BlockContainer
{
    public BlockTextRenderTest()
    {
        super(Material.ground);

        this.setBlockName("text_test");
        this.setBlockTextureName("minecraft:stone");

        this.setCreativeTab(Kitchen.instance.tab);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
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
        return new TileEntityTextTest();
    }
}
