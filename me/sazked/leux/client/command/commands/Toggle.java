// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import me.sazked.leux.client.modules.Module;
import me.sazked.leux.Leux;
import me.sazked.leux.client.manager.CommandManager;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;

public class Toggle extends Command
{
    public Toggle() {
        super("t", "turn on and off stuffs");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        String obf2 = "null";
        if (obf.length > 1) {
            obf2 = obf[1];
        }
        if (obf.length > 2) {
            MessageUtil.send_client_error_message(this.current_prefix() + "t <ModuleNameNoSpace>");
            return true;
        }
        if (obf2.equals("null")) {
            MessageUtil.send_client_error_message(CommandManager.get_prefix() + "t <ModuleNameNoSpace>");
            return true;
        }
        final Module obf3 = Leux.get_module_manager().get_module_with_tag(obf2);
        if (obf3 != null) {
            obf3.toggle();
            MessageUtil.send_client_message("[" + obf3.get_tag() + "] - Toggled to " + obf3.is_active() + ".");
        }
        else {
            MessageUtil.send_client_error_message("Module does not exist.");
        }
        return true;
    }
}
