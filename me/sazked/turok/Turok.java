// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.turok;

import me.sazked.turok.draw.GL;
import me.sazked.turok.task.Font;

public class Turok
{
    private String tag;
    private Font font_manager;
    
    public Turok(final String tag) {
        this.tag = tag;
    }
    
    public void resize(final int x, final int y, final float size) {
        GL.resize(x, y, size);
    }
    
    public void resize(final int x, final int y, final float size, final String tag) {
        GL.resize(x, y, size, "end");
    }
    
    public Font get_font_manager() {
        return this.font_manager;
    }
}
