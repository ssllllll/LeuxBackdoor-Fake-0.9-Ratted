// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class DamageUtil
{
    public static float getDamageInPercent(final ItemStack obf) {
        return getItemDamage(obf) / (float)obf.getMaxDamage() * Float.intBitsToFloat(Float.floatToIntBits(0.12260672f) ^ 0x7F33193B);
    }
    
    public static int getItemDamage(final ItemStack itemStack) {
        return itemStack.getMaxDamage() - itemStack.getItemDamage();
    }
    
    public static boolean hasDurability(final ItemStack itemStack) {
        final Item item = itemStack.getItem();
        return item instanceof ItemArmor || item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemShield;
    }
    
    public static int getRoundedDamage(final ItemStack obf) {
        return (int)getDamageInPercent(obf);
    }
}
