package dk.mrspring.kitchen.block;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.tileentity.TileEntityOven;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOven extends BlockContainer
{
	public BlockOven()
	{
		super(Material.iron);

		this.setBlockName("oven");
		this.setBlockTextureName(ModInfo.modid + ":oven");

		this.setCreativeTab(Kitchen.instance.tab);

		this.setTickRandomly(true);
		this.setHardness(4.0F);
		this.setStepSound(Block.soundTypePiston);
	}

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_)
    {
        TileEntityOven tileEntityBoard = (TileEntityOven) world.getTileEntity(x, y, z);

        if (tileEntityBoard != null)
        {
            ItemStack[] stacks = tileEntityBoard.getOvenItems();

            for (ItemStack item : stacks)
            {
                if (item != null)
                {
                    Random random = new Random();

                    float xRandPos = random.nextFloat() * 0.8F + 0.1F;
                    float yRandPos = random.nextFloat() * 0.8F + 0.1F;
                    float zRandPos = random.nextFloat() * 0.8F + 0.1F;

                    EntityItem entityItem = new EntityItem(world, x + xRandPos, y + yRandPos, z + zRandPos, item);

                    entityItem.motionX = random.nextGaussian() * 0.05F;
                    entityItem.motionY = random.nextGaussian() * 0.05F + 0.2F;
                    entityItem.motionZ = random.nextGaussian() * 0.05F;

                    world.spawnEntityInWorld(entityItem);
                }
            }
        }

        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer activator, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		world.markBlockForUpdate(x, y, z);

		if (!world.isRemote)
		{
			TileEntityOven tileEntity = (TileEntityOven) world.getTileEntity(x, y, z);

			if (!activator.isSneaking())
				if (tileEntity.isOpen())
					if (activator.getCurrentEquippedItem() != null)
						return tileEntity.addItemStack(activator.getCurrentEquippedItem());
					else
					{
						ItemStack removed = tileEntity.removeTopItem();

						if (removed != null)
							if (removed.getItem() != null)
							{
								world.spawnEntityInWorld(new EntityItem(world, x, y, z, removed));
								return true;
							}
							else
								return false;
						else
							return false;
					}
				else
					return false;
			else
			{
				this.updateBlockState(world, x, y, z);
				return true;
			}
		}
		else
			return true;
	}

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int rotation = (MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3);
		super.onBlockPlacedBy(world, x, y, z, p_149689_5_, p_149689_6_);

        world.setBlockMetadataWithNotify(x, y, z, rotation, 0);
    }

    @Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		TileEntityOven tileEntityOven = (TileEntityOven) world.getTileEntity(x, y, z);

		boolean isOpen = tileEntityOven.isOpen();
		boolean hasCoal = tileEntityOven.hasCoal();
		int itemState = tileEntityOven.getItemState();

		int metadata = world.getBlockMetadata(x, y, z);

		if (hasCoal)
			world.spawnParticle("flame", x + 0.2 + (random.nextDouble() * 0.6), y + 0.5, z + 0.2 + (random.nextDouble() * 0.6), 0.0, 0.0, 0.0);

		if (!isOpen)
		{
			switch (itemState)
			{
				case TileEntityOven.RAW: break;
				case TileEntityOven.COOKED:
				{
					switch (metadata)
					{
						case 0: world.spawnParticle("smoke", x + 0.1 + (random.nextDouble() * 0.8), y + 0.8125, z + 0.125, 0.0, 0.0, -0.025); break;
						case 1: world.spawnParticle("smoke", x + 1.0 - 0.125, y + 0.8125, z + 0.1 + (random.nextDouble() * 0.8), 0.025, 0.0, 0.0); break;
						case 2: world.spawnParticle("smoke", x + 0.1 + (random.nextDouble() * 0.8), y + 0.8125, z + 1.0 - 0.125, 0.0, 0.0, 0.025); break;
						case 3: world.spawnParticle("smoke", x + 0.125, y + 0.8125, z + 0.1 + (random.nextDouble() * 0.8), -0.025, 0.0, 0.0); break;
					}

				} break;
				case TileEntityOven.BURNT:
				{
					switch (metadata)
					{
						case 0: world.spawnParticle("smoke", x + 0.1 + (random.nextDouble() * 0.8), y + 0.8125, z + 0.125, 0.0, 0.0, -0.025); world.spawnParticle("flame", x + 0.1 + (random.nextDouble() * 0.8), y + 0.8125, z + 0.1, 0.0, 0.0, 0.0); break;
						case 1: world.spawnParticle("smoke", x + 1.0 - 0.125, y + 0.8125, z + 0.1 + (random.nextDouble() * 0.8), 0.025, 0.0, 0.0); world.spawnParticle("flame", x + 1.0 - 0.1, y + 0.8125, z + 0.1 + (random.nextDouble() * 0.8), 0.0, 0.0, 0.0); break;
						case 2: world.spawnParticle("smoke", x + 0.1 + (random.nextDouble() * 0.8), y + 0.8125, z + 1.0 - 0.125, 0.0, 0.0, 0.025); world.spawnParticle("flame", x + 0.1 + (random.nextDouble() * 0.8), y + 0.8125, z + 1.0 - 0.1, 0.0, 0.0, 0.0); break;
						case 3: world.spawnParticle("smoke", x + 0.125, y + 0.8125, z + 0.1 + (random.nextDouble() * 0.8), -0.025, 0.0, 0.0); world.spawnParticle("flame", x + 0.1, y + 0.8125, z + 0.1 + (random.nextDouble() * 0.8), -0.025, 0.0, 0.0); break;
					}
				} break;
			}
		}
	}

	public void updateBlockState(World world, int x, int y, int z)
	{
		TileEntityOven tileEntityOven = (TileEntityOven) world.getTileEntity(x, y, z);

		if (tileEntityOven.isOpen())
			tileEntityOven.setClosed();
		else
			tileEntityOven.setOpen();
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);
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
		return new TileEntityOven();
	}
}
