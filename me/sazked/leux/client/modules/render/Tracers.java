// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import net.minecraft.util.math.Vec3d;
import java.awt.Color;
import org.lwjgl.opengl.GL11;
import java.util.function.Consumer;
import net.minecraft.client.renderer.GlStateManager;
import me.sazked.leux.client.event.events.EventRender;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.util.FriendUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Tracers extends Module
{
    public Setting range;
    public Setting friends;
    public Setting width;
    public Setting offset;
    
    public void lambda$render$0(final float[][] obf, final Entity obf) {
        if (!(obf instanceof EntityPlayer) || obf == Tracers.mc.player) {
            return;
        }
        final EntityPlayer obf2 = (EntityPlayer)obf;
        if (Tracers.mc.player.getDistance((Entity)obf2) > this.range.get_value(1)) {
            return;
        }
        if (FriendUtil.isFriend(obf2.getName()) && !this.friends.get_value(true)) {
            return;
        }
        obf[0] = this.getColorByDistance((Entity)obf2);
        this.drawLineToEntity((Entity)obf2, obf[0][0], obf[0][1], obf[0][2], obf[0][3]);
    }
    
    public Tracers() {
        super(Category.render);
        this.friends = this.create("Friends", "TracerFriends", true);
        this.range = this.create("Range", "TracerRange", 150, 0, 250);
        this.width = this.create("Width", "TracerWidth", Double.longBitsToDouble(Double.doubleToLongBits(4.103939641588151) ^ 0x7FE06A6F2745842BL), Double.longBitsToDouble(Double.doubleToLongBits(8.497186257583848E307) ^ 0x7FDE4040B2CE6A0DL), Double.longBitsToDouble(Double.doubleToLongBits(0.21289986799704205) ^ 0x7FDF404D892F5C93L));
        this.offset = this.create("Offset", "TracerOffset", Double.longBitsToDouble(Double.doubleToLongBits(3.4026402603102005E306) ^ 0x7F9361D0250C6AFFL), Double.longBitsToDouble(Double.doubleToLongBits(-0.16703205930300272) ^ 0x7FD5614E780B82CDL), Double.longBitsToDouble(Double.doubleToLongBits(1.7894502413103845) ^ 0x7FECA1969383F564L));
        this.name = "Tracers";
        this.tag = "Tracers";
        this.description = "DRAWS LINES";
    }
    
    @Override
    public void render(final EventRender obf) {
        if (Tracers.mc.world == null) {
            return;
        }
        GlStateManager.pushMatrix();
        final float[][] obf2 = new float[1][1];
        Tracers.mc.world.loadedEntityList.forEach(this::lambda$render$0);
        GlStateManager.popMatrix();
    }
    
    public double interpolate(final double n, final double n2) {
        return n2 + (n - n2) * Tracers.mc.getRenderPartialTicks();
    }
    
    public void drawLineFromPosToPos(final double obf, final double obf, final double obf, final double obf, final double obf, final double obf, final double obf, final float obf, final float obf, final float obf, final float obf) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth((float)this.width.get_value(1));
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(obf, obf, obf, obf);
        GlStateManager.disableLighting();
        GL11.glLoadIdentity();
        Tracers.mc.entityRenderer.orientCamera(Tracers.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(obf, obf, obf);
        GL11.glVertex3d(obf, obf, obf);
        GL11.glVertex3d(obf, obf, obf);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glColor3d(Double.longBitsToDouble(Double.doubleToLongBits(4.793261769530832) ^ 0x7FE32C4CD0353543L), Double.longBitsToDouble(Double.doubleToLongBits(30.884116451652883) ^ 0x7FCEE25574ADB467L), Double.longBitsToDouble(Double.doubleToLongBits(4.753285272659011) ^ 0x7FE3035D36EA83FDL));
        GlStateManager.enableLighting();
    }
    
    public float[] getColorByDistance(final Entity obf) {
        if (obf instanceof EntityPlayer) {
            if (FriendUtil.isFriend(obf.getName())) {
                return new float[] { Float.intBitsToFloat(Float.floatToIntBits(1.9587632E38f) ^ 0x7F135C68), Float.intBitsToFloat(Float.floatToIntBits(19.597212f) ^ 0x7E9CC717), Float.intBitsToFloat(Float.floatToIntBits(5.9183245f) ^ 0x7F3D62EA), Float.intBitsToFloat(Float.floatToIntBits(91.54992f) ^ 0x7D37198F) };
            }
        }
        final Color obf2 = new Color(Color.HSBtoRGB((float)(Math.max(Double.longBitsToDouble(Double.doubleToLongBits(1.314262415323285E307) ^ 0x7FB2B7394C219477L), Math.min(Tracers.mc.player.getDistanceSq(obf), Double.longBitsToDouble(Double.doubleToLongBits(0.0046614384179732545) ^ 0x7FD09FDF58EB92DDL)) / Double.longBitsToDouble(Double.doubleToLongBits(0.005715750497025826) ^ 0x7FD4E166174F243DL)) / Double.longBitsToDouble(Double.doubleToLongBits(0.36230751908926856) ^ 0x7FDF300BE06554DBL)), Float.intBitsToFloat(Float.floatToIntBits(5.424189f) ^ 0x7F2D92F5), Float.intBitsToFloat(Float.floatToIntBits(3.7464824f) ^ 0x7F230A93)) | 0xFF000000);
        return new float[] { obf2.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.069690205f) ^ 0x7EF1B9BD), obf2.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.008242696f) ^ 0x7F780C5F), obf2.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.011466961f) ^ 0x7F44DFEC), Float.intBitsToFloat(Float.floatToIntBits(10.785823f) ^ 0x7EAC92BB) };
    }
    
    public void drawLineToEntity(final Entity obf, final float obf, final float obf, final float obf, final float obf) {
        final double[] obf2 = this.interpolate(obf);
        this.drawLine(obf2[0], obf2[1], obf2[2], obf.height, obf, obf, obf, obf);
    }
    
    public void drawLine(final double obf, final double obf, final double obf, final double obf, final float obf, final float obf, final float obf, final float obf) {
        final Vec3d obf2 = new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.472927985532049E308) ^ 0x7FEA380FA080CD75L), Double.longBitsToDouble(Double.doubleToLongBits(1.032851396168973E308) ^ 0x7FE262A74A0966A3L), Double.longBitsToDouble(Double.doubleToLongBits(7.822441493688711) ^ 0x7FEF4A2E1A5910CCL)).rotatePitch(-(float)Math.toRadians(Tracers.mc.player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Tracers.mc.player.rotationYaw));
        this.drawLineFromPosToPos(obf2.x, obf2.y + Tracers.mc.player.getEyeHeight() + (float)this.offset.get_value(1), obf2.z, obf, obf, obf, obf, obf, obf, obf, obf);
    }
    
    public double[] interpolate(final Entity obf) {
        final double obf2 = this.interpolate(obf.posX, obf.lastTickPosX) - Tracers.mc.getRenderManager().renderPosX;
        final double obf3 = this.interpolate(obf.posY, obf.lastTickPosY) - Tracers.mc.getRenderManager().renderPosY;
        final double obf4 = this.interpolate(obf.posZ, obf.lastTickPosZ) - Tracers.mc.getRenderManager().renderPosZ;
        return new double[] { obf2, obf3, obf4 };
    }
}
