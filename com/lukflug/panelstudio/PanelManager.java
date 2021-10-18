// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import com.lukflug.panelstudio.settings.Toggleable;

public interface PanelManager
{
    void showComponent(final FixedComponent p0);
    
    void hideComponent(final FixedComponent p0);
    
    Toggleable getComponentToggleable(final FixedComponent p0);
}
