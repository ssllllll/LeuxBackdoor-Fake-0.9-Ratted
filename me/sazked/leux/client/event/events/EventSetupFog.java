// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import me.sazked.leux.client.event.EventCancellable;

public class EventSetupFog extends EventCancellable
{
    public float partial_ticks;
    public int start_coords;
    
    public EventSetupFog(final int obf, final float obf) {
        this.start_coords = obf;
        this.partial_ticks = obf;
    }
}
