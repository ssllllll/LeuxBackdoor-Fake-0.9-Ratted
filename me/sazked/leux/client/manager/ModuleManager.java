// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.manager;

import java.util.function.Function;
import java.util.Comparator;
import me.sazked.leux.client.modules.misc.MiddleClickPearl;
import me.sazked.leux.client.modules.misc.AutoWither;
import me.sazked.leux.client.modules.misc.AntiAFK;
import me.sazked.leux.client.modules.ui.RichPresence;
import me.sazked.leux.client.modules.misc.FakePlayer;
import me.sazked.leux.client.modules.misc.AutoRespawn;
import me.sazked.leux.client.modules.misc.FastUse;
import me.sazked.leux.client.modules.misc.AutoNomadHut;
import me.sazked.leux.client.modules.misc.AutoReplenish;
import me.sazked.leux.client.modules.misc.StopEXP;
import me.sazked.leux.client.modules.misc.MiddleClickGang;
import me.sazked.leux.client.modules.ui.Settings;
import me.sazked.leux.client.modules.render.Brightness;
import me.sazked.leux.client.modules.render.CityEsp;
import me.sazked.leux.client.modules.render.AlwaysNight;
import me.sazked.leux.client.modules.render.Capes;
import me.sazked.leux.client.modules.render.Chams;
import me.sazked.leux.client.modules.render.NoRender;
import me.sazked.leux.client.modules.render.SkyColour;
import me.sazked.leux.client.modules.render.Tracers;
import me.sazked.leux.client.modules.render.BreakHighlight;
import me.sazked.leux.client.modules.render.StorageESP;
import me.sazked.leux.client.modules.render.FuckedDetector;
import me.sazked.leux.client.modules.render.NameTags;
import me.sazked.leux.client.modules.render.VoidESP;
import me.sazked.leux.client.modules.render.ViewmodleChanger;
import me.sazked.leux.client.modules.render.Peek;
import me.sazked.leux.client.modules.render.HoleESP;
import me.sazked.leux.client.modules.render.Highlight;
import me.sazked.leux.client.modules.movement.LongJump;
import me.sazked.leux.client.modules.movement.Jesus;
import me.sazked.leux.client.modules.movement.Scaffold;
import me.sazked.leux.client.modules.movement.AutoWalk;
import me.sazked.leux.client.modules.movement.Yaw;
import me.sazked.leux.client.modules.movement.PacketFly;
import me.sazked.leux.client.modules.movement.ElytraFly;
import me.sazked.leux.client.modules.movement.Velocity;
import me.sazked.leux.client.modules.movement.InventoryMove;
import me.sazked.leux.client.modules.movement.NoSlowDown;
import me.sazked.leux.client.modules.movement.Anchor;
import me.sazked.leux.client.modules.movement.Freecam;
import me.sazked.leux.client.modules.movement.Sprint;
import me.sazked.leux.client.modules.movement.ReverseStep;
import me.sazked.leux.client.modules.movement.Step;
import me.sazked.leux.client.modules.movement.Strafe;
import me.sazked.leux.client.modules.exploit.Auto8B8TDupe;
import me.sazked.leux.client.modules.exploit.AutoSalDupe;
import me.sazked.leux.client.modules.exploit.NoForceLook;
import me.sazked.leux.client.modules.exploit.PacketXP;
import me.sazked.leux.client.modules.exploit.Timer;
import me.sazked.leux.client.modules.exploit.MultiTask;
import me.sazked.leux.client.modules.exploit.AutoQueueMain;
import me.sazked.leux.client.modules.exploit.SpeedMine;
import me.sazked.leux.client.modules.exploit.NoHandshake;
import me.sazked.leux.client.modules.exploit.BuildHeight;
import me.sazked.leux.client.modules.exploit.EntityMine;
import me.sazked.leux.client.modules.exploit.PacketMine;
import me.sazked.leux.client.modules.exploit.PortalGodMode;
import me.sazked.leux.client.modules.exploit.Burrow;
import me.sazked.leux.client.modules.exploit.NoSwing;
import me.sazked.leux.client.modules.exploit.XCarry;
import me.sazked.leux.client.modules.combat.Criticals;
import me.sazked.leux.client.modules.combat.AutoObiBreaker;
import me.sazked.leux.client.modules.combat.AutoTotem;
import me.sazked.leux.client.modules.combat.Offhand;
import me.sazked.leux.client.modules.combat.BedAura;
import me.sazked.leux.client.modules.combat.AutoWeb;
import me.sazked.leux.client.modules.combat.Webfill;
import me.sazked.leux.client.modules.combat.SelfTrap;
import me.sazked.leux.client.modules.exploit.NoFall;
import me.sazked.leux.client.modules.exploit.MountByPass;
import me.sazked.leux.client.modules.exploit.InstantBurrow;
import me.sazked.leux.client.modules.exploit.InstantBreak;
import me.sazked.leux.client.modules.exploit.GodModule;
import me.sazked.leux.client.modules.exploit.FakeCreative;
import me.sazked.leux.client.modules.exploit.EntityRide;
import me.sazked.leux.client.modules.render.BurrowESP;
import me.sazked.leux.client.modules.exploit.EffectsSide;
import me.sazked.leux.client.modules.exploit.CoordExploits;
import me.sazked.leux.client.modules.combat.TotemPopCounter;
import me.sazked.leux.client.modules.exploit.AutoFrameDupe;
import me.sazked.leux.client.modules.combat.AutoTrap;
import me.sazked.leux.client.modules.combat.HoleFill;
import me.sazked.leux.client.modules.combat.Surround;
import me.sazked.leux.client.modules.combat.KillAura;
import me.sazked.leux.client.modules.combat.AutoCrystalOld;
import me.sazked.leux.client.modules.combat.AutoCrystalNew;
import me.sazked.leux.client.modules.chat.AutoArmour;
import me.sazked.leux.client.modules.combat.Auto32k;
import me.sazked.leux.client.modules.chat.StashFinder;
import me.sazked.leux.client.modules.chat.FactSpammer;
import me.sazked.leux.client.modules.chat.EntitySearch;
import me.sazked.leux.client.modules.exploit.GlobalLocation;
import me.sazked.leux.client.modules.chat.PopAnnouncer;
import me.sazked.leux.client.modules.chat.BetterChat;
import me.sazked.leux.client.modules.chat.SexDupe;
import me.sazked.leux.client.modules.chat.Notifications;
import me.sazked.leux.client.modules.chat.FastSuicide;
import me.sazked.leux.client.modules.chat.ClearChat;
import me.sazked.leux.client.modules.chat.ChatSuffix;
import me.sazked.leux.client.modules.chat.ChatColors;
import me.sazked.leux.client.modules.chat.AutoRacist;
import me.sazked.leux.client.modules.chat.AutoEz;
import me.sazked.leux.client.modules.chat.AutoExcuse;
import me.sazked.leux.client.modules.chat.Announcer;
import me.sazked.leux.client.modules.ui.ClickHUD;
import me.sazked.leux.client.modules.ui.ClickGUI;
import me.sazked.leux.client.modules.combat.WebAura;
import me.sazked.leux.client.modules.ui.VanillaPayload;
import me.sazked.leux.client.modules.combat.BowAim;
import me.sazked.leux.client.modules.combat.AnvilAura;
import me.sazked.leux.client.modules.combat.AntiCrystal;
import me.sazked.leux.client.modules.combat.AntiCityBoss;
import me.sazked.leux.client.guiandfont.ClickGUIModule;
import me.sazked.leux.client.modules.Category;
import net.minecraft.client.renderer.Tessellator;
import me.sazked.leux.client.event.events.EventRender;
import me.sazked.turok.draw.RenderHelp;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import java.util.Iterator;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;
import me.sazked.leux.client.modules.Module;
import java.util.ArrayList;

public class ModuleManager
{
    public static ArrayList<Module> array_hacks;
    public static Minecraft mc;
    
    public Vec3d process(final Entity obf, final double obf, final double obf, final double obf) {
        return new Vec3d((obf.posX - obf.lastTickPosX) * obf, (obf.posY - obf.lastTickPosY) * obf, (obf.posZ - obf.lastTickPosZ) * obf);
    }
    
    public Vec3d get_interpolated_pos(final Entity obf, final double obf) {
        return new Vec3d(obf.lastTickPosX, obf.lastTickPosY, obf.lastTickPosZ).add(this.process(obf, obf, obf, obf));
    }
    
    public void update() {
        for (final Module module : this.get_array_hacks()) {
            if (module.is_active()) {
                module.update();
            }
        }
    }
    
    public void bind(final int n) {
        if (n == 0) {
            return;
        }
        for (final Module module : this.get_array_hacks()) {
            if (module.get_bind(0) == n) {
                module.toggle();
            }
        }
    }
    
    public void render(final RenderWorldLastEvent obf) {
        ModuleManager.mc.profiler.startSection("leux");
        ModuleManager.mc.profiler.startSection("setup");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(5.8221107f) ^ 0x7F3A4EBB));
        final Vec3d obf2 = this.get_interpolated_pos((Entity)ModuleManager.mc.player, obf.getPartialTicks());
        final EventRender obf3 = new EventRender(RenderHelp.INSTANCE, obf2);
        obf3.reset_translation();
        ModuleManager.mc.profiler.endSection();
        for (final Module obf4 : this.get_array_hacks()) {
            if (obf4.is_active()) {
                ModuleManager.mc.profiler.startSection(obf4.get_tag());
                obf4.render(obf3);
                ModuleManager.mc.profiler.endSection();
            }
        }
        ModuleManager.mc.profiler.startSection("release");
        GlStateManager.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(6.925806f) ^ 0x7F5DA034));
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        RenderHelp.release_gl();
        ModuleManager.mc.profiler.endSection();
        ModuleManager.mc.profiler.endSection();
    }
    
    public void render() {
        for (final Module module : this.get_array_hacks()) {
            if (module.is_active()) {
                module.render();
            }
        }
    }
    
    static {
        ModuleManager.array_hacks = new ArrayList<Module>();
        ModuleManager.mc = Minecraft.getMinecraft();
    }
    
    public ArrayList<Module> get_array_active_hacks() {
        final ArrayList<Module> obf = new ArrayList<Module>();
        for (final Module obf2 : this.get_array_hacks()) {
            if (obf2.is_active()) {
                obf.add(obf2);
            }
        }
        return obf;
    }
    
    public Module get_module_with_tag(final String obf) {
        Module obf2 = null;
        for (final Module obf3 : this.get_array_hacks()) {
            if (obf3.get_tag().equalsIgnoreCase(obf)) {
                obf2 = obf3;
            }
        }
        return obf2;
    }
    
    public ArrayList<Module> get_modules_with_category(final Category obf) {
        final ArrayList<Module> obf2 = new ArrayList<Module>();
        for (final Module obf3 : this.get_array_hacks()) {
            if (obf3.get_category().equals(obf)) {
                obf2.add(obf3);
            }
        }
        return obf2;
    }
    
    public void add_hack(final Module obf) {
        ModuleManager.array_hacks.add(obf);
    }
    
    public ArrayList<Module> get_array_hacks() {
        return ModuleManager.array_hacks;
    }
    
    public ModuleManager() {
        this.add_hack(new ClickGUIModule());
        this.add_hack(new AntiCityBoss());
        this.add_hack(new AntiCrystal());
        this.add_hack(new AnvilAura());
        this.add_hack(new BowAim());
        this.add_hack(new VanillaPayload());
        this.add_hack(new WebAura());
        this.add_hack(new ClickGUI());
        this.add_hack(new ClickHUD());
        this.add_hack(new Announcer());
        this.add_hack(new AutoExcuse());
        this.add_hack(new AutoEz());
        this.add_hack(new AutoRacist());
        this.add_hack(new ChatColors());
        this.add_hack(new ChatSuffix());
        this.add_hack(new ClearChat());
        this.add_hack(new FastSuicide());
        this.add_hack(new Notifications());
        this.add_hack(new SexDupe());
        this.add_hack(new BetterChat());
        this.add_hack(new PopAnnouncer());
        this.add_hack(new GlobalLocation());
        this.add_hack(new EntitySearch());
        this.add_hack(new FactSpammer());
        this.add_hack(new StashFinder());
        this.add_hack(new Auto32k());
        this.add_hack(new AutoArmour());
        this.add_hack(new AutoCrystalNew());
        this.add_hack(new AutoCrystalOld());
        this.add_hack(new KillAura());
        this.add_hack(new Surround());
        this.add_hack(new HoleFill());
        this.add_hack(new AutoTrap());
        this.add_hack(new AutoFrameDupe());
        this.add_hack(new TotemPopCounter());
        this.add_hack(new CoordExploits());
        this.add_hack(new EffectsSide());
        this.add_hack(new BurrowESP());
        this.add_hack(new EntityRide());
        this.add_hack(new FakeCreative());
        this.add_hack(new GlobalLocation());
        this.add_hack(new GodModule());
        this.add_hack(new InstantBreak());
        this.add_hack(new InstantBurrow());
        this.add_hack(new MountByPass());
        this.add_hack(new GlobalLocation());
        this.add_hack(new NoFall());
        this.add_hack(new SelfTrap());
        this.add_hack(new Webfill());
        this.add_hack(new AutoWeb());
        this.add_hack(new BedAura());
        this.add_hack(new Offhand());
        this.add_hack(new AutoTotem());
        this.add_hack(new AutoObiBreaker());
        this.add_hack(new Criticals());
        this.add_hack(new XCarry());
        this.add_hack(new NoSwing());
        this.add_hack(new Burrow());
        this.add_hack(new PortalGodMode());
        this.add_hack(new PacketMine());
        this.add_hack(new EntityMine());
        this.add_hack(new BuildHeight());
        this.add_hack(new NoHandshake());
        this.add_hack(new SpeedMine());
        this.add_hack(new AutoQueueMain());
        this.add_hack(new MultiTask());
        this.add_hack(new Timer());
        this.add_hack(new PacketXP());
        this.add_hack(new NoForceLook());
        this.add_hack(new AutoSalDupe());
        this.add_hack(new Auto8B8TDupe());
        this.add_hack(new Strafe());
        this.add_hack(new Step());
        this.add_hack(new ReverseStep());
        this.add_hack(new Sprint());
        this.add_hack(new Freecam());
        this.add_hack(new Anchor());
        this.add_hack(new NoSlowDown());
        this.add_hack(new InventoryMove());
        this.add_hack(new Velocity());
        this.add_hack(new ElytraFly());
        this.add_hack(new PacketFly());
        this.add_hack(new Yaw());
        this.add_hack(new AutoWalk());
        this.add_hack(new NoFall());
        this.add_hack(new Scaffold());
        this.add_hack(new Jesus());
        this.add_hack(new LongJump());
        this.add_hack(new Highlight());
        this.add_hack(new HoleESP());
        this.add_hack(new Peek());
        this.add_hack(new ViewmodleChanger());
        this.add_hack(new VoidESP());
        this.add_hack(new NameTags());
        this.add_hack(new FuckedDetector());
        this.add_hack(new StorageESP());
        this.add_hack(new BreakHighlight());
        this.add_hack(new Tracers());
        this.add_hack(new SkyColour());
        this.add_hack(new NoRender());
        this.add_hack(new Chams());
        this.add_hack(new Capes());
        this.add_hack(new AlwaysNight());
        this.add_hack(new CityEsp());
        this.add_hack(new Brightness());
        this.add_hack(new Settings());
        this.add_hack(new MiddleClickGang());
        this.add_hack(new StopEXP());
        this.add_hack(new AutoReplenish());
        this.add_hack(new AutoNomadHut());
        this.add_hack(new FastUse());
        this.add_hack(new AutoRespawn());
        this.add_hack(new FakeCreative());
        this.add_hack(new FakePlayer());
        this.add_hack(new RichPresence());
        this.add_hack(new EffectsSide());
        this.add_hack(new AntiAFK());
        this.add_hack(new AutoWither());
        this.add_hack(new MiddleClickPearl());
        ModuleManager.array_hacks.sort(Comparator.comparing((Function<? super Module, ? extends Comparable>)Module::get_name));
    }
}
