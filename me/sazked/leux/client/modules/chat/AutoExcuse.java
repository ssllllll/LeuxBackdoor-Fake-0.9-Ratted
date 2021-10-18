// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import java.util.Random;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.modules.Module;

public class AutoExcuse extends Module
{
    public int diedTime;
    
    public AutoExcuse() {
        super(Category.chat);
        this.diedTime = 0;
        this.name = "Auto Excuse";
        this.tag = "AutoExcuse";
        this.description = "tell people why you died";
    }
    
    @Override
    public void update() {
        if (this.diedTime > 0) {
            --this.diedTime;
        }
        if (AutoExcuse.mc.player.isDead) {
            this.diedTime = 500;
        }
        if (!AutoExcuse.mc.player.isDead) {
            if (this.diedTime > 0) {
                final Random obf = new Random();
                final int obf2 = obf.nextInt(10) + 1;
                if (obf2 == 1) {
                    AutoExcuse.mc.player.sendChatMessage("U win bc u r a fucking ping player");
                }
                if (obf2 == 2) {
                    AutoExcuse.mc.player.sendChatMessage("LMAO luckiest player ever");
                }
                if (obf2 == 3) {
                    AutoExcuse.mc.player.sendChatMessage("Bruh, i was testing settings");
                }
                if (obf2 == 5) {
                    AutoExcuse.mc.player.sendChatMessage("Fucking pingspikes");
                }
                if (obf2 == 6) {
                    AutoExcuse.mc.player.sendChatMessage("Imagine saying ez");
                }
                if (obf2 == 7) {
                    AutoExcuse.mc.player.sendChatMessage("I have highping, stop saying ez");
                }
                if (obf2 == 8) {
                    AutoExcuse.mc.player.sendChatMessage("I was afk bruhh");
                }
                if (obf2 == 9) {
                    AutoExcuse.mc.player.sendChatMessage("Dont say ez, its cringe");
                }
                if (obf2 == 10) {
                    AutoExcuse.mc.player.sendChatMessage("Im so lagged :(");
                }
                this.diedTime = 0;
            }
        }
    }
}
