// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import net.minecraft.item.ItemSword;
import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class OffhandBypassTest extends Module
{
    public Setting swordGap;
    public Setting searchHotbar;
    public Setting minHealth;
    public Setting mode;
    
    public OffhandBypassTest() {
        super(Category.combat);
        this.mode = this.create("Mode", "EAOffhandMode", "Crystal", this.combobox("Crystal", "Gapple"));
        this.minHealth = this.create("Min Health", "EAOffhandMinHealth", Double.longBitsToDouble(Double.doubleToLongBits(1.3211405864687673) ^ 0x7FC5A3644FC4D36BL), Double.longBitsToDouble(Double.doubleToLongBits(228.7229409975702) ^ 0x7FD50EBBCCB12997L), Double.longBitsToDouble(Double.doubleToLongBits(0.03825868308078669) ^ 0x7FE196A4613D719BL));
        this.searchHotbar = this.create("Search Hotbar", "EAOffhandSearchHotbar", false);
        this.swordGap = this.create("Sword Gap", "EAOffhandSwordGap", false);
        this.name = "PhobosAC";
        this.tag = "OffhandBypass";
        this.description = "Offhand for Elite Anarchy";
    }
    
    public void switchToItem(final Item obf) {
        if (OffhandBypassTest.mc.player.getHeldItemOffhand().getItem() != obf) {
            int obf2 = -1;
            for (int obf3 = 44; obf3 > (this.searchHotbar.get_value(true) ? 0 : 8); --obf3) {
                if (OffhandBypassTest.mc.player.inventory.getStackInSlot(obf3).getItem() == obf) {
                    obf2 = obf3;
                }
            }
            if (obf2 != -1) {
                OffhandBypassTest.mc.playerController.windowClick(OffhandBypassTest.mc.player.inventoryContainer.windowId, obf2, 0, ClickType.PICKUP, (EntityPlayer)OffhandBypassTest.mc.player);
                OffhandBypassTest.mc.playerController.windowClick(OffhandBypassTest.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)OffhandBypassTest.mc.player);
                OffhandBypassTest.mc.playerController.windowClick(OffhandBypassTest.mc.player.inventoryContainer.windowId, obf2, 0, ClickType.PICKUP, (EntityPlayer)OffhandBypassTest.mc.player);
            }
        }
    }
    
    @Override
    public void update() {
        if (OffhandBypassTest.mc.player != null) {
            if (OffhandBypassTest.mc.world != null) {
                Item obf = this.mode.in("Crystal") ? Items.END_CRYSTAL : Items.GOLDEN_APPLE;
                if (this.swordGap.get_value(true)) {
                    if (OffhandBypassTest.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) {
                        obf = Items.GOLDEN_APPLE;
                    }
                }
                Label_0474: {
                    Label_0464: {
                        if (this.mode.in("Crystal")) {
                            if (OffhandBypassTest.mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
                                break Label_0464;
                            }
                        }
                        if (OffhandBypassTest.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL) {
                            if (OffhandBypassTest.mc.player.getHealth() + OffhandBypassTest.mc.player.getAbsorptionAmount() >= this.minHealth.get_value(Double.longBitsToDouble(Double.doubleToLongBits(7.286428074790153) ^ 0x7FED254D66B78511L))) {
                                break Label_0474;
                            }
                        }
                    }
                    obf = Items.TOTEM_OF_UNDYING;
                }
                this.switchToItem(obf);
            }
        }
    }
}
