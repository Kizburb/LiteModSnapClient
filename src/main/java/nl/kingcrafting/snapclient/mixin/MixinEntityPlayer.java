package nl.kingcrafting.snapclient.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by Jasper on 19-5-2017.
 */
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {


    @Shadow
    public abstract GameProfile getGameProfile();


}
