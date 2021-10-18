// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import me.sazked.leux.client.util.BlockUtil;
import java.util.Collection;
import me.sazked.leux.client.util.MessageUtil;
import java.util.Iterator;
import net.minecraft.util.math.Vec3i;
import net.minecraft.init.Blocks;
import me.sazked.leux.client.util.BlockInteractHelper;
import me.sazked.leux.client.util.PlayerUtil;
import me.sazked.leux.client.modules.Category;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class HoleFill extends Module
{
    public Setting hole_toggle;
    public Setting swing;
    public Setting hole_rotate;
    public ArrayList<BlockPos> holes;
    public Setting hole_range;
    
    public int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = HoleFill.mc.player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY) {
                if (stackInSlot.getItem() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stackInSlot.getItem()).getBlock();
                    if (block instanceof BlockEnderChest) {
                        return i;
                    }
                    if (block instanceof BlockObsidian) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public HoleFill() {
        super(Category.combat);
        this.hole_toggle = this.create("Toggle", "HoleFillToggle", false);
        this.hole_rotate = this.create("Rotate", "HoleFillRotate", true);
        this.hole_range = this.create("Range", "HoleFillRange", 5, 1, 6);
        this.swing = this.create("Swing", "HoleFillSwing", "Mainhand", this.combobox("Mainhand", "Offhand", "Both", "None"));
        this.holes = new ArrayList<BlockPos>();
        this.name = "Hole Fill";
        this.tag = "HoleFill";
        this.description = "Turn holes into floors";
    }
    
    public void find_new_holes() {
        this.holes.clear();
        for (final BlockPos obf : BlockInteractHelper.getSphere(PlayerUtil.GetLocalPlayerPosFloored(), (float)this.hole_range.get_value(1), this.hole_range.get_value(1), false, true, 0)) {
            if (!HoleFill.mc.world.getBlockState(obf).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleFill.mc.world.getBlockState(obf.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleFill.mc.world.getBlockState(obf.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            boolean obf2 = true;
            for (final BlockPos obf3 : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
                final Block obf4 = HoleFill.mc.world.getBlockState(obf.add((Vec3i)obf3)).getBlock();
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
    
    @Override
    public void update() {
        final int obf = HoleFill.mc.player.inventory.currentItem;
        if (this.find_in_hotbar() == -1) {
            this.disable();
            return;
        }
        if (this.holes.isEmpty()) {
            if (!this.hole_toggle.get_value(true)) {
                this.set_disable();
                MessageUtil.toggle_message(this);
                return;
            }
            this.find_new_holes();
        }
        BlockPos obf2 = null;
        while (true) {
            for (final BlockPos obf3 : new ArrayList<BlockPos>(this.holes)) {
                if (obf3 == null) {
                    continue;
                }
                final BlockInteractHelper.ValidResult obf4 = BlockInteractHelper.valid(obf3);
                if (obf4 != BlockInteractHelper.ValidResult.Ok) {
                    this.holes.remove(obf3);
                }
                else {
                    obf2 = obf3;
                    if (this.find_in_hotbar() == -1) {
                        this.disable();
                        return;
                    }
                    if (obf2 != null && BlockUtil.placeBlock(obf2, this.find_in_hotbar(), this.hole_rotate.get_value(true), this.hole_rotate.get_value(true), this.swing)) {
                        this.holes.remove(obf2);
                    }
                    HoleFill.mc.player.inventory.currentItem = obf;
                    return;
                }
            }
            continue;
        }
    }
    
    @Override
    public void disable() {
        this.holes.clear();
    }
    
    @Override
    public void enable() {
        if (this.find_in_hotbar() == -1) {
            this.set_disable();
        }
        this.find_new_holes();
    }
}
