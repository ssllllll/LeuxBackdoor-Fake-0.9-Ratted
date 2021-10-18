// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3i;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumHand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.math.BlockPos;
import java.util.Arrays;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import java.util.List;
import net.minecraft.client.Minecraft;

public class BlockUtil
{
    public static Minecraft mc;
    public static List<Block> emptyBlocks;
    public static List<Block> rightclickableBlocks;
    
    static {
        BlockUtil.mc = Minecraft.getMinecraft();
        BlockUtil.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
        BlockUtil.rightclickableBlocks = Arrays.asList((Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, (Block)Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, (Block)Blocks.BEACON, Blocks.BED, Blocks.FURNACE, (Block)Blocks.OAK_DOOR, (Block)Blocks.SPRUCE_DOOR, (Block)Blocks.BIRCH_DOOR, (Block)Blocks.JUNGLE_DOOR, (Block)Blocks.ACACIA_DOOR, (Block)Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, (Block)Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE);
    }
    
    public static boolean isValidBlock(final BlockPos blockPos) {
        final Block block = BlockUtil.mc.world.getBlockState(blockPos).getBlock();
        return !(block instanceof BlockLiquid) && block.getMaterial((IBlockState)null) != Material.AIR;
    }
    
    public static void placeCrystalOnBlock(final BlockPos obf, final EnumHand obf) {
        final RayTraceResult obf2 = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(obf.getX() + Double.longBitsToDouble(Double.doubleToLongBits(2.2056124648797826) ^ 0x7FE1A51825E2780BL), obf.getY() - Double.longBitsToDouble(Double.doubleToLongBits(18.109962365586735) ^ 0x7FD21C267E5BFC5FL), obf.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(30.83644693390951) ^ 0x7FDED62162E1FB17L)));
        final EnumFacing obf3 = (obf2 == null || obf2.sideHit == null) ? EnumFacing.UP : obf2.sideHit;
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(obf, obf3, obf, Float.intBitsToFloat(Float.floatToIntBits(1.988162E38f) ^ 0x7F15929B), Float.intBitsToFloat(Float.floatToIntBits(2.7017871E38f) ^ 0x7F4B4287), Float.intBitsToFloat(Float.floatToIntBits(3.3478093E38f) ^ 0x7F7BDC76)));
    }
    
    public static void swingArm(final Setting setting) {
        if (setting.in("Mainhand") || setting.in("Both")) {
            BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (setting.in("Offhand") || setting.in("Both")) {
            BlockUtil.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }
    
    public static void rotatePacket(final double obf, final double obf, final double obf) {
        final double obf2 = obf - BlockUtil.mc.player.posX;
        final double obf3 = obf - (BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight());
        final double obf4 = obf - BlockUtil.mc.player.posZ;
        final double obf5 = Math.sqrt(obf2 * obf2 + obf4 * obf4);
        final float obf6 = (float)Math.toDegrees(Math.atan2(obf4, obf2)) - Float.intBitsToFloat(Float.floatToIntBits(0.014011222f) ^ 0x7ED18F53);
        final float obf7 = (float)(-Math.toDegrees(Math.atan2(obf3, obf5)));
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(obf6, obf7, BlockUtil.mc.player.onGround));
    }
    
    public static boolean placeBlock(final BlockPos obf, final int obf, final boolean obf, final boolean obf, final Setting obf) {
        if (isBlockEmpty(obf)) {
            int obf2 = -1;
            if (obf != BlockUtil.mc.player.inventory.currentItem) {
                obf2 = BlockUtil.mc.player.inventory.currentItem;
                BlockUtil.mc.player.inventory.currentItem = obf;
            }
            final EnumFacing[] values;
            final EnumFacing[] obf3 = values = EnumFacing.values();
            for (final EnumFacing obf4 : values) {
                final Block obf5 = BlockUtil.mc.world.getBlockState(obf.offset(obf4)).getBlock();
                final Vec3d obf6 = new Vec3d(obf.getX() + Double.longBitsToDouble(Double.doubleToLongBits(108.90293295166917) ^ 0x7FBB39C9A74A7997L) + obf4.getXOffset() * Double.longBitsToDouble(Double.doubleToLongBits(2.6026792160327594) ^ 0x7FE4D2497B16B78BL), obf.getY() + Double.longBitsToDouble(Double.doubleToLongBits(31.033902256332507) ^ 0x7FDF08ADD17A356FL) + obf4.getYOffset() * Double.longBitsToDouble(Double.doubleToLongBits(11.258672835987246) ^ 0x7FC68470C415DB8BL), obf.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(21.870603678616266) ^ 0x7FD5DEDFE1F76F25L) + obf4.getZOffset() * Double.longBitsToDouble(Double.doubleToLongBits(2.675528227844277) ^ 0x7FE5677B57F0ED95L));
                if (!BlockUtil.emptyBlocks.contains(obf5) && BlockUtil.mc.player.getPositionEyes(BlockUtil.mc.getRenderPartialTicks()).distanceTo(obf6) <= Double.longBitsToDouble(Double.doubleToLongBits(1.7033340422336865) ^ 0x7FEA40DB3258EA27L)) {
                    final float[] obf7 = { BlockUtil.mc.player.rotationYaw, BlockUtil.mc.player.rotationPitch };
                    if (obf) {
                        rotatePacket(obf6.x, obf6.y, obf6.z);
                    }
                    if (BlockUtil.rightclickableBlocks.contains(obf5)) {
                        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, obf.offset(obf4), obf4.getOpposite(), new Vec3d((Vec3i)obf), EnumHand.MAIN_HAND);
                    if (BlockUtil.rightclickableBlocks.contains(obf5)) {
                        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    if (obf) {
                        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(obf7[0], obf7[1], BlockUtil.mc.player.onGround));
                    }
                    swingArm(obf);
                    if (obf2 != -1) {
                        BlockUtil.mc.player.inventory.currentItem = obf2;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isBlockEmpty(final BlockPos obf) {
        try {
            if (BlockUtil.emptyBlocks.contains(BlockUtil.mc.world.getBlockState(obf).getBlock())) {
                final AxisAlignedBB obf2 = new AxisAlignedBB(obf);
                for (final Entity obf4 : BlockUtil.mc.world.loadedEntityList) {
                    if (obf4 instanceof EntityLivingBase && obf2.intersects(obf4.getEntityBoundingBox())) {
                        return false;
                    }
                }
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static boolean isScaffoldPos(final BlockPos blockPos) {
        return BlockUtil.mc.world.isAirBlock(blockPos) || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.SNOW_LAYER || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.TALLGRASS || BlockUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid;
    }
    
    public static void openBlock(final BlockPos obf) {
        final EnumFacing[] values;
        final EnumFacing[] obf2 = values = EnumFacing.values();
        for (final EnumFacing obf3 : values) {
            final Block obf4 = BlockUtil.mc.world.getBlockState(obf.offset(obf3)).getBlock();
            if (BlockUtil.emptyBlocks.contains(obf4)) {
                BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, obf, obf3.getOpposite(), new Vec3d((Vec3i)obf), EnumHand.MAIN_HAND);
                return;
            }
        }
    }
    
    public static boolean canPlaceBlock(final BlockPos obf) {
        if (isBlockEmpty(obf)) {
            final EnumFacing[] values;
            final EnumFacing[] obf2 = values = EnumFacing.values();
            for (final EnumFacing obf3 : values) {
                if (!BlockUtil.emptyBlocks.contains(BlockUtil.mc.world.getBlockState(obf.offset(obf3)).getBlock()) && BlockUtil.mc.player.getPositionEyes(BlockUtil.mc.getRenderPartialTicks()).distanceTo(new Vec3d(obf.getX() + Double.longBitsToDouble(Double.doubleToLongBits(18.412930603767382) ^ 0x7FD269B5D1EEB2CDL) + obf3.getXOffset() * Double.longBitsToDouble(Double.doubleToLongBits(17.669124462651087) ^ 0x7FD1AB4BBDA40A3DL), obf.getY() + Double.longBitsToDouble(Double.doubleToLongBits(3.8963434161445414) ^ 0x7FEF2BB618D29B15L) + obf3.getYOffset() * Double.longBitsToDouble(Double.doubleToLongBits(18.899437241899538) ^ 0x7FD2E64184E2C351L), obf.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(2.608005952341837) ^ 0x7FE4DD323988A6F6L) + obf3.getZOffset() * Double.longBitsToDouble(Double.doubleToLongBits(2.087612746218891) ^ 0x7FE0B36E4FBDC878L))) <= Double.longBitsToDouble(Double.doubleToLongBits(0.3620500444280394) ^ 0x7FC62BD3F3155C97L)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos obf, final boolean obf, final float obf) {
        return !obf || BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)obf.getX(), (double)(obf.getY() + obf), (double)obf.getZ()), false, true, false) == null;
    }
    
    public static boolean canSeeBlock(final BlockPos obf) {
        return BlockUtil.mc.player != null && BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)obf.getX(), (double)obf.getY(), (double)obf.getZ()), false, true, false) == null;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos obf, final boolean obf) {
        return rayTracePlaceCheck(obf, obf, Float.intBitsToFloat(Float.floatToIntBits(4.919322f) ^ 0x7F1D6B16));
    }
}
