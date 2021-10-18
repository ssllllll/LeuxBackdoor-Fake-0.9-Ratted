// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

import java.awt.Color;
import com.lukflug.panelstudio.settings.NumberSetting;
import com.lukflug.panelstudio.settings.ColorSetting;

public class SettingsColorScheme implements ColorScheme
{
    protected final ColorSetting activeColor;
    protected final ColorSetting inactiveColor;
    protected final ColorSetting backgroundColor;
    protected final ColorSetting outlineColor;
    protected final ColorSetting fontColor;
    protected final NumberSetting opacity;
    
    public SettingsColorScheme(final ColorSetting activeColor, final ColorSetting inactiveColor, final ColorSetting backgroundColor, final ColorSetting outlineColor, final ColorSetting fontColor, final NumberSetting opacity) {
        this.activeColor = activeColor;
        this.inactiveColor = inactiveColor;
        this.backgroundColor = backgroundColor;
        this.outlineColor = outlineColor;
        this.fontColor = fontColor;
        this.opacity = opacity;
    }
    
    @Override
    public Color getActiveColor() {
        return this.activeColor.getValue();
    }
    
    @Override
    public Color getInactiveColor() {
        return this.inactiveColor.getValue();
    }
    
    @Override
    public Color getBackgroundColor() {
        return this.backgroundColor.getValue();
    }
    
    @Override
    public Color getOutlineColor() {
        return this.outlineColor.getValue();
    }
    
    @Override
    public Color getFontColor() {
        return this.fontColor.getValue();
    }
    
    @Override
    public int getOpacity() {
        return (int)this.opacity.getNumber();
    }
}
