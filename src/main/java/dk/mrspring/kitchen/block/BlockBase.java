package dk.mrspring.kitchen.block;

import static dk.mrspring.kitchen.GameRegisterer.registerBlock;
import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.KitchenBlocks;
import dk.mrspring.kitchen.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{
	public BlockBase(Material mat, String name, String textureName, boolean useCreativeTab)
	{
		super(mat);
		
		this.setBlockName(name);
		this.setBlockTextureName(textureName);
		
		if (useCreativeTab)
			this.setCreativeTab(Kitchen.instance.tab);
	}
	
	public BlockBase(Material mat, String name, boolean useCreativeTab)
	{
		this(mat, name, ModInfo.modid + ":" + name, useCreativeTab);
	}
	
	public static void load()
	{
		registerBlock(KitchenBlocks.tiles);
		registerBlock(KitchenBlocks.board);
		registerBlock(KitchenBlocks.tomato_crop);
		registerBlock(KitchenBlocks.lettuce_crop);
		registerBlock(KitchenBlocks.wild_tomato);
		registerBlock(KitchenBlocks.wild_lettuce);
		registerBlock(KitchenBlocks.grill);
	}
}
