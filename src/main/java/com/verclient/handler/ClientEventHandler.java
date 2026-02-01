package com.verclient.handler;

import com.verclient.module.ModuleManager;
import com.verclient.ui.CustomMainMenu;
import com.verclient.ui.clickgui.ClickGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "verclient", value = Dist.CLIENT)
public class ClientEventHandler {

    public static final KeyMapping CLICK_GUI_KEY = new KeyMapping("key.verclient.clickgui", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_BRACKET, "category.verclient");
    private static ClickGUI clickGUI;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(CLICK_GUI_KEY);
    }

    @SubscribeEvent
    public static void onScreenOpen(ScreenEvent.Opening event) {
        if (event.getScreen() instanceof TitleScreen && !(event.getScreen() instanceof CustomMainMenu)) {
            event.setNewScreen(new CustomMainMenu());
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getInstance().player != null) {
            if (CLICK_GUI_KEY.consumeClick()) {
                if (clickGUI == null) clickGUI = new ClickGUI();
                
                if (Minecraft.getInstance().screen instanceof ClickGUI) {
                    Minecraft.getInstance().setScreen(null);
                } else {
                    Minecraft.getInstance().setScreen(clickGUI);
                }
            }
        }
    }
}
