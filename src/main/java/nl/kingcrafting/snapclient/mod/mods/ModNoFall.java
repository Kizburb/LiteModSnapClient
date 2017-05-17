package nl.kingcrafting.snapclient.mod.mods;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;
import nl.kingcrafting.snapclient.events.EventLivingUpdate;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.Category;
import nl.kingcrafting.snapclient.utils.KeyBindMaker;
import org.lwjgl.input.Keyboard;

/**
 * Created by Jasper on 16-5-2017.
 */
public class ModNoFall extends BaseMod {

    Minecraft mc = Minecraft.getMinecraft();

    public ModNoFall() {
        super("NoFall", "NoFall", Category.MOVEMENT, KeyBindMaker.makeBind("NoFall" , Keyboard.KEY_I));
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
    public void onLivingUpdate(EventLivingUpdate e) {
        if (getState()) {
            if (mc.player.fallDistance > 2f) {
                mc.player.connection.sendPacket(new CPacketPlayer(true));
            }
        }
    }
}

