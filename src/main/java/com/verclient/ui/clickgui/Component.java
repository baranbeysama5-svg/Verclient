package com.verclient.ui.clickgui;

import net.minecraft.client.gui.GuiGraphics;

public abstract class Component {
    public abstract void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);
    public abstract boolean mouseClicked(double mouseX, double mouseY, int button);
    public abstract void mouseReleased(double mouseX, double mouseY, int button);
    public abstract void setOffset(int x, int y);
    public abstract int getHeight();
}
