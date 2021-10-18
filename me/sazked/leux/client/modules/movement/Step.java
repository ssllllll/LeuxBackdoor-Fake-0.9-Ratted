// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import java.text.DecimalFormat;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Step extends Module
{
    public Setting height;
    public Setting mode;
    
    @Override
    public void update() {
        if (Step.mc.player.onGround && !Step.mc.player.isOnLadder()) {
            if (!Step.mc.player.isInWater()) {
                if (!Step.mc.player.isInLava() && !Step.mc.player.movementInput.jump && !Step.mc.player.noClip) {
                    if (Step.mc.player.moveForward == Float.intBitsToFloat(Float.floatToIntBits(1.6805943E38f) ^ 0x7EFCDE23) && Step.mc.player.moveStrafing == Float.intBitsToFloat(Float.floatToIntBits(1.4672237E38f) ^ 0x7EDCC367)) {
                        return;
                    }
                    final double obf = this.get_n_normal();
                    if (this.mode.in("Default")) {
                        if (obf < Double.longBitsToDouble(Double.doubleToLongBits(8.586120274943517E307) ^ 0x7FDE914E6B4F039DL) || obf > Double.longBitsToDouble(Double.doubleToLongBits(0.53517893678669) ^ 0x7FE1202F93E03867L)) {
                            return;
                        }
                        if (obf == Double.longBitsToDouble(Double.doubleToLongBits(0.7919757511243056) ^ 0x7FE957DD87C9B9C7L)) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(24.368007475987774) ^ 0x7FE2BF7212FE77EFL), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(11.168271887691157) ^ 0x7FCEA3E534C0D88BL), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(1272.8950982522997) ^ 0x7F77CB61562DBD7FL), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(2.7059171085068683) ^ 0x7FE5F45C5B6B72D1L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(3.8166911657590425) ^ 0x7FE24459AC723CC5L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(9.026244633506098) ^ 0x7FD151471A07BA05L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(7.139666835092722) ^ 0x7FEBBC37E1970F4FL), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(14.681737953376736) ^ 0x7FDBBC4B6FD8193FL), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(0.9824767115097613) ^ 0x7FEF70730020837FL), Step.mc.player.posZ);
                        }
                        if (obf == Double.longBitsToDouble(Double.doubleToLongBits(7.34091126212096) ^ 0x7FE55D17D7869726L)) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(23.115952197322564) ^ 0x7FEDFCE8AB0F6677L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(2.480584102849512) ^ 0x7FEBC20AA2104C45L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(6.824019688704057) ^ 0x7FEB4EB331117DDAL), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(56.909919205608446) ^ 0x7FBEDC1A14B3A6F7L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(4.275163957390131) ^ 0x7FE2E56F0384A9CFL), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(5.191249986446204) ^ 0x7FE6785C33A72946L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(4.9952129970757575) ^ 0x7FE3FB191DABFA64L), Step.mc.player.posZ);
                        }
                        if (obf == Double.longBitsToDouble(Double.doubleToLongBits(23.212330684406947) ^ 0x7FC7365B4DC17713L)) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(19.497856753561425) ^ 0x7FE99E342A4AA3B8L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(2.3326546722407704) ^ 0x7FEAB3700224E304L), Step.mc.player.posZ, Step.mc.player.onGround));
                            Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(6.60279163741804) ^ 0x7FEA694236040D14L), Step.mc.player.posZ);
                        }
                    }
                    else if (this.mode.in("Vanilla")) {
                        Step.mc.player.stepHeight = Float.parseFloat(new DecimalFormat("#").format(this.height.get_value(Double.longBitsToDouble(Double.doubleToLongBits(21.62489713366494) ^ 0x7FC59FF94230747FL))));
                    }
                }
            }
        }
    }
    
    @Override
    public String array_detail() {
        return this.mode.get_current_value();
    }
    
    public double get_n_normal() {
        Step.mc.player.stepHeight = 0.5f;
        double maxY = -1.0;
        final AxisAlignedBB grow = Step.mc.player.getEntityBoundingBox().offset(0.0, 0.05, 0.0).grow(0.05);
        if (!Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, grow.offset(0.0, 2.0, 0.0)).isEmpty()) {
            return 100.0;
        }
        for (final AxisAlignedBB axisAlignedBB : Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, grow)) {
            if (axisAlignedBB.maxY > maxY) {
                maxY = axisAlignedBB.maxY;
            }
        }
        return maxY - Step.mc.player.posY;
    }
    
    @Override
    public void disable() {
        Step.mc.player.stepHeight = 0.5f;
    }
    
    public Step() {
        super(Category.movement);
        this.mode = this.create("Mode", "StepMode", "Vanilla", this.combobox("Vanilla", "Default"));
        this.height = this.create("Height", "StepHeight", Double.longBitsToDouble(Double.doubleToLongBits(0.26521092556891773) ^ 0x7FD4F9373EF710A9L), Double.longBitsToDouble(Double.doubleToLongBits(2.988018229825258) ^ 0x7FE7E7761A079C41L), Double.longBitsToDouble(Double.doubleToLongBits(0.6446828610313218) ^ 0x7FE0A13DF38D7ACCL));
        this.name = "Step";
        this.tag = "Step";
        this.description = "Move up block big";
    }
}
