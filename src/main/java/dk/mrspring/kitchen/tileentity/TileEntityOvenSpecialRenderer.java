package dk.mrspring.kitchen.tileentity;

import dk.mrspring.kitchen.ModInfo;
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
		TileEntityOven tileEntityOven = (TileEntityOven) var1;

        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		if (tileEntityOven.burnTime > 0)
        	Minecraft.getMinecraft().renderEngine.bindTexture(activeTexture);
		else
			Minecraft.getMinecraft().renderEngine.bindTexture(inactiveTexture);

        GL11.glPushMatrix();

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        GL11.glPushMatrix();
        int metadata;
        metadata = tileEntityOven.getBlockMetadata();

        GL11.glRotatef(metadata * (90), 0F, 1F, 0F);

        this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, tileEntityOven.getLidAngle());
        GL11.glPopMatrix();

		itemStacks = new ItemStack[4];

		itemStacks = tileEntityOven.getOvenItems();

		double d = 0.2;

		GL11.glScalef(0.75F, 0.75F, 0.75F);

		for(int i = 0; i < itemStacks.length; ++i)
		{
			if (itemStacks[i] != null)
			{
				switch (i)
				{
					case 0: renderItem(itemStacks[i], d, 1.6, -d - 0.2); break;
					case 1: renderItem(itemStacks[i], d, 1.6, d - 0.2); break;
					case 2: renderItem(itemStacks[i], -d, 1.6, d - 0.2); break;
					case 3: renderItem(itemStacks[i], -d, 1.6, -d - 0.2); break;
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

			ItemStack toRender = item.copy();
			toRender.stackSize = 1;

			EntityItem itemEntity = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, toRender);
			itemEntity.hoverStart = 0.0F;
			RenderItem.renderInFrame = true;
			GL11.glRotatef(180, 0, 1, 1);
			RenderManager.instance.renderEntityWithPosYaw(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;

			GL11.glPopMatrix();
		}
	}
}
