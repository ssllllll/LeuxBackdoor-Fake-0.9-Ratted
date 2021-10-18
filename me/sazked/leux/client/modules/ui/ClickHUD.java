// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.ui;

import me.sazked.leux.client.modules.Category;
import net.minecraft.client.gui.GuiScreen;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class ClickHUD extends Module
{
    public Setting r;
    public Setting b;
    public Setting compass_scale;
    public Setting notification_armor;
    public Setting max_notifications;
    public Setting max_player_list;
    public Setting show_all_pots;
    public Setting a;
    public Setting notification_pop;
    public Setting arraylist_mode;
    public Setting g;
    public Setting notification_enable;
    
    @Override
    public void enable() {
        if (ClickHUD.mc.world != null && ClickHUD.mc.player != null) {
            Leux.get_hack_manager().get_module_with_tag("GUI").set_active(false);
            Leux.click_hud.back = false;
            ClickHUD.mc.displayGuiScreen((GuiScreen)Leux.click_hud);
        }
    }
    
    public ClickHUD() {
        super(Category.ui);
        this.r = this.create("Color R", "ColorR", 80, 0, 255);
        this.g = this.create("Color G", "ColorG", 80, 0, 255);
        this.b = this.create("Color B", "ColorB", 200, 0, 255);
        this.a = this.create("Color A", "ColorA", 255, 0, 255);
        this.compass_scale = this.create("Compass Scale", "HUDCompassScale", 16, 1, 60);
        this.arraylist_mode = this.create("ArrayList", "HUDArrayList", "Top R", this.combobox("Free", "Top R", "Top L", "Bottom R", "Bottom L"));
        this.show_all_pots = this.create("All Potions", "HUDAllPotions", true);
        this.max_player_list = this.create("Max Players", "HUDMaxPlayers", 10, 1, 64);
        this.notification_pop = this.create("Notify on Totem", "NotificationTotem", true);
        this.notification_enable = this.create("Notify on Enable", "NotificationEnable", true);
        this.notification_armor = this.create("Armor Notifications", "NotificationArmor", true);
        this.max_notifications = this.create("Max notifications", "MaxNotifications", 3, 1, 10);
        this.name = "HUD";
        this.tag = "HUD";
        this.description = "gui for pinnables";
    }
}
