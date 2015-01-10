package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.refference.Particles;
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
        } else if(world.isRemote){
            LogHelper.info(entityPlayer.posX + " " + entityPlayer.posZ);
            double posX = entityPlayer.posX;
            double posY = entityPlayer.posY + 1;
            double posZ = entityPlayer.posZ;
            world.spawnParticle(Particles.WITCH_MAGIC, posX + Math.random() - Math.random(), posY + Math.random() - Math.random(), posZ + Math.random() - Math.random(), 0.0D, 0.0D, 0.0D);
        }
        return itemStack;
    }

    public void Bukacz(EntityPlayer player, World world) {
        ChunkCoordinates coordinates = player.getBedLocation(world.getWorldInfo().getVanillaDimension());
        player.setPositionAndUpdate(coordinates.posX, coordinates.posY+1, coordinates.posZ);
    }
}