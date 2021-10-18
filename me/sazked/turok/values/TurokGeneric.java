// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.turok.values;

public class TurokGeneric<S>
{
    S value;
    
    public TurokGeneric(final S value) {
        this.value = value;
    }
    
    public void set_value(final S value) {
        this.value = value;
    }
    
    public S get_value() {
        return this.value;
    }
}
