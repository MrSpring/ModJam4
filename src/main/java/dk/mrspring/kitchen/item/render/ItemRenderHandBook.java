package dk.mrspring.kitchen.item.render;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelHandBook;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRenderHandBook implements IItemRenderer
{
    protected ModelHandBook model = new ModelHandBook();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
            case EQUIPPED: return true;
            case EQUIPPED_FIRST_PERSON: return true;
            default: return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case EQUIPPED:
            {
                GL11.glPushMatrix();

                GL11.glRotatef(110F, 1F, 0F, 0F);
                GL11.glRotatef(16F, 0F, 1F, 0F);
                GL11.glRotatef(-0F, 0F, 0F, 1F);
                
                GL11.glTranslatef(.43F, .0F, -.2F);
                
                float scale = 0.75F;
                
                GL11.glScalef(scale, scale, scale);
                
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.modid, "textures/models/book.png"));

                model.render((Entity) data[1], 0F, 0F, 0F, 0F, 0F, 0.0625F);

                GL11.glPopMatrix();
            }
            default: break;
        }
    }
}
