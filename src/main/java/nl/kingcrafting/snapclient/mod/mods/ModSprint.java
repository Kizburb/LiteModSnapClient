package nl.kingcrafting.snapclient.mod.mods;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import nl.kingcrafting.snapclient.events.EventLivingUpdate;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.Category;
import nl.kingcrafting.snapclient.utils.KeyBindMaker;
import org.lwjgl.input.Keyboard;

/**
 * Created by Holodeck on 2017-06-17.
 */
public class ModSprint extends BaseMod {

    public ModSprint() {
        super("Sprint", "Automatically makes you sprint when moving forward.", Category.MOVEMENT, KeyBindMaker.makeBind("Sprint", Keyboard.KEY_J));
    }

    Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onEnable() {
        EventManager.register(this);
    }

    @Override
    public void onDisable() {
        EventManager.unregister(this);
    }

    @EventTarget
    public void onLivingUpdate(EventLivingUpdate e) {
        if (getState()) {
            if (mc.player.moveForward != 0 && !mc.player.isCollidedHorizontally) {
                mc.player.setSprinting(true);
            }
        }
    }
}
