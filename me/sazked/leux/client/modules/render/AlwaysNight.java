// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import net.minecraft.network.play.server.SPacketTimeUpdate;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventRender;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class AlwaysNight extends Module
{
    @EventHandler
    public Listener<EventRender> on_render;
    @EventHandler
    public Listener<EventPacket.ReceivePacket> recieve_packet;
    
    public static void lambda$new$0(final EventRender obf) {
        if (AlwaysNight.mc.world == null) {
            return;
        }
        AlwaysNight.mc.world.setWorldTime((long)1537725383 ^ 0x5BA79597L);
    }
    
    public AlwaysNight() {
        super(Category.render);
        this.on_render = new Listener<EventRender>(AlwaysNight::lambda$new$0, (Predicate<EventRender>[])new Predicate[0]);
        this.recieve_packet = new Listener<EventPacket.ReceivePacket>(AlwaysNight::lambda$new$1, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.name = "BlockHighlight";
        this.tag = "AlwaysNight";
        this.description = "see even less";
    }
    
    @Override
    public void update() {
        if (AlwaysNight.mc.world == null) {
            return;
        }
        AlwaysNight.mc.world.setWorldTime(18000L);
    }
    
    public static void lambda$new$1(final EventPacket.ReceivePacket receivePacket) {
        if (receivePacket.get_packet() instanceof SPacketTimeUpdate) {
            receivePacket.cancel();
        }
    }
}
