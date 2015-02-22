package com.haito.opbmaddon.items.model;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class OPBMEnergyItem extends OPBMItem implements IBindable{

    private int energyUsed = 0;

    public OPBMEnergyItem(){
        super();
        this.setFull3D();
    }

    public int getEnergyUsed() {
        return energyUsed;
    }
    public void setEnergyUsed(int energyUsed) {
        this.energyUsed = energyUsed;
    }

    //Stolen methods...
    protected void damagePlayer(World world, EntityPlayer player, int damage)
    {
        if (world != null)
        {
            double posX = player.posX;
            double posY = player.posY;
            double posZ = player.posZ;
            world.playSoundEffect((float)posX + 0.5F, (float)posY + 0.5F, (float)posZ + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
            float f = 1.0F;
            float f1 = f * 0.6F + 0.4F;
            float f2 = f * f * 0.7F - 0.5F;
            float f3 = f * f * 0.6F - 0.7F;
            for (int l = 0; l < 8; l++) {
                world.spawnParticle("reddust", posX + Math.random() - Math.random(), posY + Math.random() - Math.random(), posZ + Math.random() - Math.random(), f1, f2, f3);
            }
        }
        for (int i = 0; i < damage; i++)
        {
            player.setHealth(player.getHealth() - 1.0F);
            if (player.getHealth() <= 5.0E-4D)
            {
                player.inventory.dropAllItems();
                break;
            }
        }
    }

    public void setStoredLP(ItemStack itemStack, int lp) {
        if(!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = itemStack.getTagCompound();
        tag.setInteger("storedLP", lp);
    }

    public int getStoredLP(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = itemStack.getTagCompound();
        return tag.getInteger("storedLP");
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.stackTagCompound != null) {
            if (par1ItemStack.stackTagCompound.getBoolean("isActive")) {
                par3List.add("Activated");
            } else {
                par3List.add("Deactivated");
            }
            par3List.add("Current owner: " + par1ItemStack.stackTagCompound.getString("ownerName"));
        }
    }
}
