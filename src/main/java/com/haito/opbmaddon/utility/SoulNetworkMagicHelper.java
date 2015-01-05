package com.haito.opbmaddon.utility;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

public class SoulNetworkMagicHelper {
    public static void appendToSoulNetwork(String player, int amount){
        SoulNetworkHandler.setCurrentEssence(player, SoulNetworkHandler.getCurrentEssence(player) + amount);
    }
}
