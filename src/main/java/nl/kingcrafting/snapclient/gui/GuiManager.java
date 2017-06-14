package nl.kingcrafting.snapclient.gui;

import net.minecraft.client.Minecraft;
import nl.kingcrafting.snapclient.manager.ModManager;
import nl.kingcrafting.snapclient.mod.BaseMod;
import nl.kingcrafting.snapclient.mod.Category;
import org.darkstorm.minecraft.gui.AbstractGuiManager;
import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.ComboBox;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.component.basic.BasicComboBox;
import org.darkstorm.minecraft.gui.component.basic.BasicFrame;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager.HorizontalGridConstraint;
import org.darkstorm.minecraft.gui.listener.ButtonListener;
import org.darkstorm.minecraft.gui.listener.ComboBoxListener;
import org.darkstorm.minecraft.gui.theme.Theme;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class GuiManager extends AbstractGuiManager {

    private class ModuleFrame extends BasicFrame {
        private ModuleFrame() {
        }

        private ModuleFrame(String title) {
            super(title);
        }
    }

    private final AtomicBoolean setup;

    public GuiManager() {
        setup = new AtomicBoolean();
    }

    @Override
    public void setup() {
        if (!setup.compareAndSet(false, true))
            return;

        createTestFrame();

        final Map<Category, ModuleFrame> categoryFrames = new HashMap<Category, ModuleFrame>();
        for (BaseMod module : ModManager.getInstance().mods) {
            if (module.getCategory().equals(Category.GUI))
                continue;
            ModuleFrame frame = categoryFrames.get(module.getCategory());
            if (frame == null) {
                String name = module.getCategory().name().toLowerCase();
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                frame = new ModuleFrame(name);
                frame.setTheme(getTheme());
                frame.setLayoutManager(new GridLayoutManager(1, 0));
                frame.setVisible(true);
                frame.setClosable(false);
                frame.setMinimized(true);
                Dimension defaultDimension = theme.getUIForComponent(frame).getDefaultSize(frame);
                frame.setWidth(defaultDimension.width);
                frame.setHeight(defaultDimension.height);
                frame.layoutChildren();
                addFrame(frame);
                categoryFrames.put(module.getCategory(), frame);
            }
            final BaseMod updateModule = module;
            Button button = new BasicButton(module.getName()){
                public void update(){
                    setText(updateModule.getName());
                    setEnabled(updateModule.getState());
                }
            };
            button.addButtonListener(new ButtonListener() {

                @Override
                public void onButtonPress(Button button) {
                    updateModule.toggleModule();
                    button.setText(updateModule.getName());
                    button.setEnabled(updateModule.getState());
                }
            });
            frame.add(button, HorizontalGridConstraint.FILL);
        }

        // Optional equal sizing and auto-positioning
        resizeComponents();
        Minecraft minecraft = Minecraft.getMinecraft();
        Dimension maxSize = recalculateSizes();
        int offsetX = 5, offsetY = 5;
        int scale = minecraft.gameSettings.guiScale;
        if (scale == 0)
            scale = 1000;
        int scaleFactor = 0;
        while (scaleFactor < scale && minecraft.displayWidth / (scaleFactor + 1) >= 320 && minecraft.displayHeight / (scaleFactor + 1) >= 240)
            scaleFactor++;
        for (Frame frame : getFrames()) {
            frame.setX(offsetX);
            frame.setY(offsetY);
            offsetX += maxSize.width + 5;
            if (offsetX + maxSize.width + 5 > minecraft.displayWidth / scaleFactor) {
                offsetX = 5;
                offsetY += maxSize.height + 5;
            }
        }
    }




    private void createTestFrame() {
        Theme theme = getTheme();
        Frame testFrame = new BasicFrame("Theme Manager WIP!");
        testFrame.setTheme(theme);
        ComboBox comboBox = new BasicComboBox("Simple Theme", "NULL");
        comboBox.addComboBoxListener(new ComboBoxListener() {

            @Override
            public void onComboBoxSelectionChanged(ComboBox comboBox) {
                Theme theme = null;
                switch(comboBox.getSelectedIndex()) {
                    case 0:
                        theme = new SimpleTheme();
                        break;
                    case 1:
                        //theme = new EpicTheme();
                        break;

                    default:
                        return;
                }
                setTheme(theme);
            }
        });
        testFrame.add(comboBox);
        testFrame.setX(50);
        testFrame.setY(50);
        Dimension defaultDimension = theme.getUIForComponent(testFrame).getDefaultSize(testFrame);
        testFrame.setWidth(defaultDimension.width);
        testFrame.setHeight(defaultDimension.height);
        testFrame.setVisible(false);
        testFrame.setClosable(false);
        testFrame.setMinimized(true);
        addFrame(testFrame);
    }

    @Override
    protected void resizeComponents() {
        Theme theme = getTheme();
        Frame[] frames = getFrames();
        Button enable = new BasicButton("Enable");
        Button disable = new BasicButton("Disable");
        Dimension enableSize = theme.getUIForComponent(enable).getDefaultSize(enable);
        Dimension disableSize = theme.getUIForComponent(disable).getDefaultSize(disable);
        int buttonWidth = Math.max(enableSize.width, disableSize.width);
        int buttonHeight = Math.max(enableSize.height, disableSize.height);
        for (Frame frame : frames) {
            if (frame instanceof ModuleFrame) {
                for (Component component : frame.getChildren()) {
                    if (component instanceof Button) {
                        component.setWidth(buttonWidth);
                        component.setHeight(buttonHeight);
                    }
                }
            }
        }
        recalculateSizes();
    }

    private Dimension recalculateSizes() {
        Frame[] frames = getFrames();
        int maxWidth = 0, maxHeight = 0;
        for (Frame frame : frames) {
            Dimension defaultDimension = frame.getTheme().getUIForComponent(frame).getDefaultSize(frame);
            maxWidth = Math.max(maxWidth, defaultDimension.width);
            frame.setHeight(defaultDimension.height);
            if (frame.isMinimized()) {
                for (Rectangle area : frame.getTheme().getUIForComponent(frame).getInteractableRegions(frame))
                    maxHeight = Math.max(maxHeight, area.height);
            } else
                maxHeight = Math.max(maxHeight, defaultDimension.height);
        }
        for (Frame frame : frames) {
            frame.setWidth(maxWidth);
            frame.layoutChildren();
        }
        return new Dimension(maxWidth, maxHeight);
    }
}