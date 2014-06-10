package dk.mrspring.kitchen;

import net.minecraftforge.common.config.Configuration;

public class ModConfig
{
    public static int lettuceSpawnRate;
    public static int knifeRecipe;

    public static void load(Configuration config)
    {
        config.load();

        lettuceSpawnRate = config.get("WorldGen", "Lettuce Spawn percentage - How big a chance lettuce has to spawn per chunk, 0 to disable", 10).getInt();
        knifeRecipe = config.get("Recipes", "Knife Recipes - 0: Default Recipe, 1: Iron Torch Recipe, 3: Stick next to Iron Recipe", 0).getInt();

        config.save();
    }
}
