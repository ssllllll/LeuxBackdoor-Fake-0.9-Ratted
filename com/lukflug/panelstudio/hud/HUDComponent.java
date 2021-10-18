// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.hud;

import com.lukflug.panelstudio.PanelConfig;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.Context;
import java.awt.Point;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.FixedComponent;

public abstract class HUDComponent implements FixedComponent
{
    protected String title;
    protected Renderer renderer;
    protected Point position;
    
    public HUDComponent(final String title, final Renderer renderer, final Point position) {
        this.title = title;
        this.renderer = renderer;
        this.position = position;
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public void render(final Context context) {
        this.getHeight(context);
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        this.getHeight(context);
    }
    
    @Override
    public void handleKey(final Context context, final int scancode) {
        this.getHeight(context);
    }
    
    @Override
    public void handleScroll(final Context context, final int diff) {
        this.getHeight(context);
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
    public void releaseFocus() {
    }
    
    @Override
    public Point getPosition(final Interface inter) {
        return new Point(this.position);
    }
    
    @Override
    public void setPosition(final Interface inter, final Point position) {
        this.position = position;
    }
    
    @Override
    public void saveConfig(final Interface inter, final PanelConfig config) {
        config.savePositon(this.position);
    }
    
    @Override
    public void loadConfig(final Interface inter, final PanelConfig config) {
        final Point pos = config.loadPosition();
        if (pos != null) {
            this.position = pos;
        }
    }
}
