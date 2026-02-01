package com.verclient.ui.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class AnimatedBackground {
    
    public static void render(GuiGraphics guiGraphics, int width, int height, float partialTick) {
        // Simple static gradient for now, can add animation logic if tick passed
        guiGraphics.fillGradient(0, 0, width, height, VerclientTheme.BACKGROUND_START, VerclientTheme.BACKGROUND_END);
    }
}
