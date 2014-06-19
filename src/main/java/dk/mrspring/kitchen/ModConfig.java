package dk.mrspring.kitchen;

import net.minecraftforge.common.config.Configuration;

public class ModConfig
{
	// The lettuce spawn rate
    public static int lettuceSpawnRate;
	// Which knife recipe to use
    public static int knifeRecipe;

	// Loads the config file and the variables
    public static void load(Configuration config)
    {
		// Opens the config so it can be read
        config.load();

		// Getting the Lettuce Spawn rate from the config
        lettuceSpawnRate = config.get("WorldGen", "Lettuce Spawn percentage - How big a chance lettuce has to spawn per chunk, 0 to disable", 10).getInt();
		// Getting the Knife Recipe from the config
        knifeRecipe = config.get("Recipes", "Knife Recipes - 0: Default Recipe, 1: Iron Torch Recipe, 3: Stick next to Iron Recipe", 0).getInt(0);

		// Closes the config, and saves the changes made
        config.save();
    }
}
