package dk.mrspring.kitchen.item.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRenderSandwich implements IItemRenderer
{
	protected ItemStack[] items;
	protected boolean acquiredItems = false;
	
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
			NBTTagList layersList = item.stackTagCompound.getTagList("SandwichLayers", 10);
			
			this.items = new ItemStack[layersList.tagCount()];
			
			for (int i = 0; i < layersList.tagCount(); ++i)
			{
				NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
				items[i] = ItemStack.loadItemStackFromNBT(layerCompound);
			}
			
			GL11.glPushMatrix();
			
			if (data[1] != null && data[1]  instanceof EntityPlayer)
			{
				if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
				{
					GL11.glRotatef(180, 0.5F, 1.0F, 0.0F);
					GL11.glTranslatef(0.0F, 0.7F, -0.2F);
				}
				else
				{
					GL11.glRotatef(180, 0.5F, 0.15F, 0.0F);
					GL11.glTranslatef(1.0F, -1.0F, 1.0F);
					GL11.glScalef(0.6F, 0.6F, 0.6F);
				}
			}
			else
			{
				GL11.glRotatef(180, 0.5F, 1.0F, 0.0F);
				GL11.glTranslatef(0.0F, 0.7F, -0.2F);
			}
			
			for (int i = 0; i < this.items.length; ++i)
			{
				this.renderItemEntity(this.items[i], 0.0D, (i * 0.031D), 0.0D);
			}
				
			GL11.glPopMatrix();
		}
		case EQUIPPED_FIRST_PERSON:
		{
			if (((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
			{
				NBTTagList layersList = item.stackTagCompound.getTagList("SandwichLayers", 10);
				
				this.items = new ItemStack[layersList.tagCount()];
				
				for (int i = 0; i < layersList.tagCount(); ++i)
				{
					NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
					items[i] = ItemStack.loadItemStackFromNBT(layerCompound);
				}
				
				GL11.glPushMatrix();
				
				GL11.glRotatef(180, 0.5F, 0.15F, 0.0F);
				GL11.glTranslatef(0.35F, -0.5F, 0.4F);
				GL11.glScalef(0.6F, 0.6F, 0.6F);
				
				for (int i = 0; i < this.items.length; ++i)
				{
					this.renderItemEntity(this.items[i], 0.0D, (i * 0.0311D), 0.0D);
				}
					
				GL11.glPopMatrix();
			}
		}
		default: break;
		}
	}
	
	private void renderItemEntity(ItemStack item, double xOffset, double yOffset, double zOffset)
	{
		GL11.glPushMatrix();
		
			GL11.glTranslated(xOffset, yOffset, zOffset);
			
			EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, item);
			itemEntity.hoverStart = 0.0F;
			RenderItem.renderInFrame = true;
			GL11.glRotatef(180, 0, 1, 1);
			RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
			
		GL11.glPopMatrix();
	}
}
