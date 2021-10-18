// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import me.sazked.leux.Leux;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;

public class Settings extends Command
{
    public Settings() {
        super("settings", "To save/load settings.");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        String obf2 = "null";
        if (obf.length > 1) {
            obf2 = obf[1];
        }
        if (obf2.equals("null")) {
            MessageUtil.send_client_error_message(this.current_prefix() + "settings <save/load>");
            return true;
        }
        final ChatFormatting obf3 = ChatFormatting.GRAY;
        if (obf2.equalsIgnoreCase("save")) {
            Leux.get_config_manager().save_settings();
            MessageUtil.send_client_message(ChatFormatting.GREEN + "Successfully " + obf3 + "saved!");
        }
        else {
            if (!obf2.equalsIgnoreCase("load")) {
                MessageUtil.send_client_error_message(this.current_prefix() + "settings <save/load>");
                return true;
            }
            Leux.get_config_manager().load_settings();
            MessageUtil.send_client_message(ChatFormatting.GREEN + "Successfully " + obf3 + "loaded!");
        }
        return true;
    }
}
