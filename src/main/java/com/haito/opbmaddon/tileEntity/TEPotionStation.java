package com.haito.opbmaddon.tileEntity;

import com.haito.opbmaddon.utility.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TEPotionStation extends TileEntity{

    //TO Be used later on
    private ItemStack inventory;

    public String owner;
    public ItemStack potion;

    public TEPotionStation(){

    }

    private void writeSyncableDataToNBT(NBTTagCompound nbtTagCompound){
        if(potion != null) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            this.potion.writeToNBT(tagCompound);
            nbtTagCompound.setTag("inventory", tagCompound);
        }
        nbtTagCompound.setString("owner",this.owner);
        LogHelper.info("Data writen");
    }

    private void readSyncableDataFromNBT(NBTTagCompound nbtTagCompound){
        this.potion = ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("inventory"));
        this.owner = nbtTagCompound.getString("owner");
        LogHelper.info("Data read");
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        this.readSyncableDataFromNBT(nbtTagCompound);
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        this.writeSyncableDataToNBT(nbtTagCompound);
    }
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncableDataToNBT(syncData);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readSyncableDataFromNBT(pkt.func_148857_g());
    }

}
