// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.settings;

import java.awt.Color;

public interface ColorSetting
{
    Color getValue();
    
    void setValue(final Color p0);
    
    Color getColor();
    
    boolean getRainbow();
    
    void setRainbow(final boolean p0);
}
