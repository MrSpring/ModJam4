package dk.mrspring.kitchen;

import dk.mrspring.kitchen.block.BlockBase;
import dk.mrspring.kitchen.block.decorative.BlockKitchenCabinet;
import dk.mrspring.kitchen.block.machines.BlockBoard;
import dk.mrspring.kitchen.block.machines.BlockOven;
import dk.mrspring.kitchen.block.machines.BlockPlate;
import dk.mrspring.kitchen.block.plant.BlockCropBase;
import dk.mrspring.kitchen.block.plant.BlockWildPlantBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class KitchenBlocks
{
	// All the block variables
	public static final Block tiles = new BlockBase(Material.iron, "tiles", true);

	public static final Block tomato_crop = new BlockCropBase("tomato", KitchenItems.tomato);
	public static final Block lettuce_crop = new BlockCropBase("lettuce", KitchenItems.lettuce);

	public static final Block wild_tomato = new BlockWildPlantBase("tomato", KitchenItems.tomato, 2);
	public static final Block wild_lettuce = new BlockWildPlantBase("lettuce", KitchenItems.lettuce, 2);

	public static final Block oven = new BlockOven();
	public static final Block plate = new BlockPlate();
    public static final Block board = new BlockBoard();
    public static final Block kitchen_cabinet = new BlockKitchenCabinet();
}
