package com.haito.opbmaddon.block;

import codechicken.multipart.TEdgePart;
import com.haito.opbmaddon.block.model.OPBMBlock;
import com.haito.opbmaddon.block.model.OPBMBlockContainer;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.tileEntity.TEPotionStation;
import com.haito.opbmaddon.utility.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sun.rmi.runtime.Log;

import java.util.List;


public class BlockPotionStation extends OPBMBlockContainer {
    public BlockPotionStation(){
        super();
        this.setBlockName(Names.Blocks.PotionStation);
    }

    //@Override


    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if(!world.isRemote && entity instanceof EntityPlayer){
            EntityPlayer entityPlayer = (EntityPlayer) entity;
            LogHelper.info("tick");
            try{
                TEPotionStation tePotionStation = (TEPotionStation) world.getTileEntity(x,y,z);
                LogHelper.info(tePotionStation + " teEntity " + tePotionStation.potion);
                ItemPotion effect = (ItemPotion) tePotionStation.potion.getItem();
                LogHelper.info(effect + " smth " + effect.getEffects(tePotionStation.potion).get(0));
                PotionEffect potionEffect = (PotionEffect) effect.getEffects(tePotionStation.potion).get(0);
                LogHelper.info(potionEffect + " effect");
                entityPlayer.addPotionEffect(new PotionEffect(potionEffect.getPotionID(),120));
                LogHelper.info(Potion.confusion.getId());
            } catch (NullPointerException e){
                LogHelper.off(e);
            }
        }
    }
    @Override
    public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer entityPlayer, int side, float idk1, float idk2, float idk3)
    {
        LogHelper.info("I assume that x:" + blockX + " y:" + blockY + " z:"+blockZ + " clicked on side:" + side);
        LogHelper.info(idk1 + " " + idk2 + " " + idk3);
        ItemStack itemStack = entityPlayer.getHeldItem();
        if(itemStack.getItem() instanceof ItemPotion){
            LogHelper.info(((ItemPotion) itemStack.getItem()).getEffects(itemStack).get(0));
            ((TEPotionStation) world.getTileEntity(blockX,blockY,blockZ)).potion = itemStack;
            --itemStack.stackSize;
        }
        return false;
    }
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEPotionStation();
    }
}
