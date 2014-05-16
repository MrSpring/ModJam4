package dk.mrspring.kitchen.block;

import static dk.mrspring.kitchen.GameRegisterer.registerBlock;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{
	protected BlockBase(Material mat, String name, String textureName, boolean useCreativeTab)
	{
		super(mat);
		
		this.setBlockName(name);
		this.setBlockTextureName(textureName);
		
		if (useCreativeTab)
			this.setCreativeTab(Kitchen.instance.tab);
	}
	
	protected BlockBase(Material mat, String name, boolean useCreativeTab)
	{
		this(mat, name, ModInfo.modid + ":" + name, useCreativeTab);
	}
	
	public static void load()
	{
		registerBlock(new BlockBase(Material.iron, "tiles", true));
		registerBlock(new BlockBoard());
		registerBlock(new BlockTomatoCrop());
	}
}
