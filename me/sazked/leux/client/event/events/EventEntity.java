// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.entity.Entity;
import me.sazked.leux.client.event.EventCancellable;

public class EventEntity extends EventCancellable
{
    public Entity entity;
    
    public Entity get_entity() {
        return this.entity;
    }
    
    public EventEntity(final Entity obf) {
        this.entity = obf;
    }
    
    public static class EventColision extends EventEntity
    {
        public double z;
        public double x;
        public double y;
        
        public double get_y() {
            return this.y;
        }
        
        public double get_z() {
            return this.z;
        }
        
        public void set_z(final double n) {
            this.z = this.z;
        }
        
        public EventColision(final Entity obf, final double obf, final double obf, final double obf) {
            super(obf);
            this.x = obf;
            this.y = obf;
            this.z = obf;
        }
        
        public double get_x() {
            return this.x;
        }
        
        public void set_y(final double y) {
            this.y = y;
        }
        
        public void set_x(final double x) {
            this.x = x;
        }
    }
}
