package dk.mrspring.kitchen.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Konrad on 12-07-2014 for The Kitchen Mod.
 */
public class ModelKitchenTable extends ModelBase
{
    public static final int NO_CORNER = 0;
    public static final int CORNER_LEFT = 1;
    public static final int CORNER_RIGHT = 2;

    //fields
    ModelRenderer base;
    ModelRenderer top;

    ModelRenderer corner1;
    ModelRenderer corner2;

    public ModelKitchenTable()
    {
        textureWidth = 64;
        textureHeight = 64;

        base = new ModelRenderer(this, 1, 0);
        base.addBox(0F, 0F, 0F, 16, 13, 14);
        base.setRotationPoint(-8F, 11F, -6F);
        base.setTextureSize(64, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        top = new ModelRenderer(this, 0, 28);
        top.addBox(0F, 0F, 0F, 16, 3, 16);
        top.setRotationPoint(-8F, 8F, -8F);
        top.setTextureSize(64, 64);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);

        corner1 = new ModelRenderer(this, 15, 0);
        corner1.addBox(0.0F, 0.0F, 0.0F, 14, 13, 2);
        corner1.setRotationPoint(-8F, 11F, -8F);
        corner1.setTextureSize(64, 64);
        corner1.mirror = true;
        setRotation(corner1, 0F, 0F, 0F);

        corner2 = new ModelRenderer(this, 15, 0);
        corner2.addBox(0.0F, 0.0F, 0.0F, 14, 13, 2);
        corner2.setRotationPoint(-6F, 11F, -8F);
        corner2.setTextureSize(64, 64);
        corner2.mirror = true;
        setRotation(corner2, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int cornerType)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        base.render(f5);
        top.render(f5);

        switch (cornerType)
        {
            case NO_CORNER: break;
            case CORNER_LEFT: corner1.render(f5); break;
            case CORNER_RIGHT: corner2.render(f5); break;
            default: break;
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
