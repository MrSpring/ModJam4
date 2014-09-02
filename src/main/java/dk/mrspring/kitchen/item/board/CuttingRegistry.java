package dk.mrspring.kitchen.item.board;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 02-09-2014.
 */
public class CuttingRegistry
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

        if (inputStacks.contains(temp))
        {
            int index = inputStacks.indexOf(temp);
            return outputStacks.get(index);
        } else return null;
    }

    public static boolean hasOutput(ItemStack item)
    {
        ItemStack temp = item.copy();
        temp.stackSize = 1;
        return inputStacks.contains(temp);
    }
}
