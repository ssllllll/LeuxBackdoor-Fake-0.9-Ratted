// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import me.sazked.leux.client.util.TimeUtil;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class Time extends Pinnable
{
    public Time() {
        super("Time", "Time", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        String line = "";
        line += ((TimeUtil.get_hour() < 10) ? ("0" + TimeUtil.get_hour()) : Integer.valueOf(TimeUtil.get_hour()));
        line += ":";
        line += ((TimeUtil.get_minuite() < 10) ? ("0" + TimeUtil.get_minuite()) : Integer.valueOf(TimeUtil.get_minuite()));
        line += ":";
        line += ((TimeUtil.get_second() < 10) ? ("0" + TimeUtil.get_second()) : Integer.valueOf(TimeUtil.get_second()));
        this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
}
