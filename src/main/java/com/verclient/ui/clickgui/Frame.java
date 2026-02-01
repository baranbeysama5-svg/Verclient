package com.verclient.ui.clickgui;

import com.verclient.ui.component.VerclientTheme;
import net.minecraft.client.gui.GuiGraphics;
import java.util.ArrayList;
import java.util.List;

public class Frame {
    public int x, y, width, height;
    public String title;
    public boolean dragging;
    public int dragX, dragY;
    public boolean open = true;
    public List<Component> components = new ArrayList<>();

    public Frame(int x, int y, int width, int height, String title) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Render Header
        guiGraphics.fill(x, y, x + width, y + height, VerclientTheme.BACKGROUND_START);
        guiGraphics.drawString(net.minecraft.client.Minecraft.getInstance().font, title, x + 5, y + 6, VerclientTheme.TEXT_TITLE);

        if (open) {
            int yOffset = height;
            for (Component comp : components) {
                comp.setOffset(x, y + yOffset);
                comp.render(guiGraphics, mouseX, mouseY, partialTick);
                yOffset += comp.getHeight();
            }
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            dragging = true;
            dragX = (int) (x - mouseX);
            dragY = (int) (y - mouseY);
            return true;
        }
        if (isHovered(mouseX, mouseY) && button == 1) {
            open = !open;
            return true;
        }
        
        if (open) {
            for (Component comp : components) {
                if (comp.mouseClicked(mouseX, mouseY, button)) return true;
            }
        }
        return false;
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        dragging = false;
        if (open) {
            for (Component comp : components) {
                comp.mouseReleased(mouseX, mouseY, button);
            }
        }
    }

    public void mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (dragging) {
            this.x = (int) (mouseX + this.dragX);
            this.y = (int) (mouseY + this.dragY);
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
