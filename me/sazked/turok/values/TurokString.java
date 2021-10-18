// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.turok.values;

public class TurokString
{
    private String name;
    private String tag;
    private String value;
    
    public TurokString(final String name, final String tag, final String string) {
        this.name = name;
        this.tag = tag;
        this.value = string;
    }
    
    public void set_value(final String string) {
        this.value = string;
    }
    
    public String get_name() {
        return this.name;
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public String get_value() {
        return this.value;
    }
    
    public static String to_string(final String value) {
        return value;
    }
    
    public static String to_string(final boolean value) {
        return Boolean.toString(value);
    }
    
    public static String to_string(final double value) {
        return Double.toString(value);
    }
    
    public static String to_string(final float value) {
        return Float.toString(value);
    }
    
    public static String to_string(final int value) {
        return Integer.toString(value);
    }
}
