// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.util.EnumHandSide;
import me.sazked.leux.client.event.EventCancellable;

public class EventFirstPerson extends EventCancellable
{
    public EnumHandSide handSide;
    
    public EventFirstPerson(final EnumHandSide obf) {
        this.handSide = obf;
    }
    
    public EnumHandSide getHandSide() {
        return this.handSide;
    }
}
