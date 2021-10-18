// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

public abstract class ThemeMultiplexer implements Theme
{
    protected final Renderer panelRenderer;
    protected final Renderer containerRenderer;
    protected final Renderer componentRenderer;
    
    public ThemeMultiplexer() {
        this.panelRenderer = new PanelRenderer();
        this.containerRenderer = new ContainerRenderer();
        this.componentRenderer = new ComponentRenderer();
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
    
    protected abstract Theme getTheme();
    
    protected class PanelRenderer extends RendererProxy
    {
        @Override
        protected Renderer getRenderer() {
            return ThemeMultiplexer.this.getTheme().getPanelRenderer();
        }
    }
    
    protected class ContainerRenderer extends RendererProxy
    {
        @Override
        protected Renderer getRenderer() {
            return ThemeMultiplexer.this.getTheme().getContainerRenderer();
        }
    }
    
    protected class ComponentRenderer extends RendererProxy
    {
        @Override
        protected Renderer getRenderer() {
            return ThemeMultiplexer.this.getTheme().getComponentRenderer();
        }
    }
}
