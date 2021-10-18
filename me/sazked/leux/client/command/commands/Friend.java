// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.command.commands;

import java.util.Iterator;
import me.sazked.leux.client.util.FriendUtil;
import me.sazked.leux.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.command.Command;

public class Friend extends Command
{
    public static ChatFormatting red;
    public static ChatFormatting green;
    public static ChatFormatting bold;
    public static ChatFormatting reset;
    
    @Override
    public boolean get_message(final String[] obf) {
        if (obf.length == 1) {
            MessageUtil.send_client_message("Add - add friend");
            MessageUtil.send_client_message("Del - delete friend");
            MessageUtil.send_client_message("List - list friends");
            return true;
        }
        if (obf.length != 2) {
            if (obf.length >= 3) {
                if (obf[1].equalsIgnoreCase("add")) {
                    if (FriendUtil.isFriend(obf[2])) {
                        MessageUtil.send_client_message("Player " + Friend.green + Friend.bold + obf[2] + Friend.reset + " is already your friend :D");
                        return true;
                    }
                    final FriendUtil.Friend obf2 = FriendUtil.get_friend_object(obf[2]);
                    if (obf2 == null) {
                        MessageUtil.send_client_error_message("Cannot find " + Friend.red + Friend.bold + "UUID" + Friend.reset + " for that player :(");
                        return true;
                    }
                    FriendUtil.friends.add(obf2);
                    MessageUtil.send_client_message("Player " + Friend.green + Friend.bold + obf[2] + Friend.reset + " is now your friend :D");
                    return true;
                }
                else if (obf[1].equalsIgnoreCase("del") || obf[1].equalsIgnoreCase("remove") || obf[1].equalsIgnoreCase("delete")) {
                    if (!FriendUtil.isFriend(obf[2])) {
                        MessageUtil.send_client_message("Player " + Friend.red + Friend.bold + obf[2] + Friend.reset + " is already not your friend :/");
                        return true;
                    }
                    final FriendUtil.Friend obf2 = FriendUtil.friends.stream().filter(Friend::lambda$get_message$0).findFirst().get();
                    FriendUtil.friends.remove(obf2);
                    MessageUtil.send_client_message("Player " + Friend.red + Friend.bold + obf[2] + Friend.reset + " is now not your friend :(");
                    return true;
                }
            }
            return true;
        }
        if (obf[1].equalsIgnoreCase("list")) {
            if (FriendUtil.friends.isEmpty()) {
                MessageUtil.send_client_message("You appear to have " + Friend.red + Friend.bold + "no" + Friend.reset + " friends :(");
            }
            else {
                for (final FriendUtil.Friend obf3 : FriendUtil.friends) {
                    MessageUtil.send_client_message("" + Friend.green + Friend.bold + obf3.getUsername());
                }
            }
            return true;
        }
        if (FriendUtil.isFriend(obf[1])) {
            MessageUtil.send_client_message("Player " + Friend.green + Friend.bold + obf[1] + Friend.reset + " is your friend :D");
            return true;
        }
        MessageUtil.send_client_error_message("Player " + Friend.red + Friend.bold + obf[1] + Friend.reset + " is not your friend :(");
        return true;
    }
    
    public static boolean lambda$get_message$0(final String[] obf, final FriendUtil.Friend obf) {
        return obf.getUsername().equalsIgnoreCase(obf[2]);
    }
    
    public Friend() {
        super("friend", "To add friends");
    }
    
    static {
        Friend.red = ChatFormatting.RED;
        Friend.green = ChatFormatting.GREEN;
        Friend.bold = ChatFormatting.BOLD;
        Friend.reset = ChatFormatting.RESET;
    }
}
