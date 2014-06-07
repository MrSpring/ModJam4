package dk.mrspring.kitchen.block;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.KitchenBlocks;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.tileentity.TileEntityOven;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOven extends BlockContainer
{
	protected IIcon openIcon;

	public BlockOven()
	{
		super(Material.iron);

		this.setBlockName("oven");
		this.setBlockTextureName(ModInfo.modid + ":oven");

		this.setCreativeTab(Kitchen.instance.tab);

		this.setTickRandomly(true);
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
					return tileEntity.addItemStack(activator.getCurrentEquippedItem());
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
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		TileEntityOven tileEntityOven = (TileEntityOven) world.getTileEntity(x, y, z);

		boolean isOpen = tileEntityOven.isOpen();
		boolean hasCoal = tileEntityOven.hasCoal();
		int itemState = tileEntityOven.getItemState();

		if (hasCoal)
			world.spawnParticle("flame", x + 0.1 + (random.nextDouble() * 0.8), y + 0.5, z + 0.2 + (random.nextDouble() * 0.7), 0.0, 0.0, 0.0);

		if (!isOpen)
		{
			switch (itemState)
			{
				case TileEntityOven.RAW: break;
				case TileEntityOven.COOKED: break; //TODO Spawn smoke particles around Lid
				case TileEntityOven.BURNT: break; // TODO Spawn fire and smoke particles around Lid
			}
		}
	}

	public void updateBlockState(World world, int x, int y, int z)
	{
		System.out.println(" Toggling open/close");

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
		this.openIcon = iconRegister.registerIcon(ModInfo.modid + ":oven_open");
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
