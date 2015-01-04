package com.haito.opbmaddon.items;

import com.haito.opbmaddon.creativeTab.OPBMTab;
import com.haito.opbmaddon.items.model.OPBMItem;
import com.haito.opbmaddon.refference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Hajto-Lenovo on 2015-01-04.
 */
public class ItemSigilInvisibility extends OPBMItem {
    public ItemSigilInvisibility(){
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Names.Sigils.SigilInvisible);
    }

    //TODO: Invisible for mobs...

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer){
        if(!entityPlayer.isInvisible()){
            entityPlayer.setInvisible(true);
        } else {
            entityPlayer.setInvisible(false);
            entityPlayer.setHealth(entityPlayer.getHealth() - 4);
        }

        return itemStack;
    }
}
