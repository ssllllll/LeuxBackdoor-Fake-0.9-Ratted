// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import me.sazked.leux.client.modules.Module;
import org.lwjgl.input.Keyboard;
import me.sazked.leux.Leux;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;

public class Bind extends Command
{
    public Bind() {
        super("bind", "bind module to key");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        String obf2 = "null;";
        String obf3 = "null";
        if (obf.length == 3) {
            obf2 = obf[1].toUpperCase();
            obf3 = obf[2].toUpperCase();
        }
        else if (obf.length > 1) {
            MessageUtil.send_client_error_message(this.current_prefix() + "bind <ModuleTag> <key>");
            return true;
        }
        if (obf2.equals("null") || obf3.equals("null")) {
            MessageUtil.send_client_error_message(this.current_prefix() + "bind <ModuleTag> <key>");
            return true;
        }
        final Module obf4 = Leux.get_hack_manager().get_module_with_tag(obf2);
        if (obf4 == null) {
            MessageUtil.send_client_error_message("Module does not exist.");
            return true;
        }
        if (obf3.equalsIgnoreCase("NONE")) {
            obf4.set_bind(0);
            MessageUtil.send_client_message(obf4.get_tag() + " is bound to None.");
            return true;
        }
        final int obf5 = Keyboard.getKeyIndex(obf3.toUpperCase());
        if (obf5 == 0) {
            MessageUtil.send_client_error_message("Key does not exist.");
            return true;
        }
        if (obf5 == obf4.get_bind(0)) {
            MessageUtil.send_client_error_message(obf4.get_tag() + " is already bound to this key");
            return true;
        }
        obf4.set_bind(obf5);
        MessageUtil.send_client_message(obf4.get_tag() + " is bound to " + obf4.get_bind(""));
        return true;
    }
}
