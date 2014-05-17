package dk.mrspring.kitchen.combo;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import dk.mrspring.kitchen.item.ItemSandwichable;

public class ComboBLT extends SandwichCombo
{
	public ComboBLT(int ID, String[] names)
	{
		super(ID, names);
		
		this.setRarity(EnumRarity.epic);
	}
	
	@Override
	public void addCustomItemInformation(List par1)
	{
		par1.add("One Of Each");
	}
}
