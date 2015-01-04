package com.haito.opbmaddon.items;

import com.haito.opbmaddon.handler.ConfigHandler;
import com.haito.opbmaddon.items.model.OPBMWeapon;
import com.haito.opbmaddon.refference.Materials;
import com.haito.opbmaddon.refference.Names;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBloodDrinker extends OPBMWeapon {
    public ItemBloodDrinker(){
        super(Materials.bloodInfusedMetal);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Weapons.BloodDrinker);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer){
        if(entityPlayer.isSneaking()){
            //Only for sake of debugging

            entityPlayer.setHealth(4);
        }
        else{
            entityPlayer.setPosition(entityPlayer.posX,entityPlayer.posY + 20, entityPlayer.posZ);
        }
        return itemStack;
    }

    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase target, EntityLivingBase attacker)
    {
        float calculatedHealed = target.prevHealth - target.getHealth();
        if(calculatedHealed > 0) {
             calculatedHealed = (calculatedHealed / 100) * ConfigHandler.bloodDrinkerPercentageHealed;
            if(target instanceof EntityPlayer)
                calculatedHealed *= ConfigHandler.bloodDrinkerOnPlayerAttackedHealed;
            if(attacker.getHealth() + calculatedHealed > attacker.getMaxHealth())
                attacker.setHealth(attacker.getMaxHealth());
            else
                attacker.setHealth(attacker.getHealth()+calculatedHealed);
        }
        return true;
    }
    //Chyba do wywalenia
    /*
    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int idkInt, boolean smBool){
        itemStack.setItemDamage(0);

    }

    */
}
