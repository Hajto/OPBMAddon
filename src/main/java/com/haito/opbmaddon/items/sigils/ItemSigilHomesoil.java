package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.LogHelper;
import com.haito.opbmaddon.utility.NBTHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minetweaker.api.event.PlayerUseItemStartEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class ItemSigilHomesoil extends OPBMEnergyItem {
    public ItemSigilHomesoil() {
        //super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Sigils.SigilHomesoil);
        this.setEnergyUsed(50000);
    }

    //TODO: ClientOnly old data, ServerOnly Crash, Both? teleport the shit out 2 times?


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (entityPlayer.isSneaking()) {
            //if (world.getSpawnPoint().posX != entityPlayer.getBedLocation(world.getWorldInfo().getVanillaDimension()).posX && world.getSpawnPoint().posY != entityPlayer.getBedLocation(world.getWorldInfo().getVanillaDimension()).posY){
                Bukacz(entityPlayer,world);
                LogHelper.info("The bed has been set and soon i'll be ready to this up!" + world.getSpawnPoint());
                LogHelper.info(entityPlayer.getBedLocation(world.getWorldInfo().getVanillaDimension()));
            //}
        }
        return itemStack;
    }

    public void Bukacz(EntityPlayer player, World world) {
        ChunkCoordinates coordinates = player.getBedLocation(world.getWorldInfo().getVanillaDimension());
        player.setPosition(coordinates.posX,coordinates.posY,coordinates.posZ);
    }


    public ChunkCoordinates getServerBedLocation(EntityPlayer player, World world) {
        return player.getBedLocation(world.getWorldInfo().getVanillaDimension());
    }


}