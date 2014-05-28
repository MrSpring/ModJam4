package dk.mrspring.kitchen;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dk.mrspring.kitchen.tileentity.TileEntityGrill;

public class BlockGrill extends BlockContainer
{
	public BlockGrill()
	{
		super(Material.iron);
		
		this.setBlockName("grill");
		this.setBlockTextureName(ModInfo.modid + ":grill");
		
		this.setCreativeTab(Kitchen.instance.tab);
		
		this.setTickRandomly(true);
	}
	
	@Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	{
		super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		world.spawnParticle("largesmoke", x + 0.5, y + 1.0, z + 0.5, (random.nextDouble() - 0.5) / 15, 0.025, (random.nextDouble() - 0.5) / 15);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer activator, int side, float activateX, float activateY, float activateZ)
	{
		/*
		 * 4 3
		 * 1 2
		 */
		
		if (world.isRemote)
		{
			if (activator.getCurrentEquippedItem() != null)
			{
				TileEntityGrill tileEntity = (TileEntityGrill) world.getTileEntity(x, y, z);
				
				System.out.println(" Side: " + side + ", activation X: " + activateX + ", activation Y: " + activateY + ", activation Z: " + activateZ);
				if (side == 1)
				{
					int corner = 0;
					
					if (activateX < 0.5 && activateZ < 0.5)
						corner = 1;
					if (activateX < 0.5 && activateZ > 0.5)
						corner = 2;
					if (activateX > 0.5 && activateZ > 0.5)
						corner = 3;
					if (activateX > 0.5 && activateZ < 0.5)
						corner = 4;
					
					if (corner == 0)
						return false;
					
					
					
					return true;
				}
				else
					return false;
			}
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityGrill();
	}
}
