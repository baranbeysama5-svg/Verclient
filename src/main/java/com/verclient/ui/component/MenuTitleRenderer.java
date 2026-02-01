package com.verclient.ui.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.Font;

public class MenuTitleRenderer {
    public static void render(GuiGraphics guiGraphics, Font font, int width, String title, String version) {
        guiGraphics.drawCenteredString(font, title, width / 2, 50, VerclientTheme.TEXT_TITLE);
        guiGraphics.drawCenteredString(font, version, width / 2, 65, VerclientTheme.TEXT_SUBTITLE);
    }
}
