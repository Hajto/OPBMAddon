package com.haito.opbmaddon.items.sigils;

import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Configs;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.utility.BlockInteractionHelper;
import com.haito.opbmaddon.utility.LogHelper;
import com.haito.opbmaddon.utility.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemSigilSilk extends OPBMEnergyItem {
    public static List<Block> blackList = new ArrayList<Block>();
    public ItemSigilSilk() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Sigils.SILKEN_HAND);

        blackList.add(Blocks.bed);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote || !entityPlayer.isSneaking())
            return false;

        //TODO : For now it's working but i may rebuild it using rayTracing, coz it returns a nice "type" for
        //TODO : i could differenciate what mod it's in using in instead of manually right clicking to change it
        //TODO : that could be extremaly fancy!

        MovingObjectPosition mouseOver = entityPlayer.rayTrace(5, 1);
        LogHelper.info("Debug " + mouseOver);
        EnergyItems.checkAndSetItemOwner(itemStack, entityPlayer);
        //EnergyItems.syphonBatteries(itemStack,entityPlayer,this.getEnergyUsed());

        Block target = world.getBlock(x, y, z);
        LogHelper.info(target);
        //Stub need to change it to more convinient stuff
        if (NBTHelper.getBoolean(itemStack, "isBlockMode")) {
            //If stack contains BLock nbt, below is placing machanic
            if (NBTHelper.getBoolean(itemStack, "contBlock")) {
                placeBlock(itemStack,world,x,y,z,side);
            } else if ((target != Blocks.bedrock || Configs.Items.canBedrock) && !blackList.contains(target)) {
                pickUpBlock(itemStack,target,world,x,y,z);
            }
        } else if (NBTHelper.getBoolean(itemStack, "contMob")) {
            LogHelper.info("Second shit");
            placeMob(itemStack,world,x,y,z,side);
        }
        entityPlayer.swingItem();
        return true;
    }

    public void placeMob(ItemStack itemStack, World world, int x,int y,int z,int side){
        NBTTagCompound mobNBT = new NBTTagCompound();
        mobNBT = NBTHelper.getTagCompound(itemStack, "mobNBT");

        NBTTagList temporary = new NBTTagList();
        int[] correctCoords = BlockInteractionHelper.modifyOutChordsBySide(x,y,z,side);
        temporary.appendTag(new NBTTagDouble(correctCoords[0]));
        temporary.appendTag(new NBTTagDouble(correctCoords[1]));
        temporary.appendTag(new NBTTagDouble(correctCoords[2]));

        mobNBT.setTag("Pos",temporary);
        Entity mob = EntityList.createEntityFromNBT(mobNBT,world);
        world.spawnEntityInWorld(mob);
        NBTHelper.setBoolean(itemStack, "contMob", false);
        NBTHelper.removeTag(itemStack,"mobNBT");
    }

    public void pickUpBlock(ItemStack itemStack,Block target, World world, int x, int y , int z){
        NBTTagCompound blockNBT = new NBTTagCompound();
        ItemStack holder = new ItemStack(target);
        holder.writeToNBT(blockNBT);
        NBTHelper.setTagCompound(itemStack, "blockNBT", blockNBT);

        if (target.hasTileEntity(0)) {
            blockNBT = new NBTTagCompound();
            world.getTileEntity(x, y, z).writeToNBT(blockNBT);
            NBTHelper.setTagCompound(itemStack, "tileNBT", blockNBT);
            NBTHelper.setBoolean(itemStack, "contTile", true);
        }
        NBTHelper.setString(itemStack, "blockCons", target.getUnlocalizedName());
        NBTHelper.setString(itemStack, "blockName", target.getLocalizedName());
        NBTHelper.setBoolean(itemStack, "contBlock", true);
        world.removeTileEntity(x, y, z);
        world.setBlockToAir(x, y, z);
    }

    public void placeBlock(ItemStack itemStack, World world, int x,int y, int z, int side){
        ItemStack holder = ItemStack.loadItemStackFromNBT(NBTHelper.getTagCompound(itemStack, "blockNBT"));
        Block tempo = Block.getBlockFromItem(holder.getItem());
        NBTHelper.removeTag(itemStack, "blockNBT");

        int[] correctCoords = BlockInteractionHelper.modifyOutChordsBySide(x,y,z,side);

        world.setBlock(correctCoords[0], correctCoords[1] , correctCoords[2], tempo);
        if (NBTHelper.getBoolean(itemStack, "contTile")) {
            TileEntity tileEntity = TileEntity.createAndLoadEntity(NBTHelper.getTagCompound(itemStack, "tileNBT"));
            world.setTileEntity(x, y , z, tileEntity);
            NBTHelper.removeTag(itemStack, "tileNBT");
        }
        NBTHelper.setBoolean(itemStack, "contBlock", false);
        NBTHelper.setBoolean(itemStack, "contTile", false);
    }

    //AKA MobPickUp
    @Override
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer entityPlayer, EntityLivingBase target) {
        LogHelper.info("Interaction");
        if (!NBTHelper.getBoolean(itemStack, "isBlockMode") && !entityPlayer.worldObj.isRemote && !itemStack.stackTagCompound.getBoolean("contMob") && target instanceof EntityLiving && !(target instanceof EntityPlayer) && !entityPlayer.worldObj.isRemote) {
            itemStack = entityPlayer.getHeldItem();
            LogHelper.info("Containing mob : " + NBTHelper.getBoolean(itemStack, "contMob"));
            NBTTagCompound mob = new NBTTagCompound();
            target.writeEntityToNBT(mob);
            ((EntityLiving) target).isDead = true;
            LogHelper.info(mob);
            mob.setString("id",(String)EntityList.classToStringMapping.get(target.getClass()));
            NBTHelper.setTagCompound(itemStack, "mobNBT", mob);
            //NBTHelper.setString(itemStack,"mobName",targ);
            LogHelper.info(EntityList.getEntityString(target));
            NBTHelper.setString(itemStack,"mobName",EntityList.getEntityString(target));
            NBTHelper.setBoolean(itemStack, "contMob", true);

            entityPlayer.swingItem();
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if(!entityPlayer.isSneaking() && !world.isRemote)
            if (NBTHelper.getBoolean(itemStack, "isBlockMode")) {
                NBTHelper.setBoolean(itemStack, "isBlockMode", false);
                entityPlayer.addChatMessage(new ChatComponentText("Sigil turned into mob picking mode"));
            } else {
                NBTHelper.setBoolean(itemStack, "isBlockMode", true);
                entityPlayer.addChatMessage(new ChatComponentText("Sigil turned into block picking mode"));
            }
        world.playSoundEffect(entityPlayer.posX,entityPlayer.posY,entityPlayer.posZ,"mob.endermen.portal",1F,1F);
        return itemStack;
    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        NBTHelper.setBoolean(par1ItemStack, "isBlockMode", true);
        NBTHelper.setBoolean(par1ItemStack, "contBlock", false);
        //NBTHelper.setBoolean(par1ItemStack, "reqNBT", false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Mode: " + (NBTHelper.getBoolean(par1ItemStack, "isBlockMode") ? "block picking" : "mob picking"));
        par3List.add("Containing: " + (NBTHelper.getBoolean(par1ItemStack, "contBlock") ? NBTHelper.getString(par1ItemStack, "blockName") : (NBTHelper.getBoolean(par1ItemStack, "contMob") ? NBTHelper.getString(par1ItemStack,"mobName") : "none")));
        par3List.add("Current owner: " + NBTHelper.getString(par1ItemStack, "ownerName"));
    }
}
