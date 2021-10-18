// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.manager;

import me.sazked.leux.client.modules.Module;
import java.util.Iterator;
import me.sazked.leux.client.guiscreen.settings.Setting;
import java.util.ArrayList;

public class SettingManager
{
    public ArrayList<Setting> array_setting;
    
    public Setting get_setting_with_tag(final String obf, final String obf) {
        Setting obf2 = null;
        for (final Setting obf3 : this.get_array_settings()) {
            if (obf3.get_master().get_tag().equalsIgnoreCase(obf) && obf3.get_tag().equalsIgnoreCase(obf)) {
                obf2 = obf3;
                return obf2;
            }
        }
        return obf2;
    }
    
    public SettingManager() {
        this.array_setting = new ArrayList<Setting>();
    }
    
    public ArrayList<Setting> get_array_settings() {
        return this.array_setting;
    }
    
    public void register(final Setting e) {
        this.array_setting.add(e);
    }
    
    public ArrayList<Setting> get_settings_with_hack(final Module obf) {
        final ArrayList<Setting> obf2 = new ArrayList<Setting>();
        for (final Setting obf3 : this.get_array_settings()) {
            if (obf3.get_master().equals(obf)) {
                obf2.add(obf3);
            }
        }
        return obf2;
    }
    
    public Setting get_setting_with_tag(final Module obf, final String obf) {
        Setting obf2 = null;
        for (final Setting obf3 : this.get_array_settings()) {
            if (obf3.get_master().equals(obf) && obf3.get_tag().equalsIgnoreCase(obf)) {
                obf2 = obf3;
            }
        }
        return obf2;
    }
}
