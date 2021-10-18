// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import net.minecraft.client.renderer.culling.Frustum;
import me.sazked.leux.client.modules.Category;
import net.minecraft.client.renderer.RenderGlobal;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3i;
import java.util.function.Consumer;
import java.util.Collection;
import java.util.ArrayList;
import me.sazked.leux.Leux;
import me.sazked.leux.client.event.events.EventRender;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.client.renderer.culling.ICamera;
import me.sazked.leux.client.modules.Module;

public class VoidESP extends Module
{
    public ICamera camera;
    public Setting void_radius;
    public List<BlockPos> void_blocks;
    
    @Override
    public void render(final EventRender obf) {
        final int obf2 = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int obf3 = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int obf4 = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        new ArrayList(this.void_blocks).forEach((Consumer)this::lambda$render$0);
    }
    
    @Override
    public void update() {
        if (VoidESP.mc.player == null) {
            return;
        }
        this.void_blocks.clear();
        final Vec3i obf = new Vec3i(VoidESP.mc.player.posX, VoidESP.mc.player.posY, VoidESP.mc.player.posZ);
        for (int obf2 = obf.getX() - this.void_radius.get_value(1); obf2 < obf.getX() + this.void_radius.get_value(1); ++obf2) {
            for (int obf3 = obf.getZ() - this.void_radius.get_value(1); obf3 < obf.getZ() + this.void_radius.get_value(1); ++obf3) {
                for (int obf4 = obf.getY() + this.void_radius.get_value(1); obf4 > obf.getY() - this.void_radius.get_value(1); --obf4) {
                    final BlockPos obf5 = new BlockPos(obf2, obf4, obf3);
                    if (this.is_void_hole(obf5)) {
                        this.void_blocks.add(obf5);
                    }
                }
            }
        }
    }
    
    public boolean is_void_hole(final BlockPos blockPos) {
        return blockPos.getY() == 0 && VoidESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR;
    }
    
    public void lambda$render$0(final BlockPos obf) {
        final AxisAlignedBB obf2 = new AxisAlignedBB(obf.getX() - VoidESP.mc.getRenderManager().viewerPosX, obf.getY() - VoidESP.mc.getRenderManager().viewerPosY, obf.getZ() - VoidESP.mc.getRenderManager().viewerPosZ, obf.getX() + 1 - VoidESP.mc.getRenderManager().viewerPosX, obf.getY() + 1 - VoidESP.mc.getRenderManager().viewerPosY, obf.getZ() + 1 - VoidESP.mc.getRenderManager().viewerPosZ);
        this.camera.setPosition(VoidESP.mc.getRenderViewEntity().posX, VoidESP.mc.getRenderViewEntity().posY, VoidESP.mc.getRenderViewEntity().posZ);
        if (this.camera.isBoundingBoxInFrustum(new AxisAlignedBB(obf2.minX + VoidESP.mc.getRenderManager().viewerPosX, obf2.minY + VoidESP.mc.getRenderManager().viewerPosY, obf2.minZ + VoidESP.mc.getRenderManager().viewerPosZ, obf2.maxX + VoidESP.mc.getRenderManager().viewerPosX, obf2.maxY + VoidESP.mc.getRenderManager().viewerPosY, obf2.maxZ + VoidESP.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(7.3646f) ^ 0x7F2BAACE));
            RenderGlobal.drawBoundingBox(obf2.minX, obf2.minY, obf2.minZ, obf2.maxX, obf2.maxY, obf2.maxZ, Float.intBitsToFloat(Float.floatToIntBits(0.11843861f) ^ 0x7E8D8FF1), Float.intBitsToFloat(Float.floatToIntBits(0.39296183f) ^ 0x7F69324B), Float.intBitsToFloat(Float.floatToIntBits(0.3100428f) ^ 0x7F6EBDEE), Float.intBitsToFloat(Float.floatToIntBits(2.739875f) ^ 0x7F2F5A1D));
            RenderGlobal.renderFilledBox(obf2.minX, obf2.minY, obf2.minZ, obf2.maxX, obf2.maxY, obf2.maxZ, Float.intBitsToFloat(Float.floatToIntBits(0.010560545f) ^ 0x7F520623), Float.intBitsToFloat(Float.floatToIntBits(0.52825874f) ^ 0x7EA73BF7), Float.intBitsToFloat(Float.floatToIntBits(0.9250687f) ^ 0x7E9CD14D), Float.intBitsToFloat(Float.floatToIntBits(7.063771f) ^ 0x7E834DC7));
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public VoidESP() {
        super(Category.render);
        this.void_radius = this.create("Range", "VoidESPRange", 6, 1, 10);
        this.void_blocks = new ArrayList<BlockPos>();
        this.camera = (ICamera)new Frustum();
        this.name = "Void ESP";
        this.tag = "VoidESP";
        this.description = "OH FUCK A DEEP HOLE";
    }
}
