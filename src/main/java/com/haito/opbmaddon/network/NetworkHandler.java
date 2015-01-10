package com.haito.opbmaddon.network;

import com.haito.opbmaddon.network.message.MessageParticleEvent;
import com.haito.opbmaddon.network.message.MessageSoundEvent;
import com.haito.opbmaddon.refference.MainRef;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MainRef.modId.toLowerCase());

    public static void init(){
        INSTANCE.registerMessage(MessageSoundEvent.class,MessageSoundEvent.class,0, Side.CLIENT);
        INSTANCE.registerMessage(MessageParticleEvent.class,MessageParticleEvent.class,1,Side.CLIENT);
    }
}
