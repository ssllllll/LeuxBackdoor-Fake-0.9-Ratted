// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render.pinnables;

import me.sazked.leux.Leux;
import me.sazked.turok.draw.RenderHelp;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import me.sazked.leux.client.guiscreen.render.Draw;

public class Pinnable
{
    private String title;
    private String tag;
    private boolean state;
    private boolean move;
    public Draw font;
    private int x;
    private int y;
    private int width;
    private int height;
    private int move_x;
    private int move_y;
    private boolean dock;
    public final Minecraft mc;
    
    public Pinnable(final String title, final String tag, final float font_, final int x, final int y) {
        this.dock = true;
        this.mc = Minecraft.getMinecraft();
        this.title = title;
        this.tag = tag;
        this.font = new Draw(font_);
        this.x = x;
        this.y = y;
        this.width = 1;
        this.height = 10;
        this.move = false;
    }
    
    public void set_move(final boolean value) {
        this.move = value;
    }
    
    public void set_active(final boolean value) {
        this.state = value;
    }
    
    public void set_x(final int x) {
        this.x = x;
    }
    
    public void set_y(final int y) {
        this.y = y;
    }
    
    public void set_width(final int width) {
        this.width = width;
    }
    
    public void set_height(final int height) {
        this.height = height;
    }
    
    public void set_move_x(final int x) {
        this.move_x = x;
    }
    
    public void set_move_y(final int y) {
        this.move_y = y;
    }
    
    public void set_dock(final boolean value) {
        this.dock = value;
    }
    
    public boolean is_moving() {
        return this.move;
    }
    
    public String get_title() {
        return this.title;
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public int get_title_height() {
        return this.font.get_string_height();
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
    
    public boolean get_dock() {
        return this.dock;
    }
    
    public boolean is_active() {
        return this.state;
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
    
    public void render() {
    }
    
    public void click(final int mx, final int my, final int mouse) {
        if (mouse == 0 && this.is_active() && this.motion(mx, my)) {
            this.set_move(true);
            this.set_move_x(mx - this.get_x());
            this.set_move_y(my - this.get_y());
        }
    }
    
    public void release(final int mx, final int my, final int mouse) {
        this.set_move(false);
    }
    
    public void render(final int mx, final int my, final int tick) {
        if (this.is_moving()) {
            this.crush(mx, my);
        }
        if (this.x + this.width <= this.mc.displayWidth / 2 / 2) {
            this.set_dock(true);
        }
        else if (this.x + this.width >= this.mc.displayWidth / 2 / 2) {
            this.set_dock(false);
        }
        if (this.is_active()) {
            this.render();
            GL11.glPushMatrix();
            GL11.glEnable(3553);
            GL11.glEnable(3042);
            GlStateManager.enableBlend();
            GL11.glPopMatrix();
            RenderHelp.release_gl();
            if (this.motion(mx, my)) {
                Draw.draw_rect(this.x - 1, this.y - 1, this.width + 1, this.height + 1, 0, 0, 0, 90, 2, "right-left-down-up");
            }
        }
    }
    
    protected void create_line(final String string, final int pos_x, final int pos_y) {
        Draw.draw_string(string, this.x + pos_x, this.y + pos_y, 255, 255, 255, 255);
    }
    
    protected void create_line(final String string, final int pos_x, final int pos_y, final int r, final int g, final int b, final int a) {
        Draw.draw_string(string, this.x + pos_x, this.y + pos_y, r, g, b, a);
    }
    
    protected void create_rect(final int pos_x, final int pos_y, final int width, final int height, final int r, final int g, final int b, final int a) {
        Draw.draw_rect(this.x + pos_x, this.y + pos_y, this.x + width, this.y + height, r, g, b, a);
    }
    
    protected int get(final String string, final String type) {
        int value_to_request = 0;
        if (type.equals("width")) {
            value_to_request = this.font.get_string_width(string);
        }
        if (type.equals("height")) {
            value_to_request = this.font.get_string_height();
        }
        return value_to_request;
    }
    
    public int docking(final int position_x, final String string) {
        if (this.dock) {
            return position_x;
        }
        return this.width - this.get(string, "width") - position_x;
    }
    
    protected boolean is_on_gui() {
        return Leux.click_hud.on_gui;
    }
}
