// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import me.sazked.leux.client.modules.Category;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Brightness extends Module
{
    public float old_gamma_value;
    public Setting mode;
    
    @Override
    public void update() {
        if (this.mode.in("Gamma")) {
            Brightness.mc.gameSettings.gammaSetting = Float.intBitsToFloat(Float.floatToIntBits(0.023378788f) ^ 0x7D1F84DF);
        }
        else {
            Brightness.mc.gameSettings.gammaSetting = Float.intBitsToFloat(Float.floatToIntBits(14.508092f) ^ 0x7EE82125);
            Brightness.mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5210));
        }
    }
    
    @Override
    public String array_detail() {
        return this.mode.get_current_value();
    }
    
    @Override
    public void disable() {
        Brightness.mc.gameSettings.gammaSetting = this.old_gamma_value;
        Brightness.mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
    }
    
    public Brightness() {
        super(Category.render);
        this.mode = this.create("Mode", "Mode", "Gamma", this.combobox("Gamma", "Potion"));
        this.name = "Brightness";
        this.tag = "Brightness";
        this.description = "be like my dog :3";
    }
    
    @Override
    public void enable() {
        this.old_gamma_value = Brightness.mc.gameSettings.gammaSetting;
    }
}
