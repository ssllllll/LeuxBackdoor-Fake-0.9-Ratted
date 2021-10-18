// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.network.play.client.CPacketChatMessage;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class ChatColors extends Module
{
    public Setting ignore;
    @EventHandler
    public Listener<EventPacket.SendPacket> listener;
    public Setting color;
    
    public void lambda$new$0(final EventPacket.SendPacket obf) {
        if (!(obf.get_packet() instanceof CPacketChatMessage)) {
            return;
        }
        boolean obf2 = true;
        String obf3 = "";
        String obf4 = ((CPacketChatMessage)obf.get_packet()).getMessage();
        final boolean obf5 = this.ignore.get_value(true);
        if (obf4.startsWith("/") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith("\\") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith("!") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith(":")) {
            if (obf5) {
                obf2 = false;
            }
        }
        if (obf4.startsWith(";") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith(".") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith(",") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith("@") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith("&") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith("*") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith("$") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith("#") && obf5) {
            obf2 = false;
        }
        if (obf4.startsWith("(")) {
            if (obf5) {
                obf2 = false;
            }
        }
        if (obf4.startsWith(")") && obf5) {
            obf2 = false;
        }
        if (this.color.in("Green")) {
            obf3 = ">";
        }
        if (this.color.in("Yellow")) {
            obf3 = "#";
        }
        if (this.color.in("Blue")) {
            obf3 = "``";
        }
        if (this.color.in("None")) {
            obf3 = "";
        }
        if (obf2) {
            obf4 = obf3 + obf4;
        }
        ((CPacketChatMessage)obf.get_packet()).message = obf4;
    }
    
    public ChatColors() {
        super(Category.misc);
        this.color = this.create("Color", "ChatColor", "Green", this.combobox("Green", "Yellow", "Blue", "None"));
        this.ignore = this.create("Ignore", "ChatColorIgnore", true);
        this.listener = new Listener<EventPacket.SendPacket>(this::lambda$new$0, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Chat Colors";
        this.tag = "ChatColors";
        this.description = "brrr rainbow xd";
    }
}
