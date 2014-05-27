package dk.mrspring.kitchen.combo;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import dk.mrspring.kitchen.item.ItemSandwichable;

public class ComboBLT extends SandwichCombo
{
	public ComboBLT(int ID)
	{
		super(ID);
		this.setNames(new String[] { "toast", "bacon_cooked", "lettuce_leaf", "tomato_slice", "toast" });
		this.setRarity(EnumRarity.uncommon);
	}
	
	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		par1.add("The Good ol' BLT");
	}
	
	@Override
	public int getAdditionalHeal()
	{
		return 1;
	}
}
