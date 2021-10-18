// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.render;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import me.sazked.turok.draw.RenderHelp;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import me.sazked.turok.Turok;
import me.sazked.turok.task.Rect;
import java.util.Arrays;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.FontRenderer;

public class Draw
{
    private static FontRenderer font_renderer;
    private static FontRenderer custom_font;
    private float size;
    
    public Draw(final float size) {
        this.size = size;
    }
    
    public static void draw_rect(final int x, final int y, final int w, final int h, final int r, final int g, final int b, final int a) {
        Gui.drawRect(x, y, w, h, new ClientColor(r, g, b, a).hex());
    }
    
    public static void draw_rect(final int x, final int y, final int w, final int h, final int r, final int g, final int b, final int a, final int size, final String type) {
        if (Arrays.asList(type.split("-")).contains("up")) {
            draw_rect(x, y, x + w, y + size, r, g, b, a);
        }
        if (Arrays.asList(type.split("-")).contains("down")) {
            draw_rect(x, y + h - size, x + w, y + h, r, g, b, a);
        }
        if (Arrays.asList(type.split("-")).contains("left")) {
            draw_rect(x, y, x + size, y + h, r, g, b, a);
        }
        if (Arrays.asList(type.split("-")).contains("right")) {
            draw_rect(x + w - size, y, x + w, y + h, r, g, b, a);
        }
    }
    
    public static void draw_rect(final Rect rect, final int r, final int g, final int b, final int a) {
        Gui.drawRect(rect.get_x(), rect.get_y(), rect.get_screen_width(), rect.get_screen_height(), new ClientColor(r, g, b, a).hex());
    }
    
    public static void draw_string(final String string, final int x, final int y, final int r, final int g, final int b, final int a) {
        Draw.font_renderer.drawStringWithShadow(string, (float)x, (float)y, new ClientColor(r, g, b, a).hex());
    }
    
    public void draw_string_gl(final String string, final int x, final int y, final int r, final int g, final int b) {
        final Turok resize_gl = new Turok("Resize");
        resize_gl.resize(x, y, this.size);
        Draw.font_renderer.drawString(string, x, y, new ClientColor(r, g, b).hex());
        resize_gl.resize(x, y, this.size, "end");
        GL11.glPushMatrix();
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        GlStateManager.enableBlend();
        GL11.glPopMatrix();
        RenderHelp.release_gl();
    }
    
    public int get_string_height() {
        final FontRenderer fontRenderer = Draw.font_renderer;
        return (int)(fontRenderer.FONT_HEIGHT * this.size);
    }
    
    public int get_string_width(final String string) {
        final FontRenderer fontRenderer = Draw.font_renderer;
        return (int)(fontRenderer.getStringWidth(string) * this.size);
    }
    
    static {
        Draw.font_renderer = Minecraft.getMinecraft().fontRenderer;
        Draw.custom_font = Minecraft.getMinecraft().fontRenderer;
    }
    
    public static class ClientColor extends Color
    {
        public ClientColor(final int r, final int g, final int b, final int a) {
            super(r, g, b, a);
        }
        
        public ClientColor(final int r, final int g, final int b) {
            super(r, g, b);
        }
        
        public int hex() {
            return this.getRGB();
        }
    }
}
