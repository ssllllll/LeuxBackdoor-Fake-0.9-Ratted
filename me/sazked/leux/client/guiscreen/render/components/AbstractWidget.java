// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render.components;

public abstract class AbstractWidget
{
    public void set_x(final int x) {
    }
    
    public void set_y(final int y) {
    }
    
    public void set_width(final int width) {
    }
    
    public void set_height(final int height) {
    }
    
    public int get_x() {
        return 0;
    }
    
    public int get_y() {
        return 0;
    }
    
    public int get_width() {
        return 0;
    }
    
    public int get_height() {
        return 0;
    }
    
    public boolean is_binding() {
        return false;
    }
    
    public boolean motion_pass(final int mx, final int my) {
        return false;
    }
    
    public void bind(final char char_, final int key) {
    }
    
    public void does_can(final boolean value) {
    }
    
    public void mouse(final int mx, final int my, final int mouse) {
    }
    
    public void release(final int mx, final int my, final int mouse) {
    }
    
    public void render(final int master_y, final int separe, final int x, final int y) {
    }
}
