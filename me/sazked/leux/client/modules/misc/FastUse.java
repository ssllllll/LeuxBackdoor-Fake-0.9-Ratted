// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class FastUse extends Module
{
    public Setting crystal;
    public Setting exp;
    public Setting fast_place;
    public Setting bow;
    public Setting everything;
    public Setting fast_break;
    
    public FastUse() {
        super(Category.misc);
        this.everything = this.create("Everything", "FastEverything", false);
        this.fast_place = this.create("Place", "FastPlace", false);
        this.fast_break = this.create("Break", "FastBreak", false);
        this.crystal = this.create("Crystal", "FastCrystal", false);
        this.exp = this.create("EXP", "FastExp", true);
        this.bow = this.create("Bow", "FastBow", true);
        this.name = "FastUse";
        this.tag = "FastUse";
        this.description = "use things faster";
    }
    
    @Override
    public void update() {
        final Item obf = FastUse.mc.player.getHeldItemMainhand().getItem();
        final Item obf2 = FastUse.mc.player.getHeldItemOffhand().getItem();
        final boolean obf3 = obf instanceof ItemExpBottle;
        final boolean obf4 = obf2 instanceof ItemExpBottle;
        final boolean obf5 = obf instanceof ItemEndCrystal;
        final boolean obf6 = obf2 instanceof ItemEndCrystal;
        final boolean obf7 = obf instanceof ItemBow;
        final boolean obf8 = obf2 instanceof ItemBow;
        if (this.everything.get_value(true)) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (obf3 | obf4) {
            if (this.exp.get_value(true)) {
                FastUse.mc.rightClickDelayTimer = 0;
            }
        }
        if ((obf7 | obf8) && this.bow.get_value(true) && FastUse.mc.player.isHandActive() && FastUse.mc.player.getItemInUseMaxCount() >= 3) {
            FastUse.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, FastUse.mc.player.getHorizontalFacing()));
            FastUse.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(FastUse.mc.player.getActiveHand()));
            FastUse.mc.player.stopActiveHand();
        }
        if ((obf5 | obf6) && this.crystal.get_value(true)) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (!(obf3 | obf4 | obf5 | obf6)) {
            if (this.fast_place.get_value(true)) {
                FastUse.mc.rightClickDelayTimer = 0;
            }
        }
        if (this.fast_break.get_value(true)) {
            FastUse.mc.playerController.blockHitDelay = 0;
        }
    }
}
