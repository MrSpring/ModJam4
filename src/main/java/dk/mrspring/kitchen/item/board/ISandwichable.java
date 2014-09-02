package dk.mrspring.kitchen.item.board;

/**
 * Created by MrSpring on 02-09-14 for The Kitchen Mod.
 */
public interface ISandwichable extends IBoardable
{
	public int getHealAmount();

	public boolean isBread();
}
