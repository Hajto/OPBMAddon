package com.haito.opbmaddon.proxy;


import com.haito.opbmaddon.client.particles.ClientParticleHandler;
import com.haito.opbmaddon.client.particles.ClientSoundHelper;
import com.haito.opbmaddon.client.renderer.tileEntity.PotionStationRenderer;
import com.haito.opbmaddon.client.renderer.tileEntity.testRenderTile;
import com.haito.opbmaddon.tileEntity.TEPotionStation;
import com.haito.opbmaddon.tileEntity.debugTileEntity;
import com.haito.opbmaddon.utility.LogHelper;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public void initRenderStuff(){
        ClientRegistry.bindTileEntitySpecialRenderer(debugTileEntity.class, new testRenderTile());
        ClientRegistry.bindTileEntitySpecialRenderer(TEPotionStation.class, new PotionStationRenderer());
    }

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
