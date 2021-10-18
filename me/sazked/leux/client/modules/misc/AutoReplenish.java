// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import java.util.HashMap;
import me.sazked.leux.client.util.Pair;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.inventory.GuiContainer;
import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import net.minecraft.init.Items;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoReplenish extends Module
{
    public Setting mode;
    public Setting threshold;
    public int delay_step;
    public Setting tickdelay;
    
    public boolean isCompatibleStacks(final ItemStack obf, final ItemStack obf) {
        if (!obf.getItem().equals(obf.getItem())) {
            return false;
        }
        if (obf.getItem() instanceof ItemBlock && obf.getItem() instanceof ItemBlock) {
            final Block obf2 = ((ItemBlock)obf.getItem()).getBlock();
            final Block obf3 = ((ItemBlock)obf.getItem()).getBlock();
            if (!obf2.material.equals(obf3.material)) {
                return false;
            }
        }
        return obf.getDisplayName().equals(obf.getDisplayName()) && obf.getItemDamage() == obf.getItemDamage();
    }
    
    public int findCompatibleInventorySlot(final ItemStack obf) {
        int obf2 = -1;
        int obf3 = 999;
        for (final Map.Entry<Integer, ItemStack> obf4 : this.get_inventory().entrySet()) {
            final ItemStack obf5 = obf4.getValue();
            if (!obf5.isEmpty) {
                if (obf5.getItem() == Items.AIR) {
                    continue;
                }
                if (!this.isCompatibleStacks(obf, obf5)) {
                    continue;
                }
                final int obf6 = ((ItemStack)AutoReplenish.mc.player.inventoryContainer.getInventory().get((int)obf4.getKey())).stackSize;
                if (obf3 <= obf6) {
                    continue;
                }
                obf3 = obf6;
                obf2 = obf4.getKey();
            }
        }
        return obf2;
    }
    
    public AutoReplenish() {
        super(Category.misc);
        this.mode = this.create("Mode", "AutoReplenishMode", "Xp", this.combobox("All", "Crystals", "Xp", "Both"));
        this.threshold = this.create("Threshold", "AutoReplenishThreshold", 32, 1, 63);
        this.tickdelay = this.create("Delay", "AutoReplenishDelay", 2, 1, 10);
        this.delay_step = 0;
        this.name = "Hotbar Replenish";
        this.tag = "HotbarReplenish";
        this.description = "chad this doesnt desync you i swear";
    }
    
    @Override
    public void update() {
        if (AutoReplenish.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (this.delay_step < this.tickdelay.get_value(1)) {
            ++this.delay_step;
            return;
        }
        this.delay_step = 0;
        final Pair<Integer, Integer> obf = this.findReplenishableHotbarSlot();
        if (obf == null) {
            return;
        }
        final int obf2 = obf.getKey();
        final int obf3 = obf.getValue();
        AutoReplenish.mc.playerController.windowClick(0, obf2, 0, ClickType.PICKUP, (EntityPlayer)AutoReplenish.mc.player);
        AutoReplenish.mc.playerController.windowClick(0, obf3, 0, ClickType.PICKUP, (EntityPlayer)AutoReplenish.mc.player);
        AutoReplenish.mc.playerController.windowClick(0, obf2, 0, ClickType.PICKUP, (EntityPlayer)AutoReplenish.mc.player);
        AutoReplenish.mc.playerController.updateController();
    }
    
    public Pair<Integer, Integer> findReplenishableHotbarSlot() {
        Pair<Integer, Integer> obf = null;
        for (final Map.Entry<Integer, ItemStack> obf2 : this.get_hotbar().entrySet()) {
            final ItemStack obf3 = obf2.getValue();
            if (!obf3.isEmpty) {
                if (obf3.getItem() == Items.AIR) {
                    continue;
                }
                if (!obf3.isStackable()) {
                    continue;
                }
                if (obf3.stackSize >= obf3.getMaxStackSize()) {
                    continue;
                }
                if (obf3.stackSize > this.threshold.get_value(1)) {
                    continue;
                }
                final int obf4 = this.findCompatibleInventorySlot(obf3);
                if (obf4 == -1) {
                    continue;
                }
                obf = new Pair<Integer, Integer>(obf4, obf2.getKey());
            }
        }
        return obf;
    }
    
    public Map<Integer, ItemStack> get_inventory() {
        return this.get_inv_slots(9, 35);
    }
    
    public Map<Integer, ItemStack> get_hotbar() {
        return this.get_inv_slots(36, 44);
    }
    
    public Map<Integer, ItemStack> get_inv_slots(int obf, final int obf) {
        final Map<Integer, ItemStack> obf2 = new HashMap<Integer, ItemStack>();
        while (obf <= obf) {
            obf2.put(obf, (ItemStack)AutoReplenish.mc.player.inventoryContainer.getInventory().get(obf));
            ++obf;
        }
        return obf2;
    }
}
