// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

import java.awt.Color;
import com.lukflug.panelstudio.Context;

public abstract class RendererBase implements Renderer
{
    protected final int height;
    protected final int offset;
    protected final int border;
    protected final int left;
    protected final int right;
    protected ColorScheme scheme;
    
    public RendererBase(final int height, final int offset, final int border, final int left, final int right) {
        this.scheme = null;
        this.height = height;
        this.offset = offset;
        this.border = border;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int getHeight(final boolean open) {
        return this.height;
    }
    
    @Override
    public int getOffset() {
        return this.offset;
    }
    
    @Override
    public int getBorder() {
        return this.border;
    }
    
    @Override
    public int getBottomBorder() {
        return 0;
    }
    
    @Override
    public int getLeftBorder(final boolean scroll) {
        if (scroll) {
            return this.left;
        }
        return 0;
    }
    
    @Override
    public int getRightBorder(final boolean scroll) {
        if (scroll) {
            return this.right;
        }
        return 0;
    }
    
    @Override
    public void renderTitle(final Context context, final String text, final boolean focus) {
        this.renderTitle(context, text, focus, false);
    }
    
    @Override
    public void renderTitle(final Context context, final String text, final boolean focus, final boolean active) {
        this.renderRect(context, text, focus, active, context.getRect(), true);
    }
    
    @Override
    public void renderTitle(final Context context, final String text, final boolean focus, final boolean active, final boolean open) {
        this.renderTitle(context, text, focus, active);
    }
    
    @Override
    public int renderScrollBar(final Context context, final boolean focus, final boolean active, final boolean scroll, final int childHeight, final int scrollPosition) {
        return scrollPosition;
    }
    
    @Override
    public Color getFontColor(final boolean focus) {
        return this.getColorScheme().getFontColor();
    }
    
    @Override
    public void overrideColorScheme(final ColorScheme scheme) {
        this.scheme = scheme;
    }
    
    @Override
    public void restoreColorScheme() {
        this.scheme = null;
    }
    
    protected ColorScheme getColorScheme() {
        if (this.scheme == null) {
            return this.getDefaultColorScheme();
        }
        return this.scheme;
    }
}
