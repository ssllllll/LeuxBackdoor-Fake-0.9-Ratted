// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render.components.widgets;

import me.sazked.turok.draw.RenderHelp;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.Draw;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.guiscreen.render.components.ModuleButton;
import me.sazked.leux.client.guiscreen.render.components.Frame;
import me.sazked.leux.client.guiscreen.render.components.AbstractWidget;

public class Label extends AbstractWidget
{
    private Frame frame;
    private ModuleButton master;
    private Setting setting;
    private String label_name;
    private int x;
    private int y;
    private int width;
    private int height;
    private int save_y;
    private boolean can;
    private boolean info;
    private final Draw font;
    private int border_size;
    
    public Label(final Frame frame, final ModuleButton master, final String tag, final int update_postion) {
        this.font = new Draw(1.0f);
        this.border_size = 0;
        this.frame = frame;
        this.master = master;
        this.setting = Leux.get_setting_manager().get_setting_with_tag(master.get_module(), tag);
        this.x = master.get_x();
        this.y = update_postion;
        this.save_y = this.y;
        this.width = master.get_width();
        this.height = this.font.get_string_height();
        this.label_name = this.setting.get_name();
        if (this.setting.get_name().equalsIgnoreCase("info")) {
            this.info = true;
        }
        this.can = true;
    }
    
    public Setting get_setting() {
        return this.setting;
    }
    
    @Override
    public void does_can(final boolean value) {
        this.can = value;
    }
    
    @Override
    public void set_x(final int x) {
        this.x = x;
    }
    
    @Override
    public void set_y(final int y) {
        this.y = y;
    }
    
    @Override
    public void set_width(final int width) {
        this.width = width;
    }
    
    @Override
    public void set_height(final int height) {
        this.height = height;
    }
    
    @Override
    public int get_x() {
        return this.x;
    }
    
    @Override
    public int get_y() {
        return this.y;
    }
    
    @Override
    public int get_width() {
        return this.width;
    }
    
    @Override
    public int get_height() {
        return this.height;
    }
    
    public int get_save_y() {
        return this.save_y;
    }
    
    @Override
    public boolean motion_pass(final int mx, final int my) {
        return this.motion(mx, my);
    }
    
    public boolean motion(final int mx, final int my) {
        return mx >= this.get_x() && my >= this.get_save_y() && mx <= this.get_x() + this.get_width() && my <= this.get_save_y() + this.get_height();
    }
    
    public boolean can() {
        return this.can;
    }
    
    @Override
    public void mouse(final int mx, final int my, final int mouse) {
        if (mouse == 0 && this.motion(mx, my) && this.master.is_open() && this.can()) {
            this.frame.does_can(false);
        }
    }
    
    @Override
    public void render(final int master_y, final int separe, final int absolute_x, final int absolute_y) {
        this.set_width(this.master.get_width() - separe);
        final String s = "me";
        this.save_y = this.y + master_y;
        final int ns_r = Leux.click_gui.theme_widget_name_r;
        final int ns_g = Leux.click_gui.theme_widget_name_g;
        final int ns_b = Leux.click_gui.theme_widget_name_b;
        final int ns_a = Leux.click_gui.theme_widget_name_a;
        final int bg_r = Leux.click_gui.theme_widget_background_r;
        final int bg_g = Leux.click_gui.theme_widget_background_g;
        final int bg_b = Leux.click_gui.theme_widget_background_b;
        final int bg_a = Leux.click_gui.theme_widget_background_a;
        final int bd_r = Leux.click_gui.theme_widget_border_r;
        final int bd_g = Leux.click_gui.theme_widget_border_g;
        final int bd_b = Leux.click_gui.theme_widget_border_b;
        final int bd_a = 100;
        if (this.motion(absolute_x, absolute_y) && this.setting.get_master().using_widget()) {
            this.setting.get_master().event_widget();
            GL11.glPushMatrix();
            GL11.glEnable(3553);
            GL11.glEnable(3042);
            GlStateManager.enableBlend();
            GL11.glPopMatrix();
            RenderHelp.release_gl();
        }
        if (this.info) {
            Draw.draw_string(this.setting.get_value(s), this.x + 2, this.save_y, ns_r, ns_g, ns_b, ns_a);
        }
        else {
            Draw.draw_string(this.label_name + " \"" + this.setting.get_value(s) + "\"", this.x + 2, this.save_y, ns_r, ns_g, ns_b, ns_a);
        }
    }
}
