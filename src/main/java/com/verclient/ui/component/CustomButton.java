package com.verclient.ui.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class CustomButton extends Button {

    public CustomButton(int x, int y, int width, int height, Component message, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        boolean hovered = this.isHoveredOrFocused();
        int color = hovered ? VerclientTheme.BUTTON_HOVER : VerclientTheme.BUTTON_NORMAL;
        
        guiGraphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, color);
        
        // Border
        guiGraphics.renderOutline(this.getX(), this.getY(), this.width, this.height, 0xFF000000);
        
        // Text
        int textColor = VerclientTheme.TEXT_BUTTON;
        guiGraphics.drawCenteredString(net.minecraft.client.Minecraft.getInstance().font, this.getMessage(), this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, textColor);
    }
}
