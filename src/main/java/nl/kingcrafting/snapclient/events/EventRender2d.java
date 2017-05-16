package nl.kingcrafting.snapclient.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Created by Jasper on 16-5-2017.
 */
public class EventRender2d implements Event {

    private FontRenderer fr;
    private ScaledResolution sr;
    private GuiIngame guiIngame;

    public EventRender2d(FontRenderer fr, ScaledResolution sr, GuiIngame guiIngame) {
        this.fr = fr;
        this.sr = sr;
        this.guiIngame = guiIngame;
    }

    public FontRenderer getFr() {
        return fr;
    }

    public ScaledResolution getSr() {
        return sr;
    }

    public GuiIngame getGuiIngame() {
        return guiIngame;
    }
}
