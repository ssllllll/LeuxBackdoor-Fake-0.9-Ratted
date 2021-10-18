// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command;

import me.sazked.leux.client.manager.CommandManager;

public class Command
{
    public String description;
    public String name;
    
    public Command(final String obf, final String obf) {
        this.name = obf;
        this.description = obf;
    }
    
    public String current_prefix() {
        return CommandManager.get_prefix();
    }
    
    public String get_description() {
        return this.description;
    }
    
    public String get_name() {
        return this.name;
    }
    
    public boolean get_message(final String[] obf) {
        return false;
    }
}
