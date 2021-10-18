// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import me.sazked.leux.Leux;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.util.EzMessageUtil;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;

public class EzMessage extends Command
{
    public EzMessage() {
        super("ezmessage", "Set ez mode");
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        if (obf.length == 1) {
            MessageUtil.send_client_error_message("message needed");
            return true;
        }
        if (obf.length >= 2) {
            final StringBuilder obf2 = new StringBuilder();
            boolean obf3 = true;
            for (final String obf4 : obf) {
                if (obf3) {
                    obf3 = false;
                }
                else {
                    obf2.append(obf4).append(" ");
                }
            }
            EzMessageUtil.set_message(obf2.toString());
            MessageUtil.send_client_message("ez message changed to " + ChatFormatting.BOLD + obf2.toString());
            Leux.get_config_manager().save_settings();
            return true;
        }
        return false;
    }
}
