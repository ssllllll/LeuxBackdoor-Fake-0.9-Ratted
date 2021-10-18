// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class ArmorPreview extends Pinnable
{
    private final RenderItem itemRender;
    
    public ArmorPreview() {
        super("Armor Preview", "ArmorPreview", 1.0f, 0, 0);
        this.itemRender = this.mc.getRenderItem();
    }
    
    @Override
    public void render() {
        if (this.mc.player != null && this.is_on_gui()) {
            this.create_rect(0, 0, this.get_width(), this.get_height(), 0, 0, 0, 50);
        }
        GlStateManager.enableTexture2D();
        final ScaledResolution resolution = new ScaledResolution(this.mc);
        final int i = resolution.getScaledWidth() / 2;
        final int y = resolution.getScaledHeight() - 55 - (this.mc.player.isInWater() ? 10 : 0);
        int iteration = 0;
        for (final ItemStack is : this.mc.player.inventory.armorInventory) {
            ++iteration;
            if (is.isEmpty()) {
                continue;
            }
            final int x = i - 90 + (9 - iteration) * 20 + 2;
            GlStateManager.enableDepth();
            this.itemRender.zLevel = 200.0f;
            this.itemRender.renderItemAndEffectIntoGUI(is, x, y);
            this.itemRender.renderItemOverlayIntoGUI(this.mc.fontRenderer, is, x, y, "");
            this.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String s = (is.getCount() > 1) ? (is.getCount() + "") : "";
            this.mc.fontRenderer.drawStringWithShadow(s, (float)(x + 19 - 2 - this.mc.fontRenderer.getStringWidth(s)), (float)(y + 9), 16777215);
            final float green = (is.getMaxDamage() - (float)is.getItemDamage()) / is.getMaxDamage();
            final float red = 1.0f - green;
            final int dmg = 100 - (int)(red * 100.0f);
            if (dmg >= 100) {
                this.mc.fontRenderer.drawStringWithShadow(dmg + "", (float)(x + 8 - this.mc.fontRenderer.getStringWidth(dmg + "") / 2), (float)(y - 11), toHex((int)(red * 255.0f), (int)(green * 255.0f), 0));
            }
            else if (dmg <= 0) {
                this.mc.fontRenderer.drawStringWithShadow("0%", (float)(x + 8 - this.mc.fontRenderer.getStringWidth("0") / 2), (float)(y - 11), toHex((int)(red * 255.0f), (int)(green * 255.0f), 0));
            }
            else {
                this.mc.fontRenderer.drawStringWithShadow(dmg + "%", (float)(x + 8 - this.mc.fontRenderer.getStringWidth(dmg + "") / 2), (float)(y - 11), toHex((int)(red * 255.0f), (int)(green * 255.0f), 0));
            }
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        this.set_width(50);
        this.set_height(25);
    }
    
    public static int toHex(final int r, final int g, final int b) {
        return 0xFF000000 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF);
    }
}
