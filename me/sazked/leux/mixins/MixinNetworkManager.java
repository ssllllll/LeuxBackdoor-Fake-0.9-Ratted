// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventPacket;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.Packet;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    private void receive(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callback) {
        final EventPacket event_packet = new EventPacket.ReceivePacket(packet);
        EventClientBus.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void send(final Packet<?> packet, final CallbackInfo callback) {
        final EventPacket event_packet = new EventPacket.SendPacket(packet);
        EventClientBus.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "exceptionCaught" }, at = { @At("HEAD") }, cancellable = true)
    private void exception(final ChannelHandlerContext exc, final Throwable exc_, final CallbackInfo callback) {
        if (exc_ instanceof Exception) {
            callback.cancel();
        }
    }
}
