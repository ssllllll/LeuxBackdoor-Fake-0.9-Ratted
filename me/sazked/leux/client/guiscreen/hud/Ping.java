// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class Ping extends Pinnable
{
    public Ping() {
        super("Ping", "Ping", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        final String line = "Ping: " + this.get_ping();
        this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
    
    public String get_ping() {
        try {
            final int ping = this.mc.getConnection().getPlayerInfo(this.mc.player.getUniqueID()).getResponseTime();
            if (ping <= 150) {
                return "§a" + Integer.toString(ping);
            }
            if (ping <= 300) {
                return "§e" + Integer.toString(ping);
            }
            return "§c" + Integer.toString(ping);
        }
        catch (Exception e) {
            return "oh no";
        }
    }
}
