// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import net.minecraft.util.MovementInput;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import me.zero.alpine.fork.listener.EventHandler;
import net.minecraftforge.client.event.InputUpdateEvent;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class NoSlowDown extends Module
{
    @EventHandler
    public Listener<InputUpdateEvent> eventListener;
    
    public NoSlowDown() {
        super(Category.movement);
        this.eventListener = new Listener<InputUpdateEvent>(NoSlowDown::lambda$new$0, (Predicate<InputUpdateEvent>[])new Predicate[0]);
        this.name = "No SlowDown";
        this.tag = "NoSlow";
        this.description = "Just no slows";
    }
    
    public static void lambda$new$0(final InputUpdateEvent inputUpdateEvent) {
        if (NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            final MovementInput movementInput = inputUpdateEvent.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            final MovementInput movementInput2 = inputUpdateEvent.getMovementInput();
            movementInput2.moveForward *= 5.0f;
        }
    }
}
