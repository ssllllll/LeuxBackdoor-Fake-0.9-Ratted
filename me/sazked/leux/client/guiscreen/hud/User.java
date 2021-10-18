// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.util.math.MathHelper;
import me.sazked.leux.client.guiandfont.FontUtil;
import me.sazked.leux.client.guiscreen.render.Draw;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.util.TimeUtil;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class User extends Pinnable
{
    private int scaled_width;
    private int scaled_height;
    private int scale_factor;
    
    public User() {
        super("User", "User", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        this.updateResolution();
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        final int time = TimeUtil.get_hour();
        String line;
        if (time >= 0 && time < 12) {
            line = "Good Morning, " + ChatFormatting.BOLD + this.mc.player.getName() + ChatFormatting.RESET + " welcome to " + Leux.CLIENT_NAME + " :)";
        }
        else if (time >= 12 && time < 16) {
            line = "Good Afternoon, " + ChatFormatting.BOLD + this.mc.player.getName() + ChatFormatting.RESET + " welcome to " + Leux.CLIENT_NAME + " :)";
        }
        else if (time >= 16 && time < 24) {
            line = "Good Evening, " + ChatFormatting.BOLD + this.mc.player.getName() + ChatFormatting.RESET + " welcome to " + Leux.CLIENT_NAME + " :)";
        }
        else {
            line = "Hello, " + ChatFormatting.BOLD + this.mc.player.getName() + ChatFormatting.RESET + " welcome to " + Leux.CLIENT_NAME + " :)";
        }
        FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), line, this.scaled_width / 2 - this.mc.fontRenderer.getStringWidth(line) / 2, 20, new Draw.ClientColor(nl_r, nl_g, nl_b, nl_a).hex());
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
    
    public void updateResolution() {
        this.scaled_width = this.mc.displayWidth;
        this.scaled_height = this.mc.displayHeight;
        this.scale_factor = 1;
        final boolean flag = this.mc.isUnicode();
        int i = this.mc.gameSettings.guiScale;
        if (i == 0) {
            i = 1000;
        }
        while (this.scale_factor < i && this.scaled_width / (this.scale_factor + 1) >= 320 && this.scaled_height / (this.scale_factor + 1) >= 240) {
            ++this.scale_factor;
        }
        if (flag && this.scale_factor % 2 != 0 && this.scale_factor != 1) {
            --this.scale_factor;
        }
        final double scaledWidthD = this.scaled_width / (double)this.scale_factor;
        final double scaledHeightD = this.scaled_height / (double)this.scale_factor;
        this.scaled_width = MathHelper.ceil(scaledWidthD);
        this.scaled_height = MathHelper.ceil(scaledHeightD);
    }
}
