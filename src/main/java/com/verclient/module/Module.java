package com.verclient.module;

import net.minecraft.client.Minecraft;

public abstract class Module {
    protected final Minecraft mc = Minecraft.getInstance();
    private final String name;
    private final String description;
    private boolean enabled;
    private int key;
    public java.util.List<com.verclient.module.setting.Setting> settings = new java.util.ArrayList<>();

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
        this.enabled = false;
    }
    
    public void addSettings(com.verclient.module.setting.Setting... settings) {
        this.settings.addAll(java.util.Arrays.asList(settings));
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable(); else onDisable();
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
}
