package com.haito.opbmaddon.proxy;


public interface IProxy {
    public abstract void playSoundEffect(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch);
    public abstract void showParticleEffect(String particleName, float xCoord, float yCoord, float zCoord, float xMotion,float yMotion,float zMotion);
    public abstract void initRenderStuff();
}
