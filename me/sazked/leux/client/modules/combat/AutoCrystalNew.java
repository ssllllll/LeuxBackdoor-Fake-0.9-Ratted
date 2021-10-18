// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import me.sazked.leux.client.util.RenderUtil;
import me.sazked.leux.client.event.events.EventRender;
import me.sazked.leux.Leux;
import net.minecraft.item.ItemPickaxe;
import me.sazked.turok.draw.RenderHelp;
import me.sazked.leux.client.util.EntityUtil;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.network.Packet;
import java.util.Objects;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.network.play.server.SPacketSoundEffect;
import me.sazked.leux.client.util.PosManager;
import net.minecraft.util.math.Vec3d;
import java.awt.Color;
import net.minecraft.util.EnumHand;
import java.util.function.Predicate;
import java.util.concurrent.CopyOnWriteArrayList;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.modules.chat.AutoEz;
import me.sazked.leux.client.util.BlockUtil;
import me.sazked.leux.client.util.FriendUtil;
import me.sazked.leux.client.util.CrystalUtil;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.client.util.RotationUtil;
import me.sazked.leux.client.util.MathUtil;
import net.minecraft.entity.Entity;
import java.util.Iterator;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.event.events.EventEntityRemoved;
import net.minecraft.entity.item.EntityEnderCrystal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import me.sazked.leux.client.event.events.EventMotionUpdate;
import net.minecraft.entity.player.EntityPlayer;
import me.sazked.leux.client.util.Timer;
import net.minecraft.util.math.BlockPos;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoCrystalNew extends Module
{
    public int place_timeout;
    public float pitch;
    public Setting auto_switch;
    public Setting place_delay;
    public Setting g;
    public Setting render_mode;
    public Setting b;
    public Setting place_range;
    public Setting place_crystal;
    public boolean solid;
    public Setting raytrace;
    public int break_delay_counter;
    public int place_delay_counter;
    @EventHandler
    public Listener<EventPacket.ReceivePacket> receive_listener;
    public BlockPos render_block_old;
    public Setting swing;
    public boolean did_anything;
    public Setting stop_while_mining;
    public Setting break_trys;
    public Setting min_player_break;
    public Timer remove_visual_timer;
    public Setting a;
    public Setting top_block;
    public Setting break_delay;
    public Setting old_render;
    public EntityPlayer autoez_target;
    public Setting future_render;
    public Setting a_out;
    public Setting anti_weakness;
    public boolean outline;
    public Setting antiStuckTries;
    public Setting fuck_armor_mode;
    @EventHandler
    public Listener<EventMotionUpdate> on_movement;
    public Setting break_crystal;
    public Setting r;
    public Setting anti_stuck;
    public boolean already_attacking;
    public String detail_name;
    public List<BlockPos> placePosList;
    public Setting jumpy_mode;
    public BlockPos render_block_init;
    public Setting client_side;
    public Setting place_range_wall;
    public Setting render_damage;
    public Setting luscius_mode;
    public Setting enemyRange;
    public Setting rainbow_mode;
    public Setting break_range;
    public double render_damage_value;
    public Setting max_self_damage;
    public Setting faceplace_check;
    public static EntityPlayer ca_target;
    @EventHandler
    public Listener<EventPacket.SendPacket> send_listener;
    public Setting luscius_mode_damage;
    public Setting height;
    public ConcurrentHashMap<EntityEnderCrystal, Integer> attacked_crystals;
    public Setting endcrystal;
    public Setting brightness;
    public Setting fuck_armor_mode_precent;
    public boolean is_rotating;
    public Setting break_range_wall;
    @EventHandler
    public Listener<EventEntityRemoved> on_entity_removed;
    public Setting min_player_place;
    public Setting sat;
    public Setting anti_suicide;
    public Setting rotate_mode;
    public float yaw;
    public int detail_hp;
    public int break_timeout;
    
    public boolean get_armor_fucker(final EntityPlayer entityPlayer) {
        for (final ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
            if (itemStack == null || itemStack.getItem() == Items.AIR) {
                return true;
            }
            final float n = (itemStack.getMaxDamage() - itemStack.getItemDamage()) / (float)itemStack.getMaxDamage() * 100.0f;
            if (!this.fuck_armor_mode.get_value(true)) {
                continue;
            }
            if (this.fuck_armor_mode_precent.get_value(1) < n) {
                continue;
            }
            return true;
        }
        return false;
    }
    
    public void rotate_to(final Entity entity) {
        final float[] calcAngle = MathUtil.calcAngle(AutoCrystalNew.mc.player.getPositionEyes(AutoCrystalNew.mc.getRenderPartialTicks()), entity.getPositionVector());
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
    
    static {
        AutoCrystalNew.ca_target = null;
    }
    
    public void lambda$new$1(final EventPacket.SendPacket sendPacket) {
        if (sendPacket.get_packet() instanceof CPacketPlayer && this.is_rotating && this.rotate_mode.in("Old")) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)sendPacket.get_packet();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            this.is_rotating = false;
        }
        if (sendPacket.get_packet() instanceof CPacketPlayerTryUseItemOnBlock && this.is_rotating && this.rotate_mode.in("Old")) {
            final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)sendPacket.get_packet();
            cPacketPlayerTryUseItemOnBlock.facingX = (float)this.render_block_init.getX();
            cPacketPlayerTryUseItemOnBlock.facingY = (float)this.render_block_init.getY();
            cPacketPlayerTryUseItemOnBlock.facingZ = (float)this.render_block_init.getZ();
            this.is_rotating = false;
        }
    }
    
    public BlockPos get_best_block() {
        double obf = Double.longBitsToDouble(Double.doubleToLongBits(1.4522997913662862E308) ^ 0x7FE9DA0F32B390F2L);
        final double obf2 = this.max_self_damage.get_value(1);
        BlockPos obf3 = null;
        final List<BlockPos> obf4 = (List<BlockPos>)CrystalUtil.possiblePlacePositions((float)this.place_range.get_value(1), this.endcrystal.get_value(true));
        for (final EntityPlayer obf5 : AutoCrystalNew.mc.world.playerEntities) {
            if (FriendUtil.isFriend(obf5.getName())) {
                continue;
            }
            AutoCrystalNew.ca_target = obf5;
            for (final BlockPos obf6 : obf4) {
                if (obf5 != AutoCrystalNew.mc.player) {
                    if (obf5.getDistance((Entity)AutoCrystalNew.mc.player) >= this.enemyRange.get_value(1) || !BlockUtil.rayTracePlaceCheck(obf6, this.raytrace.get_value(true)) || (!BlockUtil.canSeeBlock(obf6) && AutoCrystalNew.mc.player.getDistance((double)obf6.getX(), (double)obf6.getY(), (double)obf6.getZ()) > this.place_range_wall.get_value(1)) || obf5.isDead) {
                        continue;
                    }
                    if (obf5.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(2.6408034E38f) ^ 0x7F46AC06)) {
                        continue;
                    }
                    final boolean obf7 = this.faceplace_check.get_value(true) && AutoCrystalNew.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD;
                    final double obf8 = ((obf5.getHealth() < this.luscius_mode_damage.get_value(1) && this.luscius_mode.get_value(true) && !obf7) || (this.get_armor_fucker(obf5) && !obf7)) ? Double.longBitsToDouble(Double.doubleToLongBits(0.9247695045883392) ^ 0x7FED97B6375170F5L) : this.min_player_place.get_value(1);
                    final double obf9 = CrystalUtil.calculateDamage(obf6.getX() + Double.longBitsToDouble(Double.doubleToLongBits(2.26394017584873) ^ 0x7FE21C8CAABAF75EL), obf6.getY() + Double.longBitsToDouble(Double.doubleToLongBits(5.561244454167755) ^ 0x7FE63EB6DDBED8F7L), obf6.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(2.412888024893934) ^ 0x7FE34D983C9EA2C7L), (Entity)obf5);
                    final double obf10;
                    if (obf9 < obf8 || (obf10 = CrystalUtil.calculateDamage(obf6.getX() + Double.longBitsToDouble(Double.doubleToLongBits(3.485937335141053) ^ 0x7FEBE3331D12B091L), obf6.getY() + Double.longBitsToDouble(Double.doubleToLongBits(9.932649484054002) ^ 0x7FD3DD843BB14953L), obf6.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(29.469648542132877) ^ 0x7FDD783AE3091325L), (Entity)AutoCrystalNew.mc.player)) > obf2) {
                        continue;
                    }
                    if (this.anti_suicide.get_value(true) && AutoCrystalNew.mc.player.getHealth() + AutoCrystalNew.mc.player.getAbsorptionAmount() - obf10 <= Double.longBitsToDouble(Double.doubleToLongBits(3.181600106946833) ^ 0x7FE973EAC1C24B44L)) {
                        continue;
                    }
                    if (obf9 <= obf) {
                        continue;
                    }
                    obf = obf9;
                    obf3 = obf6;
                    this.autoez_target = obf5;
                }
            }
        }
        obf4.clear();
        this.render_damage_value = obf;
        return this.render_block_init = obf3;
    }
    
    @Override
    public void disable() {
        this.render_block_init = null;
        this.autoez_target = null;
    }
    
    @Override
    public void update() {
        Label_0081: {
            if (!this.rotate_mode.in("Off")) {
                if (!this.rotate_mode.in("Old")) {
                    break Label_0081;
                }
            }
            this.do_ca();
        }
        if (AutoCrystalNew.mc.player.isDead || AutoCrystalNew.mc.player.getHealth() <= 0.0f) {
            AutoCrystalNew.ca_target = null;
        }
    }
    
    public void do_ca() {
        this.did_anything = false;
        if (AutoCrystalNew.mc.player != null) {
            if (AutoCrystalNew.mc.world != null) {
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
                if (this.break_crystal.get_value(true) && this.break_delay_counter > this.break_timeout) {
                    this.break_crystal();
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
                this.render_block_old = this.render_block_init;
                ++this.break_delay_counter;
                ++this.place_delay_counter;
            }
        }
    }
    
    public void add_attacked_crystal(final EntityEnderCrystal entityEnderCrystal) {
        if (this.attacked_crystals.containsKey(entityEnderCrystal)) {
            this.attacked_crystals.put(entityEnderCrystal, this.attacked_crystals.get(entityEnderCrystal) + 1);
        }
        else {
            this.attacked_crystals.put(entityEnderCrystal, 1);
        }
    }
    
    public AutoCrystalNew() {
        super(Category.combat);
        this.place_crystal = this.create("Place", "CaPlace", true);
        this.place_delay = this.create("Place Delay", "CaPlaceDelay", 1, 0, 10);
        this.place_range = this.create("Place Range", "CaPlaceRange", Double.longBitsToDouble(Double.doubleToLongBits(0.3934892724475307) ^ 0x7FCDE221611F4B6BL), Double.longBitsToDouble(Double.doubleToLongBits(6.374282057575131) ^ 0x7FE97F43CBB30F22L), Double.longBitsToDouble(Double.doubleToLongBits(1.1033352559633494) ^ 0x7FE9A742DE8E2E97L));
        this.place_range_wall = this.create("Place Wall Range", "CaPlaceWall", Double.longBitsToDouble(Double.doubleToLongBits(1.8349006264780905) ^ 0x7FED5BC0C262220AL), Double.longBitsToDouble(Double.doubleToLongBits(6.584372892237128E307) ^ 0x7FD770EEEAF165BBL), Double.longBitsToDouble(Double.doubleToLongBits(0.24356971032316463) ^ 0x7FD72D4AD2112CFFL));
        this.break_crystal = this.create("Break", "CaBreak", true);
        this.break_delay = this.create("Break Delay", "CaBreakDelay", 1, 0, 10);
        this.break_range = this.create("Break Range", "CaBreakRange", Double.longBitsToDouble(Double.doubleToLongBits(1.74287186176609) ^ 0x7FEF2E015AF676BEL), Double.longBitsToDouble(Double.doubleToLongBits(4.808256503786877) ^ 0x7FE33BA797CA2CFEL), Double.longBitsToDouble(Double.doubleToLongBits(1.994187138973294) ^ 0x7FE7E830C5FFE7DAL));
        this.break_range_wall = this.create("Break Wall Range", "CaBreakWall", Double.longBitsToDouble(Double.doubleToLongBits(1.1220816900061887) ^ 0x7FE1F40BEE204598L), Double.longBitsToDouble(Double.doubleToLongBits(26.226265692232868) ^ 0x7FCA39EC8C6458D3L), Double.longBitsToDouble(Double.doubleToLongBits(1.1903901580472043) ^ 0x7FEB0BD68CE4B02FL));
        this.break_trys = this.create("Break Attempts", "CaBreakAttempts", 1, 1, 6);
        this.anti_weakness = this.create("Anti-Weakness", "CaAntiWeakness", true);
        this.enemyRange = this.create("Enemy Range", "CaEnemyRange", Double.longBitsToDouble(Double.doubleToLongBits(1.9257419907126307) ^ 0x7FE2CFD6D56A50A1L), Double.longBitsToDouble(Double.doubleToLongBits(0.2364563726805841) ^ 0x7FDA4433D1CC04CDL), Double.longBitsToDouble(Double.doubleToLongBits(1.9462175383908844) ^ 0x7FD123B50064A5C7L));
        this.min_player_place = this.create("Min Enemy Place", "CaMinEnemyPlace", 6, 0, 20);
        this.min_player_break = this.create("Min Enemy Break", "CaMinEnemyBreak", 6, 0, 20);
        this.max_self_damage = this.create("Max Self Damage", "CaMaxSelfDamage", 8, 0, 20);
        this.rotate_mode = this.create("Rotate", "CaRotateMode", "Off", this.combobox("Off", "Old", "Const", "Good"));
        this.raytrace = this.create("Raytrace", "CaRaytrace", false);
        this.auto_switch = this.create("Auto Switch", "CaAutoSwitch", false);
        this.anti_suicide = this.create("Anti Suicide", "CaAntiSuicide", true);
        this.client_side = this.create("Client Side", "CaClientSide", false);
        this.jumpy_mode = this.create("Jumpy Mode", "CaJumpyMode", false);
        this.anti_stuck = this.create("Anti Stuck", "CaAntiStuck", false);
        this.antiStuckTries = this.create("Anti Stuck Tries", "CaAntiStuckTries", 5, 1, 15);
        this.endcrystal = this.create("1.13 Mode", "CaThirteen", false);
        this.luscius_mode = this.create("Luscius Mode", "CaLusciusMode", true);
        this.luscius_mode_damage = this.create("T Health", "CaLusciusModeHealth", 8, 0, 36);
        this.fuck_armor_mode = this.create("Armor Destroy", "CaArmorDestory", true);
        this.fuck_armor_mode_precent = this.create("Armor %", "CaArmorPercent", 25, 0, 100);
        this.stop_while_mining = this.create("Stop While Mining", "CaStopWhileMining", false);
        this.faceplace_check = this.create("No Sword FP", "CaJumpyFaceMode", false);
        this.swing = this.create("Swing", "CaSwing", "Offhand", this.combobox("Mainhand", "Offhand", "Both", "None"));
        this.render_mode = this.create("Render", "CaRenderMode", "Pretty", this.combobox("Pretty", "Solid", "Outline", "None"));
        this.old_render = this.create("Old Render", "CaOldRender", false);
        this.future_render = this.create("Future Render", "CaFutureRender", false);
        this.top_block = this.create("Top Block", "CaTopBlock", false);
        this.r = this.create("R", "CaR", 100, 0, 255);
        this.g = this.create("G", "CaG", 100, 0, 255);
        this.b = this.create("B", "CaB", 255, 0, 255);
        this.a = this.create("A", "CaA", 100, 0, 255);
        this.a_out = this.create("Outline A", "CaOutlineA", 255, 0, 255);
        this.rainbow_mode = this.create("Rainbow", "CaRainbow", false);
        this.sat = this.create("Satiation", "CaSatiation", Double.longBitsToDouble(Double.doubleToLongBits(15.450337773226666) ^ 0x7FC77F0B35A9CB6BL), Double.longBitsToDouble(Double.doubleToLongBits(8.141020586696416E307) ^ 0x7FDCFBA5952FEA39L), Double.longBitsToDouble(Double.doubleToLongBits(32.08376475032491) ^ 0x7FB00AB8CDA799FFL));
        this.brightness = this.create("Brightness", "CaBrightness", Double.longBitsToDouble(Double.doubleToLongBits(31.061246064420683) ^ 0x7FD696344BEA2B95L), Double.longBitsToDouble(Double.doubleToLongBits(1.2250328960279647E308) ^ 0x7FE5CE6A4AAA99F1L), Double.longBitsToDouble(Double.doubleToLongBits(21.108796114094527) ^ 0x7FC51BDA0FE7F7C7L));
        this.height = this.create("Height", "CaHeight", Double.longBitsToDouble(Double.doubleToLongBits(9.534523842523518) ^ 0x7FD311AD1BED243BL), Double.longBitsToDouble(Double.doubleToLongBits(5.005458272184974E307) ^ 0x7FD1D1ED065C0421L), Double.longBitsToDouble(Double.doubleToLongBits(7.138940984533125) ^ 0x7FEC8E468BA29344L));
        this.render_damage = this.create("Render Damage", "RenderDamage", true);
        this.attacked_crystals = new ConcurrentHashMap<EntityEnderCrystal, Integer>();
        this.placePosList = new CopyOnWriteArrayList<BlockPos>();
        this.remove_visual_timer = new Timer();
        this.autoez_target = null;
        this.detail_name = null;
        this.detail_hp = 0;
        this.already_attacking = false;
        this.on_entity_removed = new Listener<EventEntityRemoved>(this::lambda$new$0, (Predicate<EventEntityRemoved>[])new Predicate[0]);
        this.send_listener = new Listener<EventPacket.SendPacket>(this::lambda$new$1, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.on_movement = new Listener<EventMotionUpdate>(this::lambda$new$2, (Predicate<EventMotionUpdate>[])new Predicate[0]);
        this.receive_listener = new Listener<EventPacket.ReceivePacket>(this::lambda$new$3, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.name = "AutoCrystal New";
        this.tag = "AutoCrystalNew";
        this.description = "kills people (if ur good)";
    }
    
    @Override
    public String array_detail() {
        return (this.detail_name != null) ? (this.detail_name + " | " + this.detail_hp) : "None";
    }
    
    public void place_crystal() {
        final BlockPos obf = this.get_best_block();
        if (obf == null) {
            return;
        }
        this.place_delay_counter = 0;
        this.already_attacking = false;
        boolean obf2 = false;
        if (AutoCrystalNew.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            if (AutoCrystalNew.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && this.auto_switch.get_value(true)) {
                if (this.find_crystals_hotbar() == -1) {
                    return;
                }
                AutoCrystalNew.mc.player.inventory.currentItem = this.find_crystals_hotbar();
                return;
            }
        }
        else {
            obf2 = true;
        }
        this.did_anything = true;
        this.rotate_to_pos(obf);
        BlockUtil.placeCrystalOnBlock(obf, obf2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
        this.placePosList.add(obf);
    }
    
    public void cycle_rainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], (float)this.sat.get_value(1), (float)this.brightness.get_value(1));
        this.r.set_value(hsBtoRGB >> 16 & 0xFF);
        this.g.set_value(hsBtoRGB >> 8 & 0xFF);
        this.b.set_value(hsBtoRGB & 0xFF);
    }
    
    public void rotate_to_pos(final BlockPos obf) {
        final float[] obf2 = this.rotate_mode.in("Const") ? MathUtil.calcAngle(AutoCrystalNew.mc.player.getPositionEyes(AutoCrystalNew.mc.getRenderPartialTicks()), new Vec3d((double)(obf.getX() + Float.intBitsToFloat(Float.floatToIntBits(3.3117502f) ^ 0x7F53F3B7)), (double)(obf.getY() + Float.intBitsToFloat(Float.floatToIntBits(2.4612398f) ^ 0x7F1D84F4)), (double)(obf.getZ() + Float.intBitsToFloat(Float.floatToIntBits(2.6874847f) ^ 0x7F2BFFC0)))) : MathUtil.calcAngle(AutoCrystalNew.mc.player.getPositionEyes(AutoCrystalNew.mc.getRenderPartialTicks()), new Vec3d((double)(obf.getX() + Float.intBitsToFloat(Float.floatToIntBits(3.0323696f) ^ 0x7F421258)), (double)(obf.getY() - Float.intBitsToFloat(Float.floatToIntBits(2.9764407f) ^ 0x7F3E7E01)), (double)(obf.getZ() + Float.intBitsToFloat(Float.floatToIntBits(28.099588f) ^ 0x7EE0CBF5))));
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
    
    public void lambda$new$0(final EventEntityRemoved eventEntityRemoved) {
        if (eventEntityRemoved.get_entity() instanceof EntityEnderCrystal) {
            this.attacked_crystals.remove(eventEntityRemoved.get_entity());
        }
    }
    
    public void lambda$new$2(final EventMotionUpdate eventMotionUpdate) {
        if (eventMotionUpdate.stage == 0 && (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))) {
            PosManager.updatePosition();
            RotationUtil.updateRotations();
            this.do_ca();
        }
        if (eventMotionUpdate.stage == 1) {
            if (this.rotate_mode.in("Good") || this.rotate_mode.in("Const")) {
                PosManager.restorePosition();
                RotationUtil.restoreRotations();
            }
        }
    }
    
    @Override
    public void enable() {
        this.place_timeout = this.place_delay.get_value(1);
        this.break_timeout = this.break_delay.get_value(1);
        this.is_rotating = false;
        this.autoez_target = null;
        this.remove_visual_timer.reset();
        this.detail_name = null;
        this.detail_hp = 20;
    }
    
    public EntityEnderCrystal get_best_crystal() {
        double n = 0.0;
        final double n2 = this.max_self_damage.get_value(1);
        double distanceSq = 0.0;
        EntityEnderCrystal entityEnderCrystal = null;
        for (final Entity entity : AutoCrystalNew.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) {
                continue;
            }
            final EntityEnderCrystal entityEnderCrystal2 = (EntityEnderCrystal)entity;
            if (AutoCrystalNew.mc.player.getDistance((Entity)entityEnderCrystal2) > (AutoCrystalNew.mc.player.canEntityBeSeen((Entity)entityEnderCrystal2) ? this.break_range.get_value(1) : this.break_range_wall.get_value(1)) || (!AutoCrystalNew.mc.player.canEntityBeSeen((Entity)entityEnderCrystal2) && this.raytrace.get_value(true))) {
                continue;
            }
            if (this.attacked_crystals.containsKey(entityEnderCrystal2)) {
                if (this.attacked_crystals.get(entityEnderCrystal2) > this.antiStuckTries.get_value(1)) {
                    if (this.anti_stuck.get_value(true)) {
                        continue;
                    }
                }
            }
            for (final EntityPlayer obf : AutoCrystalNew.mc.world.playerEntities) {
                if (obf != AutoCrystalNew.mc.player && !FriendUtil.isFriend(obf.getName()) && obf.getDistance((Entity)AutoCrystalNew.mc.player) < this.enemyRange.get_value(1) && !obf.isDead) {
                    if (obf.getHealth() <= 0.0f) {
                        continue;
                    }
                    final boolean b = this.faceplace_check.get_value(true) && AutoCrystalNew.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD;
                    final double n3 = ((obf.getHealth() < this.luscius_mode_damage.get_value(1) && this.luscius_mode.get_value(true) && !b) || (this.get_armor_fucker(obf) && !b)) ? 2.0 : this.min_player_break.get_value(1);
                    final double n4 = CrystalUtil.calculateDamage(entityEnderCrystal2, (Entity)obf);
                    final double n5;
                    if (n4 < n3 || (n5 = CrystalUtil.calculateDamage(entityEnderCrystal2, (Entity)AutoCrystalNew.mc.player)) > n2 || (this.anti_suicide.get_value(true) && AutoCrystalNew.mc.player.getHealth() + AutoCrystalNew.mc.player.getAbsorptionAmount() - n5 <= 0.5) || n4 <= n) {
                        continue;
                    }
                    if (this.jumpy_mode.get_value(true)) {
                        continue;
                    }
                    n = n4;
                    entityEnderCrystal = entityEnderCrystal2;
                }
            }
            if (!this.jumpy_mode.get_value(true)) {
                continue;
            }
            if (AutoCrystalNew.mc.player.getDistanceSq((Entity)entityEnderCrystal2) <= distanceSq) {
                continue;
            }
            distanceSq = AutoCrystalNew.mc.player.getDistanceSq((Entity)entityEnderCrystal2);
            entityEnderCrystal = entityEnderCrystal2;
        }
        return entityEnderCrystal;
    }
    
    public void lambda$new$3(final EventPacket.ReceivePacket obf) {
        final SPacketSoundEffect obf2;
        if (obf.get_packet() instanceof SPacketSoundEffect && (obf2 = (SPacketSoundEffect)obf.get_packet()).getCategory() == SoundCategory.BLOCKS && obf2.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
            for (final Entity obf3 : AutoCrystalNew.mc.world.loadedEntityList) {
                if (obf3 instanceof EntityEnderCrystal) {
                    if (obf3.getDistance(obf2.getX(), obf2.getY(), obf2.getZ()) > Double.longBitsToDouble(Double.doubleToLongBits(0.010247334315672594) ^ 0x7F9CFC8DEE0E51FFL)) {
                        continue;
                    }
                    AutoCrystalNew.mc.world.removeEntityFromWorld(obf3.getEntityId());
                }
            }
        }
        final SPacketSpawnObject obf4;
        if (obf.get_packet() instanceof SPacketSpawnObject && (obf4 = (SPacketSpawnObject)obf.get_packet()).getType() == 51 && this.placePosList.contains(new BlockPos(obf4.getX(), obf4.getY(), obf4.getZ()).down())) {
            final CPacketUseEntity obf5 = new CPacketUseEntity();
            obf5.action = CPacketUseEntity.Action.ATTACK;
            obf5.entityId = obf4.getEntityID();
            Objects.requireNonNull(AutoCrystalNew.mc.getConnection()).sendPacket((Packet)obf5);
        }
    }
    
    public void break_crystal() {
        final EntityEnderCrystal obf = this.get_best_crystal();
        if (obf == null) {
            return;
        }
        if (this.anti_weakness.get_value(true) && AutoCrystalNew.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
            boolean obf2 = true;
            if (AutoCrystalNew.mc.player.isPotionActive(MobEffects.STRENGTH) && Objects.requireNonNull(AutoCrystalNew.mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                obf2 = false;
            }
            if (obf2) {
                if (!this.already_attacking) {
                    this.already_attacking = true;
                }
                int obf3 = -1;
                for (int obf4 = 0; obf4 < 9; ++obf4) {
                    final ItemStack obf5 = AutoCrystalNew.mc.player.inventory.getStackInSlot(obf4);
                    if (obf5.getItem() instanceof ItemSword || obf5.getItem() instanceof ItemTool) {
                        obf3 = obf4;
                        AutoCrystalNew.mc.playerController.updateController();
                        break;
                    }
                }
                if (obf3 != -1) {
                    AutoCrystalNew.mc.player.inventory.currentItem = obf3;
                }
            }
        }
        this.did_anything = true;
        this.rotate_to((Entity)obf);
        for (int obf6 = 0; obf6 < this.break_trys.get_value(1); ++obf6) {
            EntityUtil.attackEntity((Entity)obf, true, this.swing);
        }
        this.add_attacked_crystal(obf);
        if (this.client_side.get_value(true)) {
            AutoCrystalNew.mc.world.removeEntityFromWorld(obf.getEntityId());
        }
        this.break_delay_counter = 0;
    }
    
    public static EntityPlayer get_target() {
        return AutoCrystalNew.ca_target;
    }
    
    public void render_block(final BlockPos blockPos) {
        final BlockPos blockPos2 = this.top_block.get_value(true) ? blockPos.up() : blockPos;
        final float n = (float)this.height.get_value(1.0);
        if (this.solid) {
            RenderHelp.prepare("quads");
            RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)blockPos2.getX(), (float)blockPos2.getY(), (float)blockPos2.getZ(), 1.0f, n, 1.0f, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            RenderHelp.release();
        }
        if (this.outline) {
            RenderHelp.prepare("lines");
            RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)blockPos2.getX(), (float)blockPos2.getY(), (float)blockPos2.getZ(), 1.0f, n, 1.0f, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a_out.get_value(1), "all");
            RenderHelp.release();
        }
    }
    
    public boolean check_pause() {
        if (this.find_crystals_hotbar() == -1 && AutoCrystalNew.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            return true;
        }
        if (this.stop_while_mining.get_value(true)) {
            if (AutoCrystalNew.mc.gameSettings.keyBindAttack.isKeyDown()) {
                if (AutoCrystalNew.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                    if (this.old_render.get_value(true)) {
                        this.render_block_init = null;
                    }
                    return true;
                }
            }
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
    
    public int find_crystals_hotbar() {
        for (int i = 0; i < 9; ++i) {
            if (AutoCrystalNew.mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
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
        if (this.future_render.get_value(true)) {
            if (this.render_block_old != null) {
                this.render_block(this.render_block_old);
            }
        }
        if (this.render_damage.get_value(true)) {
            RenderUtil.drawText(this.render_block_init, ((Math.floor(this.render_damage_value) == this.render_damage_value) ? Integer.valueOf((int)this.render_damage_value) : String.format("%.1f", this.render_damage_value)) + "");
        }
    }
}
