package com.haito.opbmaddon.init;

import com.haito.opbmaddon.handler.ConfigHandler;
import com.haito.opbmaddon.items.BloodInfusedGem;
import com.haito.opbmaddon.items.BloodInfusedIngot;
import com.haito.opbmaddon.items.BloodInfusedPearl;
import com.haito.opbmaddon.items.DebugItem;
import com.haito.opbmaddon.items.baubles.BaubleBloodLetter;
import com.haito.opbmaddon.items.baubles.BaubleRingAwesomnes;
import com.haito.opbmaddon.items.sigils.*;
import com.haito.opbmaddon.items.weapon.ItemBloodDrinker;
import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.items.model.OPBMWeapon;
import com.haito.opbmaddon.refference.Configs;
import com.haito.opbmaddon.refference.MainRef;
import com.haito.opbmaddon.refference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(MainRef.modId)
public class ModItems {

    //Weapons
    public static final OPBMWeapon bloodDrinker = new ItemBloodDrinker();

    //Sigils
    public static final OPBMItem sigilInvisibility = new ItemSigilInvisibility();
    public static final OPBMItem sigilHomesoil = new ItemSigilHomeSoil();
    public static final OPBMItem sigilSilkenHand = new ItemSigilSilk();
    public static final OPBMItem sigilWeather = new ItemSigilWeather();
    public static final OPBMItem sigilTreachery = new ItemSigilTreachery();
    public static final OPBMItem debugTool = new DebugItem();

    //Baubles
    public static final OPBMItem ringAwesomness =  new BaubleRingAwesomnes();
    public static final OPBMItem neckBloodLetter = new BaubleBloodLetter();

    //Crafting supplies
    public static final OPBMItem bloodIngot = new BloodInfusedIngot();
    public static final OPBMItem bloodGem = new BloodInfusedGem();
    public static final OPBMItem bloodPearl = new BloodInfusedPearl();


    public static void init() {
        //Weapons
        GameRegistry.registerItem(bloodDrinker, Names.Weapons.BloodDrinker);
        //Sigils
        if (Configs.Items.isInvisibleEnabled)
            GameRegistry.registerItem(sigilInvisibility, Names.Sigils.SigilInvisible);
        GameRegistry.registerItem(sigilHomesoil,Names.Sigils.SigilHomesoil);
        GameRegistry.registerItem(sigilSilkenHand,Names.Sigils.SigilOfSilkenHand);
        GameRegistry.registerItem(sigilWeather,Names.Sigils.SigilWeather);
        GameRegistry.registerItem(sigilTreachery,Names.Sigils.SigilOfTreachery);
        GameRegistry.registerItem(debugTool,Names.EverythingChecker);
        //Baubless
        GameRegistry.registerItem(ringAwesomness,Names.Baubles.BaubleRingAwesomness);
        GameRegistry.registerItem(neckBloodLetter,Names.Baubles.BaubleNeckLetter);
        //Crafting suplies
        GameRegistry.registerItem(bloodIngot,Names.BloodInfusedMetal);
        GameRegistry.registerItem(bloodGem,Names.BloodInfusedGem);
        GameRegistry.registerItem(bloodPearl,Names.BloodInfusedPearl);
    }
}
