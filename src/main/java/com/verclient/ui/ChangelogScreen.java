package com.verclient.ui;

import com.verclient.ui.component.CustomButton;
import com.verclient.ui.component.VerclientTheme;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.ArrayList;
import java.util.List;

public class ChangelogScreen extends Screen {

    private final Screen parent;
    private final List<String> lines = new ArrayList<>();

    public ChangelogScreen(Screen parent) {
        super(Component.translatable("menu.changelog"));
        this.parent = parent;
        
        // Hardcoded changelog for 0.1.0 Alpha
        lines.add("§bVerclient 0.1.0 Alpha");
        lines.add("§7Released: 2026-02-01");
        lines.add("");
        lines.add("§6Features:");
        lines.add("§f- New Custom Main Menu (Modern Dark UI)");
        lines.add("§f- Added KillAura Module (configurable)");
        lines.add("§f- Added Telekinesis Module (Right-click to throw)");
        lines.add("§f- Localization support (English/Turkish)");
        lines.add("");
        lines.add("§6Fixes:");
        lines.add("§f- Initial release structure");
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        
        this.addRenderableWidget(new CustomButton(this.width / 2 - buttonWidth / 2, this.height - 40, buttonWidth, buttonHeight, Component.translatable("gui.back"), (button) -> {
            this.minecraft.setScreen(this.parent);
        }));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        guiGraphics.fillGradient(0, 0, this.width, this.height, VerclientTheme.BACKGROUND_START, VerclientTheme.BACKGROUND_END);
        
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, VerclientTheme.TEXT_TITLE);
        
        int y = 50;
        for (String line : lines) {
            guiGraphics.drawString(this.font, line, this.width / 2 - 100, y, 0xFFFFFFFF);
            y += 12;
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
