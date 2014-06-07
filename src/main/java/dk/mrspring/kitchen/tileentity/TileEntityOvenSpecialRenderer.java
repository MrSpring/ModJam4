package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.item.ItemSandwichable;
import dk.mrspring.kitchen.model.ModelOven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityOvenSpecialRenderer extends TileEntitySpecialRenderer
{
    protected ModelOven model;
	protected ResourceLocation inactiveTexture;
	protected ResourceLocation activeTexture;
	protected TileEntityOven tileEntityOven;

	ItemStack[] itemStacks;

    public TileEntityOvenSpecialRenderer()
    {
        super();

        this.model = new ModelOven();

		this.inactiveTexture = new ResourceLocation(ModInfo.modid + ":textures/models/oven.png");
		this.activeTexture = new ResourceLocation(ModInfo.modid + ":textures/models/oven_active.png");
    }

    @Override
    public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
    {
		if (this.tileEntityOven == null)
        	this.tileEntityOven = (TileEntityOven) var1;

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		if (tileEntityOven.burnTime > 0)
        	Minecraft.getMinecraft().renderEngine.bindTexture(activeTexture);
		else
			Minecraft.getMinecraft().renderEngine.bindTexture(inactiveTexture);

        GL11.glPushMatrix();

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, this.tileEntityOven.getLidAngle());

		itemStacks = new ItemStack[4];

		itemStacks = this.tileEntityOven.getOvenItems();

		double d = 0.5D;

		for(int i = 0; i < itemStacks.length; ++i)
		{
			if (itemStacks[i] != null)
			{
				switch (i)
				{
					case 0: renderItem(itemStacks[i], d, 1.2, -d); break;
					case 1: renderItem(itemStacks[i], d, 1.2, d); break;
					case 2: renderItem(itemStacks[i], -d, 1.2, d); break;
					case 3: renderItem(itemStacks[i], -d, 1.2, -d); break;
				}
			}
		}

        GL11.glPopMatrix();

        GL11.glPopMatrix();

		tileEntityOven = null;
    }

	private void renderItem(ItemStack item, double xOffset, double yOffset, double zOffset)
	{
		if (item != null)
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
}
