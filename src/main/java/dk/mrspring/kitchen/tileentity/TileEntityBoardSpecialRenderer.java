package dk.mrspring.kitchen.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class TileEntityBoardSpecialRenderer extends TileEntitySpecialRenderer
{
	private double x, y, z;
	private ItemStack[] layers;
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		TileEntityBoard tileEntity = (TileEntityBoard) var1;
		
		this.layers = tileEntity.getLayers();
		
		for (int i = 0; i < this.layers.length; ++i)
		{
			if (this.layers[i] != null)
				renderItem(this.layers[i], 0.5D, 0.145D + (i * 0.031D), 0.33D);
		}
	}
	
	private void renderItem(ItemStack item, double xOffset, double yOffset, double zOffset)
	{
		GL11.glPushMatrix();
		
			GL11.glTranslated(this.x + xOffset, this.y + yOffset, this.z + zOffset);
			
			EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, item);
			itemEntity.hoverStart = 0.0F;
			RenderItem.renderInFrame = true;
			GL11.glRotatef(180, 0, 1, 1);
			RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
			
		GL11.glPopMatrix();
	}
}
