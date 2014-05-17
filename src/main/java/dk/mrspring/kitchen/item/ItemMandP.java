package dk.mrspring.kitchen.item;

public class ItemMandP extends ItemBase
{
	public ItemMandP()
	{
		super("mortar_and_pestle", true);
		
		this.setContainerItem(this);
	}
}
