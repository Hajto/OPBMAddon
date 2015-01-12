package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.LogHelper;
import com.haito.opbmaddon.utility.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class ItemSigilSilk extends OPBMEnergyItem{
    public ItemSigilSilk(){
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Sigils.SigilOfSilkenHand);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        EnergyItems.checkAndSetItemOwner(itemStack, entityPlayer);
        if(NBTHelper.getBoolean(itemStack,"contBlock")){
            ItemStack holder = ItemStack.loadItemStackFromNBT(NBTHelper.getTagCompound(itemStack, "blockNBT"));
            Block tempo = Block.getBlockFromItem(holder.getItem());
            world.setBlock(x, y+1, z, tempo);
            if(NBTHelper.getBoolean(itemStack,"contTile")){
                NBTTagCompound tileNBT = new NBTTagCompound();
                tileNBT = NBTHelper.getTagCompound(itemStack, "tileNBT");
                TileEntity tileEntity = TileEntity.createAndLoadEntity(tileNBT);
                world.setTileEntity(x,y+1,z,tileEntity);
                LogHelper.info(world.getTileEntity(x,y+1,z) + " NBT -> "+tileNBT + "Tile -> "+ tileEntity);
            }
            NBTHelper.setBoolean(itemStack, "contBlock", false);
            NBTHelper.setBoolean(itemStack, "contTile", false);
        } else {
            Block target = world.getBlock(x, y, z);
            NBTTagCompound blockNBT = new NBTTagCompound();
            ItemStack holder = new ItemStack(target);
            holder.writeToNBT(blockNBT);
            NBTHelper.setTagCompound(itemStack,"blockNBT",blockNBT);

            if(target.hasTileEntity(0)){
                blockNBT = new NBTTagCompound();
                world.getTileEntity(x,y,z).writeToNBT(blockNBT);
                NBTHelper.setTagCompound(itemStack,"tileNBT",blockNBT);
                NBTHelper.setBoolean(itemStack,"contTile",true);
            }
            NBTHelper.setString(itemStack,"blockCons",target.getUnlocalizedName());
            NBTHelper.setString(itemStack, "blockName", target.getLocalizedName());
            NBTHelper.setBoolean(itemStack, "contBlock", true);
            world.removeTileEntity(x,y,z);
            world.setBlockToAir(x,y,z);
        }
        return true;
    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        NBTHelper.setBoolean(par1ItemStack, "contBlock", false);
        NBTHelper.setBoolean(par1ItemStack, "reqNBT",false);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Containing: " + (NBTHelper.getBoolean(par1ItemStack,"contBlock")?NBTHelper.getString(par1ItemStack,"blockName"):"none"));
        par3List.add("Current owner: " + NBTHelper.getString(par1ItemStack, "ownerName"));
        par3List.add("Debug: "+NBTHelper.getTagCompound(par1ItemStack,"tileNBT"));
    }
}