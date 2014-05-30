package dk.mrspring.kitchen.block;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.tileentity.TileEntityGrill;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGrill extends BlockContainer
{
	public BlockGrill()
	{
		super(Material.iron);

		this.setBlockName("grill");
		this.setBlockTextureName(ModInfo.modid + ":grill");

		this.setCreativeTab(Kitchen.instance.tab);
        this.setTickRandomly(true);
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		world.spawnParticle("largesmoke", x + 0.5, y + 1.0, z + 0.5, (random.nextDouble() - 0.5) / 15, 0.025, (random.nextDouble() - 0.5) / 15);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer activator, int side, float activateX, float activateY, float activateZ)
	{
		/*
		 * 4 3
		 * 1 2
		 */

        TileEntityGrill tileEntity = (TileEntityGrill) world.getTileEntity(x, y, z);

        int corner = 0;

        if (side == 1)
        {
            if (activateX < 0.5 && activateZ < 0.5)
                corner = 0;
            if (activateX < 0.5 && activateZ > 0.5)
                corner = 1;
            if (activateX > 0.5 && activateZ > 0.5)
                corner = 2;
            if (activateX > 0.5 && activateZ < 0.5)
                corner = 3;
        }
        else return false;

        if (!world.isRemote)
        {
            if (!activator.isSneaking())
            {
                if (activator.getCurrentEquippedItem() != null)
                {
                    if (FurnaceRecipes.smelting().getSmeltingResult(activator.getCurrentEquippedItem()).getItem() instanceof ItemFood)
                    {
                        if (tileEntity.setItem(activator.getCurrentEquippedItem(), corner))
                        {
                            --activator.getCurrentEquippedItem().stackSize;
                            world.markBlockForUpdate(x, y, z);
                            return true;
                        }
                        else
                            return false;
                    }
                    else
                        return false;
                }
                else
                {
                    if (tileEntity.getItem(corner) != null)
                    {
                        world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, tileEntity.getItem(corner)));
                        tileEntity.removeItem(corner);
                        world.markBlockForUpdate(x, y, z);
                        return true;
                    }
                    else
                        return false;
                }
            }
            else
            {
                if (tileEntity.getIsOpen())
                {
                    tileEntity.closeLid();
                    world.markBlockForUpdate(x, y, z);
                    return true;
                } else
                {
                    tileEntity.openLid();
                    world.markBlockForUpdate(x, y, z);
                    return true;
                }
            }
        }
        else
        {
            world.markBlockForUpdate(x, y, z);
            return true;
        }
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
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityGrill();
	}
}
