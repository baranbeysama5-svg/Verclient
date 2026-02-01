package com.verclient.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

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

        this.addRenderableWidget(Button.builder(Component.translatable("menu.singleplayer"), (button) -> {
            this.minecraft.setScreen(new SelectWorldScreen(this));
        }).bounds(this.width / 2 - buttonWidth / 2, startY, buttonWidth, buttonHeight).build());

        this.addRenderableWidget(Button.builder(Component.translatable("menu.multiplayer"), (button) -> {
            this.minecraft.setScreen(new JoinMultiplayerScreen(this));
        }).bounds(this.width / 2 - buttonWidth / 2, startY + spacing, buttonWidth, buttonHeight).build());

        this.addRenderableWidget(Button.builder(Component.translatable("menu.options"), (button) -> {
            this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
        }).bounds(this.width / 2 - buttonWidth / 2, startY + spacing * 2, buttonWidth, buttonHeight).build());

        this.addRenderableWidget(Button.builder(Component.translatable("menu.quit"), (button) -> {
            this.minecraft.stop();
        }).bounds(this.width / 2 - buttonWidth / 2, startY + spacing * 3, buttonWidth, buttonHeight).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        
        // Draw Gradient Background
        guiGraphics.fillGradient(0, 0, this.width, this.height, 0xFF101010, 0xFF252525);
        
        // Draw Title
        guiGraphics.drawCenteredString(this.font, "VERCLIENT", this.width / 2, 50, 0xFF55FFFF);
        guiGraphics.drawCenteredString(this.font, "Version 0.1.0 Alpha", this.width / 2, 65, 0xFFAAAAAA);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
