package dk.mrspring.kitchen.block;

import java.util.Random;

import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockTomatoCrop extends BlockBush implements IGrowable
{
	private IIcon[] icons;
	
	public BlockTomatoCrop()
	{
		super();
		
		this.setTickRandomly(true);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F - 0.0625F, 1.0F, 1.0F - 0.0625F);
		this.setCreativeTab(null);
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGlass);
		this.disableStats();
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);
		
		if (world.getBlockLightValue(x, y, z) >= 9)
		{
			int metadata = world.getBlockMetadata(x, y, z);
			
			if (metadata < 7)
			{
				float f = this.func_149864_n(world, x, y, z);
				
				if (random.nextInt((int) (15.0F / f) + 1) == 0)
				{
					++metadata;
					world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
				}
			}
		}
	}
	
	public void func_149863_m(World world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
		
		if (metadata > 7)
			metadata = 7;
		
		world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
	}
	
	private float func_149864_n(World world, int x, int y, int z)
	{
		float f = 1.0F;
		Block block0 = world.getBlock(x, y, z - 1);
		Block block1 = world.getBlock(x, y, z + 1);
		Block block2 = world.getBlock(x - 1, y, z);
		Block block3 = world.getBlock(x + 1, y, z);
		Block block4 = world.getBlock(x - 1, y, z - 1);
		Block block5 = world.getBlock(x + 1, y, z - 1);
		Block block6 = world.getBlock(x + 1, y, z + 1);
		Block block7 = world.getBlock(x - 1, y, z + 1);
		boolean flag = block2 == this || block3 == this;
		boolean flag1 = block0 == this || block1 == this;
		boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;
		
		for (int l = x - 1; l <= x + 1; ++l)
		{
			for (int i1 = z - 1; i1 <= z + 1; ++i1)
			{
				float f1 = 0.0F;

				if (world.getBlock(l, y - 1, i1).canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this))
				{
					f1 = 1.0F;

					if (world.getBlock(l, y - 1, i1).isFertile(world, l, y - 1, i1))
					{
						f1 = 3.0F;
					}
				}
				
				if (l != x || i1 != z)
				{
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		if (flag2 || flag && flag1)
		{
			f /= 2.0F;
		}

		return f;
	}
	
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		if (metadata < 0 || metadata > 7)
			metadata = 7;
		
		return this.icons[metadata];
	}
	
	@Override
	public int getRenderType()
		{ return 6; }
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return KitchenItems.tomato;
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 7)
			return random.nextInt(2) + 3;
		else
			return random.nextInt(1);
	}
	
	@Override
	public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5)
	{
		return var1.getBlockMetadata(var2, var3, var4) != 7;
	}

	@Override
	public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5)
	{
		return true;
	}

	@Override
	public void func_149853_b(World var1, Random var2, int var3, int var4, int var5)
	{
		this.func_149863_m(var1, var3, var4, var5);
	}
	
	@Override
	public Item getItem(World world, int x, int y, int z)
	{
		return KitchenItems.tomato;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister registerer)
	{
		this.icons = new IIcon[8];
		
		for (int i = 0; i < this.icons.length; ++i)
		{
			this.icons[i] = registerer.registerIcon(ModInfo.modid + ":" + this.getUnlocalizedName().replace("tile.", "") + "_stage_" + i);
		}
	}
}
