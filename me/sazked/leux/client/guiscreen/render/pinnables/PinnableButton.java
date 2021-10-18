// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render.pinnables;

import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.Draw;

public class PinnableButton
{
    private Pinnable pinnable;
    private Frame master;
    private String name;
    private String tag;
    private int x;
    private int y;
    private int save_y;
    private int width;
    private int height;
    private boolean first;
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
    
    public PinnableButton(final Frame master, final String name, final String tag) {
        this.font = new Draw(1.0f);
        this.master = master;
        this.name = name;
        this.tag = tag;
        this.pinnable = Leux.get_hud_manager().get_pinnable_with_tag(tag);
        this.x = master.get_x();
        this.y = master.get_y();
        this.save_y = this.y;
        this.width = this.master.get_width();
        this.height = this.font.get_string_height();
        this.first = true;
    }
    
    public void set_x(final int x) {
        this.x = x;
    }
    
    public void set_y(final int y) {
        this.y = y;
    }
    
    public void set_save_y(final int y) {
        this.save_y = y;
    }
    
    public void set_width(final int width) {
        this.width = width;
    }
    
    public void set_height(final int height) {
        this.height = height;
    }
    
    public int get_x() {
        return this.x;
    }
    
    public int get_y() {
        return this.y;
    }
    
    public int get_save_y() {
        return this.save_y;
    }
    
    public int get_width() {
        return this.width;
    }
    
    public int get_height() {
        return this.height;
    }
    
    public boolean motion(final int mx, final int my, final int p_x, final int p_y, final int p_w, final int p_h) {
        return mx >= p_x && my >= p_y && mx <= p_x + p_w && my <= p_y + p_h;
    }
    
    public boolean motion(final int mx, final int my) {
        return mx >= this.get_x() && my >= this.get_save_y() && mx <= this.get_x() + this.get_width() && my <= this.get_save_y() + this.get_height();
    }
    
    public void click(final int mx, final int my, final int mouse) {
        this.pinnable.click(mx, my, mouse);
        if (mouse == 0 && this.motion(mx, my)) {
            this.master.does_can(false);
            this.pinnable.set_active(!this.pinnable.is_active());
        }
    }
    
    public void release(final int mx, final int my, final int mouse) {
        this.pinnable.release(mx, my, mouse);
        this.master.does_can(true);
    }
    
    public void render(final int mx, final int my, final int separate) {
        this.set_width(this.master.get_width() - separate);
        this.save_y = this.y + this.master.get_y() - 10;
        if (this.pinnable.is_active()) {
            Draw.draw_rect(this.x, this.save_y, this.x + this.width - separate, this.save_y + this.height, PinnableButton.bg_r, PinnableButton.bg_g, PinnableButton.bg_b, PinnableButton.bg_a);
            Draw.draw_string(this.pinnable.get_title(), this.x + separate, this.save_y, PinnableButton.nc_r, PinnableButton.nc_g, PinnableButton.nc_b, PinnableButton.nc_a);
        }
        else {
            Draw.draw_string(this.pinnable.get_title(), this.x + separate, this.save_y, PinnableButton.nc_r, PinnableButton.nc_g, PinnableButton.nc_b, PinnableButton.nc_a);
        }
        this.pinnable.render(mx, my, 0);
    }
    
    static {
        PinnableButton.nc_r = 0;
        PinnableButton.nc_g = 0;
        PinnableButton.nc_b = 0;
        PinnableButton.nc_a = 0;
        PinnableButton.bg_r = 0;
        PinnableButton.bg_g = 0;
        PinnableButton.bg_b = 0;
        PinnableButton.bg_a = 0;
        PinnableButton.bd_r = 0;
        PinnableButton.bd_g = 0;
        PinnableButton.bd_b = 0;
    }
}
