package nl.kingcrafting.snapclient.mod.mods;

import net.minecraft.client.settings.KeyBinding;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.Category;
import nl.kingcrafting.snapclient.utils.KeyBindMaker;
import org.lwjgl.input.Keyboard;

/**
 * Created by Jasper on 17-5-2017.
 */
public class ModMeme extends BaseMod {



    public ModMeme() {
        super("Meme", "Meme", Category.GUI, KeyBindMaker.makeBind("meme" , Keyboard.KEY_NUMPAD0));
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
