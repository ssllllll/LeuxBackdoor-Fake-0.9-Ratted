// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import java.util.function.ToIntFunction;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class PvpHud extends Pinnable
{
    public PvpHud() {
        super("PVP Hud", "pvphud", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        final String totem = this.get_totems();
        final String trap = this.trap_enabled();
        final String aura = this.aura_enabled();
        final String surround = this.surround_enabled();
        final String holefill = this.holefill_enabled();
        final String socks = this.socks_enabled();
        final String selftrap = this.selftrap_enabled();
        final String killaura = this.killaura_enabled();
        this.create_line(totem, this.docking(1, totem), 2, nl_r, nl_g, nl_b, nl_a);
        this.create_line(aura, this.docking(1, aura), 13, nl_r, nl_g, nl_b, nl_a);
        this.create_line(trap, this.docking(1, trap), 24, nl_r, nl_g, nl_b, nl_a);
        this.create_line(surround, this.docking(1, surround), 34, nl_r, nl_g, nl_b, nl_a);
        this.create_line(holefill, this.docking(1, holefill), 45, nl_r, nl_g, nl_b, nl_a);
        this.create_line(socks, this.docking(1, socks), 56, nl_r, nl_g, nl_b, nl_a);
        this.create_line(selftrap, this.docking(1, selftrap), 67, nl_r, nl_g, nl_b, nl_a);
        this.create_line(killaura, this.docking(1, killaura), 78, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(surround, "width") + 2);
        this.set_height(this.get(surround, "height") * 5);
    }
    
    public String selftrap_enabled() {
        try {
            if (Leux.get_hack_manager().get_module_with_tag("SelfTrap").is_active()) {
                return "SelfTrap";
            }
            return "§cSelfTrap";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String trap_enabled() {
        try {
            if (Leux.get_hack_manager().get_module_with_tag("AutoTrap").is_active()) {
                return "AutoTrap";
            }
            return "§cAutoTrap";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String aura_enabled() {
        try {
            if (Leux.get_hack_manager().get_module_with_tag("AutoCrystalOld").is_active() || Leux.get_hack_manager().get_module_with_tag("AutoCrystalNew").is_active()) {
                return "Aura";
            }
            return "§cAura";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String killaura_enabled() {
        try {
            if (Leux.get_hack_manager().get_module_with_tag("KillAura").is_active()) {
                return "KillAura";
            }
            return "§cKillAura";
        }
        catch (Exception e) {
            return "§4ERROR";
        }
    }
    
    public String socks_enabled() {
        try {
            if (Leux.get_hack_manager().get_module_with_tag("AntiCityBoss").is_active()) {
                return "Socks";
            }
            return "§cSocks";
        }
        catch (Exception e) {
            return "§4ERROR";
        }
    }
    
    public String surround_enabled() {
        try {
            if (Leux.get_hack_manager().get_module_with_tag("Surround").is_active()) {
                return "Surround";
            }
            return "§cSurround";
        }
        catch (Exception e) {
            return "§4ERROR";
        }
    }
    
    public String holefill_enabled() {
        try {
            if (Leux.get_hack_manager().get_module_with_tag("HoleFill").is_active()) {
                return "HoleFill";
            }
            return "§cHoleFill";
        }
        catch (Exception e) {
            return "§4ERROR";
        }
    }
    
    public String get_totems() {
        try {
            final int totems = this.offhand() + this.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::func_190916_E).sum();
            if (totems >= 1) {
                return "Totems " + totems;
            }
            return "§cTotems " + totems;
        }
        catch (Exception e) {
            return "§4ERROR";
        }
    }
    
    public int offhand() {
        if (this.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            return 1;
        }
        return 0;
    }
}
