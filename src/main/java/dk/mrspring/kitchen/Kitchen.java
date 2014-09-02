package dk.mrspring.kitchen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.kitchen.block.BlockBase;
import dk.mrspring.kitchen.combo.SandwichCombo;
import dk.mrspring.kitchen.entity.EntityCup;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.tileentity.*;
import dk.mrspring.kitchen.world.gen.WorldGenWildPlant;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

import static java.lang.Character.valueOf;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class Kitchen
{
    public static ArrayList[] customOvenRecipes = new ArrayList[2];

	@Instance(ModInfo.modid)
	public static Kitchen instance;
	
	@SidedProxy(serverSide = "dk.mrspring.kitchen.CommonProxy", clientSide = "dk.mrspring.kitchen.ClientProxy")
	public static CommonProxy proxy;
	
	public CreativeTabs tab;
	public CreativeTabs sandwichable_tab;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		// Loading the Config
        ModConfig.load(new Configuration(event.getSuggestedConfigurationFile()));

		// Initializing the Creative Tab
		instance.tab = new CreativeTabs("tabKitchen")
		{
			@Override
			public Item getTabIconItem()
				{ return KitchenItems.basic_sandwich.getItem(); }
		};

		instance.sandwichable_tab = new CreativeTabs("tabSandwichable")
		{
			@Override
			public Item getTabIconItem()
			{
				return KitchenItems.bread_slice;
			}
		};

		// Registering Tile Entities
		GameRegistry.registerTileEntity(TileEntityBoard.class, "tileEntityBoard");
		GameRegistry.registerTileEntity(TileEntityOven.class, "tileEntityOven");
		GameRegistry.registerTileEntity(TileEntityPlate.class, "tileEntityPlate");
        GameRegistry.registerTileEntity(TileEntityKitchenCabinet.class, "tileEntityKitchenCabinet");
        GameRegistry.registerTileEntity(TileEntityTextTest.class, "tilEntityTextTest");

        EntityRegistry.registerGlobalEntityID(EntityCup.class, "entityCup", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityCup.class, "entityCup", 1, instance, 250, 1, true);

		// Loading Blocks and Items
		BlockBase.load();
		ItemBase.load();

		// Registering renderers
		proxy.registerRenderers();

        // Registers the Plant generator
        GameRegistry.registerWorldGenerator(new WorldGenWildPlant(), 1);
    }
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		ModLogger.print(ModLogger.INFO, "Loading Combos...", null);
		// Loading Combos
        SandwichCombo.load();

        ModLogger.print(ModLogger.INFO, "Loading Custom Oven recipes...", null);
        // Loading Custom Oven recipes
        OvenRecipes.load();

		// Adding Tomatoes to the grass drop list
		MinecraftForge.addGrassSeed(new ItemStack(KitchenItems.tomato), 10);

		/**
		 * RECIPES
		 */

		// Cutting Board recipe
		GameRegistry.addRecipe(new ShapedOreRecipe(KitchenBlocks.board, "SPS", valueOf('S'), "slabWood", valueOf('P'), Blocks.wooden_pressure_plate));
		// Oven recipe
		GameRegistry.addRecipe(new ItemStack(KitchenBlocks.oven, 1, 0), "III", "ICI", "IFI", valueOf('I'), new ItemStack(Items.iron_ingot), valueOf('C'), new ItemStack(Items.coal), valueOf('F'), new ItemStack(Items.flint_and_steel));
		// Tile recipe
        GameRegistry.addRecipe(new ItemStack(KitchenBlocks.tiles, 2), "IB", "CC", "CC", valueOf('I'), new ItemStack(Items.dye, 1, 0), valueOf('B'), new ItemStack(Items.dye, 1, 15), valueOf('C'), Items.clay_ball);

        // Basic knife ItemStack to allow the recipe to be used with a knife, no matter the damage value
        ItemStack knife_stack = new ItemStack(KitchenItems.knife, 1, Short.MAX_VALUE);

		/**
		 * Knife recipes
		 */
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.raw_bacon, 3), knife_stack, new ItemStack(Items.porkchop));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.tomato_slice, 4), knife_stack, new ItemStack(KitchenItems.tomato));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.lettuce_leaf, 2), knife_stack, new ItemStack(KitchenItems.lettuce));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.carrot_slice, 2), knife_stack, new ItemStack(Items.carrot));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.potato_slice, 3), knife_stack, new ItemStack(Items.potato));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.bread_slice, 2), knife_stack, new ItemStack(Items.bread));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.raw_roast_beef, 2), knife_stack, new ItemStack(Items.beef));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.raw_chicken_fillet, 4), knife_stack, new ItemStack(Items.chicken));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.chicken_leg, 2), knife_stack, new ItemStack(Items.cooked_chicken));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.cheese_slice, 2), knife_stack, new ItemStack(KitchenItems.cheese));
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.sliced_creeper, 1), knife_stack, new ItemStack(Items.skull, 1, 4));

        // Cheese recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.cheese, 2), new ItemStack(Items.milk_bucket));
        // Mortar and Pestle recipe
        GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.mortar_and_pestle, 1), new ItemStack(KitchenItems.mortar), new ItemStack(KitchenItems.pestle));

        // Mortar and Pestle separate recipes
		GameRegistry.addRecipe(new ItemStack(KitchenItems.mortar), "S S", " S ", valueOf('S'), Blocks.stone);
		GameRegistry.addRecipe(new ItemStack(KitchenItems.pestle), "S ", " S", valueOf('S'), Blocks.stone);

        // Knife recipe. 0: default, 1: Iron torch, 2: Vertical, 3: Custom recipe
        switch (ModConfig.knifeRecipe)
        {
            case 0: GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, "I ", " S", valueOf('S'), "stickWood", valueOf('I'), Items.iron_ingot)); break; // Registers Normal recipe
            case 1: GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, "I", "S", valueOf('S'), "stickWood", valueOf('I'), Items.iron_ingot)); break; // Registers Iron Torch recipe
            case 2: GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, "IS", valueOf('S'), "stickWood", valueOf('I'), Items.iron_ingot)); break; // Registers Vertical recipe
			case 3:
			{
				ModLogger.print(ModLogger.INFO, "Registering custom Knife recipe...", null);

				ArrayList<String> lines = new ArrayList<String>();

				for(String line : ModConfig.customKnifeRecipe)
				{
                    // Add the line to the list, if it's not blank
					if (!line.equals("BBB"))
						lines.add(line);
				}

				ArrayList<Object> recipe = new ArrayList<Object>();

                // Adds the information to the list, formatting it just like in the normal GameRegistry.addRecipe method
				recipe.addAll(lines);
				recipe.add(valueOf('I'));
				recipe.add(Items.iron_ingot);
				recipe.add(valueOf('S'));
				recipe.add("stickWood");

                // Registers the recipe by converting the list to an array
				GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, recipe.toArray()));

				break;
			}
            // Registers the Basic recipe if the number is higher than 3, or lower than 0
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
