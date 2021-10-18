// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import java.awt.Color;
import me.sazked.turok.draw.RenderHelp;
import me.sazked.leux.client.event.events.EventRender;
import java.util.HashMap;
import me.sazked.leux.client.modules.Category;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3i;
import java.util.Map;
import net.minecraft.util.math.BlockPos;
import me.sazked.leux.client.util.Pair;
import java.util.ArrayList;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class HoleESP extends Module
{
    public Setting line_a;
    public int color_b_o;
    public Setting gb;
    public Setting bedrock_enable;
    public Setting go;
    public boolean glow;
    public ArrayList<Pair<BlockPos, Boolean>> holes;
    public int color_g;
    public Setting rb;
    public Setting ro;
    public int color_g_o;
    public Setting ab;
    public int color_a;
    public Setting ao;
    public Setting mode;
    public Setting hide_own;
    public boolean outline;
    public int color_r;
    public ArrayList<Pair<BlockPos, Boolean>> dual_holes;
    public int color_b_b;
    public int color_b;
    public int color_g_b;
    public Map<BlockPos, Integer> dual_hole_sides;
    public Setting bo;
    public Setting obsidian_enable;
    public Setting range;
    public Setting bb;
    public Setting dual_enable;
    public int color_r_b;
    public boolean glowOutline;
    public int color_r_o;
    public Setting off_set;
    public boolean solid;
    public int safe_sides;
    
    public boolean checkDual(final BlockPos obf, final int obf) {
        int obf2 = -1;
        for (final BlockPos obf3 : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
            ++obf2;
            if (obf != this.oppositeIntOrient(obf2)) {
                final Block obf4 = HoleESP.mc.world.getBlockState(obf.add((Vec3i)obf3)).getBlock();
                if (obf4 != Blocks.BEDROCK) {
                    if (obf4 != Blocks.OBSIDIAN && obf4 != Blocks.ENDER_CHEST && obf4 != Blocks.ANVIL) {
                        return false;
                    }
                }
                if (obf4 == Blocks.BEDROCK) {
                    ++this.safe_sides;
                }
            }
        }
        return true;
    }
    
    public BlockPos player_as_blockpos() {
        return new BlockPos(Math.floor(HoleESP.mc.player.posX), Math.floor(HoleESP.mc.player.posY), Math.floor(HoleESP.mc.player.posZ));
    }
    
    public String getDirectionsToRenderQuad(final BlockPos blockPos) {
        switch (this.dual_hole_sides.get(blockPos)) {
            case 1: {
                return "east-south-west-top-bottom";
            }
            case 2: {
                return "north-south-west-top-bottom";
            }
            case 3: {
                return "north-east-west-top-bottom";
            }
            case 4: {
                return "north-east-south-top-bottom";
            }
            default: {
                return "all";
            }
        }
    }
    
    public List<BlockPos> sphere(final BlockPos obf, final float obf) {
        final int obf2 = 0;
        final List<BlockPos> obf3 = new ArrayList<BlockPos>();
        final int obf4 = obf.getX();
        final int obf5 = obf.getY();
        final int obf6 = obf.getZ();
        for (int obf7 = obf4 - (int)obf; obf7 <= obf4 + obf; ++obf7) {
            for (int obf8 = obf6 - (int)obf; obf8 <= obf6 + obf; ++obf8) {
                for (int obf9 = obf5 - (int)obf; obf9 < obf5 + obf; ++obf9) {
                    final double obf10 = (obf4 - obf7) * (obf4 - obf7) + (obf6 - obf8) * (obf6 - obf8) + (obf5 - obf9) * (obf5 - obf9);
                    if (obf10 < obf * obf) {
                        final BlockPos obf11 = new BlockPos(obf7, obf9 + obf2, obf8);
                        obf3.add(obf11);
                    }
                }
            }
        }
        return obf3;
    }
    
    public HoleESP() {
        super(Category.render);
        this.mode = this.create("Mode", "HoleESPMode", "Pretty", this.combobox("Pretty", "Solid", "Outline", "Glow", "Glow 2"));
        this.off_set = this.create("Height", "HoleESPOffSetSide", Double.longBitsToDouble(Double.doubleToLongBits(8.662970961713492) ^ 0x7FD15370EE0D83A5L), Double.longBitsToDouble(Double.doubleToLongBits(2.887784584103974E307) ^ 0x7FC48FCC252C7087L), Double.longBitsToDouble(Double.doubleToLongBits(4128.690364338457) ^ 0x7F4020B0BBB79FFFL));
        this.range = this.create("Range", "HoleESPRange", 8, 1, 12);
        this.hide_own = this.create("Hide Own", "HoleESPHideOwn", false);
        this.dual_enable = this.create("Dual holes", "HoleESPDualHoles", true);
        this.bedrock_enable = this.create("Bedrock Holes", "HoleESPBedrockHoles", true);
        this.rb = this.create("R", "HoleESPRb", 50, 0, 255);
        this.gb = this.create("G", "HoleESPGb", 210, 0, 255);
        this.bb = this.create("B", "HoleESPBb", 80, 0, 255);
        this.ab = this.create("A", "HoleESPAb", 30, 0, 255);
        this.obsidian_enable = this.create("Obsidian Holes", "HoleESPObsidianHoles", true);
        this.ro = this.create("R", "HoleESPRo", 230, 0, 255);
        this.go = this.create("G", "HoleESPGo", 20, 0, 255);
        this.bo = this.create("B", "HoleESPBo", 20, 0, 255);
        this.ao = this.create("A", "HoleESPAo", 30, 0, 255);
        this.line_a = this.create("Outline A", "HoleESPLineOutlineA", 60, 0, 255);
        this.holes = new ArrayList<Pair<BlockPos, Boolean>>();
        this.dual_holes = new ArrayList<Pair<BlockPos, Boolean>>();
        this.dual_hole_sides = new HashMap<BlockPos, Integer>();
        this.outline = false;
        this.solid = false;
        this.glow = false;
        this.glowOutline = false;
        this.name = "Hole ESP";
        this.tag = "HoleESP";
        this.description = "lets you know where holes are";
    }
    
    @Override
    public void render(final EventRender obf) {
        if (!this.holes.isEmpty() || !this.dual_holes.isEmpty()) {
            final float obf2 = (float)this.off_set.get_value(Double.longBitsToDouble(Double.doubleToLongBits(5.149063722592224) ^ 0x7FE498A4291636E8L));
            for (final Pair<BlockPos, Boolean> obf3 : this.holes) {
                if (obf3.getValue()) {
                    this.color_r = this.color_r_b;
                    this.color_g = this.color_g_b;
                    this.color_b = this.color_b_b;
                    this.color_a = this.ab.get_value(1);
                }
                else {
                    if (obf3.getValue()) {
                        continue;
                    }
                    this.color_r = this.color_r_o;
                    this.color_g = this.color_g_o;
                    this.color_b = this.color_b_o;
                    this.color_a = this.ao.get_value(1);
                }
                if (this.hide_own.get_value(true)) {
                    if (obf3.getKey().equals((Object)new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ))) {
                        continue;
                    }
                }
                if (this.solid) {
                    RenderHelp.prepare("quads");
                    RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)obf3.getKey().getX(), (float)obf3.getKey().getY(), (float)obf3.getKey().getZ(), Float.intBitsToFloat(Float.floatToIntBits(8.251174f) ^ 0x7E8404CF), obf2, Float.intBitsToFloat(Float.floatToIntBits(4.1570535f) ^ 0x7F050695), this.color_r, this.color_g, this.color_b, this.color_a, "all");
                    RenderHelp.release();
                }
                if (this.outline) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)obf3.getKey().getX(), (float)obf3.getKey().getY(), (float)obf3.getKey().getZ(), Float.intBitsToFloat(Float.floatToIntBits(5.4297266f) ^ 0x7F2DC052), obf2, Float.intBitsToFloat(Float.floatToIntBits(7.5087686f) ^ 0x7F7047D5), this.color_r, this.color_g, this.color_b, this.line_a.get_value(1), "all");
                    RenderHelp.release();
                }
                if (this.glow) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)obf3.getKey().getX(), (float)obf3.getKey().getY(), (float)obf3.getKey().getZ(), Float.intBitsToFloat(Float.floatToIntBits(876.9062f) ^ 0x7BDB39FF), Float.intBitsToFloat(Float.floatToIntBits(1.6190102E38f) ^ 0x7EF39A01), Float.intBitsToFloat(Float.floatToIntBits(14.342633f) ^ 0x7EE57B6D), this.color_r, this.color_g, this.color_b, this.line_a.get_value(1), "all");
                    RenderHelp.release();
                    RenderHelp.prepare("quads");
                    RenderHelp.draw_gradiant_cube(RenderHelp.get_buffer_build(), (float)obf3.getKey().getX(), (float)obf3.getKey().getY(), (float)obf3.getKey().getZ(), Float.intBitsToFloat(Float.floatToIntBits(22.08467f) ^ 0x7E30AD67), obf2, Float.intBitsToFloat(Float.floatToIntBits(6.8953414f) ^ 0x7F5CA6A3), new Color(this.color_r, this.color_g, this.color_b, this.color_a), new Color(0, 0, 0, 0), "all");
                    RenderHelp.release();
                }
                if (this.glowOutline) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_gradiant_outline(RenderHelp.get_buffer_build(), obf3.getKey().getX(), obf3.getKey().getY(), obf3.getKey().getZ(), obf2, new Color(this.color_r, this.color_g, this.color_b, this.line_a.get_value(1)), new Color(0, 0, 0, 0), "all");
                    RenderHelp.release();
                }
            }
            for (final Pair<BlockPos, Boolean> obf3 : this.dual_holes) {
                final BlockPos obf4 = new BlockPos((Entity)HoleESP.mc.player);
                if (this.hide_own.get_value(true)) {
                    if (obf3.getKey().equals((Object)new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ))) {
                        continue;
                    }
                    if (obf3.getKey().equals((Object)obf4.add((Vec3i)this.orientConv(this.oppositeIntOrient(this.dual_hole_sides.get(obf3.getKey())))))) {
                        continue;
                    }
                }
                if (obf3.getValue()) {
                    this.color_r = this.color_r_b;
                    this.color_g = this.color_g_b;
                    this.color_b = this.color_b_b;
                    this.color_a = this.ab.get_value(1);
                }
                else {
                    if (obf3.getValue()) {
                        continue;
                    }
                    this.color_r = this.color_r_o;
                    this.color_g = this.color_g_o;
                    this.color_b = this.color_b_o;
                    this.color_a = this.ao.get_value(1);
                }
                if (this.solid) {
                    RenderHelp.prepare("quads");
                    RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)obf3.getKey().getX(), (float)obf3.getKey().getY(), (float)obf3.getKey().getZ(), Float.intBitsToFloat(Float.floatToIntBits(9.204156f) ^ 0x7E934439), obf2, Float.intBitsToFloat(Float.floatToIntBits(5.583637f) ^ 0x7F32AD28), this.color_r, this.color_g, this.color_b, this.color_a, this.getDirectionsToRenderQuad(obf3.getKey()));
                    RenderHelp.release();
                }
                if (this.outline) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)obf3.getKey().getX(), (float)obf3.getKey().getY(), (float)obf3.getKey().getZ(), Float.intBitsToFloat(Float.floatToIntBits(15.469209f) ^ 0x7EF781E1), obf2, Float.intBitsToFloat(Float.floatToIntBits(7.7381964f) ^ 0x7F779F4E), this.color_r, this.color_g, this.color_b, this.line_a.get_value(1), this.getDirectionsToRenderOutline(obf3.getKey()));
                    RenderHelp.release();
                }
                if (this.glow) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)obf3.getKey().getX(), (float)obf3.getKey().getY(), (float)obf3.getKey().getZ(), Float.intBitsToFloat(Float.floatToIntBits(7.8765364f) ^ 0x7F7C0C96), Float.intBitsToFloat(Float.floatToIntBits(1.733926E38f) ^ 0x7F027233), Float.intBitsToFloat(Float.floatToIntBits(31.824743f) ^ 0x7E7E9913), this.color_r, this.color_g, this.color_b, this.line_a.get_value(1), this.getDirectionsToRenderOutline(obf3.getKey()));
                    RenderHelp.release();
                    RenderHelp.prepare("quads");
                    RenderHelp.draw_gradiant_cube(RenderHelp.get_buffer_build(), (float)obf3.getKey().getX(), (float)obf3.getKey().getY(), (float)obf3.getKey().getZ(), Float.intBitsToFloat(Float.floatToIntBits(6.143063f) ^ 0x7F4493F9), obf2, Float.intBitsToFloat(Float.floatToIntBits(120.870476f) ^ 0x7D71BDAF), new Color(this.color_r, this.color_g, this.color_b, this.color_a), new Color(0, 0, 0, 0), this.getDirectionsToRenderQuad(obf3.getKey()));
                    RenderHelp.release();
                }
                if (this.glowOutline) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_gradiant_outline(RenderHelp.get_buffer_build(), obf3.getKey().getX(), obf3.getKey().getY(), obf3.getKey().getZ(), obf2, new Color(this.color_r, this.color_g, this.color_b, this.line_a.get_value(1)), new Color(0, 0, 0, 0), this.getDirectionsToRenderOutline(obf3.getKey()));
                    RenderHelp.release();
                }
            }
        }
    }
    
    public boolean isBlockHole(final BlockPos obf) {
        if (!this.is_active()) {
            this.update();
        }
        return this.holes.contains(new Pair(obf, true)) || this.holes.contains(new Pair(obf, false));
    }
    
    public BlockPos orientConv(final int obf) {
        BlockPos obf2 = null;
        switch (obf) {
            case 0: {
                obf2 = new BlockPos(0, -1, 0);
                break;
            }
            case 1: {
                obf2 = new BlockPos(0, 0, -1);
                break;
            }
            case 2: {
                obf2 = new BlockPos(1, 0, 0);
                break;
            }
            case 3: {
                obf2 = new BlockPos(0, 0, 1);
                break;
            }
            case 4: {
                obf2 = new BlockPos(-1, 0, 0);
                break;
            }
            case 5: {
                obf2 = new BlockPos(0, 1, 0);
                break;
            }
        }
        return obf2;
    }
    
    public String getDirectionsToRenderOutline(final BlockPos blockPos) {
        switch (this.dual_hole_sides.get(blockPos)) {
            case 1: {
                return "downeast-upeast-downsouth-upsouth-downwest-upwest-southwest-southeast";
            }
            case 2: {
                return "downnorth-upnorth-downsouth-upsouth-downwest-upwest-northwest-southwest";
            }
            case 3: {
                return "upnorth-downnorth-upeast-downeast-upwest-downwest-northeast-northwest";
            }
            case 4: {
                return "upnorth-downnorth-upeast-downeast-upsouth-downsouth-northeast-southeast";
            }
            default: {
                return "all";
            }
        }
    }
    
    @Override
    public void update() {
        this.color_r_b = this.rb.get_value(1);
        this.color_g_b = this.gb.get_value(1);
        this.color_b_b = this.bb.get_value(1);
        this.color_r_o = this.ro.get_value(1);
        this.color_g_o = this.go.get_value(1);
        this.color_b_o = this.bo.get_value(1);
        this.holes.clear();
        this.dual_holes.clear();
        this.dual_hole_sides.clear();
        if (HoleESP.mc.player != null || HoleESP.mc.world != null) {
            if (this.mode.in("Pretty")) {
                this.outline = true;
                this.solid = true;
                this.glow = false;
                this.glowOutline = false;
            }
            if (this.mode.in("Solid")) {
                this.outline = false;
                this.solid = true;
                this.glow = false;
                this.glowOutline = false;
            }
            if (this.mode.in("Outline")) {
                this.outline = true;
                this.solid = false;
                this.glow = false;
                this.glowOutline = false;
            }
            if (this.mode.in("Glow")) {
                this.outline = false;
                this.solid = false;
                this.glow = true;
                this.glowOutline = false;
            }
            if (this.mode.in("Glow 2")) {
                this.outline = false;
                this.solid = false;
                this.glow = true;
                this.glowOutline = true;
            }
            final int obf = (int)Math.ceil(this.range.get_value(1));
            final List<BlockPos> obf2 = this.sphere(this.player_as_blockpos(), (float)obf);
            for (final BlockPos obf3 : obf2) {
                if (!HoleESP.mc.world.getBlockState(obf3).getBlock().equals(Blocks.AIR)) {
                    continue;
                }
                if (!HoleESP.mc.world.getBlockState(obf3.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                    continue;
                }
                if (!HoleESP.mc.world.getBlockState(obf3.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                    continue;
                }
                boolean obf4 = true;
                this.safe_sides = 0;
                int obf5 = -1;
                int obf6 = 0;
                for (final BlockPos obf7 : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
                    final Block obf8 = HoleESP.mc.world.getBlockState(obf3.add((Vec3i)obf7)).getBlock();
                    if (obf8 != Blocks.BEDROCK && obf8 != Blocks.OBSIDIAN && obf8 != Blocks.ENDER_CHEST && obf8 != Blocks.ANVIL) {
                        obf4 = false;
                        if (obf6 == 0) {
                            break;
                        }
                        if (obf5 != -1) {
                            obf5 = -1;
                            break;
                        }
                        if (!obf8.equals(Blocks.AIR)) {
                            break;
                        }
                        obf5 = obf6;
                    }
                    if (obf8 == Blocks.BEDROCK) {
                        ++this.safe_sides;
                    }
                    ++obf6;
                }
                if (obf4) {
                    if (this.safe_sides == 5) {
                        if (!this.bedrock_enable.get_value(true)) {
                            continue;
                        }
                        this.holes.add(new Pair<BlockPos, Boolean>(obf3, true));
                    }
                    else {
                        if (!this.obsidian_enable.get_value(true)) {
                            continue;
                        }
                        this.holes.add(new Pair<BlockPos, Boolean>(obf3, false));
                    }
                }
                else {
                    if (!this.dual_enable.get_value(true)) {
                        continue;
                    }
                    if (obf5 < 0) {
                        continue;
                    }
                    final BlockPos obf9 = obf3.add((Vec3i)this.orientConv(obf5));
                    if (!this.checkDual(obf9, obf5)) {
                        continue;
                    }
                    final boolean obf10 = !HoleESP.mc.world.getBlockState(obf9.add(0, 1, 0)).getBlock().equals(Blocks.AIR);
                    if (this.safe_sides == 8) {
                        if (obf10) {
                            this.holes.add(new Pair<BlockPos, Boolean>(obf3, true));
                        }
                        else {
                            if (!this.dual_hole_sides.containsKey(obf3)) {
                                this.dual_holes.add(new Pair<BlockPos, Boolean>(obf3, true));
                                this.dual_hole_sides.put(obf3, obf5);
                            }
                            if (this.dual_hole_sides.containsKey(obf9)) {
                                continue;
                            }
                            this.dual_holes.add(new Pair<BlockPos, Boolean>(obf9, true));
                            this.dual_hole_sides.put(obf9, this.oppositeIntOrient(obf5));
                        }
                    }
                    else if (obf10) {
                        this.holes.add(new Pair<BlockPos, Boolean>(obf3, false));
                    }
                    else {
                        if (!this.dual_hole_sides.containsKey(obf3)) {
                            this.dual_holes.add(new Pair<BlockPos, Boolean>(obf3, false));
                            this.dual_hole_sides.put(obf3, obf5);
                        }
                        if (this.dual_hole_sides.containsKey(obf9)) {
                            continue;
                        }
                        this.dual_holes.add(new Pair<BlockPos, Boolean>(obf9, false));
                        this.dual_hole_sides.put(obf9, this.oppositeIntOrient(obf5));
                    }
                }
            }
        }
    }
    
    public int oppositeIntOrient(final int n) {
        int n2 = 0;
        switch (n) {
            case 0: {
                n2 = 5;
                break;
            }
            case 1: {
                n2 = 3;
                break;
            }
            case 2: {
                n2 = 4;
                break;
            }
            case 3: {
                n2 = 1;
                break;
            }
            case 4: {
                n2 = 2;
                break;
            }
        }
        return n2;
    }
}
