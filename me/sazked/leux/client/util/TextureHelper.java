// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.client.renderer.texture.ITextureObject;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;

public class TextureHelper
{
    public static Minecraft mc;
    
    public static void bindTexture(final ResourceLocation obf) {
        try {
            final ITextureObject obf2 = TextureHelper.mc.getTextureManager().getTexture(obf);
            GL11.glBindTexture(3553, obf2.getGlTextureId());
        }
        catch (Exception ex) {}
    }
    
    public static void drawTexture(final ResourceLocation obf, final float obf, final float obf, final float obf, final float obf) {
        GL11.glPushMatrix();
        final float obf2 = obf / Float.intBitsToFloat(Float.floatToIntBits(0.2784178f) ^ 0x7E8E8CC7);
        GL11.glEnable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2848);
        GL11.glColor4f(Float.intBitsToFloat(Float.floatToIntBits(12.973245f) ^ 0x7ECF9269), Float.intBitsToFloat(Float.floatToIntBits(5.6685f) ^ 0x7F35645A), Float.intBitsToFloat(Float.floatToIntBits(15.407899f) ^ 0x7EF686C1), Float.intBitsToFloat(Float.floatToIntBits(10.376279f) ^ 0x7EA6053D));
        bindTexture(obf);
        GL11.glBegin(7);
        GL11.glTexCoord2d((double)(Float.intBitsToFloat(Float.floatToIntBits(2.0519186E38f) ^ 0x7F1A5E84) / obf2), (double)(Float.intBitsToFloat(Float.floatToIntBits(7.7746323E36f) ^ 0x7CBB2ADF) / obf2));
        GL11.glVertex2d((double)obf, (double)obf);
        GL11.glTexCoord2d((double)(Float.intBitsToFloat(Float.floatToIntBits(3.0920373E38f) ^ 0x7F689E79) / obf2), (double)((Float.intBitsToFloat(Float.floatToIntBits(3.033947E38f) ^ 0x7F643FB2) + obf2) / obf2));
        GL11.glVertex2d((double)obf, (double)(obf + obf));
        GL11.glTexCoord2d((double)((Float.intBitsToFloat(Float.floatToIntBits(1.466514E38f) ^ 0x7EDCA811) + obf2) / obf2), (double)((Float.intBitsToFloat(Float.floatToIntBits(1.8065045E38f) ^ 0x7F07E803) + obf2) / obf2));
        GL11.glVertex2d((double)(obf + obf), (double)(obf + obf));
        GL11.glTexCoord2d((double)((Float.intBitsToFloat(Float.floatToIntBits(2.2242502E38f) ^ 0x7F275580) + obf2) / obf2), (double)(Float.intBitsToFloat(Float.floatToIntBits(7.811066E37f) ^ 0x7E6B0E43) / obf2));
        GL11.glVertex2d((double)(obf + obf), (double)obf);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        TextureHelper.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack((Item)Items.DIAMOND_HELMET), 999999, 999999);
    }
    
    static {
        TextureHelper.mc = Minecraft.getMinecraft();
    }
}
