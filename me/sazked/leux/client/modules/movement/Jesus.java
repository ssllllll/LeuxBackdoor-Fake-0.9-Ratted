// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.client.util.Wrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.util.EntityUtil;
import me.sazked.leux.Leux;
import net.minecraft.util.math.AxisAlignedBB;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventCollisionBoxToList;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class Jesus extends Module
{
    @EventHandler
    public Listener<EventCollisionBoxToList> listener_collision;
    @EventHandler
    public Listener<EventPacket.SendPacket> listener;
    public static AxisAlignedBB WATER_WALK_AA;
    
    @Override
    public void update() {
        if (Leux.get_module_manager().get_module_with_tag("Freecam").is_disabled() && EntityUtil.isInWater((Entity)Jesus.mc.player)) {
            if (!Jesus.mc.player.isSneaking()) {
                Jesus.mc.player.motionY = Double.longBitsToDouble(Double.doubleToLongBits(5.993292714255266) ^ 0x7FAE60B82035CAFFL);
                if (Jesus.mc.player.getRidingEntity() != null && !(Jesus.mc.player.getRidingEntity() instanceof EntityBoat)) {
                    Jesus.mc.player.getRidingEntity().motionY = Double.longBitsToDouble(Double.doubleToLongBits(2.7210898424167738) ^ 0x7FD6F7F9F3667073L);
                }
            }
        }
    }
    
    public static void lambda$new$0(final EventCollisionBoxToList obf) {
        if (Jesus.mc.player != null) {
            if (obf.getBlock() instanceof BlockLiquid) {
                if ((EntityUtil.isDrivenByPlayer(obf.getEntity()) || obf.getEntity() == Jesus.mc.player) && !(obf.getEntity() instanceof EntityBoat) && !Jesus.mc.player.isSneaking() && Jesus.mc.player.fallDistance < Float.intBitsToFloat(Float.floatToIntBits(0.46026126f) ^ 0x7EABA75D) && !EntityUtil.isInWater((Entity)Jesus.mc.player) && (EntityUtil.isAboveWater((Entity)Jesus.mc.player, false) || EntityUtil.isAboveWater(Jesus.mc.player.getRidingEntity(), false)) && isAboveBlock((Entity)Jesus.mc.player, obf.getPos())) {
                    final AxisAlignedBB obf2 = Jesus.WATER_WALK_AA.offset(obf.getPos());
                    if (obf.getEntityBox().intersects(obf2)) {
                        obf.getCollidingBoxes().add(obf2);
                    }
                    obf.cancel();
                }
            }
        }
    }
    
    static {
        Jesus.WATER_WALK_AA = new AxisAlignedBB(Double.longBitsToDouble(Double.doubleToLongBits(1.734631006909476E308) ^ 0x7FEEE0A10349D3F2L), Double.longBitsToDouble(Double.doubleToLongBits(4.921802815499121E307) ^ 0x7FD185AEE0546939L), Double.longBitsToDouble(Double.doubleToLongBits(3.165058770125773E307) ^ 0x7FC6893519ACB38BL), Double.longBitsToDouble(Double.doubleToLongBits(110.14172137482545) ^ 0x7FAB8911F687813FL), Double.longBitsToDouble(Double.doubleToLongBits(3.3518587670820734) ^ 0x7FE57E8F2EAA2AF8L), Double.longBitsToDouble(Double.doubleToLongBits(4.8944876032877715) ^ 0x7FE393F48EEB3190L));
    }
    
    public static boolean isAboveLand(final Entity obf) {
        if (obf == null) {
            return false;
        }
        final double obf2 = obf.posY - Double.longBitsToDouble(Double.doubleToLongBits(253.9295665540144) ^ 0x7FEBC75E45F58A2BL);
        for (int obf3 = MathHelper.floor(obf.posX); obf3 < MathHelper.ceil(obf.posX); ++obf3) {
            for (int obf4 = MathHelper.floor(obf.posZ); obf4 < MathHelper.ceil(obf.posZ); ++obf4) {
                final BlockPos obf5 = new BlockPos(obf3, MathHelper.floor(obf2), obf4);
                if (Wrapper.getWorld().getBlockState(obf5).getBlock().isFullBlock(Wrapper.getWorld().getBlockState(obf5))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void lambda$new$1(final EventPacket.SendPacket sendPacket) {
        if (sendPacket.get_packet() instanceof CPacketPlayer && EntityUtil.isAboveWater((Entity)Jesus.mc.player, true) && !EntityUtil.isInWater((Entity)Jesus.mc.player) && !isAboveLand((Entity)Jesus.mc.player) && Jesus.mc.player.ticksExisted % 2 == 0) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)sendPacket.get_packet();
            cPacketPlayer.y += 0.02;
        }
    }
    
    public Jesus() {
        super(Category.movement);
        this.listener_collision = new Listener<EventCollisionBoxToList>(Jesus::lambda$new$0, (Predicate<EventCollisionBoxToList>[])new Predicate[0]);
        this.listener = new Listener<EventPacket.SendPacket>(Jesus::lambda$new$1, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Jesus";
        this.tag = "Jesus";
        this.description = "walk on da water";
    }
    
    public static boolean isAboveBlock(final Entity obf, final BlockPos obf) {
        return obf.posY >= obf.getY();
    }
}
