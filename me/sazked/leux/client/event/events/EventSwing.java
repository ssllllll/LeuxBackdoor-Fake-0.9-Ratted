// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.util.EnumHand;
import me.sazked.leux.client.event.EventCancellable;

public class EventSwing extends EventCancellable
{
    public EnumHand hand;
    
    public EventSwing(final EnumHand obf) {
        this.hand = obf;
    }
}
