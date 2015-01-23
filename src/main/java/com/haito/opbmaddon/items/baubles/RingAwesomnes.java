package com.haito.opbmaddon.items.baubles;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import baubles.api.BaubleType;
import com.haito.opbmaddon.items.model.OPBMBauble;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.LogHelper;
import com.haito.opbmaddon.utility.SoulNetworkMagicHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RingAwesomnes extends OPBMBauble {
    public RingAwesomnes(){
        super();
        this.setUnlocalizedName(Names.Baubles.RING_AWESOMNESS);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if(entityLivingBase instanceof EntityPlayer){
            EntityPlayer entityPlayer = (EntityPlayer) entityLivingBase;
            World world = entityPlayer.worldObj;
            if(!world.isRemote && world.getWorldTime() % 20L == 0){
                SoulNetworkMagicHelper.appendToSoulNetwork(entityPlayer.getDisplayName(),SoulNetworkHandler.getCurrentMaxOrb(entityPlayer.getDisplayName())*5);
            }
        }
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        LogHelper.info("");
    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        LogHelper.info("I unequiped a bauble!");
    }
}
