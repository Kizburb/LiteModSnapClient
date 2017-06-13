package nl.kingcrafting.snapclient.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.settings.KeyBinding;

/**
 * Created by Jasper on 13-6-2017.
 */
public class EventKeyBindMake implements Event {

    private int key;
    private String name;
    private KeyBinding bind;

    public EventKeyBindMake(int key , String name , KeyBinding bind){
       this.key = key;
       this.name = name;
       this.bind = bind;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public KeyBinding getBind() {
        return bind;
    }
}
