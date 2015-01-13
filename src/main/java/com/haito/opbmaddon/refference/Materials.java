package com.haito.opbmaddon.refference;

import com.haito.opbmaddon.handler.ConfigHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;


public class Materials {
    public static Item.ToolMaterial bloodInfusedMetal = EnumHelper.addToolMaterial("BloodInfusedMetal",5,3000, 11.0f,(float) Configs.Items.bloodDrinkerBaseDamage,100);
}
