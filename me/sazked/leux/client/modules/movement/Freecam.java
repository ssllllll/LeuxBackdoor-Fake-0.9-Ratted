// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import me.sazked.leux.client.util.MathUtil;
import net.minecraft.world.World;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.client.event.events.EventPacket;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import me.sazked.leux.client.event.events.EventMove;
import me.zero.alpine.fork.listener.EventHandler;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Freecam extends Module
{
    public double y;
    public double x;
    public float pitch;
    public float yaw;
    public Setting speed_moveme;
    public Entity riding_entity;
    @EventHandler
    public Listener<PlayerSPPushOutOfBlocksEvent> listener_push;
    public Setting speed_updown;
    @EventHandler
    public Listener<EventMove> listener_move;
    public EntityOtherPlayerMP soul;
    public double z;
    public boolean is_riding;
    @EventHandler
    public Listener<EventPacket.SendPacket> listener_packet;
    
    public static void lambda$new$0(final EventMove obf) {
        Freecam.mc.player.noClip = true;
    }
    
    public static void lambda$new$2(final EventPacket.SendPacket sendPacket) {
        if (sendPacket.get_packet() instanceof CPacketPlayer || sendPacket.get_packet() instanceof CPacketInput) {
            sendPacket.cancel();
        }
    }
    
    @Override
    public void disable() {
        if (Freecam.mc.player != null && Freecam.mc.world != null) {
            Freecam.mc.player.setPositionAndRotation(this.x, this.y, this.z, this.yaw, this.pitch);
            Freecam.mc.world.removeEntityFromWorld(-100);
            this.soul = null;
            this.x = 0.0;
            this.y = 0.0;
            this.z = 0.0;
            this.yaw = 0.0f;
            this.pitch = 0.0f;
            final EntityPlayerSP player = Freecam.mc.player;
            final EntityPlayerSP player2 = Freecam.mc.player;
            final EntityPlayerSP player3 = Freecam.mc.player;
            final double motionX = 0.0;
            player3.motionZ = motionX;
            player2.motionY = motionX;
            player.motionX = motionX;
            if (this.is_riding) {
                Freecam.mc.player.startRiding(this.riding_entity, true);
            }
        }
    }
    
    public Freecam() {
        super(Category.movement);
        this.speed_moveme = this.create("Speed Movement", "FreecamSpeedMovement", Double.longBitsToDouble(Double.doubleToLongBits(26.171737530495236) ^ 0x7FE3B26F643D6096L), Double.longBitsToDouble(Double.doubleToLongBits(1.6812424093646587E308) ^ 0x7FEDED56F7DAB453L), Double.longBitsToDouble(Double.doubleToLongBits(0.054763326994626486) ^ 0x7FAC09F054EC950FL));
        this.speed_updown = this.create("Speed Up/Down", "FreecamSpeedUpDown", Double.longBitsToDouble(Double.doubleToLongBits(226.10864710360661) ^ 0x7FB5DAE390E400EFL), Double.longBitsToDouble(Double.doubleToLongBits(1.7774972661787608E307) ^ 0x7FB94FF987CFBCBFL), Double.longBitsToDouble(Double.doubleToLongBits(0.4963439294208611) ^ 0x7FDFC419541B9117L));
        this.listener_move = new Listener<EventMove>(Freecam::lambda$new$0, (Predicate<EventMove>[])new Predicate[0]);
        this.listener_push = new Listener<PlayerSPPushOutOfBlocksEvent>(Freecam::lambda$new$1, (Predicate<PlayerSPPushOutOfBlocksEvent>[])new Predicate[0]);
        this.listener_packet = new Listener<EventPacket.SendPacket>(Freecam::lambda$new$2, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Freecam";
        this.tag = "Freecam";
        this.description = "Project Astral in Minecraft, a thing really insane.";
    }
    
    public static void lambda$new$1(final PlayerSPPushOutOfBlocksEvent playerSPPushOutOfBlocksEvent) {
        playerSPPushOutOfBlocksEvent.setCanceled(true);
    }
    
    @Override
    public void enable() {
        if (Freecam.mc.player != null && Freecam.mc.world != null) {
            this.is_riding = (Freecam.mc.player.getRidingEntity() != null);
            if (Freecam.mc.player.getRidingEntity() == null) {
                this.x = Freecam.mc.player.posX;
                this.y = Freecam.mc.player.posY;
                this.z = Freecam.mc.player.posZ;
            }
            else {
                this.riding_entity = Freecam.mc.player.getRidingEntity();
                Freecam.mc.player.dismountRidingEntity();
            }
            this.pitch = Freecam.mc.player.rotationPitch;
            this.yaw = Freecam.mc.player.rotationYaw;
            (this.soul = new EntityOtherPlayerMP((World)Freecam.mc.world, Freecam.mc.getSession().getProfile())).copyLocationAndAnglesFrom((Entity)Freecam.mc.player);
            this.soul.rotationYawHead = Freecam.mc.player.rotationYawHead;
            Freecam.mc.world.addEntityToWorld(-100, (Entity)this.soul);
            Freecam.mc.player.noClip = true;
        }
    }
    
    @Override
    public void update() {
        if (Freecam.mc.player != null && Freecam.mc.world != null) {
            Freecam.mc.player.noClip = true;
            Freecam.mc.player.setVelocity(0.0, 0.0, 0.0);
            Freecam.mc.player.renderArmPitch = 5000.0f;
            Freecam.mc.player.jumpMovementFactor = 0.5f;
            final double[] movement_speed = MathUtil.movement_speed(this.speed_moveme.get_value(1.0) / 2.0);
            if (Freecam.mc.player.movementInput.moveStrafe != 0.0f || Freecam.mc.player.movementInput.moveForward != 0.0f) {
                Freecam.mc.player.motionX = movement_speed[0];
                Freecam.mc.player.motionZ = movement_speed[1];
            }
            else {
                Freecam.mc.player.motionX = 0.0;
                Freecam.mc.player.motionZ = 0.0;
            }
            Freecam.mc.player.setSprinting(false);
            if (Freecam.mc.gameSettings.keyBindJump.isKeyDown()) {
                final EntityPlayerSP player = Freecam.mc.player;
                player.motionY += this.speed_updown.get_value(1.0);
            }
            if (Freecam.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final EntityPlayerSP player2 = Freecam.mc.player;
                player2.motionY -= this.speed_updown.get_value(1.0);
            }
        }
    }
}
