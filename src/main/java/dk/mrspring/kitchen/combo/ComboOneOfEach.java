package dk.mrspring.kitchen.combo;

import java.util.List;

import net.minecraft.item.EnumRarity;
import dk.mrspring.kitchen.item.ItemSandwichable;

public class ComboOneOfEach extends SandwichCombo
{
	public ComboOneOfEach(int ID, ItemSandwichable[] items)
	{
		super(ID, items);
		
		this.setRarity(EnumRarity.epic);
	}
	
	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("One Of Each");
	}
}
