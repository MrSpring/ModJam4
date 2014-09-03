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
		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y, z);
		TileEntityBoard tileEntity = (TileEntityBoard) var1;
        List<ItemStack> layers = tileEntity.getAllItems();
		ItemRenderHelper.renderSandwich(layers, tileEntity.getSpecialTagInfo());
		
		GL11.glPopMatrix();
	}
}
