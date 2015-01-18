package com.haito.opbmaddon.network.message;

import com.haito.opbmaddon.tileEntity.TEPotionStation;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageTEPotionStation implements IMessage, IMessageHandler<MessageSoundEvent, IMessage> {

    int blockX, blockY, blockZ;
    byte effect;

    public MessageTEPotionStation(){}

    public MessageTEPotionStation(TEPotionStation tePotionStation){
        this.blockX = tePotionStation.xCoord;
        this.blockY = tePotionStation.yCoord;
        this.blockZ = tePotionStation.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockX = buf.readInt();
        this.blockY = buf.readInt();
        this.blockZ = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(blockX);
        buf.writeInt(blockY);
        buf.writeInt(blockZ);

    }

    @Override
    public IMessage onMessage(MessageSoundEvent message, MessageContext ctx) {
        return null;
    }
}
