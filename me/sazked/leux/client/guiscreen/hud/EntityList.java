// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.projectile.EntityWitherSkull;
import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.stream.Collector;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import java.util.function.Predicate;
import java.util.Objects;
import java.util.Map;
import java.util.Collection;
import net.minecraft.entity.Entity;
import java.util.ArrayList;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class EntityList extends Pinnable
{
    public EntityList() {
        super("Entity List", "EntityList", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        int counter = 12;
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        final List<Entity> entity_list = new ArrayList<Entity>(this.mc.world.loadedEntityList);
        if (entity_list.size() <= 1) {
            return;
        }
        final Map<String, Integer> entity_counts = entity_list.stream().filter(Objects::nonNull).filter(e -> !(e instanceof EntityPlayer)).collect(Collectors.groupingBy((Function<? super Object, ? extends String>)EntityList::get_entity_name, (Collector<? super Object, ?, Integer>)Collectors.reducing((D)0, ent -> {
            if (ent instanceof EntityItem) {
                return Integer.valueOf(ent.getItem().getCount());
            }
            else {
                return Integer.valueOf(1);
            }
        }, Integer::sum)));
        for (final Map.Entry<String, Integer> entity : entity_counts.entrySet()) {
            final String e2 = entity.getKey() + " " + ChatFormatting.DARK_GRAY + "x" + entity.getValue();
            this.create_line(e2, this.docking(1, e2), 1 * counter, nl_r, nl_g, nl_b, nl_a);
            counter += 12;
        }
        this.set_width(this.get("aaaaaaaaaaaaaaa", "width") + 2);
        this.set_height(this.get("aaaaaaaaaaaaaaa", "height") * 5);
    }
    
    private static String get_entity_name(@Nonnull final Entity entity) {
        if (entity instanceof EntityItem) {
            return ((EntityItem)entity).getItem().getItem().getItemStackDisplayName(((EntityItem)entity).getItem());
        }
        if (entity instanceof EntityWitherSkull) {
            return "Wither skull";
        }
        if (entity instanceof EntityEnderCrystal) {
            return "End crystal";
        }
        if (entity instanceof EntityEnderPearl) {
            return "Thrown ender pearl";
        }
        if (entity instanceof EntityMinecart) {
            return "Minecart";
        }
        if (entity instanceof EntityItemFrame) {
            return "Item frame";
        }
        if (entity instanceof EntityEgg) {
            return "Thrown egg";
        }
        if (entity instanceof EntitySnowball) {
            return "Thrown snowball";
        }
        return entity.getName();
    }
}
