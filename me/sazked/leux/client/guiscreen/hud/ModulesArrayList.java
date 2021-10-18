// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.util.math.MathHelper;
import java.util.Iterator;
import me.sazked.leux.client.guiandfont.FontUtil;
import me.sazked.leux.client.guiscreen.render.Draw;
import me.sazked.leux.client.util.DrawnUtil;
import com.google.common.collect.Lists;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Comparator;
import me.sazked.leux.client.modules.Module;
import java.util.List;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class ModulesArrayList extends Pinnable
{
    boolean flag;
    private int scaled_width;
    private int scaled_height;
    private int scale_factor;
    
    public ModulesArrayList() {
        super("Array List", "ArrayList", 1.0f, 0, 0);
        this.flag = true;
    }
    
    @Override
    public void render() {
        this.updateResolution();
        int position_update_y = 2;
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        String string;
        List<Module> pretty_modules = Leux.get_hack_manager().get_array_active_hacks().stream().sorted(Comparator.comparing(modules -> {
            if (modules.array_detail() == null) {
                string = modules.get_tag();
            }
            else {
                string = modules.get_tag() + Leux.g + " [" + Leux.r + modules.array_detail() + Leux.g + "]" + Leux.r;
            }
            return Integer.valueOf(this.get(string, "width"));
        })).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
        int count = 0;
        if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top R") || Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top L")) {
            pretty_modules = (List<Module>)Lists.reverse((List)pretty_modules);
        }
        for (final Module modules2 : pretty_modules) {
            this.flag = true;
            if (!modules2.get_category().get_tag().equals("GUI")) {
                if (modules2.get_category().get_tag().equals("HUD")) {
                    continue;
                }
                for (final String s : DrawnUtil.hidden_tags) {
                    if (modules2.get_tag().equalsIgnoreCase(s)) {
                        this.flag = false;
                        break;
                    }
                    if (!this.flag) {
                        break;
                    }
                }
                if (!this.flag) {
                    continue;
                }
                final String module_name = (modules2.array_detail() == null) ? modules2.get_tag() : (modules2.get_tag() + Leux.g + " [" + Leux.r + modules2.array_detail() + Leux.g + "]" + Leux.r);
                if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Free")) {
                    this.create_line(module_name, this.docking(2, module_name), position_update_y, nl_r, nl_g, nl_b, nl_a);
                    position_update_y += this.get(module_name, "height") + 2;
                    if (this.get(module_name, "width") > this.get_width()) {
                        this.set_width(this.get(module_name, "width") + 2);
                    }
                    this.set_height(position_update_y);
                }
                else {
                    if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top R")) {
                        FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), module_name, this.scaled_width - 2 - this.mc.fontRenderer.getStringWidth(module_name), 3 + count * 10, new Draw.ClientColor(nl_r, nl_g, nl_b, nl_a).hex());
                        ++count;
                    }
                    if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top L")) {
                        FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), module_name, 2, 3 + count * 10, new Draw.ClientColor(nl_r, nl_g, nl_b, nl_a).hex());
                        ++count;
                    }
                    if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Bottom R")) {
                        FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), module_name, this.scaled_width - 2 - this.mc.fontRenderer.getStringWidth(module_name), this.scaled_height - count * 10, new Draw.ClientColor(nl_r, nl_g, nl_b, nl_a).hex());
                        ++count;
                    }
                    if (!Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Bottom L")) {
                        continue;
                    }
                    FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), module_name, 2, this.scaled_height - count * 10, new Draw.ClientColor(nl_r, nl_g, nl_b, nl_a).hex());
                    ++count;
                }
            }
        }
    }
    
    public void updateResolution() {
        this.scaled_width = this.mc.displayWidth;
        this.scaled_height = this.mc.displayHeight;
        this.scale_factor = 1;
        final boolean flag = this.mc.isUnicode();
        int i = this.mc.gameSettings.guiScale;
        if (i == 0) {
            i = 1000;
        }
        while (this.scale_factor < i && this.scaled_width / (this.scale_factor + 1) >= 320 && this.scaled_height / (this.scale_factor + 1) >= 240) {
            ++this.scale_factor;
        }
        if (flag && this.scale_factor % 2 != 0 && this.scale_factor != 1) {
            --this.scale_factor;
        }
        final double scaledWidthD = this.scaled_width / (double)this.scale_factor;
        final double scaledHeightD = this.scaled_height / (double)this.scale_factor;
        this.scaled_width = MathHelper.ceil(scaledWidthD);
        this.scaled_height = MathHelper.ceil(scaledHeightD);
    }
}
