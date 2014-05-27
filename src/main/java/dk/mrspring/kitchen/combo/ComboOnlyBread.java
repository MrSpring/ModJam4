package dk.mrspring.kitchen.combo;

import java.util.List;

import net.minecraft.item.EnumRarity;
import dk.mrspring.kitchen.item.ItemSandwichable;

public class ComboOnlyBread extends SandwichCombo
{
	public ComboOnlyBread(int ID, String[] names)
	{
		super(ID);
		this.setNames(names);
	}
	
	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("Kinda Boring.");
	}
}
