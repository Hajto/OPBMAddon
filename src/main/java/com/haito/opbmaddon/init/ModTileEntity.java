package com.haito.opbmaddon.init;

import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.tileEntity.TEPotionStation;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntity {

    public static void init(){
        GameRegistry.registerTileEntity(TEPotionStation.class, Names.Containers.TE_POTION_STATION);
    }
}
