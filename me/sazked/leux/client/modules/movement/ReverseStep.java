// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.modules.Module;

public class ReverseStep extends Module
{
    public ReverseStep() {
        super(Category.movement);
        this.name = "ReverseStep";
        this.tag = "ReverseStep";
        this.description = "Fast Fall";
    }
    
    @Override
    public void update() {
        if (!ReverseStep.mc.player.onGround || ReverseStep.mc.player.isOnLadder() || ReverseStep.mc.player.isInWater() || ReverseStep.mc.player.isInLava() || ReverseStep.mc.player.movementInput.jump || ReverseStep.mc.player.noClip) {
            return;
        }
        if (ReverseStep.mc.player.moveForward == 0.0f && ReverseStep.mc.player.moveStrafing == 0.0f) {
            return;
        }
        ReverseStep.mc.player.motionY = -1.0;
    }
}
