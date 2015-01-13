package com.haito.opbmaddon.refference;

public class Configs {
    public static class Items{
        //Enabling totally OP items
        public static boolean isInvisibleEnabled;

        //Instant kill sword configs
        public static int playerInstaMultiplayer = 10;

        //Blood Drinker Config
        public static int bloodDrinkerPercentageHealed = 25;
        public static int bloodDrinkerBaseDamage = 3;
        public static double bloodDrinkerOnPlayerAttackedHealed = 0.5;
        public static int[] bloodDrinkerLevels = {100,500,1000,2500,5000,10000};

        //Silken hand sigil thingy
        public static double hardnessMulti = 1.5;
        public static int tileEntityMulti = 2;
        public static boolean canBedrock = true;
    }
}
