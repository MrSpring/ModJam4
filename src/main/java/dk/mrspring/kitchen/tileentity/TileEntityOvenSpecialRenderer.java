package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelOven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityOvenSpecialRenderer extends TileEntitySpecialRenderer
{
    ModelOven model;

    public TileEntityOvenSpecialRenderer()
    {
        super();

        this.model = new ModelOven();
    }

    @Override
    public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
    {
        TileEntityOven tileEntityOven = (TileEntityOven) var1;

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        ResourceLocation texture = new ResourceLocation(ModInfo.modid + ":textures/models/oven.png");

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glPushMatrix();

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, tileEntityOven.isOpen());

        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }
}
