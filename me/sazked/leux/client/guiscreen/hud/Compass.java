// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import me.sazked.leux.client.util.MathUtil;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.Draw;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class Compass extends Pinnable
{
    public Draw font;
    private static final double half_pi = 1.5707963267948966;
    
    public Compass() {
        super("Compass", "Compass", 1.0f, 0, 0);
        this.font = new Draw(1.0f);
    }
    
    @Override
    public void render() {
        final int r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        for (final Direction dir : Direction.values()) {
            final double rad = this.get_pos_on_compass(dir);
            if (dir.name().equals("N")) {
                this.create_line(dir.name(), (int)(this.docking(1, dir.name()) + this.get_x(rad)), (int)this.get_y(rad), r, g, b, a);
            }
            else {
                this.create_line(dir.name(), (int)(this.docking(1, dir.name()) + this.get_x(rad)), (int)this.get_y(rad), 225, 225, 225, 225);
            }
        }
        this.set_width(50);
        this.set_height(50);
    }
    
    private double get_pos_on_compass(final Direction dir) {
        final double yaw = Math.toRadians(MathUtil.wrap(this.mc.getRenderViewEntity().rotationYaw));
        final int index = dir.ordinal();
        return yaw + index * 1.5707963267948966;
    }
    
    private double get_x(final double rad) {
        return Math.sin(rad) * Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDCompassScale").get_value(1);
    }
    
    private double get_y(final double rad) {
        final double epic_pitch = MathUtil.clamp2(this.mc.getRenderViewEntity().rotationPitch + 30.0f, -90.0f, 90.0f);
        final double pitch_radians = Math.toRadians(epic_pitch);
        return Math.cos(rad) * Math.sin(pitch_radians) * Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDCompassScale").get_value(1);
    }
    
    private enum Direction
    {
        N, 
        W, 
        S, 
        E;
    }
}
