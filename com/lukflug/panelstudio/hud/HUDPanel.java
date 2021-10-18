// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.hud;

import java.awt.Color;
import com.lukflug.panelstudio.theme.RendererProxy;
import com.lukflug.panelstudio.PanelConfig;
import java.awt.Rectangle;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Component;
import java.awt.Point;
import com.lukflug.panelstudio.Animation;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.DraggableContainer;

public class HUDPanel extends DraggableContainer
{
    protected Toggleable guiOpen;
    protected FixedComponent component;
    
    public HUDPanel(final FixedComponent component, final Renderer renderer, final Toggleable open, final Animation animation, final Toggleable guiOpen, final int minBorder) {
        super(component.getTitle(), null, new HUDRenderer(renderer, guiOpen, minBorder), open, animation, null, new Point(0, 0), 0);
        this.addComponent(component);
        this.guiOpen = guiOpen;
        this.component = component;
        this.bodyDrag = true;
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        if (this.guiOpen.isOn()) {
            super.handleButton(context, button);
        }
    }
    
    @Override
    public void handleScroll(final Context context, final int diff) {
        if (this.guiOpen.isOn()) {
            super.handleScroll(context, diff);
        }
    }
    
    @Override
    public Point getPosition(final Interface inter) {
        (this.position = this.component.getPosition(inter)).translate(0, -this.renderer.getHeight(this.open.getValue() != 0.0) - this.renderer.getOffset());
        return super.getPosition(inter);
    }
    
    @Override
    public void setPosition(final Interface inter, final Point position) {
        this.component.setPosition(inter, new Point(position.x, position.y + this.renderer.getHeight(this.open.getValue() != 0.0) + this.renderer.getOffset()));
    }
    
    @Override
    public int getWidth(final Interface inter) {
        return this.component.getWidth(inter) + this.renderer.getBorder() * 2 + this.renderer.getLeftBorder(this.scroll) + this.renderer.getRightBorder(this.scroll);
    }
    
    @Override
    protected Rectangle getClipRect(final Context context, final int height) {
        if (this.open.getValue() != 1.0) {
            return super.getClipRect(context, height);
        }
        return null;
    }
    
    @Override
    public void saveConfig(final Interface inter, final PanelConfig config) {
        this.component.saveConfig(inter, config);
        config.saveState(this.open.isOn());
    }
    
    @Override
    public void loadConfig(final Interface inter, final PanelConfig config) {
        this.component.loadConfig(inter, config);
        if (this.open.isOn() != config.loadState()) {
            this.open.toggle();
        }
    }
    
    protected static class HUDRenderer extends RendererProxy
    {
        Renderer renderer;
        protected Toggleable guiOpen;
        protected int minBorder;
        
        public HUDRenderer(final Renderer renderer, final Toggleable guiOpen, final int minBorder) {
            this.renderer = renderer;
            this.guiOpen = guiOpen;
            this.minBorder = minBorder;
        }
        
        @Override
        public int getOffset() {
            return Math.max(this.renderer.getOffset(), this.minBorder);
        }
        
        @Override
        public int getBorder() {
            return Math.max(this.renderer.getBorder(), this.minBorder);
        }
        
        @Override
        public void renderTitle(final Context context, final String text, final boolean focus) {
            if (this.guiOpen.isOn()) {
                this.renderer.renderTitle(context, text, focus);
            }
        }
        
        @Override
        public void renderTitle(final Context context, final String text, final boolean focus, final boolean active) {
            if (this.guiOpen.isOn()) {
                this.renderer.renderTitle(context, text, focus, active);
            }
        }
        
        @Override
        public void renderTitle(final Context context, final String text, final boolean focus, final boolean active, final boolean open) {
            if (this.guiOpen.isOn()) {
                this.renderer.renderTitle(context, text, focus, open);
            }
        }
        
        @Override
        public void renderRect(final Context context, final String text, final boolean focus, final boolean active, final Rectangle rectangle, final boolean overlay) {
            if (this.guiOpen.isOn()) {
                this.renderer.renderRect(context, text, focus, active, rectangle, overlay);
            }
        }
        
        @Override
        public void renderBackground(final Context context, final boolean focus) {
            if (this.guiOpen.isOn()) {
                this.renderer.renderBackground(context, focus);
            }
        }
        
        @Override
        public void renderBorder(final Context context, final boolean focus, final boolean active, final boolean open) {
            if (this.guiOpen.isOn()) {
                this.renderer.renderBorder(context, focus, active, open);
            }
        }
        
        @Override
        public int renderScrollBar(final Context context, final boolean focus, final boolean active, final boolean scroll, final int childHeight, final int scrollPosition) {
            if (this.guiOpen.isOn()) {
                return this.renderer.renderScrollBar(context, focus, active, scroll, childHeight, scrollPosition);
            }
            return scrollPosition;
        }
        
        @Override
        public Color getMainColor(final boolean focus, final boolean active) {
            if (this.guiOpen.isOn()) {
                return this.renderer.getMainColor(focus, active);
            }
            return new Color(0, 0, 0, 0);
        }
        
        @Override
        public Color getBackgroundColor(final boolean focus) {
            if (this.guiOpen.isOn()) {
                return this.renderer.getBackgroundColor(focus);
            }
            return new Color(0, 0, 0, 0);
        }
        
        @Override
        public Color getFontColor(final boolean focus) {
            if (this.guiOpen.isOn()) {
                return this.renderer.getFontColor(focus);
            }
            return new Color(0, 0, 0, 0);
        }
        
        @Override
        protected Renderer getRenderer() {
            return this.renderer;
        }
    }
}
