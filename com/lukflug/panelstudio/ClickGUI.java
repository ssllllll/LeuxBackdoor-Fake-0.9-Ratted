// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio;

import com.lukflug.panelstudio.settings.Toggleable;
import java.util.Iterator;
import java.util.ArrayList;
import com.lukflug.panelstudio.theme.DescriptionRenderer;
import java.util.List;

public class ClickGUI implements PanelManager
{
    protected List<FixedComponent> components;
    protected List<FixedComponent> permanentComponents;
    protected Interface inter;
    protected DescriptionRenderer descriptionRenderer;
    
    public ClickGUI(final Interface inter, final DescriptionRenderer descriptionRenderer) {
        this.components = new ArrayList<FixedComponent>();
        this.permanentComponents = new ArrayList<FixedComponent>();
        this.inter = inter;
        this.descriptionRenderer = descriptionRenderer;
    }
    
    public List<FixedComponent> getComponents() {
        return this.permanentComponents;
    }
    
    public void addComponent(final FixedComponent component) {
        this.components.add(component);
        this.permanentComponents.add(component);
    }
    
    @Override
    public void showComponent(final FixedComponent component) {
        if (!this.components.contains(component)) {
            this.components.add(component);
            component.enter(this.getContext(component, false));
        }
    }
    
    @Override
    public void hideComponent(final FixedComponent component) {
        if (!this.permanentComponents.contains(component) && this.components.remove(component)) {
            component.exit(this.getContext(component, false));
        }
    }
    
    public void render() {
        final List<FixedComponent> components = new ArrayList<FixedComponent>();
        for (final FixedComponent component : this.components) {
            components.add(component);
        }
        Context descriptionContext = null;
        int highest = 0;
        FixedComponent focusComponent = null;
        for (int i = components.size() - 1; i >= 0; --i) {
            final FixedComponent component2 = components.get(i);
            final Context context = this.getContext(component2, true);
            component2.getHeight(context);
            if (context.isHovered()) {
                highest = i;
                break;
            }
        }
        for (int i = 0; i < components.size(); ++i) {
            final FixedComponent component2 = components.get(i);
            final Context context = this.getContext(component2, i >= highest);
            component2.render(context);
            if (context.foucsRequested()) {
                focusComponent = component2;
            }
            if (context.isHovered() && context.getDescription() != null) {
                descriptionContext = context;
            }
        }
        if (focusComponent != null && this.components.remove(focusComponent)) {
            this.components.add(focusComponent);
        }
        if (descriptionContext != null && this.descriptionRenderer != null) {
            this.descriptionRenderer.renderDescription(descriptionContext);
        }
    }
    
    public void handleButton(final int button) {
        this.doComponentLoop((context, component) -> component.handleButton(context, button));
    }
    
    public void handleKey(final int scancode) {
        this.doComponentLoop((context, component) -> component.handleKey(context, scancode));
    }
    
    public void handleScroll(final int diff) {
        this.doComponentLoop((context, component) -> component.handleScroll(context, diff));
    }
    
    public void enter() {
        this.doComponentLoop((context, component) -> component.enter(context));
    }
    
    public void exit() {
        this.doComponentLoop((context, component) -> component.exit(context));
    }
    
    public void saveConfig(final ConfigList config) {
        config.begin(false);
        for (final FixedComponent component : this.getComponents()) {
            final PanelConfig cf = config.addPanel(component.getTitle());
            if (cf != null) {
                component.saveConfig(this.inter, cf);
            }
        }
        config.end(false);
    }
    
    public void loadConfig(final ConfigList config) {
        config.begin(true);
        for (final FixedComponent component : this.getComponents()) {
            final PanelConfig cf = config.getPanel(component.getTitle());
            if (cf != null) {
                component.loadConfig(this.inter, cf);
            }
        }
        config.end(true);
    }
    
    protected Context getContext(final FixedComponent component, final boolean highest) {
        return new Context(this.inter, component.getWidth(this.inter), component.getPosition(this.inter), true, highest);
    }
    
    @Override
    public Toggleable getComponentToggleable(final FixedComponent component) {
        return new Toggleable() {
            @Override
            public void toggle() {
                if (this.isOn()) {
                    ClickGUI.this.hideComponent(component);
                }
                else {
                    ClickGUI.this.showComponent(component);
                }
            }
            
            @Override
            public boolean isOn() {
                return ClickGUI.this.components.contains(component);
            }
        };
    }
    
    protected void doComponentLoop(final LoopFunction function) {
        final List<FixedComponent> components = new ArrayList<FixedComponent>();
        for (final FixedComponent component : this.components) {
            components.add(component);
        }
        boolean highest = true;
        FixedComponent focusComponent = null;
        for (int i = components.size() - 1; i >= 0; --i) {
            final FixedComponent component2 = components.get(i);
            final Context context = this.getContext(component2, highest);
            function.loop(context, component2);
            if (context.isHovered()) {
                highest = false;
            }
            if (context.foucsRequested()) {
                focusComponent = component2;
            }
        }
        if (focusComponent != null && this.components.remove(focusComponent)) {
            this.components.add(focusComponent);
        }
    }
    
    protected interface LoopFunction
    {
        void loop(final Context p0, final FixedComponent p1);
    }
}
