// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.turok.draw;

import org.lwjgl.opengl.GL11;

public class GL
{
    public static void color(final float r, final float g, final float b, final float a) {
        GL11.glColor4f(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f);
    }
    
    public static void start() {
        GL11.glDisable(3553);
    }
    
    public static void finish() {
        GL11.glDisable(3553);
        GL11.glDisable(3042);
    }
    
    public static void draw_rect(final int x, final int y, final int width, final int height) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(7);
        GL11.glVertex2d((double)(x + width), (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)(y + height));
        GL11.glVertex2d((double)(x + width), (double)(y + height));
        GL11.glEnd();
    }
    
    public static void resize(final int x, final int y, final float size) {
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        GL11.glTranslatef((float)x, (float)y, 0.0f);
        GL11.glScalef(size, size, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void resize(final int x, final int y, final float size, final String tag) {
        GL11.glScalef(1.0f / size, 1.0f / size, 1.0f);
        GL11.glTranslatef((float)(-x), (float)(-y), 0.0f);
        GL11.glDisable(3553);
    }
}
