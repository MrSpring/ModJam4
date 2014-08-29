package dk.mrspring.kitchen.block;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.tileentity.TileEntityFridge;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFridge extends BlockContainer
{
	boolean isBase;

	public BlockFridge(boolean isBase)
	{
		super(Material.iron);
		this.isBase = isBase;

		this.setBlockName(this.isBase ? "fridge_base" : "fridge_top");
		this.setBlockTextureName(ModInfo.modid + ":fridge");


		this.setCreativeTab(Kitchen.instance.tab);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		world.markBlockForUpdate(x, y, z);

		if (!world.isRemote)
			if (player.getCurrentEquippedItem() != null)
				if (player.getCurrentEquippedItem().getItem() != null)
				{
					TileEntityFridge tileEntity = (TileEntityFridge) world.getTileEntity(x, y, z);
					return false; //tileEntity.addItemStack(player.getCurrentEquippedItem());
				} else return true;
			else return true;
		else return true;
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
		return new TileEntityFridge();
	}
}
