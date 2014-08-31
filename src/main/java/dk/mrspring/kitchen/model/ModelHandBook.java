package dk.mrspring.kitchen.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHandBook extends ModelBase
{
	//fields
	ModelRenderer backleft;
	ModelRenderer backright;
	ModelRenderer back;
	ModelRenderer pageleft;
	ModelRenderer pageright;

	public ModelHandBook()
	{
		textureWidth = 64;
		textureHeight = 64;

		backleft = new ModelRenderer(this, 0, 14);
		backleft.addBox(-8F, 0F, 0F, 8, 1, 12);
		backleft.setRotationPoint(-1F, 1F, -6F);
		backleft.setTextureSize(64, 64);
		backleft.mirror = true;
		setRotation(backleft, 0F, 0F, 0.0872665F);
		backright = new ModelRenderer(this, 0, 0);
		backright.addBox(0F, 0F, 0F, 8, 1, 12);
		backright.setRotationPoint(1F, 1F, -6F);
		backright.setTextureSize(64, 64);
		backright.mirror = true;
		setRotation(backright, 0F, 0F, -0.0872665F);
		back = new ModelRenderer(this, 35, 34);
		back.addBox(0F, 0F, 0F, 2, 1, 12);
		back.setRotationPoint(-1F, 0.7F, -6F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		pageleft = new ModelRenderer(this, 0, 40);
		pageleft.addBox(-7F, -1F, 1F, 8, 1, 10);
		pageleft.setRotationPoint(-1F, 1F, -6F);
		pageleft.setTextureSize(64, 64);
		pageleft.mirror = true;
		setRotation(pageleft, 0F, 0F, 0.0872665F);
		pageright = new ModelRenderer(this, 0, 28);
		pageright.addBox(-1F, -1F, 1F, 8, 1, 10);
		pageright.setRotationPoint(1F, 1F, -6F);
		pageright.setTextureSize(64, 64);
		pageright.mirror = true;
		setRotation(pageright, 0F, 0F, -0.0872665F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		backleft.renderWithRotation(f5);
		backright.renderWithRotation(f5);
		back.renderWithRotation(f5);
		pageleft.renderWithRotation(f5);
		pageright.renderWithRotation(f5);
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
