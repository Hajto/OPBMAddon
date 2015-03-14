package com.haito.opbmaddon.proxy;

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
