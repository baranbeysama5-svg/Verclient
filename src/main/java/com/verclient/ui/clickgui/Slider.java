package com.verclient.ui.clickgui;

import com.verclient.module.setting.NumberSetting;
import com.verclient.ui.component.VerclientTheme;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class Slider extends Component {
    private final NumberSetting setting;
    private final Frame parent;
    private int x, y, width, height;
    private boolean dragging = false;

    public Slider(NumberSetting setting, Frame parent) {
        this.setting = setting;
        this.parent = parent;
        this.width = parent.width;
        this.height = 14;
    }

    @Override
    public void setOffset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (dragging) {
            double diff = Math.min(width, Math.max(0, mouseX - x));
            double min = setting.min;
            double max = setting.max;
            setting.value = diff == 0 ? min : roundToPlace(diff / width * (max - min) + min, 2);
        }

        guiGraphics.fill(x, y, x + width, y + height, VerclientTheme.BUTTON_NORMAL);
        
        // Slider bar
        double percent = (setting.value - setting.min) / (setting.max - setting.min);
        guiGraphics.fill(x, y + height - 2, x + (int)(width * percent), y + height, 0xFF55FFFF);
        
        guiGraphics.drawString(net.minecraft.client.Minecraft.getInstance().font, setting.name + ": " + setting.value, x + 5, y + 3, VerclientTheme.TEXT_SUBTITLE);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            dragging = true;
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    private double roundToPlace(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
