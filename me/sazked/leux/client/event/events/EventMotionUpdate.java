// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import me.sazked.leux.client.event.EventCancellable;

public class EventMotionUpdate extends EventCancellable
{
    public int stage;
    
    public EventMotionUpdate(final int obf) {
        this.stage = obf;
    }
}
