package com.haito.opbmaddon.client.renderer.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DebugModel extends ModelBase {
    //fields
    private ModelRenderer Layer1;
    private ModelRenderer Layer2;
    private ModelRenderer Layer3;

    public DebugModel()
    {
        textureWidth = 128;
        textureHeight = 128;

        Layer1 = new ModelRenderer(this, 0, 0);
        Layer1.addBox(-7F, 3F, -7F, 13, 2, 13);
        Layer1.setRotationPoint(0F, 0F, 0F);
        Layer1.setTextureSize(64, 32);
        Layer1.mirror = true;
        setRotation(Layer1, 0F, 0F, 0F);
        Layer2 = new ModelRenderer(this, 0, 16);
        Layer2.addBox(-5F, 0F, -5F, 9, 2, 9);
        Layer2.setRotationPoint(0F, 0F, 0F);
        Layer2.setTextureSize(64, 32);
        Layer2.mirror = true;
        setRotation(Layer2, 0F, 0F, 0F);
        Layer3 = new ModelRenderer(this, 0, 28);
        Layer3.addBox(-3F, -3F, -3F, 5, 2, 5);
        Layer3.setRotationPoint(0F, 0F, 0F);
        Layer3.setTextureSize(64, 32);
        Layer3.mirror = true;
        setRotation(Layer3, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5,entity);
        Layer1.render(f5);
        Layer2.render(f5);
        Layer3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity f6)
    {
        Layer1.rotateAngleY = Layer1.rotateAngleY != 1 ? Layer1.rotateAngleY +0.05F : 0F ;
        Layer2.rotateAngleY = Layer2.rotateAngleY != 0 ? Layer2.rotateAngleY - 0.03F : 1F ;
        Layer3.rotateAngleY = Layer3.rotateAngleY != 1 ? Layer3.rotateAngleY +0.01F : 0F ;
    }
}
