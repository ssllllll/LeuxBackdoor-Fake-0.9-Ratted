// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.renderer.Tessellator;
import me.sazked.leux.client.event.EventCancellable;

public class EventRender extends EventCancellable
{
    public Tessellator tessellator;
    public Vec3d render_pos;
    public ScaledResolution res;
    
    public BufferBuilder get_buffer_build() {
        return this.tessellator.getBuffer();
    }
    
    public EventRender(final Tessellator obf, final Vec3d obf) {
        this.res = new ScaledResolution(Minecraft.getMinecraft());
        this.tessellator = obf;
        this.render_pos = obf;
    }
    
    public Vec3d get_render_pos() {
        return this.render_pos;
    }
    
    public void set_translation(final Vec3d obf) {
        this.get_buffer_build().setTranslation(-obf.x, -obf.y, -obf.z);
    }
    
    public Tessellator get_tessellator() {
        return this.tessellator;
    }
    
    public void reset_translation() {
        this.set_translation(this.render_pos);
    }
    
    public double get_screen_width() {
        return this.res.getScaledWidth_double();
    }
    
    public double get_screen_height() {
        return this.res.getScaledHeight_double();
    }
}
