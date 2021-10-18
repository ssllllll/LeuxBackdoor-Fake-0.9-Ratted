// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import me.sazked.leux.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.network.play.server.SPacketChat;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class BetterChat extends Module
{
    public Setting name_highlight;
    @EventHandler
    public Listener<EventPacket.ReceivePacket> PacketEvent;
    public Setting prefix;
    
    public BetterChat() {
        super(Category.chat);
        this.prefix = this.create("Prefix", "BCPrefix", true);
        this.name_highlight = this.create("Name Highlight", "BCNameHighlight", true);
        this.PacketEvent = new Listener<EventPacket.ReceivePacket>(this::lambda$new$0, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.name = "Better Chat";
        this.tag = "BetterChat";
        this.description = "credits to Sazked";
    }
    
    public void lambda$new$0(final EventPacket.ReceivePacket obf) {
        if (obf.get_packet() instanceof SPacketChat) {
            final SPacketChat obf2 = (SPacketChat)obf.get_packet();
            if (obf2.getChatComponent() instanceof TextComponentString) {
                final TextComponentString obf3 = (TextComponentString)obf2.getChatComponent();
                if (this.prefix.get_value(true)) {
                    obf3.text = "§9§l[§9Leux§l]§r " + obf3.text;
                }
                String obf4 = obf3.getFormattedText();
                if (this.name_highlight.get_value(true) && BetterChat.mc.player != null && obf4.contains(BetterChat.mc.player.getName())) {
                    obf4 = obf4.replaceAll("(?i)" + BetterChat.mc.player.getName(), ChatFormatting.DARK_GRAY + "<<" + ChatFormatting.LIGHT_PURPLE + BetterChat.mc.player.getName() + ChatFormatting.DARK_GRAY + ">>" + ChatFormatting.RESET);
                }
                obf.cancel();
                MessageUtil.client_message(obf4);
            }
        }
    }
}
