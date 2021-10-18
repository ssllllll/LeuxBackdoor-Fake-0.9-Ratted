// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import java.util.function.Predicate;
import java.util.ArrayList;
import me.sazked.leux.client.modules.Category;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import java.util.List;
import java.util.Random;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoRacist extends Module
{
    public Setting anti_nword;
    @EventHandler
    public Listener<EventPacket.SendPacket> listener;
    public CharSequence nigger;
    public Random r;
    public Setting delay;
    public int tick_delay;
    public Setting chanter;
    public List<String> chants;
    public String[] random_correction;
    public CharSequence nigga;
    
    @Override
    public void enable() {
        this.tick_delay = 0;
        this.chants.add("<player> nigger");
        this.chants.add("Luscius is god");
        this.chants.add("#LEUXMODE");
        this.chants.add("<player>, i have ur stash coords");
        this.chants.add("justice 4 ObsidianBreaker");
        this.chants.add("WarriorCrystal loves spaghetti");
        this.chants.add("stop being gay and join Leux");
        this.chants.add("imagine not being from Leux");
        this.chants.add("<player> join Leux and stop being a nn");
        this.chants.add("<player>, ur password has leaked");
        this.chants.add(":rolf:");
        this.chants.add("<player>, Sazked wants sex with you");
    }
    
    public void lambda$new$0(final EventPacket.SendPacket obf) {
        if (!(obf.get_packet() instanceof CPacketChatMessage)) {
            return;
        }
        if (this.anti_nword.get_value(true)) {
            String obf2 = ((CPacketChatMessage)obf.get_packet()).getMessage().toLowerCase();
            if (obf2.contains(this.nigger) || obf2.contains(this.nigga)) {
                final String obf3 = Integer.toString((int)AutoRacist.mc.player.posX);
                final String obf4 = Integer.toString((int)AutoRacist.mc.player.posZ);
                final String obf5 = obf3 + " " + obf4;
                obf2 = this.random_string(this.random_correction);
                AutoRacist.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("I am not nigger"));
            }
            ((CPacketChatMessage)obf.get_packet()).message = obf2;
        }
    }
    
    public String get_random_name() {
        final List playerEntities = AutoRacist.mc.world.playerEntities;
        return playerEntities.get(this.r.nextInt(playerEntities.size())).getName();
    }
    
    public String random_string(final String[] obf) {
        return obf[this.r.nextInt(obf.length)];
    }
    
    @Override
    public void update() {
        if (this.chanter.get_value(true)) {
            ++this.tick_delay;
            if (this.tick_delay < this.delay.get_value(1) * 10) {
                return;
            }
            final String s = this.chants.get(this.r.nextInt(this.chants.size()));
            final String get_random_name = this.get_random_name();
            if (get_random_name.equals(AutoRacist.mc.player.getName())) {
                return;
            }
            AutoRacist.mc.player.sendChatMessage(s.replace("<player>", get_random_name));
            this.tick_delay = 0;
        }
    }
    
    public AutoRacist() {
        super(Category.misc);
        this.delay = this.create("Delay", "AutoRacistDelay", 6, 0, 100);
        this.anti_nword = this.create("AntiNword", "AutoRacismAntiNword", false);
        this.chanter = this.create("Chanter", "AutoRacismChanter", true);
        this.chants = new ArrayList<String>();
        this.r = new Random();
        this.random_correction = new String[] { "Yuo jstu got nea nae'd by LuxBakdor", "LeuxBackdoor just stopped me from saying something racially incorrect!", "<Insert nword word here>", "Im an edgy teenager and saying the nword makes me feel empowered on the internet.", "My mom calls me a late bloomer", "I really do think im funny", "Niger is a great county, I do say so myself", "Mommy and daddy are wrestling in the bedroom again so im going to play minecraft until their done", "How do you open the impact GUI?", "What time does FitMC do his basehunting livestreams?" };
        this.nigger = "nigger";
        this.nigga = "nigga";
        this.listener = new Listener<EventPacket.SendPacket>(this::lambda$new$0, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Auto Racist";
        this.tag = "AutoRacist";
        this.description = "i love black squares (circles on the other hand...)";
    }
}
