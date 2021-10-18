// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import me.sazked.leux.client.util.CapeUtil;
import me.sazked.leux.Leux;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import javax.annotation.Nullable;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.entity.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ AbstractClientPlayer.class })
public abstract class MixinAbstractClientPlayer
{
    @Shadow
    @Nullable
    protected abstract NetworkPlayerInfo getPlayerInfo();
    
    @Inject(method = { "getLocationCape" }, at = { @At("HEAD") }, cancellable = true)
    public void getLocationCape(final CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
        if (Leux.get_hack_manager().get_module_with_tag("Capes").is_active()) {
            final NetworkPlayerInfo info = this.getPlayerInfo();
            assert info != null;
            if (!CapeUtil.is_uuid_valid(info.getGameProfile().getId())) {
                return;
            }
            ResourceLocation r;
            if (Leux.get_setting_manager().get_setting_with_tag("Capes", "CapeCape").in("Obsidian")) {
                r = new ResourceLocation("custom/cape-obsidian.png");
            }
            else if (Leux.get_setting_manager().get_setting_with_tag("Capes", "CapeCape").in("LeuxNew")) {
                r = new ResourceLocation("custom/cape-leuxnew.png");
            }
            else {
                r = new ResourceLocation("custom/cape-leuxold.png");
            }
            callbackInfoReturnable.setReturnValue(r);
        }
    }
}
