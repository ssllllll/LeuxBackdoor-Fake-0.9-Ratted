// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.client.entity.EntityPlayerSP;
import me.sazked.leux.client.util.MathUtil;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemElytra;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import me.sazked.leux.client.event.events.EventPlayerTravel;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.util.Timer;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class ElytraFly extends Module
{
    public Setting instant_fly;
    public Setting cancel_at_height;
    public Setting up_speed;
    public Setting equip_elytra;
    public Setting mode;
    public Setting accelerate;
    public Timer acceleration_reset_timer;
    public Setting glide_speed;
    public Setting cancel_in_water;
    @EventHandler
    public Listener<EventPacket> packet_event;
    public boolean send_message;
    @EventHandler
    public Listener<EventPlayerTravel> on_travel;
    public Setting speed;
    public Timer instant_fly_timer;
    public Timer packet_timer;
    public Setting rotation_pitch;
    public Timer acceleration_timer;
    public Setting down_speed;
    public int elytra_slot;
    public Setting v_acceleration_timer;
    public Setting pitch_spoof;
    
    @Override
    public void enable() {
        this.elytra_slot = -1;
        if (this.equip_elytra.get_value(true)) {
            if (ElytraFly.mc.player != null) {
                if (ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
                    for (int i = 0; i < 44; ++i) {
                        final ItemStack stackInSlot = ElytraFly.mc.player.inventory.getStackInSlot(i);
                        if (!stackInSlot.isEmpty() && stackInSlot.getItem() == Items.ELYTRA) {
                            final ItemElytra itemElytra = (ItemElytra)stackInSlot.getItem();
                            this.elytra_slot = i;
                            break;
                        }
                    }
                    if (this.elytra_slot != -1) {
                        final boolean b = ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.AIR;
                        ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, this.elytra_slot, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
                        ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
                        if (b) {
                            ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, this.elytra_slot, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
                        }
                    }
                }
            }
        }
    }
    
    public void lambda$new$0(final EventPlayerTravel obf) {
        if (ElytraFly.mc.player == null) {
            return;
        }
        if (ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
            return;
        }
        if (!ElytraFly.mc.player.isElytraFlying()) {
            if (!ElytraFly.mc.player.onGround && this.instant_fly.get_value(true)) {
                if (!this.instant_fly_timer.passed((long)779652338 ^ 0x2E788F1AL)) {
                    return;
                }
                this.instant_fly_timer.reset();
                ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
            }
            return;
        }
        if (this.mode.in("Normal") || this.mode.in("Tarzan") || this.mode.in("Packet")) {
            this.handle_normal_mode_elytra(obf);
        }
        else if (this.mode.in("Superior")) {
            this.handle_immediate_mode_elytra(obf);
        }
        else if (this.mode.in("Control")) {
            this.handle_control_mode(obf);
        }
    }
    
    public void handle_immediate_mode_elytra(final EventPlayerTravel eventPlayerTravel) {
        if (!ElytraFly.mc.player.movementInput.jump) {
            ElytraFly.mc.player.setVelocity(0.0, 0.0, 0.0);
            eventPlayerTravel.cancel();
            final double[] directionSpeed = MathUtil.directionSpeed(this.speed.get_value(1.0));
            if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                ElytraFly.mc.player.motionX = directionSpeed[0];
                ElytraFly.mc.player.motionY = -(this.glide_speed.get_value(1.0) / 10000.0);
                ElytraFly.mc.player.motionZ = directionSpeed[1];
            }
            if (ElytraFly.mc.player.movementInput.sneak) {
                ElytraFly.mc.player.motionY = -this.down_speed.get_value(1.0);
            }
            ElytraFly.mc.player.prevLimbSwingAmount = 0.0f;
            ElytraFly.mc.player.limbSwingAmount = 0.0f;
            ElytraFly.mc.player.limbSwing = 0.0f;
            return;
        }
        if (Math.sqrt(ElytraFly.mc.player.motionX * ElytraFly.mc.player.motionX + ElytraFly.mc.player.motionZ * ElytraFly.mc.player.motionZ) > 1.0) {
            return;
        }
        final double[] directionSpeedNoForward = MathUtil.directionSpeedNoForward(this.speed.get_value(1.0));
        ElytraFly.mc.player.motionX = directionSpeedNoForward[0];
        ElytraFly.mc.player.motionY = -(this.glide_speed.get_value(1.0) / 10000.0);
        ElytraFly.mc.player.motionZ = directionSpeedNoForward[1];
        eventPlayerTravel.cancel();
    }
    
    @Override
    public void disable() {
        if (ElytraFly.mc.player == null) {
            return;
        }
        if (this.elytra_slot != -1) {
            boolean b = false;
            Label_0129: {
                if (ElytraFly.mc.player.inventory.getStackInSlot(this.elytra_slot).isEmpty()) {
                    if (ElytraFly.mc.player.inventory.getStackInSlot(this.elytra_slot).getItem() == Items.AIR) {
                        b = false;
                        break Label_0129;
                    }
                }
                b = true;
            }
            final boolean b2 = b;
            ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
            ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, this.elytra_slot, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
            if (b2) {
                ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
            }
        }
    }
    
    public void handle_control_mode(final EventPlayerTravel eventPlayerTravel) {
        final double[] directionSpeed = MathUtil.directionSpeed(this.speed.get_value(1.0));
        if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
            ElytraFly.mc.player.motionX = directionSpeed[0];
            ElytraFly.mc.player.motionZ = directionSpeed[1];
            final EntityPlayerSP player = ElytraFly.mc.player;
            player.motionX -= ElytraFly.mc.player.motionX * (Math.abs(ElytraFly.mc.player.rotationPitch) + 90.0f) / 90.0 - ElytraFly.mc.player.motionX;
            final EntityPlayerSP player2 = ElytraFly.mc.player;
            player2.motionZ -= ElytraFly.mc.player.motionZ * (Math.abs(ElytraFly.mc.player.rotationPitch) + 90.0f) / 90.0 - ElytraFly.mc.player.motionZ;
        }
        else {
            ElytraFly.mc.player.motionX = 0.0;
            ElytraFly.mc.player.motionZ = 0.0;
        }
        ElytraFly.mc.player.motionY = -MathUtil.degToRad(ElytraFly.mc.player.rotationPitch) * ElytraFly.mc.player.movementInput.moveForward;
        ElytraFly.mc.player.prevLimbSwingAmount = 0.0f;
        ElytraFly.mc.player.limbSwingAmount = 0.0f;
        ElytraFly.mc.player.limbSwing = 0.0f;
        eventPlayerTravel.cancel();
    }
    
    @Override
    public String array_detail() {
        return this.mode.get_current_value();
    }
    
    public void Accelerate() {
        if (this.acceleration_reset_timer.passed(this.v_acceleration_timer.get_value(1))) {
            this.acceleration_reset_timer.reset();
            this.acceleration_timer.reset();
            this.send_message = false;
        }
        final double[] directionSpeed = MathUtil.directionSpeed((float)this.speed.get_value(1.0));
        ElytraFly.mc.player.motionY = -(this.glide_speed.get_value(1.0) / 10000.0);
        Label_0240: {
            if (ElytraFly.mc.player.movementInput.moveStrafe == 0.0f) {
                if (ElytraFly.mc.player.movementInput.moveForward == 0.0f) {
                    ElytraFly.mc.player.motionX = 0.0;
                    ElytraFly.mc.player.motionZ = 0.0;
                    break Label_0240;
                }
            }
            ElytraFly.mc.player.motionX = directionSpeed[0];
            ElytraFly.mc.player.motionZ = directionSpeed[1];
        }
        if (ElytraFly.mc.player.movementInput.sneak) {
            ElytraFly.mc.player.motionY = -this.down_speed.get_value(1.0);
        }
        ElytraFly.mc.player.prevLimbSwingAmount = 0.0f;
        ElytraFly.mc.player.limbSwingAmount = 0.0f;
        ElytraFly.mc.player.limbSwing = 0.0f;
    }
    
    public ElytraFly() {
        super(Category.movement);
        this.mode = this.create("Mode", "Mode", "Normal", this.combobox("Normal", "Tarzan", "Superior", "Packet", "Control"));
        this.speed = this.create("Speed", "Speed", Double.longBitsToDouble(Double.doubleToLongBits(249.40293054197625) ^ 0x7F92325CAE978B1FL), Double.longBitsToDouble(Double.doubleToLongBits(3.261073971553822E307) ^ 0x7FC73838D8F2732BL), Double.longBitsToDouble(Double.doubleToLongBits(0.1644242215977379) ^ 0x7FE10BDA57375D82L));
        this.glide_speed = this.create("Glide Speed", "GlideSpeed", Double.longBitsToDouble(Double.doubleToLongBits(4.22248720030276) ^ 0x7FE0E3D3AF4450DAL), Double.longBitsToDouble(Double.doubleToLongBits(3.8416408687846967E307) ^ 0x7FCB5A77D43FA177L), Double.longBitsToDouble(Double.doubleToLongBits(1.6537806262654196) ^ 0x7FDE75E2AC891811L));
        this.up_speed = this.create("Up Speed", "UpSpeed", Double.longBitsToDouble(Double.doubleToLongBits(0.9866407870056672) ^ 0x7FEF928FB322D96FL), Double.longBitsToDouble(Double.doubleToLongBits(9.750930703816557E307) ^ 0x7FE15B739BEAD91CL), Double.longBitsToDouble(Double.doubleToLongBits(1.8618621229032313) ^ 0x7FD9CA2FEFF87CF9L));
        this.down_speed = this.create("Down Speed", "DownSpeed", Double.longBitsToDouble(Double.doubleToLongBits(67.9896715048669) ^ 0x7FADE1EEA726CBEFL), Double.longBitsToDouble(Double.doubleToLongBits(1.651221415450193E308) ^ 0x7FED648917670F4EL), Double.longBitsToDouble(Double.doubleToLongBits(0.21232660944240625) ^ 0x7FEF2D84B1D016EDL));
        this.accelerate = this.create("Accelerate", "Accelerate", true);
        this.v_acceleration_timer = this.create("Timer", "AccelerationTimer", 1000, 0, 10000);
        this.rotation_pitch = this.create("Rotation Pitch", "RotationPitch", Double.longBitsToDouble(Double.doubleToLongBits(1.7258157594882367E308) ^ 0x7FEEB8755361E24CL), Double.longBitsToDouble(Double.doubleToLongBits(-0.08965921863454115) ^ 0x7FE073E813D1FD16L), Double.longBitsToDouble(Double.doubleToLongBits(0.026281216266409137) ^ 0x7FCC697691740CE7L));
        this.cancel_in_water = this.create("Cancel In Water", "CancelWater", true);
        this.cancel_at_height = this.create("Cancel At Height", "CancelHeight", 5, 0, 10);
        this.instant_fly = this.create("Instant Fly", "InstaFly", true);
        this.equip_elytra = this.create("Equip Elytra", "EquipElytra", false);
        this.pitch_spoof = this.create("Pitch Spoof", "PitchSpoof", false);
        this.packet_timer = new Timer();
        this.acceleration_timer = new Timer();
        this.acceleration_reset_timer = new Timer();
        this.instant_fly_timer = new Timer();
        this.send_message = false;
        this.elytra_slot = -1;
        this.on_travel = new Listener<EventPlayerTravel>(this::lambda$new$0, (Predicate<EventPlayerTravel>[])new Predicate[0]);
        this.packet_event = new Listener<EventPacket>(this::lambda$new$1, (Predicate<EventPacket>[])new Predicate[0]);
        this.name = "Elytra Fly";
        this.tag = "ElytraFly";
        this.description = "yes this is skided from salhack fuck you i don't care";
    }
    
    public void handle_normal_mode_elytra(final EventPlayerTravel obf) {
        final double obf2 = ElytraFly.mc.player.posY;
        if (obf2 <= this.cancel_at_height.get_value(1)) {
            if (!this.send_message) {
                MessageUtil.send_client_message(ChatFormatting.RED + "WARNING, you must scaffold up or use fireworks, as YHeight <= CancelAtHeight!");
                this.send_message = true;
            }
            return;
        }
        final boolean obf3 = ElytraFly.mc.player.movementInput.moveForward > Float.intBitsToFloat(Float.floatToIntBits(1.465191E37f) ^ 0x7D305DAF) || ElytraFly.mc.player.movementInput.moveStrafe > Float.intBitsToFloat(Float.floatToIntBits(1.5958811E38f) ^ 0x7EF01F1B);
        final boolean obf5;
        final boolean obf4 = obf5 = (!ElytraFly.mc.player.isInWater() && !ElytraFly.mc.player.isInLava() && this.cancel_in_water.get_value(true));
        if (ElytraFly.mc.player.movementInput.jump) {
            obf.cancel();
            this.Accelerate();
            return;
        }
        if (!obf3) {
            this.acceleration_timer.resetTimeSkipTo(-this.v_acceleration_timer.get_value(1));
        }
        else if (ElytraFly.mc.player.rotationPitch <= this.rotation_pitch.get_value(1) || this.mode.in("Tarzan")) {
            if (obf4) {
                if (this.accelerate.get_value(true)) {
                    if (this.acceleration_timer.passed(this.v_acceleration_timer.get_value(1))) {
                        this.Accelerate();
                    }
                }
                return;
            }
        }
        obf.cancel();
        this.Accelerate();
    }
    
    public void lambda$new$1(final EventPacket obf) {
        if (obf.get_packet() instanceof CPacketPlayer && this.pitch_spoof.get_value(true)) {
            if (!ElytraFly.mc.player.isElytraFlying()) {
                return;
            }
            if (obf.get_packet() instanceof CPacketPlayer.PositionRotation && this.pitch_spoof.get_value(true)) {
                final CPacketPlayer.PositionRotation obf2 = (CPacketPlayer.PositionRotation)obf.get_packet();
                ElytraFly.mc.getConnection().sendPacket((Packet)new CPacketPlayer.Position(obf2.x, obf2.y, obf2.z, obf2.onGround));
                obf.cancel();
            }
            else if (obf.get_packet() instanceof CPacketPlayer.Rotation) {
                if (this.pitch_spoof.get_value(true)) {
                    obf.cancel();
                }
            }
        }
    }
}
