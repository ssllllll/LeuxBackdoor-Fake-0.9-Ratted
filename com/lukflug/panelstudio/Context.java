// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;

public final class Context
{
    private Interface inter;
    private Dimension size;
    private Point position;
    private boolean focus;
    private boolean onTop;
    private boolean focusRequested;
    private boolean focusOverride;
    private String description;
    
    public Context(final Context context, final int left, final int right, final int offset, final boolean focus, final boolean onTop) {
        this.focusRequested = false;
        this.focusOverride = false;
        this.description = null;
        this.inter = context.getInterface();
        this.size = new Dimension(context.getSize().width - left - right, 0);
        (this.position = new Point(context.getPos())).translate(left, offset);
        this.focus = (context.hasFocus() && focus);
        this.onTop = (context.onTop() && onTop);
    }
    
    public Context(final Interface inter, final int width, final Point position, final boolean focus, final boolean onTop) {
        this.focusRequested = false;
        this.focusOverride = false;
        this.description = null;
        this.inter = inter;
        this.size = new Dimension(width, 0);
        this.position = new Point(position);
        this.focus = focus;
        this.onTop = onTop;
    }
    
    public Interface getInterface() {
        return this.inter;
    }
    
    public Dimension getSize() {
        return new Dimension(this.size);
    }
    
    public void setHeight(final int height) {
        this.size.height = height;
    }
    
    public Point getPos() {
        return new Point(this.position);
    }
    
    public boolean hasFocus() {
        return this.focus;
    }
    
    public boolean onTop() {
        return this.onTop;
    }
    
    public void requestFocus() {
        this.focusRequested = true;
    }
    
    public void releaseFocus() {
        this.focusRequested = false;
        this.focusOverride = true;
    }
    
    public boolean foucsRequested() {
        return this.focusRequested;
    }
    
    public boolean focusReleased() {
        return this.focusOverride;
    }
    
    public boolean isHovered() {
        return new Rectangle(this.position, this.size).contains(this.inter.getMouse()) && this.onTop;
    }
    
    public boolean isClicked() {
        return this.isHovered() && this.inter.getButton(0);
    }
    
    public Rectangle getRect() {
        return new Rectangle(this.position, this.size);
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
}
