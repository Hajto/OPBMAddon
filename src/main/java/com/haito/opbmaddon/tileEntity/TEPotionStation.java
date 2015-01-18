package com.haito.opbmaddon.tileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TEPotionStation extends TileEntity{


    private ItemStack inventory;
    public int  effect;
    public ItemStack potion;

    public TEPotionStation(){

    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        potion = ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("inventory"));
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        NBTTagCompound tagCompound = new NBTTagCompound();
        potion.writeToNBT(tagCompound);
        nbtTagCompound.setTag("inventory",tagCompound);
    }

}
