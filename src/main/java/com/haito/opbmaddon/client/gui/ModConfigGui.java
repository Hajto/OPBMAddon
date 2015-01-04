package com.haito.opbmaddon.client.gui;

import com.haito.opbmaddon.handler.ConfigHandler;
import com.haito.opbmaddon.refference.MainRef;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModConfigGui extends GuiConfig {
    public ModConfigGui(GuiScreen guiScreen) {
        super(guiScreen,
                new ConfigElement(ConfigHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                MainRef.modId,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
    }
}
