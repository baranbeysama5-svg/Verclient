package com.verclient.ui.clickgui;

import com.verclient.module.Module;
import com.verclient.module.ModuleManager;
import com.verclient.module.setting.BooleanSetting;
import com.verclient.module.setting.NumberSetting;
import com.verclient.module.setting.Setting;
import com.verclient.ui.component.VerclientTheme;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {

    private final List<Frame> frames = new ArrayList<>();

    public ClickGUI() {
        super(Component.literal("ClickGUI"));
        
        // Simple organization: All modules in one "Modules" frame for now
        Frame moduleFrame = new Frame(20, 20, 150, 20, "Modules");
        for (Module m : ModuleManager.modules) {
            moduleFrame.addComponent(new ModuleButton(m, moduleFrame));
        }
        frames.add(moduleFrame);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        
        for (Frame frame : frames) {
            frame.render(guiGraphics, mouseX, mouseY, partialTick);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            if (frame.mouseClicked(mouseX, mouseY, button)) return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        for (Frame frame : frames) {
            frame.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
