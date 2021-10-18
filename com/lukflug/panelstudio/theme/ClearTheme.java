// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;
import com.lukflug.panelstudio.Context;

public class ClearTheme implements Theme
{
    protected ColorScheme scheme;
    protected Renderer componentRenderer;
    protected Renderer panelRenderer;
    protected final boolean gradient;
    
    public ClearTheme(final ColorScheme scheme, final boolean gradient, final int height, final int border) {
        this.scheme = scheme;
        this.gradient = gradient;
        this.panelRenderer = new ComponentRenderer(true, height, border);
        this.componentRenderer = new ComponentRenderer(false, height, border);
    }
    
    @Override
    public Renderer getPanelRenderer() {
        return this.panelRenderer;
    }
    
    @Override
    public Renderer getContainerRenderer() {
        return this.componentRenderer;
    }
    
    @Override
    public Renderer getComponentRenderer() {
        return this.componentRenderer;
    }
    
    protected class ComponentRenderer extends RendererBase
    {
        protected final boolean panel;
        
        public ComponentRenderer(final boolean panel, final int height, final int border) {
            super(height + 2 * border, border, 0, 0, 0);
            this.panel = panel;
        }
        
        @Override
        public void renderTitle(final Context context, final String text, final boolean focus, final boolean active) {
            if (this.panel) {
                super.renderTitle(context, text, focus, active);
            }
            else {
                Color overlayColor;
                if (context.isHovered()) {
                    overlayColor = new Color(0, 0, 0, 64);
                }
                else {
                    overlayColor = new Color(0, 0, 0, 0);
                }
                context.getInterface().fillRect(context.getRect(), overlayColor, overlayColor, overlayColor, overlayColor);
                Color fontColor = this.getFontColor(focus);
                if (active) {
                    fontColor = this.getMainColor(focus, true);
                }
                final Point stringPos = new Point(context.getPos());
                stringPos.translate(0, this.getOffset());
                context.getInterface().drawString(stringPos, text, fontColor);
            }
        }
        
        @Override
        public void renderTitle(final Context context, final String text, final boolean focus, final boolean active, final boolean open) {
            super.renderTitle(context, text, focus, active, open);
            if (!this.panel) {
                final Color color = this.getFontColor(active);
                Point p3;
                Point p4;
                Point p5;
                if (open) {
                    p3 = new Point(context.getPos().x + context.getSize().width - 2, context.getPos().y + context.getSize().height / 4);
                    p4 = new Point(context.getPos().x + context.getSize().width - context.getSize().height / 2, context.getPos().y + context.getSize().height * 3 / 4);
                    p5 = new Point(context.getPos().x + context.getSize().width - context.getSize().height + 2, context.getPos().y + context.getSize().height / 4);
                }
                else {
                    p3 = new Point(context.getPos().x + context.getSize().width - context.getSize().height * 3 / 4, context.getPos().y + 2);
                    p4 = new Point(context.getPos().x + context.getSize().width - context.getSize().height / 4, context.getPos().y + context.getSize().height / 2);
                    p5 = new Point(context.getPos().x + context.getSize().width - context.getSize().height * 3 / 4, context.getPos().y + context.getSize().height - 2);
                }
                context.getInterface().fillTriangle(p5, p4, p3, color, color, color);
            }
        }
        
        @Override
        public void renderRect(final Context context, final String text, final boolean focus, final boolean active, final Rectangle rectangle, final boolean overlay) {
            if (this.panel || active) {
                final Color color = this.getMainColor(focus, true);
                final Color color2 = this.getBackgroundColor(focus);
                if (ClearTheme.this.gradient && this.panel) {
                    context.getInterface().fillRect(rectangle, color, color, color2, color2);
                }
                else {
                    context.getInterface().fillRect(rectangle, color, color, color, color);
                }
            }
            if (!this.panel && overlay) {
                Color overlayColor;
                if (context.isHovered()) {
                    overlayColor = new Color(0, 0, 0, 64);
                }
                else {
                    overlayColor = new Color(0, 0, 0, 0);
                }
                context.getInterface().fillRect(context.getRect(), overlayColor, overlayColor, overlayColor, overlayColor);
            }
            final Point stringPos = new Point(rectangle.getLocation());
            stringPos.translate(0, this.getOffset());
            context.getInterface().drawString(stringPos, text, this.getFontColor(focus));
        }
        
        @Override
        public void renderBackground(final Context context, final boolean focus) {
            if (this.panel) {
                final Color color = this.getBackgroundColor(focus);
                context.getInterface().fillRect(context.getRect(), color, color, color, color);
            }
        }
        
        @Override
        public void renderBorder(final Context context, final boolean focus, final boolean active, final boolean open) {
        }
        
        @Override
        public Color getMainColor(final boolean focus, final boolean active) {
            if (active) {
                return this.getColorScheme().getActiveColor();
            }
            return new Color(0, 0, 0, 0);
        }
        
        @Override
        public Color getBackgroundColor(final boolean focus) {
            final Color color = this.getColorScheme().getBackgroundColor();
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), this.getColorScheme().getOpacity());
        }
        
        @Override
        public ColorScheme getDefaultColorScheme() {
            return ClearTheme.this.scheme;
        }
    }
}
