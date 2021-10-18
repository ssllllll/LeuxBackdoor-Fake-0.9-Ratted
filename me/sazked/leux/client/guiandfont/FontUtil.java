// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiandfont;

import me.sazked.leux.Leux;
import net.minecraft.client.Minecraft;

public class FontUtil
{
    public static Minecraft mc;
    
    public static float drawStringWithShadow(final boolean obf, final String obf, final double obf, final double obf, final int obf) {
        if (obf) {
            return Leux.fontRenderer.drawStringWithShadow(obf, (float)obf, (float)obf, obf);
        }
        return (float)FontUtil.mc.fontRenderer.drawStringWithShadow(obf, (float)obf, (float)obf, obf);
    }
    
    public static int getFontHeight(final boolean obf) {
        if (obf) {
            return Leux.fontRenderer.getHeight();
        }
        return FontUtil.mc.fontRenderer.FONT_HEIGHT;
    }
    
    public static float drawKeyStringWithShadow(final boolean b, final String obf, final int n, final int n2, final int obf2) {
        if (b) {
            return Leux.fontRenderer.drawStringWithShadow(obf, n, n2, obf2);
        }
        return (float)FontUtil.mc.fontRenderer.drawStringWithShadow(obf, (float)n, (float)n2, obf2);
    }
    
    static {
        FontUtil.mc = Minecraft.getMinecraft();
    }
    
    public static float drawStringWithShadow(final boolean b, final String obf, final int n, final int n2, final int obf2) {
        if (b) {
            return Leux.fontRenderer.drawStringWithShadow(obf, (float)n, (float)n2, obf2);
        }
        return (float)FontUtil.mc.fontRenderer.drawStringWithShadow(obf, (float)n, (float)n2, obf2);
    }
    
    public static int getStringWidth(final boolean obf, final String obf) {
        if (obf) {
            return Leux.fontRenderer.getStringWidth(obf);
        }
        return FontUtil.mc.fontRenderer.getStringWidth(obf);
    }
}
