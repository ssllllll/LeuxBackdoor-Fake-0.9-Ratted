// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import java.util.List;
import net.minecraft.util.math.AxisAlignedBB;
import me.sazked.leux.client.event.EventCancellable;

public class EventCollisionBoxToList extends EventCancellable
{
    public AxisAlignedBB entityBox;
    public boolean bool;
    public List<AxisAlignedBB> collidingBoxes;
    public Entity entity;
    public IBlockState state;
    public World world;
    public Block block;
    public BlockPos pos;
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public EventCollisionBoxToList(final Block obf, final IBlockState obf, final World obf, final BlockPos obf, final AxisAlignedBB obf, final List<AxisAlignedBB> obf, final Entity obf, final boolean obf) {
        this.block = obf;
        this.state = obf;
        this.world = obf;
        this.pos = obf;
        this.entityBox = obf;
        this.collidingBoxes = obf;
        this.entity = obf;
        this.bool = obf;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public boolean isBool() {
        return this.bool;
    }
    
    public List<AxisAlignedBB> getCollidingBoxes() {
        return this.collidingBoxes;
    }
    
    public IBlockState getState() {
        return this.state;
    }
    
    public AxisAlignedBB getEntityBox() {
        return this.entityBox;
    }
}
