// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import me.sazked.leux.client.modules.Module;
import me.sazked.leux.Leux;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;

public class Alert extends Command
{
    public Alert() {
        super("alert", "if the module should spam chat or not");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        String obf2 = "null";
        String obf3 = "null";
        if (obf.length > 1) {
            obf2 = obf[1];
        }
        if (obf.length > 2) {
            obf3 = obf[2];
        }
        if (obf.length > 3) {
            MessageUtil.send_client_error_message(this.current_prefix() + "t <ModuleName> <True/On/False/Off>");
            return true;
        }
        if (obf2.equals("null")) {
            MessageUtil.send_client_error_message(this.current_prefix() + "t <ModuleName> <True/On/False/Off>");
            return true;
        }
        if (obf3.equals("null")) {
            MessageUtil.send_client_error_message(this.current_prefix() + "t <ModuleName> <True/On/False/Off>");
            return true;
        }
        obf2 = obf2.toLowerCase();
        obf3 = obf3.toLowerCase();
        final Module obf4 = Leux.get_hack_manager().get_module_with_tag(obf2);
        if (obf4 == null) {
            MessageUtil.send_client_error_message("This module does not exist.");
            return true;
        }
        boolean obf5 = true;
        if (obf3.equals("true") || obf3.equals("on")) {
            obf5 = true;
        }
        else {
            if (!obf3.equals("false") && !obf3.equals("off")) {
                MessageUtil.send_client_error_message("This value does not exist. <True/On/False/Off>");
                return true;
            }
            obf5 = false;
        }
        obf4.set_if_can_send_message_toggle(obf5);
        MessageUtil.send_client_message("The actual value of " + obf4.get_name() + " is " + Boolean.toString(obf4.can_send_message_when_toggle()) + ".");
        return true;
    }
}
