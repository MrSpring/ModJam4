package dk.mrspring.kitchen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.kitchen.block.BlockBase;
import dk.mrspring.kitchen.combo.SandwichCombo;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.tileentity.TileEntityBoard;
import dk.mrspring.kitchen.tileentity.TileEntityOven;
import dk.mrspring.kitchen.tileentity.TileEntityPlate;
import dk.mrspring.kitchen.world.gen.WorldGenWildLettuce;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static java.lang.Character.valueOf;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class Kitchen
{
	@Instance(ModInfo.modid)
	public static Kitchen instance;
	
	@SidedProxy(serverSide = "dk.mrspring.kitchen.CommonProxy", clientSide = "dk.mrspring.kitchen.ClientProxy")
	public static CommonProxy proxy;
	
	public CreativeTabs tab;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
        ModConfig.load(new Configuration(event.getSuggestedConfigurationFile()));

		instance.tab = new CreativeTabs("tabKitchen")
		{
			@Override
			public Item getTabIconItem()
				{ return KitchenItems.basic_sandwich.getItem(); }
		};
		
		GameRegistry.registerTileEntity(TileEntityBoard.class, "tileEntityBoard");
		GameRegistry.registerTileEntity(TileEntityOven.class, "tileEntityOven");
		GameRegistry.registerTileEntity(TileEntityPlate.class, "tileEntityPlate");

		BlockBase.load();
		ItemBase.load();
		
		proxy.registerRenderers();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		ModLogger.print(ModLogger.INFO, "Loading Combos...");

        SandwichCombo.load();

		MinecraftForge.addGrassSeed(new ItemStack(KitchenItems.tomato), 10);

		GameRegistry.registerWorldGenerator(new WorldGenWildLettuce(), 1);

		/**
		 * RECIPES
		 */

		// Cutting Board recipe
		GameRegistry.addRecipe(new ShapedOreRecipe(KitchenBlocks.board, "SPS", valueOf('S'), "slabWood", valueOf('P'), Blocks.wooden_pressure_plate));
		// Tile recipe
        GameRegistry.addRecipe(new ItemStack(KitchenBlocks.tiles, 2), "IB", "CC", "CC", valueOf('I'), new ItemStack(Items.dye, 1, 0), valueOf('B'), new ItemStack(Items.dye, 1, 15), valueOf('C'), Items.clay_ball);

		/**
		 * Knife recipes
		 */
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.raw_bacon, 3), new ItemStack(KitchenItems.knife), new ItemStack(Items.porkchop));				// Raw Bacon recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.tomato_slice, 4), new ItemStack(KitchenItems.knife), new ItemStack(KitchenItems.tomato));		// Tomato Slice recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.lettuce_leaf, 2), new ItemStack(KitchenItems.knife), new ItemStack(KitchenItems.lettuce));		// Lettuce Leaf recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.carrot_slice, 2), new ItemStack(KitchenItems.knife), new ItemStack(Items.carrot));				// Carrot Slice recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.potato_slice, 3), new ItemStack(KitchenItems.knife), new ItemStack(Items.potato));				// Potato Slice recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.bread_slice, 2), new ItemStack(KitchenItems.knife), new ItemStack(Items.bread));					// Bread Slice recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.raw_roast_beef, 2), new ItemStack(KitchenItems.knife), new ItemStack(Items.beef));				// Raw Roast Beef recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.raw_chicken_fillet, 4), new ItemStack(KitchenItems.knife), new ItemStack(Items.chicken));		// Raw Chicken Fillet recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.chicken_leg, 2), new ItemStack(KitchenItems.knife), new ItemStack(Items.cooked_chicken));		// Chicken Leg recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.cheese_slice, 2), new ItemStack(KitchenItems.knife), new ItemStack(KitchenItems.cheese));		// Cheese Slice recipe
																																									//
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.cheese, 2), new ItemStack(KitchenItems.knife), new ItemStack(Items.milk_bucket));				// Cheese recipe
																																									//
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.mortar_and_pestle, 1), new ItemStack(KitchenItems.mortar), new ItemStack(KitchenItems.pestle));	// Mortar and Pestle recipe
																																									//
		GameRegistry.addRecipe(new ItemStack(KitchenItems.mortar), "S S", " S ", valueOf('S'), Blocks.stone);														// Mortar recipe
		GameRegistry.addRecipe(new ItemStack(KitchenItems.pestle), "S ", " S", valueOf('S'), Blocks.stone);															// Pestle recipe
																																									//
																																									//
        switch (ModConfig.knifeRecipe)																																// Knife recipes - Based on Config
        {																																							//
            case 0: GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, "I ", " S", valueOf('S'), "stickWood", valueOf('I'), Items.iron_ingot)); break;	// Default recipe
            case 1: GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, "I", "S", valueOf('S'), "stickWood", valueOf('I'), Items.iron_ingot)); break;	// Iron Torch recipe
            case 2: GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, "IS", valueOf('S'), "stickWood", valueOf('I'), Items.iron_ingot)); break;		// Horizontal recipe
																																									//
            default: GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, "I ", " S", valueOf('S'), "stickWood", valueOf('I'), Items.iron_ingot)); break;	// Default recipe
        }																																							//
																																									//
		GameRegistry.addRecipe(new ItemStack(KitchenBlocks.plate, 1, 0), "CBC", " C ", valueOf('C'), Items.clay_ball, valueOf('B'), new ItemStack(Items.dye, 1, 15));// Plate recipe
																																									//
		/**
		 * Mortar and Pestle recipes
		 */
		GameRegistry.addRecipe(new ItemStack(KitchenItems.flour, 1), "W", "M", valueOf('M'), KitchenItems.mortar_and_pestle, valueOf('W'), Items.wheat);			// Flour recipe
		GameRegistry.addRecipe(new ItemStack(KitchenItems.flour, 3), "B", "M", valueOf('M'), KitchenItems.mortar_and_pestle, valueOf('B'), Items.bread);			// Flour recipe
																																									//
		/**
		 * Smelting recipes
		 */
		GameRegistry.addSmelting(KitchenItems.raw_bacon, new ItemStack(KitchenItems.bacon, 1, 0), 3.0F);															// Cooked Bacon recipe
		GameRegistry.addSmelting(KitchenItems.flour, new ItemStack(KitchenItems.toast, 2, 0), 3.0F);																// Toast recipe
		GameRegistry.addSmelting(KitchenItems.raw_chicken_fillet, new ItemStack(KitchenItems.chicken_fillet, 1, 0), 3.0F);											// Cooked Chicken Fillet recipe
		GameRegistry.addSmelting(KitchenItems.raw_roast_beef, new ItemStack(KitchenItems.roast_beef, 1, 0), 3.0F);													// Roast Beef recipe
	}
}
