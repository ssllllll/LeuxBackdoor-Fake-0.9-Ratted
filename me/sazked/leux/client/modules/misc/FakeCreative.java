// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import me.sazked.leux.client.modules.Category;
import net.minecraft.world.GameType;
import me.sazked.leux.client.modules.Module;

public class FakeCreative extends Module
{
    @Override
    public void disable() {
        if (FakeCreative.mc.player != null && FakeCreative.mc.playerController != null) {
            if (!FakeCreative.mc.playerController.getCurrentGameType().equals((Object)GameType.SURVIVAL)) {
                FakeCreative.mc.playerController.setGameType(GameType.SURVIVAL);
                FakeCreative.mc.player.setGameType(GameType.SURVIVAL);
            }
        }
    }
    
    public FakeCreative() {
        super(Category.exploit);
        this.name = "Creative";
        this.tag = "Creative";
        this.description = "makes your gamemode creative in client side";
    }
    
    @Override
    public void update() {
        if (FakeCreative.mc.player == null || FakeCreative.mc.playerController == null) {
            return;
        }
        if (!FakeCreative.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            FakeCreative.mc.playerController.setGameType(GameType.CREATIVE);
            FakeCreative.mc.player.setGameType(GameType.CREATIVE);
        }
    }
    
    @Override
    public void enable() {
        if (FakeCreative.mc.player == null || FakeCreative.mc.playerController == null) {
            return;
        }
        FakeCreative.mc.playerController.setGameType(GameType.CREATIVE);
        FakeCreative.mc.player.setGameType(GameType.CREATIVE);
    }
}
