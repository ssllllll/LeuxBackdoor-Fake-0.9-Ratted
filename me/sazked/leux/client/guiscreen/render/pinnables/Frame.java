// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render.pinnables;

import java.awt.Color;
import java.util.Iterator;
import me.sazked.leux.Leux;
import net.minecraft.client.Minecraft;
import me.sazked.leux.client.guiscreen.render.Draw;
import java.util.ArrayList;

public class Frame
{
    private ArrayList<PinnableButton> pinnable_button;
    private String name;
    private String tag;
    private int x;
    private int y;
    private int move_x;
    private int move_y;
    private int width;
    private int height;
    private boolean move;
    private boolean can;
    private int border_size;
    private Draw font;
    public static int nc_r;
    public static int nc_g;
    public static int nc_b;
    public static int nc_a;
    public static int bg_r;
    public static int bg_g;
    public static int bg_b;
    public static int bg_a;
    public static int bd_r;
    public static int bd_g;
    public static int bd_b;
    public static int bd_a;
    public static int bdw_r;
    public static int bdw_g;
    public static int bdw_b;
    public static int bdw_a;
    public final Minecraft mc;
    
    public Frame(final String name, final String tag, final int initial_x, final int initial_y) {
        this.border_size = 2;
        this.font = new Draw(1.0f);
        this.mc = Minecraft.getMinecraft();
        this.pinnable_button = new ArrayList<PinnableButton>();
        this.name = name;
        this.tag = tag;
        this.x = initial_x;
        this.y = initial_y;
        this.move_x = 0;
        this.move_y = 0;
        this.width = 100;
        this.height = 25;
        this.can = true;
        final int size = Leux.get_hud_manager().get_array_huds().size();
        int count = 0;
        for (final Pinnable pinnables : Leux.get_hud_manager().get_array_huds()) {
            final PinnableButton pinnables_buttons = new PinnableButton(this, pinnables.get_title(), pinnables.get_tag());
            pinnables_buttons.set_y(this.height);
            this.pinnable_button.add(pinnables_buttons);
            if (++count >= size) {
                this.height += 5;
            }
            else {
                this.height += 12;
            }
        }
    }
    
    public void set_move(final boolean value) {
        this.move = value;
    }
    
    public void does_can(final boolean value) {
        this.can = value;
    }
    
    public void set_x(final int x) {
        this.x = x;
    }
    
    public void set_y(final int y) {
        this.y = y;
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
    
    public String get_name() {
        return this.name;
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public boolean is_moving() {
        return this.move;
    }
    
    public boolean can() {
        return this.can;
    }
    
    public int get_x() {
        return this.x;
    }
    
    public int get_y() {
        return this.y;
    }
    
    public int get_width() {
        return this.width;
    }
    
    public int get_height() {
        return this.height;
    }
    
    public boolean motion(final int mx, final int my) {
        return mx >= this.get_x() && my >= this.get_y() && mx <= this.get_x() + this.get_width() && my <= this.get_y() + this.get_height();
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
    
    public void mouse(final int mx, final int my, final int mouse) {
        for (final PinnableButton pinnables_buttons : this.pinnable_button) {
            pinnables_buttons.click(mx, my, mouse);
        }
    }
    
    public void release(final int mx, final int my, final int mouse) {
        for (final PinnableButton pinnables_buttons : this.pinnable_button) {
            pinnables_buttons.release(mx, my, mouse);
        }
        this.set_move(false);
    }
    
    public void render(final int mx, final int my, final int separate) {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        int color;
        final int color_b = color = Color.HSBtoRGB(tick_color[0], 1.0f, 1.0f);
        if (color_b <= 50) {
            color = 50;
        }
        else {
            color = Math.min(color_b, 120);
        }
        Frame.bd_a = color;
        Frame.bdw_a = 255;
        Draw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, Frame.bg_r, Frame.bg_g, Frame.bg_b, Frame.bg_a);
        Draw.draw_rect(this.x - 1, this.y, this.width + 1, this.height, Frame.bd_r, Frame.bd_g, Frame.bd_b, Frame.bd_a, this.border_size, "left-right");
        Draw.draw_string(this.name, this.x + 4, this.y + 4, Frame.nc_r, Frame.nc_g, Frame.nc_b, Frame.nc_a);
        if (this.is_moving()) {
            this.crush(mx, my);
        }
        for (final PinnableButton pinnables_buttons : this.pinnable_button) {
            pinnables_buttons.set_x(this.x + separate);
            pinnables_buttons.render(mx, my, separate);
            if (pinnables_buttons.motion(mx, my)) {
                Draw.draw_rect(this.get_x() - 1, pinnables_buttons.get_save_y(), this.get_width() + 1, pinnables_buttons.get_height(), Frame.bdw_r, Frame.bdw_g, Frame.bdw_b, Frame.bdw_a, this.border_size, "right-left");
            }
        }
    }
    
    static {
        Frame.nc_r = 0;
        Frame.nc_g = 0;
        Frame.nc_b = 0;
        Frame.nc_a = 0;
        Frame.bg_r = 0;
        Frame.bg_g = 0;
        Frame.bg_b = 0;
        Frame.bg_a = 0;
        Frame.bd_r = 0;
        Frame.bd_g = 0;
        Frame.bd_b = 0;
        Frame.bd_a = 0;
        Frame.bdw_r = 0;
        Frame.bdw_g = 0;
        Frame.bdw_b = 0;
        Frame.bdw_a = 255;
    }
}
