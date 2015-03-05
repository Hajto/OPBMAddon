package com.haito.opbmaddon.block;

import com.haito.opbmaddon.block.model.OPBMBlockContainer;
import com.haito.opbmaddon.tileEntity.TEUnholyPressenceAltar;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockUnholyPressenceAltar extends OPBMBlockContainer {
    public BlockUnholyPressenceAltar(){
        super(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEUnholyPressenceAltar();
    }
}
