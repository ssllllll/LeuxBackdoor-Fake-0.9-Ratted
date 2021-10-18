// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import me.sazked.leux.Leux;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;

public class Config extends Command
{
    public Config() {
        super("config", "changes which config is loaded");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        if (obf.length == 1) {
            MessageUtil.send_client_error_message("config needed");
            return true;
        }
        if (obf.length == 2) {
            final String obf2 = obf[1];
            if (Leux.get_config_manager().set_active_config_folder(obf2 + "/")) {
                MessageUtil.send_client_message("new config folder set as " + obf2);
            }
            else {
                MessageUtil.send_client_error_message("cannot set folder to " + obf2);
            }
            return true;
        }
        MessageUtil.send_client_error_message("config path may only be one word");
        return true;
    }
}
