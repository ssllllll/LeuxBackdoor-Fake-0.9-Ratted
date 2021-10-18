// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.modules.Module;

public class FastSuicide extends Module
{
    public FastSuicide() {
        super(Category.chat);
        this.name = "Fast Suicide";
        this.tag = "FastSuicide";
        this.description = "be more fast";
    }
    
    @Override
    public void enable() {
        FastSuicide.mc.player.sendChatMessage("/kill");
        this.set_disable();
    }
}
