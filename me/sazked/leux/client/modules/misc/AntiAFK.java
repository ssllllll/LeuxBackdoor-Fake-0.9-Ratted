// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import me.zero.alpine.fork.listener.Listenable;
import me.sazked.leux.client.event.EventClientBus;
import java.util.Random;
import net.minecraft.util.EnumHand;
import me.zero.alpine.fork.listener.EventHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AntiAFK extends Module
{
    public Setting announce;
    @EventHandler
    public Listener<ClientChatReceivedEvent> listener;
    public static String lastcode;
    public int afk_delay;
    public Setting jump;
    public Setting swing;
    public Setting delay;
    
    @Override
    public void update() {
        ++this.afk_delay;
        if (this.afk_delay < this.delay.get_value(1) * 10) {
            return;
        }
        AntiAFK.lastcode = this.getRandomHexString(8);
        if (this.announce.get_value(true)) {
            AntiAFK.mc.player.sendChatMessage("Leux AntiAFK " + AntiAFK.lastcode);
        }
        if (this.jump.get_value(true)) {
            AntiAFK.mc.player.jump();
        }
        if (this.swing.get_value(true)) {
            AntiAFK.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        this.afk_delay = 0;
    }
    
    public String getRandomHexString(final int obf) {
        final Random obf2 = new Random();
        final StringBuilder obf3 = new StringBuilder();
        while (obf3.length() < obf) {
            obf3.append(Integer.toHexString(obf2.nextInt()));
        }
        return obf3.toString().substring(0, obf);
    }
    
    @Override
    public void disable() {
        EventClientBus.EVENT_BUS.unsubscribe(this);
    }
    
    public static void lambda$new$0(final ClientChatReceivedEvent obf) {
        if (obf.getMessage().getUnformattedText().contains(AntiAFK.lastcode)) {
            obf.setCanceled(true);
        }
    }
    
    @Override
    public void enable() {
        this.afk_delay = 0;
        EventClientBus.EVENT_BUS.subscribe(this);
    }
    
    public AntiAFK() {
        super(Category.misc);
        this.delay = this.create("Delay", "AntiAFKDelay", 20, 20, 240);
        this.announce = this.create("Announce", "AntiAFKAnnounce", true);
        this.jump = this.create("Jump", "AntiAFKJump", true);
        this.swing = this.create("Hand", "AntiAFKHand", true);
        this.listener = new Listener<ClientChatReceivedEvent>(AntiAFK::lambda$new$0, (Predicate<ClientChatReceivedEvent>[])new Predicate[0]);
        this.name = "Anti AFK";
        this.tag = "AntiAFK";
        this.description = "xd";
    }
}
