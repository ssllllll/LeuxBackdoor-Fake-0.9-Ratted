// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import me.sazked.leux.Leux;
import net.minecraft.item.ItemSword;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.List;
import me.sazked.leux.client.modules.Category;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.util.FriendUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class KillAura extends Module
{
    public Setting mode;
    public Setting sword;
    public Setting sync_tps;
    public boolean start_verify;
    public double tick;
    public Setting hostile;
    public Setting range;
    public Setting delay;
    public EnumHand actual_hand;
    public Setting player;
    
    public static boolean lambda$find_entity$0(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public boolean is_compatible(final Entity entity) {
        return (this.player.get_value(true) && entity instanceof EntityPlayer && entity != KillAura.mc.player && !entity.getName().equals(KillAura.mc.player.getName())) || (this.hostile.get_value(true) && entity instanceof IMob) || (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getHealth() <= 0.0f && false);
    }
    
    public boolean checkSharpness(final ItemStack obf) {
        if (obf.getTagCompound() == null) {
            return false;
        }
        final NBTTagList obf2 = (NBTTagList)obf.getTagCompound().getTag("ench");
        if (obf2 == null) {
            return false;
        }
        int obf3 = 0;
        while (obf3 < obf2.tagCount()) {
            final NBTTagCompound obf4 = obf2.getCompoundTagAt(obf3);
            if (obf4.getInteger("id") == 16) {
                final int obf5 = obf4.getInteger("lvl");
                if (obf5 > 5) {
                    return true;
                }
                break;
            }
            else {
                ++obf3;
            }
        }
        return false;
    }
    
    public void attack_entity(final Entity obf) {
        if (this.mode.in("32k")) {
            int obf2 = -1;
            for (int obf3 = 0; obf3 < 9; ++obf3) {
                final ItemStack obf4 = KillAura.mc.player.inventory.getStackInSlot(obf3);
                if (obf4 != ItemStack.EMPTY) {
                    if (this.checkSharpness(obf4)) {
                        obf2 = obf3;
                        break;
                    }
                }
            }
            if (obf2 != -1) {
                KillAura.mc.player.inventory.currentItem = obf2;
            }
        }
        final ItemStack obf5 = KillAura.mc.player.getHeldItemOffhand();
        if (obf5.getItem() == Items.SHIELD) {
            KillAura.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, KillAura.mc.player.getHorizontalFacing()));
        }
        KillAura.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(obf));
        KillAura.mc.player.swingArm(this.actual_hand);
        KillAura.mc.player.resetCooldown();
    }
    
    public KillAura() {
        super(Category.combat);
        this.mode = this.create("Mode", "KillAuraMode", "Normal", this.combobox("32k", "Normal"));
        this.player = this.create("Player", "KillAuraPlayer", true);
        this.hostile = this.create("Hostile", "KillAuraHostile", true);
        this.sword = this.create("Sword", "KillAuraSword", true);
        this.sync_tps = this.create("Sync TPS", "KillAuraSyncTps", true);
        this.range = this.create("Range", "KillAuraRange", Double.longBitsToDouble(Double.doubleToLongBits(0.24990844306150403) ^ 0x7FDBFCFFF6F8C293L), Double.longBitsToDouble(Double.doubleToLongBits(2.0139580519137366) ^ 0x7FE01C960A03E209L), Double.longBitsToDouble(Double.doubleToLongBits(0.15349484072742117) ^ 0x7FDBA5B80C83B65DL));
        this.delay = this.create("Delay", "KillAuraDelay", 2, 0, 10);
        this.start_verify = true;
        this.actual_hand = EnumHand.MAIN_HAND;
        this.tick = Double.longBitsToDouble(Double.doubleToLongBits(8.434232370004564E307) ^ 0x7FDE06E08E23ACD7L);
        this.name = "Kill Aura";
        this.tag = "KillAura";
        this.description = "To able hit enemies in a range.";
    }
    
    public Entity find_entity() {
        Entity obf = null;
        for (final Entity obf2 : (List)KillAura.mc.world.playerEntities.stream().filter(KillAura::lambda$find_entity$0).collect(Collectors.toList())) {
            if (obf2 != null && this.is_compatible(obf2) && KillAura.mc.player.getDistance(obf2) <= this.range.get_value(Double.longBitsToDouble(Double.doubleToLongBits(7.041748224694115) ^ 0x7FEC2AC00BEEE8BDL))) {
                obf = obf2;
            }
        }
        return obf;
    }
    
    @Override
    public void update() {
        if (KillAura.mc.player != null && KillAura.mc.world != null) {
            ++this.tick;
            if (KillAura.mc.player.isDead | KillAura.mc.player.getHealth() <= 0.0f) {
                return;
            }
            if (this.mode.in("Normal")) {
                if (!(KillAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && this.sword.get_value(true)) {
                    this.start_verify = false;
                }
                else if (KillAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && this.sword.get_value(true)) {
                    this.start_verify = true;
                }
                else if (!this.sword.get_value(true)) {
                    this.start_verify = true;
                }
                final Entity find_entity = this.find_entity();
                if (find_entity != null && this.start_verify) {
                    final float n = 20.0f - Leux.get_event_handler().get_tick_rate();
                    if (KillAura.mc.player.getCooledAttackStrength(this.sync_tps.get_value(true) ? (-n) : 0.0f) >= 1.0f) {
                        this.attack_entity(find_entity);
                    }
                }
            }
            else {
                if (!(KillAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword)) {
                    return;
                }
                if (this.tick < this.delay.get_value(1)) {
                    return;
                }
                this.tick = 0.0;
                final Entity find_entity2 = this.find_entity();
                if (find_entity2 != null) {
                    this.attack_entity(find_entity2);
                }
            }
        }
    }
    
    @Override
    public String array_detail() {
        return this.mode.get_current_value();
    }
    
    @Override
    public void enable() {
        this.tick = 0.0;
    }
}
