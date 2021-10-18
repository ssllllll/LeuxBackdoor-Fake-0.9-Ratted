// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import java.awt.Point;

public interface PanelConfig
{
    void savePositon(final Point p0);
    
    Point loadPosition();
    
    void saveState(final boolean p0);
    
    boolean loadState();
}
