package dk.mrspring.kitchen;

import net.minecraftforge.common.config.Configuration;

public class ModConfig
{
	// The lettuce spawn rate
    public static int lettuceSpawnRate;
	// Which knife recipe to use
    public static int knifeRecipe;
	// Custom Knife recipe array
	public static String[] customKnifeRecipe;

	// Loads the config file and the variables
    public static void load(Configuration config)
    {
		// Opens the config so it can be read
        config.load();

		// Getting the Lettuce Spawn rate from the config
        lettuceSpawnRate = config.get("WorldGen", "Lettuce Spawn percentage - How big a chance lettuce has to spawn per chunk, 0 to disable", 10).getInt();
		// Getting the Knife Recipe from the config
        knifeRecipe = config.get("Recipes", "Knife Recipes - 0: Default Recipe, 1: Iron Torch Recipe, 2: Stick next to Iron Recipe, 3: Custom Recipe", 0).getInt(0);
		// Custom Knife Recipe
		customKnifeRecipe = config.get("Recipes", "Custom Knife Recipe - I: Iron Ingot, S: Stick, Any other characters will be seen as blank spots. This is the exact layout in the crafting table!", new String[] { "BBI", "BIB", "SBB" }).getStringList();

		// Closes the config, and saves the changes made
        config.save();
    }
}
