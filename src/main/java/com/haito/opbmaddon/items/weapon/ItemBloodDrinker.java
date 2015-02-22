package com.haito.opbmaddon.items.weapon;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import com.haito.opbmaddon.items.model.OPBMWeapon;
import com.haito.opbmaddon.refference.Configs;
import com.haito.opbmaddon.refference.Materials;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.refference.Particles;
import com.haito.opbmaddon.utility.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

import java.util.List;

public class ItemBloodDrinker extends OPBMWeapon {
    public ItemBloodDrinker() {
        super(Materials.bloodInfusedMetal);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Weapons.BLOOD_DRINKER);
        LogHelper.info(Configs.Items.bloodDrinkerLevels[2]);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (entityPlayer.isSneaking()) {
            NBTHelper.setInteger(itemStack, "bodyCount", 99);
        } else {
            SoulNetworkHandler.checkAndSetItemOwner(itemStack, entityPlayer);
            CommonSoundHelper.playSoundAt(entityPlayer, "random.fizz", 5F, 10F, 100);
            entityPlayer.setItemInUse(itemStack, 1000);
        }
        return itemStack;
    }

    //TODO: Podzielić na submetody
    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) {
        if (attacker instanceof EntityPlayer && !((EntityPlayer) attacker).capabilities.isCreativeMode) {
            World worldRef = ((EntityPlayer) attacker).worldObj;
            if(!worldRef.isRemote) {
                int weaponLevel = NBTHelper.getInt(itemStack, "weaponLevel");
                EntityPlayer player = (EntityPlayer) attacker;
                if (weaponLevel > 0) {
                    onDamage(itemStack, target, player);
                }
                if (target.getHealth() == 0 || target.isDead) {
                    weaponLevelUp(itemStack, player);
                    onKill(itemStack, player);
                }
            }
            spawnDeathParticles(worldRef,target);
        }
        try {
            if (((EntityPlayer) attacker).worldObj.isRemote) {
                LogHelper.info("Client side shit");
            }
        } catch (ClassCastException e){
            e.printStackTrace();
        }
        return true;
    }

    public void onDamage(ItemStack itemStack, EntityLivingBase target, EntityPlayer attacker){
        int weaponLevel = NBTHelper.getInt(itemStack, "weaponLevel");
        float calculatedHealed = target.prevHealth - target.getHealth();
        if (calculatedHealed > 0) {
            calculatedHealed = (calculatedHealed / 100) * Configs.Items.bloodDrinkerPercentageHealed;
            //Weapon level 3 - no penalty when attacking players
            if (target instanceof EntityPlayer && weaponLevel < 3)
                calculatedHealed *= Configs.Items.bloodDrinkerOnPlayerAttackedHealed;
            //Weapon level 4 - Cursed Blade
            //TODO: Apply some nasty bad potions effects
            if(weaponLevel >= 4){
                target.addPotionEffect(new PotionEffect(Potion.weakness.id,120 ,2));
            }
            //Weapon level 5 - health gainud doubled, be realistic no body will kill 10 000 entitites during one playthrough
            if (weaponLevel == 5) {
                calculatedHealed *= 2;
            }
            if (attacker.getHealth() + calculatedHealed > attacker.getMaxHealth())
                attacker.setHealth(attacker.getMaxHealth());
            else
                attacker.setHealth(attacker.getHealth() + calculatedHealed);

            SoulNetworkHandler.checkAndSetItemOwner(itemStack, attacker);
            SoulNetworkMagicHelper.appendToSoulNetwork(itemStack.stackTagCompound.getString("ownerName"), (int) (calculatedHealed * 100));
        }
    }

    public void weaponLevelUp(ItemStack itemStack, EntityPlayer player){
        int weaponLevel = NBTHelper.getInt(itemStack, "weaponLevel");
        int placeholder = NBTHelper.getInt(itemStack, "bodyCount");
        NBTHelper.setInteger(itemStack, "bodyCount", placeholder + 1);

        //Level up!
        if (placeholder + 1 >= Configs.Items.bloodDrinkerLevels[weaponLevel]) {
            LogHelper.info(placeholder + 1 + " is greater than " + Configs.Items.bloodDrinkerLevels[weaponLevel]);
            NBTHelper.setInteger(itemStack, "weaponLevel", weaponLevel + 1);
            player.addChatComponentMessage(new ChatComponentText("Your weapon is getting stronger! It's now level " + (placeholder + 1)));
        }
    }

    public void onKill(ItemStack itemStack, EntityPlayer player){
        int weaponLevel = NBTHelper.getInt(itemStack, "weaponLevel");
        FoodStats foodStats = player.getFoodStats();
        //Weapon level 2 - Hunger restoration 1
        if (weaponLevel >= 2 && weaponLevel < 4) {
            foodStats.setFoodLevel(foodStats.getFoodLevel() + 3);
            foodStats.setFoodSaturationLevel(foodStats.getSaturationLevel() + 2);
        }
        //Weapon level 4 - Hunger restoration 2
        if (weaponLevel >= 4) {
            foodStats.setFoodLevel(foodStats.getFoodLevel() + 6);
            foodStats.setFoodSaturationLevel(foodStats.getSaturationLevel() + 4);
        }
    }

    public void spawnDeathParticles(World worldRef,EntityLivingBase target){
        double posX = target.posX;
        double posY = target.posY + 1;
        double posZ = target.posZ;
        target.posY += 1;
        LogHelper.info("Explosion expected any time on " + posX + " " + posY + " " + posZ);
        worldRef.playSoundEffect((float) posX + 0.5F, (float) posY + 0.5F, (float) posZ + 0.5F, "random.fizz", 0.5F, 2.6F + (worldRef.rand.nextFloat() - worldRef.rand.nextFloat()) * 0.8F);
        CommonParticlesHelper.showParticleAt(Particles.WITCH_MAGIC, target, 0F, 0F, 0F);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("It drinks blood, it's very hungry right now..");
        if (par1ItemStack.stackTagCompound != null) {
            int weaponLevel = NBTHelper.getInt(par1ItemStack, "weaponLevel");
            par3List.add("Current level: " + weaponLevel);
            if (weaponLevel >= 2)
                par3List.add(" - Now your blade will satisfy your hunger");
            if (weaponLevel >= 3) {
                par3List.add(" - Now your blade can drink blood of all your enemies");
            }
            if (weaponLevel >= 5) {
                par3List.add(" - Now you completely restored your blade, you can feel it in your veins.");
            }
            par3List.add("Current body count: " + NBTHelper.getInt(par1ItemStack, "bodyCount"));
            par3List.add("Current owner: " + NBTHelper.getString(par1ItemStack, "ownerName"));
        }
    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        NBTHelper.setInteger(par1ItemStack, "bodyCount", 0);
        NBTHelper.setInteger(par1ItemStack, "weaponLevel", 1);
    }
}
