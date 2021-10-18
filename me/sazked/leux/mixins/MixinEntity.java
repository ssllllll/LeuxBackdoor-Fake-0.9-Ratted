// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventEntity;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Entity.class })
public class MixinEntity
{
    @Shadow
    public double motionX;
    @Shadow
    public double motionY;
    @Shadow
    public double motionZ;
    
    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void velocity(final Entity entity, final double x, final double y, final double z) {
        final EventEntity.EventColision event = new EventEntity.EventColision(entity, x, y, z);
        EventClientBus.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            return;
        }
        entity.motionX += x;
        entity.motionY += y;
        entity.motionZ += z;
        entity.isAirBorne = true;
    }
    
    @Shadow
    public void move(final MoverType type, final double x, final double y, final double z) {
    }
}
