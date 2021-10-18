// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import net.minecraft.entity.passive.EntityMule;
import me.sazked.leux.client.modules.Category;
import net.minecraft.entity.passive.EntityDonkey;
import java.util.function.Consumer;
import net.minecraft.entity.passive.EntityLlama;
import me.sazked.leux.client.util.MessageUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class EntitySearch extends Module
{
    public int delay;
    public Setting donkeys;
    public Setting llamas;
    public Setting slimes;
    public Setting mules;
    
    public void lambda$null$0(final Entity obf) {
        if (obf instanceof EntitySlime && this.delay == 0) {
            MessageUtil.send_client_message("Found a slime at: " + Math.round(obf.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(obf.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(obf.lastTickPosZ));
            this.delay = 200;
        }
    }
    
    @Override
    public void enable() {
        this.delay = 0;
    }
    
    public void lambda$null$2(final Entity obf) {
        if (obf instanceof EntityLlama && this.delay == 0) {
            MessageUtil.send_client_message("Found a llama at: " + Math.round(obf.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(obf.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(obf.lastTickPosZ));
            this.delay = 200;
        }
        if (this.mules.get_value(true)) {
            EntitySearch.mc.world.loadedEntityList.forEach(this::lambda$null$1);
        }
    }
    
    public void lambda$update$3(final Entity obf) {
        if (obf instanceof EntityDonkey && this.delay == 0) {
            MessageUtil.send_client_message("Found a donkey at: " + Math.round(obf.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(obf.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(obf.lastTickPosZ));
            this.delay = 200;
        }
        if (this.llamas.get_value(true)) {
            EntitySearch.mc.world.loadedEntityList.forEach(this::lambda$null$2);
        }
    }
    
    @Override
    public void update() {
        if (this.delay > 0) {
            --this.delay;
        }
        if (this.donkeys.get_value(true)) {
            EntitySearch.mc.world.loadedEntityList.forEach(this::lambda$update$3);
        }
    }
    
    public EntitySearch() {
        super(Category.misc);
        this.donkeys = this.create("Donkeys", "Donkeys", true);
        this.llamas = this.create("Llamas", "Llamas", true);
        this.mules = this.create("Mules", "Mules", true);
        this.slimes = this.create("Slimes", "Slimes", false);
        this.name = "Entity Search";
        this.tag = "EntitySearch";
        this.description = "sends a message when finds an entity";
    }
    
    public void lambda$null$1(final Entity obf) {
        if (obf instanceof EntityMule && this.delay == 0) {
            MessageUtil.send_client_message("Found a mule at: " + Math.round(obf.lastTickPosX) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(obf.lastTickPosY) + ChatFormatting.GRAY + ", " + ChatFormatting.WHITE + Math.round(obf.lastTickPosZ));
            this.delay = 200;
        }
        if (this.slimes.get_value(true)) {
            EntitySearch.mc.world.loadedEntityList.forEach(this::lambda$null$0);
        }
    }
}
