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
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class BlockOven extends BlockContainer
{
	TileEntityOven tileEntityOven;

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
	public void onBlockPreDestroy(World p_149725_1_, int p_149725_2_, int p_149725_3_, int p_149725_4_, int p_149725_5_)
	{
		this.tileEntityOven = (TileEntityOven) p_149725_1_.getTileEntity(p_149725_2_, p_149725_3_, p_149725_4_);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> toReturn = new ArrayList<ItemStack>();

		if (this.tileEntityOven != null)
			for (ItemStack item : this.tileEntityOven.getOvenItems())
			{
				if (item != null)
					toReturn.add(item);
			}

		toReturn.add(new ItemStack(this, 1, 0));

		return toReturn;
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
        System.out.println(rotation);
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
