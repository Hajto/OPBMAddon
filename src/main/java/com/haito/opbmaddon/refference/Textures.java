package com.haito.opbmaddon.refference;


import com.haito.opbmaddon.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures {
    public static final String ResourcePrefix = MainRef.MOD_ID.toLowerCase() + ":";

    public static class CustomModel{
        public static final String SUBFOLDER = "textures/model/";
        public static final ResourceLocation DEBUG_TEXTURE = ResourceLocationHelper.getResLocation(SUBFOLDER +"DEBUG_TEXTURE.png");
        public static final ResourceLocation PIRAMID = ResourceLocationHelper.getResLocation(SUBFOLDER + "Pyramid.png");
    }
}
