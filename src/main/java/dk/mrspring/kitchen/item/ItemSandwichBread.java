package dk.mrspring.kitchen.item;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.item.board.sandwichable.ItemSandwichable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class ItemSandwichBread extends ItemSandwichable
{
	public IIcon iconForSandwich;
	
	public ItemSandwichBread(String name, String textureName, boolean useCreativeTab)
	{
		super(name, textureName, useCreativeTab, 0);
	}
	
	public ItemSandwichBread(String name, boolean useCreativeTab)
	{
		this(name, ModInfo.modid + ":" + name, useCreativeTab);
	}
	

	@Override
	public void registerIcons(IIconRegister registerer)
	{
		super.registerIcons(registerer);
		
		this.iconForSandwich = registerer.registerIcon(ModInfo.modid + ":sandwich");
	}
}
