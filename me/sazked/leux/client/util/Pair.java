// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

public class Pair<T, S>
{
    public T key;
    public S value;
    
    public void setKey(final T key) {
        this.key = key;
    }
    
    public T getKey() {
        return this.key;
    }
    
    public void setValue(final S value) {
        this.value = value;
    }
    
    public S getValue() {
        return this.value;
    }
    
    public Pair(final T obf, final S obf) {
        this.key = obf;
        this.value = obf;
    }
}
