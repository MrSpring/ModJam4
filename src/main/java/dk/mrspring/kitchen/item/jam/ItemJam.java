package dk.mrspring.kitchen.item.jam;

import dk.mrspring.kitchen.Kitchen;
import dk.mrspring.kitchen.item.ItemBase;

/**
 * Created by MrSpring on 25-09-2014 for ModJam4.
 */
public class ItemJam extends ItemBase
{
	private final Jam jam;
	public ItemJam(Jam jam, String name)
	{
		super(name + "_jam", true);
		this.jam = jam;

		this.setCreativeTab(Kitchen.instance.jam_tab);
	}

	public Jam getJam()
	{
		return jam;
	}
}
