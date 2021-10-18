// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.tabgui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.ColorScheme;

public class DefaultRenderer implements TabGUIRenderer
{
    protected ColorScheme scheme;
    protected int height;
    protected int border;
    protected int up;
    protected int down;
    protected int left;
    protected int right;
    protected int enter;
    
    public DefaultRenderer(final ColorScheme scheme, final int height, final int border, final int up, final int down, final int left, final int right, final int enter) {
        this.scheme = scheme;
        this.border = border;
        this.height = height;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.enter = enter;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public int getBorder() {
        return this.border;
    }
    
    @Override
    public void renderBackground(final Context context, final int offset, final int height) {
        Color bgcolor = this.scheme.getBackgroundColor();
        bgcolor = new Color(bgcolor.getRed(), bgcolor.getGreen(), bgcolor.getBlue(), this.scheme.getOpacity());
        final Color border = this.scheme.getOutlineColor();
        final Color active = this.scheme.getActiveColor();
        context.getInterface().fillRect(context.getRect(), bgcolor, bgcolor, bgcolor, bgcolor);
        context.getInterface().drawRect(context.getRect(), border, border, border, border);
        final Point p = context.getPos();
        p.translate(0, offset);
        final Rectangle rect = new Rectangle(p, new Dimension(context.getSize().width, height));
        context.getInterface().fillRect(rect, active, active, active, active);
        context.getInterface().drawRect(rect, border, border, border, border);
    }
    
    @Override
    public void renderCaption(final Context context, final String caption, final int index, final int height, final boolean active) {
        Color color;
        if (active) {
            color = this.scheme.getActiveColor();
        }
        else {
            color = this.scheme.getFontColor();
        }
        final Point p = context.getPos();
        p.translate(0, index * height);
        context.getInterface().drawString(p, caption, color);
    }
    
    @Override
    public ColorScheme getColorScheme() {
        return this.scheme;
    }
    
    @Override
    public boolean isUpKey(final int key) {
        return key == this.up;
    }
    
    @Override
    public boolean isDownKey(final int key) {
        return key == this.down;
    }
    
    @Override
    public boolean isSelectKey(final int key) {
        return key == this.right || key == this.enter;
    }
    
    @Override
    public boolean isEscapeKey(final int key) {
        return key == this.left;
    }
}
