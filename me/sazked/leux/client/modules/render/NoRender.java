// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import java.util.function.Consumer;
import net.minecraft.init.MobEffects;
import net.minecraftforge.common.MinecraftForge;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraft.entity.Entity;
import me.sazked.leux.Leux;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import me.sazked.leux.client.event.events.EventRenderArmorOverlay;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventSetupFog;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class NoRender extends Module
{
    public Setting nofog;
    public Setting weather;
    @EventHandler
    public Listener<EventSetupFog> setup_fog;
    @EventHandler
    public Listener<EventRenderArmorOverlay> eventarmor;
    @EventHandler
    public Listener<RenderBlockOverlayEvent> renderBlockOverlayEventListener;
    public Setting totem;
    public Setting noclip;
    public Setting sand;
    public Setting bossbar;
    public Setting blind;
    public Setting fire;
    public Setting potion;
    public Setting hurtcam;
    public Setting nausea;
    public Setting armor;
    
    public void lambda$new$4(final EventSetupFog obf) {
        if (this.nofog.get_value(true)) {
            obf.cancel();
            NoRender.mc.entityRenderer.setupFogColor(false);
            GlStateManager.glNormal3f(Float.intBitsToFloat(Float.floatToIntBits(5.07476E37f) ^ 0x7E18B687), Float.intBitsToFloat(Float.floatToIntBits(-4.390018f) ^ 0x7F0C7B07), Float.intBitsToFloat(Float.floatToIntBits(6.7984644E37f) ^ 0x7E4C9577));
            GlStateManager.color(Float.intBitsToFloat(Float.floatToIntBits(22.609022f) ^ 0x7E34DF47), Float.intBitsToFloat(Float.floatToIntBits(61.126644f) ^ 0x7DF481AF), Float.intBitsToFloat(Float.floatToIntBits(13.997235f) ^ 0x7EDFF4AD), Float.intBitsToFloat(Float.floatToIntBits(7.575268f) ^ 0x7F726898));
            GlStateManager.colorMaterial(1028, 4608);
        }
    }
    
    public void lambda$new$3(final EventRenderArmorOverlay obf) {
        if (this.armor.get_value(true) && obf.entity instanceof EntityPlayer) {
            obf.cancel();
        }
        if (Leux.get_module_manager().get_module_with_tag("Chams").is_active()) {
            this.armor.set_value(true);
        }
    }
    
    public static void lambda$update$1(final Entity entity) {
        NoRender.mc.world.removeEntity(entity);
    }
    
    @SubscribeEvent
    public void fog_density(final EntityViewRenderEvent.FogDensity fogDensity) {
        if (this.nofog.get_value(true)) {
            fogDensity.setDensity(0.0f);
            fogDensity.setCanceled(true);
        }
    }
    
    public static boolean lambda$update$0(final Entity entity) {
        return entity instanceof EntityFallingBlock;
    }
    
    public NoRender() {
        super(Category.render);
        this.armor = this.create("Armor", "Armor", true);
        this.fire = this.create("Fire", "Fire", true);
        this.hurtcam = this.create("HurtCam", "NoHurtCam", true);
        this.sand = this.create("Sand", "Sand", true);
        this.weather = this.create("Weather", "Weather", true);
        this.blind = this.create("Blind", "Blind", true);
        this.nausea = this.create("Nausea", "Nausea", true);
        this.nofog = this.create("No Fog", "NoFog", true);
        this.potion = this.create("Potion Icons", "NoPotionIcons", true);
        this.bossbar = this.create("BossBar", "BossBar", true);
        this.totem = this.create("TotemAnimation", "TotemAnimation", true);
        this.noclip = this.create("Camera Clip", "CameraClip", false);
        this.renderBlockOverlayEventListener = new Listener<RenderBlockOverlayEvent>(this::lambda$new$2, (Predicate<RenderBlockOverlayEvent>[])new Predicate[0]);
        this.eventarmor = new Listener<EventRenderArmorOverlay>(this::lambda$new$3, (Predicate<EventRenderArmorOverlay>[])new Predicate[0]);
        this.setup_fog = new Listener<EventSetupFog>(this::lambda$new$4, (Predicate<EventSetupFog>[])new Predicate[0]);
        this.name = "No Render";
        this.tag = "NoRender";
        this.description = "xd";
    }
    
    @Override
    public void enable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void lambda$new$2(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        if (this.fire.get_value(true)) {
            if (renderBlockOverlayEvent.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) {
                renderBlockOverlayEvent.setCanceled(true);
            }
        }
    }
    
    @Override
    public void update() {
        if (this.blind.get_value(true)) {
            if (NoRender.mc.player.isPotionActive(MobEffects.BLINDNESS)) {
                NoRender.mc.player.removePotionEffect(MobEffects.BLINDNESS);
            }
        }
        if (this.nausea.get_value(true) && NoRender.mc.player.isPotionActive(MobEffects.NAUSEA)) {
            NoRender.mc.player.removePotionEffect(MobEffects.NAUSEA);
        }
        if (this.sand.get_value(true)) {
            NoRender.mc.world.loadedEntityList.stream().filter(NoRender::lambda$update$0).forEach(NoRender::lambda$update$1);
        }
        if (this.weather.get_value(true)) {
            if (NoRender.mc.world == null) {
                return;
            }
            if (NoRender.mc.world.isRaining()) {
                NoRender.mc.world.setRainStrength(Float.intBitsToFloat(Float.floatToIntBits(1.248039E38f) ^ 0x7EBBC8B7));
            }
            if (NoRender.mc.world.isThundering()) {
                NoRender.mc.world.setThunderStrength(Float.intBitsToFloat(Float.floatToIntBits(2.1786192E38f) ^ 0x7F23E6AE));
            }
        }
    }
    
    @Override
    public void disable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
}
