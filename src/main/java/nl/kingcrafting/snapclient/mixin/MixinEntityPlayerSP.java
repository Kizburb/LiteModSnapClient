package nl.kingcrafting.snapclient.mixin;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.DamageSource;
import nl.kingcrafting.snapclient.events.EventLivingUpdate;
import nl.kingcrafting.snapclient.events.EventPlayerDamage;
import nl.kingcrafting.snapclient.events.EventTick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Created by Jasper on 16-5-2017.
 */
@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP  {


    @Inject(method = "attackEntityFrom", at = @At("HEAD") , cancellable = true)
    public void attackEntityFrom(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        EventPlayerDamage event = new EventPlayerDamage(source , amount);
        EventManager.call(event);
    }

    @Inject(method = "onLivingUpdate()V" , at = @At("HEAD"))
    public void onLivingUpdate(CallbackInfo callback){
        EventManager.call(new EventLivingUpdate());
    }

    @Inject(method = "onUpdate()V", at = @At("HEAD"))
    public void onUpdate(CallbackInfo callback){
        EventManager.call(new EventTick());
    }

}
