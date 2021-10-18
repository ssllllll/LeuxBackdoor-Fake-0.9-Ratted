// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiandfont;

import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.lwjgl.opengl.GL11;
import java.awt.Font;
import net.minecraft.client.renderer.texture.DynamicTexture;

public class CFont
{
    public CharData[] charData;
    public float imgSize;
    public int fontHeight;
    public DynamicTexture tex;
    public int charOffset;
    public boolean antiAlias;
    public boolean fractionalMetrics;
    public Font font;
    
    public CFont(final Font obf, final boolean obf, final boolean obf) {
        this.imgSize = Float.intBitsToFloat(Float.floatToIntBits(3.0803608E-4f) ^ 0x7DA17FE7);
        this.imgSize = Float.intBitsToFloat(Float.floatToIntBits(0.0027959147f) ^ 0x7F373BAA);
        this.charData = new CharData[256];
        this.fontHeight = -1;
        this.charOffset = 0;
        this.font = obf;
        this.antiAlias = obf;
        this.fractionalMetrics = obf;
        this.tex = this.setupTexture(obf, obf, obf, this.charData);
    }
    
    public void drawQuad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        final float n9 = n5 / 512.0f;
        final float n10 = n6 / 512.0f;
        final float n11 = n7 / 512.0f;
        final float n12 = n8 / 512.0f;
        GL11.glTexCoord2f(n9 + n11, n10);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glTexCoord2f(n9, n10);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glTexCoord2f(n9, n10 + n12);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glTexCoord2f(n9, n10 + n12);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glTexCoord2f(n9 + n11, n10 + n12);
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        GL11.glTexCoord2f(n9 + n11, n10);
        GL11.glVertex2d((double)(n + n3), (double)n2);
    }
    
    public void drawChar(final CharData[] obf, final char obf, final float obf, final float obf) throws ArrayIndexOutOfBoundsException {
        Exception ex;
        try {
            this.drawQuad(obf, obf, (float)obf[obf].width, (float)obf[obf].height, (float)obf[obf].storedX, (float)obf[obf].storedY, (float)obf[obf].width, (float)obf[obf].height);
            return;
        }
        catch (Exception obf2) {
            ex = obf2;
        }
        ex.printStackTrace();
    }
    
    public void setAntiAlias(final boolean b) {
        if (this.antiAlias != b) {
            this.antiAlias = b;
            this.tex = this.setupTexture(this.font, b, this.fractionalMetrics, this.charData);
        }
    }
    
    public DynamicTexture setupTexture(final Font obf, final boolean obf, final boolean obf, final CharData[] obf) {
        final BufferedImage obf2 = this.generateFontImage(obf, obf, obf, obf);
        try {
            return new DynamicTexture(obf2);
        }
        catch (Exception obf3) {
            obf3.printStackTrace();
            return null;
        }
    }
    
    public boolean isFractionalMetrics() {
        return this.fractionalMetrics;
    }
    
    public boolean isAntiAlias() {
        return this.antiAlias;
    }
    
    public int getStringWidth(final String s) {
        int n = 0;
        for (final char c : s.toCharArray()) {
            if (c < this.charData.length) {
                if (c >= '\0') {
                    n += this.charData[c].width - 8 + this.charOffset;
                }
            }
        }
        return n / 2;
    }
    
    public void setFractionalMetrics(final boolean b) {
        if (this.fractionalMetrics != b) {
            this.fractionalMetrics = b;
            this.tex = this.setupTexture(this.font, this.antiAlias, b, this.charData);
        }
    }
    
    public void setFont(final Font obf) {
        this.font = obf;
        this.tex = this.setupTexture(obf, this.antiAlias, this.fractionalMetrics, this.charData);
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }
    
    public BufferedImage generateFontImage(final Font obf, final boolean obf, final boolean obf, final CharData[] obf) {
        this.getClass();
        final int obf2 = 512;
        final BufferedImage obf3 = new BufferedImage(512, 512, 2);
        final Graphics2D obf4 = (Graphics2D)obf3.getGraphics();
        obf4.setFont(obf);
        obf4.setColor(new Color(255, 255, 255, 0));
        obf4.fillRect(0, 0, 512, 512);
        obf4.setColor(Color.WHITE);
        obf4.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, obf ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        obf4.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, obf ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        obf4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, obf ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        final FontMetrics obf5 = obf4.getFontMetrics();
        int obf6 = 0;
        int obf7 = 0;
        int obf8 = 1;
        for (int obf9 = 0; obf9 < obf.length; ++obf9) {
            final char obf10 = (char)obf9;
            final CharData obf11 = new CharData();
            final Rectangle2D obf12 = obf5.getStringBounds(String.valueOf(obf10), obf4);
            obf11.width = obf12.getBounds().width + 8;
            obf11.height = obf12.getBounds().height;
            if (obf7 + obf11.width >= 512) {
                obf7 = 0;
                obf8 += obf6;
                obf6 = 0;
            }
            if (obf11.height > obf6) {
                obf6 = obf11.height;
            }
            obf11.storedX = obf7;
            obf11.storedY = obf8;
            if (obf11.height > this.fontHeight) {
                this.fontHeight = obf11.height;
            }
            obf[obf9] = obf11;
            obf4.drawString(String.valueOf(obf10), obf7 + 2, obf8 + obf5.getAscent());
            obf7 += obf11.width;
        }
        return obf3;
    }
    
    public int getStringHeight(final String s) {
        return this.getHeight();
    }
    
    protected class CharData
    {
        public int storedY;
        public CFont this$0;
        public int height;
        public int width;
        public int storedX;
        
        public CharData(final CFont obf) {
            this.this$0 = obf;
        }
    }
}
