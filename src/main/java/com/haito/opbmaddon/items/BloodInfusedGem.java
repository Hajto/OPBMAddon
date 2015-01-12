package com.haito.opbmaddon.items;

import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.common.potion.PotionSoulFray;
import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.refference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.List;

/**
 * Created by Haito on 2015-01-10.
 */
public class BloodInfusedGem extends OPBMItem{
    public BloodInfusedGem(){
        super();
        this.setUnlocalizedName(Names.BloodInfusedGem);
        this.setMaxStackSize(16);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!entityPlayer.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }
        entityPlayer.removePotionEffect(AlchemicalWizardry.customPotionSoulFrayID);
        return itemStack;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        list.add("Eating it clears Soul Fray... but may result in catching flesh flu..");
    }

}
