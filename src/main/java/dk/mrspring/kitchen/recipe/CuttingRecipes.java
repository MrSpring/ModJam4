package dk.mrspring.kitchen.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 02-09-2014.
 */
public class CuttingRecipes
{
    private static List<ItemStack> inputStacks = new ArrayList<ItemStack>();
    private static List<ItemStack> outputStacks = new ArrayList<ItemStack>();

    public static void registerCuttingRecipe(ItemStack input, ItemStack output)
    {
        inputStacks.add(input);
        outputStacks.add(output);
    }

    public static ItemStack getCuttingOutput(ItemStack input)
    {
        ItemStack temp = input.copy();
        temp.stackSize = 1;

        if (hasOutput(input))
        {
            int index = getIndex(temp);
            return outputStacks.get(index);
        } else return null;
    }

    public static boolean hasOutput(ItemStack item)
    {
        ItemStack temp = item.copy();

        for (ItemStack input : inputStacks)
        {
            if (OreDictionary.itemMatches(temp, input, false))
                return true;
        }
        return inputStacks.contains(temp);
    }

    private static int getIndex(ItemStack itemStack)
    {
        ItemStack temp = itemStack.copy();
        temp.stackSize = 1;

        for (int i = 0; i < inputStacks.size(); i++) {
            ItemStack input = inputStacks.get(i);
            if (OreDictionary.itemMatches(temp, input, false))
                return i;
        }

        return inputStacks.indexOf(temp);
    }
}
