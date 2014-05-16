package dk.mrspring.kitchen.item.render;

import org.lwjgl.opengl.GL11;

import dk.mrspring.kitchen.item.ItemSandwichable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.client.IItemRenderer;

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
			if (!this.acquiredItems)
			{
				NBTTagList layersList = item.stackTagCompound.getTagList("SandwichLayers", 10);
				
				this.items = new ItemStack[layersList.tagCount()];
				
				for (int i = 0; i < layersList.tagCount(); ++i)
				{
					NBTTagCompound layerCompound = layersList.getCompoundTagAt(i);
					items[i] = ItemStack.loadItemStackFromNBT(layerCompound);
				}
				
				this.acquiredItems = true;
			}
			GL11.glPushMatrix();
				
				for (int i = 0; i < this.items.length; ++i)
				{
					this.renderItemEntity(this.items[i], 0.0D, (i * 0.031D), 0.0D);
				}
				
			GL11.glPopMatrix();
		}
		default: break;
		}
	}
	
	private void renderItemEntity(ItemStack item, double xOffset, double yOffset, double zOffset)
	{
		GL11.glPushMatrix();
		
			GL11.glTranslated(this.x + xOffset, this.y + yOffset, this.z + zOffset);
			
			EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, item);
			itemEntity.hoverStart = 0.0F;
			RenderItem.renderInFrame = true;
			GL11.glRotatef(180, 0, 1, 1);
			RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
			
		GL11.glPopMatrix();
	}
}
