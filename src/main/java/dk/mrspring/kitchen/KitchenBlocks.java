package dk.mrspring.kitchen;

import static dk.mrspring.kitchen.GameRegisterer.findBlock;
import dk.mrspring.kitchen.block.BlockBase;
import dk.mrspring.kitchen.block.BlockBoard;
import dk.mrspring.kitchen.block.BlockLettuceCrop;
import dk.mrspring.kitchen.block.BlockTomatoCrop;
import dk.mrspring.kitchen.block.BlockWildLettuce;
import dk.mrspring.kitchen.block.BlockWildTomato;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class KitchenBlocks
{
	public static final Block tiles = new BlockBase(Material.iron, "tiles", true);
	public static final Block board = new BlockBoard();
	public static final Block tomato_crop = new BlockTomatoCrop();
	public static final Block lettuce_crop = new BlockLettuceCrop();
	public static final Block wild_tomato = new BlockWildTomato();
	public static final Block wild_lettuce = new BlockWildLettuce();
	public static final Block grill = new BlockGrill();
}
