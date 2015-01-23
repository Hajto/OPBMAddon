package com.haito.opbmaddon.init;

import com.haito.opbmaddon.items.BloodInfusedGem;
import com.haito.opbmaddon.items.BloodInfusedIngot;
import com.haito.opbmaddon.items.BloodInfusedPearl;
import com.haito.opbmaddon.items.DebugItem;
import com.haito.opbmaddon.items.baubles.BloodLetter;
import com.haito.opbmaddon.items.baubles.NeckFortitude;
import com.haito.opbmaddon.items.baubles.RingAwesomnes;
import com.haito.opbmaddon.items.sigils.*;
import com.haito.opbmaddon.items.weapon.ItemBloodDrinker;
import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.items.model.OPBMWeapon;
import com.haito.opbmaddon.refference.Configs;
import com.haito.opbmaddon.refference.MainRef;
import com.haito.opbmaddon.refference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(MainRef.MOD_ID)
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
    public static final OPBMItem ringAwesomness =  new RingAwesomnes();
    public static final OPBMItem neckFortitude = new NeckFortitude();
    public static final OPBMItem neckBloodLetter = new BloodLetter();

    //Crafting supplies
    public static final OPBMItem bloodIngot = new BloodInfusedIngot();
    public static final OPBMItem bloodGem = new BloodInfusedGem();
    public static final OPBMItem bloodPearl = new BloodInfusedPearl();


    public static void init() {
        //Weapons
        GameRegistry.registerItem(bloodDrinker, Names.Weapons.BLOOD_DRINKER);
        //Sigils
        if (Configs.Items.isInvisibleEnabled)
            GameRegistry.registerItem(sigilInvisibility, Names.Sigils.INVISIBILITY);
        GameRegistry.registerItem(sigilHomesoil,Names.Sigils.HOMELSOIL);
        GameRegistry.registerItem(sigilSilkenHand,Names.Sigils.SILKEN_HAND);
        GameRegistry.registerItem(sigilWeather,Names.Sigils.WEATHER);
        GameRegistry.registerItem(sigilTreachery,Names.Sigils.TREACHERY);
        GameRegistry.registerItem(debugTool,Names.EVERYTHING_CHECKER);
        //Baubless
        GameRegistry.registerItem(ringAwesomness,Names.Baubles.RING_AWESOMNESS);
        GameRegistry.registerItem(neckBloodLetter,Names.Baubles.NECK_PACK);
        GameRegistry.registerItem(neckFortitude,Names.Baubles.NECK_FORTITUDE);
        //Crafting suplies
        GameRegistry.registerItem(bloodIngot,Names.BLOOD_INFUSED_METAL);
        GameRegistry.registerItem(bloodGem,Names.BLOOD_INFUSED_GEM);
        GameRegistry.registerItem(bloodPearl,Names.BLOOD_INFUSED_PEARL);
    }
}
