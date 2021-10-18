// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import me.sazked.leux.client.util.PlayerUtil;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import me.sazked.leux.client.util.EntityUtil;
import me.sazked.leux.client.util.FriendUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import me.sazked.leux.client.util.BlockInteractHelper;
import net.minecraft.util.math.BlockPos;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoWeb extends Module
{
    public Setting always_on;
    public Setting range;
    public boolean sneak;
    public Setting rotate;
    public int new_slot;
    
    public void place_blocks(final BlockPos obf) {
        if (!AutoWeb.mc.world.getBlockState(obf).getMaterial().isReplaceable()) {
            return;
        }
        if (!BlockInteractHelper.checkForNeighbours(obf)) {
            return;
        }
        for (final EnumFacing obf2 : EnumFacing.values()) {
            final BlockPos obf3 = obf.offset(obf2);
            final EnumFacing obf4 = obf2.getOpposite();
            if (BlockInteractHelper.canBeClicked(obf3)) {
                if (BlockInteractHelper.blackList.contains(AutoWeb.mc.world.getBlockState(obf3).getBlock())) {
                    AutoWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoWeb.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    this.sneak = true;
                }
                final Vec3d obf5 = new Vec3d((Vec3i)obf3).add(Double.longBitsToDouble(Double.doubleToLongBits(2.2745414125596204) ^ 0x7FE23242C4A2BACEL), Double.longBitsToDouble(Double.doubleToLongBits(3.6194956093826702) ^ 0x7FECF4BA1D328366L), Double.longBitsToDouble(Double.doubleToLongBits(20.36404337323948) ^ 0x7FD45D31F24E639BL)).add(new Vec3d(obf4.getDirectionVec()).scale(Double.longBitsToDouble(Double.doubleToLongBits(2.8345321927124267) ^ 0x7FE6AD1F36D945B8L)));
                if (this.rotate.get_value(true)) {
                    BlockInteractHelper.faceVectorPacketInstant(obf5);
                }
                AutoWeb.mc.playerController.processRightClickBlock(AutoWeb.mc.player, AutoWeb.mc.world, obf3, obf4, obf5, EnumHand.MAIN_HAND);
                AutoWeb.mc.player.swingArm(EnumHand.MAIN_HAND);
                return;
            }
        }
    }
    
    public EntityPlayer find_closest_target() {
        if (AutoWeb.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        Object o = null;
        for (final EntityPlayer obf : AutoWeb.mc.world.playerEntities) {
            if (obf == AutoWeb.mc.player) {
                continue;
            }
            if (FriendUtil.isFriend(obf.getName())) {
                continue;
            }
            if (!EntityUtil.isLiving((Entity)obf)) {
                continue;
            }
            if (obf.getHealth() <= 0.0f) {
                continue;
            }
            if (o != null && AutoWeb.mc.player.getDistance((Entity)obf) > AutoWeb.mc.player.getDistance((Entity)o)) {
                continue;
            }
            o = obf;
        }
        return (EntityPlayer)o;
    }
    
    public AutoWeb() {
        super(Category.combat);
        this.always_on = this.create("Always On", "AlwaysOn", true);
        this.rotate = this.create("Rotate", "AutoWebRotate", true);
        this.range = this.create("Enemy Range", "AutoWebRange", 4, 0, 8);
        this.new_slot = -1;
        this.sneak = false;
        this.name = "Self Web";
        this.tag = "SelfWeb";
        this.description = "places fuckin webs at ur feet";
    }
    
    @Override
    public void enable() {
        if (AutoWeb.mc.player != null) {
            this.new_slot = this.find_in_hotbar();
            if (this.new_slot == -1) {
                MessageUtil.send_client_error_message("cannot find webs in hotbar");
                this.set_active(false);
            }
        }
    }
    
    @Override
    public void update() {
        final int obf = AutoWeb.mc.player.inventory.currentItem;
        if (AutoWeb.mc.player == null) {
            return;
        }
        if (this.always_on.get_value(true)) {
            final EntityPlayer obf2 = this.find_closest_target();
            if (obf2 == null) {
                return;
            }
            if (AutoWeb.mc.player.getDistance((Entity)obf2) < this.range.get_value(1) && this.is_surround()) {
                final int obf3 = AutoWeb.mc.player.inventory.currentItem;
                AutoWeb.mc.player.inventory.currentItem = this.new_slot;
                AutoWeb.mc.playerController.updateController();
                this.place_blocks(PlayerUtil.GetLocalPlayerPosFloored());
                AutoWeb.mc.player.inventory.currentItem = obf3;
            }
        }
        else {
            final int obf4 = AutoWeb.mc.player.inventory.currentItem;
            AutoWeb.mc.player.inventory.currentItem = this.new_slot;
            AutoWeb.mc.playerController.updateController();
            this.place_blocks(PlayerUtil.GetLocalPlayerPosFloored());
            AutoWeb.mc.player.inventory.currentItem = obf4;
            this.set_disable();
        }
        AutoWeb.mc.player.inventory.currentItem = obf;
    }
    
    public int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            if (AutoWeb.mc.player.inventory.getStackInSlot(i).getItem() == Item.getItemById(30)) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean is_surround() {
        final BlockPos getLocalPlayerPosFloored = PlayerUtil.GetLocalPlayerPosFloored();
        if (AutoWeb.mc.world.getBlockState(getLocalPlayerPosFloored.east()).getBlock() != Blocks.AIR) {
            if (AutoWeb.mc.world.getBlockState(getLocalPlayerPosFloored.west()).getBlock() != Blocks.AIR && AutoWeb.mc.world.getBlockState(getLocalPlayerPosFloored.north()).getBlock() != Blocks.AIR && AutoWeb.mc.world.getBlockState(getLocalPlayerPosFloored.south()).getBlock() != Blocks.AIR && AutoWeb.mc.world.getBlockState(getLocalPlayerPosFloored).getBlock() == Blocks.AIR) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void disable() {
        if (AutoWeb.mc.player != null && this.sneak) {
            AutoWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoWeb.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneak = false;
        }
    }
}
