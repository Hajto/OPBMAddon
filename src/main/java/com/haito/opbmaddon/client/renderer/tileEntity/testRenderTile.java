package com.haito.opbmaddon.client.renderer.tileEntity;

import com.haito.opbmaddon.init.ModItems;
import com.haito.opbmaddon.refference.Models;
import com.haito.opbmaddon.refference.Textures;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class testRenderTile extends TileEntitySpecialRenderer {
    //private DebugModel model;
    IModelCustom model;
    private RenderItem customRenderItem;
    private ResourceLocation textures;
    public testRenderTile(){
        customRenderItem = new RenderItem()
        {
            @Override
            public boolean shouldBob()
            {
                return false;
            }
        };
        customRenderItem.setRenderManager(RenderManager.instance);
        //this.model = new DebugModel();
        this.model = AdvancedModelLoader.loadModel(Models.ALUDEL);
        textures = Textures.CustomModel.PIRAMID;
    }

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        //Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!
        GL11.glPushMatrix();
        GL11.glRotatef(0F, 0F, 45F, 1.0F);
        GL11.glScaled(0.25F,0.25F,0.25F);
        //A reference to your Model file. Again, very important.
        //this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        //Tell it to stop rendering for both the PushMatrix's
        bindTexture(textures);
        model.renderAll();
        bindTexture(textures);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        float scaleFactor = 1F;
        float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
        EntityItem ghostEntityItem = new EntityItem(te.getWorldObj());
        ghostEntityItem.hoverStart = 0.0F;
        ghostEntityItem.setEntityItemStack(new ItemStack(ModItems.BLOOD_DRINKER));
        float displacement = 0.2F;

        if (ghostEntityItem.getEntityItem().getItem() instanceof ItemBlock)
        {
            GL11.glTranslatef((float) x  + 0.5F, (float) y + displacement + 1F, (float) z + 0.5F);
        } else
        {
            GL11.glTranslatef((float) x  + 0.5F, (float) y + displacement + 1F, (float) z + 0.5F) ;
        }
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
        GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);
        customRenderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
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
}
