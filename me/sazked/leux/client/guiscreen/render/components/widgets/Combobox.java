// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render.components.widgets;

import java.util.Iterator;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.Draw;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.guiscreen.render.components.ModuleButton;
import me.sazked.leux.client.guiscreen.render.components.Frame;
import java.util.ArrayList;
import me.sazked.leux.client.guiscreen.render.components.AbstractWidget;

public class Combobox extends AbstractWidget
{
    private ArrayList<String> values;
    private Frame frame;
    private ModuleButton master;
    private Setting setting;
    private String combobox_name;
    private int x;
    private int y;
    private int width;
    private int height;
    private int combobox_actual_value;
    private int save_y;
    private boolean can;
    private Draw font;
    private int border_size;
    
    public Combobox(final Frame frame, final ModuleButton master, final String tag, final int update_postion) {
        this.font = new Draw(1.0f);
        this.border_size = 0;
        this.values = new ArrayList<String>();
        this.frame = frame;
        this.master = master;
        this.setting = Leux.get_setting_manager().get_setting_with_tag(master.get_module(), tag);
        this.x = master.get_x();
        this.y = update_postion;
        this.save_y = this.y;
        this.width = master.get_width();
        this.height = this.font.get_string_height();
        this.combobox_name = this.setting.get_name();
        this.can = true;
        int count = 0;
        for (final String values : this.setting.get_values()) {
            this.values.add(values);
            ++count;
        }
        for (int i = 0; i >= this.values.size(); ++i) {
            if (this.values.get(i).equals(this.setting.get_current_value())) {
                this.combobox_actual_value = i;
                break;
            }
        }
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
            this.setting.set_current_value(this.values.get(this.combobox_actual_value));
            ++this.combobox_actual_value;
        }
    }
    
    @Override
    public void render(final int master_y, final int separe, final int absolute_x, final int absolute_y) {
        this.set_width(this.master.get_width() - separe);
        final String zbob = "me";
        this.save_y = this.y + master_y;
        final int ns_r = Leux.click_gui.theme_widget_name_r;
        final int ns_g = Leux.click_gui.theme_widget_name_g;
        final int ns_b = Leux.click_gui.theme_widget_name_b;
        final int ns_a = Leux.click_gui.theme_widget_name_b;
        final int bg_r = Leux.click_gui.theme_widget_background_r;
        final int bg_g = Leux.click_gui.theme_widget_background_g;
        final int bg_b = Leux.click_gui.theme_widget_background_b;
        final int bg_a = Leux.click_gui.theme_widget_background_a;
        final int bd_r = Leux.click_gui.theme_widget_border_r;
        final int bd_g = Leux.click_gui.theme_widget_border_g;
        final int bd_b = Leux.click_gui.theme_widget_border_b;
        final int bd_a = 100;
        Draw.draw_string(this.combobox_name + " " + this.setting.get_current_value(), this.x + 2, this.save_y, ns_r, ns_g, ns_b, ns_a);
        if (this.combobox_actual_value >= this.values.size()) {
            this.combobox_actual_value = 0;
        }
    }
}
