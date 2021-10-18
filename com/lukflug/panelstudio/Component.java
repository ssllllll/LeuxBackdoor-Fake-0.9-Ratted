// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

public interface Component
{
    String getTitle();
    
    void render(final Context p0);
    
    void handleButton(final Context p0, final int p1);
    
    void handleKey(final Context p0, final int p1);
    
    void handleScroll(final Context p0, final int p1);
    
    void getHeight(final Context p0);
    
    void enter(final Context p0);
    
    void exit(final Context p0);
    
    void releaseFocus();
}
