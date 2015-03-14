package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.api.soulNetwork.ComplexNetworkHandler;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemSigilInvisibility extends OPBMEnergyItem {
    public ItemSigilInvisibility() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Sigils.INVISIBILITY);
        this.setEnergyUsed(100000);
    }

    //TODO: Wyłączyć spawn particlów, jakis tick listener czy ki choj

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if(world.isRemote)
            return itemStack;

        SoulNetworkHandler.checkAndSetItemOwner(itemStack, entityPlayer);
        if (entityPlayer.isSneaking()) {
            if (!NBTHelper.getBoolean(itemStack, "isActive")) {
                makePlayerGod(entityPlayer,world,itemStack);
            } else {
                unGodPlayer(entityPlayer,world,itemStack);
            }
        }
        return itemStack;
    }

    private void makePlayerGod(EntityPlayer entityPlayer, World world, ItemStack itemStack){
        EnergyItems.syphonBatteries(itemStack, entityPlayer, this.getEnergyUsed());
        entityPlayer.setInvisible(true);
        entityPlayer.capabilities.disableDamage = true;
        NBTHelper.setBoolean(itemStack, "isActive", true);
        //Might conflict in some mods
        EnergyItems.syphonBatteries(itemStack, entityPlayer, this.getEnergyUsed()*9*SoulNetworkHandler.getCurrentMaxOrb(entityPlayer.getDisplayName()));
        NBTHelper.setInteger(itemStack,"worldTimeDelay",(int)(world.getWorldTime() - 1L) % 200);
    }

    private void unGodPlayer(EntityPlayer entityPlayer, World world, ItemStack itemStack){
        //Oh god this looks like really shitty PHP code...
        entityPlayer.setInvisible(false);
        entityPlayer.capabilities.disableDamage = false;
        entityPlayer.setHealth(entityPlayer.getHealth() - 4);

        world.playSoundEffect((float) entityPlayer.posX + 0.5F, (float) entityPlayer.posY + 0.5F, (float) entityPlayer.posZ + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        NBTHelper.setBoolean(itemStack, "isActive", false);
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (!(par3Entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer par3EntityPlayer = (EntityPlayer) par3Entity;
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }
        if ((par2World.getWorldTime() % 200L == par1ItemStack.stackTagCompound.getInteger("worldTimeDelay")) && (par1ItemStack.stackTagCompound.getBoolean("isActive"))) {
            if (!par3EntityPlayer.capabilities.isCreativeMode) {
                if (!EnergyItems.syphonBatteries(par1ItemStack, par3EntityPlayer, getEnergyUsed())) {
                    unGodPlayer(par3EntityPlayer, par2World, par1ItemStack);
                    par1ItemStack.stackTagCompound.setBoolean("isActive", false);
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Only true god can handle blood requirments of this piece of stone!");
        if (par1ItemStack.stackTagCompound != null) {
            if (par1ItemStack.stackTagCompound.getBoolean("isActive")) {
                par3List.add("Activated");
            } else {
                par3List.add("Deactivated");
            }
            par3List.add("Current owner: " + par1ItemStack.stackTagCompound.getString("ownerName"));
        }
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        //Hope it works
        unGodPlayer(player,player.getEntityWorld(),item);
        //TODO: Maybe i should punish player for throwing that item away...
        return super.onDroppedByPlayer(item, player);
    }
}
