// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import net.minecraft.item.ItemExpBottle;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import java.util.Map;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class StopEXP extends Module
{
    @EventHandler
    public Listener<EventPacket.SendPacket> packet_event;
    public Setting chest_leggings_percent;
    public Setting helmet_boot_percent;
    public boolean should_cancel;
    
    public void lambda$new$0(final EventPacket.SendPacket sendPacket) {
        if (sendPacket.get_packet() instanceof CPacketPlayerTryUseItem && this.should_cancel) {
            sendPacket.cancel();
        }
    }
    
    public StopEXP() {
        super(Category.misc);
        this.helmet_boot_percent = this.create("Helment Boots %", "StopEXHelmet", 80, 0, 100);
        this.chest_leggings_percent = this.create("Chest Leggins %", "StopEXChest", 100, 0, 100);
        this.should_cancel = false;
        this.packet_event = new Listener<EventPacket.SendPacket>(this::lambda$new$0, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Stop EXP";
        this.tag = "StopEXP";
        this.description = "jumpy has a good idea?? (nvm this is dumb)";
    }
    
    @Override
    public void update() {
        int obf = 0;
        for (final Map.Entry<Integer, ItemStack> obf2 : this.get_armor().entrySet()) {
            ++obf;
            if (obf2.getValue().isEmpty()) {
                continue;
            }
            final ItemStack obf3 = obf2.getValue();
            final double obf4 = obf3.getMaxDamage();
            final double obf5 = obf3.getMaxDamage() - obf3.getItemDamage();
            final double obf6 = obf5 / obf4 * Double.longBitsToDouble(Double.doubleToLongBits(0.013713845269793893) ^ 0x7FD51601277D4635L);
            if (obf == 1 || obf == 4) {
                if (obf6 >= this.helmet_boot_percent.get_value(1)) {
                    if (this.is_holding_exp()) {
                        this.should_cancel = true;
                    }
                    else {
                        this.should_cancel = false;
                    }
                }
                else {
                    this.should_cancel = false;
                }
            }
            if (obf != 2 && obf != 3) {
                continue;
            }
            if (obf6 >= this.chest_leggings_percent.get_value(1)) {
                if (this.is_holding_exp()) {
                    this.should_cancel = true;
                }
                else {
                    this.should_cancel = false;
                }
            }
            else {
                this.should_cancel = false;
            }
        }
    }
    
    public Map<Integer, ItemStack> get_armor() {
        return this.get_inv_slots(5, 8);
    }
    
    public Map<Integer, ItemStack> get_inv_slots(int obf, final int obf) {
        final Map<Integer, ItemStack> obf2 = new HashMap<Integer, ItemStack>();
        while (obf <= obf) {
            obf2.put(obf, (ItemStack)StopEXP.mc.player.inventoryContainer.getInventory().get(obf));
            ++obf;
        }
        return obf2;
    }
    
    public boolean is_holding_exp() {
        return StopEXP.mc.player.getHeldItemMainhand().getItem() instanceof ItemExpBottle || StopEXP.mc.player.getHeldItemOffhand().getItem() instanceof ItemExpBottle;
    }
}
