// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.settings;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.FocusableComponent;

public class KeybindComponent extends FocusableComponent
{
    protected KeybindSetting keybind;
    
    public KeybindComponent(final Renderer renderer, final KeybindSetting keybind) {
        super("Keybind: §7", null, renderer);
        this.keybind = keybind;
    }
    
    @Override
    public void render(final Context context) {
        super.render(context);
        String text = this.title + this.keybind.getKeyName();
        if (this.hasFocus(context)) {
            text = this.title + "...";
        }
        this.renderer.renderTitle(context, text, this.hasFocus(context));
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        super.handleButton(context, button);
        context.setHeight(this.renderer.getHeight(false));
        final boolean isSelected = this.hasFocus(context);
        super.handleButton(context, button);
        if (isSelected && !this.hasFocus(context)) {
            this.keybind.setKey(0);
        }
    }
    
    @Override
    public void handleKey(final Context context, final int scancode) {
        super.handleKey(context, scancode);
        if (this.hasFocus(context)) {
            this.keybind.setKey(scancode);
            this.releaseFocus();
        }
    }
    
    @Override
    public void exit(final Context context) {
        super.exit(context);
        if (this.hasFocus(context)) {
            this.keybind.setKey(0);
            this.releaseFocus();
        }
    }
}
