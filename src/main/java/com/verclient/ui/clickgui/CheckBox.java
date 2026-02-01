package com.verclient.ui.clickgui;

import com.verclient.module.setting.BooleanSetting;
import com.verclient.ui.component.VerclientTheme;
import net.minecraft.client.gui.GuiGraphics;

public class CheckBox extends Component {
    private final BooleanSetting setting;
    private final Frame parent;
    private int x, y, width, height;

    public CheckBox(BooleanSetting setting, Frame parent) {
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
        guiGraphics.fill(x, y, x + width, y + height, VerclientTheme.BUTTON_NORMAL);
        guiGraphics.drawString(net.minecraft.client.Minecraft.getInstance().font, setting.name, x + 5, y + 3, VerclientTheme.TEXT_SUBTITLE);
        
        // Checkbox box
        int boxSize = 10;
        int boxX = x + width - 15;
        int boxY = y + 2;
        guiGraphics.fill(boxX, boxY, boxX + boxSize, boxY + boxSize, 0xFF000000);
        if (setting.enabled) {
            guiGraphics.fill(boxX + 2, boxY + 2, boxX + boxSize - 2, boxY + boxSize - 2, 0xFF55FFFF);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            setting.toggle();
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {}

    @Override
    public int getHeight() {
        return height;
    }

    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
