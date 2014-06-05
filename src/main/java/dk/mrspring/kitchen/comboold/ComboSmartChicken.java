package dk.mrspring.kitchen.comboold;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class ComboSmartChicken extends SandwichCombo
{
	public ComboSmartChicken(int ID)
	{
		super(ID);
		this.setNames(new String[] {  });
		this.setRarity(EnumRarity.uncommon);
	}

	@Override
	public SandwichCombo getComboFromConfig(Configuration config)
	{
		SandwichCombo combo = new SandwichCombo();

		config.load();

		String[] names = config.get("Combo", "Layers - Layers required to make the Combo. Maximum of 10", new String[] { "toast", "chicken_fillet_cooked", "tomato_slice", "lettuce_leaf", "toast" }).getStringList();
		int rarity = config.get("Combo", "Rarity - What color the Name of the Item will become.", 2).getInt(); // TODO Add colors to integers in Config text
		String name = config.get("Combo", "Name - A short one-liner that is shown when you mouse over the Sandwich", "smart_chicken").getString();
		String description = config.get("Combo", "Description - The description of the Combo in the Book o' Sandwiches. Use '#' for new line. Use '.lang' if you want the description to be translatable.", ".lang").getString();

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
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		par1.add("7-Smart Chicken");
	}
	
	@Override
	public int getAdditionalHeal()
	{
		return 1;
	}
	
	@Override
	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.addPotionEffect(new PotionEffect(1, 10 * 20, 0));
	}
}
