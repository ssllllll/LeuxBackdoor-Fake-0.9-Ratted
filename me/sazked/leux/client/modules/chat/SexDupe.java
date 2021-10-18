// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class SexDupe extends Module
{
    public Setting mode;
    
    public SexDupe() {
        super(Category.chat);
        this.mode = this.create("Mode", "Mode", "Sazked", this.combobox("Sazked", "House"));
        this.name = "SexDupe";
        this.tag = "SexDupe";
        this.description = "xd";
    }
    
    @Override
    public void enable() {
        if (this.mode.in("Sazked")) {
            SexDupe.mc.player.sendChatMessage("Sazked has doing sex dupe with Luscius and they got 10 shulkers per second 0//_//0");
        }
        if (this.mode.in("House")) {
            SexDupe.mc.player.sendChatMessage("I used the HouseHouseHause1 sex dupe and i got 10 shulkers per second 0//_//0");
        }
        this.set_disable();
    }
}
