package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelPlate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPlateSpecialRenderer extends TileEntitySpecialRenderer
{
	ModelPlate modelPlate;
	ResourceLocation texture;

	public TileEntityPlateSpecialRenderer()
	{
		this.modelPlate = new ModelPlate();
		this.texture = new ResourceLocation(ModInfo.modid + ":textures/models/plate.png");
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
	{
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

		GL11.glPushMatrix();

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		int metadata = var1.getBlockMetadata();
		GL11.glRotatef(metadata * (45F), 0.0F, 1.0F, 0.0F);
		this.modelPlate.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0635F);

		GL11.glPopMatrix();

		GL11.glPopMatrix();
	}
}
