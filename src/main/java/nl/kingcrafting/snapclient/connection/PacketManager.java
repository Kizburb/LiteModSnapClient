package nl.kingcrafting.snapclient.connection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;

/**
 * Created by Jasper on 13-6-2017.
 */
public class PacketManager {

    public void sendPacket(Packet<?> packetToSend){
        EntityPlayerSP player = Minecraft.getMinecraft().player;

           NetHandlerPlayClient netHandlerPlayClient = player.connection;

           netHandlerPlayClient.sendPacket(packetToSend);


    }

}
