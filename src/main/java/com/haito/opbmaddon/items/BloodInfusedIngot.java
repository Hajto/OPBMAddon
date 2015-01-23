package com.haito.opbmaddon.items;

import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.refference.Names;

/**
 * Created by Haito on 2015-01-10.
 */
public class BloodInfusedIngot extends OPBMItem{
    public BloodInfusedIngot(){
        super();
        this.setUnlocalizedName(Names.BLOOD_INFUSED_METAL);
        this.setMaxStackSize(64);
    }
}
