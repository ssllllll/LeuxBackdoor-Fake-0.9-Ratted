// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import me.sazked.leux.client.util.BlockUtil;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.util.math.Vec3d;
import me.sazked.leux.client.modules.Module;

public class Surround extends Module
{
    public Vec3d[] surround_targets_face;
    public Setting rotate;
    public int y_level;
    public Setting center;
    public Setting tick_for_place;
    public Setting block_head;
    public Vec3d[] surround_targets;
    public Setting hybrid;
    public Setting tick_timeout;
    public int offset_step;
    public Vec3d center_block;
    public Setting triggerable;
    public int tick_runs;
    public Setting swing;
    
    @Override
    public void enable() {
        if (this.find_in_hotbar() == -1) {
            this.set_disable();
            return;
        }
        if (Surround.mc.player != null) {
            this.y_level = (int)Math.round(Surround.mc.player.posY);
            this.center_block = this.get_center(Surround.mc.player.posX, Surround.mc.player.posY, Surround.mc.player.posZ);
            if (this.center.get_value(true)) {
                Surround.mc.player.motionX = Double.longBitsToDouble(Double.doubleToLongBits(1.2191700128933293E308) ^ 0x7FE5B3B2C5B2754BL);
                Surround.mc.player.motionZ = Double.longBitsToDouble(Double.doubleToLongBits(3.051610617640862E307) ^ 0x7FC5BA6A95698217L);
            }
        }
    }
    
    public int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = Surround.mc.player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY && stackInSlot.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stackInSlot.getItem()).getBlock();
                if (block instanceof BlockEnderChest) {
                    return i;
                }
                if (block instanceof BlockObsidian) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    @Override
    public void update() {
        if (Surround.mc.player != null) {
            final int obf = Surround.mc.player.inventory.currentItem;
            if (this.center_block != Vec3d.ZERO && this.center.get_value(true)) {
                final double obf2 = Math.abs(this.center_block.x - Surround.mc.player.posX);
                final double obf3 = Math.abs(this.center_block.z - Surround.mc.player.posZ);
                if (obf2 <= Double.longBitsToDouble(Double.doubleToLongBits(103.30384768606643) ^ 0x7FE04AEBA4093E22L) && obf3 <= Double.longBitsToDouble(Double.doubleToLongBits(122.81832867335903) ^ 0x7FE72DC6E6A3C4ABL)) {
                    this.center_block = Vec3d.ZERO;
                }
                else {
                    final double obf4 = this.center_block.x - Surround.mc.player.posX;
                    final double obf5 = this.center_block.z - Surround.mc.player.posZ;
                    Surround.mc.player.motionX = obf4 / Double.longBitsToDouble(Double.doubleToLongBits(0.8714097303233878) ^ 0x7FEBE296A8A4F6D3L);
                    Surround.mc.player.motionZ = obf5 / Double.longBitsToDouble(Double.doubleToLongBits(0.7566715202704606) ^ 0x7FE836A7312C0A12L);
                }
            }
            if ((int)Math.round(Surround.mc.player.posY) != this.y_level && this.hybrid.get_value(true)) {
                this.set_disable();
                return;
            }
            if (!this.triggerable.get_value(true) && this.tick_runs >= this.tick_timeout.get_value(1)) {
                this.tick_runs = 0;
                this.set_disable();
                return;
            }
            int obf6 = 0;
            while (obf6 < this.tick_for_place.get_value(1)) {
                if (this.offset_step >= (this.block_head.get_value(true) ? this.surround_targets_face.length : this.surround_targets.length)) {
                    this.offset_step = 0;
                    break;
                }
                final BlockPos obf7 = new BlockPos(this.block_head.get_value(true) ? this.surround_targets_face[this.offset_step] : this.surround_targets[this.offset_step]);
                final BlockPos obf8 = new BlockPos(Surround.mc.player.getPositionVector()).add(obf7.getX(), obf7.getY(), obf7.getZ());
                boolean obf9 = true;
                if (!Surround.mc.world.getBlockState(obf8).getMaterial().isReplaceable()) {
                    obf9 = false;
                }
                for (final Entity obf10 : Surround.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(obf8))) {
                    if (!(obf10 instanceof EntityItem)) {
                        if (obf10 instanceof EntityXPOrb) {
                            continue;
                        }
                        obf9 = false;
                        break;
                    }
                }
                if (obf9 && BlockUtil.placeBlock(obf8, this.find_in_hotbar(), this.rotate.get_value(true), this.rotate.get_value(true), this.swing)) {
                    ++obf6;
                }
                ++this.offset_step;
            }
            ++this.tick_runs;
            Surround.mc.player.inventory.currentItem = obf;
        }
    }
    
    public Vec3d get_center(final double obf, final double obf, final double obf) {
        final double obf2 = Math.floor(obf) + Double.longBitsToDouble(Double.doubleToLongBits(104.77705633114373) ^ 0x7FBA31BB4A7A5A5FL);
        final double obf3 = Math.floor(obf);
        final double obf4 = Math.floor(obf) + Double.longBitsToDouble(Double.doubleToLongBits(3.4956204067192997) ^ 0x7FEBF707D4F0B786L);
        return new Vec3d(obf2, obf3, obf4);
    }
    
    public Surround() {
        super(Category.combat);
        this.rotate = this.create("Rotate", "SurroundSmoth", true);
        this.hybrid = this.create("Hybrid", "SurroundHybrid", true);
        this.triggerable = this.create("Toggle", "SurroundToggle", true);
        this.center = this.create("Center", "SurroundCenter", true);
        this.block_head = this.create("Block Face", "SurroundBlockFace", false);
        this.tick_for_place = this.create("Blocks per tick", "SurroundTickToPlace", 2, 1, 8);
        this.tick_timeout = this.create("Ticks til timeout", "SurroundTicks", 20, 10, 50);
        this.swing = this.create("Swing", "SurroundSwing", "Mainhand", this.combobox("Mainhand", "Offhand", "Both", "None"));
        this.y_level = 0;
        this.tick_runs = 0;
        this.offset_step = 0;
        this.center_block = Vec3d.ZERO;
        this.surround_targets = new Vec3d[] { new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.3387448001166735) ^ 0x7FED5ADFEAB8C3C2L), Double.longBitsToDouble(Double.doubleToLongBits(1.512150558533629E308) ^ 0x7FEAEACBE0A21991L), Double.longBitsToDouble(Double.doubleToLongBits(4.3275596474302113E307) ^ 0x7FCED030FCE8A0AFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.038031329706698E307) ^ 0x7FD90E64A57F674DL), Double.longBitsToDouble(Double.doubleToLongBits(1.4721566305867648E308) ^ 0x7FEA348BC7B365C1L), Double.longBitsToDouble(Double.doubleToLongBits(14.303563577931401) ^ 0x7FDC9B6CAF6EF1B9L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-14.720286235943265) ^ 0x7FDD70C95B864541L), Double.longBitsToDouble(Double.doubleToLongBits(1.4538433418627053E308) ^ 0x7FE9E117DF592D9CL), Double.longBitsToDouble(Double.doubleToLongBits(1.6159688772480816E308) ^ 0x7FECC3E433C42084L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.2743671344042599E306) ^ 0x7F7D0940C5E68BFFL), Double.longBitsToDouble(Double.doubleToLongBits(1.5174894861595294E308) ^ 0x7FEB0320293EFB97L), Double.longBitsToDouble(Double.doubleToLongBits(-101.11899000001698) ^ 0x7FA9479D883BA7EFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(13.564942418962536) ^ 0x7FDB214021FB226BL), Double.longBitsToDouble(Double.doubleToLongBits(-6.181761218369899) ^ 0x7FE8BA1F9CE251BFL), Double.longBitsToDouble(Double.doubleToLongBits(1.1016424927395176E307) ^ 0x7FAF6035B7D8270FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.073378913252529E307) ^ 0x7FD59F37E5311F5FL), Double.longBitsToDouble(Double.doubleToLongBits(-7.815692992149359) ^ 0x7FEF434506136E79L), Double.longBitsToDouble(Double.doubleToLongBits(13.577447460611518) ^ 0x7FDB27A7318CF82DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-30.269623814615212) ^ 0x7FCE450610F9FEBFL), Double.longBitsToDouble(Double.doubleToLongBits(-442.81279572711713) ^ 0x7F8BAD013617A4BFL), Double.longBitsToDouble(Double.doubleToLongBits(1.5639375938553332E308) ^ 0x7FEBD6C9928BA503L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.5141896034286E308) ^ 0x7FEAF41695E2FA67L), Double.longBitsToDouble(Double.doubleToLongBits(-6.217847870811264) ^ 0x7FE8DF1383228CEAL), Double.longBitsToDouble(Double.doubleToLongBits(-11.295512160332079) ^ 0x7FD6974D5EB064A7L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.5371299813022733E308) ^ 0x7FEB5CA05C4EA380L), Double.longBitsToDouble(Double.doubleToLongBits(-14.57204248641653) ^ 0x7FDD24E2C0B629BDL), Double.longBitsToDouble(Double.doubleToLongBits(1.0564746732652593E308) ^ 0x7FE2CE4DB87506B1L)) };
        this.surround_targets_face = new Vec3d[] { new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(8.449667830469378) ^ 0x7FD0E63ADCA3DBC7L), Double.longBitsToDouble(Double.doubleToLongBits(6.690134819592498) ^ 0x7FEAC2B2B3BFEC2DL), Double.longBitsToDouble(Double.doubleToLongBits(8.87403172173913E307) ^ 0x7FDF97B4BD245555L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.3842312474863365E308) ^ 0x7FE8A3DFD41EAA20L), Double.longBitsToDouble(Double.doubleToLongBits(5.435556265974817) ^ 0x7FE5BE027637B808L), Double.longBitsToDouble(Double.doubleToLongBits(9.905509729725734) ^ 0x7FD3CF9EF8A6C37FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-4.715176479633624) ^ 0x7FE2DC57391B944DL), Double.longBitsToDouble(Double.doubleToLongBits(26.051884722201418) ^ 0x7FCA0D4851310463L), Double.longBitsToDouble(Double.doubleToLongBits(1.8805305503666957E307) ^ 0x7FBAC796BF0319EFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.6709365937026685E308) ^ 0x7FEDBE606A2730ADL), Double.longBitsToDouble(Double.doubleToLongBits(11.50756167017237) ^ 0x7FD703DF1F8C2FDBL), Double.longBitsToDouble(Double.doubleToLongBits(-4.903644403841855) ^ 0x7FE39D54F566DB53L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(5.143706613502915) ^ 0x7FE49327D394DB11L), Double.longBitsToDouble(Double.doubleToLongBits(8.931510220018341E307) ^ 0x7FDFCC1762FC0821L), Double.longBitsToDouble(Double.doubleToLongBits(9.287678538850749E306) ^ 0x7FAA73C27F028A3FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(8.79660580704634E307) ^ 0x7FDF51240AA03E5FL), Double.longBitsToDouble(Double.doubleToLongBits(1.6910082477881574E308) ^ 0x7FEE19D798665D3FL), Double.longBitsToDouble(Double.doubleToLongBits(5.738943345738029) ^ 0x7FE6F4AD907E2AA8L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-9.87434535166757) ^ 0x7FD3BFAA31A5A4C7L), Double.longBitsToDouble(Double.doubleToLongBits(1.1064650346058972E308) ^ 0x7FE3B21B73D26B37L), Double.longBitsToDouble(Double.doubleToLongBits(1.7233930322979574E308) ^ 0x7FEEAD6B059381E0L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.471982124802412E308) ^ 0x7FEA33C0348868D9L), Double.longBitsToDouble(Double.doubleToLongBits(1.0486990881739513E308) ^ 0x7FE2AADEE1D70F71L), Double.longBitsToDouble(Double.doubleToLongBits(-16.8477108002815) ^ 0x7FC0D9039333ACCFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(56.50675458088147) ^ 0x7FBC40DD5587FDF7L), Double.longBitsToDouble(Double.doubleToLongBits(-12.866745214838831) ^ 0x7FD9BBC6075F6501L), Double.longBitsToDouble(Double.doubleToLongBits(8.674878616489046E307) ^ 0x7FDEE23326E491BFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.4052324316115412E307) ^ 0x7FB402DC4DD09367L), Double.longBitsToDouble(Double.doubleToLongBits(-252.24199112338462) ^ 0x7F9F87BE642B1B7FL), Double.longBitsToDouble(Double.doubleToLongBits(4.64667693049361) ^ 0x7FE296327A2E30DEL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-6.791141190826026) ^ 0x7FEB2A20EA947711L), Double.longBitsToDouble(Double.doubleToLongBits(-8.404277468111383) ^ 0x7FD0CEFD74D0189DL), Double.longBitsToDouble(Double.doubleToLongBits(1.3628709750193837E308) ^ 0x7FE842895F438D1EL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.0725954177051384E308) ^ 0x7FE317C3D9D46004L), Double.longBitsToDouble(Double.doubleToLongBits(-7.908809654814681) ^ 0x7FEFA29EFF86DF57L), Double.longBitsToDouble(Double.doubleToLongBits(-33.918899797209114) ^ 0x7FB0F59E8230A837L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.2433341718488402E308) ^ 0x7FE621D02F6EDDD7L), Double.longBitsToDouble(Double.doubleToLongBits(-4.2625887155585165) ^ 0x7FE10CE40E667D6AL), Double.longBitsToDouble(Double.doubleToLongBits(5.915790072804808E307) ^ 0x7FD50F97E993E2C7L)) };
        this.name = "Surround";
        this.tag = "Surround";
        this.description = "surround urself with obi and such";
    }
}
