package com.haito.opbmaddon.client.renderer.tileEntity;

import com.haito.opbmaddon.refference.Models;
import com.haito.opbmaddon.refference.Textures;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class UnholyPylonRenderer extends TileEntitySpecialRenderer {

    IModelCustom bottomBaseModel;
    IModelCustom pylonBaseModel;
    private ResourceLocation pyramidBaseTexture;
    private ResourceLocation pylonBaseTexture;

    public UnholyPylonRenderer(){
        this.bottomBaseModel = AdvancedModelLoader.loadModel(Models.ALUDEL);
        this.pylonBaseModel = AdvancedModelLoader.loadModel(Models.PYLON);

        this.pyramidBaseTexture = Textures.CustomModel.PIRAMID;
        this.pylonBaseTexture = Textures.CustomModel.PYLON_BASIC;
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!
        GL11.glPushMatrix();
        GL11.glRotatef(0F, 0F, 0F, 1.0F);
        GL11.glScaled(0.25F,0.25F,0.25F);

        float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
        GL11.glRotatef(-1*rotationAngle,0F,1.0F,0F);

        //A reference to your Model file. Again, very important.
        //Tell it to stop rendering for both the PushMatrix's
        bindTexture(pyramidBaseTexture);
        bottomBaseModel.renderAll();
        bindTexture(pyramidBaseTexture);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glScalef(0.20F,0.20F,0.20F);
        GL11.glTranslatef(0F,7.0F + (float) Math.sin((double) (System.currentTimeMillis() & 0x3FFFL) / 1000),0F);
        bindTexture(pylonBaseTexture);
        pylonBaseModel.renderAll();
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

    //Set the lighting stuff, so it changes it's brightness properly.
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.instance;
        //float brightness = block.getBlockBrightness(world, i, j, k);
        //As of MC 1.7+ block.getBlockBrightness() has become block.getLightValue():
        float brightness = block.getLightValue(world, i, j, k);
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
    }

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }
}
