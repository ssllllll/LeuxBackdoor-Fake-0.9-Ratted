// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import me.sazked.leux.client.event.EventCancellable;
import me.sazked.leux.client.event.events.EventMove;
import me.sazked.leux.client.event.events.EventEntity;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class Velocity extends Module
{
    @EventHandler
    public Listener<EventPacket.ReceivePacket> damage;
    public Setting nopush;
    @EventHandler
    public Listener<EventEntity.EventColision> explosion;
    @EventHandler
    public Listener<EventMove> player_move;
    
    public void lambda$new$0(final EventMove eventMove) {
        if (!Velocity.mc.player.isSneaking() && !Velocity.mc.player.isOnLadder() && !Velocity.mc.player.isInWeb && !Velocity.mc.player.isInLava() && !Velocity.mc.player.isInWater() && !Velocity.mc.player.capabilities.isFlying) {
            if (this.nopush.get_value(true)) {
                final float moveForward = Velocity.mc.player.movementInput.moveForward;
                final float moveStrafe = Velocity.mc.player.movementInput.moveStrafe;
                if (moveForward == 0.0f && moveStrafe == 0.0f) {
                    eventMove.set_x(0.0);
                    eventMove.set_z(0.0);
                }
                eventMove.cancel();
            }
        }
    }
    
    public static void lambda$new$2(final EventEntity.EventColision obf) {
        if (obf.get_entity() == Velocity.mc.player) {
            obf.cancel();
        }
    }
    
    public static void lambda$new$1(final EventPacket.ReceivePacket receivePacket) {
        if (receivePacket.get_era() == EventCancellable.Era.EVENT_PRE) {
            if (receivePacket.get_packet() instanceof SPacketEntityVelocity) {
                final SPacketEntityVelocity sPacketEntityVelocity = (SPacketEntityVelocity)receivePacket.get_packet();
                if (sPacketEntityVelocity.getEntityID() == Velocity.mc.player.getEntityId()) {
                    receivePacket.cancel();
                    final SPacketEntityVelocity sPacketEntityVelocity2 = sPacketEntityVelocity;
                    sPacketEntityVelocity2.motionX *= (int)0.0f;
                    final SPacketEntityVelocity sPacketEntityVelocity3 = sPacketEntityVelocity;
                    sPacketEntityVelocity3.motionY *= (int)0.0f;
                    final SPacketEntityVelocity sPacketEntityVelocity4 = sPacketEntityVelocity;
                    sPacketEntityVelocity4.motionZ *= (int)0.0f;
                }
            }
            else if (receivePacket.get_packet() instanceof SPacketExplosion) {
                receivePacket.cancel();
                final SPacketExplosion sPacketExplosion2;
                final SPacketExplosion sPacketExplosion = sPacketExplosion2 = (SPacketExplosion)receivePacket.get_packet();
                sPacketExplosion2.motionX *= 0.0f;
                final SPacketExplosion sPacketExplosion3 = sPacketExplosion;
                sPacketExplosion3.motionY *= 0.0f;
                final SPacketExplosion sPacketExplosion4 = sPacketExplosion;
                sPacketExplosion4.motionZ *= 0.0f;
            }
        }
    }
    
    public Velocity() {
        super(Category.movement);
        this.nopush = this.create("NoPush", "NoPush", true);
        this.player_move = new Listener<EventMove>(this::lambda$new$0, (Predicate<EventMove>[])new Predicate[0]);
        this.damage = new Listener<EventPacket.ReceivePacket>(Velocity::lambda$new$1, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.explosion = new Listener<EventEntity.EventColision>(Velocity::lambda$new$2, (Predicate<EventEntity.EventColision>[])new Predicate[0]);
        this.name = "Velocity";
        this.tag = "Velocity";
        this.description = "No kockback";
    }
}
