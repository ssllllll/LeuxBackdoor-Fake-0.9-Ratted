// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.ui;

import me.sazked.leux.client.guiscreen.render.pinnables.PinnableButton;
import me.sazked.leux.client.guiscreen.render.pinnables.Frame;
import java.awt.Color;
import org.lwjgl.opengl.Display;
import me.sazked.leux.Leux;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Settings extends Module
{
    public Setting guiframes;
    public int color_r;
    public int color_g;
    public Setting hud;
    public Setting tabcolor;
    public Setting gui;
    public Setting customfont;
    public int color_b;
    public Setting clientmode;
    
    public Settings() {
        super(Category.ui);
        this.clientmode = this.create("Client", "Client", "LeuxBackdoor", this.combobox("LeuxBackdoor", "Paranoia"));
        this.gui = this.create("RGB GUI", "GUI", true);
        this.guiframes = this.create("RGB GUI FRAMES", "GUIFRAMES", false);
        this.hud = this.create("RGB HUD", "HUD", true);
        this.tabcolor = this.create("Tab Color", "TabColor", false);
        this.customfont = this.create("Custom Font", "CustomFont", false);
        this.name = "Settings";
        this.tag = "Settings";
        this.description = "Rainbow go br";
    }
    
    @Override
    public void update() {
        if (Settings.mc.world != null && Settings.mc.player != null) {
            if (this.clientmode.in("Paranoia")) {
                Leux.set_client_name("Paranoia");
            }
            else if (this.clientmode.in("LeuxBackdoor")) {
                Leux.set_client_name("LeuxBackdoor");
            }
            Display.setTitle(Leux.CLIENT_NAME + " v" + "0.9");
            final float[] obf = { System.currentTimeMillis() % ((long)(-728135817) ^ 0xFFFFFFFFD499AA77L) / Float.intBitsToFloat(Float.floatToIntBits(3.167934E-5f) ^ 0x7E30DF6F) };
            final int obf2 = Color.HSBtoRGB(obf[0], Float.intBitsToFloat(Float.floatToIntBits(55.919033f) ^ 0x7DDFAD17), Float.intBitsToFloat(Float.floatToIntBits(7.423508f) ^ 0x7F6D8D61));
            this.color_r = (obf2 >> 16 & 0xFF);
            this.color_g = (obf2 >> 8 & 0xFF);
            this.color_b = (obf2 & 0xFF);
            if (this.hud.get_value(true)) {
                Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").set_value(this.color_r);
                Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").set_value(this.color_g);
                Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").set_value(this.color_b);
            }
            if (this.gui.get_value(true)) {
                Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorR").set_value(this.color_r);
                Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorG").set_value(this.color_g);
                Leux.get_setting_manager().get_setting_with_tag("GUI", "ColorB").set_value(this.color_b);
                Frame.nc_r = this.color_r;
                Frame.nc_g = this.color_g;
                Frame.nc_b = this.color_b;
                Frame.bd_r = this.color_r;
                Frame.bd_g = this.color_g;
                Frame.bd_b = this.color_b;
                Frame.bd_a = 0;
                Frame.bdw_r = this.color_r;
                Frame.bdw_g = this.color_g;
                Frame.bdw_b = this.color_b;
                Frame.bdw_a = 255;
                PinnableButton.nc_r = this.color_r;
                PinnableButton.nc_g = this.color_g;
                PinnableButton.nc_b = this.color_b;
                PinnableButton.bg_r = this.color_r;
                PinnableButton.bg_g = this.color_g;
                PinnableButton.bg_b = this.color_b;
                PinnableButton.bd_r = this.color_r;
                PinnableButton.bd_g = this.color_g;
                PinnableButton.bd_b = this.color_b;
                Frame.bg_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameR").get_value(1);
                Frame.bg_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameG").get_value(1);
                Frame.bg_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameB").get_value(1);
                Frame.bg_a = Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameA").get_value(1);
                PinnableButton.bg_a = Leux.get_setting_manager().get_setting_with_tag("GUI", "WidgetA").get_value(1);
            }
            if (this.guiframes.get_value(true)) {
                Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameR").set_value(this.color_r);
                Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameG").set_value(this.color_g);
                Leux.get_setting_manager().get_setting_with_tag("GUI", "FrameB").set_value(this.color_b);
            }
        }
    }
}
