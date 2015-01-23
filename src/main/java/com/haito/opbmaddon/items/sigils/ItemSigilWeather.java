package com.haito.opbmaddon.items.sigils;

import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.refference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemSigilWeather extends OPBMItem {
    public ItemSigilWeather(){
        super();
        this.setUnlocalizedName(Names.Sigils.WEATHER);
        this.setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if(world.isRemote)
            return itemStack;
        if(world.isRaining()){
            world.getWorldInfo().setRaining(false);
            world.getWorldInfo().setThundering(false);
            world.getWorldInfo().setRainTime(0);
            world.getWorldInfo().setThunderTime(0);
        }

        return itemStack;
    }
}
