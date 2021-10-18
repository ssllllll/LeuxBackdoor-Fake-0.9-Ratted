// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.util.SnappleFacts;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class FactSpammer extends Module
{
    public Setting delay;
    public long starttime;
    
    @Override
    public void update() {
        if (System.currentTimeMillis() - this.starttime >= this.delay.get_value(0) * 1000L) {
            FactSpammer.mc.player.sendChatMessage(SnappleFacts.randomfact());
            this.starttime = System.currentTimeMillis();
        }
    }
    
    @Override
    public void enable() {
        this.starttime = System.currentTimeMillis();
    }
    
    public FactSpammer() {
        super(Category.misc);
        this.starttime = ((long)(-931734139) ^ 0xFFFFFFFFC876DD85L);
        this.delay = this.create("Delay", "Delay", Double.longBitsToDouble(Double.doubleToLongBits(1.405261372852087) ^ 0x7FEA7BF3596BB55BL), Double.longBitsToDouble(Double.doubleToLongBits(0.7707816453222894) ^ 0x7FE8AA3E44E08591L), Double.longBitsToDouble(Double.doubleToLongBits(0.8542342597602842) ^ 0x7FA555E3161961CFL));
        this.name = "Fact Spammer";
        this.tag = "FactSpammer";
        this.description = "spams snapple facts in chat";
    }
}
