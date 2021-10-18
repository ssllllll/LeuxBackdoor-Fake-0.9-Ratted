// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import me.sazked.leux.client.event.EventCancellable;

public class EventPlayerTravel extends EventCancellable
{
    public float Vertical;
    public float Forward;
    public float Strafe;
    
    public EventPlayerTravel(final float obf, final float obf, final float obf) {
        this.Strafe = obf;
        this.Vertical = obf;
        this.Forward = obf;
    }
}
