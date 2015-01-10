package com.haito.opbmaddon.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageParticleEvent implements IMessage, IMessageHandler<MessageParticleEvent, IMessage> {
    private long mostSigUUID, leastSigUUID;
    private String particle;
    private float xCoord, yCoord, zCoord;

    public MessageParticleEvent(){
        this.leastSigUUID = 0;
        this.mostSigUUID = 0;

        this.particle = particle;

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.leastSigUUID = buf.readLong();
        this.mostSigUUID = buf.readLong();

        int particleLength = buf.readInt();
        this.particle = new String(buf.readBytes(particleLength).array());

        this.xCoord = buf.readFloat();
        this.yCoord = buf.readFloat();
        this.zCoord = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(leastSigUUID);
        buf.writeLong(mostSigUUID);

        buf.writeInt(particle.length());
        buf.writeBytes(particle.getBytes());

        buf.writeFloat(xCoord);
        buf.writeFloat(yCoord);
        buf.writeFloat(zCoord);
    }

    @Override
    public IMessage onMessage(MessageParticleEvent message, MessageContext ctx) {
        //TODO:Proxy dohicky, i go to sleep right now.
        return null;
    }
}
