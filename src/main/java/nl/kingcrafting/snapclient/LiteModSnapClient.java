package nl.kingcrafting.snapclient;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.util.DamageSource;
import nl.kingcrafting.snapclient.events.EventPlayerDamage;
import nl.kingcrafting.snapclient.events.EventTick;
import nl.kingcrafting.snapclient.gui.HUD;
import nl.kingcrafting.snapclient.manager.ModManager;
import nl.kingcrafting.snapclient.utils.SnapConfig;

import java.io.File;


@ExposableOptions(strategy = ConfigStrategy.Versioned , filename = "snapclient.json")
public class LiteModSnapClient implements Tickable {


    public LiteModSnapClient(){
        EventManager.register(this);
    }



    @Override
    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {

    }



    @Override
    public String getVersion() {
        return "2.0";
    }

    @Override
    public void init(File configPath) {

        ModManager.getInstance().initModManager();
        HUD.getInstance().initHud();


        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {

                SnapConfig.getInstance().init();

            }
        });

    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {

    }

    @Override
    public String getName() {
        return "SnapClient";
    }
}
