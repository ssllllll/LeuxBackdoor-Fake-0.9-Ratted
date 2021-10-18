// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.network.play.server.SPacketEntityStatus;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import java.util.HashMap;
import me.sazked.leux.client.modules.Module;

public class PopAnnouncer extends Module
{
    public static HashMap<String, Integer> totem_pop_counter;
    @EventHandler
    public Listener<EventPacket.ReceivePacket> packet_event;
    
    public PopAnnouncer() {
        super(Category.chat);
        this.packet_event = new Listener<EventPacket.ReceivePacket>(PopAnnouncer::lambda$new$0, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.name = "PopAnnouncer";
        this.tag = "PopAnnouncer";
        this.description = "xd";
    }
    
    public static void lambda$new$0(final EventPacket.ReceivePacket obf) {
        final SPacketEntityStatus obf2;
        if (obf.get_packet() instanceof SPacketEntityStatus && (obf2 = (SPacketEntityStatus)obf.get_packet()).getOpCode() == 35) {
            final Entity obf3 = obf2.getEntity((World)PopAnnouncer.mc.world);
            int obf4 = 1;
            if (PopAnnouncer.totem_pop_counter.containsKey(obf3.getName())) {
                obf4 = PopAnnouncer.totem_pop_counter.get(obf3.getName());
                PopAnnouncer.totem_pop_counter.put(obf3.getName(), ++obf4);
            }
            else {
                PopAnnouncer.totem_pop_counter.put(obf3.getName(), obf4);
            }
            if (obf3 == PopAnnouncer.mc.player) {
                return;
            }
            PopAnnouncer.mc.player.sendChatMessage(obf3.getName() + " popped " + obf4 + " totems \u0298\u02fd\u0298");
        }
    }
    
    @Override
    public void update() {
        for (final EntityPlayer obf : PopAnnouncer.mc.world.playerEntities) {
            if (PopAnnouncer.totem_pop_counter.containsKey(obf.getName())) {
                if (!obf.isDead && obf.getHealth() > Float.intBitsToFloat(Float.floatToIntBits(2.9316996E38f) ^ 0x7F5C8E7B)) {
                    continue;
                }
                final int obf2 = PopAnnouncer.totem_pop_counter.get(obf.getName());
                PopAnnouncer.totem_pop_counter.remove(obf.getName());
                if (obf == PopAnnouncer.mc.player) {
                    continue;
                }
                PopAnnouncer.mc.player.sendChatMessage(obf.getName() + " died after popping " + obf2 + " totems \u0298\u02fd\u0298");
            }
        }
    }
    
    static {
        PopAnnouncer.totem_pop_counter = new HashMap<String, Integer>();
    }
}
