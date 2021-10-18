// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.mixins;

import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.util.math.MathHelper;
import me.sazked.leux.client.util.Wrapper;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import java.awt.Color;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import me.sazked.leux.Leux;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import org.spongepowered.asm.mixin.Final;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderEnderCrystal.class })
public abstract class MixinEnderCrystal
{
    @Shadow
    public ModelBase modelEnderCrystal;
    @Shadow
    public ModelBase modelEnderCrystalNoBase;
    @Final
    @Shadow
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;
    
    @Shadow
    public abstract void doRender(final EntityEnderCrystal p0, final double p1, final double p2, final double p3, final float p4, final float p5);
    
    @Redirect(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void render1(final ModelBase modelBase, final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (Leux.get_hack_manager().get_module_with_tag("Chams").is_active() && Leux.get_setting_manager().get_setting_with_tag("Chams", "Crystals").get_value(true)) {
            return;
        }
        modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
    
    @Redirect(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", ordinal = 1))
    private void render2(final ModelBase modelBase, final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (Leux.get_hack_manager().get_module_with_tag("Chams").is_active() && Leux.get_setting_manager().get_setting_with_tag("Chams", "Crystals").get_value(true)) {
            return;
        }
        modelBase.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = { @At("RETURN") }, cancellable = true)
    public void IdoRender(final EntityEnderCrystal entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo callback) {
        if (Leux.get_hack_manager().get_module_with_tag("Chams").is_active() && Leux.get_setting_manager().get_setting_with_tag("Chams", "Crystals").get_value(true)) {
            final Color c = new Color(Leux.get_setting_manager().get_setting_with_tag("Chams", "ChamsR").get_value(1), Leux.get_setting_manager().get_setting_with_tag("Chams", "ChamsG").get_value(1), Leux.get_setting_manager().get_setting_with_tag("Chams", "ChamsB").get_value(1));
            GL11.glPushMatrix();
            final float f = entity.innerRotation + partialTicks;
            GlStateManager.translate(x, y, z);
            Wrapper.getMinecraft().getRenderManager().renderEngine.bindTexture(MixinEnderCrystal.ENDER_CRYSTAL_TEXTURES);
            float f2 = MathHelper.sin(f * 0.2f) / 2.0f + 0.5f;
            f2 += f2 * f2;
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -1.0E7f);
            GL11.glPushAttrib(1048575);
            if (!Leux.get_setting_manager().get_setting_with_tag("Chams", "Lines").get_value(true)) {
                GL11.glPolygonMode(1028, 6914);
            }
            else {
                GL11.glPolygonMode(1028, 6913);
            }
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, Leux.get_setting_manager().get_setting_with_tag("Chams", "ChamsA").get_value(1) / 255.0f);
            if (Leux.get_setting_manager().get_setting_with_tag("Chams", "Lines").get_value(true)) {
                GL11.glLineWidth((float)Leux.get_setting_manager().get_setting_with_tag("Chams", "Width").get_value(1.0));
            }
            if (entity.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)entity, 0.0f, f * 3.0f, f2 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)entity, 0.0f, f * 3.0f, f2 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            GL11.glPopAttrib();
            GL11.glPolygonOffset(1.0f, 100000.0f);
            GL11.glDisable(32823);
            GL11.glPopMatrix();
        }
    }
}
