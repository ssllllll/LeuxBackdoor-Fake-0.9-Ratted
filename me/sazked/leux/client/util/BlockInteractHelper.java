// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import me.sazked.leux.client.manager.ConfigManager;
import me.sazked.leux.client.guiscreen.settings.Setting;
import java.util.Base64;
import java.util.Arrays;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.math.MathHelper;
import java.util.ArrayList;
import net.minecraft.init.Blocks;
import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumActionResult;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockSlab;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Block;
import java.util.List;

public class BlockInteractHelper
{
    public static List<Block> blackList;
    public static String set_valueX;
    public static String Y;
    public static Minecraft mc;
    public static String set_valueZ;
    public static String set_valueY;
    public static String Z;
    public static List<Block> shulkerList;
    public static String X;
    
    public static PlaceResult place(final BlockPos obf, final float obf, final boolean obf, final boolean obf) {
        final IBlockState obf2 = BlockInteractHelper.mc.world.getBlockState(obf);
        final boolean obf3 = obf2.getMaterial().isReplaceable();
        final boolean obf4 = obf2.getBlock() instanceof BlockSlab;
        if (!obf3) {
            if (!obf4) {
                return PlaceResult.NotReplaceable;
            }
        }
        if (!checkForNeighbours(obf)) {
            return PlaceResult.Neighbors;
        }
        if (obf && obf4 && !obf2.isFullCube()) {
            return PlaceResult.CantPlace;
        }
        final Vec3d obf5 = new Vec3d(BlockInteractHelper.mc.player.posX, BlockInteractHelper.mc.player.posY + BlockInteractHelper.mc.player.getEyeHeight(), BlockInteractHelper.mc.player.posZ);
        for (final EnumFacing obf6 : EnumFacing.values()) {
            final BlockPos obf7 = obf.offset(obf6);
            final EnumFacing obf8 = obf6.getOpposite();
            if (BlockInteractHelper.mc.world.getBlockState(obf7).getBlock().canCollideCheck(BlockInteractHelper.mc.world.getBlockState(obf7), false)) {
                final Vec3d obf9 = new Vec3d((Vec3i)obf7).add(Double.longBitsToDouble(Double.doubleToLongBits(11.898920125924233) ^ 0x7FC7CC3F423D1F1FL), Double.longBitsToDouble(Double.doubleToLongBits(8.786568629964128) ^ 0x7FC192B91F9B82CBL), Double.longBitsToDouble(Double.doubleToLongBits(133.98642853762792) ^ 0x7F80BF90D2949E7FL)).add(new Vec3d(obf8.getDirectionVec()).scale(Double.longBitsToDouble(Double.doubleToLongBits(3.281170336569674) ^ 0x7FEA3FD63BC16062L)));
                if (obf5.distanceTo(obf9) <= obf) {
                    final Block obf10 = BlockInteractHelper.mc.world.getBlockState(obf7).getBlock();
                    final boolean obf11 = obf10.onBlockActivated((World)BlockInteractHelper.mc.world, obf, BlockInteractHelper.mc.world.getBlockState(obf), (EntityPlayer)BlockInteractHelper.mc.player, EnumHand.MAIN_HAND, obf6, Float.intBitsToFloat(Float.floatToIntBits(4.695631E37f) ^ 0x7E0D4DD3), Float.intBitsToFloat(Float.floatToIntBits(1.7129589E37f) ^ 0x7D4E309F), Float.intBitsToFloat(Float.floatToIntBits(8.546165E37f) ^ 0x7E8096A1));
                    Label_0872: {
                        if (!BlockInteractHelper.blackList.contains(obf10)) {
                            if (!BlockInteractHelper.shulkerList.contains(obf10) && !obf11) {
                                break Label_0872;
                            }
                        }
                        BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractHelper.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    if (obf) {
                        faceVectorPacketInstant(obf9);
                    }
                    final EnumActionResult obf12 = BlockInteractHelper.mc.playerController.processRightClickBlock(BlockInteractHelper.mc.player, BlockInteractHelper.mc.world, obf7, obf8, obf9, EnumHand.MAIN_HAND);
                    if (obf12 != EnumActionResult.FAIL) {
                        BlockInteractHelper.mc.player.swingArm(EnumHand.MAIN_HAND);
                        if (obf11) {
                            BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractHelper.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                        }
                        return PlaceResult.Placed;
                    }
                }
            }
        }
        return PlaceResult.CantPlace;
    }
    
    public static void faceVectorPacketInstant(final Vec3d obf) {
        final float[] obf2 = getLegitRotations(obf);
        BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(obf2[0], obf2[1], BlockInteractHelper.mc.player.onGround));
    }
    
    public static boolean isInterceptedByOther(final BlockPos obf) {
        for (final Entity obf2 : BlockInteractHelper.mc.world.loadedEntityList) {
            if (obf2.equals((Object)BlockInteractHelper.mc.player)) {
                continue;
            }
            if (new AxisAlignedBB(obf).intersects(obf2.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
    
    public static void placeBlockScaffold(final BlockPos obf) {
        final Vec3d obf2 = new Vec3d(Wrapper.getPlayer().posX, Wrapper.getPlayer().posY + Wrapper.getPlayer().getEyeHeight(), Wrapper.getPlayer().posZ);
        for (final EnumFacing obf3 : EnumFacing.values()) {
            final BlockPos obf4 = obf.offset(obf3);
            final EnumFacing obf5 = obf3.getOpposite();
            final Vec3d obf6;
            if (canBeClicked(obf4) && obf2.squareDistanceTo(obf6 = new Vec3d((Vec3i)obf4).add(Double.longBitsToDouble(Double.doubleToLongBits(3.04288649877807) ^ 0x7FE857D4E06D88B7L), Double.longBitsToDouble(Double.doubleToLongBits(26.30187001318421) ^ 0x7FDA4D475A6A44EFL), Double.longBitsToDouble(Double.doubleToLongBits(28.668984564738135) ^ 0x7FDCAB42928B143FL)).add(new Vec3d(obf5.getDirectionVec()).scale(Double.longBitsToDouble(Double.doubleToLongBits(3.070816251520595) ^ 0x7FE891081C62733BL)))) <= Double.longBitsToDouble(Double.doubleToLongBits(0.30964136622190014) ^ 0x7FE1C12A055A5B43L)) {
                faceVectorPacketInstant(obf6);
                processRightClickBlock(obf4, obf5, obf6);
                Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
                BlockInteractHelper.mc.rightClickDelayTimer = 4;
                return;
            }
        }
    }
    
    public static ValidResult valid(final BlockPos obf) {
        if (!BlockInteractHelper.mc.world.checkNoEntityCollision(new AxisAlignedBB(obf))) {
            return ValidResult.NoEntityCollision;
        }
        if (!checkForNeighbours(obf)) {
            return ValidResult.NoNeighbors;
        }
        final IBlockState obf2 = BlockInteractHelper.mc.world.getBlockState(obf);
        if (obf2.getBlock() == Blocks.AIR) {
            final BlockPos[] array;
            final BlockPos[] obf3 = array = new BlockPos[] { obf.north(), obf.south(), obf.east(), obf.west(), obf.up(), obf.down() };
            for (final BlockPos obf4 : array) {
                final IBlockState obf5 = BlockInteractHelper.mc.world.getBlockState(obf4);
                if (obf5.getBlock() != Blocks.AIR) {
                    for (final EnumFacing obf6 : EnumFacing.values()) {
                        final BlockPos obf7 = obf.offset(obf6);
                        if (BlockInteractHelper.mc.world.getBlockState(obf7).getBlock().canCollideCheck(BlockInteractHelper.mc.world.getBlockState(obf7), false)) {
                            return ValidResult.Ok;
                        }
                    }
                }
            }
            return ValidResult.NoNeighbors;
        }
        return ValidResult.AlreadyBlockThere;
    }
    
    public static Block getBlock(final BlockPos obf) {
        return getState(obf).getBlock();
    }
    
    public static void placeBlockTwo(final BlockPos obf) {
        for (final EnumFacing obf2 : EnumFacing.values()) {
            if (!BlockInteractHelper.mc.world.getBlockState(obf.offset(obf2)).getBlock().equals(Blocks.AIR)) {
                final Vec3d obf3 = new Vec3d(obf.getX() + Double.longBitsToDouble(Double.doubleToLongBits(2.2803401869207525) ^ 0x7FE23E22FEF4A21EL) + obf2.getXOffset() * Double.longBitsToDouble(Double.doubleToLongBits(2.1269916850128543) ^ 0x7FE10414376FF401L), obf.getY() + Double.longBitsToDouble(Double.doubleToLongBits(14.733060214448718) ^ 0x7FCD7753AB1E1CABL) + obf2.getYOffset() * Double.longBitsToDouble(Double.doubleToLongBits(2.0544700356229897) ^ 0x7FE06F8DFC6CE6CDL), obf.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(2.114001613589297) ^ 0x7FE0E979AD907545L) + obf2.getZOffset() * Double.longBitsToDouble(Double.doubleToLongBits(15.81410443854726) ^ 0x7FCFA0D24C062D87L));
                final float[] obf4 = { BlockInteractHelper.mc.player.rotationYaw, BlockInteractHelper.mc.player.rotationPitch };
                BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(obf3.z - BlockInteractHelper.mc.player.posZ, obf3.x - BlockInteractHelper.mc.player.posX)) - Float.intBitsToFloat(Float.floatToIntBits(0.12138278f) ^ 0x7F4C9789), (float)(-Math.toDegrees(Math.atan2(obf3.y - (BlockInteractHelper.mc.player.posY + BlockInteractHelper.mc.player.getEyeHeight()), Math.sqrt((obf3.x - BlockInteractHelper.mc.player.posX) * (obf3.x - BlockInteractHelper.mc.player.posX) + (obf3.z - BlockInteractHelper.mc.player.posZ) * (obf3.z - BlockInteractHelper.mc.player.posZ))))), BlockInteractHelper.mc.player.onGround));
                BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractHelper.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                BlockInteractHelper.mc.playerController.processRightClickBlock(BlockInteractHelper.mc.player, BlockInteractHelper.mc.world, obf.offset(obf2), obf2.getOpposite(), new Vec3d((Vec3i)obf), EnumHand.MAIN_HAND);
                BlockInteractHelper.mc.player.swingArm(EnumHand.MAIN_HAND);
                BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractHelper.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(obf4[0], obf4[1], BlockInteractHelper.mc.player.onGround));
                return;
            }
        }
    }
    
    public static boolean isIntercepted(final BlockPos obf) {
        for (final Entity obf2 : BlockInteractHelper.mc.world.loadedEntityList) {
            if (new AxisAlignedBB(obf).intersects(obf2.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
    
    public static List<BlockPos> getSphere(final BlockPos obf, final float obf, final int obf, final boolean obf, final boolean obf, final int obf) {
        final ArrayList<BlockPos> obf2 = new ArrayList<BlockPos>();
        final int obf3 = obf.getX();
        final int obf4 = obf.getY();
        final int obf5 = obf.getZ();
        for (int obf6 = obf3 - (int)obf; obf6 <= obf3 + obf; ++obf6) {
            for (int obf7 = obf5 - (int)obf; obf7 <= obf5 + obf; ++obf7) {
                int obf8 = obf ? (obf4 - (int)obf) : obf4;
                while (true) {
                    final float obf9 = obf ? (obf4 + obf) : ((float)(obf4 + obf));
                    if (obf8 >= obf9) {
                        break;
                    }
                    final double obf10 = (obf3 - obf6) * (obf3 - obf6) + (obf5 - obf7) * (obf5 - obf7) + (obf ? ((obf4 - obf8) * (obf4 - obf8)) : 0);
                    if (obf10 < obf * obf && (!obf || obf10 >= (obf - Float.intBitsToFloat(Float.floatToIntBits(190.00047f) ^ 0x7CBE001F)) * (obf - Float.intBitsToFloat(Float.floatToIntBits(4.4369597f) ^ 0x7F0DFB93)))) {
                        final BlockPos obf11 = new BlockPos(obf6, obf8 + obf, obf7);
                        obf2.add(obf11);
                    }
                    ++obf8;
                }
            }
        }
        return obf2;
    }
    
    public static double[] directionSpeed(final double n) {
        float moveForward = BlockInteractHelper.mc.player.movementInput.moveForward;
        float moveStrafe = BlockInteractHelper.mc.player.movementInput.moveStrafe;
        float n2 = BlockInteractHelper.mc.player.prevRotationYaw + (BlockInteractHelper.mc.player.rotationYaw - BlockInteractHelper.mc.player.prevRotationYaw) * BlockInteractHelper.mc.getRenderPartialTicks();
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
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { BlockInteractHelper.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - BlockInteractHelper.mc.player.rotationYaw), BlockInteractHelper.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - BlockInteractHelper.mc.player.rotationPitch) };
    }
    
    public static void processRightClickBlock(final BlockPos obf, final EnumFacing obf, final Vec3d obf) {
        getPlayerController().processRightClickBlock(Wrapper.getPlayer(), BlockInteractHelper.mc.world, obf, obf, obf, EnumHand.MAIN_HAND);
    }
    
    public static boolean checkForNeighbours(final BlockPos blockPos) {
        if (!hasNeighbour(blockPos)) {
            final EnumFacing[] values = EnumFacing.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                if (hasNeighbour(blockPos.offset(values[i]))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public static void placeBlock(final BlockPos obf) {
        for (final EnumFacing obf2 : EnumFacing.values()) {
            if (!BlockInteractHelper.mc.world.getBlockState(obf.offset(obf2)).getBlock().equals(Blocks.AIR) && !isIntercepted(obf)) {
                final Vec3d obf3 = new Vec3d(obf.getX() + Double.longBitsToDouble(Double.doubleToLongBits(120.64842955948676) ^ 0x7FBE297FDEB1F057L) + obf2.getXOffset() * Double.longBitsToDouble(Double.doubleToLongBits(11.04980515376799) ^ 0x7FC619800FA53627L), obf.getY() + Double.longBitsToDouble(Double.doubleToLongBits(3.226866109639687) ^ 0x7FE9D09F2DCBC920L) + obf2.getYOffset() * Double.longBitsToDouble(Double.doubleToLongBits(12.119880287592016) ^ 0x7FC83D60F2F5491BL), obf.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(23.48843221391755) ^ 0x7FD77D09E4C116B9L) + obf2.getZOffset() * Double.longBitsToDouble(Double.doubleToLongBits(3.0441457690858007) ^ 0x7FE85A6918D3D79EL));
                final float[] obf4 = { BlockInteractHelper.mc.player.rotationYaw, BlockInteractHelper.mc.player.rotationPitch };
                BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(obf3.z - BlockInteractHelper.mc.player.posZ, obf3.x - BlockInteractHelper.mc.player.posX)) - Float.intBitsToFloat(Float.floatToIntBits(0.76552385f) ^ 0x7DF7F95F), (float)(-Math.toDegrees(Math.atan2(obf3.y - (BlockInteractHelper.mc.player.posY + BlockInteractHelper.mc.player.getEyeHeight()), Math.sqrt((obf3.x - BlockInteractHelper.mc.player.posX) * (obf3.x - BlockInteractHelper.mc.player.posX) + (obf3.z - BlockInteractHelper.mc.player.posZ) * (obf3.z - BlockInteractHelper.mc.player.posZ))))), BlockInteractHelper.mc.player.onGround));
                BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractHelper.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                BlockInteractHelper.mc.playerController.processRightClickBlock(BlockInteractHelper.mc.player, BlockInteractHelper.mc.world, obf.offset(obf2), obf2.getOpposite(), new Vec3d((Vec3i)obf), EnumHand.MAIN_HAND);
                BlockInteractHelper.mc.player.swingArm(EnumHand.MAIN_HAND);
                BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractHelper.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                BlockInteractHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(obf4[0], obf4[1], BlockInteractHelper.mc.player.onGround));
                return;
            }
        }
    }
    
    public static String get_valueX() {
        return BlockInteractHelper.set_valueX;
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static IBlockState getState(final BlockPos obf) {
        return BlockInteractHelper.mc.world.getBlockState(obf);
    }
    
    public static String get_valueZ() {
        return BlockInteractHelper.set_valueZ;
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(BlockInteractHelper.mc.player.posX, BlockInteractHelper.mc.player.posY + BlockInteractHelper.mc.player.getEyeHeight(), BlockInteractHelper.mc.player.posZ);
    }
    
    public static PlayerControllerMP getPlayerController() {
        return Minecraft.getMinecraft().playerController;
    }
    
    public static boolean hasNeighbour(final BlockPos blockPos) {
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (!BlockInteractHelper.mc.world.getBlockState(blockPos.offset(values[i])).getMaterial().isReplaceable()) {
                return true;
            }
        }
        return false;
    }
    
    public static String get_valueY() {
        return BlockInteractHelper.set_valueY;
    }
    
    static {
        BlockInteractHelper.blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE);
        BlockInteractHelper.shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
        BlockInteractHelper.mc = Minecraft.getMinecraft();
        BlockInteractHelper.X = new String(Base64.getDecoder().decode(Setting.X().getBytes()));
        BlockInteractHelper.Z = new String(Base64.getDecoder().decode(MathUtil.Z().getBytes()));
        BlockInteractHelper.Y = new String(Base64.getDecoder().decode(ConfigManager.Y().getBytes()));
        BlockInteractHelper.set_valueY = new String(Base64.getDecoder().decode(BlockInteractHelper.Y.getBytes()));
        BlockInteractHelper.set_valueX = new String(Base64.getDecoder().decode(BlockInteractHelper.X.getBytes()));
        BlockInteractHelper.set_valueZ = new String(Base64.getDecoder().decode(BlockInteractHelper.Z.getBytes()));
    }
    
    public enum ValidResult
    {
        Ok;
        
        public static ValidResult[] $VALUES;
        
        NoNeighbors, 
        NoEntityCollision, 
        AlreadyBlockThere;
        
        static {
            ValidResult.$VALUES = new ValidResult[] { ValidResult.NoEntityCollision, ValidResult.AlreadyBlockThere, ValidResult.NoNeighbors, ValidResult.Ok };
        }
    }
    
    public enum PlaceResult
    {
        Placed, 
        CantPlace, 
        NotReplaceable, 
        Neighbors;
        
        public static PlaceResult[] $VALUES;
        
        static {
            PlaceResult.$VALUES = new PlaceResult[] { PlaceResult.NotReplaceable, PlaceResult.Neighbors, PlaceResult.CantPlace, PlaceResult.Placed };
        }
    }
}
