package dk.mrspring.kitchen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import dk.mrspring.kitchen.block.BlockBase;

public class BlockGrill extends BlockBase
{
	public BlockGrill()
	{
		super(Material.iron, "grill", true);
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
		if (world.isRemote)
		{
			System.out.println(" Side: " + side + ", activation X: " + activateX + ", activation Y: " + activateY + ", activation Z: " + activateZ);
			if (side == 1)
			{
				if (activateX < 0.5 && activateZ < 0.5)
					System.out.println(" Right-clicked in the Bottom Left corner!");
				
				if (activateX < 0.5 && activateZ > 0.5)
					System.out.println(" Right-clicked in the Bottom Right corner!");
				
				if (activateX > 0.5 && activateZ > 0.5)
					System.out.println(" Right-clicked in the Top Right corner!");
				
				if (activateX > 0.5 && activateZ < 0.5)
					System.out.println(" Right-clicked in the Top Left corner!");
				
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
}
