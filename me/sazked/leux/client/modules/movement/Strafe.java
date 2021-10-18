// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.init.MobEffects;
import me.sazked.leux.client.event.events.EventPlayerJump;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventMove;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class Strafe extends Module
{
    @EventHandler
    public Listener<EventMove> player_move;
    public Setting on_water;
    public Setting backward;
    public Setting auto_jump;
    public Setting bypass;
    public Setting speed_mode;
    @EventHandler
    public Listener<EventPlayerJump> on_jump;
    
    public void lambda$new$1(final EventMove eventMove) {
        if (this.speed_mode.in("On Ground")) {
            return;
        }
        if ((Strafe.mc.player.isInWater() || Strafe.mc.player.isInLava()) && !this.speed_mode.get_value(true)) {
            return;
        }
        if (!Strafe.mc.player.isSneaking() && !Strafe.mc.player.isOnLadder() && !Strafe.mc.player.isInWeb) {
            if (!Strafe.mc.player.isInLava()) {
                if (!Strafe.mc.player.isInWater()) {
                    if (!Strafe.mc.player.capabilities.isFlying) {
                        float n = 0.2873f;
                        float moveForward = Strafe.mc.player.movementInput.moveForward;
                        float moveStrafe = Strafe.mc.player.movementInput.moveStrafe;
                        float rotationYaw = Strafe.mc.player.rotationYaw;
                        if (Strafe.mc.player.isPotionActive(MobEffects.SPEED)) {
                            float n2 = 1.0f;
                            final int amplifier = Strafe.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
                            if (amplifier == 1) {
                                n2 = 1.4f;
                            }
                            if (amplifier == 0) {
                                n2 = 1.2f;
                            }
                            n *= n2;
                        }
                        if (!this.bypass.get_value(true)) {
                            n *= 1.0064f;
                        }
                        Label_0724: {
                            if (moveForward == 0.0f) {
                                if (moveStrafe == 0.0f) {
                                    eventMove.set_x(0.0);
                                    eventMove.set_z(0.0);
                                    break Label_0724;
                                }
                            }
                            if (moveForward != 0.0f) {
                                if (moveStrafe > 0.0f) {
                                    rotationYaw += ((moveForward > 0.0f) ? -45 : 45);
                                }
                                else if (moveStrafe < 0.0f) {
                                    rotationYaw += ((moveForward > 0.0f) ? 45 : -45);
                                }
                                moveStrafe = 0.0f;
                                if (moveForward > 0.0f) {
                                    moveForward = 1.0f;
                                }
                                else if (moveForward < 0.0f) {
                                    moveForward = -1.0f;
                                }
                            }
                            eventMove.set_x(moveForward * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + moveStrafe * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)));
                            eventMove.set_z(moveForward * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - moveStrafe * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)));
                        }
                        eventMove.cancel();
                    }
                }
            }
        }
    }
    
    public void lambda$new$0(final EventPlayerJump obf) {
        if (this.speed_mode.in("Strafe")) {
            obf.cancel();
        }
    }
    
    public float get_rotation_yaw() {
        float rotationYaw = Strafe.mc.player.rotationYaw;
        if (Strafe.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (Strafe.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (Strafe.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (Strafe.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (Strafe.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    public Strafe() {
        super(Category.movement);
        this.speed_mode = this.create("Mode", "StrafeMode", "Strafe", this.combobox("Strafe", "On Ground"));
        this.on_water = this.create("On Water", "StrafeOnWater", true);
        this.auto_jump = this.create("Auto Jump", "StrafeAutoJump", true);
        this.backward = this.create("Backwards", "StrafeBackwards", true);
        this.bypass = this.create("Bypass", "StrafeBypass", false);
        this.on_jump = new Listener<EventPlayerJump>(this::lambda$new$0, (Predicate<EventPlayerJump>[])new Predicate[0]);
        this.player_move = new Listener<EventMove>(this::lambda$new$1, (Predicate<EventMove>[])new Predicate[0]);
        this.name = "Strafe";
        this.tag = "Strafe";
        this.description = "its like running, but faster";
    }
    
    @Override
    public void update() {
        if (Strafe.mc.player.isRiding() || Strafe.mc.player.isSneaking()) {
            return;
        }
        if ((Strafe.mc.player.isInWater() || Strafe.mc.player.isInLava()) && !this.on_water.get_value(true)) {
            return;
        }
        if (Strafe.mc.player.moveForward != Float.intBitsToFloat(Float.floatToIntBits(1.6977656E38f) ^ 0x7EFF738D) || Strafe.mc.player.moveStrafing != Float.intBitsToFloat(Float.floatToIntBits(3.306288E37f) ^ 0x7DC6FD6F)) {
            if (Strafe.mc.player.moveForward < Float.intBitsToFloat(Float.floatToIntBits(4.6168783E37f) ^ 0x7E0AEF23) && !this.backward.get_value(true)) {
                return;
            }
            if (Strafe.mc.player.onGround && this.speed_mode.in("Strafe")) {
                if (this.auto_jump.get_value(true)) {
                    Strafe.mc.player.motionY = Double.longBitsToDouble(Double.doubleToLongBits(27.302431424999238) ^ 0x7FE1701C8557A787L);
                }
                final float obf = this.get_rotation_yaw() * Float.intBitsToFloat(Float.floatToIntBits(374.5361f) ^ 0x7F35BEAA);
                final EntityPlayerSP player = Strafe.mc.player;
                player.motionX -= MathHelper.sin(obf) * Float.intBitsToFloat(Float.floatToIntBits(3.8010604f) ^ 0x7EEADD09);
                final EntityPlayerSP player2 = Strafe.mc.player;
                player2.motionZ += MathHelper.cos(obf) * Float.intBitsToFloat(Float.floatToIntBits(228.14473f) ^ 0x7DFDBC97);
            }
            else if (Strafe.mc.player.onGround) {
                if (this.speed_mode.in("On Ground")) {
                    final float obf = this.get_rotation_yaw();
                    final EntityPlayerSP player3 = Strafe.mc.player;
                    player3.motionX -= MathHelper.sin(obf) * Float.intBitsToFloat(Float.floatToIntBits(2.921579f) ^ 0x7E7637EB);
                    final EntityPlayerSP player4 = Strafe.mc.player;
                    player4.motionZ += MathHelper.cos(obf) * Float.intBitsToFloat(Float.floatToIntBits(12.557116f) ^ 0x7F04253F);
                    Strafe.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Strafe.mc.player.posX, Strafe.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(3.8363901965779523) ^ 0x7FD72974CE7E631BL), Strafe.mc.player.posZ, false));
                }
            }
        }
        if (Strafe.mc.gameSettings.keyBindJump.isKeyDown()) {
            if (Strafe.mc.player.onGround) {
                Strafe.mc.player.motionY = Double.longBitsToDouble(Double.doubleToLongBits(364.30503552164856) ^ 0x7FAF5D78F574C00FL);
            }
        }
    }
}
