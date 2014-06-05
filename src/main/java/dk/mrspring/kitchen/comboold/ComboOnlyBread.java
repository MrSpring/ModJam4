package dk.mrspring.kitchen.comboold;

import net.minecraft.item.EnumRarity;
import net.minecraftforge.common.config.Configuration;

public class ComboOnlyBread extends SandwichCombo
{
	@Override
	public SandwichCombo getComboFromConfig(Configuration config)
	{
		SandwichCombo combo = new SandwichCombo();

		config.load();

		String[] names = config.get("Combo", ComboConfig.defaultLayers, new String[] { "bread_slice", "bread_slice" }).getStringList();
		int rarity = config.get("Combo", ComboConfig.defaultRarity, 2).getInt(); // TODO Add colors to integers in Config text
		String name = config.get("Combo", ComboConfig.defaultName, "only_bread").getString();
		String description = config.get("Combo", ComboConfig.defaultDescription, ".lang").getString();

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
}
