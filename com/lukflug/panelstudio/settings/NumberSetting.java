// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.settings;

public interface NumberSetting
{
    double getNumber();
    
    void setNumber(final double p0);
    
    double getMaximumValue();
    
    double getMinimumValue();
    
    int getPrecision();
}
