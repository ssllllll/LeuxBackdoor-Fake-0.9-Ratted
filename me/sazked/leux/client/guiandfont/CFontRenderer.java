// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiandfont;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import java.awt.Font;
import net.minecraft.client.renderer.texture.DynamicTexture;

public class CFontRenderer extends CFont
{
    public DynamicTexture texBold;
    public CharData[] boldItalicChars;
    public int fontSize;
    public String fontName;
    public DynamicTexture texItalicBold;
    public int[] colorCode;
    public CharData[] boldChars;
    public DynamicTexture texItalic;
    public String colorcodeIdentifiers;
    public CharData[] italicChars;
    
    public float drawStringWithShadow(final String obf, final double obf, final double obf, final int obf) {
        final float obf2 = this.drawString(obf, obf + Double.longBitsToDouble(Double.doubleToLongBits(5.2706471147432765) ^ 0x7FE51524846A5108L), obf + Double.longBitsToDouble(Double.doubleToLongBits(4.438711340329696) ^ 0x7FE1C13D8BAC66C7L), obf, true);
        return Math.max(obf2, this.drawString(obf, obf, obf, obf, false));
    }
    
    public CFontRenderer(final Font obf, final boolean obf, final boolean obf) {
        this.colorcodeIdentifiers = "0123456789abcdefklmnor";
        super(obf, obf, obf);
        this.colorcodeIdentifiers = "0123456789abcdefklmnor";
        this.boldChars = new CharData[256];
        this.italicChars = new CharData[256];
        this.boldItalicChars = new CharData[256];
        this.colorCode = new int[32];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    @Override
    public void setAntiAlias(final boolean obf) {
        super.setAntiAlias(obf);
        this.setupBoldItalicIDs();
    }
    
    @Override
    public void setFractionalMetrics(final boolean obf) {
        super.setFractionalMetrics(obf);
        this.setupBoldItalicIDs();
    }
    
    public float drawString(final String obf, double obf, double obf, int obf, final boolean obf) {
        obf -= Double.longBitsToDouble(Double.doubleToLongBits(4.440684448825389) ^ 0x7FE1C342C8BE3E4BL);
        obf -= Double.longBitsToDouble(Double.doubleToLongBits(0.7908661728086822) ^ 0x7FE94EC693773B2FL);
        if (obf == null) {
            return Float.intBitsToFloat(Float.floatToIntBits(3.3495596E38f) ^ 0x7F7BFE2C);
        }
        if (obf == 553648127) {
            obf = 16777215;
        }
        if ((obf & 0xFC000000) == 0x0) {
            obf |= 0xFF000000;
        }
        if (obf) {
            obf = ((obf & 0xFCFCFC) >> 2 | (obf & 0xFF000000));
        }
        CharData[] obf2 = this.charData;
        final float obf3 = (obf >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.06778959f) ^ 0x7EF5D545);
        boolean obf4 = false;
        boolean obf5 = false;
        boolean obf6 = false;
        boolean obf7 = false;
        boolean obf8 = false;
        final boolean obf9 = true;
        obf *= Double.longBitsToDouble(Double.doubleToLongBits(0.6719917477213301) ^ 0x7FE580F4D674A3CDL);
        obf *= Double.longBitsToDouble(Double.doubleToLongBits(0.24022634744417468) ^ 0x7FCEBFBCA8F48383L);
        GL11.glPushMatrix();
        GlStateManager.scale(Double.longBitsToDouble(Double.doubleToLongBits(16.86885928689303) ^ 0x7FD0DE6D8FEE080DL), Double.longBitsToDouble(Double.doubleToLongBits(21.244703112541455) ^ 0x7FD53EA4DCF9984FL), Double.longBitsToDouble(Double.doubleToLongBits(11.551609940359922) ^ 0x7FC71A6C9E3BFD1FL));
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color((obf >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.0101898415f) ^ 0x7F59F34B), (obf >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.27121183f) ^ 0x7DF5DC47), (obf & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.032521408f) ^ 0x7E7A352B), obf3);
        final int obf10 = obf.length();
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(this.tex.getGlTextureId());
        GL11.glBindTexture(3553, this.tex.getGlTextureId());
        for (int obf11 = 0; obf11 < obf10; ++obf11) {
            final char obf12 = obf.charAt(obf11);
            if (obf12 == '§' && obf11 < obf10) {
                int obf13 = 21;
                try {
                    obf13 = "0123456789abcdefklmnor".indexOf(obf.charAt(obf11 + 1));
                }
                catch (Exception ex) {}
                if (obf13 < 16) {
                    obf5 = false;
                    obf6 = false;
                    obf4 = false;
                    obf8 = false;
                    obf7 = false;
                    GlStateManager.bindTexture(this.tex.getGlTextureId());
                    obf2 = this.charData;
                    if (obf13 < 0 || obf13 > 15) {
                        obf13 = 15;
                    }
                    if (obf) {
                        obf13 += 16;
                    }
                    final int obf14 = this.colorCode[obf13];
                    GlStateManager.color((obf14 >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.1586952f) ^ 0x7D5D80FF), (obf14 >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.10998004f) ^ 0x7E9E3D37), (obf14 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.011190793f) ^ 0x7F485996), obf3);
                }
                else if (obf13 == 16) {
                    obf4 = true;
                }
                else if (obf13 == 17) {
                    obf5 = true;
                    if (obf6) {
                        GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                        obf2 = this.boldItalicChars;
                    }
                    else {
                        GlStateManager.bindTexture(this.texBold.getGlTextureId());
                        obf2 = this.boldChars;
                    }
                }
                else if (obf13 == 18) {
                    obf7 = true;
                }
                else if (obf13 == 19) {
                    obf8 = true;
                }
                else if (obf13 == 20) {
                    obf6 = true;
                    if (obf5) {
                        GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                        obf2 = this.boldItalicChars;
                    }
                    else {
                        GlStateManager.bindTexture(this.texItalic.getGlTextureId());
                        obf2 = this.italicChars;
                    }
                }
                else if (obf13 == 21) {
                    obf5 = false;
                    obf6 = false;
                    obf4 = false;
                    obf8 = false;
                    obf7 = false;
                    GlStateManager.color((obf >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.013295758f) ^ 0x7F26D673), (obf >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.011376684f) ^ 0x7F456545), (obf & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.008614488f) ^ 0x7F7223C8), obf3);
                    GlStateManager.bindTexture(this.tex.getGlTextureId());
                    obf2 = this.charData;
                }
                ++obf11;
            }
            else if (obf12 < obf2.length && obf12 >= '\0') {
                GL11.glBegin(4);
                this.drawChar(obf2, obf12, (float)obf, (float)obf);
                GL11.glEnd();
                if (obf7) {
                    this.drawLine(obf, obf + obf2[obf12].height / 2, obf + obf2[obf12].width - Double.longBitsToDouble(Double.doubleToLongBits(1.870049566886374) ^ 0x7FDDEBB9183AD0A7L), obf + obf2[obf12].height / 2, Float.intBitsToFloat(Float.floatToIntBits(159.3325f) ^ 0x7C9F551F));
                }
                if (obf8) {
                    this.drawLine(obf, obf + obf2[obf12].height - Double.longBitsToDouble(Double.doubleToLongBits(0.20735946392334068) ^ 0x7FCA8AC142088E37L), obf + obf2[obf12].width - Double.longBitsToDouble(Double.doubleToLongBits(0.22954613115765063) ^ 0x7FED61C4831F6A73L), obf + obf2[obf12].height - Double.longBitsToDouble(Double.doubleToLongBits(0.6244797757290504) ^ 0x7FE3FBBD02B8A145L), Float.intBitsToFloat(Float.floatToIntBits(7.5334535f) ^ 0x7F71120D));
                }
                obf += obf2[obf12].width - 8 + this.charOffset;
            }
        }
        GL11.glHint(3155, 4352);
        GL11.glPopMatrix();
        return (float)obf / Float.intBitsToFloat(Float.floatToIntBits(0.5038657f) ^ 0x7F00FD58);
    }
    
    public List<String> wrapWords(final String obf, final double obf) {
        final List obf2 = new ArrayList();
        if (this.getStringWidth(obf) > obf) {
            final String[] obf3 = obf.split(" ");
            String obf4 = "";
            char obf5 = '\uffff';
            for (final String obf6 : obf3) {
                for (int obf7 = 0; obf7 < obf6.toCharArray().length; ++obf7) {
                    final char obf8 = obf6.toCharArray()[obf7];
                    if (obf8 == '§') {
                        if (obf7 < obf6.toCharArray().length - 1) {
                            obf5 = obf6.toCharArray()[obf7 + 1];
                        }
                    }
                }
                if (this.getStringWidth(obf4 + obf6 + " ") < obf) {
                    obf4 = obf4 + obf6 + " ";
                }
                else {
                    obf2.add(obf4);
                    obf4 = "§" + obf5 + obf6 + " ";
                }
            }
            if (obf4.length() > 0) {
                if (this.getStringWidth(obf4) < obf) {
                    obf2.add("§" + obf5 + obf4 + " ");
                    obf4 = "";
                }
                else {
                    for (final String obf9 : this.formatString(obf4, obf)) {
                        obf2.add(obf9);
                    }
                }
            }
        }
        else {
            obf2.add(obf);
        }
        return (List<String>)obf2;
    }
    
    public void drawLine(final double obf, final double obf, final double obf, final double obf, final float obf) {
        GL11.glDisable(3553);
        GL11.glLineWidth(obf);
        GL11.glBegin(1);
        GL11.glVertex2d(obf, obf);
        GL11.glVertex2d(obf, obf);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public String getFontName() {
        return this.fontName;
    }
    
    public void setupMinecraftColorcodes() {
        for (int i = 0; i < 32; ++i) {
            final int n = (i >> 3 & 0x1) * 85;
            int n2 = (i >> 2 & 0x1) * 170 + n;
            int n3 = (i >> 1 & 0x1) * 170 + n;
            int n4 = (i >> 0 & 0x1) * 170 + n;
            if (i == 6) {
                n2 += 85;
            }
            if (i >= 16) {
                n2 /= 4;
                n3 /= 4;
                n4 /= 4;
            }
            this.colorCode[i] = ((n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | (n4 & 0xFF));
        }
    }
    
    public float drawString(final String obf, final float n, final float n2, final int obf2) {
        return this.drawString(obf, n, n2, obf2, false);
    }
    
    public float drawCenteredStringWithShadow(final String obf, final float n, final float n2, final int obf2) {
        return this.drawStringWithShadow(obf, n - this.getStringWidth(obf) / 2, n2, obf2);
    }
    
    public void setFontName(final String fontName) {
        this.fontName = fontName;
    }
    
    public void setFontSize(final int fontSize) {
        this.fontSize = fontSize;
    }
    
    @Override
    public int getStringWidth(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        CharData[] array = this.charData;
        int n2 = 0;
        int n3 = 0;
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '§') {
                if (i < length) {
                    final int index = "0123456789abcdefklmnor".indexOf(char1);
                    if (index < 16) {
                        n2 = 0;
                        n3 = 0;
                    }
                    else if (index == 17) {
                        n2 = 1;
                        if (n3 != 0) {
                            array = this.boldItalicChars;
                        }
                        else {
                            array = this.boldChars;
                        }
                    }
                    else if (index == 20) {
                        n3 = 1;
                        if (n2 != 0) {
                            array = this.boldItalicChars;
                        }
                        else {
                            array = this.italicChars;
                        }
                    }
                    else if (index == 21) {
                        n2 = 0;
                        n3 = 0;
                        array = this.charData;
                    }
                    ++i;
                    continue;
                }
            }
            if (char1 < array.length && char1 >= '\0') {
                n += array[char1].width - 8 + this.charOffset;
            }
        }
        return n / 2;
    }
    
    public List<String> formatString(final String obf, final double obf) {
        final List obf2 = new ArrayList();
        String obf3 = "";
        char obf4 = '\uffff';
        final char[] obf5 = obf.toCharArray();
        for (int obf6 = 0; obf6 < obf5.length; ++obf6) {
            final char obf7 = obf5[obf6];
            if (obf7 == '§' && obf6 < obf5.length - 1) {
                obf4 = obf5[obf6 + 1];
            }
            if (this.getStringWidth(obf3 + obf7) < obf) {
                obf3 += obf7;
            }
            else {
                obf2.add(obf3);
                obf3 = "§" + obf4 + obf7;
            }
        }
        if (obf3.length() > 0) {
            obf2.add(obf3);
        }
        return (List<String>)obf2;
    }
    
    public void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }
    
    public float drawCenteredString(final String obf, final float obf, final float obf, final int obf) {
        return this.drawString(obf, obf - this.getStringWidth(obf) / 2, obf, obf);
    }
    
    public int getFontSize() {
        return this.fontSize;
    }
    
    @Override
    public void setFont(final Font obf) {
        super.setFont(obf);
        this.setupBoldItalicIDs();
    }
}
