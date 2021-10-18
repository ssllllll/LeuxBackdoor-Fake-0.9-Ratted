// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import me.sazked.turok.draw.RenderHelp;
import me.sazked.leux.client.event.events.EventRender;
import java.util.ArrayList;
import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import me.sazked.leux.client.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class CityEsp extends Module
{
    public Setting a;
    public boolean solid;
    public Setting mode;
    public Setting g;
    public boolean outline;
    public Setting b;
    public List<BlockPos> blocks;
    public Setting endcrystal_mode;
    public Setting r;
    public Setting range;
    public Setting off_set;
    
    @Override
    public void update() {
        this.blocks.clear();
        for (final EntityPlayer obf : CityEsp.mc.world.playerEntities) {
            if (CityEsp.mc.player.getDistance((Entity)obf) <= this.range.get_value(1)) {
                if (CityEsp.mc.player == obf) {
                    continue;
                }
                final BlockPos is_cityable = EntityUtil.is_cityable(obf, this.endcrystal_mode.get_value(true));
                if (is_cityable == null) {
                    continue;
                }
                this.blocks.add(is_cityable);
            }
        }
    }
    
    public CityEsp() {
        super(Category.render);
        this.endcrystal_mode = this.create("EndCrystal", "CityEndCrystal", false);
        this.mode = this.create("Mode", "CityMode", "Pretty", this.combobox("Pretty", "Solid", "Outline"));
        this.off_set = this.create("Height", "CityOffSetSide", Double.longBitsToDouble(Double.doubleToLongBits(4.742096661880905) ^ 0x7FDB6E71B66D66A7L), Double.longBitsToDouble(Double.doubleToLongBits(1.625923978129899E308) ^ 0x7FECF1419E7A1D5EL), Double.longBitsToDouble(Double.doubleToLongBits(7.453510970974533) ^ 0x7FEDD0652E12D9EFL));
        this.range = this.create("Range", "CityRange", 6, 1, 12);
        this.r = this.create("R", "CityR", 0, 0, 255);
        this.g = this.create("G", "CityG", 255, 0, 255);
        this.b = this.create("B", "CityB", 0, 0, 255);
        this.a = this.create("A", "CityA", 50, 0, 255);
        this.blocks = new ArrayList<BlockPos>();
        this.outline = false;
        this.solid = false;
        this.name = "CityESP";
        this.tag = "CityESP";
        this.description = "jumpy isnt gonna be happy about this";
    }
    
    @Override
    public void render(final EventRender eventRender) {
        final float n = (float)this.off_set.get_value(1.0);
        for (final BlockPos blockPos : this.blocks) {
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
            if (this.solid) {
                RenderHelp.prepare("quads");
                RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, n, 1.0f, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
                RenderHelp.release();
            }
            if (this.outline) {
                RenderHelp.prepare("lines");
                RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, n, 1.0f, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
                RenderHelp.release();
            }
        }
    }
}
