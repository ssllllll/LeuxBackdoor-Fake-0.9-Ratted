// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.settings;

import com.lukflug.panelstudio.Animation;

public final class AnimatedToggleable implements Toggleable
{
    private final Toggleable toggle;
    private final Animation animation;
    
    public AnimatedToggleable(final Toggleable toggle, final Animation animation) {
        this.toggle = toggle;
        this.animation = animation;
        if (toggle.isOn()) {
            animation.initValue(1.0);
        }
        else {
            animation.initValue(0.0);
        }
    }
    
    @Override
    public void toggle() {
        this.toggle.toggle();
        if (this.toggle.isOn()) {
            this.animation.setValue(1.0);
        }
        else {
            this.animation.setValue(0.0);
        }
    }
    
    @Override
    public boolean isOn() {
        return this.toggle.isOn();
    }
    
    public double getValue() {
        if (this.animation.getTarget() != (this.toggle.isOn() ? 1 : 0)) {
            if (this.toggle.isOn()) {
                this.animation.setValue(1.0);
            }
            else {
                this.animation.setValue(0.0);
            }
        }
        return this.animation.getValue();
    }
}
