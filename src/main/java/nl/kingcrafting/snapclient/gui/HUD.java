package nl.kingcrafting.snapclient.gui;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import nl.kingcrafting.snapclient.events.EventRender2d;
import nl.kingcrafting.snapclient.manager.ModManager;
import nl.kingcrafting.snapclient.mod.BaseMod;

import java.util.Random;

/**
 * Created by Jasper on 16-5-2017.
 */
public class HUD {

    private static HUD instance = new HUD();

    public static HUD getInstance(){
        return instance;
    }

    private Minecraft mc = Minecraft.getMinecraft();

    private boolean meme = false;

    public void initHud(){
        EventManager.register(this);
    }
    @EventTarget
    public void onRenderHud(EventRender2d e){
        meme = ModManager.getInstance().getStateOfMod("Meme");

        if(meme){
            renderMeme(e);
        }


        ScaledResolution sr = e.getSr();



        e.getFr().drawString("SnapClient V2!" , 8 , 6 ,0x0C05E8 );
        renderMods(e);

    }

    private void renderMeme(EventRender2d e) {
        ScaledResolution sr = e.getSr();

        mc.getTextureManager().bindTexture(new ResourceLocation("textures/blocks/tnt_side.png"));
        Gui.drawScaledCustomSizeModalRect(6, sr.getScaledHeight() - 56,

                0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(), 50, 50, sr.getScaledWidth(), sr.getScaledHeight());


    }

    private void renderMods(EventRender2d e) {
        int right = e.getSr().getScaledWidth() - 30;
        int y = 10;

        for(BaseMod mod : ModManager.getInstance().mods){
            if(mod.getState()){
                e.getFr().drawString(mod.getName(), right , y , 0x299614);
                y = y+12;
            }
        }


    }

}
