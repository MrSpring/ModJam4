package dk.mrspring.kitchen;

import cpw.mods.fml.client.registry.ClientRegistry;
import dk.mrspring.kitchen.item.render.ItemRenderSandwich;
import dk.mrspring.kitchen.tileentity.TileEntityBoard;
import dk.mrspring.kitchen.tileentity.TileEntityBoardSpecialRenderer;
import dk.mrspring.kitchen.tileentity.TileEntityOven;
import dk.mrspring.kitchen.tileentity.TileEntityOvenSpecialRenderer;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoard.class, new TileEntityBoardSpecialRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOven.class, new TileEntityOvenSpecialRenderer());

		MinecraftForgeClient.registerItemRenderer(GameRegisterer.findItem("sandwich"), (IItemRenderer) new ItemRenderSandwich());
	}
}
