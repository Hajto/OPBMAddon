package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;


public class ItemSigilHomeSoil extends OPBMEnergyItem {
    public ItemSigilHomeSoil() {
        //super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Sigils.SigilHomesoil);
        this.setEnergyUsed(50000);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (entityPlayer.isSneaking() && !world.isRemote) {
            Bukacz(entityPlayer,world);
            EnergyItems.syphonBatteries(itemStack, entityPlayer, this.getEnergyUsed());
        } else {
            LogHelper.info(entityPlayer.posX + " " + entityPlayer.posZ);
        }
        return itemStack;
    }

    public void Bukacz(EntityPlayer player, World world) {
        ChunkCoordinates coordinates = player.getBedLocation(world.getWorldInfo().getVanillaDimension());
        player.setPositionAndUpdate(coordinates.posX, coordinates.posY+1, coordinates.posZ);
    }
}