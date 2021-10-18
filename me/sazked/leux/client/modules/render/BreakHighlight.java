// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import me.sazked.turok.draw.RenderHelp;
import net.minecraft.init.Blocks;
import net.minecraft.client.renderer.DestroyBlockProgress;
import me.sazked.leux.client.modules.Category;
import java.util.function.BiConsumer;
import java.awt.Color;
import me.sazked.leux.client.event.events.EventRender;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class BreakHighlight extends Module
{
    public Setting mode;
    public boolean solid;
    public ArrayList<BlockPos> BlocksBeingBroken;
    public Setting b;
    public boolean outline;
    public Setting g;
    public Setting r;
    public Setting a;
    public int color_b;
    public Setting range;
    public int color_g;
    public Setting rgb;
    public Setting l_a;
    public int color_r;
    
    @Override
    public void render(final EventRender obf) {
        if (BreakHighlight.mc.player != null && BreakHighlight.mc.world != null) {
            final float[] obf2 = { System.currentTimeMillis() % ((long)(-1329041467) ^ 0xFFFFFFFFB0C842C5L) / Float.intBitsToFloat(Float.floatToIntBits(0.005504928f) ^ 0x7D8062AF) };
            final int obf3 = Color.HSBtoRGB(obf2[0], Float.intBitsToFloat(Float.floatToIntBits(10.282201f) ^ 0x7EA483E5), Float.intBitsToFloat(Float.floatToIntBits(4.9821754f) ^ 0x7F1F6DFB));
            if (this.rgb.get_value(true)) {
                this.color_r = (obf3 >> 16 & 0xFF);
                this.color_g = (obf3 >> 8 & 0xFF);
                this.color_b = (obf3 & 0xFF);
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
            BreakHighlight.mc.renderGlobal.damagedBlocks.forEach(this::lambda$render$0);
        }
    }
    
    public BreakHighlight() {
        super(Category.render);
        this.BlocksBeingBroken = new ArrayList<BlockPos>();
        this.mode = this.create("Mode", "HighlightMode", "Pretty", this.combobox("Pretty", "Solid", "Outline"));
        this.rgb = this.create("RGB Effect", "HighlightRGBEffect", true);
        this.r = this.create("R", "BreakR", 255, 0, 255);
        this.g = this.create("G", "BreakG", 255, 0, 255);
        this.b = this.create("B", "BreakB", 255, 0, 255);
        this.range = this.create("Range", "BreakRange", 20, 0, 100);
        this.a = this.create("A", "BreakA", 100, 0, 255);
        this.l_a = this.create("Outline A", "BreakLineA", 255, 0, 255);
        this.outline = false;
        this.solid = false;
        this.name = "Break Highlight";
        this.tag = "BreakHighlight";
        this.description = "Highlight blocks being broken & warns u when someone is mining your feet";
    }
    
    public void lambda$render$0(final Integer obf, final DestroyBlockProgress obf) {
        if (obf != null) {
            final BlockPos obf2 = obf.getPosition();
            if (BreakHighlight.mc.world.getBlockState(obf2).getBlock() == Blocks.AIR) {
                return;
            }
            if (obf2.getDistance((int)BreakHighlight.mc.player.posX, (int)BreakHighlight.mc.player.posY, (int)BreakHighlight.mc.player.posZ) <= this.range.get_value(1)) {
                if (this.solid) {
                    RenderHelp.prepare("quads");
                    RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)obf2.getX(), (float)obf2.getY(), (float)obf2.getZ(), Float.intBitsToFloat(Float.floatToIntBits(429.28903f) ^ 0x7C56A4FF), Float.intBitsToFloat(Float.floatToIntBits(14.235555f) ^ 0x7EE3C4D5), Float.intBitsToFloat(Float.floatToIntBits(8.8082f) ^ 0x7E8CEE63), this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
                    RenderHelp.release();
                }
                if (this.outline) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)obf2.getX(), (float)obf2.getY(), (float)obf2.getZ(), Float.intBitsToFloat(Float.floatToIntBits(31.663778f) ^ 0x7E7D4F6B), Float.intBitsToFloat(Float.floatToIntBits(7.6115203f) ^ 0x7F739193), Float.intBitsToFloat(Float.floatToIntBits(13.269698f) ^ 0x7ED450AF), this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.l_a.get_value(1), "all");
                    RenderHelp.release();
                }
            }
        }
    }
    
    @Override
    public void disable() {
        this.outline = false;
        this.solid = false;
    }
}
