// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import java.text.DecimalFormat;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class Speedometer extends Pinnable
{
    public Speedometer() {
        super("Speedometer", "Speedometer", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        final double x = this.mc.player.posX - this.mc.player.prevPosX;
        final double z = this.mc.player.posZ - this.mc.player.prevPosZ;
        final float tr = this.mc.timer.tickLength / 1000.0f;
        final String bps = "Speed: " + new DecimalFormat("§a#.#").format(MathHelper.sqrt(x * x + z * z) / tr * 3.6);
        this.create_line(bps, this.docking(1, bps), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(bps, "width") + 2);
        this.set_height(this.get(bps, "height") + 2);
    }
    
    public static double get_speed() {
        final Minecraft mc = Minecraft.getMinecraft();
        final double x = mc.player.posX - mc.player.prevPosX;
        final double z = mc.player.posZ - mc.player.prevPosZ;
        final float tr = mc.timer.tickLength / 1000.0f;
        return MathHelper.sqrt(x * x + z * z) / tr * 3.6;
    }
}
