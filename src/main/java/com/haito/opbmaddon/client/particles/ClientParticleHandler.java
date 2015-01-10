package com.haito.opbmaddon.client.particles;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * Created by Haito on 2015-01-10.
 */
public class ClientParticleHandler {
    public static void showVanillaParticle(String particleName, float xCoord, float yCoord, float zCoord, float xMotion, float yMotion, float zMotion){
        FMLClientHandler.instance().getWorldClient().spawnParticle(particleName,xCoord,yCoord,zCoord,xMotion,yMotion,zMotion);
    }
}
