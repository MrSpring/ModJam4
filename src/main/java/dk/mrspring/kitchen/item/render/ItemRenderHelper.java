package dk.mrspring.kitchen.item.render;

import dk.mrspring.kitchen.item.boardable.IBoardable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by MrSpring on 03-09-14 for The Kitchen Mod.
 */
public class ItemRenderHelper
{
	public static void renderSandwich(List<ItemStack> layers, NBTTagCompound specialTagInfo)
	{
		double yItemOffset = 0D;

		for (int i = 0; i < layers.size(); i++)
		{
			ItemStack itemStack = layers.get(i);
			if (itemStack != null)
				if (itemStack.getItem() != null)
				{
					NBTTagCompound specialCompound = new NBTTagCompound();
					if (i == layers.size() - 1)
						specialCompound = specialTagInfo;

					renderItem(itemStack, .5, .145 + ((yItemOffset * 0.0625) * .7), .33D, layers, i, specialCompound);
					yItemOffset += ((IBoardable) itemStack.getItem()).getRenderHeight(null, i, itemStack, layers);
				}
		}
	}

	public static void renderItem(ItemStack item, double xOffset, double yOffset, double zOffset, List<ItemStack> items, int indexInList, NBTTagCompound specialTagInfo)
	{
		GL11.glPushMatrix();

			GL11.glTranslated(xOffset, yOffset, zOffset);

			NBTTagCompound specialTagInfoTemp = specialTagInfo;
			if (indexInList != items.size() - 1)
				specialTagInfoTemp = null;

			ModelBase model = ((IBoardable) item.getItem()).getModel(specialTagInfoTemp, indexInList, item, items);

			if (model != null)
				model.render(Minecraft.getMinecraft().renderViewEntity, 0F, 0F, 0F, 0F, 0F, 0.0625F);
			else
				renderSimpleItem(item, xOffset, yOffset, zOffset);

		GL11.glPopMatrix();
	}
	
	public static void renderSimpleItem(ItemStack item, double xOffset, double yOffset, double zOffset)
	{
		EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, item);
		itemEntity.hoverStart = 0.0F;
		RenderItem.renderInFrame = true;
		GL11.glRotatef(180, 0, 1, 1);
		RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		RenderItem.renderInFrame = false;
	}
}
