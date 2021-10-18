// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.entity.EntityLivingBase;
import me.sazked.leux.client.event.EventCancellable;

public class EventRenderArmorOverlay extends EventCancellable
{
    public EntityLivingBase entity;
    
    public EventRenderArmorOverlay(final EntityLivingBase obf) {
        this.entity = obf;
    }
}
