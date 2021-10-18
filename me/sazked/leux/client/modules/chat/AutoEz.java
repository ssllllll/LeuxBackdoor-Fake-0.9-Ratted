// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import me.sazked.leux.client.util.EzMessageUtil;
import net.minecraft.entity.EntityLivingBase;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import java.util.function.BiConsumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.network.play.client.CPacketUseEntity;
import java.util.Objects;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import java.util.concurrent.ConcurrentHashMap;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoEz extends Module
{
    public Setting discord;
    public Setting custom;
    public static ConcurrentHashMap targeted_players;
    @EventHandler
    public Listener<EventPacket.SendPacket> send_listener;
    public int delay_count;
    @EventHandler
    public Listener<LivingDeathEvent> living_death_listener;
    
    public static void lambda$update$2(final Object obf, final Object obf) {
        if ((int)obf <= 0) {
            AutoEz.targeted_players.remove(obf);
        }
        else {
            AutoEz.targeted_players.put(obf, (int)obf - 1);
        }
    }
    
    public static void add_target(final String s) {
        if (!Objects.equals(s, AutoEz.mc.player.getName())) {
            AutoEz.targeted_players.put(s, 20);
        }
    }
    
    static {
        AutoEz.targeted_players = new ConcurrentHashMap();
    }
    
    public static void lambda$new$0(final EventPacket.SendPacket sendPacket) {
        if (AutoEz.mc.player == null) {
            return;
        }
        if (sendPacket.get_packet() instanceof CPacketUseEntity) {
            final CPacketUseEntity cPacketUseEntity = (CPacketUseEntity)sendPacket.get_packet();
            if (cPacketUseEntity.getAction().equals((Object)CPacketUseEntity.Action.ATTACK)) {
                final Entity entityFromWorld = cPacketUseEntity.getEntityFromWorld((World)AutoEz.mc.world);
                if (entityFromWorld instanceof EntityPlayer) {
                    add_target(entityFromWorld.getName());
                }
            }
        }
    }
    
    @Override
    public void update() {
        for (final Entity obf : AutoEz.mc.world.getLoadedEntityList()) {
            if (obf instanceof EntityPlayer) {
                final EntityPlayer obf2 = (EntityPlayer)obf;
                if (obf2.getHealth() > Float.intBitsToFloat(Float.floatToIntBits(1.1822196E38f) ^ 0x7EB1E171)) {
                    continue;
                }
                if (!AutoEz.targeted_players.containsKey(obf2.getName())) {
                    continue;
                }
                this.announce(obf2.getName());
            }
        }
        AutoEz.targeted_players.forEach(AutoEz::lambda$update$2);
        ++this.delay_count;
    }
    
    public AutoEz() {
        super(Category.misc);
        this.delay_count = 0;
        this.discord = this.create("Discord", "EzDiscord", false);
        this.custom = this.create("Custom", "EzCustom", false);
        this.send_listener = new Listener<EventPacket.SendPacket>(AutoEz::lambda$new$0, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.living_death_listener = new Listener<LivingDeathEvent>(this::lambda$new$1, (Predicate<LivingDeathEvent>[])new Predicate[0]);
        this.name = "AutoGG";
        this.tag = "AutoEz";
        this.description = "Be toxic";
    }
    
    public void lambda$new$1(final LivingDeathEvent obf) {
        if (AutoEz.mc.player == null) {
            return;
        }
        final EntityLivingBase obf2 = obf.getEntityLiving();
        if (obf2 == null) {
            return;
        }
        if (obf2 instanceof EntityPlayer) {
            final EntityPlayer obf3 = (EntityPlayer)obf2;
            if (obf3.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(2.9100739E38f) ^ 0x7F5AEDFC)) {
                if (AutoEz.targeted_players.containsKey(obf3.getName())) {
                    this.announce(obf3.getName());
                }
            }
        }
    }
    
    public void announce(final String obf) {
        if (this.delay_count < 150) {
            return;
        }
        this.delay_count = 0;
        AutoEz.targeted_players.remove(obf);
        String obf2 = "";
        if (this.custom.get_value(true)) {
            obf2 += EzMessageUtil.get_message().replace("[", "").replace("]", "");
        }
        else {
            obf2 += "Leux owns me and all \u0298\u02fd\u0298";
        }
        if (this.discord.get_value(true)) {
            obf2 += " - discord.gg/ppvarYmn73";
        }
        AutoEz.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(obf2));
    }
}
