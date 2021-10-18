// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import java.awt.Color;
import me.sazked.leux.client.util.DamageUtil;
import net.minecraft.util.text.TextFormatting;
import me.sazked.leux.client.event.events.EventRender;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import me.sazked.leux.client.util.RenderUtil;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import me.sazked.leux.client.modules.chat.Notifications;
import java.util.Objects;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.nbt.NBTTagList;
import java.util.Iterator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.util.EnemyUtil;
import me.sazked.leux.client.util.FriendUtil;
import net.minecraft.entity.player.EntityPlayer;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventRenderName;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class NameTags extends Module
{
    public Setting show_health;
    public Setting b;
    public Setting r;
    public static boolean $assertionsDisabled;
    public Setting rainbow_mode;
    public Setting simplify;
    public Setting show_armor;
    public Setting m_scale;
    public Setting reverse;
    public Setting show_ping;
    public Setting range;
    public Setting show_invis;
    public Setting show_totems;
    @EventHandler
    public Listener<EventRenderName> on_render_name;
    public Setting g;
    public Setting sat;
    public Setting brightness;
    
    public int getDisplayColour(final EntityPlayer obf) {
        final int obf2 = -5592406;
        if (FriendUtil.isFriend(obf.getName())) {
            return -11157267;
        }
        if (EnemyUtil.isEnemy(obf.getName())) {
            return -49632;
        }
        return obf2;
    }
    
    public float getBiggestArmorTag(final EntityPlayer obf) {
        float obf2 = Float.intBitsToFloat(Float.floatToIntBits(3.1659555E38f) ^ 0x7F6E2E16);
        boolean obf3 = false;
        for (final ItemStack obf4 : obf.inventory.armorInventory) {
            float obf5 = Float.intBitsToFloat(Float.floatToIntBits(2.7722275E38f) ^ 0x7F508F29);
            if (obf4 != null) {
                final NBTTagList obf6 = obf4.getEnchantmentTagList();
                for (int obf7 = 0; obf7 < obf6.tagCount(); ++obf7) {
                    final short obf8 = obf6.getCompoundTagAt(obf7).getShort("id");
                    final Enchantment obf9 = Enchantment.getEnchantmentByID((int)obf8);
                    if (obf9 != null) {
                        obf5 += Float.intBitsToFloat(Float.floatToIntBits(0.21070614f) ^ 0x7F57C35A);
                        obf3 = true;
                    }
                }
            }
            if (obf5 > obf2) {
                obf2 = obf5;
            }
        }
        final ItemStack obf10 = obf.getHeldItemMainhand().copy();
        if (obf10.hasEffect()) {
            float obf11 = Float.intBitsToFloat(Float.floatToIntBits(2.908535E38f) ^ 0x7F5AD059);
            final NBTTagList obf12 = obf10.getEnchantmentTagList();
            for (int obf13 = 0; obf13 < obf12.tagCount(); ++obf13) {
                final short obf14 = obf12.getCompoundTagAt(obf13).getShort("id");
                final Enchantment obf15 = Enchantment.getEnchantmentByID((int)obf14);
                if (obf15 != null) {
                    obf11 += Float.intBitsToFloat(Float.floatToIntBits(0.1867901f) ^ 0x7F3F45E7);
                    obf3 = true;
                }
            }
            if (obf11 > obf2) {
                obf2 = obf11;
            }
        }
        ItemStack obf4 = obf.getHeldItemOffhand().copy();
        if (obf4.hasEffect()) {
            float obf5 = Float.intBitsToFloat(Float.floatToIntBits(1.880481E38f) ^ 0x7F0D78BF);
            final NBTTagList obf6 = obf4.getEnchantmentTagList();
            for (int obf7 = 0; obf7 < obf6.tagCount(); ++obf7) {
                final short obf8 = obf6.getCompoundTagAt(obf7).getShort("id");
                final Enchantment obf9 = Enchantment.getEnchantmentByID((int)obf8);
                if (obf9 != null) {
                    obf5 += Float.intBitsToFloat(Float.floatToIntBits(0.12973769f) ^ 0x7F04D9F5);
                    obf3 = true;
                }
            }
            if (obf5 > obf2) {
                obf2 = obf5;
            }
        }
        return (obf3 ? 0 : 20) + obf2;
    }
    
    public String getDisplayTag(final EntityPlayer obf) {
        String obf2 = obf.getDisplayNameString();
        if (!this.show_health.get_value(true)) {
            return obf2;
        }
        final float obf3 = obf.getHealth() + obf.getAbsorptionAmount();
        if (obf3 <= Float.intBitsToFloat(Float.floatToIntBits(2.8463894E38f) ^ 0x7F562377)) {
            return obf2 + " - DEAD";
        }
        String obf4;
        if (obf3 > Float.intBitsToFloat(Float.floatToIntBits(0.014588601f) ^ 0x7DA70507)) {
            obf4 = this.section_sign() + "a";
        }
        else if (obf3 > Float.intBitsToFloat(Float.floatToIntBits(0.51036495f) ^ 0x7EA2A747)) {
            obf4 = this.section_sign() + "2";
        }
        else if (obf3 > Float.intBitsToFloat(Float.floatToIntBits(0.21075226f) ^ 0x7F27CF71)) {
            obf4 = this.section_sign() + "e";
        }
        else if (obf3 > Float.intBitsToFloat(Float.floatToIntBits(0.21025112f) ^ 0x7F774C12)) {
            obf4 = this.section_sign() + "6";
        }
        else if (obf3 > Float.intBitsToFloat(Float.floatToIntBits(1.5326339f) ^ 0x7F642D59)) {
            obf4 = this.section_sign() + "c";
        }
        else {
            obf4 = this.section_sign() + "4";
        }
        String obf5 = "";
        if (this.show_ping.get_value(true)) {
            try {
                final int obf6 = Objects.requireNonNull(NameTags.mc.getConnection()).getPlayerInfo(obf.getUniqueID()).getResponseTime();
                if (obf6 > 300) {
                    obf5 = obf5 + this.section_sign() + "c";
                }
                else if (obf6 > 150) {
                    obf5 = obf5 + this.section_sign() + "e";
                }
                else {
                    obf5 = obf5 + this.section_sign() + "a";
                }
                obf5 = obf5 + obf6 + "ms ";
            }
            catch (Exception ex) {}
        }
        String obf7 = " ";
        if (this.show_totems.get_value(true)) {
            try {
                obf7 += ((Notifications.totem_pop_counter.get(obf.getName()) == null) ? (this.section_sign() + "70") : (this.section_sign() + "c -" + Notifications.totem_pop_counter.get(obf.getName())));
            }
            catch (Exception ex2) {}
        }
        if (Math.floor(obf3) == obf3) {
            obf2 = obf2 + obf4 + " " + ((obf3 > Float.intBitsToFloat(Float.floatToIntBits(1.1538901E38f) ^ 0x7EAD9E3B)) ? Integer.valueOf((int)Math.floor(obf3)) : "dead");
        }
        else {
            obf2 = obf2 + obf4 + " " + ((obf3 > Float.intBitsToFloat(Float.floatToIntBits(3.369527E38f) ^ 0x7F7D7EBB)) ? Integer.valueOf((int)obf3) : "dead");
        }
        return obf5 + this.section_sign() + "r" + obf2 + this.section_sign() + "r" + obf7;
    }
    
    public String section_sign() {
        return "§";
    }
    
    static {
        NameTags.$assertionsDisabled = !NameTags.class.desiredAssertionStatus();
    }
    
    public void renderNameTag(final EntityPlayer obf, final double obf, final double obf, final double obf, final float obf) {
        double obf2 = obf;
        obf2 += (obf.isSneaking() ? Double.longBitsToDouble(Double.doubleToLongBits(2.391539237155422) ^ 0x7FE321DF52D57773L) : Double.longBitsToDouble(Double.doubleToLongBits(3.1352468112143446) ^ 0x7FEF729A21DE0807L));
        final Entity obf3 = NameTags.mc.getRenderViewEntity();
        assert obf3 != null;
        final double obf4 = obf3.posX;
        final double obf5 = obf3.posY;
        final double obf6 = obf3.posZ;
        obf3.posX = this.interpolate(obf3.prevPosX, obf3.posX, obf);
        obf3.posY = this.interpolate(obf3.prevPosY, obf3.posY, obf);
        obf3.posZ = this.interpolate(obf3.prevPosZ, obf3.posZ, obf);
        final String obf7 = this.getDisplayTag(obf);
        final double obf8 = obf3.getDistance(obf + NameTags.mc.getRenderManager().viewerPosX, obf + NameTags.mc.getRenderManager().viewerPosY, obf + NameTags.mc.getRenderManager().viewerPosZ);
        final int obf9 = NameTags.mc.fontRenderer.getStringWidth(obf7) / 2;
        double obf10 = (Double.longBitsToDouble(Double.doubleToLongBits(60841.80351344709) ^ 0x7FB0C886FE1E1EB7L) + this.m_scale.get_value(1) * (obf8 * Double.longBitsToDouble(Double.doubleToLongBits(2.676221852724555) ^ 0x7FD65BD433816435L))) / Double.longBitsToDouble(Double.doubleToLongBits(0.002385113089781093) ^ 0x7FECC9F1D6F8CD3FL);
        if (obf8 <= Double.longBitsToDouble(Double.doubleToLongBits(0.21966704556228842) ^ 0x7FEC1E0CBC5977DFL)) {
            obf10 = Double.longBitsToDouble(Double.doubleToLongBits(414.5027202519017) ^ 0x7FE0FE8C0F66030CL);
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(Float.intBitsToFloat(Float.floatToIntBits(41.240566f) ^ 0x7DA4F657), Float.intBitsToFloat(Float.floatToIntBits(-1.0430198E-8f) ^ 0x7B842B7F));
        GlStateManager.disableLighting();
        GlStateManager.translate((float)obf, (float)obf2 + Float.intBitsToFloat(Float.floatToIntBits(4.3176775f) ^ 0x7F391959), (float)obf);
        GlStateManager.rotate(-NameTags.mc.getRenderManager().playerViewY, Float.intBitsToFloat(Float.floatToIntBits(6.190779E37f) ^ 0x7E3A4C07), Float.intBitsToFloat(Float.floatToIntBits(8.615287f) ^ 0x7E89D837), Float.intBitsToFloat(Float.floatToIntBits(2.8022682E38f) ^ 0x7F52D1B9));
        GlStateManager.rotate(NameTags.mc.getRenderManager().playerViewX, (NameTags.mc.gameSettings.thirdPersonView == 2) ? Float.intBitsToFloat(Float.floatToIntBits(-5.2444196f) ^ 0x7F27D249) : Float.intBitsToFloat(Float.floatToIntBits(23.016157f) ^ 0x7E382117), Float.intBitsToFloat(Float.floatToIntBits(2.4612958E38f) ^ 0x7F392AD5), Float.intBitsToFloat(Float.floatToIntBits(3.1351403E38f) ^ 0x7F6BDC9B));
        GlStateManager.scale(-obf10, -obf10, obf10);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        final boolean obf11 = FriendUtil.isFriend(obf.getName());
        final boolean obf12 = EnemyUtil.isEnemy(obf.getName());
        int obf13 = this.r.get_value(1);
        int obf14 = this.g.get_value(1);
        int obf15 = this.b.get_value(1);
        if (obf11) {
            obf13 = 157;
            obf14 = 99;
            obf15 = 255;
        }
        if (obf12) {
            obf13 = 255;
            obf14 = 40;
            obf15 = 7;
        }
        RenderUtil.drawRect(-obf9 - 2 - Float.intBitsToFloat(Float.floatToIntBits(6.158131f) ^ 0x7F450F69), -(NameTags.mc.fontRenderer.FONT_HEIGHT + 1) - Float.intBitsToFloat(Float.floatToIntBits(4.272706f) ^ 0x7F08BA02), obf9 + Float.intBitsToFloat(Float.floatToIntBits(0.17140679f) ^ 0x7E6F8543), Float.intBitsToFloat(Float.floatToIntBits(0.8752474f) ^ 0x7F401037), (float)obf13, (float)obf14, (float)obf15, Float.intBitsToFloat(Float.floatToIntBits(2.4925169E38f) ^ 0x7F3B8421));
        RenderUtil.drawRect((float)(-obf9 - 2), (float)(-(NameTags.mc.fontRenderer.FONT_HEIGHT + 1)), obf9 + Float.intBitsToFloat(Float.floatToIntBits(0.44924268f) ^ 0x7EE60323), Float.intBitsToFloat(Float.floatToIntBits(13.7798815f) ^ 0x7E9C7A65), 1426063360);
        GlStateManager.disableBlend();
        final ItemStack obf16 = obf.getHeldItemMainhand().copy();
        Label_1365: {
            if (obf16.hasEffect()) {
                if (!(obf16.getItem() instanceof ItemTool)) {
                    if (!(obf16.getItem() instanceof ItemArmor)) {
                        break Label_1365;
                    }
                }
                obf16.stackSize = 1;
            }
        }
        if (!obf16.isEmpty && obf16.getItem() != Items.AIR) {
            final String obf17 = obf16.getDisplayName();
            final int obf18 = NameTags.mc.fontRenderer.getStringWidth(obf17) / 2;
            GL11.glPushMatrix();
            GL11.glScalef(Float.intBitsToFloat(Float.floatToIntBits(22.995913f) ^ 0x7EF7F7A1), Float.intBitsToFloat(Float.floatToIntBits(2.598051f) ^ 0x7F664678), Float.intBitsToFloat(Float.floatToIntBits(1.9605498E38f) ^ 0x7F137ED1));
            NameTags.mc.fontRenderer.drawStringWithShadow(obf17, (float)(-obf18), -(this.getBiggestArmorTag(obf) + Float.intBitsToFloat(Float.floatToIntBits(0.4707824f) ^ 0x7F610A64)), -1);
            GL11.glScalef(Float.intBitsToFloat(Float.floatToIntBits(31.45239f) ^ 0x7E3B9E7F), Float.intBitsToFloat(Float.floatToIntBits(30.946653f) ^ 0x7E3792BF), Float.intBitsToFloat(Float.floatToIntBits(21.947065f) ^ 0x7E2F9397));
            GL11.glPopMatrix();
        }
        if (this.show_armor.get_value(true)) {
            GlStateManager.pushMatrix();
            int obf19 = -8;
            for (final ItemStack obf20 : obf.inventory.armorInventory) {
                if (obf20 != null) {
                    obf19 -= 8;
                }
            }
            obf19 -= 8;
            final ItemStack obf21 = obf.getHeldItemOffhand().copy();
            if (obf21.hasEffect() && (obf21.getItem() instanceof ItemTool || obf21.getItem() instanceof ItemArmor)) {
                obf21.stackSize = 1;
            }
            this.renderItemStack(obf21, obf19);
            obf19 += 16;
            if (this.reverse.get_value(true)) {
                for (final ItemStack obf22 : obf.inventory.armorInventory) {
                    if (obf22 != null) {
                        final ItemStack obf23 = obf22.copy();
                        if (obf23.hasEffect() && (obf23.getItem() instanceof ItemTool || obf23.getItem() instanceof ItemArmor)) {
                            obf23.stackSize = 1;
                        }
                        this.renderItemStack(obf23, obf19);
                        obf19 += 16;
                    }
                }
            }
            else {
                for (int obf24 = obf.inventory.armorInventory.size(); obf24 > 0; --obf24) {
                    final ItemStack obf22 = (ItemStack)obf.inventory.armorInventory.get(obf24 - 1);
                    final ItemStack obf23 = obf22.copy();
                    if (obf23.hasEffect()) {
                        if (obf23.getItem() instanceof ItemTool || obf23.getItem() instanceof ItemArmor) {
                            obf23.stackSize = 1;
                        }
                    }
                    this.renderItemStack(obf23, obf19);
                    obf19 += 16;
                }
            }
            this.renderItemStack(obf16, obf19);
            GlStateManager.popMatrix();
        }
        NameTags.mc.fontRenderer.drawStringWithShadow(obf7, (float)(-obf9), (float)(-(NameTags.mc.fontRenderer.FONT_HEIGHT - 1)), this.getDisplayColour(obf));
        obf3.posX = obf4;
        obf3.posY = obf5;
        obf3.posZ = obf6;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(Float.intBitsToFloat(Float.floatToIntBits(40.221523f) ^ 0x7DA0E2D7), Float.intBitsToFloat(Float.floatToIntBits(9.311417E-6f) ^ 0x7EAB233B));
        GlStateManager.popMatrix();
    }
    
    public void renderItemStack(final ItemStack obf, final int obf) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        NameTags.mc.getRenderItem().zLevel = Float.intBitsToFloat(Float.floatToIntBits(-0.010594368f) ^ 0x7F3B9400);
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        NameTags.mc.getRenderItem().renderItemAndEffectIntoGUI(obf, obf, -29);
        NameTags.mc.getRenderItem().renderItemOverlays(NameTags.mc.fontRenderer, obf, obf, -29);
        NameTags.mc.getRenderItem().zLevel = Float.intBitsToFloat(Float.floatToIntBits(2.5477615E38f) ^ 0x7F3FAC1A);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale(Float.intBitsToFloat(Float.floatToIntBits(28.4423f) ^ 0x7EE389D5), Float.intBitsToFloat(Float.floatToIntBits(3.745309f) ^ 0x7F6FB325), Float.intBitsToFloat(Float.floatToIntBits(3.8148146f) ^ 0x7F7425EC));
        GlStateManager.disableDepth();
        this.renderEnchantmentText(obf, obf);
        GlStateManager.enableDepth();
        GlStateManager.scale(Float.intBitsToFloat(Float.floatToIntBits(0.3376558f) ^ 0x7EACE139), Float.intBitsToFloat(Float.floatToIntBits(0.36464784f) ^ 0x7EBAB31F), Float.intBitsToFloat(Float.floatToIntBits(0.8447678f) ^ 0x7F5842B4));
        GlStateManager.popMatrix();
    }
    
    @Override
    public void render(final EventRender obf) {
        for (final EntityPlayer obf2 : NameTags.mc.world.playerEntities) {
            if (obf2 != null && !obf2.equals((Object)NameTags.mc.player) && obf2.isEntityAlive() && (!obf2.isInvisible() || this.show_invis.get_value(true)) && NameTags.mc.player.getDistance((Entity)obf2) < this.range.get_value(1)) {
                final double obf3 = this.interpolate(obf2.lastTickPosX, obf2.posX, obf.get_partial_ticks()) - NameTags.mc.getRenderManager().renderPosX;
                final double obf4 = this.interpolate(obf2.lastTickPosY, obf2.posY, obf.get_partial_ticks()) - NameTags.mc.getRenderManager().renderPosY;
                final double obf5 = this.interpolate(obf2.lastTickPosZ, obf2.posZ, obf.get_partial_ticks()) - NameTags.mc.getRenderManager().renderPosZ;
                this.renderNameTag(obf2, obf3, obf4, obf5, obf.get_partial_ticks());
            }
        }
    }
    
    @Override
    public void update() {
        if (this.rainbow_mode.get_value(true)) {
            this.cycle_rainbow();
        }
    }
    
    public double interpolate(final double obf, final double obf, final float obf) {
        return obf + (obf - obf) * obf;
    }
    
    public void renderEnchantmentText(final ItemStack obf, final int obf) {
        int obf2 = -37;
        final NBTTagList obf3 = obf.getEnchantmentTagList();
        if (obf3.tagCount() > 2 && this.simplify.get_value(true)) {
            NameTags.mc.fontRenderer.drawStringWithShadow("god", (float)(obf * 2), (float)obf2, -3977919);
            obf2 -= 8;
        }
        else {
            for (int obf4 = 0; obf4 < obf3.tagCount(); ++obf4) {
                final short obf5 = obf3.getCompoundTagAt(obf4).getShort("id");
                final short obf6 = obf3.getCompoundTagAt(obf4).getShort("lvl");
                final Enchantment obf7 = Enchantment.getEnchantmentByID((int)obf5);
                if (obf7 != null) {
                    String obf8 = obf7.isCurse() ? (TextFormatting.RED + obf7.getTranslatedName((int)obf6).substring(11).substring(0, 1).toLowerCase()) : obf7.getTranslatedName((int)obf6).substring(0, 1).toLowerCase();
                    obf8 += obf6;
                    NameTags.mc.fontRenderer.drawStringWithShadow(obf8, (float)(obf * 2), (float)obf2, -1);
                    obf2 -= 8;
                }
            }
        }
        if (DamageUtil.hasDurability(obf)) {
            final int obf4 = DamageUtil.getRoundedDamage(obf);
            String obf9;
            if (obf4 >= 60) {
                obf9 = this.section_sign() + "a";
            }
            else if (obf4 >= 25) {
                obf9 = this.section_sign() + "e";
            }
            else {
                obf9 = this.section_sign() + "c";
            }
            NameTags.mc.fontRenderer.drawStringWithShadow(obf9 + obf4 + "%", (float)(obf * 2), (obf2 < -62) ? ((float)obf2) : Float.intBitsToFloat(Float.floatToIntBits(-0.016206933f) ^ 0x7EFCC467), -1);
        }
    }
    
    public void cycle_rainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], (float)this.sat.get_value(1), (float)this.brightness.get_value(1));
        this.r.set_value(hsBtoRGB >> 16 & 0xFF);
        this.g.set_value(hsBtoRGB >> 8 & 0xFF);
        this.b.set_value(hsBtoRGB & 0xFF);
    }
    
    public static void lambda$new$0(final EventRenderName obf) {
        obf.cancel();
    }
    
    public NameTags() {
        super(Category.render);
        this.show_armor = this.create("Armor", "NametagArmor", true);
        this.show_health = this.create("Health", "NametagHealth", true);
        this.show_ping = this.create("Ping", "NametagPing", true);
        this.show_totems = this.create("Totem Count", "NametagTotems", false);
        this.show_invis = this.create("Invis", "NametagInvis", true);
        this.reverse = this.create("Armour Reverse", "NametagReverse", false);
        this.simplify = this.create("Simplify", "NametagSimp", true);
        this.m_scale = this.create("Scale", "NametagScale", 15, 1, 15);
        this.range = this.create("Range", "NametagRange", 150, 1, 250);
        this.r = this.create("R", "NametagR", 120, 0, 255);
        this.g = this.create("G", "NametagG", 120, 0, 255);
        this.b = this.create("B", "NametagB", 240, 0, 255);
        this.rainbow_mode = this.create("Rainbow", "NametagRainbow", false);
        this.sat = this.create("Saturation", "NametagSatiation", Double.longBitsToDouble(Double.doubleToLongBits(3.109643092677996) ^ 0x7FE179151753DFA0L), Double.longBitsToDouble(Double.doubleToLongBits(1.3330847171851683E308) ^ 0x7FE7BACD556B08C4L), Double.longBitsToDouble(Double.doubleToLongBits(59.875786722062735) ^ 0x7FBDF019C780C3E7L));
        this.brightness = this.create("Brightness", "NametagBrightness", Double.longBitsToDouble(Double.doubleToLongBits(12.129811266170856) ^ 0x7FC1DBEF06D42FC7L), Double.longBitsToDouble(Double.doubleToLongBits(1.668423536057073E307) ^ 0x7FB7C256FC886AC7L), Double.longBitsToDouble(Double.doubleToLongBits(11.635823476957723) ^ 0x7FD7458AA79F1FF7L));
        this.on_render_name = new Listener<EventRenderName>(NameTags::lambda$new$0, (Predicate<EventRenderName>[])new Predicate[0]);
        this.name = "Name Tags";
        this.tag = "NameTags";
        this.description = "MORE DETAILED NAMESSSSS";
    }
}
