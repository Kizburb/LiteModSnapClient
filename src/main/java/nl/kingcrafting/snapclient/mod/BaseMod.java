package nl.kingcrafting.snapclient.mod;

import net.minecraft.client.settings.KeyBinding;

/**
 * Created by Jasper on 16-5-2017.
 */
public abstract class BaseMod {

    private String name;

    private String desc;

    private Category category;

    private KeyBinding bind;


    private boolean state;

    public BaseMod(String name, String desc, Category category, KeyBinding bind) {
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.bind = bind;
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public void onToggle(){

    }

    public void toggleModule() {
        this.setState(!this.state);
    }

    public void setState(boolean flag){
        this.state = flag;
        onToggle();
        if(state){
            onEnable();
        } else {
            onDisable();
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public KeyBinding getBind() {
        return bind;
    }

    public void setBind(KeyBinding bind) {
        this.bind = bind;
    }

    public boolean getState() {
        return state;
    }

}
