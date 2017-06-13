package nl.kingcrafting.snapclient;


import com.darkmagician6.eventapi.EventManager;
import com.mojang.authlib.GameProfile;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import net.minecraft.client.Minecraft;
import nl.kingcrafting.snapclient.gui.HUD;
import nl.kingcrafting.snapclient.manager.ModManager;
import nl.kingcrafting.snapclient.gui.GuiManager;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;

import java.io.File;


@ExposableOptions(strategy = ConfigStrategy.Versioned , filename = "snapclient.json")
public class LiteModSnapClient implements Tickable {

    private static GuiManager guiManager;
    private static GuiManagerDisplayScreen gui;
    private static LiteModSnapClient instance;

    public GuiManager getGuiManager() {
        if (guiManager == null) {
            guiManager = new GuiManager();
            guiManager.setTheme(new SimpleTheme());
            guiManager.setup();
            guiManager.update();
        }
        return guiManager;
    }

    public GuiManagerDisplayScreen getGui() {
        if (gui == null) {
            gui = new GuiManagerDisplayScreen(getGuiManager());
        }
        return gui;
    }




        public LiteModSnapClient(){
        EventManager.register(this);
    }



    @Override
    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
        //This method its needed for LiteLoader
    }



    @Override
    public String getVersion() {
        return "2.0";
    }

    @Override
    public void init(File configPath) {
        instance = this;
        ModManager.getInstance().initModManager();
        HUD.getInstance().initHud();

    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {
        //This method its needed for LiteLoader
    }

    @Override
    public String getName() {
        return "SnapClient";
    }

    public static LiteModSnapClient getInstance(){
            return instance;
    }
}
