// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.ui;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.util.DiscordUtil;
import me.sazked.leux.client.modules.Module;

public class RichPresence extends Module
{
    @Override
    public void enable() {
        DiscordUtil.init();
    }
    
    public RichPresence() {
        super(Category.ui);
        this.name = "RPC";
        this.tag = "RichPresence";
        this.description = "shows discord rpc";
    }
    
    @Override
    public void disable() {
        DiscordUtil.shutdown();
    }
}
