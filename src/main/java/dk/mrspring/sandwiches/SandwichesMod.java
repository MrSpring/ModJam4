package dk.mrspring.sandwiches;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dk.mrspring.sandwiches.block.BlockBase;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class SandwichesMod
{
	@Instance(ModInfo.modid)
	public static SandwichesMod instance;
	
	public static CreativeTabs tab;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		tab = new CreativeTabs("tabSandwiches")
		{
			@Override
			public Item getTabIconItem()
				{ return Items.bread; }
		};
		
		BlockBase.load();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		
	}
}
