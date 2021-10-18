// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.tabgui;

import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.Context;

public interface TabGUIRenderer
{
    int getHeight();
    
    int getBorder();
    
    void renderBackground(final Context p0, final int p1, final int p2);
    
    void renderCaption(final Context p0, final String p1, final int p2, final int p3, final boolean p4);
    
    ColorScheme getColorScheme();
    
    boolean isUpKey(final int p0);
    
    boolean isDownKey(final int p0);
    
    boolean isSelectKey(final int p0);
    
    boolean isEscapeKey(final int p0);
}
