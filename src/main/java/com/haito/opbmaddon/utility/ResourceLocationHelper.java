package com.haito.opbmaddon.utility;

import com.haito.opbmaddon.refference.MainRef;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {
    public static ResourceLocation getResLocation(String path){
        LogHelper.info(path);
        return new ResourceLocation(MainRef.MOD_ID.toLowerCase(),path);
    }
}
