// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

public interface ConfigList
{
    void begin(final boolean p0);
    
    void end(final boolean p0);
    
    PanelConfig addPanel(final String p0);
    
    PanelConfig getPanel(final String p0);
}
