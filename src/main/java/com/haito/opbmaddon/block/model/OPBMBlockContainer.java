package com.haito.opbmaddon.block.model;

import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.tileEntity.TEPotionStation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Haito on 2015-01-18.
 */
public class OPBMBlockContainer extends BlockContainer {
    protected OPBMBlockContainer(Material p_i45386_1_) {
        super(p_i45386_1_);
    }
    public OPBMBlockContainer(){
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Names.ResourcePrefix, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
