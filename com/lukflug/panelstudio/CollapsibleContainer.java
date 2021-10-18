// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import java.awt.Rectangle;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.settings.AnimatedToggleable;
import com.lukflug.panelstudio.settings.Toggleable;

public class CollapsibleContainer extends FocusableComponent implements Toggleable
{
    protected Container container;
    protected AnimatedToggleable open;
    protected Toggleable toggle;
    protected int childHeight;
    protected int containerHeight;
    protected boolean scroll;
    protected int scrollPosition;
    
    public CollapsibleContainer(final String title, final String description, final Renderer renderer, final Toggleable open, final Animation animation, final Toggleable toggle) {
        super(title, description, renderer);
        this.childHeight = 0;
        this.containerHeight = 0;
        this.scroll = false;
        this.scrollPosition = 0;
        this.container = new Container(title, null, renderer);
        this.open = new AnimatedToggleable(open, animation);
        this.toggle = toggle;
    }
    
    public void addComponent(final Component component) {
        this.container.addComponent(component);
    }
    
    @Override
    public void render(final Context context) {
        this.getHeight(context);
        this.renderer.renderBackground(context, this.hasFocus(context));
        super.render(context);
        this.renderer.renderTitle(context, this.title, this.hasFocus(context), this.isActive(), this.open.getValue() != 0.0);
        if (this.open.getValue() != 0.0) {
            Context subContext = this.getSubContext(context, this.open.getValue() == 1.0);
            this.container.getHeight(subContext);
            final Rectangle rect = this.getClipRect(context, subContext.getSize().height);
            boolean onTop = this.open.getValue() == 1.0;
            if (rect != null) {
                onTop = rect.contains(context.getInterface().getMouse());
                context.getInterface().window(rect);
            }
            subContext = this.getSubContext(context, onTop);
            this.container.render(subContext);
            if (rect != null) {
                context.getInterface().restore();
            }
            if (subContext.isHovered()) {
                context.setDescription(subContext.getDescription());
            }
            context.setHeight(this.getRenderHeight(subContext.getSize().height));
            this.scrollPosition = this.renderer.renderScrollBar(context, this.hasFocus(context), this.isActive(), this.scroll, this.childHeight, this.scrollPosition);
            if (this.scrollPosition > this.childHeight - this.containerHeight) {
                this.scrollPosition = this.childHeight - this.containerHeight;
            }
            if (this.scrollPosition < 0) {
                this.scrollPosition = 0;
            }
        }
        this.renderer.renderBorder(context, this.hasFocus(context), this.isActive(), this.open.getValue() != 0.0);
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        context.setHeight(this.renderer.getHeight(this.open.getValue() != 0.0));
        if (context.isClicked() && button == 0) {
            if (this.toggle != null) {
                this.toggle.toggle();
            }
        }
        else if (context.isHovered() && button == 1 && context.getInterface().getButton(1)) {
            this.open.toggle();
        }
        if (this.open.getValue() == 1.0) {
            Context subContext = this.getSubContext(context, true);
            this.container.getHeight(subContext);
            context.setHeight(this.getRenderHeight(subContext.getSize().height));
            this.updateFocus(context, button);
            boolean onTop = true;
            final Rectangle rect = this.getClipRect(context, subContext.getSize().height);
            if (rect != null) {
                onTop = rect.contains(context.getInterface().getMouse());
            }
            subContext = this.getSubContext(context, onTop);
            this.container.handleButton(subContext, button);
            context.setHeight(this.getRenderHeight(subContext.getSize().height));
            if (subContext.focusReleased()) {
                context.releaseFocus();
            }
        }
        else {
            super.handleButton(context, button);
        }
    }
    
    @Override
    public void handleKey(final Context context, final int scancode) {
        if (this.open.getValue() == 1.0) {
            final Context subContext = this.getSubContext(context, true);
            this.container.handleKey(subContext, scancode);
            context.setHeight(this.getRenderHeight(subContext.getSize().height));
        }
        else {
            super.handleKey(context, scancode);
        }
    }
    
    @Override
    public void handleScroll(final Context context, final int diff) {
        if (this.open.getValue() == 1.0) {
            final Context subContext = this.getSubContext(context, true);
            this.container.handleKey(subContext, diff);
            context.setHeight(this.getRenderHeight(subContext.getSize().height));
            if (subContext.isHovered()) {
                this.scrollPosition += diff;
                if (this.scrollPosition > this.childHeight - this.containerHeight) {
                    this.scrollPosition = this.childHeight - this.containerHeight;
                }
                if (this.scrollPosition < 0) {
                    this.scrollPosition = 0;
                }
            }
        }
        else {
            super.handleKey(context, diff);
        }
    }
    
    @Override
    public void getHeight(final Context context) {
        if (this.open.getValue() != 0.0) {
            final Context subContext = this.getSubContext(context, true);
            this.container.getHeight(subContext);
            context.setHeight(this.getRenderHeight(subContext.getSize().height));
        }
        else {
            super.getHeight(context);
        }
    }
    
    @Override
    public void enter(final Context context) {
        if (this.open.getValue() == 1.0) {
            final Context subContext = this.getSubContext(context, true);
            this.container.enter(subContext);
            context.setHeight(this.getRenderHeight(subContext.getSize().height));
        }
        else {
            super.enter(context);
        }
    }
    
    @Override
    public void exit(final Context context) {
        if (this.open.getValue() == 1.0) {
            final Context subContext = this.getSubContext(context, true);
            this.container.exit(subContext);
            context.setHeight(this.getRenderHeight(subContext.getSize().height));
        }
        else {
            super.exit(context);
        }
    }
    
    protected boolean isActive() {
        return this.toggle == null || this.toggle.isOn();
    }
    
    protected int getContainerOffset() {
        if (this.scrollPosition > this.childHeight - this.containerHeight) {
            this.scrollPosition = this.childHeight - this.containerHeight;
        }
        if (this.scrollPosition < 0) {
            this.scrollPosition = 0;
        }
        return (int)(this.renderer.getHeight(this.open.getValue() != 0.0) - this.scrollPosition - (1.0 - this.open.getValue()) * this.containerHeight);
    }
    
    protected int getScrollHeight(final int childHeight) {
        return childHeight;
    }
    
    protected int getRenderHeight(final int childHeight) {
        this.childHeight = childHeight;
        this.containerHeight = this.getScrollHeight(childHeight);
        this.scroll = (childHeight > this.containerHeight);
        if (this.scrollPosition > childHeight - this.containerHeight) {
            this.scrollPosition = childHeight - this.containerHeight;
        }
        if (this.scrollPosition < 0) {
            this.scrollPosition = 0;
        }
        return (int)(this.containerHeight * this.open.getValue() + this.renderer.getHeight(this.open.getValue() != 0.0) + this.renderer.getBottomBorder());
    }
    
    protected Rectangle getClipRect(final Context context, final int height) {
        return new Rectangle(context.getPos().x + this.renderer.getLeftBorder(this.scroll), context.getPos().y + this.renderer.getHeight(this.open.getValue() != 0.0), context.getSize().width - this.renderer.getLeftBorder(this.scroll) - this.renderer.getRightBorder(this.scroll), this.getRenderHeight(height) - this.renderer.getHeight(this.open.getValue() != 0.0) - this.renderer.getBottomBorder());
    }
    
    @Override
    public void toggle() {
        this.open.toggle();
        if (!this.open.isOn()) {
            this.container.releaseFocus();
        }
    }
    
    @Override
    public boolean isOn() {
        return this.open.isOn();
    }
    
    protected Context getSubContext(final Context context, final boolean onTop) {
        return new Context(context, this.renderer.getLeftBorder(this.scroll), this.renderer.getRightBorder(this.scroll), this.getContainerOffset(), this.hasFocus(context), onTop);
    }
}
