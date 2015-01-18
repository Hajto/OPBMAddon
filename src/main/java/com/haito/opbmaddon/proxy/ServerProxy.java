package com.haito.opbmaddon.proxy;

/**
 * Created by Hajto-Lenovo on 2015-01-03.
 */
public class ServerProxy extends CommonProxy {

    public void initRenderStuff(){

    }

    @Override
    public void playSoundEffect(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
    {
        // NOOP
    }

    @Override
    public void showParticleEffect(String particleName, float xCoord, float yCoord, float zCoord, float xMotion, float yMotion, float zMotion) {
        //NOOP Musiałem tu coś wpisać bo Java
    }
}
