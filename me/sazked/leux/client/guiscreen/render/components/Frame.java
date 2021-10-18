// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render.components;

import java.awt.Color;
import java.util.Iterator;
import me.sazked.leux.client.modules.Module;
import me.sazked.leux.Leux;
import net.minecraft.client.Minecraft;
import me.sazked.leux.client.guiscreen.render.Draw;
import java.util.ArrayList;
import me.sazked.leux.client.modules.Category;

public class Frame
{
    private Category category;
    private ArrayList<ModuleButton> module_button;
    private int x;
    private int y;
    private int width;
    private int height;
    private int width_name;
    private int width_abs;
    private String frame_name;
    private String frame_tag;
    private Draw font;
    private boolean first;
    private boolean move;
    private int move_x;
    private int move_y;
    private boolean can;
    private final Minecraft mc;
    
    public Frame(final Category category) {
        this.font = new Draw(1.0f);
        this.first = false;
        this.mc = Minecraft.getMinecraft();
        this.x = 10;
        this.y = 10;
        this.width = 100;
        this.height = 25;
        this.category = category;
        this.module_button = new ArrayList<ModuleButton>();
        this.width_name = this.font.get_string_width(this.category.get_name());
        this.width_abs = this.width_name;
        this.frame_name = category.get_name();
        this.frame_tag = category.get_tag();
        this.move_x = 0;
        this.move_y = 0;
        final int size = Leux.get_hack_manager().get_modules_with_category(category).size();
        int count = 0;
        for (final Module modules : Leux.get_hack_manager().get_modules_with_category(category)) {
            final ModuleButton buttons = new ModuleButton(modules, this);
            buttons.set_y(this.height);
            this.module_button.add(buttons);
            if (++count >= size) {
                this.height += 10;
            }
            else {
                this.height += 17;
            }
        }
        this.move = false;
        this.can = true;
    }
    
    public void refresh_frame(final ModuleButton button, final int combo_height) {
        this.height = 25;
        final int size = Leux.get_hack_manager().get_modules_with_category(this.category).size();
        int count = 0;
        for (final ModuleButton buttons : this.module_button) {
            buttons.set_y(this.height);
            int compare;
            if (++count >= size) {
                compare = 10;
            }
            else {
                compare = 17;
            }
            if (buttons.is_open()) {
                if (compare == 10) {
                    this.height += buttons.get_settings_height() - compare;
                }
                else {
                    this.height += buttons.get_settings_height();
                }
            }
            else {
                this.height += compare;
            }
        }
    }
    
    public void does_can(final boolean value) {
        this.can = value;
    }
    
    public void set_move(final boolean value) {
        this.move = value;
    }
    
    public void set_move_x(final int x) {
        this.move_x = x;
    }
    
    public void set_move_y(final int y) {
        this.move_y = y;
    }
    
    public void set_width(final int width) {
        this.width = width;
    }
    
    public void set_height(final int height) {
        this.height = height;
    }
    
    public void set_x(final int x) {
        this.x = x;
    }
    
    public void set_y(final int y) {
        this.y = y;
    }
    
    public String get_name() {
        return this.frame_name;
    }
    
    public String get_tag() {
        return this.frame_tag;
    }
    
    public boolean is_moving() {
        return this.move;
    }
    
    public int get_width() {
        return this.width;
    }
    
    public int get_height() {
        return this.height;
    }
    
    public int get_x() {
        return this.x;
    }
    
    public int get_y() {
        return this.y;
    }
    
    public boolean can() {
        return this.can;
    }
    
    public boolean motion(final int mx, final int my) {
        return mx >= this.get_x() && my >= this.get_y() && mx <= this.get_x() + this.get_width() && my <= this.get_y() + this.get_height();
    }
    
    public boolean motion(final String tag, final int mx, final int my) {
        return mx >= this.get_x() && my >= this.get_y() && mx <= this.get_x() + this.get_width() && my <= this.get_y() + this.font.get_string_height();
    }
    
    public void crush(final int mx, final int my) {
        final int screen_x = this.mc.displayWidth / 2;
        final int screen_y = this.mc.displayHeight / 2;
        this.set_x(mx - this.move_x);
        this.set_y(my - this.move_y);
        if (this.x + this.width >= screen_x) {
            this.x = screen_x - this.width - 1;
        }
        if (this.x <= 0) {
            this.x = 1;
        }
        if (this.y + this.height >= screen_y) {
            this.y = screen_y - this.height - 1;
        }
        if (this.y <= 0) {
            this.y = 1;
        }
        if (this.x % 2 != 0) {
            this.x += this.x % 2;
        }
        if (this.y % 2 != 0) {
            this.y += this.y % 2;
        }
    }
    
    public boolean is_binding() {
        boolean value_requested = false;
        for (final ModuleButton buttons : this.module_button) {
            if (buttons.is_binding()) {
                value_requested = true;
            }
        }
        return value_requested;
    }
    
    public void does_button_for_do_widgets_can(final boolean can) {
        for (final ModuleButton buttons : this.module_button) {
            buttons.does_widgets_can(can);
        }
    }
    
    public void bind(final char char_, final int key) {
        for (final ModuleButton buttons : this.module_button) {
            buttons.bind(char_, key);
        }
    }
    
    public void mouse(final int mx, final int my, final int mouse) {
        for (final ModuleButton buttons : this.module_button) {
            buttons.mouse(mx, my, mouse);
        }
    }
    
    public void mouse_release(final int mx, final int my, final int mouse) {
        for (final ModuleButton buttons : this.module_button) {
            buttons.button_release(mx, my, mouse);
        }
    }
    
    public void render(final int mx, final int my) {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int color_a = Color.HSBtoRGB(tick_color[0], 1.0f, 1.0f);
        int border_a;
        if (color_a <= 50) {
            border_a = 50;
        }
        else {
            border_a = Math.min(color_a, 120);
        }
        final int nc_r = Leux.click_gui.theme_frame_name_r;
        final int nc_g = Leux.click_gui.theme_frame_name_g;
        final int nc_b = Leux.click_gui.theme_frame_name_b;
        final int nc_a = Leux.click_gui.theme_frame_name_a;
        final int bg_r = Leux.click_gui.theme_frame_background_r;
        final int bg_g = Leux.click_gui.theme_frame_background_g;
        final int bg_b = Leux.click_gui.theme_frame_background_b;
        final int bg_a = Leux.click_gui.theme_frame_background_a;
        final int bd_r = Leux.click_gui.theme_frame_border_r;
        final int bd_g = Leux.click_gui.theme_frame_border_g;
        final int bd_b = Leux.click_gui.theme_frame_border_b;
        final int bd_a = border_a;
        this.frame_name = this.category.get_name();
        this.width_name = this.font.get_string_width(this.category.get_name());
        Draw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, bg_r, bg_g, bg_b, bg_a);
        final int border_size = 1;
        Draw.draw_rect(this.x - 1, this.y, this.width + 1, this.height, bd_r, bd_g, bd_b, bd_a, border_size, "left-right");
        Draw.draw_string(this.frame_name, this.x + 4, this.y + 4, nc_r, nc_g, nc_b, nc_a);
        if (this.is_moving()) {
            this.crush(mx, my);
        }
        for (final ModuleButton buttons : this.module_button) {
            buttons.set_x(this.x + 2);
            buttons.render(mx, my, 2);
        }
        final float[] array = tick_color;
        final int n = 0;
        ++array[n];
    }
}
