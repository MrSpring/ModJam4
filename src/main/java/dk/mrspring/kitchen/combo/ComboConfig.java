package dk.mrspring.kitchen.combo;

import net.minecraft.item.EnumRarity;
import net.minecraftforge.common.config.Configuration;

public class ComboConfig
{
	public SandwichCombo getComboFromConfig(Configuration config, int id)
	{
		SandwichCombo combo = new SandwichCombo(id);
		
		config.load();
			
			String[] names = config.get("Combo", "Layers - Layers required to make the Combo. Maximum of 10", new String[] { "bread_slice", "bread_slice" }).getStringList();
			int rarity = config.get("Combo", "Rarity - What color the Name of the Item will become.", 0).getInt(); // TODO Add colors to integers
			String name = config.get("Combo", "Name - A short one-liner that is shown when you mouse over the Sandwich", "sandwich.combo.default").getString();
			String description = config.get("Combo", "Description - The description of the Combo in the Book o' Sandwiches. Use '#' for new line.", "sandwich.combo.description.default").getString();
			
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
			
		config.save();
		
		return combo;
	}
}
