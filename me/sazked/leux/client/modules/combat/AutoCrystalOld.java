// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import java.awt.Color;
import me.sazked.turok.draw.RenderHelp;
import me.sazked.leux.client.util.EntityUtil;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemSword;
import java.util.Objects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import me.sazked.leux.Leux;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.math.Vec3d;
import me.sazked.leux.client.util.MathUtil;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.util.RotationUtil;
import me.sazked.leux.client.util.PosManager;
import me.sazked.leux.client.modules.chat.AutoEz;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import me.sazked.leux.client.util.MessageUtil;
import net.minecraft.network.play.client.CPacketPlayer;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import java.util.List;
import me.sazked.leux.client.util.BlockUtil;
import me.sazked.leux.client.util.FriendUtil;
import me.sazked.leux.client.util.CrystalUtil;
import me.sazked.leux.client.util.Pair;
import java.util.ArrayList;
import me.sazked.leux.client.util.RenderUtil;
import me.sazked.leux.client.event.events.EventRender;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.init.Items;
import me.sazked.leux.client.event.events.EventEntityRemoved;
import net.minecraft.entity.item.EntityEnderCrystal;
import java.util.concurrent.ConcurrentHashMap;
import me.sazked.leux.client.event.events.EventMotionUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.util.Timer;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoCrystalOld extends Module
{
    public Setting rotate_mode;
    public Setting swing;
    public Setting anti_weakness;
    public Setting b;
    public Timer remove_visual_timer;
    @EventHandler
    public Listener<EventPacket.SendPacket> send_listener;
    public Setting faceplace_check;
    public int break_delay_counter;
    public Setting old_render;
    public String detail_name;
    public Setting r;
    public Setting fuck_armor_mode;
    public Setting min_player_break;
    public Setting render_mode;
    public Setting render_damage;
    public Setting top_block;
    public boolean place_timeout_flag;
    public boolean already_attacking;
    public Setting anti_suicide;
    public Setting debug;
    public Setting client_side;
    public Setting chain_length;
    public Setting break_delay;
    public Setting hit_range;
    public Setting stop_while_mining;
    public int chain_step;
    public Timer chain_timer;
    public int current_chain_index;
    public int place_timeout;
    public float pitch;
    public boolean outline;
    public Setting min_player_place;
    public Setting height;
    public float yaw;
    public Setting break_trys;
    public BlockPos render_block_old;
    public Setting rainbow_mode;
    public EntityPlayer autoez_target;
    @EventHandler
    public Listener<EventMotionUpdate> on_movement;
    public int place_delay_counter;
    public Setting place_crystal;
    public int break_timeout;
    public boolean solid;
    public double render_damage_value;
    public boolean did_anything;
    public int detail_hp;
    public ConcurrentHashMap<EntityEnderCrystal, Integer> attacked_crystals;
    public Setting a;
    public Setting a_out;
    public boolean is_rotating;
    public Setting max_self_damage;
    public Setting hit_range_wall;
    public Setting faceplace_mode_damage;
    public Setting g;
    public BlockPos render_block_init;
    public Setting future_render;
    public Setting fuck_armor_mode_precent;
    @EventHandler
    public Listener<EventPacket.ReceivePacket> receive_listener;
    public Setting place_range;
    @EventHandler
    public Listener<EventEntityRemoved> on_entity_removed;
    public Setting anti_stuck;
    public Setting raytrace;
    public Setting break_crystal;
    public Setting auto_switch;
    public Setting endcrystal;
    public Setting brightness;
    public Setting place_delay;
    public Setting faceplace_mode;
    public static EntityPlayer ca_target;
    public Setting fast_mode;
    public Setting sat;
    public Setting jumpy_mode;
    
    public int find_crystals_hotbar() {
        for (int i = 0; i < 9; ++i) {
            if (AutoCrystalOld.mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
    }
    
    static {
        AutoCrystalOld.ca_target = null;
    }
    
    public static void lambda$new$3(final EventPacket.ReceivePacket receivePacket) {
        if (receivePacket.get_packet() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receivePacket.get_packet();
            if (sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS) {
                if (sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                    for (final Entity entity : AutoCrystalOld.mc.world.loadedEntityList) {
                        if (entity instanceof EntityEnderCrystal && entity.getDistance(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()) <= 6.0) {
                            entity.setDead();
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void render(final EventRender obf) {
        if (this.render_block_init == null) {
            return;
        }
        if (this.render_mode.in("None")) {
            return;
        }
        if (this.render_mode.in("Pretty")) {
            this.outline = true;
            this.solid = true;
        }
        if (this.render_mode.in("Solid")) {
            this.outline = false;
            this.solid = true;
        }
        if (this.render_mode.in("Outline")) {
            this.outline = true;
            this.solid = false;
        }
        this.render_block(this.render_block_init);
        if (this.future_render.get_value(true) && this.render_block_old != null) {
            this.render_block(this.render_block_old);
        }
        if (this.render_damage.get_value(true)) {
            RenderUtil.drawText(this.render_block_init, ((Math.floor(this.render_damage_value) == this.render_damage_value) ? Integer.valueOf((int)this.render_damage_value) : String.format("%.1f", this.render_damage_value)) + "");
        }
    }
    
    public BlockPos get_best_block() {
        if (this.get_best_crystal() != null) {
            if (!this.fast_mode.get_value(true)) {
                this.place_timeout_flag = true;
                return null;
            }
        }
        if (this.place_timeout_flag) {
            this.place_timeout_flag = false;
            return null;
        }
        List<Pair<Double, BlockPos>> obf = new ArrayList<Pair<Double, BlockPos>>();
        double obf2 = Double.longBitsToDouble(Double.doubleToLongBits(7.465776396255996E306) ^ 0x7FA543637FB7FC7FL);
        final double obf3 = this.max_self_damage.get_value(1);
        BlockPos obf4 = null;
        final List<BlockPos> obf5 = CrystalUtil.possiblePlacePositions((float)this.place_range.get_value(1), this.endcrystal.get_value(true), true);
        for (final Entity obf6 : AutoCrystalOld.mc.world.playerEntities) {
            if (FriendUtil.isFriend(obf6.getName())) {
                continue;
            }
            AutoCrystalOld.ca_target = (EntityPlayer)obf6;
            for (final BlockPos obf7 : obf5) {
                if (obf6 != AutoCrystalOld.mc.player) {
                    if (!(obf6 instanceof EntityPlayer)) {
                        continue;
                    }
                    if (obf6.getDistance((Entity)AutoCrystalOld.mc.player) >= Float.intBitsToFloat(Float.floatToIntBits(0.16351917f) ^ 0x7F177192)) {
                        continue;
                    }
                    if (!BlockUtil.rayTracePlaceCheck(obf7, this.raytrace.get_value(true))) {
                        continue;
                    }
                    if (!BlockUtil.canSeeBlock(obf7) && AutoCrystalOld.mc.player.getDistance((double)obf7.getX(), (double)obf7.getY(), (double)obf7.getZ()) > this.hit_range_wall.get_value(1)) {
                        continue;
                    }
                    final EntityPlayer obf8 = (EntityPlayer)obf6;
                    if (obf8.isDead) {
                        continue;
                    }
                    if (obf8.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(1.4732137E38f) ^ 0x7EDDAA21)) {
                        continue;
                    }
                    final boolean obf9 = this.faceplace_check.get_value(true) && AutoCrystalOld.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD;
                    double obf10;
                    if ((obf8.getHealth() < this.faceplace_mode_damage.get_value(1) && this.faceplace_mode.get_value(true) && !obf9) || (this.get_armor_fucker(obf8) && !obf9)) {
                        obf10 = Double.longBitsToDouble(Double.doubleToLongBits(0.2543481466308119) ^ 0x7FD0473D72E4F693L);
                    }
                    else {
                        obf10 = this.min_player_place.get_value(1);
                    }
                    final double obf11 = CrystalUtil.calculateDamage(obf7.getX() + Double.longBitsToDouble(Double.doubleToLongBits(15.15122983158607) ^ 0x7FCE4D6DFF19AEFBL), obf7.getY() + Double.longBitsToDouble(Double.doubleToLongBits(5.569023530139341) ^ 0x7FE646AE1AB26969L), obf7.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(2.4040952285377717) ^ 0x7FE33B96477850A4L), (Entity)obf8);
                    if (obf11 < obf10) {
                        continue;
                    }
                    final double obf12 = CrystalUtil.calculateDamage(obf7.getX() + Double.longBitsToDouble(Double.doubleToLongBits(3.8484630259642234) ^ 0x7FEEC9A6FBA30D64L), obf7.getY() + Double.longBitsToDouble(Double.doubleToLongBits(4.27292892237755) ^ 0x7FE1177AADEEF9F5L), obf7.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(37.69513370104607) ^ 0x7FA2D8FA24202B8FL), (Entity)AutoCrystalOld.mc.player);
                    if (obf12 > obf3) {
                        continue;
                    }
                    if (this.anti_suicide.get_value(true) && AutoCrystalOld.mc.player.getHealth() + AutoCrystalOld.mc.player.getAbsorptionAmount() - obf12 <= Double.longBitsToDouble(Double.doubleToLongBits(66.03162975070221) ^ 0x7FB0820638CA362FL)) {
                        continue;
                    }
                    if (obf11 <= obf2) {
                        continue;
                    }
                    obf2 = obf11;
                    obf4 = obf7;
                    this.autoez_target = obf8;
                }
            }
        }
        obf5.clear();
        if (this.chain_step == 1) {
            this.current_chain_index = this.chain_length.get_value(1);
        }
        else if (this.chain_step > 1) {
            --this.current_chain_index;
        }
        this.render_damage_value = obf2;
        this.render_block_init = obf4;
        obf = this.sort_best_blocks(obf);
        return obf4;
    }
    
    public AutoCrystalOld() {
        super(Category.combat);
        this.debug = this.create("Debug", "CaDebug", false);
        this.place_crystal = this.create("Place", "CaPlace", true);
        this.break_crystal = this.create("Break", "CaBreak", true);
        this.break_trys = this.create("Break Attempts", "CaBreakAttempts", 1, 1, 6);
        this.anti_weakness = this.create("Anti-Weakness", "CaAntiWeakness", true);
        this.hit_range = this.create("Hit Range", "CaHitRange", Double.longBitsToDouble(Double.doubleToLongBits(0.16892985639555155) ^ 0x7FD153B29844B82BL), Double.longBitsToDouble(Double.doubleToLongBits(6.005996564693339) ^ 0x7FE80623F6A4FC26L), Double.longBitsToDouble(Double.doubleToLongBits(1.2644796060185726) ^ 0x7FEC3B4EF7A4F093L));
        this.place_range = this.create("Place Range", "CaPlaceRange", Double.longBitsToDouble(Double.doubleToLongBits(1.5262723700081604) ^ 0x7FECA750539F934BL), Double.longBitsToDouble(Double.doubleToLongBits(21.84779665936139) ^ 0x7FC5D90933AD9D7FL), Double.longBitsToDouble(Double.doubleToLongBits(1.9702434557416773) ^ 0x7FE7861E007918B0L));
        this.hit_range_wall = this.create("Range Wall", "CaRangeWall", Double.longBitsToDouble(Double.doubleToLongBits(1.5757924293940289) ^ 0x7FE936721F588205L), Double.longBitsToDouble(Double.doubleToLongBits(4.299548441267846) ^ 0x7FE132BCD39B40B1L), Double.longBitsToDouble(Double.doubleToLongBits(1.1586260746490584) ^ 0x7FEA89BB7EAE91A8L));
        this.place_delay = this.create("Place Delay", "CaPlaceDelay", 1, 0, 10);
        this.break_delay = this.create("Break Delay", "CaBreakDelay", 1, 0, 10);
        this.min_player_place = this.create("Min Enemy Place", "CaMinEnemyPlace", 8, 0, 20);
        this.min_player_break = this.create("Min Enemy Break", "CaMinEnemyBreak", 6, 0, 20);
        this.max_self_damage = this.create("Max Self Damage", "CaMaxSelfDamage", 6, 0, 20);
        this.rotate_mode = this.create("Rotate", "CaRotateMode", "Good", this.combobox("Off", "Old", "Const", "Good"));
        this.raytrace = this.create("Raytrace", "CaRaytrace", false);
        this.auto_switch = this.create("Auto Switch", "CaAutoSwitch", true);
        this.anti_suicide = this.create("Anti Suicide", "CaAntiSuicide", true);
        this.fast_mode = this.create("Fast Mode", "CaSpeed", true);
        this.client_side = this.create("Client Side", "CaClientSide", false);
        this.jumpy_mode = this.create("Jumpy Mode", "CaJumpyMode", false);
        this.anti_stuck = this.create("Anti Stuck", "CaAntiStuck", false);
        this.endcrystal = this.create("1.13 Mode", "CaThirteen", false);
        this.faceplace_mode = this.create("Tabbott Mode", "CaTabbottMode", true);
        this.faceplace_mode_damage = this.create("T Health", "CaTabbottModeHealth", 8, 0, 36);
        this.fuck_armor_mode = this.create("Armor Destroy", "CaArmorDestory", true);
        this.fuck_armor_mode_precent = this.create("Armor %", "CaArmorPercent", 25, 0, 100);
        this.stop_while_mining = this.create("Stop While Mining", "CaStopWhileMining", false);
        this.faceplace_check = this.create("No Sword FP", "CaJumpyFaceMode", false);
        this.swing = this.create("Swing", "CaSwing", "Mainhand", this.combobox("Mainhand", "Offhand", "Both", "None"));
        this.render_mode = this.create("Render", "CaRenderMode", "Pretty", this.combobox("Pretty", "Solid", "Outline", "None"));
        this.old_render = this.create("Old Render", "CaOldRender", false);
        this.future_render = this.create("Future Render", "CaFutureRender", false);
        this.top_block = this.create("Top Block", "CaTopBlock", false);
        this.r = this.create("R", "CaR", 255, 0, 255);
        this.g = this.create("G", "CaG", 255, 0, 255);
        this.b = this.create("B", "CaB", 255, 0, 255);
        this.a = this.create("A", "CaA", 100, 0, 255);
        this.a_out = this.create("Outline A", "CaOutlineA", 255, 0, 255);
        this.rainbow_mode = this.create("Rainbow", "CaRainbow", false);
        this.sat = this.create("Satiation", "CaSatiation", Double.longBitsToDouble(Double.doubleToLongBits(20.99125541785865) ^ 0x7FDD645B73D83611L), Double.longBitsToDouble(Double.doubleToLongBits(3.477473682089908E307) ^ 0x7FC8C2ABD5FD712FL), Double.longBitsToDouble(Double.doubleToLongBits(22.617022652965613) ^ 0x7FC69DF5325360E3L));
        this.brightness = this.create("Brightness", "CaBrightness", Double.longBitsToDouble(Double.doubleToLongBits(29.161478216372306) ^ 0x7FD4B0CF3B73CC7BL), Double.longBitsToDouble(Double.doubleToLongBits(1.6945765977344026E308) ^ 0x7FEE2A1A5BFA780CL), Double.longBitsToDouble(Double.doubleToLongBits(4.497558505288776) ^ 0x7FE1FD7FFA103F6CL));
        this.height = this.create("Height", "CaHeight", Double.longBitsToDouble(Double.doubleToLongBits(7.58683352476042) ^ 0x7FEE58EAE3342B6EL), Double.longBitsToDouble(Double.doubleToLongBits(4.325550592857278E307) ^ 0x7FCECC877FC89493L), Double.longBitsToDouble(Double.doubleToLongBits(10.905893073771795) ^ 0x7FD5CFD1378B0BA9L));
        this.render_damage = this.create("Render Damage", "RenderDamage", true);
        this.chain_length = this.create("Chain Length", "CaChainLength", 3, 1, 6);
        this.attacked_crystals = new ConcurrentHashMap<EntityEnderCrystal, Integer>();
        this.remove_visual_timer = new Timer();
        this.chain_timer = new Timer();
        this.autoez_target = null;
        this.detail_name = null;
        this.detail_hp = 0;
        this.already_attacking = false;
        this.place_timeout_flag = false;
        this.chain_step = 0;
        this.current_chain_index = 0;
        this.on_entity_removed = new Listener<EventEntityRemoved>(this::lambda$new$0, (Predicate<EventEntityRemoved>[])new Predicate[0]);
        this.send_listener = new Listener<EventPacket.SendPacket>(this::lambda$new$1, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.on_movement = new Listener<EventMotionUpdate>(this::lambda$new$2, (Predicate<EventMotionUpdate>[])new Predicate[0]);
        this.receive_listener = new Listener<EventPacket.ReceivePacket>(AutoCrystalOld::lambda$new$3, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.name = "CevBreaker";
        this.tag = "AutoCrystalOld";
        this.description = "kills people (if ur good)";
    }
    
    public void lambda$new$1(final EventPacket.SendPacket sendPacket) {
        if (sendPacket.get_packet() instanceof CPacketPlayer && this.is_rotating && this.rotate_mode.in("Old")) {
            if (this.debug.get_value(true)) {
                MessageUtil.send_client_message("Rotating");
            }
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)sendPacket.get_packet();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            this.is_rotating = false;
        }
        if (sendPacket.get_packet() instanceof CPacketPlayerTryUseItemOnBlock && this.is_rotating && this.rotate_mode.in("Old")) {
            if (this.debug.get_value(true)) {
                MessageUtil.send_client_message("Rotating");
            }
            final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)sendPacket.get_packet();
            cPacketPlayerTryUseItemOnBlock.facingX = (float)this.render_block_init.getX();
            cPacketPlayerTryUseItemOnBlock.facingY = (float)this.render_block_init.getY();
            cPacketPlayerTryUseItemOnBlock.facingZ = (float)this.render_block_init.getZ();
            this.is_rotating = false;
        }
    }
    
    public static EntityPlayer get_target() {
        return AutoCrystalOld.ca_target;
    }
    
    public void lambda$new$0(final EventEntityRemoved eventEntityRemoved) {
        if (eventEntityRemoved.get_entity() instanceof EntityEnderCrystal) {
            this.attacked_crystals.remove(eventEntityRemoved.get_entity());
        }
    }
    
    public void do_ca() {
        this.did_anything = false;
        if (AutoCrystalOld.mc.player == null || AutoCrystalOld.mc.world == null) {
            return;
        }
        if (this.rainbow_mode.get_value(true)) {
            this.cycle_rainbow();
        }
        if (this.remove_visual_timer.passed(1000L)) {
            this.remove_visual_timer.reset();
            this.attacked_crystals.clear();
        }
        if (this.check_pause()) {
            return;
        }
        if (this.place_crystal.get_value(true) && this.place_delay_counter > this.place_timeout) {
            this.place_crystal();
        }
        if (this.break_crystal.get_value(true)) {
            if (this.break_delay_counter > this.break_timeout) {
                this.break_crystal();
            }
        }
        if (!this.did_anything) {
            if (this.old_render.get_value(true)) {
                this.render_block_init = null;
            }
            this.autoez_target = null;
            this.is_rotating = false;
        }
        if (this.autoez_target != null) {
            AutoEz.add_target(this.autoez_target.getName());
            this.detail_name = this.autoez_target.getName();
            this.detail_hp = Math.round(this.autoez_target.getHealth() + this.autoez_target.getAbsorptionAmount());
        }
        if (this.chain_timer.passed(1000L)) {
            this.chain_timer.reset();
            this.chain_step = 0;
        }
        this.render_block_old = this.render_block_init;
        ++this.break_delay_counter;
        ++this.place_delay_counter;
    }
    
    public List<Pair<Double, BlockPos>> sort_best_blocks(final List<Pair<Double, BlockPos>> obf) {
        final List<Pair<Double, BlockPos>> obf2 = new ArrayList<Pair<Double, BlockPos>>();
        double obf3 = Double.longBitsToDouble(Double.doubleToLongBits(3.134940123065868E-4) ^ 0x7FBBCB8E87C4ED37L);
        for (int obf4 = 0; obf4 < obf.size(); ++obf4) {
            final double obf5 = Double.longBitsToDouble(Double.doubleToLongBits(5.055744128375825E307) ^ 0x7FD1FFC183CE6DD1L);
            Pair<Double, BlockPos> obf6 = null;
            for (final Pair<Double, BlockPos> obf7 : obf) {
                if (obf7.getKey() > obf5) {
                    if (obf7.getKey() >= obf3) {
                        continue;
                    }
                    obf6 = obf7;
                }
            }
            if (obf6 != null) {
                obf3 = obf6.getKey();
                obf2.add(obf6);
            }
        }
        return obf2;
    }
    
    public EntityEnderCrystal get_best_crystal() {
        double n = 0.0;
        final double n2 = this.max_self_damage.get_value(1);
        double distanceSq = 0.0;
        EntityEnderCrystal entityEnderCrystal = null;
        for (final Entity entity : AutoCrystalOld.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) {
                continue;
            }
            final EntityEnderCrystal entityEnderCrystal2 = (EntityEnderCrystal)entity;
            if (AutoCrystalOld.mc.player.getDistance((Entity)entityEnderCrystal2) > (AutoCrystalOld.mc.player.canEntityBeSeen((Entity)entityEnderCrystal2) ? this.hit_range.get_value(1) : this.hit_range_wall.get_value(1))) {
                continue;
            }
            if (!AutoCrystalOld.mc.player.canEntityBeSeen((Entity)entityEnderCrystal2)) {
                if (this.raytrace.get_value(true)) {
                    continue;
                }
            }
            if (entityEnderCrystal2.isDead) {
                continue;
            }
            if (this.attacked_crystals.containsKey(entityEnderCrystal2) && this.attacked_crystals.get(entityEnderCrystal2) > 5 && this.anti_stuck.get_value(true)) {
                continue;
            }
            for (final Entity entity2 : AutoCrystalOld.mc.world.playerEntities) {
                if (entity2 != AutoCrystalOld.mc.player) {
                    if (!(entity2 instanceof EntityPlayer)) {
                        continue;
                    }
                    if (FriendUtil.isFriend(entity2.getName())) {
                        continue;
                    }
                    if (entity2.getDistance((Entity)AutoCrystalOld.mc.player) >= 11.0f) {
                        continue;
                    }
                    final EntityPlayer entityPlayer = (EntityPlayer)entity2;
                    if (entityPlayer.isDead) {
                        continue;
                    }
                    if (entityPlayer.getHealth() <= 0.0f) {
                        continue;
                    }
                    final boolean b = this.faceplace_check.get_value(true) && AutoCrystalOld.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD;
                    double n3;
                    if ((entityPlayer.getHealth() < this.faceplace_mode_damage.get_value(1) && this.faceplace_mode.get_value(true) && !b) || (this.get_armor_fucker(entityPlayer) && !b)) {
                        n3 = 2.0;
                    }
                    else {
                        n3 = this.min_player_break.get_value(1);
                    }
                    final double n4 = CrystalUtil.calculateDamage(entityEnderCrystal2, (Entity)entityPlayer);
                    if (n4 < n3) {
                        continue;
                    }
                    final double n5 = CrystalUtil.calculateDamage(entityEnderCrystal2, (Entity)AutoCrystalOld.mc.player);
                    if (n5 > n2) {
                        continue;
                    }
                    if (this.anti_suicide.get_value(true) && AutoCrystalOld.mc.player.getHealth() + AutoCrystalOld.mc.player.getAbsorptionAmount() - n5 <= 0.5) {
                        continue;
                    }
                    if (n4 <= n || this.jumpy_mode.get_value(true)) {
                        continue;
                    }
                    this.autoez_target = entityPlayer;
                    n = n4;
                    entityEnderCrystal = entityEnderCrystal2;
                }
            }
            if (!this.jumpy_mode.get_value(true) || AutoCrystalOld.mc.player.getDistanceSq((Entity)entityEnderCrystal2) <= distanceSq) {
                continue;
            }
            distanceSq = AutoCrystalOld.mc.player.getDistanceSq((Entity)entityEnderCrystal2);
            entityEnderCrystal = entityEnderCrystal2;
        }
        return entityEnderCrystal;
    }
    
    public void lambda$new$2(final EventMotionUpdate obf) {
        Label_0277: {
            if (obf.stage == 0) {
                if (!this.rotate_mode.in("Good")) {
                    if (!this.rotate_mode.in("Const")) {
                        break Label_0277;
                    }
                }
                if (this.debug.get_value(true)) {
                    MessageUtil.send_client_message("updating rotation");
                }
                PosManager.updatePosition();
                RotationUtil.updateRotations();
                this.do_ca();
            }
        }
        if (obf.stage == 1 && (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))) {
            if (this.debug.get_value(true)) {
                MessageUtil.send_client_message("resetting rotation");
            }
            PosManager.restorePosition();
            RotationUtil.restoreRotations();
        }
    }
    
    @Override
    public void disable() {
        this.render_block_init = null;
        this.autoez_target = null;
    }
    
    public boolean get_armor_fucker(final EntityPlayer entityPlayer) {
        for (final ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
            if (itemStack == null || itemStack.getItem() == Items.AIR) {
                return true;
            }
            final float n = (itemStack.getMaxDamage() - itemStack.getItemDamage()) / (float)itemStack.getMaxDamage() * 100.0f;
            if (this.fuck_armor_mode.get_value(true) && this.fuck_armor_mode_precent.get_value(1) >= n) {
                return true;
            }
        }
        return false;
    }
    
    public void place_crystal() {
        final BlockPos obf = this.get_best_block();
        if (obf == null) {
            return;
        }
        this.place_delay_counter = 0;
        this.already_attacking = false;
        boolean obf2 = false;
        if (AutoCrystalOld.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            if (AutoCrystalOld.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && this.auto_switch.get_value(true)) {
                if (this.find_crystals_hotbar() == -1) {
                    return;
                }
                AutoCrystalOld.mc.player.inventory.currentItem = this.find_crystals_hotbar();
                return;
            }
        }
        else {
            obf2 = true;
        }
        if (this.debug.get_value(true)) {
            MessageUtil.send_client_message("placing");
        }
        ++this.chain_step;
        this.did_anything = true;
        this.rotate_to_pos(obf);
        this.chain_timer.reset();
        BlockUtil.placeCrystalOnBlock(obf, obf2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
    }
    
    public void rotate_to(final Entity entity) {
        final float[] calcAngle = MathUtil.calcAngle(AutoCrystalOld.mc.player.getPositionEyes(AutoCrystalOld.mc.getRenderPartialTicks()), entity.getPositionVector());
        if (this.rotate_mode.in("Off")) {
            this.is_rotating = false;
        }
        if (this.rotate_mode.in("Good")) {
            RotationUtil.setPlayerRotations(calcAngle[0], calcAngle[1]);
        }
        if (this.rotate_mode.in("Old") || this.rotate_mode.in("Cont")) {
            this.yaw = calcAngle[0];
            this.pitch = calcAngle[1];
            this.is_rotating = true;
        }
    }
    
    public void rotate_to_pos(final BlockPos obf) {
        float[] obf2;
        if (this.rotate_mode.in("Const")) {
            obf2 = MathUtil.calcAngle(AutoCrystalOld.mc.player.getPositionEyes(AutoCrystalOld.mc.getRenderPartialTicks()), new Vec3d((double)(obf.getX() + Float.intBitsToFloat(Float.floatToIntBits(7669.9995f) ^ 0x7AEFAFFF)), (double)(obf.getY() + Float.intBitsToFloat(Float.floatToIntBits(2.9718487f) ^ 0x7F3E32C5)), (double)(obf.getZ() + Float.intBitsToFloat(Float.floatToIntBits(8.533824f) ^ 0x7E088A8B))));
        }
        else {
            obf2 = MathUtil.calcAngle(AutoCrystalOld.mc.player.getPositionEyes(AutoCrystalOld.mc.getRenderPartialTicks()), new Vec3d((double)(obf.getX() + Float.intBitsToFloat(Float.floatToIntBits(34.42529f) ^ 0x7D09B37F)), (double)(obf.getY() - Float.intBitsToFloat(Float.floatToIntBits(435.66208f) ^ 0x7CD9D4BF)), (double)(obf.getZ() + Float.intBitsToFloat(Float.floatToIntBits(13.694003f) ^ 0x7E5B1AA3))));
        }
        if (this.rotate_mode.in("Off")) {
            this.is_rotating = false;
        }
        if (this.rotate_mode.in("Good") || this.rotate_mode.in("Const")) {
            RotationUtil.setPlayerRotations(obf2[0], obf2[1]);
        }
        if (this.rotate_mode.in("Old")) {
            this.yaw = obf2[0];
            this.pitch = obf2[1];
            this.is_rotating = true;
        }
    }
    
    public boolean check_pause() {
        if (this.find_crystals_hotbar() == -1 && AutoCrystalOld.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            return true;
        }
        if (this.stop_while_mining.get_value(true) && AutoCrystalOld.mc.gameSettings.keyBindAttack.isKeyDown() && AutoCrystalOld.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
            if (this.old_render.get_value(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        if (Leux.get_hack_manager().get_module_with_tag("Surround").is_active()) {
            if (this.old_render.get_value(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        if (Leux.get_hack_manager().get_module_with_tag("HoleFill").is_active()) {
            if (this.old_render.get_value(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        if (Leux.get_hack_manager().get_module_with_tag("AutoTrap").is_active()) {
            if (this.old_render.get_value(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void update() {
        if (this.rotate_mode.in("Off") || this.rotate_mode.in("Old")) {
            this.do_ca();
        }
        if (AutoCrystalOld.mc.player.isDead || AutoCrystalOld.mc.player.getHealth() <= 0.0f) {
            AutoCrystalOld.ca_target = null;
        }
    }
    
    @Override
    public String array_detail() {
        return (this.detail_name != null) ? (this.detail_name + " | " + this.detail_hp) : "None";
    }
    
    public void add_attacked_crystal(final EntityEnderCrystal entityEnderCrystal) {
        if (this.attacked_crystals.containsKey(entityEnderCrystal)) {
            this.attacked_crystals.put(entityEnderCrystal, this.attacked_crystals.get(entityEnderCrystal) + 1);
        }
        else {
            this.attacked_crystals.put(entityEnderCrystal, 1);
        }
    }
    
    public void break_crystal() {
        final EntityEnderCrystal obf = this.get_best_crystal();
        if (obf == null) {
            return;
        }
        if (this.anti_weakness.get_value(true) && AutoCrystalOld.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
            boolean obf2 = true;
            if (AutoCrystalOld.mc.player.isPotionActive(MobEffects.STRENGTH) && Objects.requireNonNull(AutoCrystalOld.mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                obf2 = false;
            }
            if (obf2) {
                if (!this.already_attacking) {
                    this.already_attacking = true;
                }
                int obf3 = -1;
                for (int obf4 = 0; obf4 < 9; ++obf4) {
                    final ItemStack obf5 = AutoCrystalOld.mc.player.inventory.getStackInSlot(obf4);
                    if (obf5.getItem() instanceof ItemSword || obf5.getItem() instanceof ItemTool) {
                        obf3 = obf4;
                        AutoCrystalOld.mc.playerController.updateController();
                        break;
                    }
                }
                if (obf3 != -1) {
                    AutoCrystalOld.mc.player.inventory.currentItem = obf3;
                }
            }
        }
        if (this.debug.get_value(true)) {
            MessageUtil.send_client_message("attacking");
        }
        this.did_anything = true;
        this.rotate_to((Entity)obf);
        for (int obf6 = 0; obf6 < this.break_trys.get_value(1); ++obf6) {
            EntityUtil.attackEntity((Entity)obf, false, this.swing);
        }
        this.add_attacked_crystal(obf);
        if (this.client_side.get_value(true) && obf.isEntityAlive()) {
            obf.setDead();
        }
        this.break_delay_counter = 0;
    }
    
    @Override
    public void enable() {
        this.place_timeout = this.place_delay.get_value(1);
        this.break_timeout = this.break_delay.get_value(1);
        this.place_timeout_flag = false;
        this.is_rotating = false;
        this.autoez_target = null;
        this.chain_step = 0;
        this.current_chain_index = 0;
        this.chain_timer.reset();
        this.remove_visual_timer.reset();
        this.detail_name = null;
        this.detail_hp = 20;
    }
    
    public void render_block(final BlockPos obf) {
        final BlockPos obf2 = this.top_block.get_value(true) ? obf.up() : obf;
        final float obf3 = (float)this.height.get_value(Double.longBitsToDouble(Double.doubleToLongBits(7.770288099169404) ^ 0x7FEF14C66749B8EEL));
        if (this.solid) {
            RenderHelp.prepare("quads");
            RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)obf2.getX(), (float)obf2.getY(), (float)obf2.getZ(), Float.intBitsToFloat(Float.floatToIntBits(4.6753635f) ^ 0x7F159C94), obf3, Float.intBitsToFloat(Float.floatToIntBits(7.750375f) ^ 0x7F780312), this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            RenderHelp.release();
        }
        if (this.outline) {
            RenderHelp.prepare("lines");
            RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)obf2.getX(), (float)obf2.getY(), (float)obf2.getZ(), Float.intBitsToFloat(Float.floatToIntBits(11.466899f) ^ 0x7EB7786B), obf3, Float.intBitsToFloat(Float.floatToIntBits(8.657973f) ^ 0x7E8A870F), this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a_out.get_value(1), "all");
            RenderHelp.release();
        }
    }
    
    public void cycle_rainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], (float)this.sat.get_value(1), (float)this.brightness.get_value(1));
        this.r.set_value(hsBtoRGB >> 16 & 0xFF);
        this.g.set_value(hsBtoRGB >> 8 & 0xFF);
        this.b.set_value(hsBtoRGB & 0xFF);
    }
}
