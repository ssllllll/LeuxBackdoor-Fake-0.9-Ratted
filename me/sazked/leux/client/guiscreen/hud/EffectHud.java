// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.client.resources.I18n;
import java.util.Iterator;
import java.util.List;
import net.minecraft.init.MobEffects;
import java.util.Comparator;
import net.minecraft.potion.Potion;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Collection;
import net.minecraft.potion.PotionEffect;
import java.util.ArrayList;
import me.sazked.leux.Leux;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class EffectHud extends Pinnable
{
    public EffectHud() {
        super("Effect Hud", "effecthud", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        int counter = 12;
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        final List<PotionEffect> effects = new ArrayList<PotionEffect>(this.mc.player.getActivePotionEffects());
        final String first_effect;
        final String second_effect;
        final float dif;
        final Comparator<PotionEffect> comparator = (first, second) -> {
            first_effect = get_friendly_potion_name(first) + " " + ChatFormatting.GRAY + Potion.getPotionDurationString(first, 1.0f);
            second_effect = get_friendly_potion_name(second) + " " + ChatFormatting.GRAY + Potion.getPotionDurationString(second, 1.0f);
            dif = (float)(this.mc.fontRenderer.getStringWidth(second_effect) - this.mc.fontRenderer.getStringWidth(first_effect));
            return (dif != 0.0f) ? ((int)dif) : second_effect.compareTo(first_effect);
        };
        effects.sort(comparator);
        for (final PotionEffect effect : effects) {
            if (effect.getPotion() == MobEffects.STRENGTH) {
                final String e = ChatFormatting.DARK_RED + get_friendly_potion_name(effect) + " " + ChatFormatting.RESET + Potion.getPotionDurationString(effect, 1.0f);
                this.create_line(e, this.docking(1, e), counter, nl_r, nl_g, nl_b, nl_a);
                counter += 12;
            }
            else if (effect.getPotion() == MobEffects.SPEED) {
                final String e = ChatFormatting.BLUE + get_friendly_potion_name(effect) + " " + ChatFormatting.RESET + Potion.getPotionDurationString(effect, 1.0f);
                this.create_line(e, this.docking(1, e), counter, nl_r, nl_g, nl_b, nl_a);
                counter += 12;
            }
            else if (effect.getPotion() == MobEffects.WEAKNESS) {
                final String e = ChatFormatting.GRAY + get_friendly_potion_name(effect) + " " + ChatFormatting.RESET + Potion.getPotionDurationString(effect, 1.0f);
                this.create_line(e, this.docking(1, e), counter, nl_r, nl_g, nl_b, nl_a);
                counter += 12;
            }
            else if (effect.getPotion() == MobEffects.JUMP_BOOST) {
                final String e = ChatFormatting.GREEN + get_friendly_potion_name(effect) + " " + ChatFormatting.RESET + Potion.getPotionDurationString(effect, 1.0f);
                this.create_line(e, this.docking(1, e), counter, nl_r, nl_g, nl_b, nl_a);
                counter += 12;
            }
            else {
                if (!Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDAllPotions").get_value(true)) {
                    continue;
                }
                final String e = get_friendly_potion_name(effect) + " " + Potion.getPotionDurationString(effect, 1.0f);
                this.create_line(e, this.docking(1, e), counter, nl_r, nl_g, nl_b, nl_a);
                counter += 12;
            }
        }
        this.set_width(this.get("weakness", "width") + 12);
        this.set_height(this.get("weakness", "height") + 36);
    }
    
    public static String get_friendly_potion_name(final PotionEffect potionEffect) {
        String effectName = I18n.format(potionEffect.getPotion().getName(), new Object[0]);
        if (potionEffect.getAmplifier() == 1) {
            effectName = effectName + " " + I18n.format("enchantment.level.2", new Object[0]);
        }
        else if (potionEffect.getAmplifier() == 2) {
            effectName = effectName + " " + I18n.format("enchantment.level.3", new Object[0]);
        }
        else if (potionEffect.getAmplifier() == 3) {
            effectName = effectName + " " + I18n.format("enchantment.level.4", new Object[0]);
        }
        return effectName;
    }
    
    public static String get_name_duration_string(final PotionEffect potionEffect) {
        return String.format("%s (%s)", get_friendly_potion_name(potionEffect), Potion.getPotionDurationString(potionEffect, 1.0f));
    }
}
