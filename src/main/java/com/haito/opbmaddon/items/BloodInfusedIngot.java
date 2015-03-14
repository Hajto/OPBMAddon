package com.haito.opbmaddon.items;

import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.refference.Names;

public class BloodInfusedIngot extends OPBMItem{
    public BloodInfusedIngot(){
        super();
        this.setUnlocalizedName(Names.BLOOD_INFUSED_METAL);
        this.setMaxStackSize(64);
    }
}
