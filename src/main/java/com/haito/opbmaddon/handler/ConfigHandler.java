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
    final static String INSTANT_KILLER = "InstaKillSword";
    final static String BLOOD_DRINKER = "bloodDrinker";
    final static String SILKEN_TOUCH = "SigilEntityPickerThingy";


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
        Configs.Items.playerInstaMultiplayer = configuration.get(INSTANT_KILLER, "playerInstaMultiplayer", 10, "How many times more LP is required to kill human entity? ").getInt();

        //Blood Drinker
        Configs.Items.bloodDrinkerBaseDamage = configuration.get(BLOOD_DRINKER,"BloodDrinkerBaseDamage", 11, "Damage dealt by sword. It's calculated by adding this value to 4, for egzample if set this to 10 Damage will be 14 etc.").getInt();
        Configs.Items.bloodDrinkerPercentageHealed = configuration.get(BLOOD_DRINKER,"BloodDrinkerBaseHealed",25,"Healed Percentage").getInt();
        Configs.Items.bloodDrinkerOnPlayerAttackedHealed = configuration.get(BLOOD_DRINKER,"bloodDrinkerOnPlayerAttackedHealed", 0.5, "How many times less (or more) health you gain when attacking players.").getDouble();
        Configs.Items.bloodDrinkerLevels = configuration.get(BLOOD_DRINKER,"levelsOfUpgrades", Configs.Items.bloodDrinkerLevels , "How many kills you have to get to level up your BLOOD_DRINKER").getIntList();

        //Silken Touch stuff
        Configs.Items.hardnessMulti = configuration.get(SILKEN_TOUCH,"hardnessMulti",Configs.Items.hardnessMulti,"Multiplier based on hardness ").getDouble();
        Configs.Items.tileEntityMulti = configuration.get(SILKEN_TOUCH,"tileEntityMulti",Configs.Items.tileEntityMulti,"LP drain multiplier based on wether you're picking up a tile entity").getInt();
        Configs.Items.canBedrock = configuration.get(SILKEN_TOUCH,"canBedrock",Configs.Items.canBedrock,"Set true if you want to enable picking up bedrock blocks").getBoolean();

        if (configuration.hasChanged())
            configuration.save();
    }

    @SubscribeEvent
    public void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.modID.equalsIgnoreCase(MainRef.MOD_ID)){
            loadConfiguration();
        }
    }
}
