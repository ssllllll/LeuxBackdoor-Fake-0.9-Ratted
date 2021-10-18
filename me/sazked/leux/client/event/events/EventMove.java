// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.entity.MoverType;
import me.sazked.leux.client.event.EventCancellable;

public class EventMove extends EventCancellable
{
    public double y;
    public MoverType move_type;
    public double z;
    public double x;
    
    public EventMove(final MoverType obf, final double obf, final double obf, final double obf) {
        this.move_type = obf;
        this.x = obf;
        this.y = obf;
        this.z = obf;
    }
    
    public double get_z() {
        return this.z;
    }
    
    public void set_y(final double y) {
        this.y = y;
    }
    
    public double get_x() {
        return this.x;
    }
    
    public void set_z(final double z) {
        this.z = z;
    }
    
    public void set_move_type(final MoverType obf) {
        this.move_type = obf;
    }
    
    public double get_y() {
        return this.y;
    }
    
    public void set_x(final double x) {
        this.x = x;
    }
    
    public MoverType get_move_type() {
        return this.move_type;
    }
}
