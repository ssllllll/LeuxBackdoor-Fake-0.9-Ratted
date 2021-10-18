// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import java.util.Iterator;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.util.EnemyUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.command.Command;

public class Enemy extends Command
{
    public static ChatFormatting bold;
    public static ChatFormatting green;
    public static ChatFormatting red;
    public static ChatFormatting reset;
    
    public Enemy() {
        super("enemy", "To add enemy");
    }
    
    static {
        Enemy.red = ChatFormatting.GREEN;
        Enemy.green = ChatFormatting.RED;
        Enemy.bold = ChatFormatting.BOLD;
        Enemy.reset = ChatFormatting.RESET;
    }
    
    public static boolean lambda$get_message$0(final String[] obf, final EnemyUtil.Enemy obf) {
        return obf.getUsername().equalsIgnoreCase(obf[2]);
    }
    
    @Override
    public boolean get_message(final String[] obf) {
        if (obf.length == 1) {
            MessageUtil.send_client_message("Add - add enemy");
            MessageUtil.send_client_message("Del - delete enemy");
            MessageUtil.send_client_message("List - list enemies");
            return true;
        }
        if (obf.length != 2) {
            if (obf.length >= 3) {
                if (obf[1].equalsIgnoreCase("add")) {
                    if (EnemyUtil.isEnemy(obf[2])) {
                        MessageUtil.send_client_message("Player " + Enemy.green + Enemy.bold + obf[2] + Enemy.reset + " is already your Enemy D:");
                        return true;
                    }
                    final EnemyUtil.Enemy obf2 = EnemyUtil.get_enemy_object(obf[2]);
                    if (obf2 == null) {
                        MessageUtil.send_client_error_message("Cannot find " + Enemy.red + Enemy.bold + "UUID" + Enemy.reset + " for that player :(");
                        return true;
                    }
                    EnemyUtil.enemies.add(obf2);
                    MessageUtil.send_client_message("Player " + Enemy.green + Enemy.bold + obf[2] + Enemy.reset + " is now your Enemy D:");
                    return true;
                }
                else if (obf[1].equalsIgnoreCase("del") || obf[1].equalsIgnoreCase("remove") || obf[1].equalsIgnoreCase("delete")) {
                    if (!EnemyUtil.isEnemy(obf[2])) {
                        MessageUtil.send_client_message("Player " + Enemy.red + Enemy.bold + obf[2] + Enemy.reset + " is already not your Enemy :/");
                        return true;
                    }
                    final EnemyUtil.Enemy obf2 = EnemyUtil.enemies.stream().filter(Enemy::lambda$get_message$0).findFirst().get();
                    EnemyUtil.enemies.remove(obf2);
                    MessageUtil.send_client_message("Player " + Enemy.red + Enemy.bold + obf[2] + Enemy.reset + " is now not your Enemy :)");
                    return true;
                }
            }
            return true;
        }
        if (obf[1].equalsIgnoreCase("list")) {
            if (EnemyUtil.enemies.isEmpty()) {
                MessageUtil.send_client_message("You appear to have " + Enemy.red + Enemy.bold + "no" + Enemy.reset + " enemies :)");
            }
            else {
                for (final EnemyUtil.Enemy obf3 : EnemyUtil.enemies) {
                    MessageUtil.send_client_message("" + Enemy.green + Enemy.bold + obf3.getUsername());
                }
            }
            return true;
        }
        if (EnemyUtil.isEnemy(obf[1])) {
            MessageUtil.send_client_message("Player " + Enemy.green + Enemy.bold + obf[1] + Enemy.reset + " is your Enemy D:");
            return true;
        }
        MessageUtil.send_client_error_message("Player " + Enemy.red + Enemy.bold + obf[1] + Enemy.reset + " is not your Enemy :)");
        return true;
    }
}
