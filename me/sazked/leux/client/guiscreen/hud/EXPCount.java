// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import java.util.function.ToIntFunction;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import me.sazked.leux.Leux;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class EXPCount extends Pinnable
{
    ChatFormatting dg;
    ChatFormatting db;
    int exp;
    
    public EXPCount() {
        super("EXP Count", "EXPCount", 1.0f, 0, 0);
        this.dg = ChatFormatting.DARK_GRAY;
        this.db = ChatFormatting.DARK_BLUE;
        this.exp = 0;
    }
    
    @Override
    public void render() {
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        if (this.mc.player != null) {
            if (this.is_on_gui()) {
                this.create_rect(0, 0, this.get_width(), this.get_height(), 0, 0, 0, 50);
            }
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            this.exp = this.mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.EXPERIENCE_BOTTLE).mapToInt(ItemStack::func_190916_E).sum();
            int off = 0;
            for (int i = 0; i < 45; ++i) {
                final ItemStack stack2 = this.mc.player.inventory.getStackInSlot(i);
                final ItemStack off_h = this.mc.player.getHeldItemOffhand();
                if (off_h.getItem() == Items.EXPERIENCE_BOTTLE) {
                    off = off_h.getMaxStackSize();
                }
                else {
                    off = 0;
                }
                if (stack2.getItem() == Items.EXPERIENCE_BOTTLE) {
                    this.mc.getRenderItem().renderItemAndEffectIntoGUI(stack2, this.get_x(), this.get_y());
                    this.create_line(Integer.toString(this.exp + off), 18, 16 - this.get(Integer.toString(this.exp + off), "height"), nl_r, nl_g, nl_b, nl_a);
                }
            }
            this.mc.getRenderItem().zLevel = 0.0f;
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
            this.set_width(16 + this.get(Integer.toString(this.exp) + off, "width") + 2);
            this.set_height(16);
        }
    }
}
