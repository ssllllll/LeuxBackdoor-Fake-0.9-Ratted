// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.MoverType;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventPlayerTravel;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayer.class })
public class MixinPlayer extends MixinEntity
{
    @Inject(method = { "travel" }, at = { @At("HEAD") }, cancellable = true)
    public void travel(final float strafe, final float vertical, final float forward, final CallbackInfo info) {
        final EventPlayerTravel event_packet = new EventPlayerTravel(strafe, vertical, forward);
        EventClientBus.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            info.cancel();
        }
    }
}
