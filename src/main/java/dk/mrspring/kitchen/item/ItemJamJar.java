package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by MrSpring on 25-09-2014 for ModJam4.
 */
public class ItemJamJar extends ItemBlock
{
	public ItemJamJar(Block name)
	{
		super(name);
		this.setCreativeTab(Kitchen.instance.tab);
		this.setUnlocalizedName("jam_jar_item");
		this.setTextureName(ModInfo.modid + ":jam_jar");
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		return super.getItemStackDisplayName(par1ItemStack);
	}
}
