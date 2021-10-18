// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.gui.inventory.GuiInventory;
import org.lwjgl.opengl.GL11;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class PlayerModel extends Pinnable
{
    public PlayerModel() {
        super("Player Model", "PlayerModel", 1.0f, 0, 0);
        this.set_width(40);
        this.set_height(100);
    }
    
    @Override
    public void render() {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        final EntityLivingBase player = (EntityLivingBase)this.mc.player;
        final float yaw = this.interpolateAndWrap(player.prevRotationYaw, player.rotationYaw);
        final float pitch = this.interpolateAndWrap(player.prevRotationPitch, player.rotationPitch);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GuiInventory.drawEntityOnScreen(this.get_x() + 20, this.get_y() + 80, 40, -yaw, -pitch, player);
    }
    
    private float interpolateAndWrap(final Float prev, final Float current) {
        return MathHelper.wrapDegrees(prev + (current - prev) * this.mc.getRenderPartialTicks());
    }
}
