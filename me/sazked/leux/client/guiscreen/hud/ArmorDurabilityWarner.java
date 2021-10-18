// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import java.util.HashMap;
import java.util.Iterator;
import me.sazked.leux.client.util.NotificationUtil;
import me.sazked.leux.client.util.Notification;
import me.sazked.leux.Leux;
import net.minecraft.item.ItemStack;
import java.util.Map;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class ArmorDurabilityWarner extends Pinnable
{
    private boolean already_warned_helmet;
    private boolean already_warned_chestplate;
    private boolean already_warned_leggings;
    private boolean already_warned_boots;
    
    public ArmorDurabilityWarner() {
        super("Armor Warner", "ArmorWarner", 1.0f, 0, 0);
        this.already_warned_helmet = false;
        this.already_warned_chestplate = false;
        this.already_warned_leggings = false;
        this.already_warned_boots = false;
    }
    
    @Override
    public void render() {
        final String line = "See ur armor :p";
        if (this.is_damaged()) {
            this.create_line(line, this.docking(1, line), 2, 255, 20, 20, 255);
        }
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
    
    private boolean is_damaged() {
        for (final Map.Entry<Integer, ItemStack> armor_slot : this.get_armor().entrySet()) {
            if (armor_slot.getValue().isEmpty()) {
                continue;
            }
            final ItemStack stack = armor_slot.getValue();
            final double max_dam = stack.getMaxDamage();
            final double dam_left = stack.getMaxDamage() - stack.getItemDamage();
            final double percent = dam_left / max_dam * 100.0;
            String notification = "";
            if (this.mc.player.inventoryContainer.getInventory().get(5) == stack) {
                notification += "Your helmet is at ";
                if (percent < 30.0 && !this.already_warned_helmet) {
                    if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationArmor").get_value(true)) {
                        NotificationUtil.send_notification(new Notification(notification + (int)percent + "%", 214, 38, 26));
                    }
                    this.already_warned_helmet = true;
                }
                else if (percent > 30.0) {
                    this.already_warned_helmet = false;
                }
            }
            else if (this.mc.player.inventoryContainer.getInventory().get(6) == stack) {
                notification += "Your chestplate is at ";
                if (percent < 40.0 && !this.already_warned_chestplate) {
                    if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationArmor").get_value(true)) {
                        NotificationUtil.send_notification(new Notification(notification + (int)percent + "%", 214, 38, 26));
                    }
                    this.already_warned_chestplate = true;
                }
                else if (percent > 40.0) {
                    this.already_warned_chestplate = false;
                }
            }
            else if (this.mc.player.inventoryContainer.getInventory().get(7) == stack) {
                notification += "Your leggings are at ";
                if (percent < 40.0 && !this.already_warned_leggings) {
                    if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationArmor").get_value(true)) {
                        NotificationUtil.send_notification(new Notification(notification + (int)percent + "%", 214, 38, 26));
                    }
                    this.already_warned_leggings = true;
                }
                else if (percent > 40.0) {
                    this.already_warned_leggings = false;
                }
            }
            else if (this.mc.player.inventoryContainer.getInventory().get(8) == stack) {
                notification += "Your boots are at ";
                if (percent < 30.0 && !this.already_warned_boots) {
                    if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationArmor").get_value(true)) {
                        NotificationUtil.send_notification(new Notification(notification + (int)percent + "%", 214, 38, 26));
                    }
                    this.already_warned_boots = true;
                }
                else if (percent > 30.0) {
                    this.already_warned_boots = false;
                }
            }
            if (percent < 30.0) {
                return true;
            }
        }
        return false;
    }
    
    private Map<Integer, ItemStack> get_armor() {
        return this.get_inv_slots(5, 8);
    }
    
    private Map<Integer, ItemStack> get_inv_slots(int current, final int last) {
        final Map<Integer, ItemStack> full_inv_slots = new HashMap<Integer, ItemStack>();
        while (current <= last) {
            full_inv_slots.put(current, (ItemStack)this.mc.player.inventoryContainer.getInventory().get(current));
            ++current;
        }
        return full_inv_slots;
    }
}
