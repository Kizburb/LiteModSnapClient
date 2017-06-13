package nl.kingcrafting.snapclient.utils;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.settings.KeyBinding;
import nl.kingcrafting.snapclient.events.EventKeyBindMake;

/**
 * Created by Jasper on 16-5-2017.
 */
public class KeyBindMaker {

    public static KeyBinding makeBind(String name , int key){
        KeyBinding bind = new KeyBinding(name , key , "SnapClient");

        EventManager.call(new EventKeyBindMake(key , name , bind));
        
        return bind;
    }

}
