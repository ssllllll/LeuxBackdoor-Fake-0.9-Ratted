// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.modules.Module;

public class AutoWalk extends Module
{
    @Override
    public void update() {
        if (!AutoWalk.mc.gameSettings.keyBindForward.isPressed()) {
            AutoWalk.mc.gameSettings.keyBindForward.pressed = true;
        }
    }
    
    public AutoWalk() {
        super(Category.movement);
        this.name = "Auto Walk";
        this.tag = "AutoWalk";
        this.description = "xd";
    }
    
    @Override
    public void disable() {
        AutoWalk.mc.gameSettings.keyBindForward.pressed = false;
    }
}
