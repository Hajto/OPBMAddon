package com.haito.opbmaddon.utility;

import com.haito.opbmaddon.network.NetworkHandler;
import com.haito.opbmaddon.network.message.MessageSoundEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Haito on 2015-01-10.
 */
public class CommonSoundHelper {
    public static void playSoundAt(EntityPlayer entityPlayer, String soundName, float volume, float pitch, double range)
    {
        NetworkHandler.INSTANCE.sendToAllAround(new MessageSoundEvent(entityPlayer, soundName, volume, pitch), new NetworkRegistry.TargetPoint(entityPlayer.worldObj.provider.dimensionId, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, range));
        LogHelper.info("Common Sound! Should happen first");
    }
}
