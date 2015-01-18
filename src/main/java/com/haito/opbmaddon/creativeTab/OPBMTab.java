package com.haito.opbmaddon.creativeTab;

import com.haito.opbmaddon.init.ModItems;
import com.haito.opbmaddon.refference.MainRef;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class OPBMTab {
    public static final CreativeTabs OPBMAddonTab = new CreativeTabs(MainRef.modId) {
        @Override
        public Item getTabIconItem() {
            return ModItems.bloodDrinker;
        }
    };
}
