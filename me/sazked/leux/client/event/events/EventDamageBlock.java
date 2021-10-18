// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import me.sazked.leux.client.event.EventCancellable;

public class EventDamageBlock extends EventCancellable
{
    public BlockPos BlockPos;
    public EnumFacing Direction;
    
    public BlockPos getPos() {
        return this.BlockPos;
    }
    
    public EventDamageBlock(final BlockPos obf, final EnumFacing obf) {
        this.BlockPos = obf;
        this.setDirection(obf);
    }
    
    public void setDirection(final EnumFacing obf) {
        this.Direction = obf;
    }
    
    public EnumFacing getDirection() {
        return this.Direction;
    }
}
