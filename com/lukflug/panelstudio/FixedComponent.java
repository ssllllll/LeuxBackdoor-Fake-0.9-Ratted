// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import java.awt.Point;

public interface FixedComponent extends Component
{
    Point getPosition(final Interface p0);
    
    void setPosition(final Interface p0, final Point p1);
    
    int getWidth(final Interface p0);
    
    void saveConfig(final Interface p0, final PanelConfig p1);
    
    void loadConfig(final Interface p0, final PanelConfig p1);
}
