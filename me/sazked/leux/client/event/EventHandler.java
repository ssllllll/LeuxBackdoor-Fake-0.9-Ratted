// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event;

import java.util.function.Predicate;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.math.MathHelper;
import java.util.Arrays;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.zero.alpine.fork.listener.Listenable;

public class EventHandler implements Listenable
{
    @me.zero.alpine.fork.listener.EventHandler
    public Listener<EventPacket.ReceivePacket> receive_event_packet;
    public static float[] ticks;
    public long last_update_tick;
    public static EventHandler INSTANCE;
    public int next_index;
    
    public void reset_tick() {
        this.next_index = 0;
        this.last_update_tick = -1L;
        Arrays.fill(EventHandler.ticks, 0.0f);
    }
    
    public float get_tick_rate() {
        float n = 0.0f;
        float n2 = 0.0f;
        for (final float n3 : EventHandler.ticks) {
            if (n3 > 0.0f) {
                n2 += n3;
                ++n;
            }
        }
        return MathHelper.clamp(n2 / n, 0.0f, 20.0f);
    }
    
    static {
        EventHandler.ticks = new float[20];
    }
    
    public static void lambda$new$0(final EventPacket.ReceivePacket receivePacket) {
        if (receivePacket.get_packet() instanceof SPacketTimeUpdate) {
            EventHandler.INSTANCE.update_time();
        }
    }
    
    public EventHandler() {
        this.next_index = 0;
        this.receive_event_packet = new Listener<EventPacket.ReceivePacket>(EventHandler::lambda$new$0, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        EventClientBus.EVENT_BUS.subscribe(this);
        this.reset_tick();
    }
    
    public void update_time() {
        if (this.last_update_tick != -1L) {
            EventHandler.ticks[this.next_index % EventHandler.ticks.length] = MathHelper.clamp(20.0f / ((System.currentTimeMillis() - this.last_update_tick) / 1000.0f), 0.0f, 20.0f);
            ++this.next_index;
        }
        this.last_update_tick = System.currentTimeMillis();
    }
}
