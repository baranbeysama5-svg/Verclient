package com.verclient.module.setting;

public class BooleanSetting extends Setting {
    public boolean enabled;

    public BooleanSetting(String name, boolean enabled) {
        super(name);
        this.enabled = enabled;
    }
    
    public void toggle() {
        this.enabled = !this.enabled;
    }
}
