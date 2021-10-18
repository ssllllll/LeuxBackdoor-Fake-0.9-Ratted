// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import me.sazked.leux.client.modules.Category;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemAir;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.GuiHopper;
import me.sazked.leux.client.util.BlockUtil;
import java.util.Objects;
import net.minecraft.util.math.RayTraceResult;
import me.sazked.leux.client.util.MessageUtil;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Auto32k extends Module
{
    public Setting debug;
    public Setting delay;
    public int hopper_slot;
    public int redstone_slot;
    public Setting autooff;
    public int[] rot;
    public BlockPos pos;
    public boolean dispenser_done;
    public int shulker_slot;
    public Setting place_mode;
    public boolean setup;
    public Setting rotate;
    public int ticks_past;
    public boolean place_redstone;
    public Setting swing;
    
    @Override
    public void enable() {
        this.ticks_past = 0;
        this.setup = false;
        this.dispenser_done = false;
        this.place_redstone = false;
        this.hopper_slot = -1;
        int n = -1;
        this.redstone_slot = -1;
        this.shulker_slot = -1;
        int n2 = -1;
        for (int i = 0; i < 9; ++i) {
            final Item item = Auto32k.mc.player.inventory.getStackInSlot(i).getItem();
            if (item == Item.getItemFromBlock((Block)Blocks.HOPPER)) {
                this.hopper_slot = i;
            }
            else if (item == Item.getItemFromBlock(Blocks.DISPENSER)) {
                n = i;
            }
            else if (item == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) {
                this.redstone_slot = i;
            }
            else if (item instanceof ItemShulkerBox) {
                this.shulker_slot = i;
            }
            else if (item instanceof ItemBlock) {
                n2 = i;
            }
        }
        if ((this.hopper_slot == -1 || n == -1 || this.redstone_slot == -1 || this.shulker_slot == -1 || n2 == -1) && !this.place_mode.in("Hopper")) {
            MessageUtil.send_client_message("Missing item, u need hoppers, dispensers, redstone blocks, shulkers and obsidian");
            this.set_disable();
            return;
        }
        if (this.hopper_slot == -1 || this.shulker_slot == -1) {
            MessageUtil.send_client_message("Missing item, u need hoppers and shulkers");
            this.set_disable();
            return;
        }
        if (this.place_mode.in("Looking")) {
            this.pos = Objects.requireNonNull(Auto32k.mc.player.rayTrace(5.0, Auto32k.mc.getRenderPartialTicks())).getBlockPos().up();
            final double a = this.pos.getX() - Auto32k.mc.player.posX;
            final double a2 = this.pos.getZ() - Auto32k.mc.player.posZ;
            this.rot = ((Math.abs(a) > Math.abs(a2)) ? ((a > 0.0) ? new int[] { -1, 0 } : new int[] { 1, 0 }) : ((a2 > 0.0) ? new int[] { 0, -1 } : new int[] { 0, 1 }));
            if (BlockUtil.canPlaceBlock(this.pos) && BlockUtil.isBlockEmpty(this.pos)) {
                if (BlockUtil.isBlockEmpty(this.pos.add(this.rot[0], 0, this.rot[1]))) {
                    if (BlockUtil.isBlockEmpty(this.pos.add(0, 1, 0))) {
                        if (BlockUtil.isBlockEmpty(this.pos.add(0, 2, 0)) && BlockUtil.isBlockEmpty(this.pos.add(this.rot[0], 1, this.rot[1]))) {
                            BlockUtil.placeBlock(this.pos, n2, this.rotate.get_value(true), false, this.swing);
                            BlockUtil.rotatePacket(this.pos.add(-this.rot[0], 1, -this.rot[1]).getX() + 0.5, this.pos.getY() + 1, this.pos.add(-this.rot[0], 1, -this.rot[1]).getZ() + 0.5);
                            BlockUtil.placeBlock(this.pos.up(), n, false, false, this.swing);
                            BlockUtil.openBlock(this.pos.up());
                            this.setup = true;
                            return;
                        }
                    }
                }
            }
            MessageUtil.send_client_message("unable to place");
            this.set_disable();
        }
        else if (this.place_mode.in("Auto")) {
            for (int j = -2; j <= 2; ++j) {
                for (int k = -1; k <= 1; ++k) {
                    for (int l = -2; l <= 2; ++l) {
                        this.rot = ((Math.abs(j) > Math.abs(l)) ? ((j > 0) ? new int[] { -1, 0 } : new int[] { 1, 0 }) : ((l > 0) ? new int[] { 0, -1 } : new int[] { 0, 1 }));
                        this.pos = Auto32k.mc.player.getPosition().add(j, k, l);
                        if (Auto32k.mc.player.getPositionEyes(Auto32k.mc.getRenderPartialTicks()).distanceTo(Auto32k.mc.player.getPositionVector().add((double)(j - this.rot[0] / 2.0f), k + 0.5, (double)(l + this.rot[1] / 2))) <= 4.5 && Auto32k.mc.player.getPositionEyes(Auto32k.mc.getRenderPartialTicks()).distanceTo(Auto32k.mc.player.getPositionVector().add(j + 0.5, k + 2.5, l + 0.5)) <= 4.5 && BlockUtil.canPlaceBlock(this.pos) && BlockUtil.isBlockEmpty(this.pos) && BlockUtil.isBlockEmpty(this.pos.add(this.rot[0], 0, this.rot[1])) && BlockUtil.isBlockEmpty(this.pos.add(0, 1, 0))) {
                            if (BlockUtil.isBlockEmpty(this.pos.add(0, 2, 0))) {
                                if (BlockUtil.isBlockEmpty(this.pos.add(this.rot[0], 1, this.rot[1]))) {
                                    BlockUtil.placeBlock(this.pos, n2, this.rotate.get_value(true), false, this.swing);
                                    BlockUtil.rotatePacket(this.pos.add(-this.rot[0], 1, -this.rot[1]).getX() + 0.5, this.pos.getY() + 1, this.pos.add(-this.rot[0], 1, -this.rot[1]).getZ() + 0.5);
                                    BlockUtil.placeBlock(this.pos.up(), n, false, false, this.swing);
                                    BlockUtil.openBlock(this.pos.up());
                                    this.setup = true;
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            MessageUtil.send_client_message("Can't place");
            this.set_disable();
        }
        else {
            for (int n3 = -2; n3 <= 2; ++n3) {
                for (int n4 = -1; n4 <= 2; ++n4) {
                    for (int n5 = -2; n5 <= 2; ++n5) {
                        if (n3 == 0) {
                            if (n4 == 0 && n5 == 0) {
                                continue;
                            }
                        }
                        if (n3 != 0 || n4 != 1 || n5 != 0) {
                            if (BlockUtil.isBlockEmpty(Auto32k.mc.player.getPosition().add(n3, n4, n5))) {
                                if (Auto32k.mc.player.getPositionEyes(Auto32k.mc.getRenderPartialTicks()).distanceTo(Auto32k.mc.player.getPositionVector().add(n3 + 0.5, n4 + 0.5, n5 + 0.5)) < 4.5) {
                                    if (BlockUtil.isBlockEmpty(Auto32k.mc.player.getPosition().add(n3, n4 + 1, n5))) {
                                        if (Auto32k.mc.player.getPositionEyes(Auto32k.mc.getRenderPartialTicks()).distanceTo(Auto32k.mc.player.getPositionVector().add(n3 + 0.5, n4 + 1.5, n5 + 0.5)) < 4.5) {
                                            BlockUtil.placeBlock(Auto32k.mc.player.getPosition().add(n3, n4, n5), this.hopper_slot, this.rotate.get_value(true), false, this.swing);
                                            BlockUtil.placeBlock(Auto32k.mc.player.getPosition().add(n3, n4 + 1, n5), this.shulker_slot, this.rotate.get_value(true), false, this.swing);
                                            BlockUtil.openBlock(Auto32k.mc.player.getPosition().add(n3, n4, n5));
                                            this.pos = Auto32k.mc.player.getPosition().add(n3, n4, n5);
                                            this.dispenser_done = true;
                                            this.place_redstone = true;
                                            this.setup = true;
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void update() {
        if (this.autooff.get_value(true) && this.ticks_past > 50 && !(Auto32k.mc.currentScreen instanceof GuiHopper)) {
            MessageUtil.send_client_message("Inactive too long, disabling");
            this.set_disable();
            return;
        }
        if (this.setup && this.ticks_past > this.delay.get_value(1)) {
            if (!this.dispenser_done) {
                try {
                    Auto32k.mc.playerController.windowClick(Auto32k.mc.player.openContainer.windowId, 36 + this.shulker_slot, 0, ClickType.QUICK_MOVE, (EntityPlayer)Auto32k.mc.player);
                    this.dispenser_done = true;
                    if (this.debug.get_value(true)) {
                        MessageUtil.send_client_message("Sent item");
                    }
                }
                catch (Exception ex) {}
            }
            if (!this.place_redstone) {
                BlockUtil.placeBlock(this.pos.add(0, 2, 0), this.redstone_slot, this.rotate.get_value(true), false, this.swing);
                if (this.debug.get_value(true)) {
                    MessageUtil.send_client_message("Placed redstone");
                }
                this.place_redstone = true;
                return;
            }
            if (!this.place_mode.in("Hopper") && Auto32k.mc.world.getBlockState(this.pos.add(this.rot[0], 1, this.rot[1])).getBlock() instanceof BlockShulkerBox && Auto32k.mc.world.getBlockState(this.pos.add(this.rot[0], 0, this.rot[1])).getBlock() != Blocks.HOPPER && this.place_redstone) {
                if (this.dispenser_done) {
                    if (!(Auto32k.mc.currentScreen instanceof GuiInventory)) {
                        BlockUtil.placeBlock(this.pos.add(this.rot[0], 0, this.rot[1]), this.hopper_slot, this.rotate.get_value(true), false, this.swing);
                        BlockUtil.openBlock(this.pos.add(this.rot[0], 0, this.rot[1]));
                        if (this.debug.get_value(true)) {
                            MessageUtil.send_client_message("In the hopper");
                        }
                    }
                }
            }
            if (Auto32k.mc.currentScreen instanceof GuiHopper) {
                final GuiHopper guiHopper = (GuiHopper)Auto32k.mc.currentScreen;
                for (int i = 32; i <= 40; ++i) {
                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, guiHopper.inventorySlots.getSlot(i).getStack()) > 5) {
                        Auto32k.mc.player.inventory.currentItem = i - 32;
                        break;
                    }
                }
                if (!(((Slot)guiHopper.inventorySlots.inventorySlots.get(0)).getStack().getItem() instanceof ItemAir)) {
                    boolean b = true;
                    if (((GuiContainer)Auto32k.mc.currentScreen).inventorySlots.getSlot(0).getStack().isEmpty) {
                        b = false;
                    }
                    if (!((GuiContainer)Auto32k.mc.currentScreen).inventorySlots.getSlot(this.shulker_slot + 32).getStack().isEmpty) {
                        b = false;
                    }
                    if (b) {
                        Auto32k.mc.playerController.windowClick(((GuiContainer)Auto32k.mc.currentScreen).inventorySlots.windowId, 0, this.shulker_slot, ClickType.SWAP, (EntityPlayer)Auto32k.mc.player);
                        this.disable();
                    }
                }
            }
        }
        ++this.ticks_past;
    }
    
    public Auto32k() {
        super(Category.combat);
        this.place_mode = this.create("Place Mode", "AutotkPlaceMode", "Auto", this.combobox("Auto", "Looking", "Hopper"));
        this.swing = this.create("Swing", "AutotkSwing", "Offhand", this.combobox("Mainhand", "Offhand", "Both", "None"));
        this.delay = this.create("Delay", "AutotkDelay", 25, 0, 50);
        this.rotate = this.create("Rotate", "Autotkrotate", false);
        this.autooff = this.create("Auto Disable", "AutoDisable", false);
        this.debug = this.create("Debug", "AutotkDebug", false);
        this.name = "Auto 32k";
        this.tag = "Auto32k";
        this.description = "fastest in the west";
    }
}
