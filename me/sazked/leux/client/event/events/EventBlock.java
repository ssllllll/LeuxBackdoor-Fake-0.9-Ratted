// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import me.sazked.leux.client.event.EventCancellable;

public class EventBlock extends EventCancellable
{
    public int stage;
    public EnumFacing facing;
    public BlockPos pos;
    
    public void set_stage(final int obf) {
        this.stage = obf;
    }
    
    public EventBlock(final int obf, final BlockPos obf, final EnumFacing obf) {
        this.pos = obf;
        this.facing = obf;
        this.stage = obf;
    }
    
    public int get_stage() {
        return this.stage;
    }
}
