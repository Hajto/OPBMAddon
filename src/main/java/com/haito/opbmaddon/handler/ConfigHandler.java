package com.haito.opbmaddon.handler;

import com.haito.opbmaddon.items.ItemBloodDrinker;
import com.haito.opbmaddon.refference.MainRef;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration configuration;

    //Category names
    final static String insantKiller = "InstaKillSword";
    final static String bloodDrinker = "BloodDrinker";

    //Enabling totally OP items
    public static boolean isInvisibleEnabled;

    //Instant kill sword configs
    public static int playerInstaMultiplayer = 10;

    //Blood Drinker Config
    public static int bloodDrinkerPercentageHealed = 25;
    public static int bloodDrinkerBaseDamage = 3;
    public static double bloodDrinkerOnPlayerAttackedHealed = 0.5;
    public static int[] bloodDrinkerLevels = {500,1000,2500,5000,10000};

    public static void init(File target) {
        if(configuration == null){
            configuration = new Configuration(target);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.modID.equalsIgnoreCase(MainRef.modId)){
            loadConfiguration();
        }
    }

    public static void loadConfiguration(){
        //Enabling totally OP Stuff
        isInvisibleEnabled = configuration.get(Configuration.CATEGORY_GENERAL,"InvisibleEnabled",true,"Do i enable Sigil of Invisibility for you?").getBoolean();

        //Configurations for insta kill sword
        playerInstaMultiplayer = configuration.get(insantKiller, "playerInstaMultiplayer", 10, "How many times more LP is required to kill human entity? ").getInt();

        //Blood Drinker
        bloodDrinkerBaseDamage = configuration.get(bloodDrinker,"BloodDrinkerBaseDamage", 11, "Damage dealt by sword. It's calculated by adding this value to 4, for egzample if set this to 10 Damage will be 14 etc.").getInt();
        bloodDrinkerPercentageHealed = configuration.get(bloodDrinker,"BloodDrinkerBaseHealed",25,"Healed Percentage").getInt();
        bloodDrinkerOnPlayerAttackedHealed = configuration.get(bloodDrinker,"bloodDrinkerOnPlayerAttackedHealed", 0.5, "How many times less (or more) health you gain when attacking players.").getDouble();
        bloodDrinkerLevels = configuration.get(bloodDrinker,"levelsOfUpgrades", bloodDrinkerLevels , "How many kills you have to get to level up your bloodDrinker").getIntList();

        if (configuration.hasChanged())
            configuration.save();
    }
}
