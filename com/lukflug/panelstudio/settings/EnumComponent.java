// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.settings;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.FocusableComponent;

public class EnumComponent extends FocusableComponent
{
    protected EnumSetting setting;
    
    public EnumComponent(final String title, final String description, final Renderer renderer, final EnumSetting setting) {
        super(title, description, renderer);
        this.setting = setting;
    }
    
    @Override
    public void render(final Context context) {
        super.render(context);
        this.renderer.renderTitle(context, this.title + ": §7" + this.setting.getValueName(), this.hasFocus(context));
    }
    
    @Override
    public void handleButton(final Context context, final int button) {
        super.handleButton(context, button);
        if (button == 0 && context.isClicked()) {
            this.setting.increment();
        }
    }
}
