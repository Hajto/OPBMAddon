package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.refference.Particles;
import com.haito.opbmaddon.utility.CommonParticlesHelper;
import com.haito.opbmaddon.utility.LogHelper;
import com.haito.opbmaddon.utility.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import java.util.List;


public class ItemSigilHomeSoil extends OPBMEnergyItem {
    //TODO: Reset motion
    public ItemSigilHomeSoil() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Sigils.SigilHomesoil);
        this.setEnergyUsed(50000);
        this.setFull3D();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        EnergyItems.checkAndSetItemOwner(itemStack, entityPlayer);
        if (entityPlayer.isSneaking() && !world.isRemote) {
            if (NBTHelper.getBoolean(itemStack, "isActive")) {
                Bukacz(entityPlayer);
            } else {
                Double posX = NBTHelper.getDouble(itemStack, "posX");
                Double posY = NBTHelper.getDouble(itemStack, "posY");
                Double posZ = NBTHelper.getDouble(itemStack, "posZ");
                if (posX != 0 && posY != 0 && posZ != 0) {
                    entityPlayer.setPositionAndUpdate(posX, posY, posZ);
                } else {
                    NBTHelper.setDouble(itemStack, "posX", entityPlayer.posX);
                    NBTHelper.setDouble(itemStack, "posY", entityPlayer.posY);
                    NBTHelper.setDouble(itemStack, "posZ", entityPlayer.posZ);
                }
            }
            EnergyItems.syphonBatteries(itemStack, entityPlayer, this.getEnergyUsed());
        } else {
            if (NBTHelper.getBoolean(itemStack, "isActive")) {
                LogHelper.info(false);
                NBTHelper.setBoolean(itemStack, "isActive", false);
            } else {
                LogHelper.info(true);
                NBTHelper.setBoolean(itemStack, "isActive", true);
            }
        }

        if (world.isRemote) {
            CommonParticlesHelper.showParticleAt(Particles.WITCH_MAGIC, entityPlayer, 0F, 0F, 0F);
        }
        return itemStack;
    }

    public void Bukacz(EntityPlayer player) {
        ChunkCoordinates coordinates = player.getBedLocation(player.worldObj.provider.dimensionId);
        if (coordinates == null)
            coordinates = player.worldObj.getSpawnPoint();
        player.setPositionAndUpdate(coordinates.posX, coordinates.posY + 1, coordinates.posZ);
        //player.worldObj.isRemote
    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        NBTHelper.setBoolean(par1ItemStack, "isActive", true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("It can take you to home or to your favorite place, you choose...");
        if (NBTHelper.getBoolean(par1ItemStack, "isActive")) {
            par3List.add("Home");
        } else {
            par3List.add("Waypoint: " + Math.round(NBTHelper.getDouble(par1ItemStack, "posX")) + " " + Math.round(NBTHelper.getInt(par1ItemStack, "posY")) + " " + Math.round(NBTHelper.getDouble(par1ItemStack, "posZ")));
        }
        par3List.add("Current owner: " + NBTHelper.getString(par1ItemStack, "ownerName"));
    }
}