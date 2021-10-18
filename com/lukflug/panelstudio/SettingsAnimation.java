// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import com.lukflug.panelstudio.settings.NumberSetting;

public class SettingsAnimation extends Animation
{
    protected final NumberSetting speed;
    
    public SettingsAnimation(final NumberSetting speed) {
        this.speed = speed;
    }
    
    @Override
    protected int getSpeed() {
        return (int)this.speed.getNumber();
    }
}
