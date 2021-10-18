// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import net.minecraft.init.Blocks;
import me.sazked.leux.client.util.PlayerUtil;
import me.sazked.leux.client.modules.Category;
import net.minecraft.block.Block;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import java.util.Iterator;
import me.sazked.leux.client.util.BlockInteractHelper;
import java.util.Collection;
import me.sazked.leux.client.util.MessageUtil;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Webfill extends Module
{
    public boolean sneak;
    public Setting web_rotate;
    public Setting web_range;
    public ArrayList<BlockPos> holes;
    public Setting web_toggle;
    
    @Override
    public void update() {
        final int obf = Webfill.mc.player.inventory.currentItem;
        if (this.holes.isEmpty()) {
            if (!this.web_toggle.get_value(true)) {
                this.set_disable();
                MessageUtil.toggle_message(this);
                return;
            }
            this.find_new_holes();
        }
        BlockPos obf2 = null;
        for (final BlockPos obf3 : new ArrayList<BlockPos>(this.holes)) {
            if (obf3 == null) {
                continue;
            }
            final BlockInteractHelper.ValidResult obf4 = BlockInteractHelper.valid(obf3);
            if (obf4 == BlockInteractHelper.ValidResult.Ok) {
                obf2 = obf3;
                break;
            }
            this.holes.remove(obf3);
        }
        final int obf5 = this.find_in_hotbar();
        if (obf2 != null) {
            if (obf5 != -1) {
                Webfill.mc.player.inventory.currentItem = obf5;
                Webfill.mc.playerController.updateController();
                if (this.place_blocks(obf2)) {
                    this.holes.remove(obf2);
                }
                Webfill.mc.player.inventory.currentItem = obf;
            }
        }
    }
    
    public int find_in_hotbar() {
        for (int obf = 0; obf < 9; ++obf) {
            final ItemStack obf2 = Webfill.mc.player.inventory.getStackInSlot(obf);
            if (obf2.getItem() == Item.getItemById(30)) {
                return obf;
            }
        }
        return -1;
    }
    
    public boolean place_blocks(final BlockPos obf) {
        if (!Webfill.mc.world.getBlockState(obf).getMaterial().isReplaceable()) {
            return false;
        }
        if (!BlockInteractHelper.checkForNeighbours(obf)) {
            return false;
        }
        for (final EnumFacing obf2 : EnumFacing.values()) {
            final BlockPos obf3 = obf.offset(obf2);
            final EnumFacing obf4 = obf2.getOpposite();
            if (BlockInteractHelper.canBeClicked(obf3)) {
                final Block obf5;
                if (BlockInteractHelper.blackList.contains(obf5 = Webfill.mc.world.getBlockState(obf3).getBlock())) {
                    Webfill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Webfill.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    this.sneak = true;
                }
                final Vec3d obf6 = new Vec3d((Vec3i)obf3).add(Double.longBitsToDouble(Double.doubleToLongBits(17.058496746863415) ^ 0x7FD10EF9A48EB365L), Double.longBitsToDouble(Double.doubleToLongBits(10.539620599160193) ^ 0x7FC5144926B3482BL), Double.longBitsToDouble(Double.doubleToLongBits(2.407557449728372) ^ 0x7FE342AD7AEE989FL)).add(new Vec3d(obf4.getDirectionVec()).scale(Double.longBitsToDouble(Double.doubleToLongBits(3.3905754928710223) ^ 0x7FEB1FE60B440040L)));
                if (this.web_rotate.get_value(true)) {
                    BlockInteractHelper.faceVectorPacketInstant(obf6);
                }
                Webfill.mc.playerController.processRightClickBlock(Webfill.mc.player, Webfill.mc.world, obf3, obf4, obf6, EnumHand.MAIN_HAND);
                Webfill.mc.player.swingArm(EnumHand.MAIN_HAND);
                return true;
            }
        }
        return false;
    }
    
    public Webfill() {
        super(Category.combat);
        this.web_toggle = this.create("Toggle", "WebFillToggle", true);
        this.web_rotate = this.create("Rotate", "WebFillRotate", true);
        this.web_range = this.create("Range", "WebFillRange", 5, 1, 6);
        this.holes = new ArrayList<BlockPos>();
        this.name = "Web Fill";
        this.tag = "WebFill";
        this.description = "its like hole fill, but more annoying";
    }
    
    @Override
    public void disable() {
        this.holes.clear();
    }
    
    @Override
    public void enable() {
        this.find_new_holes();
    }
    
    public void find_new_holes() {
        this.holes.clear();
        for (final BlockPos obf : BlockInteractHelper.getSphere(PlayerUtil.GetLocalPlayerPosFloored(), (float)this.web_range.get_value(1), this.web_range.get_value(1), false, true, 0)) {
            if (!Webfill.mc.world.getBlockState(obf).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!Webfill.mc.world.getBlockState(obf.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!Webfill.mc.world.getBlockState(obf.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            boolean obf2 = true;
            for (final BlockPos obf3 : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
                final Block obf4 = Webfill.mc.world.getBlockState(obf.add((Vec3i)obf3)).getBlock();
                if (obf4 != Blocks.BEDROCK && obf4 != Blocks.OBSIDIAN && obf4 != Blocks.ENDER_CHEST && obf4 != Blocks.ANVIL) {
                    obf2 = false;
                    break;
                }
            }
            if (!obf2) {
                continue;
            }
            this.holes.add(obf);
        }
    }
}
