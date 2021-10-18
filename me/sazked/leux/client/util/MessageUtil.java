// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.util.text.TextComponentBase;
import me.sazked.leux.client.modules.Module;
import me.sazked.leux.Leux;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.Minecraft;
import com.mojang.realmsclient.gui.ChatFormatting;

public class MessageUtil
{
    public static ChatFormatting r;
    public static Minecraft mc;
    public static ChatFormatting b;
    public static ChatFormatting g;
    public static ChatFormatting c;
    
    public static void client_message(final String obf) {
        if (MessageUtil.mc.player != null) {
            MessageUtil.mc.player.sendMessage((ITextComponent)new ChatMessage(obf));
        }
    }
    
    public static void client_message_simple(final String obf) {
        if (MessageUtil.mc.player != null) {
            final ITextComponent obf2 = new TextComponentString(obf).setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString("Client by ObsidianBreaker :3"))));
            MessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(obf2, 5936);
        }
    }
    
    public static void send_client_message_simple(final String obf) {
        if (Leux.get_setting_manager().get_setting_with_tag("Settings", "Client").in("Paranoia")) {
            client_message(ChatFormatting.DARK_PURPLE + "Para" + ChatFormatting.LIGHT_PURPLE + "noia" + MessageUtil.g + " >>> " + MessageUtil.r + obf);
        }
        else {
            client_message(MessageUtil.b + Leux.CLIENT_NAME + MessageUtil.g + " >>> " + MessageUtil.r + obf);
        }
    }
    
    public static void toggle_message(final Module obf) {
        if (Leux.get_setting_manager().get_setting_with_tag("Settings", "Client").in("Paranoia")) {
            if (obf.is_active()) {
                client_message_simple(ChatFormatting.DARK_PURPLE + "Para" + ChatFormatting.LIGHT_PURPLE + "noia" + MessageUtil.g + " >>> " + MessageUtil.r + obf.get_name() + ChatFormatting.GREEN + " Enabled");
            }
            else {
                client_message_simple(ChatFormatting.DARK_PURPLE + "Para" + ChatFormatting.LIGHT_PURPLE + "noia" + MessageUtil.g + " >>> " + MessageUtil.r + obf.get_name() + MessageUtil.c + " Disabled");
            }
        }
        else if (obf.is_active()) {
            client_message_simple(MessageUtil.b + Leux.CLIENT_NAME + MessageUtil.g + " >>> " + MessageUtil.r + obf.get_name() + ChatFormatting.GREEN + " Enabled");
        }
        else {
            client_message_simple(MessageUtil.b + Leux.CLIENT_NAME + MessageUtil.g + " >>> " + MessageUtil.r + obf.get_name() + MessageUtil.c + " Disabled");
        }
    }
    
    public static void send_client_error_message(final String obf) {
        if (Leux.get_setting_manager().get_setting_with_tag("Settings", "Client").in("Paranoia")) {
            client_message(ChatFormatting.DARK_RED + "Para" + ChatFormatting.RED + "noia" + MessageUtil.g + " >>> " + MessageUtil.r + obf);
        }
        else {
            client_message(MessageUtil.c + Leux.CLIENT_NAME + MessageUtil.g + " >>> " + MessageUtil.r + obf);
        }
    }
    
    public static void send_client_message(final String obf) {
        if (Leux.get_setting_manager().get_setting_with_tag("Settings", "Client").in("Paranoia")) {
            client_message(ChatFormatting.DARK_PURPLE + "Para" + ChatFormatting.LIGHT_PURPLE + "noia" + MessageUtil.g + " >>> " + MessageUtil.r + obf);
        }
        else {
            client_message(MessageUtil.b + Leux.CLIENT_NAME + MessageUtil.g + " >>> " + MessageUtil.r + obf);
        }
    }
    
    static {
        MessageUtil.mc = Minecraft.getMinecraft();
        MessageUtil.b = ChatFormatting.BLUE;
        MessageUtil.c = ChatFormatting.RED;
        MessageUtil.r = ChatFormatting.RESET;
        MessageUtil.g = ChatFormatting.GRAY;
    }
    
    public static class ChatMessage extends TextComponentBase
    {
        public String message_input;
        
        public String getUnformattedComponentText() {
            return this.message_input;
        }
        
        public ChatMessage(final String obf) {
            final Pattern obf2 = Pattern.compile("&[0123456789abcdefrlosmk]");
            final Matcher obf3 = obf2.matcher(obf);
            final StringBuffer obf4 = new StringBuffer();
            while (obf3.find()) {
                final String obf5 = "§" + obf3.group().substring(1);
                obf3.appendReplacement(obf4, obf5);
            }
            obf3.appendTail(obf4);
            this.message_input = obf4.toString();
        }
        
        public ITextComponent createCopy() {
            return (ITextComponent)new ChatMessage(this.message_input);
        }
    }
}
