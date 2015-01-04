package com.haito.opbmaddon.items.model;

import com.haito.opbmaddon.creativeTab.OPBMTab;
import com.haito.opbmaddon.refference.MainRef;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;


public class OPBMWeapon extends ItemSword {
    public OPBMWeapon(ToolMaterial toolMaterial){
        super(toolMaterial);
        this.setCreativeTab(OPBMTab.OPBMAddonTab);
    }
    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", MainRef.modId.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", MainRef.modId.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
