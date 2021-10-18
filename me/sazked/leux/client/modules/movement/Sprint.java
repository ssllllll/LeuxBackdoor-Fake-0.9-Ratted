// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Sprint extends Module
{
    public Setting rage;
    
    @Override
    public void update() {
        if (Sprint.mc.player == null) {
            return;
        }
        if (this.rage.get_value(true)) {
            if (Sprint.mc.player.moveForward != 0.0f || Sprint.mc.player.moveStrafing != 0.0f) {
                Sprint.mc.player.setSprinting(true);
                return;
            }
        }
        Sprint.mc.player.setSprinting(Sprint.mc.player.moveForward > 0.0f || Sprint.mc.player.moveStrafing > 0.0f);
    }
    
    public Sprint() {
        super(Category.movement);
        this.rage = this.create("Rage", "SprintRage", true);
        this.name = "Sprint";
        this.tag = "Sprint";
        this.description = "ZOOOOOOOOM";
    }
}
