package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.utility.LogHelper;
import com.haito.opbmaddon.utility.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class ItemSigilHomesoil extends OPBMEnergyItem {
    public ItemSigilHomesoil() {
        super();
        this.setMaxStackSize(1);
        this.setEnergyUsed(50000);
    }

    //TODO: ClientOnly old data, ServerOnly Crash, Both? teleport the shit out 2 times?

    @Override
    @SideOnly(Side.SERVER)
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (entityPlayer.isSneaking()) {
            ChunkCoordinates coordinates = getServerBedLocation(entityPlayer, world);
            LogHelper.info("We are young" + coordinates.posX + coordinates.posZ);
        }
        return itemStack;
    }

    @SideOnly(Side.SERVER)
    public ChunkCoordinates getServerBedLocation(EntityPlayer player, World world){
        return player.getBedLocation(world.getWorldInfo().getVanillaDimension());
    }
}