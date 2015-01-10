package com.haito.opbmaddon.proxy;


import com.haito.opbmaddon.client.particles.ClientParticleHandler;
import com.haito.opbmaddon.client.particles.ClientSoundHelper;
import com.haito.opbmaddon.utility.LogHelper;

public class ClientProxy extends CommonProxy {

    @Override
    public void playSoundEffect(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
    {
        ClientSoundHelper.playSoundEffect(soundName, xCoord, yCoord, zCoord, volume, pitch);
        LogHelper.info("Client Proxy should happen 4");
    }

    @Override
    public void showParticleEffect(String particleName, float xCoord, float yCoord, float zCoord, float xMotion, float yMotion, float zMotion) {
        ClientParticleHandler.showVanillaParticle(particleName,xCoord,yCoord,zCoord,xMotion,yMotion,zMotion);
    }

}
