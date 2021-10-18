// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.event.EventClientBus;
import org.lwjgl.input.Keyboard;
import me.sazked.leux.client.event.events.EventRender;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.event.events.EventRenderEntityModel;
import net.minecraft.client.Minecraft;
import me.zero.alpine.fork.listener.Listenable;

public class Module implements Listenable
{
    public String name;
    public String tag;
    public boolean toggle_message;
    public Category category;
    public static Minecraft mc;
    public boolean widget_usage;
    public String description;
    public int bind;
    public boolean state_module;
    
    public void on_render_model(final EventRenderEntityModel obf) {
    }
    
    public Setting create(final String obf, final String obf, final boolean obf) {
        Leux.get_setting_manager().register(new Setting(this, obf, obf, obf));
        return Leux.get_setting_manager().get_setting_with_tag(this, obf);
    }
    
    public void render(final EventRender eventRender) {
    }
    
    public Module(final Category obf) {
        this.name = "";
        this.tag = "";
        this.description = "";
        this.bind = -1;
        this.toggle_message = true;
        this.widget_usage = false;
        this.category = obf;
    }
    
    public void update() {
    }
    
    public void render() {
    }
    
    public boolean is_active() {
        return this.state_module;
    }
    
    public Setting create(final String obf, final String obf, final double obf, final double obf, final double obf) {
        Leux.get_setting_manager().register(new Setting(this, obf, obf, obf, obf, obf));
        return Leux.get_setting_manager().get_setting_with_tag(this, obf);
    }
    
    public String get_name() {
        return this.name;
    }
    
    public void enable() {
    }
    
    public String getMeta() {
        return "";
    }
    
    public String array_detail() {
        return null;
    }
    
    public String get_bind(final String obf) {
        String obf2 = "null";
        if (this.get_bind(0) < 0) {
            obf2 = "NONE";
        }
        if (!obf2.equals("NONE")) {
            final String obf3 = Keyboard.getKeyName(this.get_bind(0));
            obf2 = Character.toUpperCase(obf3.charAt(0)) + ((obf3.length() != 1) ? obf3.substring(1).toLowerCase() : "");
        }
        else {
            obf2 = "None";
        }
        return obf2;
    }
    
    public String get_description() {
        return this.description;
    }
    
    public void toggle() {
        this.set_active(!this.is_active());
    }
    
    public boolean is_disabled() {
        return !this.is_active();
    }
    
    public boolean can_send_message_when_toggle() {
        return this.toggle_message;
    }
    
    public int get_bind(final int n) {
        return this.bind;
    }
    
    public void set_enable() {
        this.state_module = true;
        this.enable();
        EventClientBus.EVENT_BUS.subscribe(this);
    }
    
    public void set_if_can_send_message_toggle(final boolean toggle_message) {
        this.toggle_message = toggle_message;
    }
    
    public void set_active(final boolean b) {
        if (this.state_module != b) {
            if (b) {
                this.set_enable();
            }
            else {
                this.set_disable();
            }
        }
        if (!this.tag.equals("GUI") && !this.tag.equals("HUD") && this.toggle_message) {
            MessageUtil.toggle_message(this);
        }
    }
    
    public Category get_category() {
        return this.category;
    }
    
    public Setting create(final String obf, final String obf, final int obf, final int obf, final int obf) {
        Leux.get_setting_manager().register(new Setting(this, obf, obf, obf, obf, obf));
        return Leux.get_setting_manager().get_setting_with_tag(this, obf);
    }
    
    static {
        Module.mc = Minecraft.getMinecraft();
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public void set_bind(final int bind) {
        this.bind = bind;
    }
    
    public Setting create(final String obf, final String obf, final String obf, final List<String> obf) {
        Leux.get_setting_manager().register(new Setting(this, obf, obf, obf, obf));
        return Leux.get_setting_manager().get_setting_with_tag(this, obf);
    }
    
    public void disable() {
    }
    
    public boolean using_widget() {
        return this.widget_usage;
    }
    
    public Setting create(final String obf, final String obf, final String obf) {
        Leux.get_setting_manager().register(new Setting(this, obf, obf, obf));
        return Leux.get_setting_manager().get_setting_with_tag(this, obf);
    }
    
    public List<String> combobox(final String... obf) {
        return new ArrayList<String>(Arrays.asList(obf));
    }
    
    public void set_disable() {
        this.state_module = false;
        this.disable();
        EventClientBus.EVENT_BUS.unsubscribe(this);
    }
    
    public void event_widget() {
    }
}
