// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import me.sazked.leux.client.manager.CommandManager;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;

public class Prefix extends Command
{
    public Prefix() {
        super("prefix", "Change prefix.");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        String obf2 = "null";
        if (obf.length > 1) {
            obf2 = obf[1];
        }
        if (obf.length > 2) {
            MessageUtil.send_client_error_message(this.current_prefix() + "prefix <character>");
            return true;
        }
        if (obf2.equals("null")) {
            MessageUtil.send_client_error_message(this.current_prefix() + "prefix <character>");
            return true;
        }
        CommandManager.set_prefix(obf2);
        MessageUtil.send_client_message("The new prefix is " + obf2);
        return true;
    }
}
