// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import me.sazked.leux.Leux;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventFirstPerson;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ItemRenderer.class })
public abstract class MixinItemRenderer
{
    private boolean injection;
    
    public MixinItemRenderer() {
        this.injection = true;
    }
    
    @Shadow
    public abstract void renderItemInFirstPerson(final AbstractClientPlayer p0, final float p1, final float p2, final EnumHand p3, final float p4, final ItemStack p5, final float p6);
    
    @Inject(method = { "transformSideFirstPerson" }, at = { @At("HEAD") })
    public void transformSideFirstPerson(final EnumHandSide hand, final float p_187459_2_, final CallbackInfo ci) {
        final EventFirstPerson event = new EventFirstPerson(hand);
        EventClientBus.EVENT_BUS.post(event);
    }
    
    @Inject(method = { "transformEatFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    public void transformEatFirstPerson(final float p_187454_1_, final EnumHandSide hand, final ItemStack stack, final CallbackInfo ci) {
        final EventFirstPerson event = new EventFirstPerson(hand);
        EventClientBus.EVENT_BUS.post(event);
        if (Leux.get_hack_manager().get_module_with_tag("CustomViewmodel").is_active() && Leux.get_setting_manager().get_setting_with_tag("CustomViewmodel", "NoEat").get_value(true)) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "transformFirstPerson" }, at = { @At("HEAD") })
    public void transformFirstPerson(final EnumHandSide hand, final float p_187453_2_, final CallbackInfo ci) {
        final EventFirstPerson event = new EventFirstPerson(hand);
        EventClientBus.EVENT_BUS.post(event);
    }
}
