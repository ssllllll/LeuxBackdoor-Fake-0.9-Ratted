// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import me.sazked.leux.client.event.events.EventSwing;
import net.minecraft.util.EnumHand;
import me.sazked.leux.client.event.events.EventMotionUpdate;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventMove;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.MoverType;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayerSP.class })
public class MixinEntitySP extends MixinEntity
{
    @Inject(method = { "move" }, at = { @At("HEAD") }, cancellable = true)
    private void move(final MoverType type, final double x, final double y, final double z, final CallbackInfo info) {
        final EventMove event = new EventMove(type, x, y, z);
        EventClientBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            super.move(type, event.get_x(), event.get_y(), event.get_z());
            info.cancel();
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    public void OnPreUpdateWalkingPlayer(final CallbackInfo p_Info) {
        final EventMotionUpdate l_Event = new EventMotionUpdate(0);
        EventClientBus.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") }, cancellable = true)
    public void OnPostUpdateWalkingPlayer(final CallbackInfo p_Info) {
        final EventMotionUpdate l_Event = new EventMotionUpdate(1);
        EventClientBus.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "swingArm" }, at = { @At("RETURN") }, cancellable = true)
    public void swingArm(final EnumHand p_Hand, final CallbackInfo p_Info) {
        final EventSwing l_Event = new EventSwing(p_Hand);
        EventClientBus.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
}
