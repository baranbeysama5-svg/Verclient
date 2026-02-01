package com.verclient.module;

import com.verclient.module.impl.KillAura;
import com.verclient.module.impl.Telekinesis;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static final List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new KillAura());
        modules.add(new Telekinesis());
    }

    public static <T extends Module> T getModule(Class<T> clazz) {
        for (Module m : modules) {
            if (clazz.isInstance(m)) return clazz.cast(m);
        }
        return null;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side.isClient()) {
            for (Module m : modules) {
                if (m.isEnabled()) {
                    m.onTick();
                }
            }
        }
    }
}
