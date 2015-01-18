package com.haito.opbmaddon.items;

import WayofTime.alchemicalWizardry.common.tileEntity.TEAltar;
import com.haito.opbmaddon.init.ModTileEntity;
import com.haito.opbmaddon.items.model.OPBMEnergyItem;
import com.haito.opbmaddon.refference.Names;
import com.haito.opbmaddon.tileEntity.TEPotionStation;
import com.haito.opbmaddon.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class DebugItem extends OPBMEnergyItem {
    public DebugItem(){
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.EverythingChecker);
        this.setEnergyUsed(100);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if(net.minecraft.item.crafting.CraftingManager.getInstance().getRecipeList().get(1) instanceof ShapedRecipes){
            ShapedRecipes recipe = (ShapedRecipes) net.minecraft.item.crafting.CraftingManager.getInstance().getRecipeList().get(0);
            LogHelper.info(recipe.getRecipeOutput().getItem());
            entityPlayer.inventory.addItemStackToInventory(new ItemStack(recipe.getRecipeOutput().getItem(),64));
        }
        return itemStack;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        Block target = world.getBlock(x,y,z);
        ItemStack holder = new ItemStack(target);
        NBTTagCompound blockNBT = new NBTTagCompound();
        holder.writeToNBT(blockNBT);
        LogHelper.info(blockNBT);


        if (target.hasTileEntity(0)) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);

            if (tileEntity instanceof TEAltar) {
                blockNBT = new NBTTagCompound();
                LogHelper.info(blockNBT);
                NBTTagList reagentsTank = blockNBT.getTagList("reagentTanks", 10);
                LogHelper.info(reagentsTank);
                for (int i = 0; i < reagentsTank.tagCount(); i++) {
                    NBTTagCompound helper = reagentsTank.getCompoundTagAt(i);
                    //Wonder if it's even necesary here
                    StringBuilder comm = new StringBuilder();
                    comm.append("Tank ").append(i + 1).append((helper.getInteger("amount") > 0 ? " contains " + helper.getInteger("amount") + " of " + helper.getString("Reagent") : " is empty "));
                    player.addChatComponentMessage(new ChatComponentText(comm.toString()));
                }
                player.addChatComponentMessage(new ChatComponentText("Ritual is " + (blockNBT.getBoolean("isActive") ? "running, ritual name is " + blockNBT.getString("currentRitualString") : "not running")));
                LogHelper.info(blockNBT.getTag("attunedTankMap"));
            } else if(tileEntity instanceof TEPotionStation) {
                TEPotionStation tePotionStation = (TEPotionStation) tileEntity;
                LogHelper.info(tePotionStation);
            }
        }
        return true;
    }
}
