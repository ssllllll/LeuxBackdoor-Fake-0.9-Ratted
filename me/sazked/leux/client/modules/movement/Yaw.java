// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Yaw extends Module
{
    public Setting yaw;
    
    @Override
    public void update() {
        Yaw.mc.player.rotationYaw = (float)this.yaw.get_value(1);
    }
    
    public Yaw() {
        super(Category.movement);
        this.yaw = this.create("Pitch", "Pitch", 180, 0, 360);
        this.name = "Yaw";
        this.tag = "Yaw";
        this.description = "xd";
    }
}
