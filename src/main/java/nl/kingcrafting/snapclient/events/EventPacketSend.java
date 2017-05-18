package nl.kingcrafting.snapclient.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.network.Packet;

/**
 * Created by Jasper on 18-5-2017.
 */
public class EventPacketSend implements Event {

    private Packet packet;

    public EventPacketSend(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }
}
