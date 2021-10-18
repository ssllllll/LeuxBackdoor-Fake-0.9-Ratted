// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import net.minecraft.world.World;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import me.sazked.leux.client.modules.Module;

public class FakePlayer extends Module
{
    public EntityOtherPlayerMP fake_player;
    public Setting copy;
    
    public FakePlayer() {
        super(Category.exploit);
        this.copy = this.create("Copy Inv", "CopyInv", true);
        this.name = "Fake Player";
        this.tag = "FakePlayer";
        this.description = "hahahaaha what a noob its in beta ahahahahaha";
    }
    
    @Override
    public void disable() {
        try {
            FakePlayer.mc.world.removeEntity((Entity)this.fake_player);
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void enable() {
        (this.fake_player = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("192bfaa7-3fe9-450d-bab3-80e6c93f3d1b"), "CRYSTALPVPFEMBOY"))).copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        this.fake_player.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        if (this.copy.get_value(true)) {
            this.fake_player.inventory.copyInventory(FakePlayer.mc.player.inventory);
        }
        FakePlayer.mc.world.addEntityToWorld(-100, (Entity)this.fake_player);
    }
}
