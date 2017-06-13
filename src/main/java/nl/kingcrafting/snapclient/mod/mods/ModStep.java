package nl.kingcrafting.snapclient.mod.mods;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import nl.kingcrafting.snapclient.events.EventLivingUpdate;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.Category;
import nl.kingcrafting.snapclient.utils.KeyBindMaker;
import org.lwjgl.input.Keyboard;

/**
 * Created by Jasper on 16-5-2017.
 */
public class ModStep extends BaseMod {


    public ModStep() {
        super("Step", "Step", Category.MOVEMENT, KeyBindMaker.makeBind("Step" , Keyboard.KEY_P));
    }

    @Override
    public void onEnable() {

    EventManager.register(this);
    }
    @EventTarget
    public void onLivingUpdate(EventLivingUpdate e){
        Minecraft.getMinecraft().player.stepHeight = 2f;
    }


    @Override
    public void onDisable() {

        EventManager.unregister(this);
        Minecraft.getMinecraft().player.stepHeight = 0.6f;
    }
}
