package com.haito.opbmaddon.network.message;

import com.haito.opbmaddon.client.particles.ClientParticleHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class MessageParticleEvent implements IMessage, IMessageHandler<MessageParticleEvent, IMessage> {
    private long mostSigUUID, leastSigUUID;
    private String particle;
    private float xCoord, yCoord, zCoord , xMotion, yMotion, zMotion;

    public MessageParticleEvent(){}

    public MessageParticleEvent(String particleName, float xCoord, float yCoord, float zCoord, float xMotion, float yMotion, float zMotion){
        this.leastSigUUID = 0;
        this.mostSigUUID = 0;

        this.particle = particleName;

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;

        this.xMotion = xMotion;
        this.yMotion = yMotion;
        this.zMotion = zMotion;

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

        this.xMotion = buf.readFloat();
        this.yMotion = buf.readFloat();
        this.zMotion = buf.readFloat();
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

        buf.writeFloat(xMotion);
        buf.writeFloat(yMotion);
        buf.writeFloat(zMotion);
    }

    @Override
    public IMessage onMessage(MessageParticleEvent event, MessageContext ctx) {
        UUID originUUID = new UUID(event.mostSigUUID, event.leastSigUUID);
        ClientParticleHandler.showVanillaParticle(event.particle,event.xCoord,event.yCoord,event.zCoord,event.xMotion,event.yMotion,event.zMotion);
        return null;
    }
}
