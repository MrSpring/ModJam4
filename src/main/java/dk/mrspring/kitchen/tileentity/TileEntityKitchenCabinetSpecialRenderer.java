package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelKitchenCabinet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Konrad on 12-07-2014 for The Kitchen Mod.
 */
public class TileEntityKitchenCabinetSpecialRenderer extends TileEntitySpecialRenderer
{
    ModelKitchenCabinet model;
    ResourceLocation texture;

    public TileEntityKitchenCabinetSpecialRenderer()
    {
        this.model = new ModelKitchenCabinet();
        this.texture = new ResourceLocation(ModInfo.modid, "textures/models/kitchen_cabinet.png");
    }

    @Override
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef(0.5F + (float) var2, 1.5F + (float) var4, 0.5F + (float) var6);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);

        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, 0.0F);

        GL11.glPopMatrix();
    }
}
