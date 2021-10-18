// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.util.PlayerUtil;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class SurroundBlocks extends Pinnable
{
    public SurroundBlocks() {
        super("Surround Blocks", "SurroundBlocks", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        Block west = this.get_neg_x();
        Block east = this.get_pos_x();
        Block north = this.get_neg_z();
        Block south = this.get_pos_z();
        switch (PlayerUtil.GetFacing()) {
            case North: {
                west = this.get_neg_x();
                east = this.get_pos_x();
                north = this.get_neg_z();
                south = this.get_pos_z();
                break;
            }
            case East: {
                west = this.get_neg_z();
                east = this.get_pos_z();
                north = this.get_pos_x();
                south = this.get_neg_x();
                break;
            }
            case South: {
                west = this.get_pos_x();
                east = this.get_neg_x();
                north = this.get_pos_z();
                south = this.get_neg_z();
                break;
            }
            case West: {
                west = this.get_pos_z();
                east = this.get_neg_z();
                north = this.get_neg_x();
                south = this.get_pos_x();
                break;
            }
        }
        this.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(west), this.get_x() - 20, this.get_y());
        this.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(east), this.get_x() + 20, this.get_y());
        this.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(north), this.get_x(), this.get_y() - 20);
        this.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(south), this.get_x(), this.get_y() + 20);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
        this.set_width(50);
        this.set_height(25);
    }
    
    public Block get_neg_x() {
        final BlockPos player_block = PlayerUtil.GetLocalPlayerPosFloored();
        final Block b = this.mc.world.getBlockState(player_block.west()).getBlock();
        if (b != null) {
            return b;
        }
        return null;
    }
    
    public Block get_pos_x() {
        final BlockPos player_block = PlayerUtil.GetLocalPlayerPosFloored();
        final Block b = this.mc.world.getBlockState(player_block.east()).getBlock();
        if (b != null) {
            return b;
        }
        return null;
    }
    
    public Block get_pos_z() {
        final BlockPos player_block = PlayerUtil.GetLocalPlayerPosFloored();
        final Block b = this.mc.world.getBlockState(player_block.south()).getBlock();
        if (b != null) {
            return b;
        }
        return null;
    }
    
    public Block get_neg_z() {
        final BlockPos player_block = PlayerUtil.GetLocalPlayerPosFloored();
        final Block b = this.mc.world.getBlockState(player_block.north()).getBlock();
        if (b != null) {
            return b;
        }
        return null;
    }
}
