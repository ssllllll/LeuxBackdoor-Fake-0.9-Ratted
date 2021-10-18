// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.modules.Category;
import net.minecraft.init.Items;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoWither extends Module
{
    public int head_slot;
    public Setting range;
    public int sand_slot;
    
    public boolean has_blocks() {
        int n = 0;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = AutoWither.mc.player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY && stackInSlot.getItem() instanceof ItemBlock && ((ItemBlock)stackInSlot.getItem()).getBlock() instanceof BlockSoulSand) {
                this.sand_slot = i;
                ++n;
                break;
            }
        }
        for (int j = 0; j < 9; ++j) {
            final ItemStack stackInSlot2 = AutoWither.mc.player.inventory.getStackInSlot(j);
            if (stackInSlot2.getItem() == Items.SKULL && stackInSlot2.getItemDamage() == 1) {
                this.head_slot = j;
                ++n;
                return n == 2;
            }
        }
        return n == 2;
    }
    
    public AutoWither() {
        super(Category.misc);
        this.range = this.create("Range", "WitherRange", 5, 0, 6);
        this.name = "Auto Wither";
        this.tag = "AutoWither";
        this.description = "makes withers";
    }
    
    @Override
    public void enable() {
        if (this.has_blocks()) {
            MessageUtil.send_client_message("This module isn't finished");
        }
        else {
            MessageUtil.send_client_message("You don't have materials");
        }
    }
}
