package dk.mrspring.kitchen.block;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.tileentity.TileEntityJamJar;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by MrSpring on 25-09-2014 for ModJam4.
 */
public class BlockJamJar extends BlockContainer
{
	public BlockJamJar(String name)
	{
		super(Material.glass);
		this.setBlockName(name);
		this.setBlockTextureName(ModInfo.modid + ":" + name);
		this.setCreativeTab(Kitchen.instance.tab);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityJamJar();
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
}
