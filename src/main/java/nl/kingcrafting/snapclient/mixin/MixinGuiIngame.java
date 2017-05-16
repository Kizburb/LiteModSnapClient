package nl.kingcrafting.snapclient.mixin;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import nl.kingcrafting.snapclient.events.EventRender2d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Jasper on 16-5-2017.
 */
@Mixin(GuiIngame.class)

public class MixinGuiIngame {

    @Shadow
    Minecraft mc;

    @Inject(method = "renderGameOverlay" , at = @At("HEAD"))
    public void onRenderGameOverlay(CallbackInfo call){

        ScaledResolution mixSR = new ScaledResolution(mc);

        FontRenderer mixFR = Minecraft.getMinecraft().fontRendererObj;

        EventManager.call(new EventRender2d(mixFR , mixSR , mc.ingameGUI));

    }

}
