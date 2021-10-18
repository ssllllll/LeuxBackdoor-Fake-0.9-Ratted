// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import me.sazked.turok.draw.RenderHelp;
import me.sazked.leux.client.event.events.EventRender;
import java.util.HashSet;
import me.sazked.leux.client.modules.Category;
import net.minecraft.init.Blocks;
import me.sazked.leux.client.util.CrystalUtil;
import java.util.Iterator;
import me.sazked.leux.client.util.FriendUtil;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.util.EntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import java.util.Set;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class FuckedDetector extends Module
{
    public Setting a;
    public Setting g;
    public boolean outline;
    public Set<BlockPos> fucked_players;
    public Setting draw_friends;
    public Setting render_mode;
    public boolean solid;
    public Setting b;
    public Setting r;
    public Setting draw_own;
    
    @Override
    public void update() {
        if (FuckedDetector.mc.world == null) {
            return;
        }
        this.set_fucked_players();
    }
    
    public void set_fucked_players() {
        this.fucked_players.clear();
        for (final EntityPlayer obf : FuckedDetector.mc.world.playerEntities) {
            if (EntityUtil.isLiving((Entity)obf)) {
                if (obf.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(1.7780276E38f) ^ 0x7F05C391)) {
                    continue;
                }
                if (!this.is_fucked(obf)) {
                    continue;
                }
                if (FriendUtil.isFriend(obf.getName()) && !this.draw_friends.get_value(true)) {
                    continue;
                }
                if (obf == FuckedDetector.mc.player && !this.draw_own.get_value(true)) {
                    continue;
                }
                this.fucked_players.add(new BlockPos(obf.posX, obf.posY, obf.posZ));
            }
        }
    }
    
    @Override
    public void enable() {
        this.fucked_players.clear();
    }
    
    public boolean is_fucked(final EntityPlayer obf) {
        final BlockPos obf2 = new BlockPos(obf.posX, obf.posY - Double.longBitsToDouble(Double.doubleToLongBits(37.161820449073836) ^ 0x7FB294B688504C4FL), obf.posZ);
        if (!CrystalUtil.canPlaceCrystal(obf2.south()) && (!CrystalUtil.canPlaceCrystal(obf2.south().south()) || FuckedDetector.mc.world.getBlockState(obf2.add(0, 1, 1)).getBlock() != Blocks.AIR)) {
            if (!CrystalUtil.canPlaceCrystal(obf2.east())) {
                if (CrystalUtil.canPlaceCrystal(obf2.east().east())) {
                    if (FuckedDetector.mc.world.getBlockState(obf2.add(1, 1, 0)).getBlock() == Blocks.AIR) {
                        return true;
                    }
                }
                return CrystalUtil.canPlaceCrystal(obf2.west()) || (CrystalUtil.canPlaceCrystal(obf2.west().west()) && FuckedDetector.mc.world.getBlockState(obf2.add(-1, 1, 0)).getBlock() == Blocks.AIR) || CrystalUtil.canPlaceCrystal(obf2.north()) || (CrystalUtil.canPlaceCrystal(obf2.north().north()) && FuckedDetector.mc.world.getBlockState(obf2.add(0, 1, -1)).getBlock() == Blocks.AIR);
            }
            return true;
        }
        return true;
    }
    
    public FuckedDetector() {
        super(Category.render);
        this.draw_own = this.create("Draw Own", "FuckedDrawOwn", false);
        this.draw_friends = this.create("Draw Friends", "FuckedDrawFriends", false);
        this.render_mode = this.create("Render Mode", "FuckedRenderMode", "Pretty", this.combobox("Pretty", "Solid", "Outline"));
        this.r = this.create("R", "FuckedR", 255, 0, 255);
        this.g = this.create("G", "FuckedG", 255, 0, 255);
        this.b = this.create("B", "FuckedB", 255, 0, 255);
        this.a = this.create("A", "FuckedA", 100, 0, 255);
        this.fucked_players = new HashSet<BlockPos>();
        this.name = "Fucked Detector";
        this.tag = "FuckedDetector";
        this.description = "see if people are hecked";
    }
    
    @Override
    public void render(final EventRender eventRender) {
        if (this.render_mode.in("Pretty")) {
            this.outline = true;
            this.solid = true;
        }
        if (this.render_mode.in("Solid")) {
            this.outline = false;
            this.solid = true;
        }
        if (this.render_mode.in("Outline")) {
            this.outline = true;
            this.solid = false;
        }
        for (final BlockPos blockPos : this.fucked_players) {
            if (blockPos == null) {
                return;
            }
            if (this.solid) {
                RenderHelp.prepare("quads");
                RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
                RenderHelp.release();
            }
            if (!this.outline) {
                continue;
            }
            RenderHelp.prepare("lines");
            RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            RenderHelp.release();
        }
    }
}
