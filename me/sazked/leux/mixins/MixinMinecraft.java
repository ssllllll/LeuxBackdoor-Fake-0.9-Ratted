// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import org.spongepowered.asm.mixin.injection.Redirect;
import me.sazked.leux.Leux;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import me.sazked.leux.client.event.EventClientBus;
import me.sazked.leux.client.event.events.EventGUIScreen;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public class MixinMinecraft
{
    @Shadow
    public EntityPlayerSP player;
    @Shadow
    public PlayerControllerMP playerController;
    
    @Inject(method = { "displayGuiScreen" }, at = { @At("HEAD") })
    private void displayGuiScreen(final GuiScreen guiScreenIn, final CallbackInfo info) {
        final EventGUIScreen guiscreen = new EventGUIScreen(guiScreenIn);
        EventClientBus.EVENT_BUS.post(guiscreen);
    }
    
    @Inject(method = { "shutdown" }, at = { @At("HEAD") })
    private void shutdown(final CallbackInfo info) {
        Leux.get_config_manager().save_settings();
    }
    
    @Redirect(method = { "sendClickBlockToController" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"))
    private boolean isHandActive(final EntityPlayerSP player) {
        return !Leux.get_hack_manager().get_module_with_tag("MultiTask").is_active() && this.player.isHandActive();
    }
    
    @Redirect(method = { "rightClickMouse" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z"))
    private boolean isHittingBlock(final PlayerControllerMP playerControllerMP) {
        return !Leux.get_hack_manager().get_module_with_tag("MultiTask").is_active() && this.playerController.getIsHittingBlock();
    }
}
