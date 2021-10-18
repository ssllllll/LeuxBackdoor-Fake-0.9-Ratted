// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import java.util.Iterator;
import java.util.ArrayList;
import com.lukflug.panelstudio.theme.Renderer;
import java.util.List;

public class Container extends FocusableComponent
{
    protected List<Component> components;
    private String tempDescription;
    
    public Container(final String title, final String description, final Renderer renderer) {
        super(title, description, renderer);
        this.components = new ArrayList<Component>();
    }
    
    public void addComponent(final Component component) {
        this.components.add(component);
    }
    
    @Override
    public void render(final Context context) {
        this.tempDescription = null;
        this.doComponentLoop(context, (subContext, component) -> {
            component.render(subContext);
            if (subContext.isHovered() && subContext.getDescription() != null) {
                this.tempDescription = subContext.getDescription();
            }
            return;
        });
        if (this.tempDescription == null) {
            this.tempDescription = this.description;
        }
        context.setDescription(this.tempDescription);
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        this.getHeight(context);
        this.updateFocus(context, button);
        this.doComponentLoop(context, (subContext, component) -> {
            component.handleButton(subContext, button);
            if (subContext.focusReleased()) {
                context.releaseFocus();
            }
        });
    }
    
    @Override
    public void handleKey(final Context context, final int scancode) {
        this.doComponentLoop(context, (subContext, component) -> component.handleKey(subContext, scancode));
    }
    
    @Override
    public void handleScroll(final Context context, final int diff) {
        this.doComponentLoop(context, (subContext, component) -> component.handleScroll(subContext, diff));
    }
    
    @Override
    public void getHeight(final Context context) {
        this.doComponentLoop(context, (subContext, component) -> component.getHeight(subContext));
    }
    
    @Override
    public void enter(final Context context) {
        this.doComponentLoop(context, (subContext, component) -> component.enter(subContext));
    }
    
    @Override
    public void exit(final Context context) {
        this.doComponentLoop(context, (subContext, component) -> component.exit(subContext));
    }
    
    @Override
    public void releaseFocus() {
        super.releaseFocus();
        for (final Component component : this.components) {
            component.releaseFocus();
        }
    }
    
    @Override
    protected void handleFocus(final Context context, final boolean focus) {
        if (!focus) {
            this.releaseFocus();
        }
    }
    
    protected Context getSubContext(final Context context, final int posy) {
        return new Context(context, this.renderer.getBorder(), this.renderer.getBorder(), posy, this.hasFocus(context), true);
    }
    
    protected void doComponentLoop(final Context context, final LoopFunction function) {
        int posy = this.renderer.getOffset();
        for (final Component component : this.components) {
            final Context subContext = this.getSubContext(context, posy);
            function.loop(subContext, component);
            posy += subContext.getSize().height + this.renderer.getOffset();
        }
        context.setHeight(posy);
    }
    
    protected interface LoopFunction
    {
        void loop(final Context p0, final Component p1);
    }
}
