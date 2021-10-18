// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render.components.widgets;

import me.sazked.leux.Leux;
import java.awt.Color;
import me.sazked.leux.client.guiscreen.render.Draw;
import me.sazked.leux.client.guiscreen.render.components.ModuleButton;
import me.sazked.leux.client.guiscreen.render.components.Frame;
import me.sazked.leux.client.guiscreen.render.components.AbstractWidget;

public class ButtonBind extends AbstractWidget
{
    private Frame frame;
    private ModuleButton master;
    private String button_name;
    private String points;
    private int x;
    private int y;
    private int width;
    private int height;
    private int save_y;
    private float tick;
    private boolean can;
    private boolean waiting;
    private Draw font;
    private int border_size;
    
    public ButtonBind(final Frame frame, final ModuleButton master, final String tag, final int update_postion) {
        this.font = new Draw(1.0f);
        this.border_size = 0;
        this.frame = frame;
        this.master = master;
        this.x = master.get_x();
        this.y = update_postion;
        this.save_y = this.y;
        this.width = master.get_width();
        this.height = this.font.get_string_height();
        this.button_name = tag;
        this.can = true;
        this.points = ".";
        this.tick = 0.0f;
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
    public boolean is_binding() {
        return this.waiting;
    }
    
    @Override
    public void bind(final char char_, final int key) {
        if (this.waiting) {
            switch (key) {
                case 1: {
                    this.waiting = false;
                    break;
                }
                case 211: {
                    this.master.get_module().set_bind(0);
                    this.waiting = false;
                    break;
                }
                default: {
                    this.master.get_module().set_bind(key);
                    this.waiting = false;
                    break;
                }
            }
        }
    }
    
    @Override
    public void mouse(final int mx, final int my, final int mouse) {
        if (mouse == 0 && this.motion(mx, my) && this.master.is_open() && this.can()) {
            this.frame.does_can(false);
            this.waiting = true;
        }
    }
    
    @Override
    public void render(final int master_y, final int separe, final int absolute_x, final int absolute_y) {
        this.set_width(this.master.get_width() - separe);
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        int bd_a;
        final int color_a = bd_a = Color.HSBtoRGB(tick_color[0], 1.0f, 1.0f);
        if (color_a <= 100) {
            bd_a = 100;
        }
        else if (color_a >= 200) {
            bd_a = 200;
        }
        else {
            bd_a = color_a;
        }
        if (this.waiting) {
            if (this.tick >= 15.0f) {
                this.points = "..";
            }
            if (this.tick >= 30.0f) {
                this.points = "...";
            }
            if (this.tick >= 45.0f) {
                this.points = ".";
                this.tick = 0.0f;
            }
        }
        final boolean zbob = true;
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
        if (this.waiting) {
            Draw.draw_rect(this.get_x(), this.save_y, this.get_x() + this.width, this.save_y + this.height, bg_r, bg_g, bg_b, bg_a);
            this.tick += 0.5f;
            Draw.draw_string("Listening " + this.points, this.x + 2, this.save_y, ns_r, ns_g, ns_b, ns_a);
        }
        else {
            Draw.draw_string("Bind <" + this.master.get_module().get_bind("string") + ">", this.x + 2, this.save_y, ns_r, ns_g, ns_b, ns_a);
        }
        final float[] array = tick_color;
        final int n = 0;
        array[n] += 5.0f;
    }
}
