package dk.mrspring.kitchen.block;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import dk.mrspring.kitchen.GameRegisterer;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.combo.SandwichCombo;
import dk.mrspring.kitchen.item.ItemSandwich;
import dk.mrspring.kitchen.item.ItemSandwichable;
import dk.mrspring.kitchen.tileentity.TileEntityBoard;

public class BlockBoard extends BlockContainer
{
	TileEntityBoard tEntity;
	
	public BlockBoard()
	{
		super(Material.wood);
		
		this.setBlockName("board");
		this.setBlockTextureName("minecraft:planks_oak");
		this.setTickRandomly(true);
		
		this.setCreativeTab(Kitchen.instance.tab);
		
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(2.0F);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer activator, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		TileEntityBoard entity = (TileEntityBoard) world.getTileEntity(x, y, z);
		
		super.onBlockActivated(world, x, y, z, activator, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
		
		world.markBlockForUpdate(x, y, z);
		
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
						{ world.spawnEntityInWorld(new EntityItem(world, (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, entity.getLastRemoved())); return true; }
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
						ItemStack[] itemsFromEntity = entity.getLayers();
						ArrayList<ItemStack> layers = new ArrayList<ItemStack>();
						
						for (int i = 0; i < itemsFromEntity.length && itemsFromEntity[i] != null; ++i)
						{
							layers.add(itemsFromEntity[i]);
						}
						
						ItemStack item = GameRegistry.findItemStack(ModInfo.modid, "sandwich", 1);
						
						NBTTagList layersList = new NBTTagList();
						
						for (int i = 0; i < layers.size(); ++i)
						{
							NBTTagCompound layerCompound = new NBTTagCompound();
							layers.get(i).writeToNBT(layerCompound);
							layersList.appendTag(layerCompound);
						}
						
						item.setTagInfo("SandwichLayers", layersList);
						
						NBTTagCompound comboCompound = new NBTTagCompound();
						byte combo = 0;
						
						for (int i = 1; i < SandwichCombo.combos.length && SandwichCombo.combos[i] != null; ++i)
						{
							if (SandwichCombo.combos[i].matches(item))
								combo = (byte) i;
						}
						
						comboCompound.setByte("Id", combo);
						item.setTagInfo("Combo", comboCompound);
						
						world.spawnEntityInWorld(new EntityItem(world, (double) x + 0.5, (double) y + 0.5, (double) z + 0.5, item));
						entity.resetLayers();
						return true;
					}
					else
						{ entity.resetLayers(); return true; }
				}
				else
					return false;
			else
				return false;
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> toReturn = new ArrayList<ItemStack>();
		
		for (int i = 0; i < this.tEntity.getLayers().length && this.tEntity.getLayers()[i] != null; ++i)
		{
			toReturn.add(this.tEntity.getLayers()[i]);
		}
		
		return toReturn;
	}
	
	@Override
	public void onBlockPreDestroy(World p_149725_1_, int p_149725_2_, int p_149725_3_, int p_149725_4_, int p_149725_5_)
	{
		this.tEntity = (TileEntityBoard) p_149725_1_.getTileEntity(p_149725_2_, p_149725_3_, p_149725_4_);
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
