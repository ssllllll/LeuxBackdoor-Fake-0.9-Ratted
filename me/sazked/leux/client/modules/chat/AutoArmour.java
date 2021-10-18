// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import me.sazked.leux.client.util.FriendUtil;
import me.sazked.leux.client.modules.Category;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.List;
import java.util.HashMap;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemArmor;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import java.util.Iterator;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import java.util.Map;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoArmour extends Module
{
    public Setting crystal_range;
    public Setting player_range;
    public Setting chest_percent;
    public Setting smart_mode;
    public Setting put_back;
    public Setting delay;
    public int delay_count;
    public Setting boot_percent;
    
    public static Map<Integer, ItemStack> get_armour() {
        return get_inv_slots(5, 8);
    }
    
    public boolean is_space() {
        final Iterator<Map.Entry<Integer, ItemStack>> iterator = get_inv().entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().getItem() == Items.AIR) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void update() {
        if (AutoArmour.mc.player.ticksExisted % 2 == 0) {
            return;
        }
        boolean obf = false;
        if (this.delay_count < this.delay.get_value(0)) {
            ++this.delay_count;
            return;
        }
        this.delay_count = 0;
        if (this.smart_mode.get_value(true)) {
            if (!this.is_crystal_in_range(this.crystal_range.get_value(1)) && !this.is_player_in_range(this.player_range.get_value(1))) {
                obf = true;
            }
        }
        if (obf) {
            if (AutoArmour.mc.gameSettings.keyBindUseItem.isKeyDown() && AutoArmour.mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                this.take_off();
            }
            return;
        }
        if (!this.put_back.get_value(true)) {
            return;
        }
        if (AutoArmour.mc.currentScreen instanceof GuiContainer && !(AutoArmour.mc.currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        final int[] obf2 = new int[4];
        final int[] obf3 = new int[4];
        for (int obf4 = 0; obf4 < 4; ++obf4) {
            final ItemStack obf5 = AutoArmour.mc.player.inventory.armorItemInSlot(obf4);
            if (obf5.getItem() instanceof ItemArmor) {
                obf3[obf4] = ((ItemArmor)obf5.getItem()).damageReduceAmount;
            }
            obf2[obf4] = -1;
        }
        for (int obf4 = 0; obf4 < 36; ++obf4) {
            final ItemStack obf5 = AutoArmour.mc.player.inventory.getStackInSlot(obf4);
            if (obf5.getCount() <= 1) {
                if (obf5.getItem() instanceof ItemArmor) {
                    final ItemArmor obf6 = (ItemArmor)obf5.getItem();
                    final int obf7 = obf6.armorType.ordinal() - 2;
                    if (obf7 == 2) {
                        if (AutoArmour.mc.player.inventory.armorItemInSlot(obf7).getItem().equals(Items.ELYTRA)) {
                            continue;
                        }
                    }
                    final int obf8 = obf6.damageReduceAmount;
                    if (obf8 > obf3[obf7]) {
                        obf2[obf7] = obf4;
                        obf3[obf7] = obf8;
                    }
                }
            }
        }
        for (int obf4 = 0; obf4 < 4; ++obf4) {
            int obf9 = obf2[obf4];
            if (obf9 != -1) {
                final ItemStack obf10 = AutoArmour.mc.player.inventory.armorItemInSlot(obf4);
                if (obf10 != ItemStack.EMPTY || AutoArmour.mc.player.inventory.getFirstEmptyStack() != -1) {
                    if (obf9 < 9) {
                        obf9 += 36;
                    }
                    AutoArmour.mc.playerController.windowClick(0, 8 - obf4, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmour.mc.player);
                    AutoArmour.mc.playerController.windowClick(0, obf9, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmour.mc.player);
                    break;
                }
            }
        }
    }
    
    public static boolean lambda$is_crystal_in_range$1(final Entity entity) {
        return entity instanceof EntityEnderCrystal;
    }
    
    public void take_off() {
        if (!this.is_space()) {
            return;
        }
        for (final Map.Entry<Integer, ItemStack> entry : get_armour().entrySet()) {
            if (this.is_healed(entry.getValue())) {
                AutoArmour.mc.playerController.windowClick(0, (int)entry.getKey(), 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmour.mc.player);
            }
        }
    }
    
    @Override
    public void enable() {
        this.delay_count = 0;
    }
    
    public boolean is_healed(final ItemStack itemStack) {
        if (itemStack.getItem() == Items.DIAMOND_BOOTS || itemStack.getItem() == Items.DIAMOND_HELMET) {
            return (itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double)itemStack.getMaxDamage() * 100.0 >= this.boot_percent.get_value(1);
        }
        return (itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double)itemStack.getMaxDamage() * 100.0 >= this.chest_percent.get_value(1);
    }
    
    public static Map<Integer, ItemStack> get_inv_slots(int obf, final int obf) {
        final Map<Integer, ItemStack> obf2 = new HashMap<Integer, ItemStack>();
        while (obf <= obf) {
            obf2.put(obf, (ItemStack)AutoArmour.mc.player.inventoryContainer.getInventory().get(obf));
            ++obf;
        }
        return obf2;
    }
    
    public static Map<Integer, ItemStack> get_inv() {
        return get_inv_slots(9, 44);
    }
    
    public boolean is_crystal_in_range(final int obf) {
        for (final Entity obf2 : (List)AutoArmour.mc.world.loadedEntityList.stream().filter(AutoArmour::lambda$is_crystal_in_range$1).collect(Collectors.toList())) {
            if (AutoArmour.mc.player.getDistance(obf2) < obf) {
                return true;
            }
        }
        return false;
    }
    
    public AutoArmour() {
        super(Category.chat);
        this.delay = this.create("Delay", "AADelay", 2, 0, 5);
        this.smart_mode = this.create("Smart Mode", "AASmartMode", false);
        this.put_back = this.create("Equip Armour", "AAEquipArmour", true);
        this.player_range = this.create("Player Range", "AAPlayerRange", 8, 0, 20);
        this.crystal_range = this.create("Crystal Range", "AACrystalRange", 13, 0, 20);
        this.boot_percent = this.create("Boot Percent", "AATBootPercent", 80, 0, 100);
        this.chest_percent = this.create("Chest Percent", "AATChestPercent", 80, 0, 100);
        this.name = "AutoArmor";
        this.tag = "AutoArmor";
        this.description = "WATCH UR BOOTS";
    }
    
    public static boolean lambda$is_player_in_range$0(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public boolean is_player_in_range(final int obf) {
        for (final Entity obf2 : (List)AutoArmour.mc.world.playerEntities.stream().filter(AutoArmour::lambda$is_player_in_range$0).collect(Collectors.toList())) {
            if (obf2 == AutoArmour.mc.player) {
                continue;
            }
            if (AutoArmour.mc.player.getDistance(obf2) < obf) {
                return true;
            }
        }
        return false;
    }
}
