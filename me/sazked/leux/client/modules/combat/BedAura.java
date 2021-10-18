// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import me.sazked.leux.client.modules.Category;
import net.minecraft.inventory.ClickType;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.client.util.FriendUtil;
import me.sazked.turok.draw.RenderHelp;
import me.sazked.leux.client.event.events.EventRender;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import java.util.Iterator;
import me.sazked.leux.client.util.BlockUtil;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import me.sazked.leux.client.util.BlockInteractHelper;
import net.minecraft.entity.player.EntityPlayer;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class BedAura extends Module
{
    public Setting delay;
    public Setting range;
    public BlockPos render_pos;
    public int counter;
    public Setting swing;
    public spoof_face spoof_looking;
    public Setting hard;
    
    public void break_bed() {
        for (final BlockPos obf : BlockInteractHelper.getSphere(get_pos_floor((EntityPlayer)BedAura.mc.player), (float)this.range.get_value(1), this.range.get_value(1), false, true, 0).stream().filter((Predicate<? super Object>)BedAura::is_bed).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList())) {
            if (BedAura.mc.player.isSneaking()) {
                BedAura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BedAura.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            BlockUtil.openBlock(obf);
        }
    }
    
    public static boolean is_bed(final BlockPos blockPos) {
        return BedAura.mc.world.getBlockState(blockPos).getBlock() == Blocks.BED;
    }
    
    public int find_bed() {
        for (int i = 0; i < 9; ++i) {
            if (BedAura.mc.player.inventory.getStackInSlot(i).getItem() == Items.BED) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean is_space() {
        for (int i = 0; i < 9; ++i) {
            if (BedAura.mc.player.inventoryContainer.getSlot(i).getHasStack()) {
                return true;
            }
        }
        return false;
    }
    
    public BlockPos check_side_block(final BlockPos obf) {
        if (BedAura.mc.world.getBlockState(obf.east()).getBlock() != Blocks.AIR) {
            if (BedAura.mc.world.getBlockState(obf.east().up()).getBlock() == Blocks.AIR) {
                this.spoof_looking = spoof_face.WEST;
                return obf.east();
            }
        }
        if (BedAura.mc.world.getBlockState(obf.north()).getBlock() != Blocks.AIR) {
            if (BedAura.mc.world.getBlockState(obf.north().up()).getBlock() == Blocks.AIR) {
                this.spoof_looking = spoof_face.SOUTH;
                return obf.north();
            }
        }
        if (BedAura.mc.world.getBlockState(obf.west()).getBlock() != Blocks.AIR) {
            if (BedAura.mc.world.getBlockState(obf.west().up()).getBlock() == Blocks.AIR) {
                this.spoof_looking = spoof_face.EAST;
                return obf.west();
            }
        }
        if (BedAura.mc.world.getBlockState(obf.south()).getBlock() != Blocks.AIR && BedAura.mc.world.getBlockState(obf.south().up()).getBlock() == Blocks.AIR) {
            this.spoof_looking = spoof_face.NORTH;
            return obf.south();
        }
        return null;
    }
    
    @Override
    public void disable() {
        this.render_pos = null;
    }
    
    public static BlockPos get_pos_floor(final EntityPlayer obf) {
        return new BlockPos(Math.floor(obf.posX), Math.floor(obf.posY), Math.floor(obf.posZ));
    }
    
    @Override
    public void update() {
        if (BedAura.mc.player == null) {
            return;
        }
        if (this.counter > this.delay.get_value(1)) {
            this.counter = 0;
            this.place_bed();
            this.break_bed();
            this.refill_bed();
        }
        ++this.counter;
    }
    
    @Override
    public void render(final EventRender eventRender) {
        if (this.render_pos == null) {
            return;
        }
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)this.render_pos.getX(), (float)this.render_pos.getY(), (float)this.render_pos.getZ(), 1.0f, 0.2f, 1.0f, 255, 20, 20, 180, "all");
        RenderHelp.release();
    }
    
    public static boolean lambda$place_bed$0(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public void place_bed() {
        if (this.find_bed() == -1) {
            return;
        }
        final int obf = this.find_bed();
        BlockPos obf2 = null;
        EntityPlayer obf3 = null;
        float obf4 = (float)this.range.get_value(1);
        for (final EntityPlayer obf5 : (List)BedAura.mc.world.playerEntities.stream().filter(BedAura::lambda$place_bed$0).collect(Collectors.toList())) {
            if (obf5 == BedAura.mc.player) {
                continue;
            }
            if (obf4 < BedAura.mc.player.getDistance((Entity)obf5)) {
                continue;
            }
            boolean obf6 = true;
            final BlockPos obf7 = get_pos_floor(obf5).down();
            final BlockPos obf8 = this.check_side_block(obf7);
            if (obf8 != null) {
                obf2 = obf8.up();
                obf3 = obf5;
                obf4 = BedAura.mc.player.getDistance((Entity)obf5);
                obf6 = false;
            }
            if (!obf6) {
                continue;
            }
            final BlockPos obf9 = get_pos_floor(obf5);
            final BlockPos obf10 = this.check_side_block(obf9);
            if (obf10 == null) {
                continue;
            }
            obf2 = obf10.up();
            obf3 = obf5;
            obf4 = BedAura.mc.player.getDistance((Entity)obf5);
        }
        if (obf3 == null) {
            return;
        }
        this.render_pos = obf2;
        if (this.spoof_looking == spoof_face.NORTH) {
            if (this.hard.get_value(true)) {
                BedAura.mc.player.rotationYaw = Float.intBitsToFloat(Float.floatToIntBits(0.014432778f) ^ 0x7F587775);
            }
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Float.intBitsToFloat(Float.floatToIntBits(0.015367371f) ^ 0x7F4FC76D), Float.intBitsToFloat(Float.floatToIntBits(1.3024504E38f) ^ 0x7EC3F891), BedAura.mc.player.onGround));
        }
        else if (this.spoof_looking == spoof_face.SOUTH) {
            if (this.hard.get_value(true)) {
                BedAura.mc.player.rotationYaw = Float.intBitsToFloat(Float.floatToIntBits(5.6220603E37f) ^ 0x7E292EC7);
            }
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Float.intBitsToFloat(Float.floatToIntBits(6.01329E37f) ^ 0x7E34F4B3), Float.intBitsToFloat(Float.floatToIntBits(2.5389741E38f) ^ 0x7F3F02DD), BedAura.mc.player.onGround));
        }
        else if (this.spoof_looking == spoof_face.WEST) {
            if (this.hard.get_value(true)) {
                BedAura.mc.player.rotationYaw = Float.intBitsToFloat(Float.floatToIntBits(1.4418353f) ^ 0x7D0C8E0F);
            }
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Float.intBitsToFloat(Float.floatToIntBits(0.007937514f) ^ 0x7EB60C59), Float.intBitsToFloat(Float.floatToIntBits(7.9004767E37f) ^ 0x7E6DBF0F), BedAura.mc.player.onGround));
        }
        else if (this.spoof_looking == spoof_face.EAST) {
            if (this.hard.get_value(true)) {
                BedAura.mc.player.rotationYaw = Float.intBitsToFloat(Float.floatToIntBits(-0.105085894f) ^ 0x7F633746);
            }
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(Float.intBitsToFloat(Float.floatToIntBits(-0.07223495f) ^ 0x7F27EFEB), Float.intBitsToFloat(Float.floatToIntBits(8.83566E37f) ^ 0x7E84F1B9), BedAura.mc.player.onGround));
        }
        BlockUtil.placeBlock(obf2, obf, false, false, this.swing);
    }
    
    @Override
    public void enable() {
        this.render_pos = null;
        this.counter = 0;
    }
    
    public void refill_bed() {
        if (!(BedAura.mc.currentScreen instanceof GuiContainer) && this.is_space()) {
            for (int obf = 9; obf < 35; ++obf) {
                if (BedAura.mc.player.inventory.getStackInSlot(obf).getItem() == Items.BED) {
                    BedAura.mc.playerController.windowClick(BedAura.mc.player.inventoryContainer.windowId, obf, 0, ClickType.QUICK_MOVE, (EntityPlayer)BedAura.mc.player);
                    break;
                }
            }
        }
    }
    
    public BedAura() {
        super(Category.combat);
        this.delay = this.create("Delay", "BedAuraDelay", 6, 0, 20);
        this.range = this.create("Range", "BedAuraRange", 5, 0, 6);
        this.hard = this.create("Hard Rotate", "BedAuraRotate", false);
        this.swing = this.create("Swing", "BedAuraSwing", "Mainhand", this.combobox("Mainhand", "Offhand", "Both", "None"));
        this.name = "Bed Aura";
        this.tag = "BedAura";
        this.description = "fucking endcrystal.me";
    }
    
    enum spoof_face
    {
        EAST;
        
        public static spoof_face[] $VALUES;
        
        NORTH, 
        WEST, 
        SOUTH;
        
        static {
            spoof_face.$VALUES = new spoof_face[] { spoof_face.EAST, spoof_face.WEST, spoof_face.NORTH, spoof_face.SOUTH };
        }
    }
}
