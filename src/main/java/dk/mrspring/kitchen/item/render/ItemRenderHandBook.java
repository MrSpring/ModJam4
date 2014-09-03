package dk.mrspring.kitchen.item.render;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelHandBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
			case EQUIPPED_FIRST_PERSON:
			{
				if (((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glRotatef(180, 0.0F, 0.0F, 1F);
					GL11.glTranslatef(-0.7F, -0.5F, 0.7F);
					GL11.glScalef(0.8F, 0.8F, 0.8F);
					
					GL11.glPushMatrix();
					
					GL11.glRotatef(90, 0.2F, 0.5F, 0.2F);

					Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ModInfo.modid, "textures/models/book.png"));

					model.render((Entity) data[1], 0F, 0F, 0F, 0F, 0F, 0.0625F);
					
					GL11.glPopMatrix();
				}
			}
            default: break;
        }
    }
}
