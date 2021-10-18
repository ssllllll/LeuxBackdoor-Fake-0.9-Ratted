// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.tabgui;

import java.awt.Point;
import com.lukflug.panelstudio.Context;
import java.util.ArrayList;
import com.lukflug.panelstudio.Animation;
import java.util.List;

public class TabGUIContainer implements TabGUIComponent
{
    protected String title;
    protected TabGUIRenderer renderer;
    protected List<TabGUIComponent> components;
    protected boolean childOpen;
    protected int selected;
    protected Animation selectedAnimation;
    
    public TabGUIContainer(final String title, final TabGUIRenderer renderer, final Animation animation) {
        this.childOpen = false;
        this.selected = 0;
        this.selectedAnimation = null;
        this.title = title;
        this.renderer = renderer;
        this.components = new ArrayList<TabGUIComponent>();
        if (animation != null) {
            animation.initValue(this.selected);
            this.selectedAnimation = animation;
        }
    }
    
    public void addComponent(final TabGUIComponent component) {
        this.components.add(component);
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public void render(final Context context) {
        this.getHeight(context);
        int offset = this.selected * this.renderer.getHeight();
        if (this.selectedAnimation != null) {
            offset = (int)(this.selectedAnimation.getValue() * this.renderer.getHeight());
        }
        this.renderer.renderBackground(context, offset, this.renderer.getHeight());
        for (int i = 0; i < this.components.size(); ++i) {
            final TabGUIComponent component = this.components.get(i);
            this.renderer.renderCaption(context, component.getTitle(), i, this.renderer.getHeight(), component.isActive());
        }
        if (this.childOpen) {
            this.components.get(this.selected).render(this.getSubContext(context));
        }
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        this.getHeight(context);
    }
    
    @Override
    public void handleKey(final Context context, final int scancode) {
        this.getHeight(context);
        if (this.renderer.isEscapeKey(scancode)) {
            this.childOpen = false;
        }
        else if (!this.childOpen) {
            if (this.renderer.isUpKey(scancode)) {
                if (--this.selected < 0) {
                    this.selected = this.components.size() - 1;
                }
                if (this.selectedAnimation != null) {
                    this.selectedAnimation.setValue(this.selected);
                }
            }
            else if (this.renderer.isDownKey(scancode)) {
                if (++this.selected >= this.components.size()) {
                    this.selected = 0;
                }
                if (this.selectedAnimation != null) {
                    this.selectedAnimation.setValue(this.selected);
                }
            }
            else if (this.renderer.isSelectKey(scancode) && this.components.get(this.selected).select()) {
                this.childOpen = true;
            }
        }
        else {
            this.components.get(this.selected).handleKey(this.getSubContext(context), scancode);
        }
    }
    
    @Override
    public void handleScroll(final Context context, final int diff) {
        this.getHeight(context);
    }
    
    @Override
    public void getHeight(final Context context) {
        context.setHeight(this.renderer.getHeight() * this.components.size());
    }
    
    @Override
    public void enter(final Context context) {
        this.getHeight(context);
    }
    
    @Override
    public void exit(final Context context) {
        this.getHeight(context);
    }
    
    @Override
    public boolean isActive() {
        return false;
    }
    
    @Override
    public boolean select() {
        return true;
    }
    
    protected Context getSubContext(final Context context) {
        final Point p = context.getPos();
        p.translate(context.getSize().width + this.renderer.getBorder(), this.selected * this.renderer.getHeight());
        return new Context(context.getInterface(), context.getSize().width, p, context.hasFocus(), context.onTop());
    }
    
    @Override
    public void releaseFocus() {
    }
}
