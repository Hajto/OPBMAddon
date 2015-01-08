package com.haito.opbmaddon.handler;

import com.haito.opbmaddon.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

/**
 * Created by Haito on 2015-01-07.
 */
public class ItemActivatedHandler {
    public ItemActivatedHandler(){

    }

    @SubscribeEvent
    public void onSleepyTime(PlayerSleepInBedEvent event)
    {
        LogHelper.info("Booker " + event.x + " " + event.z);
    }

    @SubscribeEvent
    public void thing(PlayerUseItemEvent.Stop e){
        LogHelper.info("Stop Click " + e.entityPlayer.getHeldItem());
    }

    @SubscribeEvent
    public void anotherThing(PlayerUseItemEvent.Start event){
        LogHelper.info("Start Click");
        LogHelper.info(event.entityPlayer.getBedLocation(0));
    }
}
