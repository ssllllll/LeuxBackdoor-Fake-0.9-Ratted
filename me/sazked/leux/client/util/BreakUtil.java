// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import me.sazked.leux.Leux;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;

public class BreakUtil
{
    public static Minecraft mc;
    public static BlockPos current_block;
    public static boolean is_mining;
    
    public static void setblock(final BlockPos current_block) {
        BreakUtil.current_block = current_block;
    }
    
    public static boolean breakblock(final int obf) {
        if (BreakUtil.current_block == null) {
            return false;
        }
        final IBlockState obf2 = BreakUtil.mc.world.getBlockState(BreakUtil.current_block);
        if (finished(obf2) || BreakUtil.mc.player.getDistanceSq(BreakUtil.current_block) > obf * obf) {
            Leux.get_module_manager().get_module_with_tag("AutoObiBreaker").set_disable();
            BreakUtil.current_block = null;
            return false;
        }
        BreakUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        final EnumFacing obf3 = EnumFacing.UP;
        if (!BreakUtil.is_mining) {
            BreakUtil.is_mining = true;
            BreakUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, BreakUtil.current_block, obf3));
        }
        else {
            BreakUtil.mc.playerController.onPlayerDamageBlock(BreakUtil.current_block, obf3);
        }
        return true;
    }
    
    public static boolean finished(final IBlockState obf) {
        return obf.getBlock() == Blocks.BEDROCK || obf.getBlock() == Blocks.AIR || obf.getBlock() instanceof BlockLiquid;
    }
    
    static {
        BreakUtil.mc = Minecraft.getMinecraft();
        BreakUtil.current_block = null;
        BreakUtil.is_mining = false;
    }
}
