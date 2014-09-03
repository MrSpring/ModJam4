package dk.mrspring.kitchen.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.mrspring.kitchen.item.board.IBoardable;
import dk.mrspring.kitchen.item.render.ItemRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class TileEntityBoardSpecialRenderer extends TileEntitySpecialRenderer
{
    @Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
	{
        double yItemOffset = 0;
		
		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y, z);
		
		TileEntityBoard tileEntity = (TileEntityBoard) var1;

        List<ItemStack> layers = tileEntity.getAllItems();

		ItemRenderHelper.renderSandwich(layers, tileEntity.getSpecialTagInfo());

        /*for (int i = 0; i < layers.size(); i++)
        {
            ItemStack itemStack = layers.get(i);
            if (itemStack != null)
                if (itemStack.getItem() != null)
                {
                    NBTTagCompound specialCompound = new NBTTagCompound();
                    if (i == layers.size() - 1)
                        specialCompound = tileEntity.specialTagInfo;

                    this.renderItem(itemStack, .5, .145 + ((yItemOffset * 0.0625) * .7), .33D, i, layers, specialCompound);
                    yItemOffset += ((IBoardable) itemStack.getItem()).getRenderHeight(null, i, itemStack, layers);
                }
        }*/

		/*for (int i = 0; i < this.layers.size(); ++i)
		{
			if (this.layers.get(i) != null)
				renderItem(this.layers[i], 0.5D, 0.145D + (i * 0.033F + this.yItemOffset), 0.33D, i);
		}*/
		
		GL11.glPopMatrix();
	}
	
	private void renderItem(ItemStack item, double xOffset, double yOffset, double zOffset, int index, List<ItemStack> items, NBTTagCompound specialInfo)
	{
		GL11.glPushMatrix();
			
			GL11.glTranslated(xOffset, yOffset, zOffset);

            ModelBase model = ((IBoardable) item.getItem()).getModel(specialInfo, index, item, items);

            if (model != null)
            {
                model.render(Minecraft.getMinecraft().renderViewEntity, 0F, 0F, 0F, 0F, 0F, 0.0625F);
            } else
            {
                EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, item);
                itemEntity.hoverStart = 0.0F;
                RenderItem.renderInFrame = true;
                GL11.glRotatef(180, 0, 1, 1);
                RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;
            }
			
			/*if (((ISandwichable) this.layers[i].getItem()).hasCustomModel)
				if (i + 1 < this.layers.length)
					if (this.layers[i + 1] != null)
						{ ((ISandwichable) this.layers[i].getItem()).getBottomModel().render(Minecraft.getMinecraft().renderViewEntity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F); this.yItemOffset += (((ISandwichable) this.layers[i].getItem()).modelBottomHeight * 0.03D); }
					else
						{ ((ISandwichable) this.layers[i].getItem()).getTopModel().render(Minecraft.getMinecraft().renderViewEntity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F); this.yItemOffset += (((ISandwichable) this.layers[i].getItem()).modelTopHeight * 0.03D); }
				else
				{ ((ISandwichable) this.layers[i].getItem()).getTopModel().render(Minecraft.getMinecraft().renderViewEntity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F); this.yItemOffset += (((ISandwichable) this.layers[i].getItem()).modelTopHeight * 0.03D); }
			else*/
			/*{
				EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, item);
				itemEntity.hoverStart = 0.0F;
				RenderItem.renderInFrame = true;
				GL11.glRotatef(180, 0, 1, 1);
				RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
				RenderItem.renderInFrame = false;
			}*/
			
		GL11.glPopMatrix();
	}
}
