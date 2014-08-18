package dk.mrspring.kitchen.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Created by MrSpring on 14-08-14 for MC Music Player.
 */
public class TileEntityFridgeSpecialRenderer extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
	{
		TileEntityFridge tileEntity = (TileEntityFridge) var1;

		System.out.println(" Rendering: " + tileEntity.getItemStack(0).getDisplayName());
		this.renderItem(tileEntity.getItemStack(0), .5D, .5D, .5D);
	}

	private void renderItem(ItemStack item, double xOffset, double yOffset, double zOffset)
	{
		if (item != null)
		{
			GL11.glPushMatrix();

			GL11.glTranslated(xOffset, yOffset, zOffset);

			ItemStack toRender = item.copy();
			toRender.stackSize = 1;

			EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, toRender);
			itemEntity.hoverStart = 0.0F;
			RenderItem.renderInFrame = true;
			GL11.glRotatef(180, 0, 1, 1);
			RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;

			GL11.glPopMatrix();
		}
	}
}
