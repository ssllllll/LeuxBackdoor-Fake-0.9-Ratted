// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event;

import me.zero.alpine.fork.bus.EventManager;
import me.zero.alpine.fork.bus.EventBus;

public class EventClientBus
{
    public static EventBus EVENT_BUS;
    
    static {
        EventClientBus.EVENT_BUS = new EventManager();
    }
}
