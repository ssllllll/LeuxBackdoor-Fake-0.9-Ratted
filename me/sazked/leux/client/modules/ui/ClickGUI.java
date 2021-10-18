// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.ui;

import me.sazked.leux.client.modules.Category;
import net.minecraft.client.gui.GuiScreen;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class ClickGUI extends Module
{
    public Setting r;
    public Setting frame_g;
    public Setting g;
    public Setting b;
    public Setting frame_r;
    public Setting widget_a;
    public Setting frame_a;
    public Setting frame_b;
    
    @Override
    public void enable() {
        if (ClickGUI.mc.world != null && ClickGUI.mc.player != null) {
            ClickGUI.mc.displayGuiScreen((GuiScreen)Leux.click_gui);
        }
    }
    
    @Override
    public void disable() {
        if (ClickGUI.mc.world != null) {
            if (ClickGUI.mc.player != null) {
                ClickGUI.mc.displayGuiScreen((GuiScreen)null);
            }
        }
    }
    
    public ClickGUI() {
        super(Category.ui);
        this.r = this.create("Color R", "ColorR", 255, 0, 255);
        this.g = this.create("Color G", "ColorG", 45, 0, 255);
        this.b = this.create("Color B", "ColorB", 255, 0, 255);
        this.widget_a = this.create("Widget A", "WidgetA", 135, 40, 255);
        this.frame_r = this.create("Frame R", "FrameR", 0, 0, 255);
        this.frame_g = this.create("Frame G", "FrameG", 0, 0, 255);
        this.frame_b = this.create("Frame B", "FrameB", 0, 0, 255);
        this.frame_a = this.create("Frame A", "FrameA", 255, 0, 255);
        this.name = "GUI";
        this.tag = "GUI";
        this.description = "The main gui";
        this.set_bind(54);
    }
    
    @Override
    public void update() {
        if (ClickGUI.mc.world != null && ClickGUI.mc.player != null) {
            Leux.click_gui.theme_frame_name_r = this.r.get_value(1);
            Leux.click_gui.theme_frame_name_g = this.g.get_value(1);
            Leux.click_gui.theme_frame_name_b = this.b.get_value(1);
            Leux.click_gui.theme_widget_name_r = this.r.get_value(1);
            Leux.click_gui.theme_widget_name_g = this.g.get_value(1);
            Leux.click_gui.theme_widget_name_b = this.b.get_value(1);
            Leux.click_gui.theme_frame_border_r = this.r.get_value(1);
            Leux.click_gui.theme_frame_border_g = this.g.get_value(1);
            Leux.click_gui.theme_frame_border_b = this.b.get_value(1);
            Leux.click_gui.theme_widget_border_r = this.r.get_value(1);
            Leux.click_gui.theme_widget_border_g = this.g.get_value(1);
            Leux.click_gui.theme_widget_border_b = this.b.get_value(1);
            Leux.click_gui.theme_widget_background_r = this.r.get_value(1);
            Leux.click_gui.theme_widget_background_g = this.g.get_value(1);
            Leux.click_gui.theme_widget_background_b = this.b.get_value(1);
            Leux.click_gui.theme_widget_background_a = this.widget_a.get_value(1);
            Leux.click_gui.theme_frame_background_r = this.frame_r.get_value(1);
            Leux.click_gui.theme_frame_background_g = this.frame_g.get_value(1);
            Leux.click_gui.theme_frame_background_b = this.frame_b.get_value(1);
            Leux.click_gui.theme_frame_background_a = this.frame_a.get_value(1);
        }
    }
}
