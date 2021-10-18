// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.event.events;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.event.EventCancellable;

public class EventRenderEntityModel extends EventCancellable
{
    public int stage;
    public Entity entity;
    public ModelBase modelBase;
    public float headYaw;
    public float limbSwing;
    public float headPitch;
    public float age;
    public float limbSwingAmount;
    public float scale;
    
    public EventRenderEntityModel(final int obf, final ModelBase obf, final Entity obf, final float obf, final float obf, final float obf, final float obf, final float obf, final float obf) {
        this.stage = obf;
        this.modelBase = obf;
        this.entity = obf;
        this.limbSwing = obf;
        this.limbSwingAmount = obf;
        this.age = obf;
        this.headYaw = obf;
        this.headPitch = obf;
        this.scale = obf;
    }
}
