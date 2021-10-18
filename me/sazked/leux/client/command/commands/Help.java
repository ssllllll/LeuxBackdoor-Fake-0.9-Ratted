// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import java.util.Iterator;
import me.sazked.leux.client.command.Commands;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;

public class Help extends Command
{
    public Help() {
        super("help", "helps people");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        String obf2 = "null";
        if (obf.length == 1) {
            obf2 = "list";
        }
        if (obf.length > 1) {
            obf2 = obf[1];
        }
        if (obf.length > 2) {
            MessageUtil.send_client_error_message(this.current_prefix() + "help <List/NameCommand>");
            return true;
        }
        if (obf2.equals("null")) {
            MessageUtil.send_client_error_message(this.current_prefix() + "help <List/NameCommand>");
            return true;
        }
        if (obf2.equalsIgnoreCase("list")) {
            for (final Command obf3 : Commands.get_pure_command_list()) {
                MessageUtil.send_client_message(obf3.get_name());
            }
            return true;
        }
        final Command obf4 = Commands.get_command_with_name(obf2);
        if (obf4 == null) {
            MessageUtil.send_client_error_message("This command does not exist.");
            return true;
        }
        MessageUtil.send_client_message(obf4.get_name() + " - " + obf4.get_description());
        return true;
    }
}
