package nl.kingcrafting.snapclient.gui;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import nl.kingcrafting.snapclient.events.EventRender2d;
import nl.kingcrafting.snapclient.manager.ModManager;
import nl.kingcrafting.snapclient.mod.BaseMod;

import java.util.Random;

/**
 * Created by Jasper on 16-5-2017.
 */
public class HUD {

    private static HUD instance = new HUD();

    public static HUD getInstance(){
        return instance;
    }

    public void initHud(){
        EventManager.register(this);
    }
    @EventTarget
    public void onRenderHud(EventRender2d e){




        e.getFr().drawString("SnapClient V2!" , 8 , 6 ,0x0C05E8 );
        mods(e);



    }

    private void mods(EventRender2d e) {
        int right = e.getSr().getScaledWidth() - 30;
        int y = 10;

        for(BaseMod mod : ModManager.getInstance().mods){
            if(mod.getState()){
                e.getFr().drawString(mod.getName(), right , y , 0x299614);
                y = y+12;
            }
        }


    }

}
