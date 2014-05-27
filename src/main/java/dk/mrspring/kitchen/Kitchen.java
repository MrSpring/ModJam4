package dk.mrspring.kitchen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.kitchen.block.BlockBase;
import dk.mrspring.kitchen.item.ItemBase;
import dk.mrspring.kitchen.tileentity.TileEntityBoard;
import dk.mrspring.kitchen.world.gen.WorldGenWildLettuce;

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
				{ return KitchenItems.basic_sandwich.getItem(); }
		};
		
		GameRegistry.registerTileEntity(TileEntityBoard.class, "tileEntityBoard");
		
		BlockBase.load();
		ItemBase.load();
		
		instance.proxy.registerRenderers();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		MinecraftForge.addGrassSeed(new ItemStack(KitchenItems.tomato), 10);
		
		GameRegistry.registerWorldGenerator(new WorldGenWildLettuce(), 1);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(KitchenBlocks.board, new Object[] { "SPS", Character.valueOf('S'), "slabWood", Character.valueOf('P'), Blocks.wooden_pressure_plate }));
		GameRegistry.addRecipe(new ShapedOreRecipe(KitchenItems.knife, new Object[] { "I ", " S", Character.valueOf('S'), "stickWood", Character.valueOf('I'), Items.iron_ingot }));
		
		GameRegistry.addRecipe(new ItemStack(KitchenItems.raw_bacon, 3), new Object[] { "K", "P", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('P'), Items.porkchop });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.tomato_slice, 4), new Object[] { "K", "T", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('T'), KitchenItems.tomato });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.lettuce_leaf, 5), new Object[] { "K", "L", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('L'), KitchenItems.lettuce });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.carrot_slice, 2), new Object[] { "K", "C", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('C'), Items.carrot });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.potato_slice, 3), new Object[] { "K", "P", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('P'), Items.potato });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.bread_slice, 2), new Object[] { "K", "B", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('B'), Items.bread });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.raw_roast_beef, 2), new Object[] { "K", "B", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('B'), Items.beef });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.raw_chicken_fillet, 4), new Object[] { "K", "C", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('C'), Items.chicken });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.chicken_leg, 2), new Object[] { "K", "C", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('C'), Items.cooked_chicken });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.cheese_slice, 2), new Object[] { "K", "C", Character.valueOf('K'), KitchenItems.knife, Character.valueOf('C'), KitchenItems.cheese });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.cheese, 2), new Object[] { "M", Character.valueOf('M'), Items.milk_bucket });
		
		GameRegistry.addRecipe(new ItemStack(KitchenItems.mortar_and_pestle), new Object[] { "P", "M", Character.valueOf('M'), KitchenItems.mortar, Character.valueOf('P'), KitchenItems.pestle });
		
		GameRegistry.addRecipe(new ItemStack(KitchenItems.flour, 1), new Object[] { "W", "M", Character.valueOf('M'), KitchenItems.mortar_and_pestle, Character.valueOf('W'), Items.wheat });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.flour, 3), new Object[] { "B", "M", Character.valueOf('M'), KitchenItems.mortar_and_pestle, Character.valueOf('B'), Items.bread });
		
		GameRegistry.addRecipe(new ItemStack(KitchenItems.mortar), new Object[] { "S S", " S ", Character.valueOf('S'), Blocks.stone });
		GameRegistry.addRecipe(new ItemStack(KitchenItems.pestle), new Object[] { "S ", " S", Character.valueOf('S'), Blocks.stone });
		
		GameRegistry.addSmelting(KitchenItems.raw_bacon, new ItemStack(KitchenItems.bacon, 1, 0), 3.0F);
		GameRegistry.addSmelting(KitchenItems.flour, new ItemStack(KitchenItems.toast, 2, 0), 3.0F);
		GameRegistry.addSmelting(KitchenItems.raw_chicken_fillet, new ItemStack(KitchenItems.chicken_fillet, 1, 0), 3.0F);
		GameRegistry.addSmelting(KitchenItems.raw_roast_beef, new ItemStack(KitchenItems.roast_beef, 1, 0), 3.0F);
	}
}
