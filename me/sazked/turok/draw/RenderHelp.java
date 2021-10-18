// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.turok.draw;

import java.awt.Color;
import java.util.List;
import java.util.Arrays;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;

public class RenderHelp extends Tessellator
{
    public static RenderHelp INSTANCE;
    
    public RenderHelp() {
        super(2097152);
    }
    
    public static void prepare(final String mode_requested) {
        int mode = 0;
        if (mode_requested.equalsIgnoreCase("quads")) {
            mode = 7;
        }
        else if (mode_requested.equalsIgnoreCase("lines")) {
            mode = 1;
        }
        else if (mode_requested.equalsIgnoreCase("triangles")) {
            mode = 4;
        }
        prepare_gl();
        begin(mode);
    }
    
    public static void prepare_gl() {
        GL11.glBlendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(1.5f);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    public static void begin(final int mode) {
        RenderHelp.INSTANCE.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
    }
    
    public static void release() {
        render();
        release_gl();
    }
    
    public static void render() {
        RenderHelp.INSTANCE.draw();
    }
    
    public static void release_gl() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
    
    public static void draw_cube(final BlockPos blockPos, final int r, final int g, final int b, final int a, final String sides) {
        draw_cube(RenderHelp.INSTANCE.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }
    
    public static void draw_cube_line(final BlockPos blockPos, final int r, final int g, final int b, final int a, final String sides) {
        draw_cube_line(RenderHelp.INSTANCE.getBuffer(), (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ(), 1.0f, 1.0f, 1.0f, r, g, b, a, sides);
    }
    
    public static BufferBuilder get_buffer_build() {
        return RenderHelp.INSTANCE.getBuffer();
    }
    
    public static void draw_cube(final BufferBuilder buffer, final float x, final float y, final float z, final float w, final float h, final float d, final int r, final int g, final int b, final int a, final String sides) {
        final List<String> sidesList = Arrays.asList(sides.split("-"));
        if (sidesList.contains("down") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)y, (double)z).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("up") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("north") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("south") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("west") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)z).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("east") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("top")) {
            buffer.pos((double)x, (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("bottom")) {
            buffer.pos((double)x, (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
        }
    }
    
    public static void draw_gradiant_cube(final BufferBuilder buffer, final float x, final float y, final float z, final float w, final float h, final float d, final Color startColor, final Color endColor, final String sides) {
        final int r1 = startColor.getRed();
        final int g1 = startColor.getGreen();
        final int b1 = startColor.getBlue();
        final int a1 = startColor.getAlpha();
        final int r2 = endColor.getRed();
        final int g2 = endColor.getGreen();
        final int b2 = endColor.getBlue();
        final int a2 = endColor.getAlpha();
        final List<String> sidesList = Arrays.asList(sides.split("-"));
        if (sidesList.contains("north") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)y, (double)z).color(r1, g1, b1, a1).endVertex();
            buffer.pos((double)x, (double)y, (double)z).color(r1, g1, b1, a1).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)z).color(r2, g2, b2, a2).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r2, g2, b2, a2).endVertex();
        }
        if (sidesList.contains("south") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r1, g1, b1, a1).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r1, g1, b1, a1).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r2, g2, b2, a2).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r2, g2, b2, a2).endVertex();
        }
        if (sidesList.contains("west") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)z).color(r1, g1, b1, a1).endVertex();
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r1, g1, b1, a1).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r2, g2, b2, a2).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)z).color(r2, g2, b2, a2).endVertex();
        }
        if (sidesList.contains("east") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r1, g1, b1, a1).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)z).color(r1, g1, b1, a1).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r2, g2, b2, a2).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r2, g2, b2, a2).endVertex();
        }
    }
    
    public static void draw_gradiant_outline(final BufferBuilder buffer, final double x, final double y, final double z, final double height, final Color startColor, final Color endColor, final String sides) {
        final List<String> sidesList = Arrays.asList(sides.split("-"));
        final boolean drawAll = sides.equalsIgnoreCase("all");
        if (sidesList.contains("northwest") || drawAll) {
            draw_gradiant_line(buffer, x, y, z, x, y + height, z, startColor, endColor);
        }
        if (sidesList.contains("northeast") || drawAll) {
            draw_gradiant_line(buffer, x + 1.0, y, z, x + 1.0, y + height, z, startColor, endColor);
        }
        if (sidesList.contains("southwest") || drawAll) {
            draw_gradiant_line(buffer, x, y, z + 1.0, x, y + height, z + 1.0, startColor, endColor);
        }
        if (sidesList.contains("southeast") || drawAll) {
            draw_gradiant_line(buffer, x + 1.0, y, z + 1.0, x + 1.0, y + height, z + 1.0, startColor, endColor);
        }
    }
    
    public static void draw_gradiant_line(final BufferBuilder buffer, final double x1, final double y1, final double z1, final double x2, final double y2, final double z2, final Color startColor, final Color endColor) {
        buffer.pos(x1, y1, z1).color(startColor.getRed(), startColor.getGreen(), startColor.getBlue(), startColor.getAlpha()).endVertex();
        buffer.pos(x2, y2, z2).color(endColor.getRed(), endColor.getGreen(), endColor.getBlue(), endColor.getAlpha()).endVertex();
    }
    
    public static void draw_cube_line(final BufferBuilder buffer, final float x, final float y, final float z, final float w, final float h, final float d, final int r, final int g, final int b, final int a, final String sides) {
        final List<String> sidesList = Arrays.asList(sides.split("-"));
        if (sidesList.contains("downwest") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("upwest") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("downeast") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("upeast") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("downnorth") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)z).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("upnorth") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)(y + h), (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("downsouth") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("upsouth") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("northwest") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)z).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("northeast") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)y, (double)z).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("southwest") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)x, (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)x, (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
        if (sidesList.contains("southeast") || sides.equalsIgnoreCase("all")) {
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color(r, g, b, a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color(r, g, b, a).endVertex();
        }
    }
    
    static {
        RenderHelp.INSTANCE = new RenderHelp();
    }
}
