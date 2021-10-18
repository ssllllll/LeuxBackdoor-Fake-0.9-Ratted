// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Point;

public interface Interface
{
    public static final int LBUTTON = 0;
    public static final int RBUTTON = 1;
    
    Point getMouse();
    
    boolean getButton(final int p0);
    
    void drawString(final Point p0, final String p1, final Color p2);
    
    int getFontWidth(final String p0);
    
    int getFontHeight();
    
    void fillTriangle(final Point p0, final Point p1, final Point p2, final Color p3, final Color p4, final Color p5);
    
    void drawLine(final Point p0, final Point p1, final Color p2, final Color p3);
    
    void fillRect(final Rectangle p0, final Color p1, final Color p2, final Color p3, final Color p4);
    
    void drawRect(final Rectangle p0, final Color p1, final Color p2, final Color p3, final Color p4);
    
    int loadImage(final String p0);
    
    void drawImage(final Rectangle p0, final int p1, final boolean p2, final int p3);
    
    void window(final Rectangle p0);
    
    void restore();
}
