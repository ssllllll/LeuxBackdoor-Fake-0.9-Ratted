// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event;

import me.sazked.leux.client.manager.CommandManager;
import net.minecraftforge.common.MinecraftForge;
import me.sazked.leux.client.manager.EventManager;

public class EventRegister
{
    public static void register_module_manager(final EventManager obf) {
        MinecraftForge.EVENT_BUS.register((Object)obf);
    }
    
    public static void register_command_manager(final CommandManager obf) {
        MinecraftForge.EVENT_BUS.register((Object)obf);
    }
}
