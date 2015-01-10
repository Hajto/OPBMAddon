package com.haito.opbmaddon.utility;

import com.haito.opbmaddon.refference.Particles;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientSoundHelper {
    @SideOnly(Side.CLIENT)
    public static void playSoundEffect(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch)
    {
        //It's kinda working, everything executes just right, but the final execution method is.... not working
        FMLClientHandler.instance().getWorldClient().playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, soundName, volume, pitch);
        LogHelper.info("ClientSoundHlper should 5 playing "+ soundName + " on " + xCoord + " " + yCoord + " " + zCoord);
    }
}
