// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import me.sazked.leux.client.guiandfont.FontUtil;
import me.sazked.leux.Leux;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.OpenGlHelper;
import java.awt.Color;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.EXTFramebufferObject;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.culling.ICamera;

public class RenderUtil
{
    public static ICamera camera;
    public static RenderItem itemRender;
    public static Minecraft mc;
    
    public static void drawArc(final float n, final float n2, final float n3, final float n4, final float n5, final int n6) {
        GL11.glBegin(4);
        for (int n7 = (int)(n6 / (360.0f / n4)) + 1; n7 <= n6 / (360.0f / n5); ++n7) {
            final double n8 = 6.283185307179586 * (n7 - 1) / n6;
            final double n9 = 6.283185307179586 * n7 / n6;
            GL11.glVertex2d((double)n, (double)n2);
            GL11.glVertex2d(n + Math.cos(n9) * n3, n2 + Math.sin(n9) * n3);
            GL11.glVertex2d(n + Math.cos(n8) * n3, n2 + Math.sin(n8) * n3);
        }
        glEnd();
    }
    
    public static AxisAlignedBB convertBox(final AxisAlignedBB obf) {
        return new AxisAlignedBB(obf.minX - RenderUtil.mc.getRenderManager().viewerPosX, obf.minY - RenderUtil.mc.getRenderManager().viewerPosY, obf.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, obf.maxX - RenderUtil.mc.getRenderManager().viewerPosX, obf.maxY - RenderUtil.mc.getRenderManager().viewerPosY, obf.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
    }
    
    public static void setupFBO(final Framebuffer obf) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(obf.depthBuffer);
        final int obf2 = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, obf2);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, RenderUtil.mc.displayWidth, RenderUtil.mc.displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, obf2);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, obf2);
    }
    
    public static void drawRect(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos((double)n, (double)n4, 0.0).color(n5 / 255.0f, n6 / 255.0f, n7 / 255.0f, n8).endVertex();
        buffer.pos((double)n3, (double)n4, 0.0).color(n5 / 255.0f, n6 / 255.0f, n7 / 255.0f, n8).endVertex();
        buffer.pos((double)n3, (double)n2, 0.0).color(n5 / 255.0f, n6 / 255.0f, n7 / 255.0f, n8).endVertex();
        buffer.pos((double)n, (double)n2, 0.0).color(n5 / 255.0f, n6 / 255.0f, n7 / 255.0f, n8).endVertex();
        instance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    static {
        RenderUtil.mc = Minecraft.getMinecraft();
        RenderUtil.itemRender = RenderUtil.mc.getRenderItem();
        RenderUtil.camera = (ICamera)new Frustum();
    }
    
    public static void GLPre(final boolean obf, final boolean obf, final boolean obf, final boolean obf, final boolean obf, final float obf) {
        if (obf) {
            GL11.glDisable(2896);
        }
        if (!obf) {
            GL11.glEnable(3042);
        }
        GL11.glLineWidth(obf);
        if (obf) {
            GL11.glDisable(3553);
        }
        if (obf) {
            GL11.glDisable(2929);
        }
        if (!obf) {
            GL11.glEnable(2848);
        }
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glHint(3154, 4354);
        GlStateManager.depthMask(false);
    }
    
    public static void drawArcOutline(final float n, final float n2, final float n3, final float n4, final float n5, final int n6) {
        GL11.glBegin(2);
        for (int n7 = (int)(n6 / (360.0f / n4)) + 1; n7 <= n6 / (360.0f / n5); ++n7) {
            final double n8 = 6.283185307179586 * n7 / n6;
            GL11.glVertex2d(n + Math.cos(n8) * n3, n2 + Math.sin(n8) * n3);
        }
        glEnd();
    }
    
    public static void renderFive() {
        GL11.glPolygonOffset(1.0f, 2000000.0f);
        GL11.glDisable(10754);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(2960);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glEnable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glPopAttrib();
    }
    
    public static void checkSetupFBO() {
        final Framebuffer framebuffer = RenderUtil.mc.getFramebuffer();
        if (framebuffer.depthBuffer > -1) {
            setupFBO(framebuffer);
            framebuffer.depthBuffer = -1;
        }
    }
    
    public static void glCleanup() {
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void renderOne(final float n) {
        checkSetupFBO();
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3008);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(n);
        GL11.glEnable(2848);
        GL11.glEnable(2960);
        GL11.glClear(1024);
        GL11.glClearStencil(15);
        GL11.glStencilFunc(512, 1, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void drawBox(final AxisAlignedBB obf, final int obf, final int obf, final int obf, final int obf, final boolean obf, final boolean obf) {
        try {
            glSetup();
            if (obf) {
                RenderGlobal.renderFilledBox(obf, obf / Float.intBitsToFloat(Float.floatToIntBits(0.041787248f) ^ 0x7E54291B), obf / Float.intBitsToFloat(Float.floatToIntBits(0.09566463f) ^ 0x7EBCEBD1), obf / Float.intBitsToFloat(Float.floatToIntBits(0.012867567f) ^ 0x7F2DD27D), obf / Float.intBitsToFloat(Float.floatToIntBits(0.04924391f) ^ 0x7E36B3FB));
            }
            if (obf) {
                RenderGlobal.drawSelectionBoundingBox(obf, obf / Float.intBitsToFloat(Float.floatToIntBits(0.048301112f) ^ 0x7E3AD763), obf / Float.intBitsToFloat(Float.floatToIntBits(0.007958243f) ^ 0x7F7D634A), obf / Float.intBitsToFloat(Float.floatToIntBits(0.013932837f) ^ 0x7F1B468E), obf / Float.intBitsToFloat(Float.floatToIntBits(0.09632834f) ^ 0x7EBA47CB) * Float.intBitsToFloat(Float.floatToIntBits(6.7627397f) ^ 0x7F18685D));
            }
            glCleanup();
        }
        catch (Exception ex) {}
    }
    
    public static void glSetup() {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.5f);
    }
    
    public static void glStart(final float obf, final float obf, final float obf, final float obf) {
        glrendermethod();
        GL11.glColor4f(obf, obf, obf, obf);
    }
    
    public static void glEnd() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void renderFour(final Color color) {
        setColor(color);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(10754);
        GL11.glPolygonOffset(1.0f, -2000000.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
    }
    
    public static void drawText(final BlockPos obf, final String obf) {
        GlStateManager.pushMatrix();
        glBillboardDistanceScaled(obf.getX() + Float.intBitsToFloat(Float.floatToIntBits(3.1226368f) ^ 0x7F47D948), obf.getY() + Float.intBitsToFloat(Float.floatToIntBits(29.35288f) ^ 0x7EEAD2B3), obf.getZ() + Float.intBitsToFloat(Float.floatToIntBits(20.614141f) ^ 0x7EA4E9C3), (EntityPlayer)RenderUtil.mc.player, Float.intBitsToFloat(Float.floatToIntBits(5.123067f) ^ 0x7F23F02A));
        GlStateManager.disableDepth();
        GlStateManager.translate(-(RenderUtil.mc.fontRenderer.getStringWidth(obf) / Double.longBitsToDouble(Double.doubleToLongBits(0.5330878985813448) ^ 0x7FE10F0E5A499B92L)), Double.longBitsToDouble(Double.doubleToLongBits(4.4617262367342876E307) ^ 0x7FCFC4BF62E0255BL), Double.longBitsToDouble(Double.doubleToLongBits(1.7046447671420434E308) ^ 0x7FEE57FBADF771BEL));
        FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), obf, 0, 0, -5592406);
        GlStateManager.popMatrix();
    }
    
    public static void drawBlockOutline(final AxisAlignedBB obf, final Color obf, final float obf) {
        final float obf2 = obf.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.009222786f) ^ 0x7F681B2B);
        final float obf3 = obf.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.123005085f) ^ 0x7E84EA17);
        final float obf4 = obf.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.35055086f) ^ 0x7DCC7B67);
        final float obf5 = obf.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.014698557f) ^ 0x7F0FD237);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(obf);
        final Tessellator obf6 = Tessellator.getInstance();
        final BufferBuilder obf7 = obf6.getBuffer();
        obf7.begin(3, DefaultVertexFormats.POSITION_COLOR);
        obf7.pos(obf.minX, obf.minY, obf.minZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.minX, obf.minY, obf.maxZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.maxX, obf.minY, obf.maxZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.maxX, obf.minY, obf.minZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.minX, obf.minY, obf.minZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.minX, obf.maxY, obf.minZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.minX, obf.maxY, obf.maxZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.minX, obf.minY, obf.maxZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.maxX, obf.minY, obf.maxZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.maxX, obf.maxY, obf.maxZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.minX, obf.maxY, obf.maxZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.maxX, obf.maxY, obf.maxZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.maxX, obf.maxY, obf.minZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.maxX, obf.minY, obf.minZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.maxX, obf.maxY, obf.minZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf7.pos(obf.minX, obf.maxY, obf.minZ).color(obf2, obf3, obf4, obf5).endVertex();
        obf6.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void glrendermethod() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        final double viewerPosX = RenderUtil.mc.getRenderManager().viewerPosX;
        final double viewerPosY = RenderUtil.mc.getRenderManager().viewerPosY;
        final double viewerPosZ = RenderUtil.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glTranslated(-viewerPosX, -viewerPosY, -viewerPosZ);
    }
    
    public static void glBillboard(final float obf, final float obf, final float obf) {
        final float obf2 = Float.intBitsToFloat(Float.floatToIntBits(123.49515f) ^ 0x7E2C898B);
        GlStateManager.translate(obf - RenderUtil.mc.getRenderManager().renderPosX, obf - RenderUtil.mc.getRenderManager().renderPosY, obf - RenderUtil.mc.getRenderManager().renderPosZ);
        GlStateManager.glNormal3f(Float.intBitsToFloat(Float.floatToIntBits(1.9877966E38f) ^ 0x7F158B92), Float.intBitsToFloat(Float.floatToIntBits(24.671041f) ^ 0x7E455E4B), Float.intBitsToFloat(Float.floatToIntBits(3.0874667E38f) ^ 0x7F684672));
        GlStateManager.rotate(-RenderUtil.mc.player.rotationYaw, Float.intBitsToFloat(Float.floatToIntBits(2.5949256E38f) ^ 0x7F433873), Float.intBitsToFloat(Float.floatToIntBits(4.1267996f) ^ 0x7F040EBE), Float.intBitsToFloat(Float.floatToIntBits(6.797345E37f) ^ 0x7E4C8CD7));
        GlStateManager.rotate(RenderUtil.mc.player.rotationPitch, (RenderUtil.mc.gameSettings.thirdPersonView == 2) ? Float.intBitsToFloat(Float.floatToIntBits(-13.174676f) ^ 0x7ED2CB79) : Float.intBitsToFloat(Float.floatToIntBits(16.742758f) ^ 0x7E05F12B), Float.intBitsToFloat(Float.floatToIntBits(7.868276E36f) ^ 0x7CBD6BFF), Float.intBitsToFloat(Float.floatToIntBits(3.260076E38f) ^ 0x7F7542C8));
        GlStateManager.scale(Float.intBitsToFloat(Float.floatToIntBits(-447.1017f) ^ 0x7F05F90A), Float.intBitsToFloat(Float.floatToIntBits(-294.7488f) ^ 0x7F492BD6), Float.intBitsToFloat(Float.floatToIntBits(58.12773f) ^ 0x7EB2F6C3));
    }
    
    public static void drawRect(final float obf, final float obf, final float obf, final float obf, final int obf) {
        final float obf2 = (obf >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.46282622f) ^ 0x7D93F78F);
        final float obf3 = (obf >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.062100288f) ^ 0x7E015CDF);
        final float obf4 = (obf >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.008978965f) ^ 0x7F6C1C82);
        final float obf5 = (obf & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.2934794f) ^ 0x7DE942EF);
        final Tessellator obf6 = Tessellator.getInstance();
        final BufferBuilder obf7 = obf6.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        obf7.begin(7, DefaultVertexFormats.POSITION_COLOR);
        obf7.pos((double)obf, (double)obf, Double.longBitsToDouble(Double.doubleToLongBits(7.917560687227925E307) ^ 0x7FDC2FFCD58C2F29L)).color(obf3, obf4, obf5, obf2).endVertex();
        obf7.pos((double)obf, (double)obf, Double.longBitsToDouble(Double.doubleToLongBits(1.6868258546317347E308) ^ 0x7FEE06C88047F554L)).color(obf3, obf4, obf5, obf2).endVertex();
        obf7.pos((double)obf, (double)obf, Double.longBitsToDouble(Double.doubleToLongBits(1.3242347053587496E308) ^ 0x7FE792791751904EL)).color(obf3, obf4, obf5, obf2).endVertex();
        obf7.pos((double)obf, (double)obf, Double.longBitsToDouble(Double.doubleToLongBits(1.4716697865129809E308) ^ 0x7FEA3253D67706DEL)).color(obf3, obf4, obf5, obf2).endVertex();
        obf6.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void setColor(final Color color) {
        GL11.glColor4d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, color.getAlpha() / 255.0);
    }
    
    public static void renderTwo() {
        GL11.glStencilFunc(512, 0, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void drawBox(final BlockPos obf, final Color obf, final boolean obf, final boolean obf) {
        final AxisAlignedBB obf2 = new AxisAlignedBB(obf.getX() - RenderUtil.mc.getRenderManager().viewerPosX, obf.getY() - RenderUtil.mc.getRenderManager().viewerPosY, obf.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, obf.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, obf.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, obf.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        drawBox(obf2, obf.getRed(), obf.getGreen(), obf.getBlue(), obf.getAlpha(), obf, obf);
    }
    
    public static void glBillboardDistanceScaled(final float obf, final float obf, final float obf, final EntityPlayer obf, final float obf) {
        glBillboard(obf, obf, obf);
        final int obf2 = (int)obf.getDistance((double)obf, (double)obf, (double)obf);
        float obf3 = obf2 / Float.intBitsToFloat(Float.floatToIntBits(0.110734396f) ^ 0x7DE2C8B7) / (Float.intBitsToFloat(Float.floatToIntBits(0.87776697f) ^ 0x7F60B556) + (Float.intBitsToFloat(Float.floatToIntBits(0.19101016f) ^ 0x7E43982B) - obf));
        if (obf3 < Float.intBitsToFloat(Float.floatToIntBits(8.165982f) ^ 0x7E82A7DD)) {
            obf3 = Float.intBitsToFloat(Float.floatToIntBits(4.5044684f) ^ 0x7F10249B);
        }
        GlStateManager.scale(obf3, obf3, obf3);
    }
    
    public static void renderThree() {
        GL11.glStencilFunc(514, 1, 15);
        GL11.glStencilOp(7680, 7680, 7680);
        GL11.glPolygonMode(1032, 6913);
    }
}
