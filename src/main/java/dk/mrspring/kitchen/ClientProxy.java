package dk.mrspring.kitchen;

import dk.mrspring.kitchen.tileentity.TileEntityGrill;
import dk.mrspring.kitchen.tileentity.TileEntityGrillSpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import dk.mrspring.kitchen.item.render.ItemRenderSandwich;
import dk.mrspring.kitchen.tileentity.TileEntityBoard;
import dk.mrspring.kitchen.tileentity.TileEntityBoardSpecialRenderer;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoard.class, new TileEntityBoardSpecialRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrill.class, new TileEntityGrillSpecialRenderer());

		MinecraftForgeClient.registerItemRenderer(GameRegisterer.findItem("sandwich"), (IItemRenderer) new ItemRenderSandwich());
	}
}
