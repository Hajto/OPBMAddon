package com.haito.opbmaddon.items.model;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OPBMBauble extends OPBMEnergyItem implements IBauble {
    public OPBMBauble() {
        super();
        this.setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return null;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (world.isRemote)
            return itemStack;
        if (!entityPlayer.isSneaking()) {
            InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(entityPlayer);
            for (int i = 0; i < baubles.getSizeInventory(); i++) {
                if (baubles.isItemValidForSlot(i, itemStack)) {
                    ItemStack slotContents = baubles.getStackInSlot(i);
                    if (slotContents == null || ((IBauble) slotContents.getItem()).canUnequip(slotContents, entityPlayer)) {
                        baubles.setInventorySlotContents(i, itemStack.copy());
                        if (!entityPlayer.capabilities.isCreativeMode)
                            entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, null);
                        if (slotContents != null) {
                            ((IBauble) slotContents.getItem()).onUnequipped(slotContents, entityPlayer);
                            return slotContents.copy();
                        }
                        onEquipped(itemStack, entityPlayer);
                        break;
                    }
                }
            }
        } else {
            specialStuff(itemStack,world,entityPlayer);
        }
        return itemStack;
    }

    public void specialStuff(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        //Stub
    }
}
