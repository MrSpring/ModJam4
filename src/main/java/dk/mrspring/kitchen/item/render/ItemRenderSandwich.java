package dk.mrspring.kitchen.item.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ItemRenderSandwich implements IItemRenderer
{
	protected List<ItemStack> items;

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		switch(type)
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
            if (item.getTagCompound() != null)
            {
                NBTTagList layersList = item.getTagCompound().getTagList("SandwichLayers", 10);

                if (layersList != null)
                {

                    this.items = new ArrayList<ItemStack>();

                    for (int i = 0; i < layersList.tagCount(); ++i)
                    {
                        NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
                        items.add(ItemStack.loadItemStackFromNBT(layerCompound));
                    }

                    GL11.glPushMatrix();

                    if (data[1] != null && data[1] instanceof EntityPlayer)
                    {
                        if (!((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
                        {
                            GL11.glRotatef(180, -0.15F, 1.0F, -0.6F);
                            GL11.glTranslatef(-1.1F, -0.4F, -0.9F);
                            GL11.glScalef(1.4F, 1.4F, 1.4F);
                        } else
                        {
                            GL11.glRotatef(180, 0.5F, 0.15F, 0.0F);
                            GL11.glTranslatef(1.0F, -1.0F, 1.0F);
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                        }
                    } else
					{
                        GL11.glRotatef(180, 0.5F, 1.0F, 0.0F);
                        GL11.glTranslatef(0.0F, 0.7F, -0.2F);
                    }

                    ItemRenderHelper.renderSandwich(items, null);

                    GL11.glPopMatrix();
                }
            }
		}
		case EQUIPPED_FIRST_PERSON:
		{
			if (((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
			{
                if (item.getTagCompound() != null)
                {
                    NBTTagList layersList = item.stackTagCompound.getTagList("SandwichLayers", 10);

                    if (layersList != null)
                    {
						this.items = new ArrayList<ItemStack>();

						for (int i = 0; i < layersList.tagCount(); ++i)
						{
							NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
							items.add(ItemStack.loadItemStackFromNBT(layerCompound));
						}

                        GL11.glPushMatrix();
                        
                        if (((EntityPlayer) data[1]).isUsingItem())
                        {
                        	GL11.glRotatef(180, 0.6F, 0.2F, 0.6F);
	                        GL11.glTranslatef(-0.5F, -0.6F, 1.1F);
	                        GL11.glScalef(0.8F, 0.8F, 0.8F);
                        } else
                        {
	                        GL11.glRotatef(180, 0.6F, 0.2F, 0.5F);
	                        GL11.glTranslatef(-0.7F, -0.5F, 0.7F);
	                        GL11.glScalef(0.8F, 0.8F, 0.8F);
                        }
                        GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);

                        ItemRenderHelper.renderSandwich(items, null);

                        GL11.glPopMatrix();
                    }
                }
			}
		}
		default: break;
		}
	}
}
