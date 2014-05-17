package dk.mrspring.kitchen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.kitchen.block.BlockBase;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.tileentity.TileEntityBoard;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class Kitchen
{
	@Instance(ModInfo.modid)
	public static Kitchen instance;
	
	@SidedProxy(serverSide = "dk.mrspring.kitchen.CommonProxy", clientSide = "dk.mrspring.kitchen.ClientProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tab;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		tab = new CreativeTabs("tabKitchen")
		{
			@Override
			public Item getTabIconItem()
				{ return Items.bread; }
		};
		
		GameRegistry.registerTileEntity(TileEntityBoard.class, "tileEntityBoard");
		
		BlockBase.load();
		ItemBase.load();
		
		instance.proxy.registerRenderers();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(KitchenBlocks.board, new Object[] { "SPS", Character.valueOf('S'), "slabWood", Character.valueOf('P'), Blocks.wooden_pressure_plate }));
		GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, new Object[] { "I ", " S", Character.valueOf('S'), "stickWood", Character.valueOf('I'), Items.iron_ingot }));
		
		GameRegistry.addShapelessRecipe(new ItemStack(KitchenItems.bacon_raw, 3), new Object[] { new ItemStack(KitchenItems.knife), new ItemStack(Items.porkchop) });
		
		/*GameRegistry.addRecipe(new ItemStack(KitchenItems.bacon_raw), new Object[] {
			"K", "P", 
			Character.valueOf('K'), KitchenItems.knife,
			Character.valueOf('P'), Items.porkchop });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.tomato_slice), new Object[] { "K", "T", 
			Character.valueOf('K'), KitchenItems.knife,
			Character.valueOf('R'), KitchenItems.lettuce });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.lettuce_leaf), new Object[] { "K", "L", 
			Character.valueOf('K'), KitchenItems.knife,
			Character.valueOf('P'),KitchenItems.tomato }); */
	}
}
