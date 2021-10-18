// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import me.sazked.leux.client.event.EventCancellable;

public class EventPlayerJump extends EventCancellable
{
    public double motion_x;
    public double motion_y;
    
    public EventPlayerJump(final double obf, final double obf) {
        this.motion_x = obf;
        this.motion_y = obf;
    }
}
