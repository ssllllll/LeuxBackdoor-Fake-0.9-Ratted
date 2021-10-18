// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import com.lukflug.panelstudio.Context;

public class GameSenseTheme implements Theme
{
    protected ColorScheme scheme;
    protected Renderer componentRenderer;
    protected Renderer containerRenderer;
    protected Renderer panelRenderer;
    
    public GameSenseTheme(final ColorScheme scheme, final int height, final int border, final int scroll) {
        this.scheme = scheme;
        this.panelRenderer = new ComponentRenderer(0, height, border, scroll);
        this.containerRenderer = new ComponentRenderer(1, height, border, scroll);
        this.componentRenderer = new ComponentRenderer(2, height, border, scroll);
    }
    
    @Override
    public Renderer getPanelRenderer() {
        return this.panelRenderer;
    }
    
    @Override
    public Renderer getContainerRenderer() {
        return this.containerRenderer;
    }
    
    @Override
    public Renderer getComponentRenderer() {
        return this.componentRenderer;
    }
    
    protected class ComponentRenderer extends RendererBase
    {
        protected final int level;
        protected final int border;
        
        public ComponentRenderer(final int level, final int height, final int border, final int scroll) {
            super(height + 2 * border, 0, 0, 0, scroll);
            this.level = level;
            this.border = border;
        }
        
        @Override
        public void renderRect(final Context context, final String text, final boolean focus, final boolean active, final Rectangle rectangle, final boolean overlay) {
            final Color color = this.getMainColor(focus, active);
            context.getInterface().fillRect(rectangle, color, color, color, color);
            if (overlay) {
                Color overlayColor;
                if (context.isHovered()) {
                    overlayColor = new Color(255, 255, 255, 64);
                }
                else {
                    overlayColor = new Color(255, 255, 255, 0);
                }
                context.getInterface().fillRect(context.getRect(), overlayColor, overlayColor, overlayColor, overlayColor);
            }
            final Point stringPos = new Point(rectangle.getLocation());
            stringPos.translate(0, this.border);
            context.getInterface().drawString(stringPos, text, this.getFontColor(focus));
        }
        
        @Override
        public void renderBackground(final Context context, final boolean focus) {
        }
        
        @Override
        public void renderBorder(final Context context, final boolean focus, final boolean active, final boolean open) {
            final Color color = this.getDefaultColorScheme().getOutlineColor();
            if (this.level == 0) {
                context.getInterface().fillRect(new Rectangle(context.getPos(), new Dimension(context.getSize().width, 1)), color, color, color, color);
                context.getInterface().fillRect(new Rectangle(context.getPos(), new Dimension(1, context.getSize().height)), color, color, color, color);
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x + context.getSize().width - 1, context.getPos().y), new Dimension(1, context.getSize().height)), color, color, color, color);
            }
            if (this.level == 0 || open) {
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x, context.getPos().y + context.getSize().height - 1), new Dimension(context.getSize().width, 1)), color, color, color, color);
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x, context.getPos().y + this.getHeight(open) - 1), new Dimension(context.getSize().width, 1)), color, color, color, color);
            }
        }
        
        @Override
        public int renderScrollBar(final Context context, final boolean focus, final boolean active, final boolean scroll, final int childHeight, final int scrollPosition) {
            if (scroll) {
                final int containerHeight = context.getSize().height - this.getHeight(true);
                final int a = (int)(scrollPosition / (double)childHeight * containerHeight);
                final int b = (int)((scrollPosition + containerHeight) / (double)childHeight * containerHeight);
                final Color background = this.getMainColor(focus, false);
                final Color slider = this.getMainColor(focus, true);
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x + context.getSize().width - this.getRightBorder(true), context.getPos().y + this.getHeight(true)), new Dimension(this.getRightBorder(true), a)), background, background, background, background);
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x + context.getSize().width - this.getRightBorder(true), context.getPos().y + this.getHeight(true) + a), new Dimension(this.getRightBorder(true), b - a)), slider, slider, slider, slider);
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x + context.getSize().width - this.getRightBorder(true), context.getPos().y + this.getHeight(true) + b), new Dimension(this.getRightBorder(true), context.getSize().height - this.getHeight(true) - b)), background, background, background, background);
                final Color color = this.getDefaultColorScheme().getOutlineColor();
                context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x + context.getSize().width - this.getRightBorder(true) - 1, context.getPos().y + this.getHeight(true)), new Dimension(1, context.getSize().height - this.getHeight(true))), color, color, color, color);
                if (context.isClicked() && context.getInterface().getMouse().x >= context.getPos().x + context.getSize().width - this.getRightBorder(true)) {
                    return (int)((context.getInterface().getMouse().y - context.getPos().y - this.getHeight(true)) * childHeight / (double)containerHeight - containerHeight / 2.0);
                }
            }
            return scrollPosition;
        }
        
        @Override
        public Color getMainColor(final boolean focus, final boolean active) {
            Color color;
            if (active) {
                color = this.getColorScheme().getActiveColor();
            }
            else {
                color = this.getColorScheme().getBackgroundColor();
            }
            if (!active && this.level < 2) {
                color = this.getColorScheme().getInactiveColor();
            }
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), this.getColorScheme().getOpacity());
            return color;
        }
        
        @Override
        public Color getBackgroundColor(final boolean focus) {
            return new Color(0, 0, 0, 0);
        }
        
        @Override
        public ColorScheme getDefaultColorScheme() {
            return GameSenseTheme.this.scheme;
        }
    }
}
