package com.haito.opbmaddon.init;

import com.haito.opbmaddon.items.BloodInfusedGem;
import com.haito.opbmaddon.items.BloodInfusedIngot;
import com.haito.opbmaddon.items.BloodInfusedPearl;
import com.haito.opbmaddon.items.DebugItem;
import com.haito.opbmaddon.items.baubles.BloodLetter;
import com.haito.opbmaddon.items.baubles.NeckFortitude;
import com.haito.opbmaddon.items.baubles.RingAwesomnes;
import com.haito.opbmaddon.items.baubles.RingTrueFeatherFalling;
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
    public static final OPBMWeapon BLOOD_DRINKER = new ItemBloodDrinker();

    //Sigils
    public static final OPBMItem SIGIL_INVISIBILITY = new ItemSigilInvisibility();
    public static final OPBMItem SIGIL_HOMESOIL = new ItemSigilHomesoil();
    public static final OPBMItem SIGIL_SILKEN_HAND = new ItemSigilSilk();
    public static final OPBMItem SIGIL_Weather = new ItemSigilWeather();
    public static final OPBMItem SIGIL_TREACHERY = new ItemSigilTreachery();
    public static final OPBMItem debugTool = new DebugItem();

    //Baubles
    public static final OPBMItem RING_AWESOMNESS =  new RingAwesomnes();
    public static final OPBMItem NECK_FORTITUDE = new NeckFortitude();
    public static final OPBMItem NECK_BLOOD_LETTER = new BloodLetter();
    public static final OPBMItem RING_FEATHER_FALLING = new RingTrueFeatherFalling();

    //Crafting supplies
    public static final OPBMItem BLOOD_INGOT = new BloodInfusedIngot();
    public static final OPBMItem BLOOD_GEM = new BloodInfusedGem();
    public static final OPBMItem BLOOD_PEARL = new BloodInfusedPearl();


    public static void init() {
        //Weapons
        GameRegistry.registerItem(BLOOD_DRINKER, Names.Weapons.BLOOD_DRINKER);
        //Sigils
        if (Configs.Items.isInvisibleEnabled)
            GameRegistry.registerItem(SIGIL_INVISIBILITY, Names.Sigils.INVISIBILITY);
        GameRegistry.registerItem(SIGIL_HOMESOIL,Names.Sigils.HOMELSOIL);
        GameRegistry.registerItem(SIGIL_SILKEN_HAND,Names.Sigils.SILKEN_HAND);
        GameRegistry.registerItem(SIGIL_Weather,Names.Sigils.WEATHER);
        GameRegistry.registerItem(SIGIL_TREACHERY,Names.Sigils.TREACHERY);
        GameRegistry.registerItem(debugTool,Names.EVERYTHING_CHECKER);
        //Baubless
        GameRegistry.registerItem(RING_AWESOMNESS,Names.Baubles.RING_AWESOMNESS);
        GameRegistry.registerItem(RING_FEATHER_FALLING,Names.Baubles.RING_FEATHER_FALL);
        GameRegistry.registerItem(NECK_BLOOD_LETTER,Names.Baubles.NECK_PACK);
        GameRegistry.registerItem(NECK_FORTITUDE,Names.Baubles.NECK_FORTITUDE);
        //Crafting suplies
        GameRegistry.registerItem(BLOOD_INGOT,Names.BLOOD_INFUSED_METAL);
        GameRegistry.registerItem(BLOOD_GEM,Names.BLOOD_INFUSED_GEM);
        GameRegistry.registerItem(BLOOD_PEARL,Names.BLOOD_INFUSED_PEARL);
    }
}
