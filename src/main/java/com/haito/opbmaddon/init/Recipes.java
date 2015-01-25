package com.haito.opbmaddon.init;

import WayofTime.alchemicalWizardry.api.items.ShapedBloodOrbRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


public class Recipes {
    public static void init(){
        //Shapeless - reset/repair
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.sigilHomesoil, 1), new ItemStack(ModItems.sigilHomesoil, 1));
        //Shaped - crafting suplies
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.bloodGem,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.diamond),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.bloodGem,6),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.emerald),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.bloodPearl,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.ender_pearl),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.bloodIngot,1),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.iron_ingot),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.bucketLife),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.bloodIngot,4),"xyx","xzx","xyx",'x', new ItemStack(net.minecraft.init.Items.gold_ingot),'z', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),'y', new ItemStack(WayofTime.alchemicalWizardry.ModItems.weakBloodShard));
        //Sigils
        GameRegistry.addRecipe(new ShapedBloodOrbRecipe(new ItemStack(ModItems.sigilHomesoil), new Object[]{"xyx","yzy","xyx",'x',new ItemStack(ModItems.bloodPearl),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'z',new ItemStack(WayofTime.alchemicalWizardry.ModItems.masterBloodOrb)}));
        GameRegistry.addRecipe(new ShapedBloodOrbRecipe(new ItemStack(ModItems.sigilSilkenHand), new Object[]{"xyx","yzy","xyx",'x',new ItemStack(ModItems.bloodGem),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'z',new ItemStack(WayofTime.alchemicalWizardry.ModItems.masterBloodOrb)}));
        GameRegistry.addRecipe(new ShapedBloodOrbRecipe(new ItemStack(ModItems.sigilInvisibility), new Object[]{"xyx","yzy","xyx",'x',new ItemStack(ModItems.bloodGem),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.imbuedSlate),'z',new ItemStack(WayofTime.alchemicalWizardry.ModItems.masterBloodOrb)}));
        GameRegistry.addRecipe(new ShapedBloodOrbRecipe(new ItemStack(ModItems.sigilTreachery), new Object[]{"xyx","yzy","xyx",'x',new ItemStack(ModItems.bloodIngot),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'z',new ItemStack(WayofTime.alchemicalWizardry.ModItems.masterBloodOrb)}));
        //Baubles
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.neckBloodLetter),"xyx",'x',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.itemBloodPack));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.neckFortitude),"xyx",'x',new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonicSlate),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.lavaCrystal));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ringAwesomness),"xyx",'x',new ItemStack(WayofTime.alchemicalWizardry.ModBlocks.bloodRune,3),'y',new ItemStack(WayofTime.alchemicalWizardry.ModItems.lavaCrystal));

        //Blocks
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.potionStation),"x x"," y ","x x",'x', new ItemStack(Items.potionitem),'y',new ItemStack(ModItems.bloodGem));
    }
}
