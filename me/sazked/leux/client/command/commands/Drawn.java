// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import me.sazked.leux.client.util.DrawnUtil;
import me.sazked.leux.client.util.MessageUtil;
import java.util.Iterator;
import me.sazked.leux.client.modules.Module;
import me.sazked.leux.Leux;
import me.sazked.leux.client.command.Command;

public class Drawn extends Command
{
    public boolean is_module(final String anotherString) {
        final Iterator<Module> iterator = Leux.get_hack_manager().get_array_hacks().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().get_tag().equalsIgnoreCase(anotherString)) {
                return true;
            }
        }
        return false;
    }
    
    public Drawn() {
        super("drawn", "Hide elements of the array list");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        if (obf.length == 1) {
            MessageUtil.send_client_error_message("module name needed");
            return true;
        }
        if (obf.length == 2) {
            if (this.is_module(obf[1])) {
                DrawnUtil.add_remove_item(obf[1]);
                Leux.get_config_manager().save_settings();
            }
            else {
                MessageUtil.send_client_error_message("cannot find module by name: " + obf[1]);
            }
            return true;
        }
        return false;
    }
}
