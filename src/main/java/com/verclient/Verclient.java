package com.verclient;

import com.mojang.logging.LogUtils;
import com.verclient.module.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Verclient.MOD_ID)
public class Verclient {
    public static final String MOD_ID = "verclient";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Verclient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        
        // Initialize Modules
        ModuleManager.init();
        MinecraftForge.EVENT_BUS.register(new ModuleManager());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Verclient Initialized");
    }
}
