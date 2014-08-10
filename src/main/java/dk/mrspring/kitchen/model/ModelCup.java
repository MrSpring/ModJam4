package dk.mrspring.kitchen.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCup extends ModelBase
{
  //fields
    ModelRenderer base;
    ModelRenderer side1;
    ModelRenderer base1;
    ModelRenderer side2;
    ModelRenderer base2;
    ModelRenderer corner1;
    ModelRenderer corner2;
    ModelRenderer corner3;
    ModelRenderer corner4;
    ModelRenderer base3;
    ModelRenderer side3;
    ModelRenderer base4;
    ModelRenderer side4;
    ModelRenderer handleBottom;
    ModelRenderer handleSide;
    ModelRenderer handleTop;
  
  public ModelCup()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      base = new ModelRenderer(this, 0, 0);
      base.addBox(0F, 0F, 0F, 4, 1, 4);
      base.setRotationPoint(-2F, 23F, -2F);
      base.setTextureSize(64, 32);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      side1 = new ModelRenderer(this, 36, 9);
      side1.addBox(0F, 0F, 0F, 4, 5, 1);
      side1.setRotationPoint(-2F, 18F, -3.5F);
      side1.setTextureSize(64, 32);
      side1.mirror = true;
      setRotation(side1, 0F, 0F, 0F);
      base1 = new ModelRenderer(this, 25, 2);
      base1.addBox(0F, 0F, 0F, 4, 1, 1);
      base1.setRotationPoint(-2F, 23F, -3F);
      base1.setTextureSize(64, 32);
      base1.mirror = true;
      setRotation(base1, 0F, 0F, 0F);
      side2 = new ModelRenderer(this, 25, 11);
      side2.addBox(0F, 0F, 0F, 1, 5, 4);
      side2.setRotationPoint(-3.5F, 18F, -2F);
      side2.setTextureSize(64, 32);
      side2.mirror = true;
      setRotation(side2, 0F, 0F, 0F);
      base2 = new ModelRenderer(this, 25, 5);
      base2.addBox(0F, 0F, 0F, 1, 1, 4);
      base2.setRotationPoint(-3F, 23F, -2F);
      base2.setTextureSize(64, 32);
      base2.mirror = true;
      setRotation(base2, 0F, 0F, 0F);
      corner1 = new ModelRenderer(this, 36, 2);
      corner1.addBox(0F, 0F, 0F, 1, 5, 1);
      corner1.setRotationPoint(-3F, 18F, 2F);
      corner1.setTextureSize(64, 32);
      corner1.mirror = true;
      setRotation(corner1, 0F, 0F, 0F);
      corner2 = new ModelRenderer(this, 41, 2);
      corner2.addBox(0F, 0F, 0F, 1, 5, 1);
      corner2.setRotationPoint(-3F, 18F, -3F);
      corner2.setTextureSize(64, 32);
      corner2.mirror = true;
      setRotation(corner2, 0F, 0F, 0F);
      corner3 = new ModelRenderer(this, 36, 2);
      corner3.addBox(0F, 0F, 0F, 1, 5, 1);
      corner3.setRotationPoint(2F, 18F, -3F);
      corner3.setTextureSize(64, 32);
      corner3.mirror = true;
      setRotation(corner3, 0F, 0F, 0F);
      corner4 = new ModelRenderer(this, 41, 2);
      corner4.addBox(0F, 0F, 0F, 1, 5, 1);
      corner4.setRotationPoint(2F, 18F, 2F);
      corner4.setTextureSize(64, 32);
      corner4.mirror = true;
      setRotation(corner4, 0F, 0F, 0F);
      base3 = new ModelRenderer(this, 25, 5);
      base3.addBox(0F, 0F, 0F, 1, 1, 4);
      base3.setRotationPoint(2F, 23F, -2F);
      base3.setTextureSize(64, 32);
      base3.mirror = true;
      setRotation(base3, 0F, 0F, 0F);
      side3 = new ModelRenderer(this, 25, 11);
      side3.addBox(0F, 0F, 0F, 1, 5, 4);
      side3.setRotationPoint(2.5F, 18F, -2F);
      side3.setTextureSize(64, 32);
      side3.mirror = true;
      setRotation(side3, 0F, 0F, 0F);
      base4 = new ModelRenderer(this, 25, 2);
      base4.addBox(0F, 0F, 0F, 4, 1, 1);
      base4.setRotationPoint(-2F, 23F, 2F);
      base4.setTextureSize(64, 32);
      base4.mirror = true;
      setRotation(base4, 0F, 0F, 0F);
      side4 = new ModelRenderer(this, 36, 9);
      side4.addBox(0F, 0F, 0F, 4, 5, 1);
      side4.setRotationPoint(-2F, 18F, 2.5F);
      side4.setTextureSize(64, 32);
      side4.mirror = true;
      setRotation(side4, 0F, 0F, 0F);
      handleBottom = new ModelRenderer(this, 18, 8);
      handleBottom.addBox(0F, 0F, 0F, 2, 1, 1);
      handleBottom.setRotationPoint(3.5F, 21.5F, -0.5F);
      handleBottom.setTextureSize(64, 32);
      handleBottom.mirror = true;
      setRotation(handleBottom, 0F, 0F, 0F);
      handleSide = new ModelRenderer(this, 20, 11);
      handleSide.addBox(0F, 0F, 0F, 1, 2, 1);
      handleSide.setRotationPoint(5F, 19.5F, -0.5F);
      handleSide.setTextureSize(64, 32);
      handleSide.mirror = true;
      setRotation(handleSide, 0F, 0F, 0F);
      handleTop = new ModelRenderer(this, 18, 5);
      handleTop.addBox(0F, 0F, 0F, 2, 1, 1);
      handleTop.setRotationPoint(3.5F, 18.5F, -0.5F);
      handleTop.setTextureSize(64, 32);
      handleTop.mirror = true;
      setRotation(handleTop, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    base.render(f5);
    side1.render(f5);
    base1.render(f5);
    side2.render(f5);
    base2.render(f5);
    corner1.render(f5);
    corner2.render(f5);
    corner3.render(f5);
    corner4.render(f5);
    base3.render(f5);
    side3.render(f5);
    base4.render(f5);
    side4.render(f5);
    handleBottom.render(f5);
    handleSide.render(f5);
    handleTop.render(f5);
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
