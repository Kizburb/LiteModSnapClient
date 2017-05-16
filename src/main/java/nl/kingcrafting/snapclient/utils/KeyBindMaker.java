package nl.kingcrafting.snapclient.utils;

import net.minecraft.client.settings.KeyBinding;

/**
 * Created by Jasper on 16-5-2017.
 */
public class KeyBindMaker {

    public static KeyBinding makeBind(String name , int key){
        KeyBinding bind = new KeyBinding(name , key , "SnapClient");
        return bind;
    }

}
