package dk.mrspring.kitchen.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.ModInfo;

public class BlockLettuceCrop extends BlockCrops
{
	private IIcon[] icons;
	
	public BlockLettuceCrop()
	{
		this.setBlockName("lettuce_crop");
		this.setBlockTextureName(ModInfo.modid + ":lettuce_crop");
	}
}
