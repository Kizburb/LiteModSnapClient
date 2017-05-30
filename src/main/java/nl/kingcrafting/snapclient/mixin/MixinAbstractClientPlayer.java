package nl.kingcrafting.snapclient.mixin;
import  com.capesapi.CapesAPI;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

/**
 * Created by Jasper on 19-5-2017.
 */
@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer extends MixinEntityPlayer{

    @Shadow
    @Nullable
    protected abstract NetworkPlayerInfo getPlayerInfo();

    /**
     * @author Hexeption
     */
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    public void IAbstractClientPlayer(World worldIn, GameProfile playerProfile, CallbackInfo callbackInfo) {
        com.capesapi.CapesAPI.loadCape(getGameProfile().getId());
    }


    /**
     * @author Hexeption
     */
    @Overwrite
    @Nullable
    public ResourceLocation getLocationCape() {

        if (com.capesapi.CapesAPI.hasCape(getGameProfile().getId())) {
            return CapesAPI.getCape(getGameProfile().getId());
        } else {
            NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
            return networkplayerinfo == null ? null : networkplayerinfo.getLocationCape();
        }

    }


}
