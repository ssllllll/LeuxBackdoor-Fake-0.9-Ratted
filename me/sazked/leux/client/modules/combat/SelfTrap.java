// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import net.minecraft.util.math.Vec3d;
import me.sazked.leux.client.util.BlockUtil;
import me.sazked.leux.client.util.BlockInteractHelper;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.util.MathUtil;
import me.sazked.leux.client.modules.Category;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.util.math.BlockPos;
import me.sazked.leux.client.modules.Module;

public class SelfTrap extends Module
{
    public BlockPos trap_pos;
    public Setting rotate;
    public Setting swing;
    public Setting toggle;
    
    @Override
    public void enable() {
        if (this.find_in_hotbar() == -1) {
            this.set_disable();
        }
    }
    
    public boolean is_trapped() {
        if (this.trap_pos == null) {
            return false;
        }
        final IBlockState blockState = SelfTrap.mc.world.getBlockState(this.trap_pos);
        return blockState.getBlock() != Blocks.AIR && blockState.getBlock() != Blocks.WATER && blockState.getBlock() != Blocks.LAVA;
    }
    
    public int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = SelfTrap.mc.player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY && stackInSlot.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stackInSlot.getItem()).getBlock();
                if (block instanceof BlockEnderChest) {
                    return i;
                }
                if (block instanceof BlockObsidian) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public SelfTrap() {
        super(Category.combat);
        this.toggle = this.create("Toggle", "SelfTrapToggle", false);
        this.rotate = this.create("Rotate", "SelfTrapRotate", false);
        this.swing = this.create("Swing", "SelfTrapSwing", "Mainhand", this.combobox("Mainhand", "Offhand", "Both", "None"));
        this.name = "Self Trap";
        this.tag = "SelfTrap";
        this.description = "oh 'eck, ive trapped me sen again";
    }
    
    @Override
    public void update() {
        final Vec3d obf = MathUtil.interpolateEntity((Entity)SelfTrap.mc.player, SelfTrap.mc.getRenderPartialTicks());
        final int obf2 = SelfTrap.mc.player.inventory.currentItem;
        this.trap_pos = new BlockPos(obf.x, obf.y + Double.longBitsToDouble(Double.doubleToLongBits(0.8752577651021048) ^ 0x7FEC021C9295DFF9L), obf.z);
        if (this.is_trapped() && !this.toggle.get_value(true)) {
            this.toggle();
            return;
        }
        final BlockInteractHelper.ValidResult obf3 = BlockInteractHelper.valid(this.trap_pos);
        if (obf3 == BlockInteractHelper.ValidResult.AlreadyBlockThere && !SelfTrap.mc.world.getBlockState(this.trap_pos).getMaterial().isReplaceable()) {
            return;
        }
        if (obf3 == BlockInteractHelper.ValidResult.NoNeighbors) {
            final BlockPos[] array;
            final BlockPos[] obf4 = array = new BlockPos[] { this.trap_pos.north(), this.trap_pos.south(), this.trap_pos.east(), this.trap_pos.west(), this.trap_pos.up(), this.trap_pos.down().west() };
            for (final BlockPos obf5 : array) {
                final BlockInteractHelper.ValidResult obf6 = BlockInteractHelper.valid(obf5);
                if (obf6 != BlockInteractHelper.ValidResult.NoNeighbors) {
                    if (obf6 != BlockInteractHelper.ValidResult.NoEntityCollision) {
                        if (BlockUtil.placeBlock(obf5, this.find_in_hotbar(), this.rotate.get_value(true), this.rotate.get_value(true), this.swing)) {
                            return;
                        }
                    }
                }
            }
            return;
        }
        BlockUtil.placeBlock(this.trap_pos, this.find_in_hotbar(), this.rotate.get_value(true), this.rotate.get_value(true), this.swing);
        SelfTrap.mc.player.inventory.currentItem = obf2;
    }
}
