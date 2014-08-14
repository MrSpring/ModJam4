package dk.mrspring.kitchen.block;

import dk.mrspring.kitchen.ModInfo;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by MrSpring on 14-08-14 for MC Music Player.
 */
public class BlockFridge extends BlockContainer
{
	boolean isBase;

	public BlockFridge(boolean isBase)
	{
		super(Material.iron);
		this.isBase = isBase;

		this.setBlockName(this.isBase ? "fridge_base" : "fridge_top");
		this.setBlockTextureName(ModInfo.modid + ":fridge");
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
		return null;
	}
}
