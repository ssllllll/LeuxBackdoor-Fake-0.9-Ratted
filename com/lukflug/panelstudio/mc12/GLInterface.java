// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.mc12;

import net.minecraft.client.renderer.GLAllocation;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import net.minecraft.client.renderer.texture.TextureUtil;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Stack;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import com.lukflug.panelstudio.Interface;

public abstract class GLInterface implements Interface
{
    private static final FloatBuffer MODELVIEW;
    private static final FloatBuffer PROJECTION;
    private static final IntBuffer VIEWPORT;
    private static final FloatBuffer COORDS;
    private Stack<Rectangle> clipRect;
    protected boolean clipX;
    
    public GLInterface(final boolean clipX) {
        this.clipRect = new Stack<Rectangle>();
        this.clipX = clipX;
    }
    
    @Override
    public void fillTriangle(final Point pos1, final Point pos2, final Point pos3, final Color c1, final Color c2, final Color c3) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(4, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)pos1.x, (double)pos1.y, (double)this.getZLevel()).color(c1.getRed() / 255.0f, c1.getGreen() / 255.0f, c1.getBlue() / 255.0f, c1.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)pos2.x, (double)pos2.y, (double)this.getZLevel()).color(c2.getRed() / 255.0f, c2.getGreen() / 255.0f, c2.getBlue() / 255.0f, c2.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)pos3.x, (double)pos3.y, (double)this.getZLevel()).color(c3.getRed() / 255.0f, c3.getGreen() / 255.0f, c3.getBlue() / 255.0f, c3.getAlpha() / 255.0f).endVertex();
        tessellator.draw();
    }
    
    @Override
    public void drawLine(final Point a, final Point b, final Color c1, final Color c2) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)a.x, (double)a.y, (double)this.getZLevel()).color(c1.getRed() / 255.0f, c1.getGreen() / 255.0f, c1.getBlue() / 255.0f, c1.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)b.x, (double)b.y, (double)this.getZLevel()).color(c2.getRed() / 255.0f, c2.getGreen() / 255.0f, c2.getBlue() / 255.0f, c2.getAlpha() / 255.0f).endVertex();
        tessellator.draw();
    }
    
    @Override
    public void fillRect(final Rectangle r, final Color c1, final Color c2, final Color c3, final Color c4) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)r.x, (double)(r.y + r.height), (double)this.getZLevel()).color(c4.getRed() / 255.0f, c4.getGreen() / 255.0f, c4.getBlue() / 255.0f, c4.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)(r.x + r.width), (double)(r.y + r.height), (double)this.getZLevel()).color(c3.getRed() / 255.0f, c3.getGreen() / 255.0f, c3.getBlue() / 255.0f, c3.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)(r.x + r.width), (double)r.y, (double)this.getZLevel()).color(c2.getRed() / 255.0f, c2.getGreen() / 255.0f, c2.getBlue() / 255.0f, c2.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)r.x, (double)r.y, (double)this.getZLevel()).color(c1.getRed() / 255.0f, c1.getGreen() / 255.0f, c1.getBlue() / 255.0f, c1.getAlpha() / 255.0f).endVertex();
        tessellator.draw();
    }
    
    @Override
    public void drawRect(final Rectangle r, final Color c1, final Color c2, final Color c3, final Color c4) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(2, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)r.x, (double)(r.y + r.height), (double)this.getZLevel()).color(c4.getRed() / 255.0f, c4.getGreen() / 255.0f, c4.getBlue() / 255.0f, c4.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)(r.x + r.width), (double)(r.y + r.height), (double)this.getZLevel()).color(c3.getRed() / 255.0f, c3.getGreen() / 255.0f, c3.getBlue() / 255.0f, c3.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)(r.x + r.width), (double)r.y, (double)this.getZLevel()).color(c2.getRed() / 255.0f, c2.getGreen() / 255.0f, c2.getBlue() / 255.0f, c2.getAlpha() / 255.0f).endVertex();
        bufferbuilder.pos((double)r.x, (double)r.y, (double)this.getZLevel()).color(c1.getRed() / 255.0f, c1.getGreen() / 255.0f, c1.getBlue() / 255.0f, c1.getAlpha() / 255.0f).endVertex();
        tessellator.draw();
    }
    
    @Override
    public synchronized int loadImage(final String name) {
        try {
            final ResourceLocation rl = new ResourceLocation(this.getResourcePrefix() + name);
            final InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(rl).getInputStream();
            final BufferedImage image = ImageIO.read(stream);
            final int texture = TextureUtil.glGenTextures();
            TextureUtil.uploadTextureImage(texture, image);
            return texture;
        }
        catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public void drawImage(final Rectangle r, final int rotation, final boolean parity, final int image) {
        if (image == 0) {
            return;
        }
        final int[][] texCoords = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 0, 0 } };
        for (int i = 0; i < rotation % 4; ++i) {
            final int temp1 = texCoords[3][0];
            final int temp2 = texCoords[3][1];
            texCoords[3][0] = texCoords[2][0];
            texCoords[3][1] = texCoords[2][1];
            texCoords[2][0] = texCoords[1][0];
            texCoords[2][1] = texCoords[1][1];
            texCoords[1][0] = texCoords[0][0];
            texCoords[1][1] = texCoords[0][1];
            texCoords[0][0] = temp1;
            texCoords[0][1] = temp2;
        }
        if (parity) {
            int temp3 = texCoords[3][0];
            int temp4 = texCoords[3][1];
            texCoords[3][0] = texCoords[0][0];
            texCoords[3][1] = texCoords[0][1];
            texCoords[0][0] = temp3;
            texCoords[0][1] = temp4;
            temp3 = texCoords[2][0];
            temp4 = texCoords[2][1];
            texCoords[2][0] = texCoords[1][0];
            texCoords[2][1] = texCoords[1][1];
            texCoords[1][0] = temp3;
            texCoords[1][1] = temp4;
        }
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.bindTexture(image);
        GlStateManager.enableTexture2D();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)r.x, (double)(r.y + r.height), (double)this.getZLevel()).tex((double)texCoords[0][0], (double)texCoords[0][1]).endVertex();
        bufferbuilder.pos((double)(r.x + r.width), (double)(r.y + r.height), (double)this.getZLevel()).tex((double)texCoords[1][0], (double)texCoords[1][1]).endVertex();
        bufferbuilder.pos((double)(r.x + r.width), (double)r.y, (double)this.getZLevel()).tex((double)texCoords[2][0], (double)texCoords[2][1]).endVertex();
        bufferbuilder.pos((double)r.x, (double)r.y, (double)this.getZLevel()).tex((double)texCoords[3][0], (double)texCoords[3][1]).endVertex();
        tessellator.draw();
        GlStateManager.disableTexture2D();
    }
    
    protected void scissor(final Rectangle r) {
        if (r == null) {
            GL11.glScissor(0, 0, 0, 0);
            GL11.glEnable(3089);
            return;
        }
        GLU.gluProject((float)r.x, (float)r.y, this.getZLevel(), GLInterface.MODELVIEW, GLInterface.PROJECTION, GLInterface.VIEWPORT, GLInterface.COORDS);
        float x1 = GLInterface.COORDS.get(0);
        final float y1 = GLInterface.COORDS.get(1);
        GLU.gluProject((float)(r.x + r.width), (float)(r.y + r.height), this.getZLevel(), GLInterface.MODELVIEW, GLInterface.PROJECTION, GLInterface.VIEWPORT, GLInterface.COORDS);
        float x2 = GLInterface.COORDS.get(0);
        final float y2 = GLInterface.COORDS.get(1);
        if (!this.clipX) {
            x1 = (float)GLInterface.VIEWPORT.get(0);
            x2 = x1 + GLInterface.VIEWPORT.get(2);
        }
        GL11.glScissor(Math.round(Math.min(x1, x2)), Math.round(Math.min(y1, y2)), Math.round(Math.abs(x2 - x1)), Math.round(Math.abs(y2 - y1)));
        GL11.glEnable(3089);
    }
    
    @Override
    public void window(final Rectangle r) {
        if (this.clipRect.isEmpty()) {
            this.scissor(r);
            this.clipRect.push(r);
        }
        else {
            final Rectangle top = this.clipRect.peek();
            if (top == null) {
                this.scissor(null);
                this.clipRect.push(null);
            }
            else {
                final int x1 = Math.max(r.x, top.x);
                final int y1 = Math.max(r.y, top.y);
                final int x2 = Math.min(r.x + r.width, top.x + top.width);
                final int y2 = Math.min(r.y + r.height, top.y + top.height);
                if (x2 > x1 && y2 > y1) {
                    final Rectangle rect = new Rectangle(x1, y1, x2 - x1, y2 - y1);
                    this.scissor(rect);
                    this.clipRect.push(rect);
                }
                else {
                    this.scissor(null);
                    this.clipRect.push(null);
                }
            }
        }
    }
    
    @Override
    public void restore() {
        if (!this.clipRect.isEmpty()) {
            this.clipRect.pop();
            if (this.clipRect.isEmpty()) {
                GL11.glDisable(3089);
            }
            else {
                this.scissor(this.clipRect.peek());
            }
        }
    }
    
    public void getMatrices() {
        GlStateManager.getFloat(2982, GLInterface.MODELVIEW);
        GlStateManager.getFloat(2983, GLInterface.PROJECTION);
        GlStateManager.glGetInteger(2978, GLInterface.VIEWPORT);
    }
    
    public static void begin() {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        GlStateManager.glLineWidth(2.0f);
    }
    
    public static void end() {
        GlStateManager.shadeModel(7424);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    protected abstract float getZLevel();
    
    protected abstract String getResourcePrefix();
    
    static {
        MODELVIEW = GLAllocation.createDirectFloatBuffer(16);
        PROJECTION = GLAllocation.createDirectFloatBuffer(16);
        VIEWPORT = GLAllocation.createDirectIntBuffer(16);
        COORDS = GLAllocation.createDirectFloatBuffer(3);
    }
}
