package com.haito.opbmaddon.items.model;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Hajto-Lenovo on 2015-01-05.
 */
public class OPBMEnergyItem extends OPBMItem implements IBindable{

    private int energyUsed = 0;

    public OPBMEnergyItem(){
        super();
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
}
