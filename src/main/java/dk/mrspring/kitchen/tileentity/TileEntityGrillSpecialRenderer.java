package dk.mrspring.kitchen.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityGrillSpecialRenderer extends TileEntitySpecialRenderer
{

    @Override
    public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
    {
        /*
         * 3 2
		 * 0 1
		 */

        GL11.glPushMatrix();

            GL11.glTranslated(x, y, z);

            TileEntityGrill tileEntityGrill = (TileEntityGrill) var1;

            for(int i = 0; i < 4; ++i)
            {
                this.renderItem(tileEntityGrill.getItem(i), 0.0, 1.4, 0.0 + (i * 0.4));
            }

            /*this.renderItem(tileEntityGrill.getItem(0), -0.25D + 0.5D, 1.4D, -0.25D + 0.5D);
            this.renderItem(tileEntityGrill.getItem(1), -0.25D + 0.5D, 1.4D, +0.25D + 0.5D);
            this.renderItem(tileEntityGrill.getItem(2), +0.25D + 0.5D, 1.4D, -0.25D + 0.5D);
            this.renderItem(tileEntityGrill.getItem(3), +0.25D + 0.5D, 1.4D, +0.25D + 0.5D);
*/
        GL11.glPopMatrix();
    }

    private void renderItem(ItemStack item, double xOffset, double yOffset, double zOffset)
    {
        GL11.glPushMatrix();

            if (item != null)
            {
                GL11.glTranslated(xOffset, yOffset, zOffset - 0.2);

                EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, item);
                itemEntity.hoverStart = 0.0F;
                RenderItem.renderInFrame = true;
                GL11.glRotatef(180, 0, 1, 1);
                RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;

                GL11.glTranslated(0.0D, 0.0D, 0.0D);
            }

        GL11.glPopMatrix();
    }
}