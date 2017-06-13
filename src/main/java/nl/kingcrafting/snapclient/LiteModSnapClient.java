package nl.kingcrafting.snapclient;


import com.darkmagician6.eventapi.EventManager;
import com.mojang.authlib.GameProfile;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import net.minecraft.client.Minecraft;
import nl.kingcrafting.snapclient.gui.HUD;
import nl.kingcrafting.snapclient.manager.ModManager;

import java.io.File;


@ExposableOptions(strategy = ConfigStrategy.Versioned , filename = "snapclient.json")
public class LiteModSnapClient implements Tickable {



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
}
