// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import net.minecraft.init.Blocks;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventMotionUpdate;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import me.sazked.leux.client.modules.Module;

public class Anchor extends Module
{
    public int holeblocks;
    public ArrayList<BlockPos> holes;
    public Vec3d Center;
    public static boolean AnchorING;
    public Setting Pull;
    public Setting Pitch;
    @EventHandler
    public Listener<EventMotionUpdate> OnClientTick;
    
    public void lambda$new$0(final EventMotionUpdate eventMotionUpdate) {
        if (Anchor.mc.player.rotationPitch >= this.Pitch.get_value(60)) {
            if (this.isBlockHole(this.getPlayerPos().down(1)) || this.isBlockHole(this.getPlayerPos().down(2)) || this.isBlockHole(this.getPlayerPos().down(3)) || this.isBlockHole(this.getPlayerPos().down(4))) {
                Anchor.AnchorING = true;
                if (!this.Pull.get_value(true)) {
                    Anchor.mc.player.motionX = 0.0;
                    Anchor.mc.player.motionZ = 0.0;
                }
                else {
                    this.Center = this.GetCenter(Anchor.mc.player.posX, Anchor.mc.player.posY, Anchor.mc.player.posZ);
                    final double abs = Math.abs(this.Center.x - Anchor.mc.player.posX);
                    final double abs2 = Math.abs(this.Center.z - Anchor.mc.player.posZ);
                    if (abs <= 0.1 && abs2 <= 0.1) {
                        this.Center = Vec3d.ZERO;
                    }
                    else {
                        final double n = this.Center.x - Anchor.mc.player.posX;
                        final double n2 = this.Center.z - Anchor.mc.player.posZ;
                        Anchor.mc.player.motionX = n / 2.0;
                        Anchor.mc.player.motionZ = n2 / 2.0;
                    }
                }
            }
            else {
                Anchor.AnchorING = false;
            }
        }
    }
    
    public BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(Anchor.mc.player.posX), Math.floor(Anchor.mc.player.posY), Math.floor(Anchor.mc.player.posZ));
    }
    
    public Anchor() {
        super(Category.movement);
        this.Pitch = this.create("Pitch", "AnchorPitch", 60, 0, 90);
        this.Pull = this.create("Pull", "AnchorPull", true);
        this.holes = new ArrayList<BlockPos>();
        this.Center = Vec3d.ZERO;
        this.OnClientTick = new Listener<EventMotionUpdate>(this::lambda$new$0, (Predicate<EventMotionUpdate>[])new Predicate[0]);
        this.name = "Anchor";
        this.tag = "Anchor";
        this.description = "Stops all movement if player is above a hole";
    }
    
    public boolean isBlockHole(final BlockPos blockPos) {
        this.holeblocks = 0;
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 3, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 0, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        Label_0496: {
            if (Anchor.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock() != Blocks.OBSIDIAN) {
                if (Anchor.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    break Label_0496;
                }
            }
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        return this.holeblocks >= 9;
    }
    
    public Vec3d GetCenter(final double obf, final double obf, final double obf) {
        final double obf2 = Math.floor(obf) + Double.longBitsToDouble(Double.doubleToLongBits(53.443810196671386) ^ 0x7FAAB8CEC5C42ADFL);
        final double obf3 = Math.floor(obf);
        final double obf4 = Math.floor(obf) + Double.longBitsToDouble(Double.doubleToLongBits(12.196696431203732) ^ 0x7FC864B565068517L);
        return new Vec3d(obf2, obf3, obf4);
    }
    
    public void onDisable() {
        Anchor.AnchorING = false;
        this.holeblocks = 0;
    }
}
