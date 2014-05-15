package dk.mrspring.kitchen.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.item.ItemSandwich;
import dk.mrspring.kitchen.item.ItemSandwichable;
import dk.mrspring.kitchen.tileentity.TileEntityBoard;

public class BlockBoard extends BlockContainer
{
	public BlockBoard()
	{
		super(Material.wood);
		
		this.setBlockName("board");
		this.setBlockTextureName("minecraft:planks_oak");
		
		this.setCreativeTab(Kitchen.instance.tab);
		
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(2.0F);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer activator, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		TileEntityBoard entity = (TileEntityBoard) world.getTileEntity(x, y, z);
		
		if (!activator.isSneaking())
		{
			if (activator.getCurrentEquippedItem() != null)
				if (activator.getCurrentEquippedItem().getItem() instanceof ItemSandwichable)
					if (entity.addLayer((ItemSandwichable) activator.getCurrentEquippedItem().getItem()))
					{
						--activator.getCurrentEquippedItem().stackSize;
						return true;
					}
					else
						return false;
				else
					return false;
			else
				if (entity.removeTopLayer())
					if (!world.isRemote)
						{ world.spawnEntityInWorld(new EntityItem(world, (double) x, (double) y, (double) z, entity.getLastRemoved())); return true; }
					else
						return true;
				else
					return false;
		}
		else
		{
			if (activator.getCurrentEquippedItem() == null)
				if (entity.isAcceptableSandwich())
				{
					if (!world.isRemote)
					{
						ArrayList<ItemStack> layers;
						
						ItemSandwich sandwich = new ItemSandwich();
						
						world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.apple, 1, 0)));
						entity.resetLayers();
						return true;
					}
					else
						return false;
				}
				else
					return false;
			else
				return false;
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (metadata == 0)
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F * 3, 1.0F - 0.0625F, 0.0625F * 2, 1.0F - (0.0625F * 3));
		else if (metadata == 1)
			this.setBlockBounds(0.0625F * 3, 0.0F, 0.0625F, 1.0F - (0.0625F * 3), 0.0625F * 2, 1.0F - 0.0625F);
	}
	
	@Override
	public void setBlockBoundsForItemRender()
		{ this.setBlockBounds(0.0625F, 0.0F, 0.0625F * 3, 1.0F - 0.0625F, 0.0625F * 2, 1.0F - (0.0625F * 3)); }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack placed)
	{
		int direction = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if (direction == 0 || direction == 2)
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		
		else if (direction == 1 || direction == 3)
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		
		super.onBlockPlacedBy(world, x, y, z, placer, placed);
	}
	
	@Override
	public boolean isOpaqueCube()
		{ return false; }
	
	@Override
	public boolean renderAsNormalBlock()
		{ return false; }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityBoard();
	}
}
