// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.entity.Entity;
import me.sazked.leux.client.event.EventCancellable;

public class EventEntityRemoved extends EventCancellable
{
    public Entity entity;
    
    public Entity get_entity() {
        return this.entity;
    }
    
    public EventEntityRemoved(final Entity obf) {
        this.entity = obf;
    }
}
