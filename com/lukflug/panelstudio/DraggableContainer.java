// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.Renderer;
import java.awt.Point;

public class DraggableContainer extends CollapsibleContainer implements FixedComponent
{
    protected boolean dragging;
    protected Point attachPoint;
    protected Point position;
    protected int width;
    protected boolean bodyDrag;
    
    public DraggableContainer(final String title, final String description, final Renderer renderer, final Toggleable open, final Animation animation, final Toggleable toggle, final Point position, final int width) {
        super(title, description, renderer, open, animation, toggle);
        this.dragging = false;
        this.bodyDrag = false;
        this.position = position;
        this.width = width;
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        if (this.bodyDrag) {
            super.handleButton(context, button);
        }
        else {
            context.setHeight(this.renderer.getHeight(this.open.getValue() != 0.0));
        }
        if (context.isClicked() && button == 0) {
            this.dragging = true;
            this.attachPoint = context.getInterface().getMouse();
        }
        else if (!context.getInterface().getButton(0) && this.dragging) {
            final Point mouse = context.getInterface().getMouse();
            this.dragging = false;
            final Point p = this.getPosition(context.getInterface());
            p.translate(mouse.x - this.attachPoint.x, mouse.y - this.attachPoint.y);
            this.setPosition(context.getInterface(), p);
        }
        if (!this.bodyDrag) {
            super.handleButton(context, button);
        }
    }
    
    @Override
    public Point getPosition(final Interface inter) {
        if (this.dragging) {
            final Point point = new Point(this.position);
            point.translate(inter.getMouse().x - this.attachPoint.x, inter.getMouse().y - this.attachPoint.y);
            return point;
        }
        return this.position;
    }
    
    @Override
    public void setPosition(final Interface inter, final Point position) {
        this.position = new Point(position);
    }
    
    @Override
    public int getWidth(final Interface inter) {
        return this.width;
    }
    
    @Override
    protected void handleFocus(final Context context, final boolean focus) {
        if (focus) {
            context.requestFocus();
        }
    }
    
    @Override
    public void saveConfig(final Interface inter, final PanelConfig config) {
        config.savePositon(this.position);
        config.saveState(this.open.isOn());
    }
    
    @Override
    public void loadConfig(final Interface inter, final PanelConfig config) {
        final Point pos = config.loadPosition();
        if (pos != null) {
            this.position = pos;
        }
        if (this.open.isOn() != config.loadState()) {
            this.open.toggle();
        }
    }
}
