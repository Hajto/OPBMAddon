package com.haito.opbmaddon.init;

import com.haito.opbmaddon.block.BlockPotionStation;
import com.haito.opbmaddon.block.debugBlock;
import com.haito.opbmaddon.block.model.OPBMBlockContainer;
import com.haito.opbmaddon.refference.MainRef;
import com.haito.opbmaddon.refference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;


@GameRegistry.ObjectHolder(MainRef.MOD_ID)
public class ModBlocks {

    //Ordinary Blocks
    public static final OPBMBlockContainer potionStation = new BlockPotionStation();
    public static final Block debug = new debugBlock();

    public static void init(){
        GameRegistry.registerBlock(potionStation, Names.Blocks.POTION_STATION);
        GameRegistry.registerBlock(debug, "debugBlock");
    }
}
