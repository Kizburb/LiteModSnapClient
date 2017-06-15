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
 * Created by Jasper on 14-6-2017.
 */
public class ModAirJump extends BaseMod {


    public ModAirJump() {
        super("AirJump", "Jump in the Air", Category.MOVEMENT, KeyBindMaker.makeBind("AirJump" , Keyboard.KEY_C));
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
    public void onLivingUpate(EventLivingUpdate e){
        if(Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()){
            if(Minecraft.getMinecraft().player.isAirBorne){
                Minecraft.getMinecraft().player.jump();
            }
        }
    }
}