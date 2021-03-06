package com.haito.opbmaddon.init;

import WayofTime.alchemicalWizardry.api.items.ShapedBloodOrbRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


public class Recipes {
    public static void init(){
        //Shapeless - reset/repair
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.SIGIL_HOMESOIL, 1), new ItemStack(ModItems.SIGIL_HOMESOIL, 1));
        //Shaped - crafting suplies
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.BLOOD_GEM,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.diamond),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.BLOOD_GEM,6),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.emerald),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.BLOOD_PEARL,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.ender_pearl),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.BLOOD_INGOT,1),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.iron_ingot),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.bucketLife),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.BLOOD_INGOT,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.gold_ingot),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        //Sigils
        GameRegistry.addRecipe(new ShapedBloodOrbRecipe(new ItemStack(ModItems.SIGIL_HOMESOIL), new Object[]{"xyx","yzy","xyx",'x',new ItemStack(ModItems.BLOOD_PEARL),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'z',new ItemStack(WayofTime.alchemicalWizardry.ModItems.masterBloodOrb)}));
        GameRegistry.addRecipe(new ShapedBloodOrbRecipe(new ItemStack(ModItems.SIGIL_SILKEN_HAND), new Object[]{"xyx","yzy","xyx",'x',new ItemStack(ModItems.BLOOD_GEM),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'z',new ItemStack(WayofTime.alchemicalWizardry.ModItems.masterBloodOrb)}));
        GameRegistry.addRecipe(new ShapedBloodOrbRecipe(new ItemStack(ModItems.SIGIL_INVISIBILITY), new Object[]{"xyx","yzy","xyx",'x',new ItemStack(ModItems.BLOOD_GEM),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.imbuedSlate),'z',new ItemStack(WayofTime.alchemicalWizardry.ModItems.masterBloodOrb)}));
        GameRegistry.addRecipe(new ShapedBloodOrbRecipe(new ItemStack(ModItems.SIGIL_TREACHERY), new Object[]{"xyx","yzy","xyx",'x',new ItemStack(ModItems.BLOOD_INGOT),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'z',new ItemStack(WayofTime.alchemicalWizardry.ModItems.masterBloodOrb)}));
        //Baubles
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.NECK_BLOOD_LETTER),"xyx",'x',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.itemBloodPack));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.NECK_FORTITUDE),"xyx",'x',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.lavaCrystal));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.RING_AWESOMNESS),"xyx",'x',new ItemStack(WayofTime.alchemicalWizardry.ModBlocks.bloodRune,3),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.lavaCrystal));

        //Blocks
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.POTION_STATION),"x x"," y ","x x",'x', new ItemStack(Items.potionitem,1,8257),'y',new ItemStack(ModItems.BLOOD_GEM));
    }
}
