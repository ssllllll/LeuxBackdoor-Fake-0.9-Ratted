// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import me.sazked.leux.client.modules.Category;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.Mod;
import me.sazked.leux.client.modules.Module;

@Mod.EventBusSubscriber({ Side.CLIENT })
public class LongJump extends Module
{
    public Setting toggle;
    public static boolean boostable;
    public static boolean jumped;
    public Setting speed;
    public Setting packet;
    
    public double getDirection() {
        float rotationYaw = LongJump.mc.player.rotationYaw;
        if (LongJump.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (LongJump.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (LongJump.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (LongJump.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (LongJump.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return Math.toRadians(rotationYaw);
    }
    
    @Override
    public void update() {
        if (LongJump.mc.player == null || LongJump.mc.world == null) {
            return;
        }
        if (LongJump.jumped) {
            if (LongJump.mc.player.onGround || LongJump.mc.player.capabilities.isFlying) {
                LongJump.jumped = false;
                LongJump.mc.player.motionX = Double.longBitsToDouble(Double.doubleToLongBits(5.830214355039015E307) ^ 0x7FD4C199BC8540C9L);
                LongJump.mc.player.motionZ = Double.longBitsToDouble(Double.doubleToLongBits(5.165236530707967E307) ^ 0x7FD2638BD571D83FL);
                if (this.packet.get_value(true)) {
                    LongJump.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(LongJump.mc.player.posX, LongJump.mc.player.posY, LongJump.mc.player.posZ, LongJump.mc.player.onGround));
                    LongJump.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(LongJump.mc.player.posX + LongJump.mc.player.motionX, Double.longBitsToDouble(Double.doubleToLongBits(1.1350547265573971E308) ^ 0x7FE434639A04994DL), LongJump.mc.player.posZ + LongJump.mc.player.motionZ, LongJump.mc.player.onGround));
                }
                if (!this.toggle.get_value(true)) {
                    this.toggle();
                }
                return;
            }
            if (LongJump.mc.player.movementInput.moveForward == Float.intBitsToFloat(Float.floatToIntBits(2.0045116E38f) ^ 0x7F16CD7D) && LongJump.mc.player.movementInput.moveStrafe == Float.intBitsToFloat(Float.floatToIntBits(2.5844483E38f) ^ 0x7F426EAA)) {
                return;
            }
            final double obf = this.getDirection();
            LongJump.mc.player.motionX = -Math.sin(obf) * ((float)Math.sqrt(LongJump.mc.player.motionX * LongJump.mc.player.motionX + LongJump.mc.player.motionZ * LongJump.mc.player.motionZ) * (LongJump.boostable ? (this.speed.get_value(0) / Float.intBitsToFloat(Float.floatToIntBits(1.4144403f) ^ 0x7E950C61)) : Float.intBitsToFloat(Float.floatToIntBits(14.814683f) ^ 0x7EED08F1)));
            LongJump.mc.player.motionZ = Math.cos(obf) * ((float)Math.sqrt(LongJump.mc.player.motionX * LongJump.mc.player.motionX + LongJump.mc.player.motionZ * LongJump.mc.player.motionZ) * (LongJump.boostable ? (this.speed.get_value(0) / Float.intBitsToFloat(Float.floatToIntBits(0.04021954f) ^ 0x7C04BD3F)) : Float.intBitsToFloat(Float.floatToIntBits(5.540094f) ^ 0x7F314873)));
            LongJump.boostable = false;
            if (!this.toggle.get_value(true)) {
                this.toggle();
            }
        }
        if (LongJump.mc.player.movementInput.moveForward == Float.intBitsToFloat(Float.floatToIntBits(3.1984658E38f) ^ 0x7F70A036) && LongJump.mc.player.movementInput.moveStrafe == Float.intBitsToFloat(Float.floatToIntBits(1.9223991E37f) ^ 0x7D67667F) && LongJump.jumped) {
            LongJump.mc.player.motionX = Double.longBitsToDouble(Double.doubleToLongBits(1.2852317998812682E308) ^ 0x7FE6E0BD19A7BD3AL);
            LongJump.mc.player.motionZ = Double.longBitsToDouble(Double.doubleToLongBits(1.7171181549461908E308) ^ 0x7FEE90D2E0DBC34FL);
        }
    }
    
    @SubscribeEvent
    public static void onJump(final LivingEvent.LivingJumpEvent obf) {
        if (LongJump.mc.player != null) {
            if (LongJump.mc.world != null && obf.getEntity() == LongJump.mc.player && (LongJump.mc.player.movementInput.moveForward != Float.intBitsToFloat(Float.floatToIntBits(1.9019913E38f) ^ 0x7F0F1705) || LongJump.mc.player.movementInput.moveStrafe != Float.intBitsToFloat(Float.floatToIntBits(1.4543282E38f) ^ 0x7EDAD2AF))) {
                LongJump.jumped = true;
                LongJump.boostable = true;
            }
        }
    }
    
    static {
        LongJump.jumped = false;
        LongJump.boostable = false;
    }
    
    public LongJump() {
        super(Category.movement);
        this.speed = this.create("Speed", "LGSpeed", Double.longBitsToDouble(Double.doubleToLongBits(1.4935595614210022) ^ 0x7FC9E59EB5EEE6A3L), Double.longBitsToDouble(Double.doubleToLongBits(9.092241300864364) ^ 0x7FD22F3A40751B1BL), Double.longBitsToDouble(Double.doubleToLongBits(0.07175136375697394) ^ 0x7FEB5E4C20C7940CL));
        this.packet = this.create("Packet", "LGPacket", false);
        this.toggle = this.create("Toggle", "LGToggle", false);
        this.name = "LongJump";
        this.tag = "LongJump";
        this.description = "MadrriorCrystal";
    }
}
