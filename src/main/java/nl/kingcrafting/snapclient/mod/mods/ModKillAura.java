package nl.kingcrafting.snapclient.mod.mods;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import nl.kingcrafting.snapclient.events.EventLivingUpdate;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.Category;
import nl.kingcrafting.snapclient.utils.KeyBindMaker;
import nl.kingcrafting.snapclient.utils.Timer;
import org.lwjgl.input.Keyboard;

import java.util.Iterator;

/**
 * Created by Jasper on 22-5-2017.
 */
public class ModKillAura extends BaseMod {

    private Timer timer = new Timer();
    private Minecraft mc = Minecraft.getMinecraft();


    public ModKillAura(String name, String desc, Category category, KeyBinding bind) {
        super("KillAura", "REKT The N00BS", Category.COMBAT, KeyBindMaker.makeBind("KillAura" , Keyboard.KEY_K));
    }

    @Override
    public void onEnable() {
        EventManager.register(this);
    }

    @Override
    public void onDisable() {
    EventManager.unregister(this);
    }

    @EventTarget
    public void onLivingUpdate(EventLivingUpdate e){

    if(timer.hasTimePassed((long) 690)){

            timer.reset();

        for(Iterator<Entity> entitys = mc.world.loadedEntityList.iterator(); entitys.hasNext(); ){

            while (entitys.hasNext()){

            }

        }

    }

    }
}
