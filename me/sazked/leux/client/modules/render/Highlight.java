// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import net.minecraft.util.math.BlockPos;
import me.sazked.turok.draw.RenderHelp;
import net.minecraft.util.math.RayTraceResult;
import java.awt.Color;
import me.sazked.leux.client.event.events.EventRender;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Highlight extends Module
{
    public Setting rgb;
    public int color_b;
    public boolean solid;
    public Setting r;
    public Setting mode;
    public Setting a;
    public Setting b;
    public Setting l_a;
    public int color_r;
    public boolean outline;
    public Setting g;
    public int color_g;
    
    @Override
    public void disable() {
        this.outline = false;
        this.solid = false;
    }
    
    public Highlight() {
        super(Category.render);
        this.mode = this.create("Mode", "HighlightMode", "Pretty", this.combobox("Pretty", "Solid", "Outline"));
        this.rgb = this.create("RGB Effect", "HighlightRGBEffect", true);
        this.r = this.create("R", "HighlightR", 255, 0, 255);
        this.g = this.create("G", "HighlightG", 255, 0, 255);
        this.b = this.create("B", "HighlightB", 255, 0, 255);
        this.a = this.create("A", "HighlightA", 100, 0, 255);
        this.l_a = this.create("Outline A", "HighlightLineA", 255, 0, 255);
        this.outline = false;
        this.solid = false;
        this.name = "Block Highlight";
        this.tag = "BlockHighlight";
        this.description = "see what block ur targeting";
    }
    
    @Override
    public void render(final EventRender eventRender) {
        if (Highlight.mc.player != null && Highlight.mc.world != null) {
            final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 1.0f, 1.0f);
            if (this.rgb.get_value(true)) {
                this.color_r = (hsBtoRGB >> 16 & 0xFF);
                this.color_g = (hsBtoRGB >> 8 & 0xFF);
                this.color_b = (hsBtoRGB & 0xFF);
                this.r.set_value(this.color_r);
                this.g.set_value(this.color_g);
                this.b.set_value(this.color_b);
            }
            else {
                this.color_r = this.r.get_value(1);
                this.color_g = this.g.get_value(2);
                this.color_b = this.b.get_value(3);
            }
            if (this.mode.in("Pretty")) {
                this.outline = true;
                this.solid = true;
            }
            if (this.mode.in("Solid")) {
                this.outline = false;
                this.solid = true;
            }
            if (this.mode.in("Outline")) {
                this.outline = true;
                this.solid = false;
            }
            final RayTraceResult objectMouseOver = Highlight.mc.objectMouseOver;
            if (objectMouseOver != null && objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
                final BlockPos blockPos = objectMouseOver.getBlockPos();
                if (this.solid) {
                    RenderHelp.prepare("quads");
                    RenderHelp.draw_cube(blockPos, this.color_r, this.color_g, this.color_b, this.a.get_value(1), "all");
                    RenderHelp.release();
                }
                if (this.outline) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_cube_line(blockPos, this.color_r, this.color_g, this.color_b, this.l_a.get_value(1), "all");
                    RenderHelp.release();
                }
            }
        }
    }
}
