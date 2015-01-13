package com.haito.opbmaddon.handler;

import com.haito.opbmaddon.refference.Configs;
import com.haito.opbmaddon.refference.MainRef;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration configuration;

    //Items category names
    final static String insantKiller = "InstaKillSword";
    final static String bloodDrinker = "BloodDrinker";
    final static String silkenTouch = "SigilEntityPickerThingy";


    public static void init(File target) {
        if(configuration == null){
            configuration = new Configuration(target);
            loadConfiguration();
        }
    }



    public static void loadConfiguration(){
        //Enabling totally OP Stuff
        Configs.Items.isInvisibleEnabled = configuration.get(Configuration.CATEGORY_GENERAL,"InvisibleEnabled",true,"Do i enable Sigil of Invisibility for you?").getBoolean();

        //Configurations for insta kill sword
        Configs.Items.playerInstaMultiplayer = configuration.get(insantKiller, "playerInstaMultiplayer", 10, "How many times more LP is required to kill human entity? ").getInt();

        //Blood Drinker
        Configs.Items.bloodDrinkerBaseDamage = configuration.get(bloodDrinker,"BloodDrinkerBaseDamage", 11, "Damage dealt by sword. It's calculated by adding this value to 4, for egzample if set this to 10 Damage will be 14 etc.").getInt();
        Configs.Items.bloodDrinkerPercentageHealed = configuration.get(bloodDrinker,"BloodDrinkerBaseHealed",25,"Healed Percentage").getInt();
        Configs.Items.bloodDrinkerOnPlayerAttackedHealed = configuration.get(bloodDrinker,"bloodDrinkerOnPlayerAttackedHealed", 0.5, "How many times less (or more) health you gain when attacking players.").getDouble();
        Configs.Items.bloodDrinkerLevels = configuration.get(bloodDrinker,"levelsOfUpgrades", Configs.Items.bloodDrinkerLevels , "How many kills you have to get to level up your bloodDrinker").getIntList();

        //Silken Touch stuff
        Configs.Items.hardnessMulti = configuration.get(silkenTouch,"hardnessMulti",Configs.Items.hardnessMulti,"Multiplier based on hardness ").getDouble();
        Configs.Items.tileEntityMulti = configuration.get(silkenTouch,"tileEntityMulti",Configs.Items.tileEntityMulti,"LP drain multiplier based on wether you're picking up a tile entity").getInt();
        Configs.Items.canBedrock = configuration.get(silkenTouch,"canBedrock",Configs.Items.canBedrock,"Set true if you want to enable picking up bedrock blocks").getBoolean();

        if (configuration.hasChanged())
            configuration.save();
    }

    @SubscribeEvent
    public void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.modID.equalsIgnoreCase(MainRef.modId)){
            loadConfiguration();
        }
    }
}
