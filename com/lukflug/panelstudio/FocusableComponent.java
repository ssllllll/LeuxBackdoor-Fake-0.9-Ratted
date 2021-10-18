// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import com.lukflug.panelstudio.theme.Renderer;

public class FocusableComponent implements Component
{
    protected String title;
    protected String description;
    protected Renderer renderer;
    private boolean focus;
    
    public FocusableComponent(final String title, final String description, final Renderer renderer) {
        this.focus = false;
        this.title = title;
        this.renderer = renderer;
        this.description = description;
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public void render(final Context context) {
        context.setHeight(this.renderer.getHeight(false));
        context.setDescription(this.description);
    }
    
    @Override
    public void handleKey(final Context context, final int scancode) {
        context.setHeight(this.renderer.getHeight(false));
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        context.setHeight(this.renderer.getHeight(false));
        this.updateFocus(context, button);
    }
    
    @Override
    public void getHeight(final Context context) {
        context.setHeight(this.renderer.getHeight(false));
    }
    
    @Override
    public void handleScroll(final Context context, final int diff) {
        context.setHeight(this.renderer.getHeight(false));
    }
    
    @Override
    public void enter(final Context context) {
        context.setHeight(this.renderer.getHeight(false));
    }
    
    @Override
    public void exit(final Context context) {
        context.setHeight(this.renderer.getHeight(false));
    }
    
    public boolean hasFocus(final Context context) {
        return context.hasFocus() && this.focus;
    }
    
    @Override
    public void releaseFocus() {
        this.focus = false;
    }
    
    protected void updateFocus(final Context context, final int button) {
        if (context.getInterface().getButton(button)) {
            this.focus = context.isHovered();
            this.handleFocus(context, this.focus && context.hasFocus());
        }
    }
    
    protected void handleFocus(final Context context, final boolean focus) {
    }
}
