package nl.kingcrafting.snapclient.mod.mods;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.CPacketPlayer;
import nl.kingcrafting.snapclient.events.EventLivingUpdate;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.Category;
import nl.kingcrafting.snapclient.utils.KeyBindMaker;
import org.lwjgl.input.Keyboard;

/**
 * Created by Jasper on 16-5-2017.
 */
public class ModFlight extends BaseMod {
    Minecraft mc = Minecraft.getMinecraft();

    public ModFlight() {
        super("Flight", "Fly Like A Bird", Category.MOVEMENT, KeyBindMaker.makeBind("Flight", Keyboard.KEY_F));
    }

    @Override
    public void onEnable() {
        EventManager.register(this);
    }

    @Override
    public void onDisable() {
    EventManager.unregister(this);
        mc.player.capabilities.isFlying = false;
    }
    @EventTarget
    public void onLivingUpdate(EventLivingUpdate e){
        if(getState()){
            if(!mc.player.capabilities.isCreativeMode){
                mc.player.capabilities.isFlying = true;
                if(mc.gameSettings.keyBindJump.isPressed()){
                    mc.player.motionY += 0.2;
                } else if(mc.gameSettings.keyBindSneak.isPressed()){
                    mc.player.motionY -=0.2;
                }

            }
            if(mc.player.fallDistance >2f){
                mc.player.connection.sendPacket(new CPacketPlayer(true));
            }
        }
  }
}
