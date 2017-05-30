package nl.kingcrafting.snapclient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;

/**
 * Created by Jasper on 22-5-2017.
 */
public class PlayerUtils {
    private static Minecraft mc = Minecraft.getMinecraft();


    public static void swingHandClient(EnumHand e){
        mc.player.swingArm(e);
    }
    public static void swingHandPacket(EnumHand e){
        mc.player.connection.sendPacket(new CPacketAnimation(e));
    }

}




