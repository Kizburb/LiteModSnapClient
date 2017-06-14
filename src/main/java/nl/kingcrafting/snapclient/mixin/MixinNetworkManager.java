package nl.kingcrafting.snapclient.mixin;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import nl.kingcrafting.snapclient.events.EventPacketSend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Jasper on 18-5-2017.
 */
@Mixin(NetworkManager.class)
public class MixinNetworkManager {


    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V" , at = @At("HEAD"))
    public void MixinSendPacket(Packet<?> packet , CallbackInfo callback){
        EventManager.call(new EventPacketSend(packet));
    }
}
