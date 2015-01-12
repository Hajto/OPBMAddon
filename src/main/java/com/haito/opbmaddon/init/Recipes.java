package com.haito.opbmaddon.init;

import WayofTime.alchemicalWizardry.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;


public class Recipes {
    public static void init(){
        //Shapeless - reset/repair
        GameRegistry.addShapelessRecipe(new ItemStack(Items.sigilHomesoil, 1), new ItemStack(Items.sigilHomesoil, 1));
        //Shaped - crafting suplies
        GameRegistry.addShapedRecipe(new ItemStack(Items.bloodGem,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.diamond),'z', new ItemStack(ModItems.demonBloodShard),'y', new ItemStack(ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(Items.bloodGem,6),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.emerald),'z', new ItemStack(ModItems.demonBloodShard),'y', new ItemStack(ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(Items.bloodPearl,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.ender_pearl),'z', new ItemStack(ModItems.demonBloodShard),'y', new ItemStack(ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(Items.bloodIngot,1),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.iron_ingot),'z', new ItemStack(ModItems.bucketLife),'y', new ItemStack(ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(Items.bloodIngot,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.gold_ingot),'z', new ItemStack(ModItems.demonBloodShard),'y', new ItemStack(ModItems.weakBloodShard));
    }
}
