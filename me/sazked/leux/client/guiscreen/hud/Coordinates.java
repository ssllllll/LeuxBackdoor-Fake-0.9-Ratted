// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import me.sazked.leux.Leux;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class Coordinates extends Pinnable
{
    ChatFormatting g;
    ChatFormatting db;
    ChatFormatting r;
    
    public Coordinates() {
        super("Coordinates", "Coordinates", 1.0f, 0, 0);
        this.g = ChatFormatting.GRAY;
        this.db = ChatFormatting.DARK_BLUE;
        this.r = ChatFormatting.RED;
    }
    
    @Override
    public void render() {
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        final String x = this.g + "[" + Leux.r + Integer.toString((int)this.mc.player.posX) + this.g + "]" + Leux.r;
        final String y = this.g + "[" + Leux.r + Integer.toString((int)this.mc.player.posY) + this.g + "]" + Leux.r;
        final String z = this.g + "[" + Leux.r + Integer.toString((int)this.mc.player.posZ) + this.g + "]" + Leux.r;
        final String x_nether = this.g + "[" + this.r + Long.toString(Math.round((this.mc.player.dimension != -1) ? (this.mc.player.posX / 8.0) : (this.mc.player.posX * 8.0))) + this.g + "]" + Leux.r;
        final String z_nether = this.g + "[" + this.r + Long.toString(Math.round((this.mc.player.dimension != -1) ? (this.mc.player.posZ / 8.0) : (this.mc.player.posZ * 8.0))) + this.g + "]" + Leux.r;
        final String line = "XYZ " + x + y + z + this.r + " XZ " + x_nether + z_nether;
        this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(line, "width"));
        this.set_height(this.get(line, "height") + 2);
    }
}
