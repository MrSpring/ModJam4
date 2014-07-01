package dk.mrspring.kitchen;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by Konrad on 01-07-2014.
 */
public class OvenRecipes
{
    static ArrayList<ItemStack>[] customOvenRecipes = new ArrayList[2];

    public static void load()
    {
        if (ModConfig.customOvenRecipes.length == ModConfig.customOvenRecipesResults.length)
        {
            ArrayList<ItemStack> input = new ArrayList<ItemStack>();
            ArrayList<ItemStack> output = new ArrayList<ItemStack>();

            for (int i = 0; i < ModConfig.customOvenRecipes.length; ++i)
            {
                String modId = "minecraft";
                String itemName;

                System.out.println(" Input:");

                if (ModConfig.customOvenRecipes[i].contains(":"))
                {
                    modId = ModConfig.customOvenRecipes[i].split(":")[0];
                    itemName = ModConfig.customOvenRecipes[i].split(":")[1];

                    ModLogger.print(ModLogger.DEBUG, " ModID: " + modId);
                    ModLogger.print(ModLogger.DEBUG, " Item Name: " + itemName);
                } else
                    itemName = ModConfig.customOvenRecipes[i];

                ItemStack inputStack = GameRegistry.findItemStack(modId, itemName, 1);
                ModLogger.print(ModLogger.DEBUG, " Returned ItemStack Display Name: " + inputStack.getDisplayName());


                System.out.println(" Output:");

                if (ModConfig.customOvenRecipesResults[i].contains(":"))
                {
                    modId = ModConfig.customOvenRecipesResults[i].split(":")[0];
                    itemName = ModConfig.customOvenRecipesResults[i].split(":")[1];

                    ModLogger.print(ModLogger.DEBUG, "  ModID: " + modId);
                    ModLogger.print(ModLogger.DEBUG, "  Item Name: " + itemName);
                } else
                    itemName = ModConfig.customOvenRecipesResults[i];

                ItemStack outputStack = GameRegistry.findItemStack(modId, itemName, 1);
                System.out.println(" Returned ItemStack Display Name: " + outputStack.getDisplayName());

                System.out.println("");
                System.out.println("");

                input.add(inputStack);
                output.add(outputStack);
            }

            customOvenRecipes[0] = input;
            customOvenRecipes[1] = output;
        }
        else
        {
            ModLogger.print(ModLogger.INFO, "Unable to load Custom oven recipes! Loading defaults.");
            ModLogger.print(ModLogger.DEBUG, "One of the list were bigger than the other! Input length: " + ModConfig.customOvenRecipes.length + ", Output length: " + ModConfig.customOvenRecipesResults.length);


        }
    }

    public static ArrayList<ItemStack> getArrayFromStringList(String[] list)
    {

    }
}
