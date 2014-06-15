package dk.mrspring.kitchen.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemDough extends ItemBase
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons = new IIcon[6];

	public ItemDough(String doughName)
	{
		super("dough_" + doughName, ModInfo.modid + ":dough_" + doughName, true);
		this.setHasSubtypes(true);
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		for(int i = 0; i < this.icons.length; ++i)
		{
			par1IconRegister.registerIcon(ModInfo.modid + ":" + this.getUnlocalizedName().substring(5) + "_" + i);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int par1)
	{
		if (par1 > icons.length || par1 < 0)
			return this.icons[0];
		else
			return this.icons[par1];
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return this.getIconFromDamage(stack.getItemDamage());
	}
}
