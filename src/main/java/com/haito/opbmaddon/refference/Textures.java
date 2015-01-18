package com.haito.opbmaddon.refference;


import com.haito.opbmaddon.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures {
    public static final String ResourcePrefix = MainRef.modId.toLowerCase() + ":";

    public static class CustomModel{
        public static final String subfolder = "textures/model/";
        public static ResourceLocation debug = ResourceLocationHelper.getResLocation(subfolder+"debug.png");
    }
}
