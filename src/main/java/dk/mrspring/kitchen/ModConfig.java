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
    // Oven custom recipes
    public static String[] customOvenRecipes;
    // Oven custom recipes Results
    public static String[] customOvenRecipesResults;

	// Loads the config file and the variables
    public static void load(Configuration config)
    {
		// Opens the config so it can be read
        config.load();

        lettuceSpawnRate = config.get("WorldGen", "Lettuce Spawn percentage - How big a chance lettuce has to spawn per chunk, 0 to disable", 10).getInt();
        knifeRecipe = config.get("Recipes", "Knife Recipes - 0: Default Recipe, 1: Iron Torch Recipe, 2: Stick next to Iron Recipe, 3: Custom Recipe", 0).getInt(0);
		customKnifeRecipe = config.get("Recipes", "Custom Knife Recipe - I: Iron Ingot, S: Stick, Any other characters will be seen as blank spots. This is the exact layout in the crafting table!", new String[] { "BBI", "BIB", "SBB" }).getStringList();
        customOvenRecipes =                     config.get("Recipes", "Custom Oven Recipes - The name of the Item to be able to cook in the Oven",   new String[] { "kitchen:raw_roast_beef", "kitchen:raw_chicken_fillet", "kitchen:raw_bacon" }).getStringList();
        customOvenRecipesResults = config.get("Recipes", "Custom Oven Recipes Results - The name of the result Item, when it's cooked in the Oven.", new String[] { "kitchen:roast_beef", "kitchen:chicken_fillet", "kitchen:bacon" }).getStringList();

		// Closes the config, and saves the changes made
        config.save();
    }
}
