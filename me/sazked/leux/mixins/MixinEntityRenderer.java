// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import org.spongepowered.asm.mixin.injection.Inject;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventSetupFog;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import me.sazked.leux.Leux;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityRenderer.class })
public class MixinEntityRenderer
{
    @Redirect(method = { "orientCamera" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"))
    public RayTraceResult orientCamera(final WorldClient world, final Vec3d start, final Vec3d end) {
        return (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "CameraClip").get_value(true)) ? null : world.rayTraceBlocks(start, end);
    }
    
    @Inject(method = { "setupFog" }, at = { @At("HEAD") }, cancellable = true)
    public void setupFog(final int startCoords, final float partialTicks, final CallbackInfo p_Info) {
        final EventSetupFog event = new EventSetupFog(startCoords, partialTicks);
        EventClientBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            return;
        }
    }
    
    @Inject(method = { "hurtCameraEffect" }, at = { @At("HEAD") }, cancellable = true)
    private void hurtCameraEffect(final float ticks, final CallbackInfo info) {
        if (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "NoHurtCam").get_value(true)) {
            info.cancel();
        }
    }
}
