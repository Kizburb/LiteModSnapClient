package nl.kingcrafting.snapclient.mixin;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.entity.EntityPlayerSP;
import nl.kingcrafting.snapclient.events.EventLivingUpdate;
import nl.kingcrafting.snapclient.events.EventTick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Jasper on 16-5-2017.
 */
@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP  {


    @Inject(method = "onLivingUpdate()V" , at = @At("HEAD"))
    public void onLivingUpdate(CallbackInfo callback){
        EventManager.call(new EventLivingUpdate());

    }

    @Inject(method = "onUpdate()V", at = @At("HEAD"))
    public void onUpdate(CallbackInfo callback){
        EventManager.call(new EventTick());
    }



}
