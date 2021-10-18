// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.init.MobEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import java.util.ArrayList;
import net.minecraft.block.Block;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayer;
import java.util.List;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import java.util.Iterator;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;

public class CrystalUtil
{
    public static Minecraft mc;
    
    public static boolean lambda$possiblePlacePositions$0(final boolean obf, final boolean obf, final BlockPos obf) {
        return canPlaceCrystal(obf, obf, obf);
    }
    
    public static boolean canPlaceCrystal(final BlockPos obf, final boolean obf) {
        final BlockPos obf2 = obf.add(0, 1, 0);
        final BlockPos obf3 = obf.add(0, 2, 0);
        final BlockPos obf4 = obf.add(0, 3, 0);
        try {
            if (CrystalUtil.mc.world.getBlockState(obf).getBlock() != Blocks.BEDROCK && CrystalUtil.mc.world.getBlockState(obf).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (CrystalUtil.mc.world.getBlockState(obf2).getBlock() != Blocks.AIR || (CrystalUtil.mc.world.getBlockState(obf3).getBlock() != Blocks.AIR && !obf)) {
                return false;
            }
            for (final Object obf5 : CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(obf2))) {
                if (obf5 instanceof EntityEnderCrystal) {
                    continue;
                }
                return false;
            }
            for (final Object obf5 : CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(obf3))) {
                if (obf5 instanceof EntityEnderCrystal) {
                    continue;
                }
                return false;
            }
            for (final Object obf5 : CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(obf4))) {
                if (!(obf5 instanceof EntityEnderCrystal)) {
                    continue;
                }
                return false;
            }
        }
        catch (Exception obf6) {
            return false;
        }
        return true;
    }
    
    public static boolean canPlaceCrystal(final BlockPos obf, final boolean obf, final boolean obf) {
        final BlockPos obf2 = obf.add(0, 1, 0);
        final BlockPos obf3 = obf.add(0, 2, 0);
        final BlockPos obf4 = obf.add(0, 3, 0);
        try {
            if (CrystalUtil.mc.world.getBlockState(obf).getBlock() != Blocks.BEDROCK && CrystalUtil.mc.world.getBlockState(obf).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (CrystalUtil.mc.world.getBlockState(obf2).getBlock() != Blocks.AIR || (CrystalUtil.mc.world.getBlockState(obf3).getBlock() != Blocks.AIR && !obf)) {
                return false;
            }
            if (!obf) {
                if (CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(obf2)).isEmpty()) {
                    if (CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(obf3)).isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
            for (final Object obf5 : CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(obf2))) {
                if (!(obf5 instanceof EntityEnderCrystal)) {
                    return false;
                }
            }
            for (final Object obf5 : CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(obf3))) {
                if (!(obf5 instanceof EntityEnderCrystal)) {
                    return false;
                }
            }
            for (final Object obf5 : CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(obf4))) {
                if (obf5 instanceof EntityEnderCrystal) {
                    return false;
                }
            }
        }
        catch (Exception obf6) {
            return false;
        }
        return true;
    }
    
    public static float calculateDamage(final double obf, final double obf, final double obf, final Entity obf) {
        if (obf == CrystalUtil.mc.player && CrystalUtil.mc.player.capabilities.isCreativeMode) {
            return Float.intBitsToFloat(Float.floatToIntBits(1.710232E38f) ^ 0x7F00A9DF);
        }
        final float obf2 = Float.intBitsToFloat(Float.floatToIntBits(0.17652625f) ^ 0x7F74C34C);
        final double obf3 = obf.getDistance(obf, obf, obf) / Double.longBitsToDouble(Double.doubleToLongBits(1.2539757205935331) ^ 0x7FDC1048D85ED60DL);
        final Vec3d obf4 = new Vec3d(obf, obf, obf);
        double obf5 = Double.longBitsToDouble(Double.doubleToLongBits(1.4472395269077344E308) ^ 0x7FE9C2FFFF4573A1L);
        try {
            obf5 = obf.world.getBlockDensity(obf4, obf.getEntityBoundingBox());
        }
        catch (Exception ex) {}
        final double obf6 = (Double.longBitsToDouble(Double.doubleToLongBits(85.51991188574742) ^ 0x7FA561463C80858FL) - obf3) * obf5;
        final float obf7 = (float)(int)((obf6 * obf6 + obf6) / Double.longBitsToDouble(Double.doubleToLongBits(0.44567490119220504) ^ 0x7FDC85F005512FF5L) * Double.longBitsToDouble(Double.doubleToLongBits(1.5457863129772311) ^ 0x7FE4BB8A6DCD774FL) * Double.longBitsToDouble(Double.doubleToLongBits(0.1460030596886395) ^ 0x7FEAB03A6F3D4429L) + Double.longBitsToDouble(Double.doubleToLongBits(45.95646153067752) ^ 0x7FB6FA6D54D9122FL));
        double obf8 = Double.longBitsToDouble(Double.doubleToLongBits(8.257261140374643) ^ 0x7FD083B7BB70E1ABL);
        if (obf instanceof EntityLivingBase) {
            obf8 = getBlastReduction((EntityLivingBase)obf, getDamageMultiplied(obf7), new Explosion((World)CrystalUtil.mc.world, (Entity)null, obf, obf, obf, Float.intBitsToFloat(Float.floatToIntBits(1.0758182f) ^ 0x7F49B469), false, true));
        }
        return (float)obf8;
    }
    
    public static float calculateDamage(final EntityEnderCrystal obf, final Entity obf) {
        return calculateDamage(obf.posX, obf.posY, obf.posZ, obf);
    }
    
    public static NonNullList possiblePlacePositions(final float obf, final boolean obf) {
        final NonNullList obf2 = NonNullList.create();
        obf2.addAll((Collection)getSphere(getPlayerPos((EntityPlayer)CrystalUtil.mc.player), obf, (int)obf, false, true, 0).stream().filter(CrystalUtil::lambda$possiblePlacePositions$1).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return obf2;
    }
    
    public static boolean canPlaceCrystal(final BlockPos obf) {
        final Block obf2 = CrystalUtil.mc.world.getBlockState(obf).getBlock();
        if (obf2 == Blocks.OBSIDIAN || obf2 == Blocks.BEDROCK) {
            final Block obf3 = CrystalUtil.mc.world.getBlockState(obf.add(0, 1, 0)).getBlock();
            final Block obf4 = CrystalUtil.mc.world.getBlockState(obf.add(0, 2, 0)).getBlock();
            if (obf3 == Blocks.AIR && obf4 == Blocks.AIR && CrystalUtil.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(obf.add(0, 1, 0))).isEmpty() && CrystalUtil.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(obf.add(0, 2, 0))).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public static BlockPos getPlayerPos(final EntityPlayer obf) {
        return new BlockPos(Math.floor(obf.posX), Math.floor(obf.posY), Math.floor(obf.posZ));
    }
    
    static {
        CrystalUtil.mc = Minecraft.getMinecraft();
    }
    
    public static float getDamageMultiplied(final float n) {
        final int id = CrystalUtil.mc.world.getDifficulty().getId();
        return n * ((id == 0) ? 0.0f : ((id == 2) ? 1.0f : ((id == 1) ? 0.5f : 1.5f)));
    }
    
    public static List<BlockPos> possiblePlacePositions(final float obf, final boolean obf, final boolean obf) {
        final NonNullList<BlockPos> obf2 = (NonNullList<BlockPos>)NonNullList.create();
        obf2.addAll((Collection)getSphere(getPlayerPos((EntityPlayer)CrystalUtil.mc.player), obf, (int)obf, false, true, 0).stream().filter(CrystalUtil::lambda$possiblePlacePositions$0).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)obf2;
    }
    
    public static List<BlockPos> getSphere(final BlockPos obf, final float obf, final int obf, final boolean obf, final boolean obf, final int obf) {
        final List<BlockPos> obf2 = new ArrayList<BlockPos>();
        final int obf3 = obf.getX();
        final int obf4 = obf.getY();
        final int obf5 = obf.getZ();
        for (int obf6 = obf3 - (int)obf; obf6 <= obf3 + obf; ++obf6) {
            for (int obf7 = obf5 - (int)obf; obf7 <= obf5 + obf; ++obf7) {
                for (int obf8 = obf ? (obf4 - (int)obf) : obf4; obf8 < (obf ? (obf4 + obf) : ((float)(obf4 + obf))); ++obf8) {
                    final double obf9 = (obf3 - obf6) * (obf3 - obf6) + (obf5 - obf7) * (obf5 - obf7) + (obf ? ((obf4 - obf8) * (obf4 - obf8)) : 0);
                    if (obf9 < obf * obf && (!obf || obf9 >= (obf - Float.intBitsToFloat(Float.floatToIntBits(15.541413f) ^ 0x7EF8A9A1)) * (obf - Float.intBitsToFloat(Float.floatToIntBits(7.3007336f) ^ 0x7F699F9C)))) {
                        final BlockPos obf10 = new BlockPos(obf6, obf8 + obf, obf7);
                        obf2.add(obf10);
                    }
                }
            }
        }
        return obf2;
    }
    
    public static float getBlastReduction(final EntityLivingBase obf, final float obf, final Explosion obf) {
        float obf2 = obf;
        if (obf instanceof EntityPlayer) {
            final EntityPlayer obf3 = (EntityPlayer)obf;
            final DamageSource obf4 = DamageSource.causeExplosionDamage(obf);
            obf2 = CombatRules.getDamageAfterAbsorb(obf2, (float)obf3.getTotalArmorValue(), (float)obf3.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            int obf5 = 0;
            try {
                obf5 = EnchantmentHelper.getEnchantmentModifierDamage(obf3.getArmorInventoryList(), obf4);
            }
            catch (Exception ex) {}
            final float obf6 = MathHelper.clamp((float)obf5, Float.intBitsToFloat(Float.floatToIntBits(6.413788E37f) ^ 0x7E410207), Float.intBitsToFloat(Float.floatToIntBits(0.37523618f) ^ 0x7F601EF5));
            obf2 *= Float.intBitsToFloat(Float.floatToIntBits(6.646663f) ^ 0x7F54B177) - obf6 / Float.intBitsToFloat(Float.floatToIntBits(0.46854657f) ^ 0x7F27E556);
            if (obf.isPotionActive(MobEffects.RESISTANCE)) {
                obf2 -= obf2 / Float.intBitsToFloat(Float.floatToIntBits(0.35954592f) ^ 0x7E381667);
            }
            obf2 = Math.max(obf2, Float.intBitsToFloat(Float.floatToIntBits(1.3181576E38f) ^ 0x7EC65595));
            return obf2;
        }
        obf2 = CombatRules.getDamageAfterAbsorb(obf2, (float)obf.getTotalArmorValue(), (float)obf.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        return obf2;
    }
    
    public static boolean lambda$possiblePlacePositions$1(final boolean obf, final BlockPos obf) {
        return canPlaceCrystal(obf, obf);
    }
}
