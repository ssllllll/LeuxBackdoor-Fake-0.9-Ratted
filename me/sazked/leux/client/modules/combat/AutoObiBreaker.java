// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import net.minecraft.item.ItemPickaxe;
import me.sazked.leux.Leux;
import me.sazked.leux.client.util.FriendUtil;
import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import me.sazked.leux.client.util.BreakUtil;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.List;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.util.math.BlockPos;
import me.sazked.leux.client.modules.Module;

public class AutoObiBreaker extends Module
{
    public BlockPos target_block;
    public Setting swap;
    public Setting range;
    
    @Override
    public void enable() {
        for (final EntityPlayer obf : (List)AutoObiBreaker.mc.world.playerEntities.stream().filter(AutoObiBreaker::lambda$enable$0).collect(Collectors.toList())) {
            if (AutoObiBreaker.mc.player.getDistance((Entity)obf) <= this.range.get_value(1)) {
                if (AutoObiBreaker.mc.player == obf) {
                    continue;
                }
                final BlockPos obf2 = EntityUtil.is_cityable(obf, true);
                if (obf2 == null) {
                    continue;
                }
                this.target_block = obf2;
            }
        }
        if (this.target_block == null) {
            MessageUtil.send_client_message("Can't find block");
            this.set_disable();
        }
        final int obf3 = this.findPickaxe();
        if (this.swap.get_value(true) && obf3 != -1) {
            AutoObiBreaker.mc.player.inventory.currentItem = obf3;
        }
        BreakUtil.setblock(this.target_block);
    }
    
    public AutoObiBreaker() {
        super(Category.combat);
        this.range = this.create("Range", "MineRange", 5, 1, 6);
        this.swap = this.create("Swap to Pick", "MineSwap", true);
        this.target_block = null;
        this.name = "AutoObiBreaker";
        this.tag = "AutoObiBreaker";
        this.description = "sex";
    }
    
    public static boolean lambda$enable$0(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    @Override
    public void disable() {
        BreakUtil.setblock(null);
        this.target_block = null;
    }
    
    @Override
    public void update() {
        BreakUtil.breakblock(this.range.get_value(1));
        if (Leux.get_module_manager().get_module_with_tag("PacketMine").is_active()) {
            this.set_disable();
        }
    }
    
    public int findPickaxe() {
        for (int i = 0; i < 9; ++i) {
            if (AutoObiBreaker.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemPickaxe) {
                return i;
            }
        }
        return -1;
    }
}
