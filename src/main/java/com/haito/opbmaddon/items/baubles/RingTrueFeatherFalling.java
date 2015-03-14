package com.haito.opbmaddon.items.baubles;

import baubles.api.BaubleType;
import com.haito.opbmaddon.items.model.OPBMBauble;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.LogHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RingTrueFeatherFalling extends OPBMBauble{
    public RingTrueFeatherFalling(){
        super();
        this.setUnlocalizedName(Names.Baubles.RING_FEATHER_FALL);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        super.onWornTick(itemStack, entityLivingBase);

        if(entityLivingBase instanceof EntityPlayer){
            EntityPlayer entityPlayer = (EntityPlayer) entityLivingBase;

            if(!entityPlayer.getEntityWorld().isRemote && entityPlayer.motionY < -0.50 && entityPlayer.motionY > - 10){

                entityPlayer.motionY = -0.50;
                entityPlayer.fallDistance = 0;

                LogHelper.info("Falling with speed" + entityPlayer.motionY);
                LogHelper.info("Fall distance "+ entityPlayer.fallDistance);
            }
        }
    }
}
