package nl.kingcrafting.snapclient.gui;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import nl.kingcrafting.snapclient.events.EventRender2d;
import nl.kingcrafting.snapclient.manager.ModManager;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.ttf.TTFManager;

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
        int right = e.getSr().getScaledWidth() - 2;
        int y = 12;

        for(BaseMod mod : ModManager.getInstance().mods){
            if(mod.getState()){

                TTFManager.getInstance().getModListFont().drawString(mod.getName(),
                        right - TTFManager.getInstance().getModListFont().getWidth(mod.getName()), y, 0x156806
                );


                y = y+12;
            }
        }
    }

    public int genColor(){
        Random randomColor = new Random();
        int toReturn = 0x1D9310;
        StringBuilder sb = new StringBuilder();

        sb.append("0x");

        while(sb.length() < 10){
            sb.append(Integer.toHexString(randomColor.nextInt()));
        }

        sb.setLength(8);
        toReturn = Integer.decode(sb.toString()).intValue();

        return toReturn;
    }

}
