package dk.mrspring.kitchen;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dk.mrspring.kitchen.entity.EntityCup;
import dk.mrspring.kitchen.entity.RenderCup;
import dk.mrspring.kitchen.item.render.ItemRenderHandBook;
import dk.mrspring.kitchen.item.render.ItemRenderSandwich;
import dk.mrspring.kitchen.tileentity.*;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoard.class, new TileEntityBoardSpecialRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOven.class, new TileEntityOvenSpecialRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlate.class, new TileEntityPlateSpecialRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKitchenCabinet.class, new TileEntityKitchenCabinetSpecialRenderer());

        RenderingRegistry.registerEntityRenderingHandler(EntityCup.class, new RenderCup());

		MinecraftForgeClient.registerItemRenderer(GameRegisterer.findItem("sandwich"), new ItemRenderSandwich());
        MinecraftForgeClient.registerItemRenderer(KitchenItems.hand_book, new ItemRenderHandBook());
	}
}
