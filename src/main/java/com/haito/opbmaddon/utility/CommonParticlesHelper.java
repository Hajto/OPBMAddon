package com.haito.opbmaddon.utility;

import com.haito.opbmaddon.network.NetworkHandler;
import com.haito.opbmaddon.network.message.MessageParticleEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.EntityLivingBase;


public class CommonParticlesHelper {
    public static void showParticleAt(String particleName, EntityLivingBase entity, float xMotion, float yMotion, float zMotion){
        NetworkHandler.INSTANCE.sendToAllAround(new MessageParticleEvent(particleName,(float) entity.posX, (float)entity.posY, (float)entity.posZ,xMotion,yMotion,zMotion), new NetworkRegistry.TargetPoint(entity.worldObj.provider.dimensionId,entity.posX,entity.posY,entity.posZ,100.0D));
    }
}
