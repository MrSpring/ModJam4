package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelCup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityCupSpecialRenderer extends TileEntitySpecialRenderer
{
    ModelCup model;
    ResourceLocation texture;

    public TileEntityCupSpecialRenderer()
    {
        model = new ModelCup();
        texture = new ResourceLocation(ModInfo.modid, "textures/models/cup.png");
    }

    @Override
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef(0.5F + (float) var2, 1.5F + (float) var4, 0.5F + (float) var6);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);

        GL11.glPushMatrix();

        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }
}
