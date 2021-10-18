// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.settings;

public class SimpleToggleable implements Toggleable
{
    private boolean value;
    
    public SimpleToggleable(final boolean value) {
        this.value = value;
    }
    
    @Override
    public void toggle() {
        this.value = !this.value;
    }
    
    @Override
    public boolean isOn() {
        return this.value;
    }
}
