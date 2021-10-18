// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.modules.Module;

public class Peek extends Module
{
    public Peek() {
        super(Category.render);
        this.name = "Peek";
        this.tag = "ShulkerPreview";
        this.description = "See what's inside a shulker via hovering over it";
    }
}
