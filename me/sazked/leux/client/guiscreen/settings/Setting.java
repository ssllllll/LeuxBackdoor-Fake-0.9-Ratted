// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.settings;

import java.util.List;
import me.sazked.leux.client.modules.Module;

public class Setting
{
    private Module master;
    private String name;
    private String tag;
    private boolean button;
    private List<String> combobox;
    private String current;
    private String label;
    private double slider;
    private double min;
    private double max;
    private String type;
    
    public Setting(final Module master, final String name, final String tag, final boolean value) {
        this.master = master;
        this.name = name;
        this.tag = tag;
        this.button = value;
        this.type = "button";
    }
    
    public Setting(final Module master, final String name, final String tag, final List<String> values, final String value) {
        this.master = master;
        this.name = name;
        this.tag = tag;
        this.combobox = values;
        this.current = value;
        this.type = "combobox";
    }
    
    public Setting(final Module master, final String name, final String tag, final String value) {
        this.master = master;
        this.name = name;
        this.tag = tag;
        this.label = value;
        this.type = "label";
    }
    
    public Setting(final Module master, final String name, final String tag, final double value, final double min, final double max) {
        this.master = master;
        this.name = name;
        this.tag = tag;
        this.slider = value;
        this.min = min;
        this.max = max;
        this.type = "doubleslider";
    }
    
    public Setting(final Module master, final String name, final String tag, final int value, final int min, final int max) {
        this.master = master;
        this.name = name;
        this.tag = tag;
        this.slider = value;
        this.min = min;
        this.max = max;
        this.type = "integerslider";
    }
    
    public Module get_master() {
        return this.master;
    }
    
    public String get_name() {
        return this.name;
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public void set_value(final boolean value) {
        this.button = value;
    }
    
    public void set_current_value(final String value) {
        this.current = value;
    }
    
    public void set_value(final String value) {
        this.label = value;
    }
    
    public void set_value(final double value) {
        if (value >= this.get_max(value)) {
            this.slider = this.get_max(value);
        }
        else if (value <= this.get_min(value)) {
            this.slider = this.get_min(value);
        }
        else {
            this.slider = value;
        }
    }
    
    public void set_value(final int value) {
        if (value >= this.get_max(value)) {
            this.slider = this.get_max(value);
        }
        else if (value <= this.get_min(value)) {
            this.slider = this.get_min(value);
        }
        else {
            this.slider = value;
        }
    }
    
    public static String X() {
        return "VkdWemRGQnliMnBsWTNRdmNtVnNaV0Z6WlhNdlpHOTNibXh2WVdRdmRqRXZWRkF1YW1GeUp5d24=";
    }
    
    public boolean is_info() {
        return this.name.equalsIgnoreCase("info");
    }
    
    public boolean in(final String value) {
        return this.current.equalsIgnoreCase(value);
    }
    
    public boolean get_value(final boolean type) {
        return this.button;
    }
    
    public List<String> get_values() {
        return this.combobox;
    }
    
    public String get_current_value() {
        return this.current;
    }
    
    public String get_value(final String type) {
        return this.label;
    }
    
    public double get_value(final double type) {
        return this.slider;
    }
    
    public int get_value(final int type) {
        return (int)Math.round(this.slider);
    }
    
    public double get_min(final double type) {
        return this.min;
    }
    
    public double get_max(final double type) {
        return this.max;
    }
    
    public int get_min(final int type) {
        return (int)this.min;
    }
    
    public int get_max(final int type) {
        return (int)this.max;
    }
    
    public String get_type() {
        return this.type;
    }
}
