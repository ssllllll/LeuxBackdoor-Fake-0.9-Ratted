// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event;

import net.minecraft.client.Minecraft;
import me.zero.alpine.fork.event.type.Cancellable;

public class EventCancellable extends Cancellable
{
    public float partial_ticks;
    public Era era_switch;
    
    public float get_partial_ticks() {
        return this.partial_ticks;
    }
    
    public EventCancellable() {
        this.era_switch = Era.EVENT_PRE;
        this.partial_ticks = Minecraft.getMinecraft().getRenderPartialTicks();
    }
    
    public Era get_era() {
        return this.era_switch;
    }
    
    public enum Era
    {
        EVENT_POST, 
        EVENT_PERI, 
        EVENT_PRE;
        
        public static Era[] $VALUES;
        
        static {
            Era.$VALUES = new Era[] { Era.EVENT_PRE, Era.EVENT_PERI, Era.EVENT_POST };
        }
    }
}
