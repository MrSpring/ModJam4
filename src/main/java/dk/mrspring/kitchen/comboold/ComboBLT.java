package dk.mrspring.kitchen.comboold;

import net.minecraft.item.EnumRarity;
import net.minecraftforge.common.config.Configuration;

public class ComboBLT extends SandwichCombo
{
	@Override
	public SandwichCombo getComboFromConfig(Configuration config)
	{
		SandwichCombo combo = new SandwichCombo();

		config.load();

		String[] names = config.get("Combo", "Layers - Layers required to make the Combo. Maximum of 10", new String[] { "toast", "bacon_cooked", "lettuce_leaf", "tomato_slice", "toast" }).getStringList();
		int rarity = config.get("Combo", "Rarity - What color the Name of the Item will become.", 2).getInt(); // TODO Add colors to integers in Config text
		String name = config.get("Combo", "Name - A short one-liner that is shown when you mouse over the Sandwich", "blt").getString();
		String description = config.get("Combo", "Description - The description of the Combo in the Book o' Sandwiches. Use '#' for new line.", ".lang").getString();

		config.save();

		switch(rarity)
		{
			case 1: combo.setRarity(EnumRarity.common);
			case 2: combo.setRarity(EnumRarity.uncommon);
			case 3: combo.setRarity(EnumRarity.rare);
			case 4: combo.setRarity(EnumRarity.epic);
			default: combo.setRarity(EnumRarity.common);
		}

		combo.setUnlocalizedName(name);
		combo.setNames(names);
		combo.setDesription(description);

		return combo;
	}

/*	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		par1.add("The Good ol' BLT");
	}

	@Override
	public int getAdditionalHeal()
	{
		return 1;
	}*/
}
