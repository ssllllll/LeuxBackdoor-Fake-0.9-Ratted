// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.Minecraft;

public class RotationUtil
{
    public static Minecraft mc;
    public static float yaw;
    public static float pitch;
    
    public float getPitch() {
        return RotationUtil.pitch;
    }
    
    public void setYaw(final float yaw) {
        RotationUtil.yaw = yaw;
    }
    
    public void lookAtVec3d(final Vec3d obf) {
        final float[] obf2 = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), new Vec3d(obf.x, obf.y, obf.z));
        setPlayerRotations(obf2[0], obf2[1]);
    }
    
    public String getDirection4D(final boolean obf) {
        return this.getDirection4D(obf);
    }
    
    public int getDirection4D() {
        return this.getDirection4D();
    }
    
    public static void updateRotations() {
        RotationUtil.yaw = RotationUtil.mc.player.rotationYaw;
        RotationUtil.pitch = RotationUtil.mc.player.rotationPitch;
    }
    
    public float getYaw() {
        return RotationUtil.yaw;
    }
    
    public void setPlayerYaw(final float obf) {
        RotationUtil.mc.player.rotationYaw = obf;
        RotationUtil.mc.player.rotationYawHead = obf;
    }
    
    public void setPlayerPitch(final float rotationPitch) {
        RotationUtil.mc.player.rotationPitch = rotationPitch;
    }
    
    public void lookAtPos(final BlockPos obf) {
        final float[] obf2 = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), new Vec3d((double)(obf.getX() + Float.intBitsToFloat(Float.floatToIntBits(3.3524106f) ^ 0x7F568DE5)), (double)(obf.getY() + Float.intBitsToFloat(Float.floatToIntBits(3.649672f) ^ 0x7F69943A)), (double)(obf.getZ() + Float.intBitsToFloat(Float.floatToIntBits(87.09118f) ^ 0x7DAE2EAF))));
        setPlayerRotations(obf2[0], obf2[1]);
    }
    
    static {
        RotationUtil.mc = Minecraft.getMinecraft();
    }
    
    public static void restoreRotations() {
        RotationUtil.mc.player.rotationYaw = RotationUtil.yaw;
        RotationUtil.mc.player.rotationYawHead = RotationUtil.yaw;
        RotationUtil.mc.player.rotationPitch = RotationUtil.pitch;
    }
    
    public void setPitch(final float pitch) {
        RotationUtil.pitch = pitch;
    }
    
    public void lookAtVec3d(final double obf, final double obf, final double obf) {
        final Vec3d obf2 = new Vec3d(obf, obf, obf);
        this.lookAtVec3d(obf2);
    }
    
    public void lookAtEntity(final Entity entity) {
        final float[] calcAngle = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), entity.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()));
        setPlayerRotations(calcAngle[0], calcAngle[1]);
    }
    
    public static void setPlayerRotations(final float obf, final float obf) {
        RotationUtil.mc.player.rotationYaw = obf;
        RotationUtil.mc.player.rotationYawHead = obf;
        RotationUtil.mc.player.rotationPitch = obf;
    }
}
