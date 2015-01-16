package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.LogHelper;
import com.haito.opbmaddon.utility.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;
//TODO : Almost finished item
//TODO : It may need some balancing
//TODO : Way is gona hate me fo this....


public class ItemSigilTreachery extends OPBMEnergyItem {
    public ItemSigilTreachery() {
        super();
        this.setEnergyUsed(10000);
        this.setUnlocalizedName(Names.Sigils.SigilOfTreachery);
        this.setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (entityPlayer.isSneaking())
            if (NBTHelper.getBoolean(itemStack, "isActive"))
                NBTHelper.setBoolean(itemStack, "isActive", false);
            else
                NBTHelper.setBoolean(itemStack, "isActive", true);

        LogHelper.info("worldTimeDelay" + NBTHelper.getInt(itemStack,"worldTimeDelay"));

        return itemStack;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (!world.isRemote && entity instanceof EntityPlayer && NBTHelper.getBoolean(itemStack,"isActive")) {
            EntityPlayer entityPlayer = (EntityPlayer) entity;
            EnergyItems.checkAndSetItemOwner(itemStack, entityPlayer);
            //Payment first
            if ((world.getWorldTime() % 200L == itemStack.stackTagCompound.getInteger("worldTimeDelay")) && (itemStack.stackTagCompound.getBoolean("isActive"))) {
                if (!entityPlayer.capabilities.isCreativeMode) {
                    if (!EnergyItems.syphonBatteries(itemStack, entityPlayer, getEnergyUsed())) {
                        itemStack.stackTagCompound.setBoolean("isActive", false);
                    }
                }
            }
            if (entityPlayer.getHealth() < 8){
                //Actual effect
                entityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 1200));
                entityPlayer.addPotionEffect(new PotionEffect(AlchemicalWizardry.customPotionSoulFrayID, 2400));
                EnergyItems.syphonBatteries(itemStack, entityPlayer, this.getEnergyUsed());
            }
        }
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("It should save your butt...");
        par3List.add("It may also kill you, but who cares..");
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
