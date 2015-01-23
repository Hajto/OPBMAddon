package com.haito.opbmaddon.block;

import com.haito.opbmaddon.creativeTab.OPBMTab;
import com.haito.opbmaddon.tileEntity.debugTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Haito on 2015-01-17.
 */
public class debugBlock extends BlockContainer {
    //Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public debugBlock() {
        super(Material.iron);
        this.setCreativeTab(OPBMTab.OPBMADDON_TAB);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 2.0F, 1F);
    }

    //Make sure you set this as your TileEntity class relevant for the block!

    //You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
        return -1;
    }

    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //It's not a normal block, so you need this too.
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new debugTileEntity();
    }
}
