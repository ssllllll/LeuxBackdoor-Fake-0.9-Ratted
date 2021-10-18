// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class InventoryPreview extends Pinnable
{
    public InventoryPreview() {
        super("Inventory Preview", "InventoryPreview", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        if (this.mc.player != null) {
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            this.create_rect(0, 0, this.get_width(), this.get_height(), 0, 0, 0, 60);
            this.set_width(144);
            this.set_height(48);
            for (int i = 0; i < 27; ++i) {
                final ItemStack item_stack = (ItemStack)this.mc.player.inventory.mainInventory.get(i + 9);
                final int item_position_x = this.get_x() + i % 9 * 16;
                final int item_position_y = this.get_y() + i / 9 * 16;
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(item_stack, item_position_x, item_position_y);
                this.mc.getRenderItem().renderItemOverlayIntoGUI(this.mc.fontRenderer, item_stack, item_position_x, item_position_y, (String)null);
            }
            this.mc.getRenderItem().zLevel = -5.0f;
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
        }
    }
}
