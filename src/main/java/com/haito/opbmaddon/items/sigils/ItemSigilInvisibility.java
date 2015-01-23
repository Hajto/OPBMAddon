package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemSigilInvisibility extends OPBMEnergyItem {
    public ItemSigilInvisibility() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Sigils.INVISIBILITY);
        this.setEnergyUsed(100000);
    }

    //TODO: Wyłączyć spawn particlów, jakis tick listener czy ki choj

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        SoulNetworkHandler.checkAndSetItemOwner(itemStack, entityPlayer);
        if (entityPlayer.isSneaking()) {
            if (!NBTHelper.getBoolean(itemStack, "isActive")) {
                EnergyItems.syphonBatteries(itemStack, entityPlayer, this.getEnergyUsed());
                entityPlayer.setInvisible(true);
                entityPlayer.capabilities.disableDamage = true;
                NBTHelper.setBoolean(itemStack, "isActive", true);
                NBTHelper.setInteger(itemStack,"worldTimeDelay",(int)(world.getWorldTime() - 1L) % 200);
            } else {
                entityPlayer.setInvisible(false);
                entityPlayer.capabilities.disableDamage = false;
                entityPlayer.setHealth(entityPlayer.getHealth() - 4);
                double posX = entityPlayer.posX;
                double posY = entityPlayer.posY;
                double posZ = entityPlayer.posZ;
                world.playSoundEffect((float) posX + 0.5F, (float) posY + 0.5F, (float) posZ + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                NBTHelper.setBoolean(itemStack, "isActive", false);
            }
        }
        return itemStack;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (!(par3Entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer par3EntityPlayer = (EntityPlayer) par3Entity;
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }
        if ((par2World.getWorldTime() % 200L == par1ItemStack.stackTagCompound.getInteger("worldTimeDelay")) && (par1ItemStack.stackTagCompound.getBoolean("isActive"))) {
            if (!par3EntityPlayer.capabilities.isCreativeMode) {
                if (!EnergyItems.syphonBatteries(par1ItemStack, par3EntityPlayer, getEnergyUsed())) {
                    par1ItemStack.stackTagCompound.setBoolean("isActive", false);
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Only true god can handle blood requirments of this piece of stone!");
        if (par1ItemStack.stackTagCompound != null) {
            if (par1ItemStack.stackTagCompound.getBoolean("isActive")) {
                par3List.add("Activated");
            } else {
                par3List.add("Deactivated");
            }
            par3List.add("Current owner: " + par1ItemStack.stackTagCompound.getString("ownerName"));
        }
    }

}
