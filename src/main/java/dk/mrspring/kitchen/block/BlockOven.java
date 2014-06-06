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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOven extends BlockContainer
{
	protected IIcon openIcon;

	public BlockOven(boolean isActive)
	{
		super(Material.iron);

		this.setBlockName(isActive ? "oven_active" : "oven_inactive");
		this.setBlockTextureName(ModInfo.modid + ":" + (isActive ? "oven_active" : "oven_inactive"));

		if (!isActive)
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
				this.updateBlockState(world, x, y, z, TileEntityOven.TOGGLE_OPEN_CLOSE);
				return true;
			}
		}
		else
			return true;
	}

	public void updateBlockState(World world, int x, int y, int z, int action)
	{
		switch (action)
		{
		case(TileEntityOven.SET_ACTIVE):
		{
			if (world.getBlock(x, y, z) == KitchenBlocks.oven)
				world.setBlock(x, y, z, KitchenBlocks.oven_active);

			break;
		}
		case(TileEntityOven.SET_INACTIVE):
		{
			if (world.getBlock(x, y, z) == KitchenBlocks.oven_active)
				world.setBlock(x, y, z, KitchenBlocks.oven);

			break;
		}
		case(TileEntityOven.TOGGLE_OPEN_CLOSE):
		{
			System.out.println(" Toggling open/close");

			TileEntityOven tileEntityOven = (TileEntityOven) world.getTileEntity(x, y, z);

			world.markBlockForUpdate(x, y, z);

			if (tileEntityOven.isOpen())
				tileEntityOven.setClosed();
			else
				tileEntityOven.setOpen();

			break;
		}
	}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);
		this.openIcon = iconRegister.registerIcon(ModInfo.modid + ":oven_open");
	}

	@Override
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
	{
		TileEntityOven tileEntityOven = (TileEntityOven) p_149673_1_.getTileEntity(p_149673_2_, p_149673_3_, p_149673_4_);

		System.out.println(" Getting icon. Is oven open?: " + tileEntityOven.isOpen());

		return tileEntityOven.isOpen() ? this.openIcon : super.getIcon(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_, p_149673_5_);
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
		return new TileEntityOven();
	}
}
