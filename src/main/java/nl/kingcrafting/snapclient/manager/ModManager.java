package nl.kingcrafting.snapclient.manager;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.mumfrey.liteloader.core.LiteLoader;
import net.minecraft.client.settings.KeyBinding;
import nl.kingcrafting.snapclient.events.EventTick;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.mods.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jasper on 16-5-2017.
 */
public class ModManager {

    private static ModManager instance = new ModManager();

    public static ModManager getInstance(){
        return instance;
    }

    public ArrayList<BaseMod> mods = new ArrayList<BaseMod>();

    public HashMap<BaseMod , KeyBinding> binds = new HashMap<BaseMod, KeyBinding>();

    public void initModManager(){
        EventManager.register(this);

        addMods();
        registerBinds();
    }

    private void addMods() {
    ModManager.getInstance().mods.add(new ModStep());
    ModManager.getInstance().mods.add(new ModFlight());
    ModManager.getInstance().mods.add(new ModNoFall());
    ModManager.getInstance().mods.add(new ModMeme());
    ModManager.getInstance().mods.add(new ModKillAura());
    ModManager.getInstance().mods.add(new ModGui());
    ModManager.getInstance().mods.add(new ModAirJump());
    }
    private void registerBinds(){

        for(BaseMod mod : ModManager.getInstance().mods){
            LiteLoader.getInput().registerKeyBinding(mod.getBind());
        }
        for(BaseMod mod : ModManager.getInstance().mods){
            if(!binds.containsKey(mod)){
                binds.put(mod , mod.getBind());
            }
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

    public boolean getStateOfMod(String name){
        boolean state = false;

        for(BaseMod mod: ModManager.getInstance().mods){
            if(mod.getName().equals(name)){
                state = mod.getState();
            }
        }
        return state;
    }

    public BaseMod getMod(String name) throws Exception {
        BaseMod m = null;
        for (BaseMod mod : ModManager.getInstance().mods) {
            if (mod.getName().equals(name)) {
                m = mod;
            }
        }

        return m;
    }
}
