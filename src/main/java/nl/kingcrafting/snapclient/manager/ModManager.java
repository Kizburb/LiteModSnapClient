package nl.kingcrafting.snapclient.manager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.mumfrey.liteloader.core.LiteLoader;
import com.sun.org.apache.xpath.internal.operations.Mod;
import nl.kingcrafting.snapclient.events.EventTick;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.mods.ModFlight;
import nl.kingcrafting.snapclient.mod.mods.ModStep;

import java.util.ArrayList;

/**
 * Created by Jasper on 16-5-2017.
 */
public class ModManager {



    private static ModManager instance = new ModManager();
    public static ModManager getInstance(){
        return instance;
    }

    public ArrayList<BaseMod> mods = new ArrayList<BaseMod>();


    public void initModManager(){
        EventManager.register(this);

        addMods();
        registerBinds();
    }

    private void addMods() {
    ModManager.instance.mods.add(new ModStep());
    ModManager.getInstance().mods.add(new ModFlight());
    }
    private void registerBinds(){

        for(BaseMod mod : ModManager.getInstance().mods){
            LiteLoader.getInput().registerKeyBinding(mod.getBind());
        }

    }
    @EventTarget
    public void onTick(EventTick e){
        for(BaseMod mod : ModManager.getInstance().mods){
            if(mod.getBind().isPressed()){
                mod.toggleModule();
            }
        }


    }


}
