// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.turok.values;

public class TurokBoolean
{
    private TurokString name;
    private TurokString tag;
    private TurokGeneric<Boolean> value;
    
    public TurokBoolean(final TurokString name, final TurokString tag, final boolean _bool) {
        this.name = name;
        this.tag = tag;
        this.value = new TurokGeneric<Boolean>(_bool);
    }
    
    public void set_value(final boolean _bool) {
        this.value.set_value(_bool);
    }
    
    public TurokString get_name() {
        return this.name;
    }
    
    public TurokString get_tag() {
        return this.tag;
    }
    
    public boolean get_value() {
        return this.value.get_value();
    }
}
