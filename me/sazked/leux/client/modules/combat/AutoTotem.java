// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import me.sazked.leux.client.modules.Category;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoTotem extends Module
{
    public Setting delay;
    public boolean switching;
    public int last_slot;
    
    public void swap_items(final int last_slot, final int n) {
        if (last_slot == -1) {
            return;
        }
        if (n == 0) {
            AutoTotem.mc.playerController.windowClick(0, last_slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, last_slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
        }
        if (n == 1) {
            AutoTotem.mc.playerController.windowClick(0, last_slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            this.switching = true;
            this.last_slot = last_slot;
        }
        if (n == 2) {
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, last_slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            this.switching = false;
        }
        AutoTotem.mc.playerController.updateController();
    }
    
    public AutoTotem() {
        super(Category.combat);
        this.delay = this.create("Delay", "TotemDelay", false);
        this.switching = false;
        this.name = "Auto Totem";
        this.tag = "AutoTotem";
        this.description = "put totem in offhand";
    }
    
    public int get_item_slot() {
        if (Items.TOTEM_OF_UNDYING == AutoTotem.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        int i = 36;
        while (i >= 0) {
            if (AutoTotem.mc.player.inventory.getStackInSlot(i).getItem() == Items.TOTEM_OF_UNDYING) {
                if (i < 9) {
                    return -1;
                }
                return i;
            }
            else {
                --i;
            }
        }
        return -1;
    }
    
    @Override
    public void update() {
        if (AutoTotem.mc.currentScreen == null || AutoTotem.mc.currentScreen instanceof GuiInventory) {
            if (this.switching) {
                this.swap_items(this.last_slot, 2);
                return;
            }
            if (AutoTotem.mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
                this.swap_items(this.get_item_slot(), this.delay.get_value(true) ? 1 : 0);
            }
        }
    }
}
