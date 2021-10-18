// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import me.sazked.leux.client.event.events.EventRenderEntityModel;
import me.sazked.leux.client.modules.Category;
import net.minecraft.util.math.Vec3d;
import java.util.Iterator;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityXPOrb;
import me.sazked.leux.client.util.RenderUtil;
import java.awt.Color;
import net.minecraft.client.renderer.RenderGlobal;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.AxisAlignedBB;
import me.sazked.leux.client.util.EntityUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.event.events.EventRender;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Chams extends Module
{
    public Setting r;
    public Setting b;
    public Setting crystals;
    public Setting players;
    public Setting self;
    public Setting brightness;
    public Setting items;
    public Setting pearl;
    public Setting a;
    public Setting xpbottles;
    public Setting scale;
    public Setting mobs;
    public Setting width;
    public Setting g;
    public Setting mode;
    public Setting xporbs;
    public Setting lines;
    public Setting cwidth;
    public Setting box_a;
    public Setting rainbow_mode;
    public Setting sat;
    public Setting top;
    
    @Override
    public void update() {
        if (this.rainbow_mode.get_value(true)) {
            this.cycle_rainbow();
        }
    }
    
    @Override
    public void render(final EventRender obf) {
        if (this.items.get_value(true)) {
            int obf2 = 0;
            for (final Entity obf3 : Chams.mc.world.loadedEntityList) {
                if (obf3 instanceof EntityItem) {
                    if (Chams.mc.player.getDistanceSq(obf3) >= Double.longBitsToDouble(Double.doubleToLongBits(9.193258218251638E-4) ^ 0x7FED97DD2B67DF10L)) {
                        continue;
                    }
                    final Vec3d obf4 = EntityUtil.getInterpolatedRenderPos(obf3, Chams.mc.getRenderPartialTicks());
                    final AxisAlignedBB obf5 = new AxisAlignedBB(obf3.getEntityBoundingBox().minX - Double.longBitsToDouble(Double.doubleToLongBits(40.67661538142183) ^ 0x7FEDCF02CCAA0F29L) - obf3.posX + obf4.x, obf3.getEntityBoundingBox().minY - Double.longBitsToDouble(Double.doubleToLongBits(1.0867636063451086E308) ^ 0x7FE358542B6DCAF3L) - obf3.posY + obf4.y, obf3.getEntityBoundingBox().minZ - Double.longBitsToDouble(Double.doubleToLongBits(53.1163586290526) ^ 0x7FE3177D4F74A82EL) - obf3.posZ + obf4.z, obf3.getEntityBoundingBox().maxX + Double.longBitsToDouble(Double.doubleToLongBits(359.5829476047366) ^ 0x7FDFE0CA5947839BL) - obf3.posX + obf4.x, obf3.getEntityBoundingBox().maxY + Double.longBitsToDouble(Double.doubleToLongBits(9.353661110420726) ^ 0x7F9B2C8A8837A61FL) - obf3.posY + obf4.y, obf3.getEntityBoundingBox().maxZ + Double.longBitsToDouble(Double.doubleToLongBits(37.50958405335729) ^ 0x7FEB58A395444452L) - obf3.posZ + obf4.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(14.1355715f) ^ 0x7EE22B4D));
                    RenderGlobal.renderFilledBox(obf5.grow((double)this.scale.get_value(1)), this.r.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.010859691f) ^ 0x7F4EECD8), this.g.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.053382795f) ^ 0x7E25A7EB), this.b.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.013284018f) ^ 0x7F26A536), this.box_a.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.29076907f) ^ 0x7DEBDFAF));
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(obf5.grow((double)this.scale.get_value(1)), new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1)), Float.intBitsToFloat(Float.floatToIntBits(7.8799205f) ^ 0x7F7C284F));
                    if (++obf2 >= 50) {
                        break;
                    }
                    continue;
                }
            }
        }
        if (this.xporbs.get_value(true)) {
            int obf2 = 0;
            for (final Entity obf3 : Chams.mc.world.loadedEntityList) {
                if (obf3 instanceof EntityXPOrb && Chams.mc.player.getDistanceSq(obf3) < Double.longBitsToDouble(Double.doubleToLongBits(0.003185880570214993) ^ 0x7FC991469B75064BL)) {
                    final Vec3d obf4 = EntityUtil.getInterpolatedRenderPos(obf3, Chams.mc.getRenderPartialTicks());
                    final AxisAlignedBB obf5 = new AxisAlignedBB(obf3.getEntityBoundingBox().minX - Double.longBitsToDouble(Double.doubleToLongBits(142.06263345397034) ^ 0x7FC85B988E46179FL) - obf3.posX + obf4.x, obf3.getEntityBoundingBox().minY - Double.longBitsToDouble(Double.doubleToLongBits(1.788523195319862E308) ^ 0x7FEFD636894B0D23L) - obf3.posY + obf4.y, obf3.getEntityBoundingBox().minZ - Double.longBitsToDouble(Double.doubleToLongBits(408.84904969022483) ^ 0x7FD0140C2CB95A87L) - obf3.posZ + obf4.z, obf3.getEntityBoundingBox().maxX + Double.longBitsToDouble(Double.doubleToLongBits(4.561917079029695) ^ 0x7FBBA6FEA94C65D7L) - obf3.posX + obf4.x, obf3.getEntityBoundingBox().maxY + Double.longBitsToDouble(Double.doubleToLongBits(505.57003102762184) ^ 0x7FC6008741434CEFL) - obf3.posY + obf4.y, obf3.getEntityBoundingBox().maxZ + Double.longBitsToDouble(Double.doubleToLongBits(421.53774456020943) ^ 0x7FD3C1030393A2A3L) - obf3.posZ + obf4.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(6.284408f) ^ 0x7F4919DF));
                    RenderGlobal.renderFilledBox(obf5.grow((double)this.scale.get_value(1)), this.r.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.10664182f) ^ 0x7EA56707), this.g.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(1.1606215f) ^ 0x7CEB8F3F), this.b.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.00956674f) ^ 0x7F63BDD1), this.box_a.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.11946767f) ^ 0x7E8BAB77));
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(obf5.grow((double)this.scale.get_value(1)), new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1)), Float.intBitsToFloat(Float.floatToIntBits(11.220845f) ^ 0x7EB38895));
                    if (++obf2 >= 50) {
                        break;
                    }
                    continue;
                }
            }
        }
        if (this.pearl.get_value(true)) {
            int obf2 = 0;
            for (final Entity obf3 : Chams.mc.world.loadedEntityList) {
                if (obf3 instanceof EntityEnderPearl && Chams.mc.player.getDistanceSq(obf3) < Double.longBitsToDouble(Double.doubleToLongBits(0.005496924399603456) ^ 0x7FD50BF16865E9E9L)) {
                    final Vec3d obf4 = EntityUtil.getInterpolatedRenderPos(obf3, Chams.mc.getRenderPartialTicks());
                    final AxisAlignedBB obf5 = new AxisAlignedBB(obf3.getEntityBoundingBox().minX - Double.longBitsToDouble(Double.doubleToLongBits(45.96699400721412) ^ 0x7FEF625FEC33AD4BL) - obf3.posX + obf4.x, obf3.getEntityBoundingBox().minY - Double.longBitsToDouble(Double.doubleToLongBits(1.4740584754424929E308) ^ 0x7FEA3D366EEFC36DL) - obf3.posY + obf4.y, obf3.getEntityBoundingBox().minZ - Double.longBitsToDouble(Double.doubleToLongBits(386.78364283581055) ^ 0x7FD1B5105488614BL) - obf3.posZ + obf4.z, obf3.getEntityBoundingBox().maxX + Double.longBitsToDouble(Double.doubleToLongBits(48.92124945917874) ^ 0x7FE1EC72190CC92AL) - obf3.posX + obf4.x, obf3.getEntityBoundingBox().maxY + Double.longBitsToDouble(Double.doubleToLongBits(167.23799491875084) ^ 0x7FDD7E043E1C8D5FL) - obf3.posY + obf4.y, obf3.getEntityBoundingBox().maxZ + Double.longBitsToDouble(Double.doubleToLongBits(58.195823305847625) ^ 0x7FE48089256AAD1AL) - obf3.posZ + obf4.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(16.572134f) ^ 0x7E0493BB));
                    RenderGlobal.renderFilledBox(obf5.grow((double)this.scale.get_value(1)), this.r.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.014337168f) ^ 0x7F15E671), this.g.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.012043149f) ^ 0x7F3A50A1), this.b.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.01312692f) ^ 0x7F28124B), this.box_a.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.10786069f) ^ 0x7EA3E611));
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(obf5.grow((double)this.scale.get_value(1)), new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1)), Float.intBitsToFloat(Float.floatToIntBits(9.441428f) ^ 0x7E971017));
                    if (++obf2 >= 50) {
                        break;
                    }
                    continue;
                }
            }
        }
        if (this.xpbottles.get_value(true)) {
            int obf2 = 0;
            for (final Entity obf3 : Chams.mc.world.loadedEntityList) {
                if (obf3 instanceof EntityExpBottle) {
                    if (Chams.mc.player.getDistanceSq(obf3) >= Double.longBitsToDouble(Double.doubleToLongBits(0.002772547803617393) ^ 0x7FC53E7444948C5BL)) {
                        continue;
                    }
                    final Vec3d obf4 = EntityUtil.getInterpolatedRenderPos(obf3, Chams.mc.getRenderPartialTicks());
                    final AxisAlignedBB obf5 = new AxisAlignedBB(obf3.getEntityBoundingBox().minX - Double.longBitsToDouble(Double.doubleToLongBits(6.421037944546005) ^ 0x7FB036BD0BB1556FL) - obf3.posX + obf4.x, obf3.getEntityBoundingBox().minY - Double.longBitsToDouble(Double.doubleToLongBits(8.771846073663435E307) ^ 0x7FDF3A9333B0B535L) - obf3.posY + obf4.y, obf3.getEntityBoundingBox().minZ - Double.longBitsToDouble(Double.doubleToLongBits(181.34040413331743) ^ 0x7FCF337D0EAC192BL) - obf3.posZ + obf4.z, obf3.getEntityBoundingBox().maxX + Double.longBitsToDouble(Double.doubleToLongBits(412.6201705085063) ^ 0x7FD05075AE70A63DL) - obf3.posX + obf4.x, obf3.getEntityBoundingBox().maxY + Double.longBitsToDouble(Double.doubleToLongBits(118.34553847293522) ^ 0x7FE40F84D4FFA895L) - obf3.posY + obf4.y, obf3.getEntityBoundingBox().maxZ + Double.longBitsToDouble(Double.doubleToLongBits(253.16549150195866) ^ 0x7FC63CD22D4C0C2FL) - obf3.posZ + obf4.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(41.149715f) ^ 0x7DA4994F));
                    RenderGlobal.renderFilledBox(obf5.grow((double)this.scale.get_value(1)), this.r.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.011410422f) ^ 0x7F45F2C7), this.g.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.115300946f) ^ 0x7E9322E7), this.b.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.015402691f) ^ 0x7F035B92), this.box_a.get_value(1) / Float.intBitsToFloat(Float.floatToIntBits(0.014199527f) ^ 0x7F17A522));
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(obf5.grow((double)this.scale.get_value(1)), new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1)), Float.intBitsToFloat(Float.floatToIntBits(19.002691f) ^ 0x7E180583));
                    if (++obf2 >= 50) {
                        break;
                    }
                    continue;
                }
            }
        }
    }
    
    public Chams() {
        super(Category.render);
        this.mode = this.create("Mode", "ChamsMode", "Outline", this.combobox("Outline", "Wireframe"));
        this.players = this.create("Players", "ChamsPlayers", false);
        this.mobs = this.create("Mobs", "ChamsMobs", false);
        this.self = this.create("Self", "ChamsSelf", false);
        this.items = this.create("Items", "ChamsItems", false);
        this.xporbs = this.create("Xp Orbs", "ChamsXPO", false);
        this.xpbottles = this.create("Xp Bottles", "ChamsBottles", false);
        this.pearl = this.create("Pearls", "ChamsPearls", false);
        this.top = this.create("Top", "ChamsTop", false);
        this.crystals = this.create("Crystals", "Crystals", true);
        this.lines = this.create("Crystals Lines", "Lines", true);
        this.cwidth = this.create("Crystals Width", "Width", Double.longBitsToDouble(Double.doubleToLongBits(0.5824269615656409) ^ 0x7FE0C55BB861133BL), Double.longBitsToDouble(Double.doubleToLongBits(1.0305677766367974E308) ^ 0x7FE2583F43F2A303L), Double.longBitsToDouble(Double.doubleToLongBits(0.2310206500371187) ^ 0x7FE99215AC4E174CL));
        this.scale = this.create("Factor", "ChamsFactor", Double.longBitsToDouble(Double.doubleToLongBits(2.956416553996819E307) ^ 0x7FC50CE60167FC4FL), Double.longBitsToDouble(Double.doubleToLongBits(-28.88938159669969) ^ 0x7FCCE3AE83277D4BL), Double.longBitsToDouble(Double.doubleToLongBits(14.957772260493469) ^ 0x7FDDEA61202FABBFL));
        this.r = this.create("R", "ChamsR", 120, 0, 255);
        this.g = this.create("G", "ChamsG", 120, 0, 255);
        this.b = this.create("B", "ChamsB", 240, 0, 255);
        this.a = this.create("A", "ChamsA", 100, 0, 255);
        this.box_a = this.create("Box A", "ChamsABox", 100, 0, 255);
        this.width = this.create("Width", "ChamsWdith", Double.longBitsToDouble(Double.doubleToLongBits(0.3375002506014371) ^ 0x7FD5999AA6AE6605L), Double.longBitsToDouble(Double.doubleToLongBits(15.913439936862202) ^ 0x7FCFD3AE663F5DEFL), Double.longBitsToDouble(Double.doubleToLongBits(0.049204169836604465) ^ 0x7FBD3149F88CD2CFL));
        this.rainbow_mode = this.create("Rainbow", "ChamsRainbow", false);
        this.sat = this.create("Satiation", "ChamsSatiation", Double.longBitsToDouble(Double.doubleToLongBits(9.690227559110207) ^ 0x7FCAF8FC182BC3A7L), Double.longBitsToDouble(Double.doubleToLongBits(9.738931145050496E307) ^ 0x7FE155FBC3DD7568L), Double.longBitsToDouble(Double.doubleToLongBits(7.9128763794218555) ^ 0x7FEFA6C910CBA18BL));
        this.brightness = this.create("Brightness", "ChamsBrightness", Double.longBitsToDouble(Double.doubleToLongBits(19.47926245107148) ^ 0x7FDAE3296830141FL), Double.longBitsToDouble(Double.doubleToLongBits(1.1789669502054321E308) ^ 0x7FE4FC7EB3DAADEBL), Double.longBitsToDouble(Double.doubleToLongBits(5.181759245769113) ^ 0x7FE4BA1F18814BBEL));
        this.name = "Chams";
        this.tag = "Chams";
        this.description = "see even less (now with epic colours)";
    }
    
    public void cycle_rainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], (float)this.sat.get_value(1), (float)this.brightness.get_value(1));
        this.r.set_value(hsBtoRGB >> 16 & 0xFF);
        this.g.set_value(hsBtoRGB >> 8 & 0xFF);
        this.b.set_value(hsBtoRGB & 0xFF);
    }
    
    @Override
    public void on_render_model(final EventRenderEntityModel obf) {
        if (obf.stage == 0 && obf.entity != null && (this.self.get_value(true) || !obf.entity.equals((Object)Chams.mc.player))) {
            if (!this.players.get_value(true)) {
                if (obf.entity instanceof EntityPlayer) {
                    return;
                }
            }
            if (this.mobs.get_value(true) || !(obf.entity instanceof EntityMob)) {
                final Color obf2 = new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1));
                final boolean obf3 = Chams.mc.gameSettings.fancyGraphics;
                Chams.mc.gameSettings.fancyGraphics = false;
                final float obf4 = Chams.mc.gameSettings.gammaSetting;
                Chams.mc.gameSettings.gammaSetting = Float.intBitsToFloat(Float.floatToIntBits(1.3367028E-4f) ^ 0x7F1069D8);
                if (this.top.get_value(true)) {
                    obf.modelBase.render(obf.entity, obf.limbSwing, obf.limbSwingAmount, obf.age, obf.headYaw, obf.headPitch, obf.scale);
                }
                if (this.mode.in("outline")) {
                    RenderUtil.renderOne((float)this.width.get_value(1));
                    obf.modelBase.render(obf.entity, obf.limbSwing, obf.limbSwingAmount, obf.age, obf.headYaw, obf.headPitch, obf.scale);
                    GlStateManager.glLineWidth((float)this.width.get_value(1));
                    RenderUtil.renderTwo();
                    obf.modelBase.render(obf.entity, obf.limbSwing, obf.limbSwingAmount, obf.age, obf.headYaw, obf.headPitch, obf.scale);
                    GlStateManager.glLineWidth((float)this.width.get_value(1));
                    RenderUtil.renderThree();
                    RenderUtil.renderFour(obf2);
                    obf.modelBase.render(obf.entity, obf.limbSwing, obf.limbSwingAmount, obf.age, obf.headYaw, obf.headPitch, obf.scale);
                    GlStateManager.glLineWidth((float)this.width.get_value(1));
                    RenderUtil.renderFive();
                }
                else {
                    GL11.glPushMatrix();
                    GL11.glPushAttrib(1048575);
                    GL11.glPolygonMode(1028, 6913);
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glDisable(2929);
                    GL11.glEnable(2848);
                    GL11.glEnable(3042);
                    GlStateManager.blendFunc(770, 771);
                    GlStateManager.color((float)obf2.getRed(), (float)obf2.getGreen(), (float)obf2.getBlue(), (float)obf2.getAlpha());
                    GlStateManager.glLineWidth((float)this.width.get_value(1));
                    obf.modelBase.render(obf.entity, obf.limbSwing, obf.limbSwingAmount, obf.age, obf.headYaw, obf.headPitch, obf.scale);
                    GL11.glPopAttrib();
                    GL11.glPopMatrix();
                }
                if (!this.top.get_value(true)) {
                    obf.modelBase.render(obf.entity, obf.limbSwing, obf.limbSwingAmount, obf.age, obf.headYaw, obf.headPitch, obf.scale);
                }
                try {
                    Chams.mc.gameSettings.fancyGraphics = obf3;
                    Chams.mc.gameSettings.gammaSetting = obf4;
                }
                catch (Exception ex) {}
                obf.cancel();
            }
        }
    }
}
