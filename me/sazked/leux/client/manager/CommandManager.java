// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.manager;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.Style;
import me.sazked.leux.client.command.Commands;

public class CommandManager
{
    public static Commands command_list;
    
    public static String get_prefix() {
        return CommandManager.command_list.get_prefix();
    }
    
    public static void set_prefix(final String obf) {
        CommandManager.command_list.set_prefix(obf);
    }
    
    public CommandManager() {
        CommandManager.command_list = new Commands(new Style().setColor(TextFormatting.BLUE));
    }
}
