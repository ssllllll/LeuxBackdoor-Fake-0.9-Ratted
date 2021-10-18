// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

import java.awt.Color;
import java.awt.Rectangle;
import com.lukflug.panelstudio.Context;

public abstract class RendererProxy implements Renderer
{
    @Override
    public int getHeight(final boolean open) {
        return this.getRenderer().getHeight(open);
    }
    
    @Override
    public int getOffset() {
        return this.getRenderer().getOffset();
    }
    
    @Override
    public int getBorder() {
        return this.getRenderer().getBorder();
    }
    
    @Override
    public int getBottomBorder() {
        return this.getRenderer().getBottomBorder();
    }
    
    @Override
    public int getLeftBorder(final boolean scroll) {
        return this.getRenderer().getLeftBorder(scroll);
    }
    
    @Override
    public int getRightBorder(final boolean scroll) {
        return this.getRenderer().getRightBorder(scroll);
    }
    
    @Override
    public void renderTitle(final Context context, final String text, final boolean focus) {
        this.getRenderer().renderTitle(context, text, focus);
    }
    
    @Override
    public void renderTitle(final Context context, final String text, final boolean focus, final boolean active) {
        this.getRenderer().renderTitle(context, text, focus, active);
    }
    
    @Override
    public void renderTitle(final Context context, final String text, final boolean focus, final boolean active, final boolean open) {
        this.getRenderer().renderTitle(context, text, focus, active, open);
    }
    
    @Override
    public void renderRect(final Context context, final String text, final boolean focus, final boolean active, final Rectangle rectangle, final boolean overlay) {
        this.getRenderer().renderRect(context, text, focus, active, rectangle, overlay);
    }
    
    @Override
    public void renderBackground(final Context context, final boolean focus) {
        this.getRenderer().renderBackground(context, focus);
    }
    
    @Override
    public void renderBorder(final Context context, final boolean focus, final boolean active, final boolean open) {
        this.getRenderer().renderBorder(context, focus, active, open);
    }
    
    @Override
    public int renderScrollBar(final Context context, final boolean focus, final boolean active, final boolean scroll, final int childHeight, final int scrollPosition) {
        return this.getRenderer().renderScrollBar(context, focus, active, scroll, childHeight, scrollPosition);
    }
    
    @Override
    public Color getMainColor(final boolean focus, final boolean active) {
        return this.getRenderer().getMainColor(focus, active);
    }
    
    @Override
    public Color getBackgroundColor(final boolean focus) {
        return this.getRenderer().getBackgroundColor(focus);
    }
    
    @Override
    public Color getFontColor(final boolean focus) {
        return this.getRenderer().getFontColor(focus);
    }
    
    @Override
    public ColorScheme getDefaultColorScheme() {
        return this.getRenderer().getDefaultColorScheme();
    }
    
    @Override
    public void overrideColorScheme(final ColorScheme scheme) {
        this.getRenderer().overrideColorScheme(scheme);
    }
    
    @Override
    public void restoreColorScheme() {
        this.getRenderer().restoreColorScheme();
    }
    
    protected abstract Renderer getRenderer();
}
