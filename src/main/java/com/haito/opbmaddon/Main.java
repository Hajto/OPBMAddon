package com.haito.opbmaddon;

import com.haito.opbmaddon.handler.ConfigHandler;
import com.haito.opbmaddon.handler.ItemActivatedHandler;
import com.haito.opbmaddon.init.Items;
import com.haito.opbmaddon.proxy.IProxy;
import com.haito.opbmaddon.refference.MainRef;
import com.haito.opbmaddon.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.common.MinecraftForge;


@Mod(modid = MainRef.modId, name = MainRef.modName, version = MainRef.version, guiFactory = MainRef.GuiFactory)
public class Main {

    @Mod.Instance(MainRef.modId)
    public static Main mainInstance;

    @SidedProxy(serverSide = MainRef.ServerProxy,clientSide = MainRef.ClientProxy,modId = MainRef.modId)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        ConfigHandler.init(e.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());


        Items.init();
        //MinecraftForge.EVENT_BUS.register(Items.sigilHomesoil);
        MinecraftForge.EVENT_BUS.register(new ItemActivatedHandler());
        LogHelper.info("Pre-Initialization has succeded! Yep, no joking it's almost ready :>");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        LogHelper.info("Mod has loaded correctly... Probably.");

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){

    }
}
