package com.haito.opbmaddon.items.baubles;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.tileEntity.TEAltar;
import baubles.api.BaubleType;
import com.haito.opbmaddon.items.model.OPBMBauble;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.LogHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;

//Code by WayOfTime, I just made it bauble

public class BloodLetter extends OPBMBauble {
    public static int conversionRate = 100;
    public static float activationPoint = 0.5F;
    public static int tickRate = 20;
    public static int maxStored = 20000;

    public BloodLetter() {
        super();
        this.setUnlocalizedName(Names.Baubles.NECK_PACK);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) entityLivingBase;
            World world = entityPlayer.worldObj;
            if (!world.isRemote && !entityPlayer.capabilities.isCreativeMode) {
                if (world.getWorldTime() % 20 == 0) {
                    boolean shouldExecute = entityPlayer.getHealth() / entityPlayer.getMaxHealth() > activationPoint && this.getStoredLP(itemStack) < maxStored;
                    if (shouldExecute) {
                        SoulNetworkHandler.hurtPlayer(entityPlayer, 1.0F);
                        this.setStoredLP(itemStack, Math.min(this.getStoredLP(itemStack) + conversionRate, maxStored));
                    }
                }
            }
        }
    }

    @Override
    public void notSneakRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!world.isRemote) {

            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, entityPlayer, false);
            if (entityPlayer.isSneaking() && movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int x = movingobjectposition.blockX;
                int y = movingobjectposition.blockY;
                int z = movingobjectposition.blockZ;
                TileEntity tile = world.getTileEntity(x, y, z);
                LogHelper.info("Sneaking server...");

                if (tile instanceof TEAltar && !((TEAltar) tile).isActive()) {
                    LogHelper.info("Insance of altar found");
                    TEAltar altar = (TEAltar) tile;
                    int amount = this.getStoredLP(itemStack);
                    LogHelper.info("Transfer bugn " + amount);
                    if (amount > 0) {
                        int filledAmount = altar.fillMainTank(amount);
                        amount -= filledAmount;
                        this.setStoredLP(itemStack, amount);
                        world.markBlockForUpdate(x, y, z);
                    }
                }
            }
        }
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("This pack really chaffes...");
        if (par1ItemStack.stackTagCompound != null) {
            NBTTagCompound itemTag = par1ItemStack.stackTagCompound;
            par3List.add("Stored LP: " + this.getStoredLP(par1ItemStack));
        }

    }
}
