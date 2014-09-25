package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.item.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 06-09-2014 for ModJam4.
 */
public class TileEntityMixingBowl extends TileEntity
{
    protected List<IIngredient> ingredients = new ArrayList<IIngredient>();

    public boolean addIngredient(ItemStack itemStack)
    {
        if (itemStack != null)
            if (itemStack.getItem() instanceof IIngredient)
            {
                this.ingredients.add((IIngredient) itemStack.getItem());
                return true;
            }

        return false;
    }
}
