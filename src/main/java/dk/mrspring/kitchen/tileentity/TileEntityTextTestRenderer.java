package dk.mrspring.kitchen.tileentity;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityTextTestRenderer extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        FontRenderer fontRenderer = this.func_147498_b();

        GL11.glPushMatrix();

        GL11.glTranslated(var2, var4, var6);

        float f1 = 0.6666667F, f3;
        f3 = 0.016666668F * f1;
        GL11.glTranslatef(0F, .5F * f1, 0.07F * f1);
        GL11.glScalef(f3, -f3, f3);
        GL11.glNormal3f(0F, 0F, -1F * f3);
        GL11.glDepthMask(false);
        byte b0 = 0;
        String text = "This is test text!";

        fontRenderer.drawString(text, 0, 0, b0);

        GL11.glDepthMask(true);
        GL11.glColor4f(1F, 1F, 1F, 1F);

        GL11.glPopMatrix();
    }
}
