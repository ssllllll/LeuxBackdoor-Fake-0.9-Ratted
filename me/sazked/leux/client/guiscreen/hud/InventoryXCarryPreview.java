// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Slot;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class InventoryXCarryPreview extends Pinnable
{
    public InventoryXCarryPreview() {
        super("Inventory XCarry", "InventoryXPreview", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        if (this.mc.player != null) {
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            this.create_rect(0, 0, this.get_width(), this.get_height(), 0, 0, 0, 60);
            this.set_width(32);
            this.set_height(32);
            for (int i = 1; i < 5; ++i) {
                final ItemStack item_stack = this.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                int item_position_x = this.get_x();
                int item_position_y = this.get_y();
                if (i == 1) {
                    item_position_x += 0;
                    item_position_y += 0;
                }
                if (i == 2) {
                    item_position_x += 16;
                    item_position_y += 0;
                }
                if (i == 3) {
                    item_position_x += 0;
                    item_position_y += 16;
                }
                if (i == 4) {
                    item_position_x += 16;
                    item_position_y += 16;
                }
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(item_stack, item_position_x, item_position_y);
                this.mc.getRenderItem().renderItemOverlayIntoGUI(this.mc.fontRenderer, item_stack, item_position_x, item_position_y, (String)null);
            }
            this.mc.getRenderItem().zLevel = -5.0f;
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
        }
    }
}
