// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.hud;

import com.lukflug.panelstudio.PanelConfig;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.Context;
import java.awt.Point;
import com.lukflug.panelstudio.theme.Renderer;

public class ListComponent extends HUDComponent
{
    protected HUDList list;
    protected boolean lastUp;
    protected boolean lastRight;
    
    public ListComponent(final String name, final Renderer renderer, final Point position, final HUDList list) {
        super(name, renderer, position);
        this.lastUp = false;
        this.lastRight = false;
        this.list = list;
    }
    
    @Override
    public void render(final Context context) {
        super.render(context);
        for (int i = 0; i < this.list.getSize(); ++i) {
            final String s = this.list.getItem(i);
            final Point p = context.getPos();
            if (this.list.sortUp()) {
                p.translate(0, context.getSize().height - (i + 1) * context.getInterface().getFontHeight());
            }
            else {
                p.translate(0, i * context.getInterface().getFontHeight());
            }
            if (this.list.sortRight()) {
                p.translate(this.getWidth(context.getInterface()) - context.getInterface().getFontWidth(s), 0);
            }
            context.getInterface().drawString(p, s, this.list.getItemColor(i));
        }
    }
    
    @Override
    public Point getPosition(final Interface inter) {
        final int width = this.getWidth(inter);
        final int height = this.renderer.getHeight(false) + (this.list.getSize() - 1) * inter.getFontHeight();
        if (this.lastUp != this.list.sortUp()) {
            if (this.list.sortUp()) {
                this.position.translate(0, height);
            }
            else {
                this.position.translate(0, -height);
            }
            this.lastUp = this.list.sortUp();
        }
        if (this.lastRight != this.list.sortRight()) {
            if (this.list.sortRight()) {
                this.position.translate(width, 0);
            }
            else {
                this.position.translate(-width, 0);
            }
            this.lastRight = this.list.sortRight();
        }
        if (this.list.sortUp()) {
            if (this.list.sortRight()) {
                return new Point(this.position.x - width, this.position.y - height);
            }
            return new Point(this.position.x, this.position.y - height);
        }
        else {
            if (this.list.sortRight()) {
                return new Point(new Point(this.position.x - width, this.position.y));
            }
            return new Point(this.position);
        }
    }
    
    @Override
    public void setPosition(final Interface inter, final Point position) {
        final int width = this.getWidth(inter);
        final int height = this.renderer.getHeight(false) + (this.list.getSize() - 1) * inter.getFontHeight();
        if (this.list.sortUp()) {
            if (this.list.sortRight()) {
                this.position = new Point(position.x + width, position.y + height);
            }
            else {
                this.position = new Point(position.x, position.y + height);
            }
        }
        else if (this.list.sortRight()) {
            this.position = new Point(position.x + width, position.y);
        }
        else {
            this.position = new Point(position);
        }
    }
    
    @Override
    public int getWidth(final Interface inter) {
        int width = inter.getFontWidth(this.getTitle());
        for (int i = 0; i < this.list.getSize(); ++i) {
            final String s = this.list.getItem(i);
            width = Math.max(width, inter.getFontWidth(s));
        }
        return width;
    }
    
    @Override
    public void getHeight(final Context context) {
        context.setHeight(this.renderer.getHeight(false) + (this.list.getSize() - 1) * context.getInterface().getFontHeight());
    }
    
    @Override
    public void loadConfig(final Interface inter, final PanelConfig config) {
        super.loadConfig(inter, config);
        this.lastUp = this.list.sortUp();
        this.lastRight = this.list.sortRight();
    }
}
