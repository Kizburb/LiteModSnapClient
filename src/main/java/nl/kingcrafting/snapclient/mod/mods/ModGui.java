package nl.kingcrafting.snapclient.mod.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import nl.kingcrafting.snapclient.LiteModSnapClient;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.Category;
import nl.kingcrafting.snapclient.utils.KeyBindMaker;
import org.lwjgl.input.Keyboard;

/**
 * Created by Jasper on 13-6-2017.
 */
public class ModGui extends BaseMod {

    public ModGui() {
        super("ClickGui", "", Category.GUI, KeyBindMaker.makeBind("ClickGui" , Keyboard.KEY_RSHIFT));
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().displayGuiScreen(LiteModSnapClient.getInstance().getGui());
        toggleModule();
    }

    @Override
    public void onDisable() {

    }
}
