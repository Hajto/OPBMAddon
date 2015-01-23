package com.haito.opbmaddon.items.model;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import com.haito.opbmaddon.creativeTab.OPBMTab;
import com.haito.opbmaddon.refference.MainRef;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;


public class OPBMWeapon extends ItemSword implements IBindable{
    public OPBMWeapon(ToolMaterial toolMaterial){
        super(toolMaterial);
        this.setCreativeTab(OPBMTab.OPBMADDON_TAB);
        this.setFull3D();
    }
    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", MainRef.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", MainRef.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
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
