package com.verclient.module.setting;

public class NumberSetting extends Setting {
    public double value;
    public double min;
    public double max;
    public double increment;

    public NumberSetting(String name, double value, double min, double max, double increment) {
        super(name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
}
