// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.client.gui.ScaledResolution;
import me.sazked.leux.client.event.EventCancellable;

public class EventGameOverlay extends EventCancellable
{
    public ScaledResolution scaled_resolution;
    public float partial_ticks;
    
    public ScaledResolution get_scaled_resoltion() {
        return this.scaled_resolution;
    }
    
    public EventGameOverlay(final float obf, final ScaledResolution obf) {
        this.partial_ticks = obf;
        this.scaled_resolution = obf;
    }
}
