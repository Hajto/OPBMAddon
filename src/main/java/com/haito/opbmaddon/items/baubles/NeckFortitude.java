package com.haito.opbmaddon.items.baubles;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import baubles.api.BaubleType;
import com.haito.opbmaddon.items.model.OPBMBauble;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.LogHelper;
import com.haito.opbmaddon.utility.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import java.util.List;

public class NeckFortitude extends OPBMBauble {
    public NeckFortitude(){
        super();
        this.setUnlocalizedName(Names.Baubles.NECK_FORTITUDE);
        this.setEnergyUsed(500);
    }

    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if(entityLivingBase instanceof EntityPlayer && !((EntityPlayer) entityLivingBase).worldObj.isRemote && !((EntityPlayer) entityLivingBase).capabilities.isCreativeMode){
            EntityPlayer entityPlayer = (EntityPlayer) entityLivingBase;
            World world = entityPlayer.worldObj;
            if(!world.isRemote && world.getWorldTime() % 200L == 0){
                EnergyItems.syphonBatteries(itemStack,entityPlayer,this.getEnergyUsed()* SoulNetworkHandler.getCurrentMaxOrb(entityPlayer.getDisplayName()));
            }
        }
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if(entityLivingBase instanceof EntityPlayer && !((EntityPlayer) entityLivingBase).worldObj.isRemote){
            EntityPlayer entityPlayer = (EntityPlayer) entityLivingBase;
            entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40D);
            LogHelper.info(entityPlayer.getMaxHealth());
        }
    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if(entityLivingBase instanceof EntityPlayer && !((EntityPlayer) entityLivingBase).worldObj.isRemote){
            EntityPlayer entityPlayer = (EntityPlayer) entityLivingBase;
            entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
            LogHelper.info(entityPlayer.getMaxHealth());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Only for badasses!");
        par3List.add("Current owner: " + NBTHelper.getString(par1ItemStack, "ownerName"));
    }
}
