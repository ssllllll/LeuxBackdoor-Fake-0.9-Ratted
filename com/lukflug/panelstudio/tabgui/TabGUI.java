// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.tabgui;

import com.lukflug.panelstudio.PanelConfig;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.Animation;
import java.awt.Point;
import com.lukflug.panelstudio.FixedComponent;

public class TabGUI extends TabGUIContainer implements FixedComponent
{
    protected Point position;
    protected int width;
    
    public TabGUI(final String title, final TabGUIRenderer renderer, final Animation animation, final Point position, final int width) {
        super(title, renderer, animation);
        this.position = position;
        this.width = width;
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
    public int getWidth(final Interface inter) {
        return this.width;
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
