package dk.mrspring.kitchen.combo;

import dk.mrspring.kitchen.event.sandwich.IOnEatenEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SandwichCombo
{
    protected ArrayList<String> comboLayers = new ArrayList<String>();
    protected String name = "default";
    protected EnumRarity rarity = EnumRarity.common;
    protected int extraHeal = 0;
    protected IOnEatenEvent onSandwichEatenEvent;
	public int id;

    public static SandwichCombo[] combos = new SandwichCombo[16];

	public SandwichCombo(int id, String localizableName, EnumRarity enumRarity)
    {
        if (combos[id] == null)
            combos[id] = this;

		this.id = id;

        this.setName(localizableName);
        this.setRarity(enumRarity);
    }

    public static SandwichCombo big_mac;
    public static SandwichCombo blt;
    public static SandwichCombo only_bread;
    public static SandwichCombo retro_roast_beef;
    public static SandwichCombo smart_chicken;
    public static SandwichCombo veggie;
    public static SandwichCombo creeper;
	public static SandwichCombo only_toast;

    public static void load()
    {
        big_mac = new SandwichCombo(1, "big_mac", EnumRarity.rare).setComboLayers(new String[] { "bread_slice", "cheese_slice", "roast_beef", "lettuce_leaf", "bread_slice", "roast_beef", "lettuce_leaf", "bread_slice" }).setExtraHeal(1);
        blt = new SandwichCombo(2, "blt", EnumRarity.uncommon).setComboLayers(new String[] { "toast", "bacon_cooked", "lettuce_leaf", "tomato_slice", "toast" }).setExtraHeal(1);
        only_bread = new SandwichCombo(3, "only_bread", EnumRarity.common).setComboLayers(new String[] { "bread_slice", "bread_slice" });
        retro_roast_beef = new SandwichCombo(4, "rrb", EnumRarity.uncommon).setComboLayers(new String[] { "bread_slice", "roast_beef", "roast_beef", "tomato_slice", "lettuce_leaf", "bread_slice" }).setExtraHeal(3);
        smart_chicken = new SandwichCombo(5, "smart_chicken", EnumRarity.uncommon).setComboLayers(new String[] { "toast", "chicken_fillet_cooked", "tomato_slice", "lettuce_leaf", "toast" });
        veggie = new SandwichCombo(6, "veggie", EnumRarity.rare).setComboLayers(new String[] { "bread_slice", "carrot_slice", "lettuce_leaf", "tomato_slice", "cheese_slice", "bread_slice" });
        creeper = new SandwichCombo(7, "creeper", EnumRarity.epic).setComboLayers(new String[] { "bread_slice", "bread_slice", "lettuce_leaf", "lettuce_leaf", "creeper_slice" }).registerOnEatenEvent(new IOnEatenEvent()
		{
			@Override
			public String getName()
			{
				return "ON_EATEN:CREEPER";
			}

			@Override
			public void onEaten(ItemStack item, World world, EntityPlayer entityPlayer)
			{
				entityPlayer.addPotionEffect(new PotionEffect(11, 10, 6, false));
				if (!world.isRemote)
					world.createExplosion(entityPlayer, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, 1.5F, world.getGameRules().getGameRuleBooleanValue("mobGriefing"));
			}
		});
		only_toast = new SandwichCombo(8, "only_bread", EnumRarity.common).setComboLayers(new String[] { "toast", "toast" });
    }

    public SandwichCombo setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getUnlocalizedName()
    {
        return "combo." + this.name;
    }

    public String getLocalizedName()
    {
        if (StatCollector.canTranslate(this.getUnlocalizedName() + ".name"))
            return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
        else
            return this.getUnlocalizedName() + ".name";
    }

    public SandwichCombo setRarity(EnumRarity rarity)
    {
        this.rarity = rarity;
        return this;
    }

    public EnumRarity getRarity()
    {
        return this.rarity;
    }

    public SandwichCombo setComboLayers(String[] array)
    {
        this.comboLayers = new ArrayList<String>(Arrays.asList(array));
        return this;
    }

    public ArrayList<String> getComboLayers()
    {
        return comboLayers;
    }

    public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        this.onSandwichEatenEvent.onEaten(itemStack, world, player);
    }

    public SandwichCombo setExtraHeal(int extraHeal)
    {
        this.extraHeal = extraHeal;
        return this;
    }

    public SandwichCombo registerOnEatenEvent(IOnEatenEvent event)
    {
        this.onSandwichEatenEvent = event;
        return this;
    }

    public int getExtraHeal()
    {
        return this.extraHeal;
    }

    public void addCustomInfo(List list)
    {
        list.add("");

        if (StatCollector.canTranslate(this.getUnlocalizedName() + ".name"))
            list.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".name"));
        else
            list.add(this.getUnlocalizedName() + ".name");
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
}
