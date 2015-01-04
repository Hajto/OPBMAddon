package com.haito.opbmaddon.init;

import com.haito.opbmaddon.handler.ConfigHandler;
import com.haito.opbmaddon.items.ItemBloodDrinker;
import com.haito.opbmaddon.items.ItemSigilInvisibility;
import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.items.model.OPBMWeapon;
import com.haito.opbmaddon.refference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items {

    //Weapons
    public static final OPBMWeapon bloodDrinker = new ItemBloodDrinker();

    //Sigils
    public static final OPBMItem sigilInvisibility = new ItemSigilInvisibility();

    public static void init() {
        //Weapons
        GameRegistry.registerItem(bloodDrinker, Names.Weapons.BloodDrinker);
        //Sigils
        if (ConfigHandler.isInvisibleEnabled)
            GameRegistry.registerItem(sigilInvisibility, Names.Sigils.SigilInvisible);
    }
}
