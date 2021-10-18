// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.PotionEffect;
import java.util.Objects;
import net.minecraft.potion.Potion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

public class EntityUtil
{
    public static Minecraft mc;
    
    public static Vec3d getInterpolatedRenderPos(final Entity obf, final float obf2) {
        return getInterpolatedPos(obf, obf2).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }
    
    public static boolean isLiving(final Entity obf) {
        return obf instanceof EntityLivingBase;
    }
    
    public static Vec3d getInterpolatedAmount(final Entity obf, final double obf) {
        return getInterpolatedAmount(obf, obf, obf, obf);
    }
    
    public static double getMaxSpeed() {
        double n = 0.2873;
        if (EntityUtil.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(1)))) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(EntityUtil.mc.player.getActivePotionEffect((Potion)Objects.requireNonNull(Potion.getPotionById(1)))).getAmplifier() + 1);
        }
        return n;
    }
    
    public static Vec3d get_interpolated_amout(final Entity obf, final float n) {
        return process_interpolated_amount(obf, n, n, n);
    }
    
    public static boolean isDrivenByPlayer(final Entity entity) {
        if (Wrapper.getPlayer() != null) {
            if (entity != null) {
                if (entity.equals((Object)Wrapper.getPlayer().getRidingEntity())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        EntityUtil.mc = Minecraft.getMinecraft();
    }
    
    public static Vec3d get_interpolated_entity(final Entity obf, final float obf) {
        return new Vec3d(obf.lastTickPosX, obf.lastTickPosY, obf.lastTickPosZ).add(get_interpolated_amout(obf, obf));
    }
    
    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos((EntityUtil.mc.player.getRidingEntity() != null) ? EntityUtil.mc.player.getRidingEntity().posX : EntityUtil.mc.player.posX, (EntityUtil.mc.player.getRidingEntity() != null) ? EntityUtil.mc.player.getRidingEntity().posY : EntityUtil.mc.player.posY, (EntityUtil.mc.player.getRidingEntity() != null) ? EntityUtil.mc.player.getRidingEntity().posZ : EntityUtil.mc.player.posZ);
    }
    
    public static Vec3d get_interpolated_render_pos(final Entity obf, final float obf) {
        return process_interpolated_pos(obf, obf).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }
    
    public static boolean isAboveWater(final Entity obf, final boolean obf) {
        if (obf == null) {
            return false;
        }
        final double obf2 = obf.posY - (obf ? Double.longBitsToDouble(Double.doubleToLongBits(393.9726927158209) ^ 0x7FE627C1CDB9A69AL) : (isPlayer(obf) ? Double.longBitsToDouble(Double.doubleToLongBits(15.5777236924826) ^ 0x7FE6BE52FFC33506L) : Double.longBitsToDouble(Double.doubleToLongBits(11.860543000100064) ^ 0x7FC7B89917947687L)));
        for (int obf3 = MathHelper.floor(obf.posX); obf3 < MathHelper.ceil(obf.posX); ++obf3) {
            for (int obf4 = MathHelper.floor(obf.posZ); obf4 < MathHelper.ceil(obf.posZ); ++obf4) {
                final BlockPos obf5 = new BlockPos(obf3, MathHelper.floor(obf2), obf4);
                if (Wrapper.getWorld().getBlockState(obf5).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Vec3d process_interpolated_amount(final Entity obf, final double obf, final double obf, final double obf) {
        return new Vec3d((obf.posX - obf.lastTickPosX) * obf, (obf.posY - obf.lastTickPosY) * obf, (obf.posZ - obf.lastTickPosZ) * obf);
    }
    
    public static void attackEntity(final Entity obf, final boolean obf, final Setting obf) {
        if (obf) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(obf));
        }
        else {
            EntityUtil.mc.playerController.attackEntity((EntityPlayer)EntityUtil.mc.player, obf);
        }
        if (obf.in("Mainhand") || obf.in("Both")) {
            EntityUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (!obf.in("Offhand")) {
            if (!obf.in("Both")) {
                return;
            }
        }
        EntityUtil.mc.player.swingArm(EnumHand.OFF_HAND);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity obf, final double obf, final double obf, final double obf) {
        return new Vec3d((obf.posX - obf.lastTickPosX) * obf, Double.longBitsToDouble(Double.doubleToLongBits(2.1657305879552588E307) ^ 0x7FBED74D90B2244FL) * obf, (obf.posZ - obf.lastTickPosZ) * obf);
    }
    
    public static BlockPos is_cityable(final EntityPlayer obf, final boolean obf) {
        final BlockPos obf2 = new BlockPos(obf.posX, obf.posY, obf.posZ);
        if (EntityUtil.mc.world.getBlockState(obf2.north()).getBlock() == Blocks.OBSIDIAN) {
            if (obf) {
                return obf2.north();
            }
            if (EntityUtil.mc.world.getBlockState(obf2.north().north()).getBlock() == Blocks.AIR) {
                return obf2.north();
            }
        }
        if (EntityUtil.mc.world.getBlockState(obf2.east()).getBlock() == Blocks.OBSIDIAN) {
            if (obf) {
                return obf2.east();
            }
            if (EntityUtil.mc.world.getBlockState(obf2.east().east()).getBlock() == Blocks.AIR) {
                return obf2.east();
            }
        }
        if (EntityUtil.mc.world.getBlockState(obf2.south()).getBlock() == Blocks.OBSIDIAN) {
            if (obf) {
                return obf2.south();
            }
            if (EntityUtil.mc.world.getBlockState(obf2.south().south()).getBlock() == Blocks.AIR) {
                return obf2.south();
            }
        }
        if (EntityUtil.mc.world.getBlockState(obf2.west()).getBlock() == Blocks.OBSIDIAN) {
            if (obf) {
                return obf2.west();
            }
            if (EntityUtil.mc.world.getBlockState(obf2.west().west()).getBlock() == Blocks.AIR) {
                return obf2.west();
            }
        }
        return null;
    }
    
    public static Vec3d getInterpolatedPos(final Entity obf, final float obf) {
        return new Vec3d(obf.lastTickPosX, obf.lastTickPosY, obf.lastTickPosZ).add(getInterpolatedAmount(obf, obf));
    }
    
    public static boolean isMoving() {
        return EntityUtil.mc.player.moveForward != 0.0 || EntityUtil.mc.player.moveStrafing != 0.0;
    }
    
    public static Vec3d process_interpolated_pos(final Entity obf, final float obf) {
        return new Vec3d(obf.lastTickPosX, obf.lastTickPosY, obf.lastTickPosZ).add(get_interpolated_amout(obf, obf));
    }
    
    public static boolean isAboveWater(final Entity obf) {
        return isAboveWater(obf, false);
    }
    
    public static boolean isEntityMoving(final Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            return EntityUtil.mc.gameSettings.keyBindForward.isKeyDown() || EntityUtil.mc.gameSettings.keyBindBack.isKeyDown() || EntityUtil.mc.gameSettings.keyBindLeft.isKeyDown() || EntityUtil.mc.gameSettings.keyBindRight.isKeyDown();
        }
        return entity.motionX != 0.0 || entity.motionY != 0.0 || entity.motionZ != 0.0;
    }
    
    public static boolean isInWater(final Entity obf) {
        if (obf == null) {
            return false;
        }
        final double obf2 = obf.posY + Double.longBitsToDouble(Double.doubleToLongBits(143.917355444799) ^ 0x7FE587BBBE60537FL);
        for (int obf3 = MathHelper.floor(obf.posX); obf3 < MathHelper.ceil(obf.posX); ++obf3) {
            for (int obf4 = MathHelper.floor(obf.posZ); obf4 < MathHelper.ceil(obf.posZ); ++obf4) {
                final BlockPos obf5 = new BlockPos(obf3, (int)obf2, obf4);
                if (Wrapper.getWorld().getBlockState(obf5).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isPlayer(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
}
