// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command;

import java.util.function.Function;
import java.util.Comparator;
import me.sazked.leux.client.command.commands.Config;
import me.sazked.leux.client.command.commands.Enemy;
import me.sazked.leux.client.command.commands.EzMessage;
import me.sazked.leux.client.command.commands.Drawn;
import me.sazked.leux.client.command.commands.Friend;
import me.sazked.leux.client.command.commands.Help;
import me.sazked.leux.client.command.commands.Alert;
import me.sazked.leux.client.command.commands.Toggle;
import me.sazked.leux.client.command.commands.Settings;
import me.sazked.leux.client.command.commands.Prefix;
import me.sazked.leux.client.command.commands.Bind;
import net.minecraft.util.text.Style;
import java.util.HashMap;
import java.util.ArrayList;
import me.sazked.turok.values.TurokString;

public class Commands
{
    public static TurokString prefix;
    public static ArrayList<Command> command_list;
    public static HashMap<String, Command> list_command;
    public Style style;
    
    public static void add_command(final Command obf) {
        Commands.command_list.add(obf);
        Commands.list_command.put(obf.get_name().toLowerCase(), obf);
    }
    
    public static ArrayList<Command> get_pure_command_list() {
        return Commands.command_list;
    }
    
    public void set_prefix(final String obf) {
        Commands.prefix.set_value(obf);
    }
    
    public String get_prefix() {
        return Commands.prefix.get_value();
    }
    
    static {
        Commands.command_list = new ArrayList<Command>();
        Commands.list_command = new HashMap<String, Command>();
        Commands.prefix = new TurokString("Prefix", "Prefix", "!");
    }
    
    public Commands(final Style obf) {
        this.style = obf;
        add_command(new Bind());
        add_command(new Prefix());
        add_command(new Settings());
        add_command(new Toggle());
        add_command(new Alert());
        add_command(new Help());
        add_command(new Friend());
        add_command(new Drawn());
        add_command(new EzMessage());
        add_command(new Enemy());
        add_command(new Config());
        Commands.command_list.sort(Comparator.comparing((Function<? super Command, ? extends Comparable>)Command::get_name));
    }
    
    public static Command get_command_with_name(final String obf) {
        return Commands.list_command.get(obf.toLowerCase());
    }
    
    public String[] get_message(final String obf) {
        String[] obf2 = new String[0];
        if (this.has_prefix(obf)) {
            obf2 = obf.replaceFirst(Commands.prefix.get_value(), "").split(" ");
        }
        return obf2;
    }
    
    public boolean has_prefix(final String s) {
        return s.startsWith(Commands.prefix.get_value());
    }
}
