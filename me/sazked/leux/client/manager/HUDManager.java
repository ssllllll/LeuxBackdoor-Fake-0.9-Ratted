// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.manager;

import java.util.function.Function;
import java.util.Comparator;
import me.sazked.leux.client.guiscreen.hud.Notifications;
import me.sazked.leux.client.guiscreen.hud.EnemyInfo;
import me.sazked.leux.client.guiscreen.hud.PlayerModel;
import me.sazked.leux.client.guiscreen.hud.Direction;
import me.sazked.leux.client.guiscreen.hud.PlayerList;
import me.sazked.leux.client.guiscreen.hud.TPS;
import me.sazked.leux.client.guiscreen.hud.EntityList;
import me.sazked.leux.client.guiscreen.hud.Speedometer;
import me.sazked.leux.client.guiscreen.hud.EffectHud;
import me.sazked.leux.client.guiscreen.hud.Compass;
import me.sazked.leux.client.guiscreen.hud.PvpHud;
import me.sazked.leux.client.guiscreen.hud.ArmorDurabilityWarner;
import me.sazked.leux.client.guiscreen.hud.FriendList;
import me.sazked.leux.client.guiscreen.hud.SurroundBlocks;
import me.sazked.leux.client.guiscreen.hud.Ping;
import me.sazked.leux.client.guiscreen.hud.FPS;
import me.sazked.leux.client.guiscreen.hud.Logo;
import me.sazked.leux.client.guiscreen.hud.Time;
import me.sazked.leux.client.guiscreen.hud.GappleCount;
import me.sazked.leux.client.guiscreen.hud.EXPCount;
import me.sazked.leux.client.guiscreen.hud.CrystalCount;
import me.sazked.leux.client.guiscreen.hud.TotemCount;
import me.sazked.leux.client.guiscreen.hud.User;
import me.sazked.leux.client.guiscreen.hud.ArmorPreview;
import me.sazked.leux.client.guiscreen.hud.InventoryXCarryPreview;
import me.sazked.leux.client.guiscreen.hud.InventoryPreview;
import me.sazked.leux.client.guiscreen.hud.Coordinates;
import me.sazked.leux.client.guiscreen.hud.ModulesArrayList;
import me.sazked.leux.client.guiscreen.hud.Watermark;
import java.util.Iterator;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;
import java.util.ArrayList;

public class HUDManager
{
    public static ArrayList<Pinnable> array_hud;
    
    static {
        HUDManager.array_hud = new ArrayList<Pinnable>();
    }
    
    public Pinnable get_pinnable_with_tag(final String obf) {
        Pinnable obf2 = null;
        for (final Pinnable obf3 : this.get_array_huds()) {
            if (obf3.get_tag().equalsIgnoreCase(obf)) {
                obf2 = obf3;
            }
        }
        return obf2;
    }
    
    public HUDManager() {
        this.add_component_pinnable(new Watermark());
        this.add_component_pinnable(new ModulesArrayList());
        this.add_component_pinnable(new Coordinates());
        this.add_component_pinnable(new InventoryPreview());
        this.add_component_pinnable(new InventoryXCarryPreview());
        this.add_component_pinnable(new ArmorPreview());
        this.add_component_pinnable(new User());
        this.add_component_pinnable(new TotemCount());
        this.add_component_pinnable(new CrystalCount());
        this.add_component_pinnable(new EXPCount());
        this.add_component_pinnable(new GappleCount());
        this.add_component_pinnable(new Time());
        this.add_component_pinnable(new Logo());
        this.add_component_pinnable(new FPS());
        this.add_component_pinnable(new Ping());
        this.add_component_pinnable(new SurroundBlocks());
        this.add_component_pinnable(new FriendList());
        this.add_component_pinnable(new ArmorDurabilityWarner());
        this.add_component_pinnable(new PvpHud());
        this.add_component_pinnable(new Compass());
        this.add_component_pinnable(new EffectHud());
        this.add_component_pinnable(new Speedometer());
        this.add_component_pinnable(new EntityList());
        this.add_component_pinnable(new TPS());
        this.add_component_pinnable(new PlayerList());
        this.add_component_pinnable(new Direction());
        this.add_component_pinnable(new PlayerModel());
        this.add_component_pinnable(new EnemyInfo());
        this.add_component_pinnable(new Notifications());
        HUDManager.array_hud.sort(Comparator.comparing((Function<? super Pinnable, ? extends Comparable>)Pinnable::get_title));
    }
    
    public void render() {
        for (final Pinnable pinnable : this.get_array_huds()) {
            if (pinnable.is_active()) {
                pinnable.render();
            }
        }
    }
    
    public void add_component_pinnable(final Pinnable obf) {
        HUDManager.array_hud.add(obf);
    }
    
    public ArrayList<Pinnable> get_array_huds() {
        return HUDManager.array_hud;
    }
}
