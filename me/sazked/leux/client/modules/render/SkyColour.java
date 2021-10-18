// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import me.sazked.leux.client.modules.Category;
import net.minecraftforge.common.MinecraftForge;
import java.awt.Color;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class SkyColour extends Module
{
    public Setting rainbow_mode;
    public Setting g;
    public Setting r;
    public Setting b;
    
    public void cycle_rainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.r.set_value(hsBtoRGB >> 16 & 0xFF);
        this.g.set_value(hsBtoRGB >> 8 & 0xFF);
        this.b.set_value(hsBtoRGB & 0xFF);
    }
    
    @Override
    public void enable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public SkyColour() {
        super(Category.render);
        this.r = this.create("R", "SkyColourR", 255, 0, 255);
        this.g = this.create("G", "SkyColourG", 255, 0, 255);
        this.b = this.create("B", "SkyColourB", 255, 0, 255);
        this.rainbow_mode = this.create("Rainbow", "SkyColourRainbow", false);
        this.name = "Sky Colour";
        this.tag = "SkyColour";
        this.description = "Changes the sky's colour";
    }
    
    @Override
    public void disable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @Override
    public void update() {
        if (this.rainbow_mode.get_value(true)) {
            this.cycle_rainbow();
        }
    }
    
    @SubscribeEvent
    public void fog_colour(final EntityViewRenderEvent.FogColors obf) {
        obf.setRed(this.r.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.3113651f) ^ 0x7DE06B3F));
        obf.setGreen(this.g.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.012272502f) ^ 0x7F36129B));
        obf.setBlue(this.b.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.015051199f) ^ 0x7F09994E));
    }
    
    @SubscribeEvent
    public void fog_density(final EntityViewRenderEvent.FogDensity fogDensity) {
        fogDensity.setDensity(0.0f);
        fogDensity.setCanceled(true);
    }
}
