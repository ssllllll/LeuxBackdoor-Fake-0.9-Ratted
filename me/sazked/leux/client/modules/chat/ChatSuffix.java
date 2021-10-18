// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.network.play.client.CPacketChatMessage;
import java.util.Random;
import me.sazked.leux.Leux;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class ChatSuffix extends Module
{
    public boolean suffix_random;
    public String text_separator;
    public boolean suffix_obsidian;
    public boolean suffix_leux;
    public boolean accept_suffix;
    public Setting separator;
    public boolean suffix_2b2t;
    public Setting type;
    public StringBuilder suffix;
    @EventHandler
    public Listener<EventPacket.SendPacket> listener;
    public String[] random_client_name;
    public Setting ignore;
    public String[] random_client_finish;
    
    public String convert_base(final String s) {
        return Leux.smoth(s);
    }
    
    @Override
    public String array_detail() {
        return this.type.get_current_value();
    }
    
    public String random_string(final String[] obf) {
        return obf[new Random().nextInt(obf.length)];
    }
    
    public void lambda$new$0(final EventPacket.SendPacket obf) {
        if (!(obf.get_packet() instanceof CPacketChatMessage)) {
            return;
        }
        this.accept_suffix = true;
        final boolean obf2 = this.ignore.get_value(true);
        String obf3 = ((CPacketChatMessage)obf.get_packet()).getMessage();
        if (obf3.startsWith("/")) {
            if (obf2) {
                this.accept_suffix = false;
            }
        }
        if (obf3.startsWith("\\") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith("!") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith(":") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith(";") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith(".")) {
            if (obf2) {
                this.accept_suffix = false;
            }
        }
        if (obf3.startsWith(",") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith("@") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith("&") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith("*") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith("$")) {
            if (obf2) {
                this.accept_suffix = false;
            }
        }
        if (obf3.startsWith("#") && obf2) {
            this.accept_suffix = false;
        }
        if (obf3.startsWith("(")) {
            if (obf2) {
                this.accept_suffix = false;
            }
        }
        if (obf3.startsWith(")") && obf2) {
            this.accept_suffix = false;
        }
        if (this.separator.in("»")) {
            this.text_separator = "»";
        }
        if (this.separator.in("«")) {
            this.text_separator = "«";
        }
        if (this.separator.in("\u23d0")) {
            this.text_separator = "\u23d0";
        }
        if (this.separator.in("\u269d")) {
            this.text_separator = "\u269d";
        }
        if (this.separator.in("None")) {
            this.text_separator = "";
        }
        if (this.type.in("Leux")) {
            this.suffix_leux = true;
            this.suffix_obsidian = false;
            this.suffix_2b2t = false;
            this.suffix_random = false;
        }
        if (this.type.in("Obsidian")) {
            this.suffix_leux = false;
            this.suffix_obsidian = true;
            this.suffix_2b2t = false;
            this.suffix_random = false;
        }
        if (this.type.in("2B2T")) {
            this.suffix_leux = false;
            this.suffix_obsidian = false;
            this.suffix_2b2t = true;
            this.suffix_random = false;
        }
        if (this.type.in("Random")) {
            this.suffix_leux = false;
            this.suffix_obsidian = false;
            this.suffix_2b2t = false;
            this.suffix_random = true;
        }
        if (this.accept_suffix) {
            if (this.suffix_leux) {
                obf3 = obf3 + " " + this.text_separator + " " + "L\u03a3uxB\u03b1ckdoor";
            }
            if (this.suffix_obsidian) {
                obf3 = obf3 + " " + this.text_separator + " " + this.convert_base("obsidian+");
            }
            if (this.suffix_2b2t) {
                obf3 = obf3 + " " + this.text_separator + " " + "LeuxBackdoor";
            }
            if (this.suffix_random) {
                final StringBuilder obf4 = new StringBuilder();
                obf4.append(this.convert_base(this.random_string(this.random_client_name)));
                obf4.append(this.convert_base(this.random_string(this.random_client_finish)));
                obf3 = obf3 + " " + this.text_separator + " " + obf4.toString();
            }
            if (obf3.length() >= 256) {
                obf3.substring(0, 256);
            }
        }
        ((CPacketChatMessage)obf.get_packet()).message = obf3;
    }
    
    public ChatSuffix() {
        super(Category.misc);
        this.ignore = this.create("Ignore", "ChatSuffixIgnore", true);
        this.type = this.create("Type", "ChatSuffixType", "Leux", this.combobox("Leux", "Obsidian", "2B2T", "Random"));
        this.separator = this.create("Separator", "ChatSuffixSeparator", "None", this.combobox("»", "«", "\u23d0", "\u269d", "None"));
        this.suffix_2b2t = false;
        this.suffix_leux = false;
        this.suffix_random = false;
        this.suffix_obsidian = false;
        this.text_separator = "";
        this.random_client_name = new String[] { "leux", "tater", "luscius", "obsidian", "backdoor" };
        this.random_client_finish = new String[] { " plus", " luscius", "+", " bbcversion", " brrr", " antiniggers", " popper", " backdoor", " obsidian", " miner", " cold", " tater", " user" };
        this.listener = new Listener<EventPacket.SendPacket>(this::lambda$new$0, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Chat Suffix";
        this.tag = "ChatSuffix";
        this.description = "show off how cool u are";
    }
}
