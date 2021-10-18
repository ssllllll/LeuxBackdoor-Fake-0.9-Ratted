// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiandfont;

import net.minecraft.client.gui.GuiScreen;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class ClickGUIModule extends Module
{
    public Setting frame_g;
    public Setting scrolling;
    public Setting frame_r;
    public Setting a;
    public Setting frame_a;
    public Setting theme;
    public Setting scroll_speed;
    public Setting r;
    public Setting g;
    public Setting frame_b;
    public Setting b;
    
    public ClickGUIModule() {
        super(Category.ui);
        this.scrolling = this.create("Scrolling", "Scrolling", "Screen", this.combobox("Container", "Screen"));
        this.scroll_speed = this.create("Scroll Speed", "ScrollSpeed", 10, 1, 20);
        this.theme = this.create("Theme", "Theme", "1", this.combobox("1", "2"));
        this.r = this.create("Color R", "ColorR", 80, 0, 255);
        this.g = this.create("Color G", "ColorG", 80, 0, 255);
        this.b = this.create("Color B", "ColorB", 200, 0, 255);
        this.a = this.create("Color A", "ColorA", 135, 40, 255);
        this.frame_r = this.create("Frame R", "FrameR", 0, 0, 255);
        this.frame_g = this.create("Frame G", "FrameG", 0, 0, 255);
        this.frame_b = this.create("Frame B", "FrameB", 0, 0, 255);
        this.frame_a = this.create("Frame A", "FrameA", 255, 0, 255);
        this.name = "NEW GUI";
        this.tag = "NEWGUI";
        this.description = "The new main gui";
        this.set_bind(24);
    }
    
    @Override
    public void enable() {
        if (ClickGUIModule.mc.world == null || ClickGUIModule.mc.player != null) {}
    }
    
    @Override
    public void update() {
        if (ClickGUIModule.mc.world != null) {
            if (ClickGUIModule.mc.player != null) {}
        }
    }
    
    public int enabledColor() {
        return this.r.get_value(1) + this.g.get_value(1) + this.b.get_value(1) + this.a.get_value(1);
    }
    
    @Override
    public void disable() {
        if (ClickGUIModule.mc.world != null) {
            if (ClickGUIModule.mc.player != null) {
                ClickGUIModule.mc.displayGuiScreen((GuiScreen)null);
            }
        }
    }
}
