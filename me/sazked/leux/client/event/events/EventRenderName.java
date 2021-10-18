// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.client.entity.AbstractClientPlayer;
import me.sazked.leux.client.event.EventCancellable;

public class EventRenderName extends EventCancellable
{
    public String Name;
    public double Y;
    public double X;
    public AbstractClientPlayer Entity;
    public double DistanceSq;
    public double Z;
    
    public EventRenderName(final AbstractClientPlayer obf, double obf, double obf, double obf, final String obf, final double obf) {
        this.Entity = obf;
        obf = this.X;
        obf = this.Y;
        obf = this.Z;
        this.Name = obf;
        this.DistanceSq = obf;
    }
}
