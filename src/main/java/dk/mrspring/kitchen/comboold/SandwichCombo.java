package dk.mrspring.kitchen.comboold;

import java.util.ArrayList;
import java.util.List;

import dk.mrspring.kitchen.ModLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class SandwichCombo
{
	protected ArrayList<String> comboLayers = new ArrayList<String>();
	protected EnumRarity rarity = EnumRarity.common;
	protected String unlocalizedName = "sandwich.comboold.name.default";
	protected String description = ""; // TODO Add localization of: sandwich.comboold.description.default to: The default description; edit in the Config!
	protected OnEatenEffect onEaten;
	
	public static SandwichCombo[] combos = new SandwichCombo[32];
	private static int lastID = 0;

	public SandwichCombo()
	{

	}

	public static SandwichCombo defaultCombo = new SandwichCombo();

	public static final SandwichCombo blt = new ComboBLT();
	public static final SandwichCombo onlyBread = new ComboOnlyBread();
	public static final SandwichCombo bigMac = new ComboBigMac();
	public static final SandwichCombo smartChicken = new ComboSmartChicken();
	public static final SandwichCombo retroRB = new ComboRetroRoastBeef();
	public static final SandwichCombo veggie = new ComboVeggie();

	public static void addCombo(SandwichCombo combo)
	{
		if (lastID > combos.length)
			ModLogger.print(ModLogger.WARNING, "No more room in the Array.");
		else
		{
			combos[lastID] = combo;
			++lastID;
		}
	}

	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) { }
	
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		
		if (StatCollector.canTranslate(this.getDescription()))
			par1.add(StatCollector.translateToLocal(this.getDescription()));
		else
		{
			par1.add("");

			String[] splitDescription;
			String description = this.getDescription();

			splitDescription = description.split("#");

			for(String line : splitDescription)
				par1.add(line);
		}
	}
	
	public void setRarity(EnumRarity par1)
	{
		this.rarity = par1;
	}
	
	public EnumRarity getRarity()
	{
		return this.rarity;
	}
	
	public boolean matches(ItemStack sandwich)
	{
		ArrayList<String> layersInSandwich = new ArrayList<String>();
		NBTTagList layersList = sandwich.stackTagCompound.getTagList("SandwichLayers", 10);
		
		for (int i = 0; i < layersList.tagCount(); ++i)
		{
			NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
			String name = ItemStack.loadItemStackFromNBT(layerCompound).getItem().getUnlocalizedName().replace("item.", "");
			
			layersInSandwich.add(name);
		}
		if (layersInSandwich.containsAll(this.comboLayers) && this.comboLayers.containsAll(layersInSandwich))
			return true;
		else
			return false;
	}
	
	public SandwichCombo setNames(String[] names)
	{
		for (int i = 0; i < names.length; ++i)
		{
			if (names[i] != null)
				this.comboLayers.add(names[i]);
		}
		
		return this;
	}

	public SandwichCombo getComboFromConfig(Configuration config)
	{
		SandwichCombo combo = new SandwichCombo();

		config.load();

			String[] names = config.get("Combo", "Layers - Layers required to make the Combo. Maximum of 10", new String[] { "bread_slice", "bread_slice" }).getStringList();
			int rarity = config.get("Combo", "Rarity - What color the Name of the Item will become.", 0).getInt(); // TODO Add colors to integers in Config text
			String name = config.get("Combo", "Name - A short one-liner that is shown when you mouse over the Sandwich", "sandwich.comboold.name.default").getString();
			String description = config.get("Combo", "Description - The description of the Combo in the Book o' Sandwiches. Use '#' for new line.", "sandwich.comboold.description.default").getString();

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

	public SandwichCombo registerOnEatenEffect(OnEatenEffect effect)
	{
		if (effect != null)
			this.onEaten = effect;
		return this;
	}
	
	public int getAdditionalHeal()
	{
		return 0;
	}
	
	public void setUnlocalizedName(String name)
	{
		this.unlocalizedName = name;
	}
	
	public String getUnlocalizedName()
	{
		return this.unlocalizedName;
	}
	
	public String getLocalizedName()
	{
		if (StatCollector.canTranslate(this.getUnlocalizedName()))
			return StatCollector.translateToLocal(this.getUnlocalizedName());
		else
			return this.getUnlocalizedName();
	}

	public SandwichCombo setDesription(String description)
	{
		this.description = description;
		return this;
	}
	
	public String getDescription()
	{
		return description;
	}
}
