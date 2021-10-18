// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.client.gui.GuiScreen;
import me.sazked.leux.client.event.EventCancellable;

public class EventGUIScreen extends EventCancellable
{
    public GuiScreen guiscreen;
    
    public GuiScreen get_guiscreen() {
        return this.guiscreen;
    }
    
    public EventGUIScreen(final GuiScreen obf) {
        this.guiscreen = obf;
    }
    
    public void set_screen(final GuiScreen obf) {
        this.guiscreen = this.guiscreen;
    }
    
    public static class Closed extends EventGUIScreen
    {
        public Closed(final GuiScreen obf) {
            super(obf);
        }
    }
    
    public static class Displayed extends EventGUIScreen
    {
        public Displayed(final GuiScreen obf) {
            super(obf);
        }
    }
}
