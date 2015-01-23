package com.haito.opbmaddon.items;

import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.refference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by Haito on 2015-01-10.
 */
public class BloodInfusedPearl extends OPBMItem {
    private int eatingDuration = 30;

    public BloodInfusedPearl() {
        super();
        this.setUnlocalizedName(Names.BLOOD_INFUSED_PEARL);
        this.setMaxStackSize(16);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        --itemStack.stackSize;
        //Fly, fly, fly with the wind
        Random fly = new Random();
        player.motionY = fly.nextGaussian()*10;
        player.motionX = fly.nextGaussian()*10;
        player.motionZ = fly.nextGaussian()*10;
        player.fallDistance = 0;
        return itemStack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.eat;
    }


    public int getEatingDuration() {
        return eatingDuration;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        list.add("Use it, you'll gona have good time..");
        list.add("Resets fall distance");
    }
}
