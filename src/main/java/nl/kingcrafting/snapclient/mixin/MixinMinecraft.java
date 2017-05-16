package nl.kingcrafting.snapclient.mixin;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import nl.kingcrafting.snapclient.events.EventGameStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Jasper on 16-5-2017.
 */
@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow
    static Minecraft theMinecraft;

    @Inject(method = "init()V" , at = @At("HEAD"))
    public void MixinInit(CallbackInfo info){
        EventManager.call(new EventGameStart(theMinecraft));
    }


}
