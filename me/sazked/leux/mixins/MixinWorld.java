// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventEntityRemoved;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ World.class })
public class MixinWorld
{
    @Inject(method = { "onEntityRemoved" }, at = { @At("HEAD") }, cancellable = true)
    public void onEntityRemoved(final Entity event_packet, final CallbackInfo p_Info) {
        final EventEntityRemoved l_Event = new EventEntityRemoved(event_packet);
        EventClientBus.EVENT_BUS.post(l_Event);
    }
}
