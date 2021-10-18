// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import me.zero.alpine.fork.listener.EventHandler;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class PacketFly extends Module
{
    @EventHandler
    public Listener<PlayerSPPushOutOfBlocksEvent> listener_push;
    
    public PacketFly() {
        super(Category.exploit);
        this.listener_push = new Listener<PlayerSPPushOutOfBlocksEvent>(PacketFly::lambda$new$0, (Predicate<PlayerSPPushOutOfBlocksEvent>[])new Predicate[0]);
        this.name = "Packet Fly";
        this.tag = "PacketFly";
        this.description = "please god kill me";
    }
    
    public static double[] moveLooking(final int obf) {
        return new double[] { PacketFly.mc.player.rotationYaw * Float.intBitsToFloat(Float.floatToIntBits(0.17400692f) ^ 0x7D862EDF) / Float.intBitsToFloat(Float.floatToIntBits(0.017645704f) ^ 0x7F248DB9) * Float.intBitsToFloat(Float.floatToIntBits(0.015585695f) ^ 0x7F4B5B25) / Float.intBitsToFloat(Float.floatToIntBits(0.010186205f) ^ 0x7F12E40A), obf };
    }
    
    @Override
    public void update() {
        if (!this.is_active()) {
            return;
        }
        PacketFly.mc.player.noClip = true;
        final double[] obf = moveLooking(0);
        final double obf2 = obf[0];
        final double obf3 = obf[1];
        PacketFly.mc.player.motionX = Double.longBitsToDouble(Double.doubleToLongBits(1.591696233990927E308) ^ 0x7FEC55483BCE3800L);
        PacketFly.mc.player.motionY = Double.longBitsToDouble(Double.doubleToLongBits(2.820693675364679E307) ^ 0x7FC4158164A0E3EFL);
        PacketFly.mc.player.motionZ = Double.longBitsToDouble(Double.doubleToLongBits(1.4585528051883425E308) ^ 0x7FE9F68DD5FFD802L);
        PacketFly.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PacketFly.mc.player.posX + PacketFly.mc.player.motionX + (PacketFly.mc.gameSettings.keyBindForward.isKeyDown() ? Double.longBitsToDouble(Double.doubleToLongBits(478.9897452442826) ^ 0x7FD21D31719188D5L) : Double.longBitsToDouble(Double.doubleToLongBits(2.840762245323556E306) ^ 0x7F902E78055D0EBFL)) - (PacketFly.mc.gameSettings.keyBindBack.isKeyDown() ? Double.longBitsToDouble(Double.doubleToLongBits(47.44987693103145) ^ 0x7FE84B711FB3717FL) : Double.longBitsToDouble(Double.doubleToLongBits(1.4265641771623357E308) ^ 0x7FE964C88EB8C71EL)), PacketFly.mc.player.posY + (PacketFly.mc.gameSettings.keyBindJump.isKeyDown() ? Double.longBitsToDouble(Double.doubleToLongBits(425.6179316190085) ^ 0x7FD56B0782C99D3BL) : Double.longBitsToDouble(Double.doubleToLongBits(1.5758852701301549E308) ^ 0x7FEC0D3B7CA7BD18L)) - (PacketFly.mc.gameSettings.keyBindSneak.isKeyDown() ? Double.longBitsToDouble(Double.doubleToLongBits(506.20921415075924) ^ 0x7FD051BC7E65842FL) : Double.longBitsToDouble(Double.doubleToLongBits(1.5703244111119285E308) ^ 0x7FEBF3E44D7BF740L)), PacketFly.mc.player.posZ + PacketFly.mc.player.motionZ + (PacketFly.mc.gameSettings.keyBindRight.isKeyDown() ? Double.longBitsToDouble(Double.doubleToLongBits(315.17640082136876) ^ 0x7FDC403607209D9DL) : Double.longBitsToDouble(Double.doubleToLongBits(1.7837710114267405E308) ^ 0x7FEFC08EBC62BC45L)) - (PacketFly.mc.gameSettings.keyBindLeft.isKeyDown() ? Double.longBitsToDouble(Double.doubleToLongBits(59.46858063237557) ^ 0x7FE2491EFDB7BA17L) : Double.longBitsToDouble(Double.doubleToLongBits(7.060776688899325E307) ^ 0x7FD9231F8022D269L)), PacketFly.mc.player.rotationYaw, PacketFly.mc.player.rotationPitch, false));
        PacketFly.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PacketFly.mc.player.posX + PacketFly.mc.player.motionX, PacketFly.mc.player.posY - Double.longBitsToDouble(Double.doubleToLongBits(5.789349804862393E-5) ^ 0x7FEAD0F572377A88L), PacketFly.mc.player.posZ + PacketFly.mc.player.motionZ, PacketFly.mc.player.rotationYaw, PacketFly.mc.player.rotationPitch, true));
    }
    
    public static void lambda$new$0(final PlayerSPPushOutOfBlocksEvent playerSPPushOutOfBlocksEvent) {
        playerSPPushOutOfBlocksEvent.setCanceled(true);
    }
}
