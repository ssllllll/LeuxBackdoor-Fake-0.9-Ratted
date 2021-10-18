// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import me.sazked.leux.client.modules.Category;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import me.sazked.leux.client.util.BlockInteractHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.util.Wrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.util.EntityUtil;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Scaffold extends Module
{
    public Setting tower;
    public Setting future;
    
    @Override
    public void update() {
        if (Scaffold.mc.player == null) {
            return;
        }
        final Vec3d obf = EntityUtil.getInterpolatedPos((Entity)Scaffold.mc.player, (float)this.future.get_value(0));
        final BlockPos obf2 = new BlockPos(obf).down();
        final BlockPos obf3 = obf2.down();
        if (!Wrapper.getWorld().getBlockState(obf2).getMaterial().isReplaceable()) {
            return;
        }
        int obf4 = -1;
        for (int obf5 = 0; obf5 < 9; ++obf5) {
            final ItemStack obf6 = Wrapper.getPlayer().inventory.getStackInSlot(obf5);
            if (obf6 != ItemStack.EMPTY) {
                if (obf6.getItem() instanceof ItemBlock) {
                    final Block obf7;
                    if (!BlockInteractHelper.blackList.contains(obf7 = ((ItemBlock)obf6.getItem()).getBlock())) {
                        if (!(obf7 instanceof BlockContainer) && Block.getBlockFromItem(obf6.getItem()).getDefaultState().isFullBlock()) {
                            if (!(((ItemBlock)obf6.getItem()).getBlock() instanceof BlockFalling) || !Wrapper.getWorld().getBlockState(obf3).getMaterial().isReplaceable()) {
                                obf4 = obf5;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (obf4 == -1) {
            return;
        }
        int obf5 = Wrapper.getPlayer().inventory.currentItem;
        Wrapper.getPlayer().inventory.currentItem = obf4;
        if (!BlockInteractHelper.checkForNeighbours(obf2)) {
            return;
        }
        BlockInteractHelper.placeBlockScaffold(obf2);
        Wrapper.getPlayer().inventory.currentItem = obf5;
    }
    
    public Scaffold() {
        super(Category.movement);
        this.future = this.create("Ticks", "Ticks", 0, 0, 60);
        this.tower = this.create("Tower", "Tower", true);
        this.name = "Scaffold";
        this.tag = "Scaffold";
        this.description = "Andaime";
    }
}
