// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen;

import me.sazked.leux.client.guiscreen.render.pinnables.PinnableButton;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Frame;
import net.minecraft.client.gui.GuiScreen;

public class HUD extends GuiScreen
{
    private final Frame frame;
    private int frame_height;
    public boolean on_gui;
    public boolean back;
    
    public HUD() {
        this.frame = new Frame("HUD", "HUD", 40, 40);
        this.back = false;
        this.on_gui = false;
    }
    
    public Frame get_frame_hud() {
        return this.frame;
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void initGui() {
        this.on_gui = true;
        Frame.nc_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorR").get_value(1);
        Frame.nc_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorG").get_value(1);
        Frame.nc_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorB").get_value(1);
        Frame.bg_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameR").get_value(1);
        Frame.bg_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameG").get_value(1);
        Frame.bg_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameB").get_value(1);
        Frame.bg_a = Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameA").get_value(1);
        Frame.bd_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorR").get_value(1);
        Frame.bd_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorG").get_value(1);
        Frame.bd_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorB").get_value(1);
        Frame.bd_a = 0;
        Frame.bdw_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorR").get_value(1);
        Frame.bdw_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorG").get_value(1);
        Frame.bdw_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorB").get_value(1);
        Frame.bdw_a = 255;
        PinnableButton.nc_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorR").get_value(1);
        PinnableButton.nc_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorG").get_value(1);
        PinnableButton.nc_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorB").get_value(1);
        PinnableButton.bg_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorR").get_value(1);
        PinnableButton.bg_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorG").get_value(1);
        PinnableButton.bg_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorB").get_value(1);
        PinnableButton.bg_a = Leux.get_setting_manager().get_setting_with_tag("GUI", "WidgetA").get_value(1);
        PinnableButton.bd_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorR").get_value(1);
        PinnableButton.bd_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorG").get_value(1);
        PinnableButton.bd_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorB").get_value(1);
    }
    
    public void onGuiClosed() {
        if (this.back) {
            Leux.get_hack_manager().get_module_with_tag("GUI").set_active(true);
            Leux.get_hack_manager().get_module_with_tag("HUD").set_active(false);
        }
        else {
            Leux.get_hack_manager().get_module_with_tag("HUD").set_active(false);
            Leux.get_hack_manager().get_module_with_tag("GUI").set_active(false);
        }
        this.on_gui = false;
        Leux.get_config_manager().save_settings();
    }
    
    protected void mouseClicked(final int mx, final int my, final int mouse) {
        this.frame.mouse(mx, my, mouse);
        if (mouse == 0 && this.frame.motion(mx, my) && this.frame.can()) {
            this.frame.set_move(true);
            this.frame.set_move_x(mx - this.frame.get_x());
            this.frame.set_move_y(my - this.frame.get_y());
        }
    }
    
    protected void mouseReleased(final int mx, final int my, final int state) {
        this.frame.release(mx, my, state);
        this.frame.set_move(false);
    }
    
    public void drawScreen(final int mx, final int my, final float tick) {
        this.drawDefaultBackground();
        this.frame.render(mx, my, 2);
    }
}
