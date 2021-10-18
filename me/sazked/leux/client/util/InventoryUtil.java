// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.item.Item;
import net.minecraft.client.Minecraft;

public class InventoryUtil
{
    public static Minecraft mc;
    
    public static void switchToSlotGhost(final Item item) {
        switchToSlotGhost(getHotbarItemSlot(item));
    }
    
    public static int getHotbarSlot(final Item obf) {
        for (int obf2 = 0; obf2 < 9; ++obf2) {
            final Item obf3 = InventoryUtil.mc.player.inventory.getStackInSlot(obf2).getItem();
            if (obf.equals(obf3)) {
                return obf2;
            }
        }
        return -1;
    }
    
    public static void switchToHotbarSlot(final int obf, final boolean obf) {
        if (InventoryUtil.mc.player.inventory.currentItem == obf || obf < 0) {
            return;
        }
        if (obf) {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(obf));
            InventoryUtil.mc.playerController.updateController();
        }
        else {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(obf));
            InventoryUtil.mc.player.inventory.currentItem = obf;
            InventoryUtil.mc.playerController.updateController();
        }
    }
    
    public static int getHotbarSlot(final Block obf) {
        for (int obf2 = 0; obf2 < 9; ++obf2) {
            final Item obf3 = InventoryUtil.mc.player.inventory.getStackInSlot(obf2).getItem();
            if (obf3 instanceof ItemBlock && ((ItemBlock)obf3).getBlock().equals(obf)) {
                return obf2;
            }
        }
        return -1;
    }
    
    static {
        InventoryUtil.mc = Minecraft.getMinecraft();
    }
    
    public static void switchToSlot(final int currentItem) {
        InventoryUtil.mc.player.inventory.currentItem = currentItem;
    }
    
    public static void switchToSlot(final Item item) {
        InventoryUtil.mc.player.inventory.currentItem = getHotbarItemSlot(item);
    }
    
    public static boolean isNull(final ItemStack itemStack) {
        return itemStack == null || itemStack.getItem() instanceof ItemAir;
    }
    
    public static int getHotbarItemSlot(final Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                n = i;
                break;
            }
        }
        return (n == -1) ? InventoryUtil.mc.player.inventory.currentItem : n;
    }
    
    public static void switchToSlotGhost(final int obf) {
        InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(obf));
    }
}
