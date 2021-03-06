package dk.mrspring.kitchen;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.kitchen.item.*;
import dk.mrspring.kitchen.item.boardable.ItemBoardAnalyzer;
import dk.mrspring.kitchen.item.boardable.sandwichable.ItemButter;
import dk.mrspring.kitchen.item.boardable.sandwichable.ItemSandwichBread;
import dk.mrspring.kitchen.item.boardable.sandwichable.ItemSandwichableBase;
import dk.mrspring.kitchen.item.jam.ItemJam;
import dk.mrspring.kitchen.item.jam.Jam;
import dk.mrspring.kitchen.model.ModelBaconCooked;
import dk.mrspring.kitchen.model.ModelBaconRaw;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class KitchenItems
{
	// All the Item variables
	public static final Item knife = new ItemKnife("knife").setMaxStackSize(1);
    public static final Item butter_knife = new ItemKnife("butter_knife").setMaxDamage(1);

	public static final Item mortar_and_pestle = new ItemMandP().setMaxStackSize(1);
	public static final Item mortar = new ItemBase("mortar", true);
	public static final Item pestle = new ItemBase("pestle", true);

	public static final Item raw_bacon = new ItemSandwichableBase("bacon_raw", true, 1).setModel(new ModelBaconRaw(), 1);
	public static final Item bread_slice = new ItemSandwichBread("bread_slice");

	// The basic sandwich ItemStack. It's used as the icon fot the creative tab
	public static ItemStack basic_sandwich = getSandwichItemStackWithNBTTags(new ItemStack[] { new ItemStack(bread_slice, 1, 0), new ItemStack(raw_bacon, 1, 0), new ItemStack(bread_slice, 1, 0) });

	public static final Item tomato = new ItemFoodBase("tomato", 2);
	public static final Item lettuce = new ItemFoodBase("lettuce", 2);

	public static final Item tomato_slice = new ItemSandwichableBase("tomato_slice", true, 1);
	public static final Item lettuce_leaf = new ItemSandwichableBase("lettuce_leaf", true, 2);

	public static final Item bacon = new ItemSandwichableBase("bacon_cooked", true, 4).setModel(new ModelBaconCooked(), 2);

	public static final Item potato_slice = new ItemSandwichableBase("potato_slice", true, 2);
	public static final Item carrot_slice = new ItemSandwichableBase("carrot_slice", true, 2);

	public static final Item flour = new ItemBase("flour", true);

	public static final Item toast = new ItemSandwichableBase("toast", true, 0).setIsBread();

	public static final Item raw_roast_beef = new ItemSandwichableBase("raw_roast_beef", ModInfo.modid + ":beef_slice", true, 1);
	public static final Item roast_beef = new ItemSandwichableBase("roast_beef", true, 4);
	public static final Item raw_chicken_fillet = new ItemSandwichableBase("chicken_fillet_raw", true, 1);
	public static final Item chicken_fillet = new ItemSandwichableBase("chicken_fillet_cooked", true, 4);

	public static final Item chicken_leg = new ItemFoodBase("chicken_leg", 4, true);

	public static final Item cheese = new ItemFoodBase("cheese", 3);
	public static final Item cheese_slice = new ItemSandwichableBase("cheese_slice", true, 3);

    public static final Item burnt_meat = new ItemFoodBase("burnt_meat", 1);

    public static final Item sliced_creeper = new ItemSandwichableBase("creeper_slice", false, 2);

    public static final Item cup = new ItemCup();

    public static final Item board_analyzer = new ItemBoardAnalyzer();

    public static final Item butter = new ItemButter("butter");

    public static final Item hand_book = new ItemHandBook("hand_book", true);
	public static final Item strawberry = new ItemFoodBase("strawberry", 2);
	public static final ItemJam strawberry_jam = new ItemJam(Jam.STRAWBERRY, "strawberry");
	public static final ItemJam apple_jam = new ItemJam(Jam.APPLE, "apple");

	// Pre-loads the sandwich ItemStack with some NBT-Data.
	private static ItemStack getSandwichItemStackWithNBTTags(ItemStack[] layers)
	{
		ItemSandwich sandwich = new ItemSandwich();
		GameRegistry.registerItem(sandwich, "sandwich");
		
		ItemStack basicSandwich = new ItemStack(sandwich, 1, 0);
		
		NBTTagList layersList = new NBTTagList();

		for (ItemStack layer : layers)
		{
			NBTTagCompound layerCompound = new NBTTagCompound();
			layer.writeToNBT(layerCompound);
			layersList.appendTag(layerCompound);
		}
		
		basicSandwich.setTagInfo("SandwichLayers", layersList);
		
		return basicSandwich;
	}
}
