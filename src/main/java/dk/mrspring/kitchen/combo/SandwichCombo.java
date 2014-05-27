package dk.mrspring.kitchen.combo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import dk.mrspring.kitchen.KitchenItems;
import dk.mrspring.kitchen.item.ItemSandwichable;

public class SandwichCombo
{
	protected ArrayList<String> comboLayers = new ArrayList<String>();
	protected EnumRarity rarity = EnumRarity.common;
	protected String unlocalizedName = "sandwich.combo.untitled";
	protected String description = "Default description, edit the config!";
	
	public static SandwichCombo[] combos = new SandwichCombo[16];
	private int lastId = 0;
	
	public SandwichCombo(int ID)
	{
		combos[ID] = this;
	}
	
	/*public static final SandwichCombo defaultCombo = new SandwichCombo(0).setNames(new String[] {  });;
	public static final SandwichCombo blt = new ComboBLT(1);
	public static final SandwichCombo onlyBread = new ComboOnlyBread(2, new String[] { "bread_slice", "bread_slice" });
	public static final SandwichCombo bigMac = new ComboBigMac(3);
	public static final SandwichCombo smartChicken = new ComboSmartChicken(4);
	public static final SandwichCombo retroRB = new ComboRetroRoastBeef(5);
	public static final SandwichCombo veggie = new ComboVeggie(6);*/
	
	public void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) { }
	
	public void addCustomItemInformation(List par1)
	{
		par1.add("");
		
		if (StatCollector.canTranslate(this.getUnlocalizedName()))
			par1.add(StatCollector.translateToLocal(this.getUnlocalizedName()));
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
