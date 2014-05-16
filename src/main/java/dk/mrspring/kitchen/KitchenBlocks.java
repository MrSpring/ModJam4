package dk.mrspring.kitchen;

import static dk.mrspring.kitchen.GameRegisterer.findBlock;
import net.minecraft.block.Block;

public class KitchenBlocks
{
	public static final Block tiles = findBlock("tiles");
	public static final Block board = findBlock("board");
	public static final Block tomato_crop = findBlock("tomato_crop");
	public static final Block lettuce_crop = findBlock("lettuce_crop");
}
