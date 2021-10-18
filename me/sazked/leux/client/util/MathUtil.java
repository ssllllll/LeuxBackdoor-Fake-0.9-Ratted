// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.entity.player.EntityPlayer;
import java.math.RoundingMode;
import java.math.BigDecimal;
import net.minecraft.util.math.BlockPos;
import java.util.Calendar;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.ArrayList;
import net.minecraft.util.math.Vec3d;
import java.util.List;
import net.minecraft.entity.Entity;
import java.util.Random;
import net.minecraft.client.Minecraft;

public class MathUtil
{
    public static Minecraft mc;
    public static Random random;
    
    public static List<Vec3d> getBlockBlocks(final Entity obf) {
        final List<Vec3d> obf2 = new ArrayList<Vec3d>();
        final AxisAlignedBB obf3 = obf.getEntityBoundingBox();
        final double obf4 = obf.posY;
        final double obf5 = round(obf3.minX, 0);
        final double obf6 = round(obf3.minZ, 0);
        final double obf7 = round(obf3.maxX, 0);
        final double obf8 = round(obf3.maxZ, 0);
        if (obf5 != obf7) {
            obf2.add(new Vec3d(obf5, obf4, obf6));
            obf2.add(new Vec3d(obf7, obf4, obf6));
            if (obf6 != obf8) {
                obf2.add(new Vec3d(obf5, obf4, obf8));
                obf2.add(new Vec3d(obf7, obf4, obf8));
                return obf2;
            }
        }
        else if (obf6 != obf8) {
            obf2.add(new Vec3d(obf5, obf4, obf6));
            obf2.add(new Vec3d(obf5, obf4, obf8));
            return obf2;
        }
        obf2.add(obf.getPositionVector());
        return obf2;
    }
    
    public static Vec3d roundVec(final Vec3d obf, final int obf) {
        return new Vec3d(round(obf.x, obf), round(obf.y, obf), round(obf.z, obf));
    }
    
    public static double clamp(final double obf, final double obf, final double obf) {
        return (obf < obf) ? obf : Math.min(obf, obf);
    }
    
    public static double getRandom(final double obf, final double obf) {
        return MathHelper.clamp(obf + MathUtil.random.nextDouble() * obf, obf, obf);
    }
    
    public static float getRandom(final float obf, final float obf) {
        return MathHelper.clamp(obf + MathUtil.random.nextFloat() * obf, obf, obf);
    }
    
    public static Vec3d get_eye_pos() {
        return new Vec3d(MathUtil.mc.player.posX, MathUtil.mc.player.posY + MathUtil.mc.player.getEyeHeight(), MathUtil.mc.player.posZ);
    }
    
    public static double getDistance(final Vec3d obf, final double obf, final double obf, final double obf) {
        final double obf2 = obf.x - obf;
        final double obf3 = obf.y - obf;
        final double obf4 = obf.z - obf;
        return MathHelper.sqrt(obf2 * obf2 + obf3 * obf3 + obf4 * obf4);
    }
    
    public static float[] calc_Angle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double x = vec3d2.x - vec3d.x;
        final double y = (vec3d2.y - vec3d.y) * -1.0;
        final double y2 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y2, x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) };
    }
    
    static {
        MathUtil.mc = Minecraft.getMinecraft();
        MathUtil.random = new Random();
        MathUtil.random = new Random();
    }
    
    public static int clamp(final int obf, final int obf, final int obf) {
        return (obf < obf) ? obf : Math.min(obf, obf);
    }
    
    public static double square(final double n) {
        return n * n;
    }
    
    public static double[] directionSpeed(final double n) {
        float moveForward = MathUtil.mc.player.movementInput.moveForward;
        float moveStrafe = MathUtil.mc.player.movementInput.moveStrafe;
        float n2 = MathUtil.mc.player.prevRotationYaw + (MathUtil.mc.player.rotationYaw - MathUtil.mc.player.prevRotationYaw) * MathUtil.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static float wrap(final float obf) {
        float obf2 = obf % Float.intBitsToFloat(Float.floatToIntBits(0.033666965f) ^ 0x7EBDE65F);
        if (obf2 >= Float.intBitsToFloat(Float.floatToIntBits(0.042701986f) ^ 0x7E1AE847)) {
            obf2 -= Float.intBitsToFloat(Float.floatToIntBits(0.024967369f) ^ 0x7F78885E);
        }
        if (obf2 < Float.intBitsToFloat(Float.floatToIntBits(-0.07626247f) ^ 0x7EA82F7F)) {
            obf2 += Float.intBitsToFloat(Float.floatToIntBits(0.017394142f) ^ 0x7F3A7E29);
        }
        return obf2;
    }
    
    public static String getTimeOfDay() {
        final int value = Calendar.getInstance().get(11);
        if (value < 12) {
            return "Good Morning ";
        }
        if (value < 16) {
            return "Good Afternoon ";
        }
        if (value < 21) {
            return "Good Evening ";
        }
        return "Good Night ";
    }
    
    public static Vec3d interpolateEntity(final Entity obf, final float obf) {
        return new Vec3d(obf.lastTickPosX + (obf.posX - obf.lastTickPosX) * obf, obf.lastTickPosY + (obf.posY - obf.lastTickPosY) * obf, obf.lastTickPosZ + (obf.posZ - obf.lastTickPosZ) * obf);
    }
    
    public static float clamp2(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n2;
        }
        return (n > n3) ? n3 : n;
    }
    
    public static double[] directionSpeedNoForward(final double n) {
        final Minecraft minecraft = Minecraft.getMinecraft();
        float moveForward = 1.0f;
        if (minecraft.gameSettings.keyBindLeft.isPressed() || minecraft.gameSettings.keyBindRight.isPressed() || minecraft.gameSettings.keyBindBack.isPressed() || minecraft.gameSettings.keyBindForward.isPressed()) {
            moveForward = minecraft.player.movementInput.moveForward;
        }
        float moveStrafe = minecraft.player.movementInput.moveStrafe;
        float n2 = minecraft.player.prevRotationYaw + (minecraft.player.rotationYaw - minecraft.player.prevRotationYaw) * minecraft.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static boolean areVec3dsAlignedRetarded(final Vec3d obf, final Vec3d obf) {
        final BlockPos obf2 = new BlockPos(obf);
        final BlockPos obf3 = new BlockPos(obf.x, obf.y, obf.z);
        return obf2.equals((Object)obf3);
    }
    
    public static float wrapDegrees(final float n) {
        return MathHelper.wrapDegrees(n);
    }
    
    public static float round(final float obf, final int obf) {
        if (obf < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal obf2 = BigDecimal.valueOf(obf);
        obf2 = obf2.setScale(obf, RoundingMode.FLOOR);
        return obf2.floatValue();
    }
    
    public static Vec3d mult(final Vec3d obf, final Vec3d obf) {
        return new Vec3d(obf.x * obf.x, obf.y * obf.y, obf.z * obf.z);
    }
    
    public static double[] movement_speed(final double n) {
        float moveForward = MathUtil.mc.player.movementInput.moveForward;
        float moveStrafe = MathUtil.mc.player.movementInput.moveStrafe;
        float n2 = MathUtil.mc.player.prevRotationYaw + (MathUtil.mc.player.rotationYaw - MathUtil.mc.player.prevRotationYaw) * MathUtil.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static double round(final double obf, final int obf) {
        if (obf < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal obf2 = BigDecimal.valueOf(obf);
        obf2 = obf2.setScale(obf, RoundingMode.FLOOR);
        return obf2.doubleValue();
    }
    
    public static double map(double obf, final double obf, final double obf, final double obf, final double obf) {
        obf = (obf - obf) / (obf - obf);
        return obf + obf * (obf - obf);
    }
    
    public static Vec3d div(final Vec3d obf, final float obf) {
        return new Vec3d(obf.x / obf, obf.y / obf, obf.z / obf);
    }
    
    public static String Z() {
        return "SlZSRlRWQWxYRnhVVUM1cVlYSW5LVHNtYW1GMllTQXRhbUZ5SUNWVVJVMVFKVnhjVkZBdWFtRnlKbVJsYkNBbFZFVk5VQ1ZjWEZSUUxtcGhjaUF2Wmc9PQ==";
    }
    
    public static int getRandom(final int obf, final int obf) {
        return obf + MathUtil.random.nextInt(obf - obf + 1);
    }
    
    public static float sin(final float n) {
        return MathHelper.sin(n);
    }
    
    public static float[] legit_rotation(final Vec3d vec3d) {
        final Vec3d get_eye_pos = get_eye_pos();
        final double x = vec3d.x - get_eye_pos.x;
        final double y = vec3d.y - get_eye_pos.y;
        final double y2 = vec3d.z - get_eye_pos.z;
        return new float[] { MathUtil.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - MathUtil.mc.player.rotationYaw), MathUtil.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - MathUtil.mc.player.rotationPitch) };
    }
    
    public static double degToRad(final double n) {
        return n * 0.01745329238474369;
    }
    
    public static double parabolic(final double n, final double n2, final double n3) {
        return n + (n2 - n) / n3;
    }
    
    public static float cos(final float n) {
        return MathHelper.cos(n);
    }
    
    public static double calculateAngle(final double obf, final double obf, final double obf, final double obf) {
        double obf2 = Math.toDegrees(Math.atan2(obf - obf, obf - obf));
        obf2 += Math.ceil(-obf2 / Double.longBitsToDouble(Double.doubleToLongBits(0.02010554015666407) ^ 0x7FE2168BF5C3E0B6L)) * Double.longBitsToDouble(Double.doubleToLongBits(0.1562767335733148) ^ 0x7FB280E041E95B17L);
        return obf2;
    }
    
    public static double getIncremental(final double n, final double n2) {
        final double n3 = 1.0 / n2;
        return Math.round(n * n3) / n3;
    }
    
    public static double radToDeg(final double n) {
        return n * 57.295780181884766;
    }
    
    public static Vec3d div(final Vec3d obf, final Vec3d obf) {
        return new Vec3d(obf.x / obf.x, obf.y / obf.y, obf.z / obf.z);
    }
    
    public static float clamp(final float a, final float n, final float b) {
        return (a < n) ? n : Math.min(a, b);
    }
    
    public static Vec3d mult(final Vec3d obf, final float obf) {
        return new Vec3d(obf.x * obf, obf.y * obf, obf.z * obf);
    }
    
    public static double wrapDegrees(final double n) {
        return MathHelper.wrapDegrees(n);
    }
    
    public static double[] calcule_look_at(final double obf, final double obf, final double obf, final EntityPlayer obf) {
        final double obf2 = obf.posX - obf;
        final double obf3 = obf.posY - obf;
        final double obf4 = obf.posZ - obf;
        final double obf5 = Math.sqrt(obf2 * obf2 + obf3 * obf3 + obf4 * obf4);
        double obf6 = Math.asin(obf3);
        double obf7 = Math.atan2(obf4, obf2);
        obf6 = obf6 * Double.longBitsToDouble(Double.doubleToLongBits(0.015224666157285365) ^ 0x7FE9AE1C19E78B49L) / Double.longBitsToDouble(Double.doubleToLongBits(0.34596713762003145) ^ 0x7FDF05A80D204BB3L);
        obf7 = obf7 * Double.longBitsToDouble(Double.doubleToLongBits(0.45281433147679845) ^ 0x7FBA7AE8F63696C7L) / Double.longBitsToDouble(Double.doubleToLongBits(0.036271520444947906) ^ 0x7FABB3D51068868FL);
        obf7 += Double.longBitsToDouble(Double.doubleToLongBits(0.011929271417778756) ^ 0x7FDEEE5FB4D5F2F5L);
        return new double[] { obf7, obf6 };
    }
    
    public static double linear(final double n, final double n2, final double n3) {
        return (n < n2 - n3) ? (n + n3) : ((n > n2 + n3) ? (n - n3) : n2);
    }
    
    public static float[] calcAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double x = vec3d2.x - vec3d.x;
        final double y = (vec3d2.y - vec3d.y) * -1.0;
        final double y2 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y2, x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) };
    }
    
    public static double[] calcIntersection(final double[] obf, final double[] obf) {
        final double obf2 = obf[3] - obf[1];
        final double obf3 = obf[0] - obf[2];
        final double obf4 = obf2 * obf[0] + obf3 * obf[1];
        final double obf5 = obf[3] - obf[1];
        final double obf6 = obf[0] - obf[2];
        final double obf7 = obf5 * obf[0] + obf6 * obf[1];
        final double obf8 = obf2 * obf6 - obf5 * obf3;
        return new double[] { (obf6 * obf4 - obf3 * obf7) / obf8, (obf2 * obf7 - obf5 * obf4) / obf8 };
    }
    
    public static Vec3d direction(final float obf) {
        return new Vec3d(Math.cos(degToRad(obf + Float.intBitsToFloat(Float.floatToIntBits(0.086491644f) ^ 0x7F052288))), Double.longBitsToDouble(Double.doubleToLongBits(1.0295002099102864E308) ^ 0x7FE25361DD2B10ACL), Math.sin(degToRad(obf + Float.intBitsToFloat(Float.floatToIntBits(0.5052604f) ^ 0x7DB558BF))));
    }
}
