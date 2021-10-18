// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import java.math.BigDecimal;
import net.minecraft.network.play.server.SPacketUseBed;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import me.sazked.leux.Leux;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import java.math.RoundingMode;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.math.Vec3d;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import java.util.Map;
import java.text.DecimalFormat;
import java.util.Queue;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Announcer extends Module
{
    public Setting min_distance;
    public Setting suffix;
    public Setting queue_size;
    public static Queue<String> message_q;
    public static DecimalFormat df;
    public Setting smol;
    public Setting movement_string;
    public static Map<String, Integer> consumed_items;
    @EventHandler
    public Listener<EventPacket.SendPacket> send_listener;
    public static int delay_count;
    public Setting delay;
    public static Vec3d thisTickPos;
    public static float lostHealth;
    public Setting units;
    public static Map<String, Integer> placed_blocks;
    public static float gainedHealth;
    public static float lastTickHealth;
    public Setting max_distance;
    public boolean first_run;
    public static float thisTickHealth;
    public static Map<String, Integer> mined_blocks;
    @EventHandler
    public Listener<EventPacket.ReceivePacket> receive_listener;
    public static Vec3d lastTickPos;
    public static Map<String, Integer> dropped_items;
    public static double distanceTraveled;
    
    public void queue_message(final String s) {
        if (Announcer.message_q.size() > this.queue_size.get_value(1)) {
            return;
        }
        Announcer.message_q.add(s);
    }
    
    public void composeGameTickData() {
        if (this.first_run) {
            this.first_run = false;
            return;
        }
        if (Announcer.distanceTraveled >= Double.longBitsToDouble(Double.doubleToLongBits(6.514197300391673) ^ 0x7FEA0E89BCB37EB1L)) {
            if (Announcer.distanceTraveled < this.delay.get_value(1) * this.min_distance.get_value(1)) {
                return;
            }
            if (Announcer.distanceTraveled > this.delay.get_value(1) * this.max_distance.get_value(1)) {
                Announcer.distanceTraveled = Double.longBitsToDouble(Double.doubleToLongBits(1.7295632505092698E307) ^ 0x7FB8A13A75254DEFL);
                return;
            }
            final CharSequence obf = new StringBuilder();
            if (this.movement_string.in("Aha x")) {
                ((StringBuilder)obf).append("aha x, I just traveled ");
            }
            if (this.movement_string.in("FUCK")) {
                ((StringBuilder)obf).append("FUCK, I just fucking traveled ");
            }
            if (this.movement_string.in("Leyta")) {
                ((StringBuilder)obf).append("leyta bitch, I just traveled ");
            }
            if (this.units.in("Feet")) {
                ((StringBuilder)obf).append(round(Announcer.distanceTraveled * Double.longBitsToDouble(Double.doubleToLongBits(0.7900825368000645) ^ 0x7FE3774F3E13F1C7L), 2));
                if ((int)Announcer.distanceTraveled == Double.longBitsToDouble(Double.doubleToLongBits(7.020842777025447) ^ 0x7FEC1557CF16BA7CL)) {
                    ((StringBuilder)obf).append(" Foot");
                }
                else {
                    ((StringBuilder)obf).append(" Feet");
                }
            }
            if (this.units.in("Yards")) {
                ((StringBuilder)obf).append(round(Announcer.distanceTraveled * Double.longBitsToDouble(Double.doubleToLongBits(15.80532068580243) ^ 0x7FDEE330489EDDF7L), 2));
                if ((int)Announcer.distanceTraveled == Double.longBitsToDouble(Double.doubleToLongBits(53.5557021698388) ^ 0x7FBAC7213FAAE30FL)) {
                    ((StringBuilder)obf).append(" Yard");
                }
                else {
                    ((StringBuilder)obf).append(" Yards");
                }
            }
            if (this.units.in("Inches")) {
                ((StringBuilder)obf).append(round(Announcer.distanceTraveled * Double.longBitsToDouble(Double.doubleToLongBits(0.0251857212360593) ^ 0x7FDA65150CD621F9L), 2));
                if ((int)Announcer.distanceTraveled == Double.longBitsToDouble(Double.doubleToLongBits(10.5360539046566) ^ 0x7FD51275A84AC951L)) {
                    ((StringBuilder)obf).append(" Inch");
                }
                else {
                    ((StringBuilder)obf).append(" Inches");
                }
            }
            if (this.units.in("Meters")) {
                ((StringBuilder)obf).append(round(Announcer.distanceTraveled, 2));
                if ((int)Announcer.distanceTraveled == Double.longBitsToDouble(Double.doubleToLongBits(28.272357483370453) ^ 0x7FCC45B93853E59FL)) {
                    ((StringBuilder)obf).append(" Meter");
                }
                else {
                    ((StringBuilder)obf).append(" Meters");
                }
            }
            this.queue_message(obf.toString());
            Announcer.distanceTraveled = Double.longBitsToDouble(Double.doubleToLongBits(2.8013218757133893E307) ^ 0x7FC3F231E52938E3L);
        }
        if (Announcer.lostHealth != Float.intBitsToFloat(Float.floatToIntBits(9.304479E37f) ^ 0x7E8BFF8D)) {
            final CharSequence obf = "HECK! I just lost " + Announcer.df.format(Announcer.lostHealth) + " health D:";
            this.queue_message((String)obf);
            Announcer.lostHealth = Float.intBitsToFloat(Float.floatToIntBits(7.3441363E37f) ^ 0x7E5D012B);
        }
        if (Announcer.gainedHealth != Float.intBitsToFloat(Float.floatToIntBits(1.5783172E38f) ^ 0x7EED7A91)) {
            final CharSequence obf = "#Leuxmode I now have " + Announcer.df.format(Announcer.gainedHealth) + " more health";
            this.queue_message((String)obf);
            Announcer.gainedHealth = Float.intBitsToFloat(Float.floatToIntBits(6.9682363E37f) ^ 0x7E51B157);
        }
    }
    
    public void lambda$new$1(final EventPacket.SendPacket sendPacket) {
        if (Announcer.mc.world == null) {
            return;
        }
        if (sendPacket.get_packet() instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)sendPacket.get_packet();
            if (Announcer.mc.player.getHeldItemMainhand().getItem() != Items.AIR && (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.DROP_ITEM) || cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.DROP_ALL_ITEMS))) {
                final String displayName = Announcer.mc.player.inventory.getCurrentItem().getDisplayName();
                if (Announcer.dropped_items.containsKey(displayName)) {
                    Announcer.dropped_items.put(displayName, Announcer.dropped_items.get(displayName) + 1);
                }
                else {
                    Announcer.dropped_items.put(displayName, 1);
                }
            }
            if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                final String localizedName = Announcer.mc.world.getBlockState(cPacketPlayerDigging.getPosition()).getBlock().getLocalizedName();
                if (Announcer.mined_blocks.containsKey(localizedName)) {
                    Announcer.mined_blocks.put(localizedName, Announcer.mined_blocks.get(localizedName) + 1);
                }
                else {
                    Announcer.mined_blocks.put(localizedName, 1);
                }
            }
        }
        else {
            if (sendPacket.get_packet() instanceof CPacketUpdateSign) {
                this.queue_message("I just updated a sign with some epic text");
            }
            if (sendPacket.get_packet() instanceof CPacketPlayerTryUseItemOnBlock) {
                final ItemStack currentItem = Announcer.mc.player.inventory.getCurrentItem();
                if (currentItem.isEmpty()) {
                    return;
                }
                if (currentItem.getItem() instanceof ItemBlock) {
                    final String displayName2 = Announcer.mc.player.inventory.getCurrentItem().getDisplayName();
                    if (Announcer.placed_blocks.containsKey(displayName2)) {
                        Announcer.placed_blocks.put(displayName2, Announcer.placed_blocks.get(displayName2) + 1);
                    }
                    else {
                        Announcer.placed_blocks.put(displayName2, 1);
                    }
                    return;
                }
                if (currentItem.getItem() == Items.END_CRYSTAL) {
                    final String s = "Crystals";
                    if (Announcer.placed_blocks.containsKey(s)) {
                        Announcer.placed_blocks.put(s, Announcer.placed_blocks.get(s) + 1);
                    }
                    else {
                        Announcer.placed_blocks.put(s, 1);
                    }
                }
            }
        }
    }
    
    public void send_cycle() {
        ++Announcer.delay_count;
        if (Announcer.delay_count > this.delay.get_value(1) * 20) {
            Announcer.delay_count = 0;
            this.composeGameTickData();
            this.composeEventData();
            final Iterator<String> iterator = Announcer.message_q.iterator();
            if (iterator.hasNext()) {
                final String obf = iterator.next();
                this.send_message(obf);
                Announcer.message_q.remove(obf);
            }
        }
    }
    
    @Override
    public void enable() {
        this.first_run = true;
        (Announcer.df = new DecimalFormat("#.#")).setRoundingMode(RoundingMode.CEILING);
        final Vec3d obf = Announcer.thisTickPos = (Announcer.lastTickPos = Announcer.mc.player.getPositionVector());
        Announcer.distanceTraveled = Double.longBitsToDouble(Double.doubleToLongBits(6.284487802844065E307) ^ 0x7FD65F9EF4F2D647L);
        final float obf2 = Announcer.thisTickHealth = (Announcer.lastTickHealth = Announcer.mc.player.getHealth() + Announcer.mc.player.getAbsorptionAmount());
        Announcer.lostHealth = Float.intBitsToFloat(Float.floatToIntBits(3.0323786E38f) ^ 0x7F64217D);
        Announcer.gainedHealth = Float.intBitsToFloat(Float.floatToIntBits(3.0950777E38f) ^ 0x7F68D907);
        Announcer.delay_count = 0;
    }
    
    public void get_tick_data() {
        Announcer.lastTickPos = Announcer.thisTickPos;
        Announcer.thisTickPos = Announcer.mc.player.getPositionVector();
        Announcer.distanceTraveled += Announcer.thisTickPos.distanceTo(Announcer.lastTickPos);
        Announcer.lastTickHealth = Announcer.thisTickHealth;
        Announcer.thisTickHealth = Announcer.mc.player.getHealth() + Announcer.mc.player.getAbsorptionAmount();
        final float n = Announcer.thisTickHealth - Announcer.lastTickHealth;
        if (n < 0.0f) {
            Announcer.lostHealth += n * -1.0f;
        }
        else {
            Announcer.gainedHealth += n;
        }
    }
    
    public Announcer() {
        super(Category.misc);
        this.min_distance = this.create("Min Distance", "AnnouncerMinDist", 12, 1, 100);
        this.max_distance = this.create("Max Distance", "AnnouncerMaxDist", 144, 12, 1200);
        this.delay = this.create("Delay Seconds", "AnnouncerDelay", 4, 0, 20);
        this.queue_size = this.create("Queue Size", "AnnouncerQueueSize", 5, 1, 20);
        this.units = this.create("Units", "AnnouncerUnits", "Meters", this.combobox("Meters", "Feet", "Yards", "Inches"));
        this.movement_string = this.create("Movement", "AnnouncerMovement", "FUCK", this.combobox("Aha x", "Leyta", "FUCK"));
        this.suffix = this.create("Suffix", "AnnouncerSuffix", true);
        this.smol = this.create("Small Text", "AnnouncerSmallText", false);
        this.receive_listener = new Listener<EventPacket.ReceivePacket>(this::lambda$new$0, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.send_listener = new Listener<EventPacket.SendPacket>(this::lambda$new$1, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Announcer";
        this.tag = "Announcer";
        this.description = "how to get muted 101";
    }
    
    @Override
    public void update() {
        if (Announcer.mc.player == null || Announcer.mc.world == null) {
            this.set_disable();
            return;
        }
        try {
            this.get_tick_data();
        }
        catch (Exception obf) {
            this.set_disable();
            return;
        }
        this.send_cycle();
    }
    
    public void send_message(String obf) {
        if (this.suffix.get_value(true)) {
            final String obf2 = " \u269d ";
            obf = obf + obf2 + Leux.smoth("sponsored by leux");
        }
        if (this.smol.get_value(true)) {
            obf = Leux.smoth(obf.toLowerCase());
        }
        Announcer.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(obf.replaceAll("§", "")));
    }
    
    public void composeEventData() {
        for (final Map.Entry<String, Integer> obf : Announcer.mined_blocks.entrySet()) {
            this.queue_message("We be mining " + obf.getValue() + " " + obf.getKey() + " out here");
            Announcer.mined_blocks.remove(obf.getKey());
        }
        for (final Map.Entry<String, Integer> obf : Announcer.placed_blocks.entrySet()) {
            this.queue_message("We be placing " + obf.getValue() + " " + obf.getKey() + " out here");
            Announcer.placed_blocks.remove(obf.getKey());
        }
        for (final Map.Entry<String, Integer> obf : Announcer.dropped_items.entrySet()) {
            this.queue_message("I just dropped " + obf.getValue() + " " + obf.getKey() + ", whoops!");
            Announcer.dropped_items.remove(obf.getKey());
        }
        for (final Map.Entry<String, Integer> obf : Announcer.consumed_items.entrySet()) {
            this.queue_message("NOM NOM, I just ate " + obf.getValue() + " " + obf.getKey() + ", yummy");
            Announcer.consumed_items.remove(obf.getKey());
        }
    }
    
    static {
        Announcer.df = new DecimalFormat();
        Announcer.message_q = new ConcurrentLinkedQueue<String>();
        Announcer.mined_blocks = new ConcurrentHashMap<String, Integer>();
        Announcer.placed_blocks = new ConcurrentHashMap<String, Integer>();
        Announcer.dropped_items = new ConcurrentHashMap<String, Integer>();
        Announcer.consumed_items = new ConcurrentHashMap<String, Integer>();
    }
    
    public void lambda$new$0(final EventPacket.ReceivePacket receivePacket) {
        if (Announcer.mc.world == null) {
            return;
        }
        if (receivePacket.get_packet() instanceof SPacketUseBed) {
            this.queue_message("I am going to bed now, goodnight");
        }
    }
    
    public static double round(final double obf, final int obf) {
        final BigDecimal obf2 = new BigDecimal(obf);
        final BigDecimal obf3 = obf2.setScale(obf, 4);
        return obf3.doubleValue();
    }
}
