// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.tabgui;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.settings.Toggleable;

public class TabGUIItem implements TabGUIComponent
{
    protected String title;
    protected Toggleable toggle;
    
    public TabGUIItem(final String title, final Toggleable toggle) {
        this.title = title;
        this.toggle = toggle;
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public void render(final Context context) {
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
    }
    
    @Override
    public void handleKey(final Context context, final int scancode) {
    }
    
    @Override
    public void handleScroll(final Context context, final int diff) {
    }
    
    @Override
    public void getHeight(final Context context) {
    }
    
    @Override
    public void enter(final Context context) {
    }
    
    @Override
    public void exit(final Context context) {
    }
    
    @Override
    public boolean isActive() {
        return this.toggle.isOn();
    }
    
    @Override
    public boolean select() {
        this.toggle.toggle();
        return false;
    }
    
    @Override
    public void releaseFocus() {
    }
}
