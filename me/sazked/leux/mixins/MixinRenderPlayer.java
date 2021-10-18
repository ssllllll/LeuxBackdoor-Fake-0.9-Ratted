// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventRenderName;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderPlayer.class })
public class MixinRenderPlayer
{
    @Inject(method = { "renderEntityName" }, at = { @At("HEAD") }, cancellable = true)
    public void renderLivingLabel(final AbstractClientPlayer entityIn, final double x, final double y, final double z, final String name, final double distanceSq, final CallbackInfo info) {
        final EventRenderName event_packet = new EventRenderName(entityIn, x, y, z, name, distanceSq);
        EventClientBus.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            info.cancel();
        }
    }
}
