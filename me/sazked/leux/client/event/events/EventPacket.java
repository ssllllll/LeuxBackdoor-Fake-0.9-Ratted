// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.network.Packet;
import me.sazked.leux.client.event.EventCancellable;

public class EventPacket extends EventCancellable
{
    public Packet packet;
    
    public Packet get_packet() {
        return this.packet;
    }
    
    public EventPacket(final Packet obf) {
        this.packet = obf;
    }
    
    public static class ReceivePacket extends EventPacket
    {
        public ReceivePacket(final Packet obf) {
            super(obf);
        }
    }
    
    public static class SendPacket extends EventPacket
    {
        public SendPacket(final Packet obf) {
            super(obf);
        }
    }
}
