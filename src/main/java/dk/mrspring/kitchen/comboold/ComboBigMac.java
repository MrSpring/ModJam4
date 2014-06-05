package dk.mrspring.kitchen.comboold;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class ComboBigMac extends SandwichCombo
{
	@Override
	public SandwichCombo getComboFromConfig(Configuration config)
	{
		SandwichCombo combo = new SandwichCombo();

		config.load();

		String[] names = config.get("Combo", ComboConfig.defaultLayers, new String[] { "bread_slice", "cheese_slice", "roast_beef", "lettuce_leaf", "bread_slice", "roast_beef", "lettuce_leaf", "bread_slice" }).getStringList();
		int rarity = config.get("Combo", ComboConfig.defaultRarity, 3).getInt();

		boolean useTranslator = config.get("Combo", ComboConfig.defaultUseTranslator, true).getBoolean(true);

		String name, description;

		if (useTranslator)
		{
			name = config.get("Combo", ComboConfig.translatableName, "big_mac").getString();
		}
		else
		{
			name = config.get("Combo", ComboConfig.defaultToolTop, "There is only one!").getString();
			description = config.get("Combo", ComboConfig.defaultDescription, "Blah blah blah.#BLAH!!!").getString();
		}

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
	
	@Override
	public int getAdditionalHeal()
	{
		return 1;
	}
	
	@Override
	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.addPotionEffect(new PotionEffect(2, 20 * 20, 0));
	}
}
