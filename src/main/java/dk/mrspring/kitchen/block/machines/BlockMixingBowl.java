package dk.mrspring.kitchen.block.machines;

import dk.mrspring.kitchen.tileentity.TileEntityMixingBowl;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Konrad on 06-09-2014 for ModJam4.
 */
public class BlockMixingBowl extends BlockContainer
{
    public BlockMixingBowl()
    {
        super(Material.ground);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileEntityMixingBowl();
    }
}
