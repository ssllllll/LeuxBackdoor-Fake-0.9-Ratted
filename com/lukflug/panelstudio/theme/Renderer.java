// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

import java.awt.Color;
import java.awt.Rectangle;
import com.lukflug.panelstudio.Context;

public interface Renderer
{
    int getHeight(final boolean p0);
    
    int getOffset();
    
    int getBorder();
    
    int getBottomBorder();
    
    int getLeftBorder(final boolean p0);
    
    int getRightBorder(final boolean p0);
    
    void renderTitle(final Context p0, final String p1, final boolean p2);
    
    void renderTitle(final Context p0, final String p1, final boolean p2, final boolean p3);
    
    void renderTitle(final Context p0, final String p1, final boolean p2, final boolean p3, final boolean p4);
    
    void renderRect(final Context p0, final String p1, final boolean p2, final boolean p3, final Rectangle p4, final boolean p5);
    
    void renderBackground(final Context p0, final boolean p1);
    
    void renderBorder(final Context p0, final boolean p1, final boolean p2, final boolean p3);
    
    int renderScrollBar(final Context p0, final boolean p1, final boolean p2, final boolean p3, final int p4, final int p5);
    
    Color getMainColor(final boolean p0, final boolean p1);
    
    Color getBackgroundColor(final boolean p0);
    
    Color getFontColor(final boolean p0);
    
    ColorScheme getDefaultColorScheme();
    
    void overrideColorScheme(final ColorScheme p0);
    
    void restoreColorScheme();
    
    default Color brighter(final Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        r += 64;
        g += 64;
        b += 64;
        if (r > 255) {
            r = 255;
        }
        if (g > 255) {
            g = 255;
        }
        if (b > 255) {
            b = 255;
        }
        return new Color(r, g, b, color.getAlpha());
    }
    
    default Color darker(final Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        r -= 64;
        g -= 64;
        b -= 64;
        if (r < 0) {
            r = 0;
        }
        if (g < 0) {
            g = 0;
        }
        if (b < 0) {
            b = 0;
        }
        return new Color(r, g, b, color.getAlpha());
    }
}
