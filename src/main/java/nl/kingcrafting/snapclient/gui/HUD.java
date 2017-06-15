package nl.kingcrafting.snapclient.gui;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.sun.javafx.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import nl.kingcrafting.snapclient.events.EventRender2d;
import nl.kingcrafting.snapclient.manager.ModManager;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.ttf.TTFManager;


/**
 * Created by Jasper on 16-5-2017.
 */
public class HUD {
    public boolean goingDown = true;

    private static HUD instance = new HUD();

    public static HUD getInstance(){
        return instance;
    }

    private static float mouseX = 1;


    private Minecraft mc = Minecraft.getMinecraft();

    private boolean meme = false;

    public void initHud(){
        EventManager.register(this);
    }

    @EventTarget
    public void onRenderHud(EventRender2d e){

        if (goingDown) {
            mouseX--;
            if (mouseX == -100) {
                goingDown = false;
            }
        } else {
            mouseX++;
            if (mouseX == 100) {
                goingDown = true;
            }
        }

        drawEntityOnScreen(20, 80, 30, mouseX, 0f, Minecraft.getMinecraft().player, true);

        int color = getColor((System.currentTimeMillis() / 20) % 360);
        meme = ModManager.getInstance().getStateOfMod("Meme");

        if(meme){
            renderMeme(e);
        }

        ScaledResolution sr = e.getSr();
        e.getFr().drawString("SnapClient V2" , 8 , 6 ,0x0C05E8 );

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
        int color = getColor((System.currentTimeMillis() / 20) % 360);
        for(BaseMod mod : ModManager.getInstance().mods){
            if(mod.getState()){

                TTFManager.getInstance().getModListFont().drawString(mod.getName(),
                        right - TTFManager.getInstance().getModListFont().getWidth(mod.getName()), y, color
                );

                y = y+12;
            }
        }
    }

    private int getColor(double hue)
    {
        double[] c = Utils.HSBtoRGB(hue, 1, 1);
        return (0xFF << 24) | ((int)(c[0] * 255) << 16) | ((int)(c[1] * 255) << 8) | (((int)c[2] * 255));
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY,
                                          EntityLivingBase ent, boolean rotate) {
        GlStateManager.enableDepth();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        if (rotate) {
            ent.renderYawOffset = (float) Math.atan((double) ((posX - mouseX) / 40.0F)) * 20.0F;
            ent.rotationYaw = (float) Math.atan((double) ((posX - mouseX) / 40.0F)) * 40.0F;
            GlStateManager.rotate(-((float) Math.atan((double) ((posY - mouseY) / 40.0F) - ent.getEyeHeight())) * 20.0F,
                    1.0F, 0.0F, 0.0F);
            ent.rotationPitch = -((float) Math.atan((double) ((posY - mouseY) / 40.0F) - ent.getEyeHeight())) * 20.0F;
        } else {
            ent.renderYawOffset = 0;
            ent.rotationYaw = 0;
            ent.rotationPitch = -0;
        }

        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        // rendermanager.func_188391_a(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F,
        // false);
        rendermanager.doRenderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.disableDepth();
    }
}
