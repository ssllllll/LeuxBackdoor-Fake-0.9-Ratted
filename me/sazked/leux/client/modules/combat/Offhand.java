// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import net.minecraft.item.ItemSword;
import net.minecraft.client.gui.inventory.GuiInventory;
import me.sazked.leux.client.modules.Category;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Offhand extends Module
{
    public Setting sword_gap;
    public Setting totem_switch;
    public boolean switching;
    public int last_slot;
    public Setting switch_mode;
    public Setting delay;
    
    public int get_item_slot(final Item item) {
        if (item == Offhand.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        for (int i = 36; i >= 0; --i) {
            if (Offhand.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                if (i < 9) {
                    if (item == Items.GOLDEN_APPLE) {
                        return -1;
                    }
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }
    
    public void swap_items(final int obf, final int obf) {
        if (obf == -1) {
            return;
        }
        if (obf == 0) {
            Offhand.mc.playerController.windowClick(0, obf, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(0, obf, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
        }
        if (obf == 1) {
            Offhand.mc.playerController.windowClick(0, obf, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            this.switching = true;
            this.last_slot = obf;
        }
        if (obf == 2) {
            Offhand.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(0, obf, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            this.switching = false;
        }
        Offhand.mc.playerController.updateController();
    }
    
    public Offhand() {
        super(Category.combat);
        this.switch_mode = this.create("Offhand", "OffhandOffhand", "Totem", this.combobox("Totem", "Crystal", "Gapple"));
        this.totem_switch = this.create("Totem HP", "OffhandTotemHP", 13, 0, 36);
        this.sword_gap = this.create("Sword Gap", "OffhandSwordGap", false);
        this.delay = this.create("Delay", "OffhandDelay", false);
        this.switching = false;
        this.name = "Offhand";
        this.tag = "Offhand";
        this.description = "Switches shit to ur offhand";
    }
    
    @Override
    public void update() {
        if (Offhand.mc.currentScreen != null) {
            if (!(Offhand.mc.currentScreen instanceof GuiInventory)) {
                return;
            }
        }
        if (this.switching) {
            this.swap_items(this.last_slot, 2);
            return;
        }
        final float obf = Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount();
        if (obf <= this.totem_switch.get_value(1)) {
            this.swap_items(this.get_item_slot(Items.TOTEM_OF_UNDYING), this.delay.get_value(true) ? 1 : 0);
            return;
        }
        if (this.switch_mode.in("Crystal") && !(Offhand.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword)) {
            this.swap_items(this.get_item_slot(Items.END_CRYSTAL), 0);
            return;
        }
        if (this.sword_gap.get_value(true) && Offhand.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) {
            this.swap_items(this.get_item_slot(Items.GOLDEN_APPLE), this.delay.get_value(true) ? 1 : 0);
            return;
        }
        if (this.switch_mode.in("Totem")) {
            this.swap_items(this.get_item_slot(Items.TOTEM_OF_UNDYING), this.delay.get_value(true) ? 1 : 0);
            return;
        }
        if (this.switch_mode.in("Gapple")) {
            this.swap_items(this.get_item_slot(Items.GOLDEN_APPLE), this.delay.get_value(true) ? 1 : 0);
            return;
        }
        if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
            this.swap_items(this.get_item_slot(Items.TOTEM_OF_UNDYING), this.delay.get_value(true) ? 1 : 0);
        }
    }
    
    @Override
    public String array_detail() {
        return this.switch_mode.get_current_value() + ", " + this.totem_switch.get_value(1);
    }
}
