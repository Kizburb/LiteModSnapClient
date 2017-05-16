package nl.kingcrafting.snapclient.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.Minecraft;

/**
 * Created by Jasper on 16-5-2017.
 */
public class EventGameStart implements Event {

    private Minecraft mc;

    public EventGameStart(Minecraft minecraft) {
        this.mc = minecraft;


    }

    public Minecraft getMc() {
        return mc;
    }

}
