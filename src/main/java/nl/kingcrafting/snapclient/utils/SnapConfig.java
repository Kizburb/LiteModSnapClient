package nl.kingcrafting.snapclient.utils;

import nl.kingcrafting.snapclient.manager.ModManager;
import nl.kingcrafting.snapclient.mod.BaseMod;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Jasper on 18-5-2017.
 */
public class SnapConfig {




    private static SnapConfig instance = new SnapConfig();
    public static SnapConfig getInstance(){
        return instance;
    }


    public HashMap<String, Object> config = new HashMap<String, Object>();

    public Properties properties = new Properties();



    public void init(){

        for(BaseMod m : ModManager.getInstance().mods){
            config.put(m.getName() , m.getState());
        }


        saveConfig();

    }
    public void loadConfig(){

    }
    public void saveConfig(){

    for(Map.Entry<String , Object> entry : config.entrySet()){
        properties.put(entry.getKey(), entry.getValue());
    }
        try {
            properties.store(new FileOutputStream("snap.properties") , null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void initTheConfig(){



    }









}
