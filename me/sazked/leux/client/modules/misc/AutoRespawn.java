// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.client.gui.GuiScreen;
import me.sazked.leux.client.util.MessageUtil;
import net.minecraft.client.gui.GuiGameOver;
import me.zero.alpine.fork.listener.Listenable;
import me.sazked.leux.client.event.EventClientBus;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventGUIScreen;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoRespawn extends Module
{
    public Setting coords;
    @EventHandler
    public Listener<EventGUIScreen> listener;
    
    @Override
    public void disable() {
        EventClientBus.EVENT_BUS.unsubscribe(this);
    }
    
    @Override
    public void enable() {
        EventClientBus.EVENT_BUS.subscribe(this);
    }
    
    public void lambda$new$0(final EventGUIScreen eventGUIScreen) {
        if (eventGUIScreen.get_guiscreen() instanceof GuiGameOver) {
            if (this.coords.get_value(true)) {
                MessageUtil.send_client_message(String.format("You died at x%d y%d z%d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
            }
            if (AutoRespawn.mc.player != null) {
                AutoRespawn.mc.player.respawnPlayer();
            }
            AutoRespawn.mc.displayGuiScreen((GuiScreen)null);
            AutoRespawn.mc.player.noClip = true;
        }
    }
    
    public AutoRespawn() {
        super(Category.misc);
        this.coords = this.create("DeathCoords", "DeathCoords", true);
        this.listener = new Listener<EventGUIScreen>(this::lambda$new$0, (Predicate<EventGUIScreen>[])new Predicate[0]);
        this.name = "Auto Respawn";
        this.tag = "AutoRespawn";
        this.description = "Respawn Fast lol";
    }
}
