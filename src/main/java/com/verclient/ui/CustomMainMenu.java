package com.verclient.ui;

import com.verclient.ui.component.CustomButton;
import com.verclient.ui.component.VerclientTheme;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.network.chat.Component;

public class CustomMainMenu extends Screen {

    public CustomMainMenu() {
        super(Component.translatable("verclient.menu.title"));
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        int spacing = 24;
        int startY = this.height / 4 + 48;
        int x = this.width / 2 - buttonWidth / 2;

        this.addRenderableWidget(new CustomButton(x, startY, buttonWidth, buttonHeight, Component.translatable("menu.singleplayer"), (button) -> {
            this.minecraft.setScreen(new SelectWorldScreen(this));
        }));

        this.addRenderableWidget(new CustomButton(x, startY + spacing, buttonWidth, buttonHeight, Component.translatable("menu.multiplayer"), (button) -> {
            this.minecraft.setScreen(new JoinMultiplayerScreen(this));
        }));

        this.addRenderableWidget(new CustomButton(x, startY + spacing * 2, buttonWidth, buttonHeight, Component.translatable("menu.options"), (button) -> {
            this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
        }));
        
        this.addRenderableWidget(new CustomButton(x, startY + spacing * 3, buttonWidth, buttonHeight, Component.translatable("menu.changelog"), (button) -> {
            this.minecraft.setScreen(new ChangelogScreen(this));
        }));

        this.addRenderableWidget(new CustomButton(x, startY + spacing * 4, buttonWidth, buttonHeight, Component.translatable("menu.quit"), (button) -> {
            this.minecraft.stop();
        }));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        
        // Draw Gradient Background
        guiGraphics.fillGradient(0, 0, this.width, this.height, VerclientTheme.BACKGROUND_START, VerclientTheme.BACKGROUND_END);
        
        // Draw Title
        guiGraphics.drawCenteredString(this.font, "VERCLIENT", this.width / 2, 50, VerclientTheme.TEXT_TITLE);
        guiGraphics.drawCenteredString(this.font, "Version 0.1.0 Alpha", this.width / 2, 65, VerclientTheme.TEXT_SUBTITLE);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
