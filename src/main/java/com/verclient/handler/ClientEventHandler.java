package com.verclient.handler;

import com.verclient.ui.CustomMainMenu;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "verclient", value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onScreenOpen(ScreenEvent.Opening event) {
        if (event.getScreen() instanceof TitleScreen && !(event.getScreen() instanceof CustomMainMenu)) {
            event.setNewScreen(new CustomMainMenu());
        }
    }
}
