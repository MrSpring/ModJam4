package dk.mrspring.kitchen.entity;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelCup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCup extends Render
{
    ModelCup model;

    public RenderCup()
    {
        model = new ModelCup();
    }

    @Override
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef(/*0.5F + */(float) var2, 1.5F + (float) var4, /*0.5F + */(float) var6);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        Minecraft.getMinecraft().renderEngine.bindTexture(this.getEntityTexture(var1));

        GL11.glPushMatrix();

        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1)
    {
        return new ResourceLocation(ModInfo.modid, "textures/models/cup.png");
    }
}
