// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.hud;

import java.awt.Color;

public interface HUDList
{
    int getSize();
    
    String getItem(final int p0);
    
    Color getItemColor(final int p0);
    
    boolean sortUp();
    
    boolean sortRight();
}
