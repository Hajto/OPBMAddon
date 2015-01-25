package com.haito.opbmaddon.client.renderer.tileEntity;

import com.haito.opbmaddon.init.ModItems;
import com.haito.opbmaddon.refference.Models;
import com.haito.opbmaddon.refference.Textures;
import com.haito.opbmaddon.tileEntity.TEPotionStation;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;


public class PotionStationRenderer extends TileEntitySpecialRenderer {

    private IModelCustom model;
    private ResourceLocation textures;
    private RenderItem item;

    public PotionStationRenderer(){
        this.model= AdvancedModelLoader.loadModel(Models.POTION_STATION);
        this.textures = Textures.CustomModel.POTION_STATION;

        item = new RenderItem()
        {
            @Override
            public boolean shouldBob()
            {
                return false;
            }
        };
        item.setRenderManager(RenderManager.instance);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float scale) {
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) posX + 0.5F, (float) posY + 0.5F, (float) posZ + 0.5F);
        //Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!
        GL11.glPushMatrix();
        GL11.glRotatef(0F, 0F, 45F, 1.0F);
        GL11.glScaled(0.17F,0.17F,0.17F);
        //A reference to your Model file. Again, very important.
        //this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        //Tell it to stop rendering for both the PushMatrix's
        bindTexture(textures);
        model.renderAll();
        bindTexture(textures);
        GL11.glPopMatrix();
        GL11.glPopMatrix();


        if(tileEntity instanceof TEPotionStation && ((TEPotionStation) tileEntity).potion != null) {
            GL11.glPushMatrix();
            float scaleFactor = 1F;
            float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
            EntityItem ghostEntityItem = new EntityItem(tileEntity.getWorldObj());
            ghostEntityItem.hoverStart = 0.0F;
            ghostEntityItem.setEntityItemStack(((TEPotionStation) tileEntity).potion);
            float displacement = 0.2F;

            if (ghostEntityItem.getEntityItem().getItem() instanceof ItemBlock) {
                GL11.glTranslatef((float) posX + 0.5F, (float) posY + displacement + 0.25F , (float) posZ + 0.5F);
            } else {
                GL11.glTranslatef((float) posX + 0.5F, (float) posY + displacement + 0.25F, (float) posZ + 0.5F);
            }
            GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
            GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);
            item.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
            GL11.glPopMatrix();
        }
    }
}
